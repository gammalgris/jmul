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
import jmul.reflection.classes.MissingAccessorException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests which invoke setter methods via reflection mechanisms.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class InvokeSetterInvalidParametersTest {

    /**
     * The target object.
     */
    private Object target;

    /**
     * The name of a field.
     */
    private String fieldName;

    /**
     * The new value of a field.
     */
    private Object fieldValue;

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
     * @param aFieldValue
     *        the new field value
     * @param anExpectedExceptionType
     *        the expected exception type
     */
    public InvokeSetterInvalidParametersTest(Object aTarget, String aFieldName, Object aFieldValue,
                                             Class anExpectedExceptionType) {

        super();

        target = aTarget;
        fieldName = aFieldName;
        fieldValue = aFieldValue;
        expectedExceptionType = anExpectedExceptionType;
    }

    /**
     * Tests invoking a setter method via reflection mechanisms. Invalid parameters are used.
     *
     * @throws NoSuchMethodException
     *         is thrown if no suitable setter method exists
     * @throws IllegalAccessException
     *         is thrown if the setter method cannot be accessed.
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the setter method.
     */
    @Test
    public void testInvokeSetter() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        try {

            ReflectionHelper.invokeSetter(target, fieldName, fieldValue);
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

        parameters.add(new Object[] { null, "number", 2, NullParameterException.class });
        parameters.add(new Object[] { new ClassWithAccessors(), null, 2, NullParameterException.class });
        parameters.add(new Object[] { new ClassWithAccessors(), "", 2, EmptyStringParameterException.class });
        parameters.add(new Object[] { new ClassWithAccessors(), " ", 2, EmptyStringParameterException.class });
        parameters.add(new Object[] { new ClassWithAccessors(), "number", null, NullPointerException.class });
        parameters.add(new Object[] { new ClassWithAccessors(), "number2", 2, InvocationTargetException.class });
        parameters.add(new Object[] { new ClassWithAccessors(), "number3", 2, MissingAccessorException.class });
        parameters.add(new Object[] { new ClassWithAccessors(), "number4", 2, MissingAccessorException.class });
        parameters.add(new Object[] { new ClassWithAccessors(), "number5", 2, MissingAccessorException.class });
        parameters.add(new Object[] { new ClassWithAccessors(), "number6", 2, MissingAccessorException.class });
        parameters.add(new Object[] { new ClassWithAccessors(), "number", "Hello World!",
                                      IllegalArgumentException.class });

        return parameters;
    }

}
