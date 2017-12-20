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


import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Collection;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains tests to check properties of a class.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class FieldsTest {

    /**
     * A constant value.
     */
    private static final boolean RECURSE = true;

    /**
     * A constant value.
     */
    private static final boolean DONT_RECURSE = !RECURSE;

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { FieldsA.class, 1, 1 });
        parameters.add(new Object[] { FieldsB.class, 2, 2 });
        parameters.add(new Object[] { FieldsC.class, 0, 0 });
        parameters.add(new Object[] { FieldsD.class, 1, 2 });
        parameters.add(new Object[] { FieldsE.class, 1, 3 });
        parameters.add(new Object[] { FieldsF.class, 1, 1 });

        return parameters;
    }

    /**
     * A class definition.
     */
    private ClassDefinition clazz;

    /**
     * The expected field count (not recursed).
     */
    private int expectedFieldCount;

    /**
     * The expected field count (recursed).
     */
    private int expectedTotalFieldCount;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aClass
     *        the class which is to be tested
     * @param anExpectedFieldCount
     *        an expected field count
     * @param anExpectedTotalFieldCount
     *        an expected total field count
     *
     * @throws ClassNotFoundException
     *         is thrown if there is no corresponding class definition
     */
    public FieldsTest(Class aClass, int anExpectedFieldCount,
                      int anExpectedTotalFieldCount) throws ClassNotFoundException {

        clazz = ClassHelper.getClass(aClass);
        expectedFieldCount = anExpectedFieldCount;
        expectedTotalFieldCount = anExpectedTotalFieldCount;
    }

    /**
     * The method checks if the expected number of fields is found.
     */
    @Test
    public void testFieldProperties() {

        {
            Field[] fields = clazz.getFields(DONT_RECURSE);
            int actualsFields = fields.length;
            String message =
                "The class " + clazz + " is expected to have " + expectedFieldCount + " fields (not recursed) but " +
                actualsFields + " were found!";
            assertEquals(message, expectedFieldCount, actualsFields);
        }

        {
            Field[] fields = clazz.getFields(RECURSE);
            int actualsFields = fields.length;
            String message =
                "The class " + clazz + " is expected to have " + expectedTotalFieldCount + " fields (recursed) but " +
                actualsFields + " were found!";
            assertEquals(message, expectedTotalFieldCount, actualsFields);
        }
    }

}


class FieldsA {

    private int i;
}


class FieldsB {

    private String s;
    private String s2;

}

class FieldsC {

    private static char c;

}

class FieldsD extends FieldsA {

    protected float f;

}

class FieldsE extends FieldsB {

    public double d;

}

class FieldsF extends FieldsC {

    boolean b;

}
