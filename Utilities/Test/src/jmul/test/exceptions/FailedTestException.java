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

package jmul.test.exceptions;


import static jmul.checks.ParameterCheckHelper.checkExceptionCause;
import static jmul.checks.ParameterCheckHelper.checkExceptionMessage;


/**
 * This exception is thrown if a test fails.
 *
 * @author Kristian Kutin
 */
public class FailedTestException extends RuntimeException {

    /**
     * The default constructor.
     */
    public FailedTestException() {

        super();
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     *        the error message
     */
    public FailedTestException(String aMessage) {

        super(checkExceptionMessage(aMessage));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aCause
     *        the error cause
     */
    public FailedTestException(Throwable aCause) {

        super(checkExceptionCause(aCause));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     *        the error message
     * @param aCause
     *        the error cause
     */
    public FailedTestException(String aMessage, Throwable aCause) {

        super(checkExceptionMessage(aMessage), checkExceptionCause(aCause));
    }

}
