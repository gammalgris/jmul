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

package jmul.math.formula.parser;


import jmul.checks.ParameterCheckHelper;


/**
 * A special exception type for cases when the parsing of a formula runs
 * into a parsing error.
 *
 * @author Kristian Kutin
 */
public class FormulaParsingException extends RuntimeException {

    /**
     * The default constructor with no parameters.
     */
    public FormulaParsingException() {

        super();
    }

    /**
     * A constructor which requires a message which describes the cause
     * of the exception.
     *
     * @param aMessage
     *        a message which contains the cause of the exception
     */
    public FormulaParsingException(String aMessage) {

        super(ParameterCheckHelper.checkStringParameter(aMessage));
    }

    /**
     * A constructor which wraps an existing exception, which represents
     * the cause of the exception
     *
     * @param aCause
     *        an exception which represents the cause of the exception
     */
    public FormulaParsingException(Throwable aCause) {

        super(ParameterCheckHelper.checkExceptionCause(aCause));
    }

    /**
     * A constructor which requires a message and an excisting exception.
     * Both describe the cause of the exception.
     *
     * @param aMessage
     *        a message which contains the cause of the exception
     * @param aCause
     *        an exception which represents the cause of the exception
     */
    public FormulaParsingException(String aMessage, Throwable aCause) {

        super(ParameterCheckHelper.checkStringParameter(aMessage), ParameterCheckHelper.checkExceptionCause(aCause));
    }

}
