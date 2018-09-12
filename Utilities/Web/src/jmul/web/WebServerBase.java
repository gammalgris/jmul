/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

package jmul.web;


import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import jmul.logging.Logger;

import jmul.misc.management.ResourceContainer;
import jmul.misc.management.ResourceContainerImpl;
import jmul.misc.state.State;

import static jmul.string.Constants.NEW_LINE;
import static jmul.string.Constants.TABULATOR;

import static jmul.web.WebServerStates.ERROR;
import static jmul.web.WebServerStates.INITIALIZATION;
import static jmul.web.WebServerStates.INITIALIZED;
import static jmul.web.WebServerStates.RUNNING;
import static jmul.web.WebServerStates.STARTING;
import static jmul.web.WebServerStates.STOPPED;
import static jmul.web.WebServerStates.STOPPING;
import static jmul.web.WebServerStates.UNINITIALIZED;


/**
 * A base implementation for a web server.
 *
 * @author Kristian Kutin
 */
abstract class WebServerBase implements WebServer {

    /**
     * The web server state.
     */
    private State serverState;

    /**
     * All web server resources.
     */
    private ResourceContainer resourceContainer;

    /**
     * A configuration reader.
     */
    private ConfigurationReader configurationReader;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aBundleName
     *        the name of a resource bundle
     */
    protected WebServerBase(String aBundleName) {

        super();

        serverState = UNINITIALIZED;
        serverState = serverState.transitionTo(INITIALIZATION);


        resourceContainer = new ResourceContainerImpl();
        configurationReader = new ConfigurationReader(aBundleName);


        try {

            initWebServer();

        } catch (Exception e) {

            serverState = serverState.transitionTo(ERROR);
            throw new WebServerException(e);
        }


        Runtime.getRuntime().addShutdownHook(new SigintHandler(this));


        serverState = serverState.transitionTo(INITIALIZED);
        logCurrentServerState();
    }

    /**
     * The actual initialization is delegated to a subclass/ an implementation class.
     *
     * @throws IOException
     *         is thrown if a error occurrs during initialization
     */
    protected abstract void initWebServer() throws IOException;

    /**
     * A getter method.
     *
     * @return a reference to a resource container.
     */
    protected ResourceContainer getResourceContainer() {

        return resourceContainer;
    }

    /**
     * A getter method.
     *
     * @return a reference to a configuration reader.
     */
    protected ConfigurationReader getConfigurationReader() {

        return configurationReader;
    }

    /**
     * Returns a reference to a logger.
     *
     * @return a logger
     */
    protected Logger getLogger() {

        return (Logger) resourceContainer.getResource(WebServerResourcesKeys.LOGGER);
    }

    /**
     * Logs the current web server state.
     */
    private void logCurrentServerState() {

        getLogger().logInfo(getWebServerState().getStateName());
    }

    /**
     * Logs the current web server confiugration.
     */
    private void logCurrentConfiguration() {

        HttpServer server = (HttpServer) resourceContainer.getResource(WebServerResourcesKeys.SERVER);
        InetSocketAddress address = server.getAddress();


        StringBuilder message = new StringBuilder();

        message.append(NEW_LINE);
        message.append(NEW_LINE);


        message.append(TABULATOR);
        message.append("host");
        message.append(NEW_LINE);

        try {

            message.append(TABULATOR);
            message.append(TABULATOR);
            message.append("name: ");
            message.append(address.getAddress().getLocalHost());
            message.append(NEW_LINE);

        } catch (UnknownHostException e) {

            message.append(TABULATOR);
            message.append(TABULATOR);
            message.append("name: ");
            message.append(e.getClass().getName());
            message.append(" \"");
            message.append(e.getMessage());
            message.append("\"");
            message.append(NEW_LINE);
        }

        message.append(TABULATOR);
        message.append(TABULATOR);
        message.append("ip address: ");
        message.append(address.getAddress().getHostAddress());
        message.append(NEW_LINE);

        message.append(NEW_LINE);


        message.append(TABULATOR);
        message.append("socket");
        message.append(NEW_LINE);

        message.append(TABULATOR);
        message.append(TABULATOR);
        message.append("port: ");
        message.append(configurationReader.getPort());
        message.append(NEW_LINE);

        message.append(TABULATOR);
        message.append(TABULATOR);
        message.append("backlog size: ");
        int backlogSize = configurationReader.getBacklogSize();
        if (backlogSize <= 0) {

            message.append("system default");

        } else {

            message.append(backlogSize);
        }
        message.append(NEW_LINE);

        message.append(TABULATOR);
        message.append(TABULATOR);
        message.append("shutdown delay: ");
        message.append(configurationReader.getShutdownTime());
        message.append(" (sec)");
        message.append(NEW_LINE);


        getLogger().logInfo(message.toString());
    }

    /**
     * Returns the current stwte of the web server.
     *
     * @return the current state
     */
    @Override
    public State getWebServerState() {

        return serverState;
    }

    /**
     * Starts the web server.
     */
    @Override
    public void startServer() {

        serverState = serverState.transitionTo(STARTING);
        logCurrentServerState();

        try {

            HttpServer server = (HttpServer) resourceContainer.getResource(WebServerResourcesKeys.SERVER);
            server.setExecutor(null);
            server.start();

        } catch (Exception e) {

            serverState = serverState.transitionTo(ERROR);
            logCurrentServerState();
            throw new WebServerException(e);
        }

        logCurrentConfiguration();

        serverState = serverState.transitionTo(RUNNING);
        logCurrentServerState();
    }

    /**
     * Stops the web server.
     */
    @Override
    public void stopServer() {

        serverState = serverState.transitionTo(STOPPING);
        logCurrentServerState();

        try {

            HttpServer server = (HttpServer) resourceContainer.getResource(WebServerResourcesKeys.SERVER);
            server.stop(configurationReader.getShutdownTime());

        } catch (Exception e) {

            serverState = serverState.transitionTo(ERROR);
            logCurrentServerState();
            throw new WebServerException(e);
        }

        serverState = serverState.transitionTo(STOPPED);
        logCurrentServerState();
    }

    /**
     * Logs the allowed content types for manual validation.
     */
    protected void logContentTypes() {

        String[] contentTypes = configurationReader.getContentTypes();


        StringBuilder message = new StringBuilder();

        message.append(NEW_LINE);
        message.append(NEW_LINE);


        message.append(TABULATOR);
        message.append("allowed content types");
        message.append(NEW_LINE);


        for (String contentType : contentTypes) {

            message.append(TABULATOR);
            message.append(TABULATOR);
            message.append(contentType);
            message.append(NEW_LINE);
        }


        getLogger().logInfo(message.toString());
    }

}
