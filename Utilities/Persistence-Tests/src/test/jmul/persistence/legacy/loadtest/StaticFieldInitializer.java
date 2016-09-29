/*
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

package test.jmul.persistence.legacy.loadtest;


/**
 * A utility class that contains a field's name and the value which is to be
 * assigned to that field.
 *
 * @author Kristian Kutin
 */
public class StaticFieldInitializer extends FieldInitializerBase {

    /**
     * The value which is to be assigned to the specified field.
     */
    private Object value;

    /**
     * Constructs a field initialization object.
     *
     * @param aFieldName
     * a field name
     * @param aValue
     * he value which is to be assigned to the specified field
     */
    public StaticFieldInitializer(String aFieldName, Object aValue) {

        super(aFieldName);

        value = aValue;
    }

    /**
     * Returns the field's value.
     *
     * @return a field value
     */
    public Object getFieldValue() {

        return value;
    }

}
