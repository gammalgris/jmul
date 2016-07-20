/*
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

import jmul.misc.logging.ConsoleLogger;
import jmul.misc.logging.Logger;
import jmul.misc.management.ResourceContainer;
import jmul.misc.management.ResourceContainerImpl;
import jmul.misc.state.State;

import static jmul.string.StringConstants.COMMA;
import static jmul.string.StringConstants.NEW_LINE;
import static jmul.string.StringConstants.TABULATOR;


/**
 * An implementation of a web server.
 *
 * @author Kristian Kutin
 */
public class WebServerImpl implements WebServer {

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
     */
    public WebServerImpl(String aBundleName) {

        serverState = WebServerState.UNINITIALIZED;

        serverState = serverState.transitionTo(WebServerState.INITIALIZATION);


        configurationReader = new ConfigurationReader(aBundleName);


        try {

            initWebServer();

        } catch (Exception e) {

            serverState = serverState.transitionTo(WebServerState.ERROR);
            throw new RuntimeException(e);
        }


        Runtime.getRuntime().addShutdownHook(new SigintHandler());


        serverState = serverState.transitionTo(WebServerState.INITIALIZED);
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
    }

    /**
     * Scans the web content directory for content files.
     *
     * @return a list of content files
     */
    private List<File> scanWebContent() {

        List<File> allResults = new ArrayList<File>();

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
     * Returns a reference to a logger.
     *
     * @return a logger
     */
    private Logger getLogger() {

        Logger logger = (Logger) resourceContainer.getResource(WebServerResourcesKeys.LOGGER);
        return logger;
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

            ;
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

        serverState = serverState.transitionTo(WebServerState.STARTING);
        logCurrentServerState();

        try {

            HttpServer server = (HttpServer) resourceContainer.getResource(WebServerResourcesKeys.SERVER);
            server.setExecutor(null);
            server.start();

        } catch (Exception e) {

            serverState = serverState.transitionTo(WebServerState.ERROR);
            logCurrentServerState();
            throw new RuntimeException(e);
        }

        logCurrentConfiguration();

        serverState = serverState.transitionTo(WebServerState.RUNNING);
        logCurrentServerState();
    }

    /**
     * Stops the web server.
     */
    @Override
    public void stopServer() {

        serverState = serverState.transitionTo(WebServerState.STOPPING);
        logCurrentServerState();

        try {

            HttpServer server = (HttpServer) resourceContainer.getResource(WebServerResourcesKeys.SERVER);
            server.stop(configurationReader.getShutdownTime());

        } catch (Exception e) {

            serverState = serverState.transitionTo(WebServerState.ERROR);
            logCurrentServerState();
            throw new RuntimeException(e);
        }

        serverState = serverState.transitionTo(WebServerState.STOPPED);
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

            setName("WebServer.SigintHandler");
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

        String s = bundle.getString(aKey);
        return s;
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
        int i = Integer.parseInt(s);

        return i;
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
        File f = new File(s);

        return f;
    }

    /**
     * Returns the content types which are to be scanned within the web content.
     *
     * @return content types (i.e. file suffixes)
     */
    public String[] getContentyTypes() {

        String s = getString(CONTENT_CONTENT_TYPES_KEY);
        s = s.trim();

        String[] contentTypes = s.split(COMMA);
        return contentTypes;
    }

}
