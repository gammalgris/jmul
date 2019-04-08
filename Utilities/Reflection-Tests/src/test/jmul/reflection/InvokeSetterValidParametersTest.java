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

import jmul.reflection.ReflectionHelper;

import jmul.test.classification.UnitTest;

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
public class InvokeSetterValidParametersTest {

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
     * Creates a test case according to the specified parameters.
     *
     * @param aTarget
     * @param aFieldName
     * @param aFieldValue
     */
    public InvokeSetterValidParametersTest(Object aTarget, String aFieldName, Object aFieldValue) {

        super();

        target = aTarget;
        fieldName = aFieldName;
        fieldValue = aFieldValue;
    }

    /**
     * Tests invoking a setter method via reflection mechanisms. Valid parameters are used.
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Test
    public void testInvokeSetter() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        ReflectionHelper.invokeSetter(target, fieldName, fieldValue);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { new ClassWithAccessors(), "number", 2 });
        parameters.add(new Object[] { new ClassWithAccessors(), "text", "Hello World!" });

        return parameters;
    }

}
