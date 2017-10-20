/*
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


import java.lang.reflect.Method;

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
public class MethodsTest {

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

        parameters.add(new Object[] { MethodsA.class, 2, 13 });
        parameters.add(new Object[] { MethodsB.class, 1, 1 });
        parameters.add(new Object[] { MethodsC.class, 1, 12 });
        parameters.add(new Object[] { MethodsD.class, 1, 12 });
        parameters.add(new Object[] { MethodsE.class, 1, 12 });
        parameters.add(new Object[] { MethodsF.class, 1, 14 });
        parameters.add(new Object[] { MethodsG.class, 1, 12 });
        parameters.add(new Object[] { MethodsH.class, 1, 13 });
        parameters.add(new Object[] { MethodsI.class, 1, 13 });
        parameters.add(new Object[] { MethodsA.class, 2, 13 });

        return parameters;
    }

    /**
     * A class definition.
     */
    private ClassDefinition clazz;

    /**
     * The expected field count (not recursed).
     */
    private int expectedMethodCount;

    /**
     * The expected field count (recursed).
     */
    private int expectedTotalMethodCount;

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
    public MethodsTest(Class aClass, int anExpectedMethodCount,
                       int anExpectedTotalMethodCount) throws ClassNotFoundException {

        clazz = ClassHelper.getClass(aClass);
        expectedMethodCount = anExpectedMethodCount;
        expectedTotalMethodCount = anExpectedTotalMethodCount;
    }

    /**
     * The method checks if the expected number of methods is found.
     */
    @Test
    public void testMethodProperties() {

        {
            Method[] methods = clazz.getMethods(DONT_RECURSE);
            int actualsMethods = methods.length;
            String message =
                "The class " + clazz + " is expected to have " + expectedMethodCount + " methods (not recursed) but " +
                actualsMethods + " were found!";
            assertEquals(message, expectedMethodCount, actualsMethods);
        }

        {
            Method[] methods = clazz.getMethods(RECURSE);
            int actualsMethods = methods.length;
            String message =
                "The class " + clazz + " is expected to have " + expectedTotalMethodCount + " fields (recursed) but " +
                actualsMethods + " were found!";
            assertEquals(message, expectedTotalMethodCount, actualsMethods);
        }
    }

}


class MethodsA {

    public void a() {
    }

    public int b() {
        return 0;
    }

}

interface MethodsB {

    void c();

}

abstract class MethodsC {

    private void d() {
    }

}

class MethodsD {

    protected void e() {
    }

}

class MethodsE {

    void f() {
    }

}

class MethodsF extends MethodsA {

    public void g() {
    }

}

class MethodsG implements MethodsB {

    @Override
    public void c() {
    }

}

class MethodsH extends MethodsC {

    int h() {
        return 0;
    }

}

class MethodsI extends MethodsD {

    protected void i() {
    }

}

class MethodsJ extends MethodsG {

    void j() {
    }

}
