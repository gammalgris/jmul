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

import java.io.File;
import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import jmul.io.FileHelper;

import jmul.logging.ConsoleLogger;
import jmul.logging.Logger;

import jmul.misc.management.ResourceContainer;
import jmul.misc.management.ResourceContainerImpl;
import jmul.misc.state.State;

import jmul.network.http.ResponseCode;
import jmul.network.http.ResponseCodes;

import static jmul.string.Constants.COMMA;
import static jmul.string.Constants.FILE_SEPARATOR;
import static jmul.string.Constants.NEW_LINE;
import static jmul.string.Constants.SLASH;
import static jmul.string.Constants.TABULATOR;

import static jmul.web.WebServerStates.ERROR;
import static jmul.web.WebServerStates.INITIALIZATION;
import static jmul.web.WebServerStates.INITIALIZED;
import static jmul.web.WebServerStates.RUNNING;
import static jmul.web.WebServerStates.STARTING;
import static jmul.web.WebServerStates.STOPPED;
import static jmul.web.WebServerStates.STOPPING;
import static jmul.web.WebServerStates.UNINITIALIZED;
import jmul.web.logging.WebServerLogger;
import jmul.web.page.PageHandler;
import jmul.web.page.StatusCodeResponseHandler;


/**
 * An implementation of a web server that makes static content accessible.
 *
 * @author Kristian Kutin
 */
public class StaticContentWebServer implements WebServer {

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
     * Creates a new instance according to the specified parameter.
     *
     * @param aBundleName
     *        the name of a resource bundle
     */
    public StaticContentWebServer(String aBundleName) {

        serverState = UNINITIALIZED;

        serverState = serverState.transitionTo(INITIALIZATION);


        configurationReader = new ConfigurationReader(aBundleName);


        try {

            initWebServer();

        } catch (Exception e) {

            serverState = serverState.transitionTo(ERROR);
            throw new WebServerException(e);
        }


        Runtime.getRuntime().addShutdownHook(new SigintHandler());


        serverState = serverState.transitionTo(INITIALIZED);
        logCurrentServerState();
    }

    /**
     * The initialization of the actual web server.
     *
     * @throws IOException
     *         is thrown if the server cannot be initialized.
     */
    private void initWebServer() throws IOException {

        resourceContainer = new ResourceContainerImpl();


        Logger logger = new WebServerLogger(new ConsoleLogger());
        resourceContainer.putResource(WebServerResourcesKeys.LOGGER, logger);


        InetSocketAddress address = new InetSocketAddress(configurationReader.getPort());
        HttpServer server = HttpServer.create(address, configurationReader.getBacklogSize());
        resourceContainer.putResource(WebServerResourcesKeys.SERVER, server);


        bindWebContent(scanWebContent());
        bindMainPage();
        bindStatusCodeResponseContent();
    }

    /**
     * Scans the web content directory for content files.
     *
     * @return a list of content files
     */
    private List<File> scanWebContent() {

        List<File> allResults = new ArrayList<>();

        File baseDir = configurationReader.getBaseDirectory();
        String[] contentTypes = configurationReader.getContentyTypes();

        logContentTypes();


        for (String contentType : contentTypes) {

            Collection<File> results = FileHelper.getFiles(baseDir, contentType, true);
            allResults.addAll(results);
        }

        return allResults;
    }

    /**
     * Binds the page handlers to the web server.
     *
     * @param someContentFiles
     */
    private void bindWebContent(List<File> someContentFiles) {

        HttpServer server = (HttpServer) resourceContainer.getResource(WebServerResourcesKeys.SERVER);
        Logger logger = (Logger) resourceContainer.getResource(WebServerResourcesKeys.LOGGER);

        File baseDir = configurationReader.getBaseDirectory();


        for (File file : someContentFiles) {

            String message = "found content: \"" + file + "\"";
            logger.logInfo(message);

            PageHandler handler = new PageHandler(logger, baseDir, file);
            String path = handler.getPath();
            server.createContext(path, handler);

            message = "content registered as \"" + path + "\"";
            logger.logInfo(message);
        }
    }

    /**
     * Binds a main page if one was specified.
     */
    private void bindMainPage() {

        HttpServer server = (HttpServer) resourceContainer.getResource(WebServerResourcesKeys.SERVER);
        Logger logger = (Logger) resourceContainer.getResource(WebServerResourcesKeys.LOGGER);

        File baseDir = configurationReader.getBaseDirectory();

        if (!configurationReader.existsMainPage()) {

            logger.logInfo("No main page has been specified.");
            return;
        }

        String relativePath = configurationReader.getMainPage();
        String absolutePath = baseDir.getAbsolutePath() + FILE_SEPARATOR + FILE_SEPARATOR + relativePath;
        File file = new File(absolutePath);

        String message = "found content: \"" + file + "\"";
        logger.logInfo(message);

        PageHandler handler = new PageHandler(logger, baseDir, file);
        server.createContext(SLASH, handler);

        message = "content registered as main page";
        logger.logInfo(message);
    }

    /**
     * Binds pages which return only special response codes.
     */
    private void bindStatusCodeResponseContent() {

        HttpServer server = (HttpServer) resourceContainer.getResource(WebServerResourcesKeys.SERVER);
        Logger logger = (Logger) resourceContainer.getResource(WebServerResourcesKeys.LOGGER);

        for (ResponseCode code : ResponseCodes.values()) {

            if (!configurationReader.existSpecialResponseContent(code)) {

                continue;
            }


            for (String path : configurationReader.getSpecialResponseContent(code)) {

                String message =
                    "special response content: status code (" + code.getValue() + "); path: \"" + path + "\"";
                logger.logInfo(message);

                String fullPath;
                if (path.startsWith(SLASH)) {

                    fullPath = path;

                } else {

                    fullPath = SLASH + path;
                }

                StatusCodeResponseHandler handler = new StatusCodeResponseHandler(logger, fullPath, code);
                server.createContext(fullPath, handler);

                message = "content registered as \"" + path + "\"";
                logger.logInfo(message);
            }
        }
    }

    /**
     * Returns a reference to a logger.
     *
     * @return a logger
     */
    private Logger getLogger() {

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
     * Logs the allowed content types for manual validation.
     */
    private void logContentTypes() {

        String[] contentTypes = configurationReader.getContentyTypes();


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
     * This handler listens to a control+c event.
     */
    class SigintHandler extends Thread {

        /**
         * The default constructor.
         */
        public SigintHandler() {

            setName(this.getClass().getSimpleName());
        }

        /**
         * Initiates the shutdown of this web server instance.
         */
        @Override
        public void run() {

            stopServer();
        }
    }

}


/**
 * A utility class for reading the web server configuration.
 *
 * @author Kristian Kutin
 */
class ConfigurationReader {

    /**
     * A property key.
     */
    private static final String SOCKET_PORT_KEY = "socket.port";

    /**
     * A property key.
     */
    private static final String SOCKET_BACKLOG_KEY = "socket.backlog";

    /**
     * A property key.
     */
    private static final String SOCKET_SHUTDOWN_TIME_KEY = "socket.shutdown-time";

    /**
     * A property key.
     */
    private static final String CONTENT_BASE_DIRECTORY_KEY = "content.base-directory";

    /**
     * A property key.
     */
    private static final String CONTENT_CONTENT_TYPES_KEY = "content.content-types";

    /**
     * A property key (optional).
     */
    private static final String CONTENT_MAIN_PAGE_KEY = "content.main-page";

    /**
     * A property key (optional).
     */
    private static final String SPECIAL_RESPONSE_PREFIX_KEY = "content.special-response.";

    /**
     * The name of a resource bundle.
     */
    private final String bundleName;

    /**
     * Creates a configuration reader according to the specified parameter.
     *
     * @param aBundleName
     */
    ConfigurationReader(String aBundleName) {

        bundleName = aBundleName;
    }

    /**
     * Returns the resource bundle which contains the confiugration for this web server.
     *
     * @return a resource bundle
     */
    private ResourceBundle getBundle() {

        return ResourceBundle.getBundle(bundleName);
    }

    /**
     * Returns the string value for the specified property key.
     *
     * @param aKey
     *
     * @return a string value
     */
    private String getString(String aKey) {

        ResourceBundle bundle = getBundle();

        return bundle.getString(aKey);
    }

    /**
     * Returns the integer value for the specified property key.
     *
     * @param aKey
     *
     * @return an integer value
     */
    private int getInteger(String aKey) {

        String s = getString(aKey);
        return Integer.parseInt(s);
    }

    /**
     * Returns the port under which the web server will reply.
     *
     * @return a port
     */
    public int getPort() {

        return getInteger(SOCKET_PORT_KEY);
    }

    /**
     * Returns the backlog size which determines how many requests the web server will
     * accept.
     *
     * @return the backlog size
     */
    public int getBacklogSize() {

        return getInteger(SOCKET_BACKLOG_KEY);
    }

    /**
     * Returns the shutdown time the web server is epxetced to wait before closing
     * all remaining TCP connections.
     *
     * @return a shutdown time
     */
    public int getShutdownTime() {

        return getInteger(SOCKET_SHUTDOWN_TIME_KEY);
    }

    /**
     * Returns the base directory of the web content.
     *
     * @return a base directoy
     */
    public File getBaseDirectory() {

        String s = getString(CONTENT_BASE_DIRECTORY_KEY);
        return new File(s);
    }

    /**
     * Returns the content types which are to be scanned within the web content.
     *
     * @return content types (i.e. file suffixes)
     */
    public String[] getContentyTypes() {

        String s = getString(CONTENT_CONTENT_TYPES_KEY);
        s = s.trim();

        return s.split(COMMA);
    }

    /**
     * Checks if a main page has been specified.
     *
     * @return <code>true</code> if a main page has been specified,
     *         else <code>false</code>
     */
    public boolean existsMainPage() {

        return getBundle().containsKey(CONTENT_MAIN_PAGE_KEY);
    }

    /**
     * Returns the specified main page.
     *
     * @return a relative main page path
     */
    public String getMainPage() {

        return getString(CONTENT_MAIN_PAGE_KEY);
    }

    /**
     * Builds a property key according to the specified parameters.
     *
     * @param aResponseCode
     *
     * @return a property key
     */
    private static String buildSpecialResponseContentKey(ResponseCode aResponseCode) {

        return SPECIAL_RESPONSE_PREFIX_KEY + aResponseCode.getValue();
    }

    /**
     * Checks if special response content for the specified response code
     * exists.
     *
     * @param aResponseCode
     *
     * @return <code>true</code> if special response content exists,
     *         else <code>false</code>
     */
    public boolean existSpecialResponseContent(ResponseCode aResponseCode) {

        String key = buildSpecialResponseContentKey(aResponseCode);
        return getBundle().containsKey(key);
    }

    /**
     * Returns the pages (i.e. their paths) that are supposed to return the
     * specified status code.
     *
     * @param aResponseCode
     *
     * @return a list of paths
     */
    public String[] getSpecialResponseContent(ResponseCode aResponseCode) {

        String key = buildSpecialResponseContentKey(aResponseCode);
        String s = getString(key);
        s = s.trim();

        return s.split(COMMA);
    }

}
