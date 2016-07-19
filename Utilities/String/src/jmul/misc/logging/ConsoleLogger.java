/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package jmul.misc.logging;


import java.io.PrintStream;

import static jmul.string.StringConstants.NEW_LINE;
import static jmul.string.StringConstants.TABULATOR;


/**
 * A simple implementation of a console logger.
 *
 * @author Kristian Kutin
 */
public class ConsoleLogger extends BaseLogger implements Logger {

    /**
     * Creates a new console logger.
     */
    public ConsoleLogger() {

        super();
    }

    /**
     * Creates a new console logger.
     *
     * @param aStandardOutput
     * @param anErrorOutput
     */
    public ConsoleLogger(PrintStream aStandardOutput, PrintStream anErrorOutput) {

        super(aStandardOutput, anErrorOutput);
    }

    /**
     * Log a debug message.
     *
     * @param aMessage
     */
    public void logDebug(String aMessage) {

        logMessage(LogLevels.DEBUG, aMessage);
    }

    /**
     * Log a warning message.
     *
     * @param aMessage
     */
    public void logWarning(String aMessage) {

        logMessage(LogLevels.WARNING, aMessage);
    }

    /**
     * Log an error message.
     *
     * @param aMessage
     */
    public void logError(String aMessage) {

        logMessage(LogLevels.ERROR, aMessage);
    }

    /**
     * Log an exception.
     *
     * @param anException
     */
    public void logError(Throwable anException) {

        StringBuffer buffer = new StringBuffer();

        buffer.append(anException.getMessage());
        buffer.append(NEW_LINE);

        buffer.append(TABULATOR);
        buffer.append("Class: ");
        buffer.append(anException.getClass());
        buffer.append(NEW_LINE);

        buffer.append(TABULATOR);
        buffer.append("Stacktrace:");
        buffer.append(NEW_LINE);

        for (StackTraceElement line : anException.getStackTrace()) {

            buffer.append(TABULATOR);
            buffer.append(String.valueOf(line));
            buffer.append(NEW_LINE);
        }

        logMessage(LogLevels.ERROR, String.valueOf(buffer));
    }

    /**
     * Log an info message.
     *
     * @param aMessage
     */
    public void logInfo(String aMessage) {

        logMessage(LogLevels.INFO, aMessage);
    }

}
