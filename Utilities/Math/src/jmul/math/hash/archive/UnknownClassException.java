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

package jmul.math.hash.archive;


import jmul.checks.ParameterCheckHelper;


/**
 * The exception is thrown if an unknown class was specified as parameter.
 *
 * @author Kristian Kutin
 */
public class UnknownClassException extends IllegalArgumentException {

    /**
     * The serial UID as required by java's serialization mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aClass
     *        the class for which no entry exists
     */
    public UnknownClassException(Class aClass) {

        super(createMessage(ParameterCheckHelper.checkClassParameter(aClass)));
    }

    /**
     * Creates an error message according to the specified parameters.
     *
     * @param aClass
     *        a class
     *
     * @return an error message
     */
    private static String createMessage(Class aClass) {

        StringBuilder buffer = new StringBuilder();

        buffer.append("No entry exists for the specified class (");
        buffer.append(aClass.getCanonicalName());
        buffer.append("!");

        return String.valueOf(buffer);
    }

}
