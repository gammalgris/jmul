/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

import java.io.IOException;
import java.io.OutputStream;

import jmul.logging.Logger;

import jmul.network.http.ResponseCode;


public class StatusCodeResponseHandler implements HttpHandler {

    /**
     * A reference to a logger.
     */
    private final Logger logger;

    /**
     * The site's path.
     */
    private final String path;

    /**
     * The expected status code upon invoking this site.
     */
    private final ResponseCode responseCode;

    /**
     * Creates a new content handler according to the specified parameters.
     *
     * @param aLogger
     *        a logger instance
     * @param aPath
     *        the published path
     * @param aResponseCode
     *        the response code which is to be returned
     */
    public StatusCodeResponseHandler(Logger aLogger, String aPath, ResponseCode aResponseCode) {

        logger = aLogger;

        path = aPath;
        responseCode = aResponseCode;
    }

    /**
     * Returns the path of the web page.
     *
     * @return a path
     *        the published path
     */
    public String getPath() {

        return path;
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

        String message = "requested page: " + getPath();
        logger.logInfo(message);

        httpExchange.sendResponseHeaders(responseCode.getValue(), 0);

        OutputStream os = httpExchange.getResponseBody();
        os.close();
    }

}
