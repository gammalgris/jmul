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

package jmul.external;


import java.io.IOException;

import jmul.logging.Logger;

import static jmul.string.Constants.NEW_LINE;


/**
 * An implementation of an external command ivoker.
 *
 * @author Kristian Kutin
 */
public class CommandInvokerImpl implements CommandInvoker {

    /**
     * A reference to a logger.
     */
    private Logger logger;

    /**
     * The default constructor.
     */
    public CommandInvokerImpl() {

        this(null);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aLogger
     *        a reference to a logger
     */
    public CommandInvokerImpl(Logger aLogger) {

        super();

        logger = aLogger;
    }

    /**
     * Invokes the specified command and returns the result.
     *
     * @param aCommand
     *        the command with all parameters
     *
     * @return a result
     */
    @Override
    public InvocationResult invoke(Command aCommand) {

        Runtime runtime = Runtime.getRuntime();

        Process process = null;

        try {

            process = runtime.exec(aCommand.toArray());

        } catch (IOException e) {

            String message = "The external process encountered an error!";
            throw new ExternalProcessExecutionException(message, e);
        }


        ProcessMonitor monitor = new ProcessMonitor(process, logger);

        try {

            // Assuming that the process ended, the exit code and console
            // outputs are stored.

            int exitValue = process.exitValue();
            return new InvocationResultImpl(exitValue, monitor.getOutputMessage(), monitor.getErrorMessage(),
                                            monitor.isCompleteOutput());

        } catch (IllegalThreadStateException e) {

            // The process has not finished yet. Wait for the next opportunity
            // to get the exit code and console outputs.

            try {

                int alternativeExitValue = process.waitFor();
                return new InvocationResultImpl(alternativeExitValue, monitor.getOutputMessage(),
                                                monitor.getErrorMessage(), monitor.isCompleteOutput());

            } catch (InterruptedException f) {

                logger.logWarning(f.getMessage());
                Thread.currentThread().interrupt();
            }

            String message = "The external process has exited with an error!" + NEW_LINE + monitor.getErrorMessage();
            throw new ExternalProcessExecutionException(message, e);
        }
    }

}
