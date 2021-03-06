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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.web.logging;


import java.util.Date;

import jmul.logging.Logger;

import static jmul.string.Constants.TABULATOR;


/**
 * This is a custom implementation of a logger.
 *
 * @author Kristian Kutin
 */
public class WebServerLogger implements Logger {

    /**
     * The actual logger.
     */
    private Logger logger;

    /**
     * The default constructor.
     *
     * @param aLogger
     *        the logger which is wrapped
     */
    public WebServerLogger(Logger aLogger) {

        logger = aLogger;
    }

    /**
     * Returns a prefix which is appended to log messages.
     *
     * @return a prefix.
     */
    private static String getPrefix() {

        StringBuilder prefix = new StringBuilder();

        prefix.append(TABULATOR);
        prefix.append(Thread.currentThread().getName());

        prefix.append(TABULATOR);
        prefix.append(new Date());

        prefix.append(TABULATOR);

        return prefix.toString();
    }

    /**
     * Log a debug message.
     *
     * @param aMessage
     *        the log message
     */
    @Override
    public void logDebug(String aMessage) {

        logger.logDebug(getPrefix() + aMessage);
    }

    /**
     * Log a warning message.
     *
     * @param aMessage
     *        the log message
     */
    @Override
    public void logWarning(String aMessage) {

        logger.logWarning(getPrefix() + aMessage);
    }

    /**
     * Log an error message.
     *
     * @param aMessage
     *        the log message
     */
    @Override
    public void logError(String aMessage) {

        logger.logError(getPrefix() + aMessage);
    }

    /**
     * Log an exception.
     *
     * @param anException
     *        the exception which is to be logged
     */
    @Override
    public void logError(Throwable anException) {

        logger.logError(getPrefix() + anException);
    }

    /**
     * Log an info message.
     *
     * @param aMessage
     *        the log message
     */
    @Override
    public void logInfo(String aMessage) {

        logger.logInfo(getPrefix() + aMessage);
    }

}
