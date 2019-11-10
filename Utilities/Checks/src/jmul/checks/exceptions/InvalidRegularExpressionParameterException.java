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

package jmul.checks.exceptions;


import static jmul.checks.ParameterCheckHelper.checkExceptionCause;
import static jmul.checks.ParameterCheckHelper.checkExceptionMessage;


/**
 * An exception which is derived from {@link java.lang.IllegalArgumentException}.
 *
 * @author Kristian Kutin
 */
public class InvalidRegularExpressionParameterException extends IllegalArgumentException {

    /**
     * The serial UID as required by java's serialization mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The default message for this kind of exception.
     */
    private static final String DEFAULT_MESSAGE = "No regular expression (null) has been specified!";

    /**
     * A placeholder.
     */
    private static final String REGEX_PLACEHOLDER = "<regex>";

    /**
     * An exception message.
     */
    private static final String EXCEPTION_MESSAGE = "Invalid regular expression: \"" + REGEX_PLACEHOLDER + "\"";

    /**
     * The default constructor.
     */
    public InvalidRegularExpressionParameterException() {

        super(DEFAULT_MESSAGE);
    }

    /**
     * Creates a new exception according to the specified parameter.
     *
     * @param aRegex
     *        a regular exception
     */
    public InvalidRegularExpressionParameterException(String aRegex) {

        super(checkExceptionMessage(newExceptionMessage(aRegex)));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aRegex
     *        a regular exception
     * @param aCause
     *        the cause of this exception
     */
    public InvalidRegularExpressionParameterException(String aRegex, Throwable aCause) {

        super(checkExceptionMessage(newExceptionMessage(aRegex)), checkExceptionCause(aCause));
    }

    /**
     * Creates a new exception message according to the specified parameter.
     *
     * @param aRegex
     *
     * @return an exception message
     */
    private static String newExceptionMessage(String aRegex) {

        return EXCEPTION_MESSAGE.replace(REGEX_PLACEHOLDER, aRegex);
    }

}
