/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package jmul.logging;


/**
 * A simplistic implementation of a logger.
 *
 * TODO
 * For testing purposes this implementation is sufficient. In the long run a
 * new implementation (e.g. with log4j) is recommended.
 *
 * @author Kristian Kutin
 */
public class ApplicationLoggerImpl implements ApplicationLogger {

    /**
     * A constant text.
     */
    private static final String PREFIX = "@";

    /**
     * A constant text.
     */
    private static final String SEPARATOR = "::";

    /**
     * A constant text.
     */
    private static final String ERROR_PREFIX = "ERROR";

    /**
     * A constant text.
     */
    private static final String WARNING_PREFIX = "WARN";

    /**
     * A constant text.
     */
    private static final String INFO_PREFIX = "INFO";

    /**
     * The class member contains the name of this logger.
     */
    private final String name;

    /**
     * Constructs a logger.
     *
     * @param aName
     *        the name for this logger
     */
    public ApplicationLoggerImpl(String aName) {

        name = aName;
    }

    /**
     * The method prints a message to the console.
     *
     * @param aPrefix
     *        a category / prefix
     * @param aMessage
     *        a message
     */
    private void println(String aPrefix, String aMessage) {

        System.out.println(PREFIX + name + SEPARATOR + aPrefix + SEPARATOR +
                           aMessage);
    }

    /**
     * The method logError logs an error message. Exceptions that cannot
     * be handled or have a dire effect on the application should be logged
     * as error.
     *
     * @param aMessage
     *        the message is a String containing the error message
     */
    public void logError(String aMessage) {

        println(ERROR_PREFIX, aMessage);
    }

    /**
     * The method logWarning logs a warning. Exceptions that can be handled
     * and have no further effect on the application should be logged as
     * warning.
     *
     * @param aMessage
     *        the message is a String containing the warning
     */
    public void logWarning(String aMessage) {

        println(WARNING_PREFIX, aMessage);
    }

    /**
     * The method logInfo logs a message which is no warning and no error
     * (e.g. comments during the initializing phase to indicate the
     * configuration).
     *
     * @param aMessage
     *        the message is a String containing the information
     */
    public void logInfo(String aMessage) {

        println(INFO_PREFIX, aMessage);
    }

}
