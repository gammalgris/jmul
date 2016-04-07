/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package test.jmul.helper;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.string.Constants;

import jmul.test.classification.UnitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the constructor of helper classes.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class HelperConstructorTest {

    /**
     * A helper class.
     */
    private Class helperClass;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aHelperClass
     */
    public HelperConstructorTest(Class aHelperClass) {

        helperClass = aHelperClass;
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Checks if instantiating the utility class fails.
     */
    @Test(expected = IllegalAccessException.class)
    public void testConstructorAccess() throws InstantiationException, IllegalAccessException {

        helperClass.newInstance();
    }

    /**
     * Checks if instantiating the utility class fails.
     */
    @Test(expected = InvocationTargetException.class)
    public void testInstantiation2() throws NoSuchMethodException, InstantiationException, IllegalAccessException,
                                            InvocationTargetException {

        Constructor<Constants> c = Constants.class.getDeclaredConstructor();
        c.setAccessible(true);
        c.newInstance();
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { jmul.math.Constants.class });
        parameters.add(new Object[] { jmul.math.MathHelper.class });
        parameters.add(new Object[] { jmul.string.Constants.class });
        parameters.add(new Object[] { jmul.misc.checks.ParameterCheckHelper.class });

        return parameters;
    }

}
