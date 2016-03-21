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
 * The interface ApplicationLogger describes the behaviour of an entity which
 * handles the logging of messages the application will publish.
 *
 * @author Kristian Kutin
 */
public interface ApplicationLogger {

    /**
     * The method logError logs an error message. Exceptions that cannot
     * be handled or have a dire effect on the application should be logged
     * as error.
     *
     * @param aMessage
     *        the message is a String containing the error message
     */
    void logError(String aMessage);

    /**
     * The method logWarning logs a warning. Exceptions that can be handled
     * and have no further effect on the application should be logged as
     * warning.
     *
     * @param aMessage
     *        the message is a String containing the warning
     */
    void logWarning(String aMessage);

    /**
     * The method logInfo logs a message which is no warning and no error
     * (e.g. comments during the initializing phase to indicate the
     * configuration).
     *
     * @param aMessage
     *        the message is a String containing the information
     */
    void logInfo(String aMessage);

}
