/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.web;


import static jmul.misc.checks.ParameterCheckHelper.checkExceptionCause;
import static jmul.misc.checks.ParameterCheckHelper.checkExceptionMessage;


/**
 * An exception which can be thrown during the execution of a web server.
 *
 * @author Kristian Kutin
 */
public class WebServerException extends RuntimeException {

    /**
     * Constructs an exception.
     *
     */
    public WebServerException() {

        super();
    }

    /**
     * Constructs an exception.
     *
     * @param aMessage
     *        a message which provides details about the exception
     * @param aCause
     *        the cause of this exception
     */
    public WebServerException(String aMessage, Throwable aCause) {

        super(checkExceptionMessage(aMessage), checkExceptionCause(aCause));
    }

    /**
     * Constructs an exception
     *
     * @param aMessage
     *        a message which provides details about the exception
     */
    public WebServerException(String aMessage) {

        super(checkExceptionMessage(aMessage));
    }

    /**
     * Constructs an exception.
     *
     * @param aCause
     *        the cause of this exception
     */
    public WebServerException(Throwable aCause) {

        super(checkExceptionCause(aCause));
    }

}
