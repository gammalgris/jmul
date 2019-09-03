/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package test.jmul.reflection;


import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.checks.exceptions.EmptyStringParameterException;
import jmul.checks.exceptions.NullParameterException;

import jmul.reflection.ReflectionHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests which invoke getter methods via reflection mechanisms.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class InvokeGetterNegativeTest {

    /**
     * The target object.
     */
    private Object target;

    /**
     * The name of a field.
     */
    private String fieldName;

    /**
     * The expected exception type.
     */
    private Class expectedExceptionType;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param aTarget
     *        the target object
     * @param aFieldName
     *        a field name
     * @param anExpectedExceptionType
     *        the expected exception type
     */
    public InvokeGetterNegativeTest(Object aTarget, String aFieldName, Class anExpectedExceptionType) {

        super();

        target = aTarget;
        fieldName = aFieldName;
        expectedExceptionType = anExpectedExceptionType;
    }

    /**
     * Tests invoking a getter method via reflection mechanisms.
     *
     * @throws NoSuchMethodException
     *         is thrown if no suitable getter method exists.
     * @throws IllegalAccessException
     *         is thrown if the getter method cannot be accessed.
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the getter method.
     */
    @Test
    public void testInvokeGetter() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        try {

            ReflectionHelper.invokeGetter(target, fieldName);
            fail("An exception is expected but none was thrown!");

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();
            assertEquals(expectedExceptionType, actualExceptionType);
        }
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, "number", NullParameterException.class });
        parameters.add(new Object[] { new ClassWithAccessors(1, 1, 1, 1, "1"), null, NullParameterException.class });
        parameters.add(new Object[] { new ClassWithAccessors(1, 1, 1, 1, "1"), "",
                                      EmptyStringParameterException.class });
        parameters.add(new Object[] { new ClassWithAccessors(1, 1, 1, 1, "1"), " ",
                                      EmptyStringParameterException.class });
        parameters.add(new Object[] { new ClassWithAccessors(1, 1, 1, 1, "1"), "number2",
                                      InvocationTargetException.class });
        parameters.add(new Object[] { new ClassWithAccessors(1, 1, 1, 1, "1"), "number3",
                                      NoSuchMethodException.class });
        parameters.add(new Object[] { new ClassWithAccessors(1, 1, 1, 1, "1"), "number4",
                                      NoSuchMethodException.class });
        parameters.add(new Object[] { new ClassWithAccessors(1, 1, 1, 1, "1"), "number5",
                                      NoSuchMethodException.class });

        return parameters;
    }

}
