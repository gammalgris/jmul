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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.reflection;


import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.reflection.ReflectionHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
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
public class InvokeGetterPositiveTest {

    /**
     * The target object.
     */
    private Object target;

    /**
     * The name of a field.
     */
    private String fieldName;

    /**
     * The expected result.
     */
    private Object expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param aTarget
     * @param aFieldName
     * @param anExpectedResult
     */
    public InvokeGetterPositiveTest(Object aTarget, String aFieldName, Object anExpectedResult) {

        super();

        target = aTarget;
        fieldName = aFieldName;
        expectedResult = anExpectedResult;
    }

    /**
     * Tests invoking a getter method via reflection mechanisms.
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Test
    public void testInvokeGetter() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Object actualResult = ReflectionHelper.invokeGetter(target, fieldName);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { new ClassWithAccessors(1, 1, 1, 1, "1"), "number", 1 });
        parameters.add(new Object[] { new ClassWithAccessors(1, 1, 1, 1, "Hello World!"), "text", "Hello World!" });

        return parameters;
    }

}
