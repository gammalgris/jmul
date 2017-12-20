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

package jmul.web.page;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import jmul.logging.Logger;

import static jmul.network.http.ResponseCodes.RC200;


/**
 * This is an implementation of a page handler which loads a page only
 * after the page has been requested.
 *
 * @author Kristian Kutin
 */
public class PageHandler implements HttpHandler {

    /**
     * A reference to a logger.
     */
    private final Logger logger;

    /**
     * The actual page loader.
     */
    private final PageLoader loader;

    /**
     * The loaded web content.
     */
    private PublishedPage page;

    /**
     * Creates a new content handler according to the specified parameters.
     *
     * @param aLogger
     *        a logger instance
     * @param aBaseDirectory
     *        the base directory for the web content
     * @param aFile
     *        the file which is published
     */
    public PageHandler(Logger aLogger, File aBaseDirectory, File aFile) {

        logger = aLogger;

        loader = new PageLoader(aBaseDirectory, aFile);
        page = null;
    }

    /**
     * Returns the path of the web page.
     *
     * @return a path
     */
    public String getPath() {

        return loader.getPath();
    }

    /**
     * Handles a request and returns the web content to the caller.
     *
     * @param httpExchange
     *        the request which is answered
     *
     * @throws IOException
     *         is thrown if an error occurs while delivering the web content
     *         to the caller
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        synchronized (this) {

            if (page == null) {

                try {

                    page = loader.loadContent();

                } catch (Exception e) {

                    logger.logError(e);
                    throw new PageHandlerException(e);
                }
            }
        }


        String path = page.getPath();
        byte[] content = page.getContent();


        String message = "requested page: " + path;
        logger.logInfo(message);


        httpExchange.sendResponseHeaders(RC200.getValue(), content.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(content);
        os.close();
    }

}
