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

package jmul.web.service;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;

import jmul.external.Command;
import jmul.external.CommandImpl;
import jmul.external.CommandInvoker;
import jmul.external.CommandInvokerImpl;
import jmul.external.ExternalProcessExecutionException;
import jmul.external.InvocationResult;
import jmul.external.InvocationResultImpl;

import jmul.logging.Logger;

import static jmul.network.http.ResponseCodes.RC200;
import static jmul.network.http.ResponseCodes.RC500;
import static jmul.network.http.ResponseCodes.RC503;

import static jmul.string.Constants.FILE_SEPARATOR;
import static jmul.string.Constants.NEW_LINE;
import static jmul.string.Constants.SLASH;


/**
 * This implementation of a http handler invokes an external script and
 * returns the success and the output with the response.
 *
 * @author Kristian Kutin
 */
public class WebServiceRequestHandler implements HttpHandler {

    /**
     * A reference to a logger.
     */
    private final Logger logger;

    /**
     * The configuration of this web service.
     */
    private final WebServiceConfiguration configuration;

    /**
     * Creates a new web service request handler according to the specified parameters.
     *
     * @param aLogger
     *        a reference to a logger
     * @param aConfiguration
     *        a configuration
     */
    public WebServiceRequestHandler(Logger aLogger, WebServiceConfiguration aConfiguration) {

        super();

        logger = aLogger;
        configuration = aConfiguration;
    }

    /**
     * Returns the path of the web page.
     *
     * @return a path
     */
    public String getPath() {

        String path = configuration.getWebPath();
        path = path.replace(FILE_SEPARATOR, SLASH);

        if (path.startsWith(SLASH)) {

            return path;

        } else {

            return SLASH + path;
        }
    }

    /**
     * Returns the external command which is to be invoked.
     *
     * @return an external command
     */
    public Command getCommand() {

        List<String> allComponents = new ArrayList<>();
        allComponents.add(configuration.getScriptPath());

        for (String s : configuration.getParameters()) {

            allComponents.add(s);
        }

        return new CommandImpl(allComponents.toArray(new String[] { }));
    }

    /**
     * The output of the invocation result is returned.
     *
     * @param aResult
     *        the invocation result
     *
     * @return the output of a command invocation
     */
    private static String getOutput(InvocationResult aResult) {

        String standardOutput = aResult.getStandardOutput();
        String errorOutput = aResult.getErrorOutput();

        // There is a chance that due to timing issues the output which is
        // supposed to be captured, is missing.

        StringBuilder buffer = new StringBuilder();

        if (standardOutput != null) {

            buffer.append(standardOutput);
        }

        if (errorOutput != null) {

            if (standardOutput != null) {

                buffer.append(NEW_LINE);
            }

            buffer.append(errorOutput);
        }

        return buffer.toString();
    }

    /**
     * Handles a request and returns the output of the underlying external script to the caller.
     *
     * @param httpExchange
     *        the request which is answered
     *
     * @throws IOException
     *         is thrown if an error occurs while delivering the response
     *         to the caller
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        Command command = getCommand();
        String path = getPath();

        logger.logInfo("requested web service: " + path);
        logger.logInfo("invoked script: " + command);


        CommandInvoker invoker = new CommandInvokerImpl(logger);
        InvocationResult result;

        try {

            result = invoker.invoke(command);

        } catch (ExternalProcessExecutionException e) {

            logger.logError(e);

            String message = e.getMessage();

            logger.logError(message);
            logger.logError(e);

            result = new InvocationResultImpl(1, "", message, true);
        }


        String output = getOutput(result);
        byte[] content = output.getBytes();

        if (result.wasSuccessful()) {

            if (result.hasIncompleteOutput()) {

                httpExchange.sendResponseHeaders(RC500.getValue(), content.length);

            } else {

                httpExchange.sendResponseHeaders(RC200.getValue(), content.length);
            }

        } else {

            if (result.hasIncompleteOutput()) {

                httpExchange.sendResponseHeaders(RC500.getValue(), content.length);

            } else {

                httpExchange.sendResponseHeaders(RC503.getValue(), content.length);
            }
        }


        OutputStream os = httpExchange.getResponseBody();
        os.write(content);
        os.close();
    }

}
