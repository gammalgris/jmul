/*
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


import jmul.misc.checks.ParameterCheckHelper;


/**
 * This exception is thrown on trying to replace an existing entry in
 * a prime number archive.
 *
 * @author Kristian Kutin
 */
public class ExistingEntryException extends IllegalArgumentException {

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aClass
     */
    public ExistingEntryException(Class aClass) {

        super(createMessage(ParameterCheckHelper.checkClass(aClass)));
    }

    /**
     * Creates an error message according to the specified parameters.
     *
     * @param aClass
     *
     * @return an error message
     */
    private static String createMessage(Class aClass) {

        StringBuilder buffer = new StringBuilder();

        buffer.append("An entry already exists for the specified class (");
        buffer.append(aClass.getCanonicalName());
        buffer.append("!");

        return String.valueOf(buffer);
    }

}
