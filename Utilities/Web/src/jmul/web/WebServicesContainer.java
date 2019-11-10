/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.web;


import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;

import java.net.InetSocketAddress;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;

import jmul.io.FileHelper;

import jmul.logging.ConsoleLogger;
import jmul.logging.Logger;

import jmul.misc.management.ResourceContainer;

import jmul.web.logging.WebServerLogger;
import jmul.web.service.WebServiceConfiguration;
import jmul.web.service.WebServiceConfigurationReader;
import jmul.web.service.WebServiceConfigurationReaderImpl;
import jmul.web.service.WebServiceRequestHandler;

import org.xml.sax.SAXException;


/**
 * An implementation of a web server that provides web services.
 *
 * @author Kristian Kutin
 */
public class WebServicesContainer extends WebServerBase {

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param aBundleName
     *        the name of a resource bundle
     */
    public WebServicesContainer(String aBundleName) {

        super(aBundleName);
    }

    /**
     * The initialization of the actual web server.
     *
     * @throws IOException
     *         is thrown if the server cannot be initialized.
     */
    @Override
    protected void initWebServer() throws IOException {

        ResourceContainer resourceContainer = getResourceContainer();
        ConfigurationReader configurationReader = getConfigurationReader();


        Logger logger = new WebServerLogger(new ConsoleLogger());
        resourceContainer.putResource(WebServerResourcesKeys.LOGGER, logger);


        InetSocketAddress address = new InetSocketAddress(configurationReader.getPort());
        HttpServer server = HttpServer.create(address, configurationReader.getBacklogSize());

        // Change due to https://stackoverflow.com/questions/14729475/can-i-make-a-java-httpserver-threaded-process-requests-in-parallel
        server.setExecutor(Executors.newCachedThreadPool());

        resourceContainer.putResource(WebServerResourcesKeys.SERVER, server);


        bindWebServices(scanWebServiceConfiguration());
    }

    /**
     * Scans the web content directory for content files.
     *
     * @return a list of content files
     */
    private List<File> scanWebServiceConfiguration() {

        ConfigurationReader configurationReader = getConfigurationReader();

        List<File> allResults = new ArrayList<>();

        File baseDir = configurationReader.getBaseDirectory();
        String[] contentTypes = configurationReader.getContentTypes();

        logContentTypes();


        for (String contentType : contentTypes) {

            Collection<File> results = FileHelper.getFiles(baseDir, contentType, true);
            allResults.addAll(results);
        }

        return allResults;
    }

    /**
     * Binds the web service handlers to the web server.
     *
     * @param someContentFiles
     */
    private void bindWebServices(List<File> someConfigurationFiles) {

        ResourceContainer resourceContainer = getResourceContainer();

        WebServiceConfigurationReader reader = new WebServiceConfigurationReaderImpl();

        HttpServer server = (HttpServer) resourceContainer.getResource(WebServerResourcesKeys.SERVER);
        Logger logger = (Logger) resourceContainer.getResource(WebServerResourcesKeys.LOGGER);


        for (File file : someConfigurationFiles) {

            String message = "found configuration: \"" + file + "\"";
            logger.logInfo(message);

            WebServiceConfiguration configuration;

            try {

                configuration = reader.readConfiguration(file);

            } catch (IOException | SAXException e) {

                String message2 = "Unable to read the configuration file " + file.getAbsolutePath() + "!";

                logger.logError(message2);
                logger.logError(e);

                continue;
            }

            WebServiceRequestHandler handler = new WebServiceRequestHandler(logger, configuration);

            String path = handler.getPath();
            server.createContext(path, handler);

            message = "web service registered as \"" + path + "\"";
            logger.logInfo(message);
        }
    }
}
