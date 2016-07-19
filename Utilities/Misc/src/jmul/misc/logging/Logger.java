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


/**
 * This interface describes a logger.
 *
 * @author Kristian Kutin
 */
public interface Logger {

    /**
     * Log a debug message.
     *
     * @param aMessage
     */
    void logDebug(String aMessage);

    /**
     * Log a warning message.
     *
     * @param aMessage
     */
    void logWarning(String aMessage);

    /**
     * Log an error message.
     *
     * @param aMessage
     */
    void logError(String aMessage);

    /**
     * Log an exception.
     *
     * @param anException
     */
    void logError(Throwable anException);

    /**
     * Log an info message.
     *
     * @param aMessage
     */
    void logInfo(String aMessage);

}
