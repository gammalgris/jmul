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

package jmul.logging;


import java.io.PrintStream;


/**
 * A base implementation for loggers.
 *
 * @author Kristian Kutin
 *
 * @deprecated This package causes various naming issues. Use the new package messaging instead.
 */
@Deprecated
abstract class BaseLogger implements Logger {

    /**
     * The print stream for standard (INFO, DEBUG, WARNING) messages.
     */
    private PrintStream standard;

    /**
     * The print stream for error (ERROR) messages.
     */
    private PrintStream error;

    /**
     * Creates a new logger.
     */
    protected BaseLogger() {

        this(System.out, System.err);
    }

    /**
     * Creates a new logger.
     *
     * @param aStandardOutput
     * @param anErrorOutput
     */
    protected BaseLogger(PrintStream aStandardOutput, PrintStream anErrorOutput) {

        standard = aStandardOutput;
        error = anErrorOutput;
    }

    /**
     * Writes a log message according to the specified parameters.
     *
     * @param aLevel
     * @param aMessage
     */
    protected void logMessage(LogLevels aLevel, String aMessage) {

        StringBuilder buffer = new StringBuilder();

        buffer.append(getPrefix(aLevel));
        buffer.append(" ");
        buffer.append(aMessage);

        if (aLevel.equals(LogLevels.ERROR)) {

            error.println(String.valueOf(buffer));

        } else {

            standard.println(String.valueOf(buffer));
        }
    }

    /**
     * Returns a prefix according to the specified log level.
     *
     * @param aLevel
     *
     * @return a prefix
     */
    static String getPrefix(LogLevels aLevel) {

        return aLevel.name() + "::";
    }

}
