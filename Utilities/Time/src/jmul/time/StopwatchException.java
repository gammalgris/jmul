/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
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

package jmul.time;


import jmul.misc.checks.ParameterCheckHelper;


/**
 * This exception is thrown if the corresponding stopwatch encounters an error.
 *
 * @author Kristian Kutin
 */
public class StopwatchException extends RuntimeException {

    /**
     * The default constructor.
     */
    public StopwatchException() {

        super();
    }

    /**
     * Creates a new Exception according to the specified parameters.
     *
     * @param aMessage
     */
    public StopwatchException(String aMessage) {

        super(ParameterCheckHelper.checkExceptionMessage(aMessage));
    }

    /**
     * Creates a new Exception according to the specified parameters.
     *
     * @param aCause
     */
    public StopwatchException(Throwable aCause) {

        super(ParameterCheckHelper.checkExceptionCause(aCause));
    }

    /**
     * Creates a new Exception according to the specified parameters.
     *
     * @param aMessage
     * @param aCause
     */
    public StopwatchException(String aMessage, Throwable aCause) {

        super(ParameterCheckHelper.checkExceptionMessage(aMessage), ParameterCheckHelper.checkExceptionCause(aCause));
    }

}