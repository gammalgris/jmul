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

package jmul.reflection;


import jmul.checks.ParameterCheckHelper;


/**
 * A utility class which contains a field name and a field's value. This information is used
 * to initialize a field by reflection mechanisms.
 *
 * @author Kristian Kutin
 */
public class FieldValuePair {

    /**
     * A field name.
     */
    private String fieldName;

    /**
     * A field's value.
     */
    private Object fieldValue;

    /**
     * Creates a new pair.
     *
     * @param aFieldName
     *        the name of a class field
     * @param aFieldValue
     *        the value of the field
     */
    public FieldValuePair(String aFieldName, Object aFieldValue) {

        ParameterCheckHelper.checkStringParameter(aFieldName);

        fieldName = aFieldName;
        fieldValue = aFieldValue;
    }

    /**
     * Returns a field name.
     *
     * @return a field name
     */
    public String getFieldName() {

        return fieldName;
    }

    /**
     * Returns a field's value.
     *
     * @return a field's value
     */
    public Object getFieldValue() {

        return fieldValue;
    }

}
