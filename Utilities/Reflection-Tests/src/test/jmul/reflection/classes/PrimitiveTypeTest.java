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

package test.jmul.reflection.classes;


import java.util.ArrayList;
import java.util.Collection;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains tests to check primitive types and their wrappers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class PrimitiveTypeTest {

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { Byte.TYPE, Byte.class });
        parameters.add(new Object[] { Short.TYPE, Short.class });
        parameters.add(new Object[] { Integer.TYPE, Integer.class });
        parameters.add(new Object[] { Long.TYPE, Long.class });
        parameters.add(new Object[] { Float.TYPE, Float.class });
        parameters.add(new Object[] { Double.TYPE, Double.class });
        parameters.add(new Object[] { Boolean.TYPE, Boolean.class });
        parameters.add(new Object[] { Character.TYPE, Character.class });

        return parameters;
    }

    /**
     * A class definition.
     */
    private ClassDefinition primitiveType;

    /**
     * A class definition.
     */
    private ClassDefinition wrapperType;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aPrimitiveType
     *        a primitive type
     * @param aWrapperType
     *        the corresponding wrapper type
     *
     * @throws ClassNotFoundException
     *         is thrown if there is no corresponding class definition
     */
    public PrimitiveTypeTest(Class aPrimitiveType, Class aWrapperType) throws ClassNotFoundException {

        primitiveType = ClassHelper.getClass(aPrimitiveType);
        wrapperType = ClassHelper.getClass(aWrapperType);
    }

    /**
     * Tests properties of the class.
     */
    @Test
    public void testTypeProperties() {

        {
            String message = "The specified class " + primitiveType + " is no primitive type!";
            assertTrue(message, primitiveType.isPrimitiveType());
        }

        {
            String message = "The specified class " + wrapperType + " is no primitive wrapper type!";
            assertTrue(message, wrapperType.isPrimitiveWrapper());
        }

        {
            String message =
                " The specified primitive type " + primitiveType + " and the corresponding wrapper type " +
                wrapperType + " don't match!";
            assertEquals(message, primitiveType, wrapperType.getCorrespondingPrimitiveType());
        }
    }

}
