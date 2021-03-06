/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.persistence;


import static jmul.checks.ParameterCheckHelper.checkExceptionCause;
import static jmul.checks.ParameterCheckHelper.checkExceptionMessage;


/**
 * This is exception is thrown if a persistence mechanism runs into an error
 * which requires some error handling.
 *
 * @author Kristian Kutin
 */
public class InvalidRootNodeException extends Exception {

    /**
     * The serial UID as required by java's serialization mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an exception.
     *
     * @param aCause
     *        the cause for this exception
     */
    public InvalidRootNodeException(Throwable aCause) {

        super(checkExceptionCause(aCause));
    }

    /**
     * Constructs an exception.
     *
     * @param aMessage
     *        an exception message
     * @param aCause
     *        the cause for this exception
     */
    public InvalidRootNodeException(String aMessage, Throwable aCause) {

        super(checkExceptionMessage(aMessage), checkExceptionCause(aCause));
    }

    /**
     * Constructs an exception.
     *
     * @param aMessage
     *        an exception message
     */
    public InvalidRootNodeException(String aMessage) {

        super(checkExceptionMessage(aMessage));
    }

    /**
     * The default constructor.
     */
    public InvalidRootNodeException() {

        super();
    }

}
