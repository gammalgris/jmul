/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.creation.creators;


import jmul.classes.ClassDefinition;
import jmul.classes.ClassHelper;
import jmul.string.StringConcatenator;


/**
 * An implementation of a creation rule.
 *
 * @author Kristian Kutin
 */
public class ClassCreator implements ObjectCreator {

    /**
     * The default constructor.
     */
    public ClassCreator() {
    }

    /**
     * Instantiates and initializes a {@link Class#}.
     *
     * @param anInitialValue
     *        a string which contains an initial value
     * @param aPattern
     *        a pattern which tells how to parse the string which contains the
     *        inital value
     *
     * @return a new object
     */
    public Object createObject(String anInitialValue, String aPattern) {

        // Check the specified parameters.

        if (anInitialValue == null) {

            String message = "No initial value has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (aPattern != null) {

            String message =
                "A pattern was specified but no pattern is required!";
            throw new IllegalArgumentException(message);
        }


        // Create a new instance.

        Class clazz = null;

        try {

            ClassDefinition definition = ClassHelper.getClass(anInitialValue);
            clazz = definition.getType();

        } catch (ClassNotFoundException e) {

            StringConcatenator message =
                new StringConcatenator("Unknown class (", anInitialValue,
                                       ")!");
            throw new IllegalArgumentException(message.toString());
        }

        return clazz;
    }

}
