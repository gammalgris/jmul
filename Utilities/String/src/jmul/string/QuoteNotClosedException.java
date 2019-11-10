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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.string;


import jmul.checks.ParameterCheckHelper;


/**
 * A special exception implementation which is thrown if a quoted
 * section within a string isn't closed properly.
 *
 * @author Kristian Kutin
 */
public class QuoteNotClosedException extends RuntimeException {

    /**
     * The serial UID as required by java's serialization mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The default constructor.
     */
    public QuoteNotClosedException() {

        super();
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     *        the error message
     */
    public QuoteNotClosedException(String aMessage) {

        super(ParameterCheckHelper.checkStringParameter(aMessage));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aCause
     *        the error cause
     */
    public QuoteNotClosedException(Throwable aCause) {

        super(ParameterCheckHelper.checkExceptionCause(aCause));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     *        the error message
     * @param aCause
     *        the error cause
     */
    public QuoteNotClosedException(String aMessage, Throwable aCause) {

        super(ParameterCheckHelper.checkStringParameter(aMessage), ParameterCheckHelper.checkExceptionCause(aCause));
    }

}
