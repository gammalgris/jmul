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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jmul.io.FileHelper;

import jmul.logging.ConsoleLogger;
import jmul.logging.Logger;

import jmul.misc.management.ResourceContainer;

import jmul.network.http.ResponseCode;
import jmul.network.http.ResponseCodes;

import static jmul.string.Constants.FILE_SEPARATOR;
import static jmul.string.Constants.SLASH;

import jmul.web.logging.WebServerLogger;
import jmul.web.page.PageHandler;
import jmul.web.page.StatusCodeResponseHandler;


/**
 * An implementation of a web server that makes static content accessible.
 *
 * @author Kristian Kutin
 */
public class StaticContentWebServer extends WebServerBase {

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param aBundleName
     *        the name of a resource bundle
     */
    public StaticContentWebServer(String aBundleName) {

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
     * Binds the page handlers to the web server.
     *
     * @param someContentFiles
     */
    private void bindWebContent(List<File> someContentFiles) {

        ResourceContainer resourceContainer = getResourceContainer();
        ConfigurationReader configurationReader = getConfigurationReader();

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

        ResourceContainer resourceContainer = getResourceContainer();
        ConfigurationReader configurationReader = getConfigurationReader();

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

        ResourceContainer resourceContainer = getResourceContainer();
        ConfigurationReader configurationReader = getConfigurationReader();

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

}
