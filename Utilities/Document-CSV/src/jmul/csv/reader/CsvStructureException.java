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

package jmul.csv.reader;


import jmul.checks.ParameterCheckHelper;


/**
 * Is thrown if the structure of a CSV file doesn't match its header.
 *
 * @author Kristian Kutin
 */
public class CsvStructureException extends RuntimeException {

    /**
     * The serial UID as required by java's serialization mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The default constructor.
     */
    public CsvStructureException() {

        super();
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     *        the error message
     */
    public CsvStructureException(String aMessage) {

        super(ParameterCheckHelper.checkStringParameter(aMessage));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aCause
     *        the exception cause
     */
    public CsvStructureException(Throwable aCause) {

        super(ParameterCheckHelper.checkExceptionCause(aCause));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     *        the error message
     * @param aCause
     *        the exception cause
     */
    public CsvStructureException(String aMessage, Throwable aCause) {

        super(ParameterCheckHelper.checkStringParameter(aMessage), ParameterCheckHelper.checkExceptionCause(aCause));
    }

}
