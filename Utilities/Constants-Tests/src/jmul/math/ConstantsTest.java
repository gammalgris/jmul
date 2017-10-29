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

package jmul.math;


import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This class contains tests to check the mathematical constants.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ConstantsTest {

    /**
     * Tests zero with byte types.
     */
    @Test
    public void testByteZero() {

        byte primitiveZero = 0;
        Byte objectZero = new Byte(primitiveZero);

        Number actualObjectZero = Constants.getZero(Byte.TYPE);
        byte actualPrimitiveZero = actualObjectZero.byteValue();

        assertEquals(primitiveZero, actualPrimitiveZero);
        assertEquals(primitiveZero, actualObjectZero);

        assertEquals(objectZero, actualObjectZero);
    }

    /**
     * Tests zero with short types.
     */
    @Test
    public void testShortZero() {

        short primitiveZero = 0;
        Short objectZero = new Short(primitiveZero);

        Number actualObjectZero = Constants.getZero(Short.TYPE);
        short actualPrimitiveZero = actualObjectZero.shortValue();

        assertEquals(primitiveZero, actualPrimitiveZero);
        assertEquals(primitiveZero, actualObjectZero);

        assertEquals(objectZero, actualObjectZero);
    }

    /**
     * Tests zero with integer types.
     */
    @Test
    public void testIntegerZero() {

        int primitiveZero = 0;
        Integer objectZero = new Integer(primitiveZero);

        Number actualObjectZero = Constants.getZero(Integer.TYPE);
        int actualPrimitiveZero = actualObjectZero.intValue();

        assertEquals(primitiveZero, actualPrimitiveZero);
        assertEquals(primitiveZero, actualObjectZero);

        assertEquals(objectZero, actualObjectZero);
    }

    /**
     * Tests zero with long types.
     */
    @Test
    public void testLongZero() {

        long primitiveZero = 0;
        Long objectZero = new Long(primitiveZero);

        Number actualObjectZero = Constants.getZero(Long.TYPE);
        long actualPrimitiveZero = actualObjectZero.longValue();

        assertEquals(primitiveZero, actualPrimitiveZero);
        assertEquals(primitiveZero, actualObjectZero);

        assertEquals(objectZero, actualObjectZero);
    }

    /**
     * Tests zero with float types.
     */
    @Test
    public void testFloatZero() {

        float primitiveZero = 0F;
        Float objectZero = new Float(primitiveZero);
        float delta = Constants.getEpsilon(Float.TYPE).floatValue();

        Number actualObjectZero = Constants.getZero(Float.TYPE);
        float actualPrimitiveZero = actualObjectZero.floatValue();

        assertEquals(primitiveZero, actualPrimitiveZero, delta);
        assertEquals(primitiveZero, actualObjectZero);

        assertEquals(objectZero, actualObjectZero);
    }

    /**
     * Tests zero with double types.
     */
    @Test
    public void testDoubleZero() {

        double primitiveZero = 0F;
        Double objectZero = new Double(primitiveZero);
        double delta = Constants.getEpsilon(Double.TYPE).doubleValue();

        Number actualObjectZero = Constants.getZero(Double.TYPE);
        double actualPrimitiveZero = actualObjectZero.doubleValue();

        assertEquals(primitiveZero, actualPrimitiveZero, delta);
        assertEquals(primitiveZero, actualObjectZero);

        assertEquals(objectZero, actualObjectZero);
    }

    /**
     * Tests requesting zero with a <code>null</code> parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testZeroInvalidCall() {

        Constants.getZero(null);
    }

    /**
     * Tests requesting zero with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testZeroInvalidCall2() {

        Constants.getZero(String.class);
    }

    /**
     * Tests epsilon for float types.
     */
    @Test
    public void testFloatEpsilon() {

        float limit = 1F / 1000F;
        float zero = Constants.getZero(Float.TYPE).floatValue();
        float epsilon = Constants.getEpsilon(Float.TYPE).floatValue();

        {
            String message = "Epsilon is zero or negative (epsilon=" + epsilon + ")!";
            assertTrue(message, epsilon > zero);
        }

        {
            String message = "Epsilon is not equal or smaller than the expected limit (epsilon=" + epsilon + ")!";
            assertTrue(message, epsilon <= limit);
        }
    }

    /**
     * Tests epsilon for double types.
     */
    @Test
    public void testDoubleEpsilon() {

        double limit = 1F / 1000F;
        double zero = Constants.getZero(Double.TYPE).doubleValue();
        double epsilon = Constants.getEpsilon(Double.TYPE).doubleValue();

        {
            String message = "Epsilon is zero or negative (epsilon=" + epsilon + ")!";
            assertTrue(message, epsilon > zero);
        }

        {
            String message = "Epsilon is not equal or smaller than the expected limit (epsilon=" + epsilon + ")!";
            assertTrue(message, epsilon <= limit);
        }
    }

    /**
     * Tests requesting epsilon with a <code>null</code> parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEpsilonInvalidCall() {

        Constants.getEpsilon(null);
    }

    /**
     * Tests requesting epsilon with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEpsilonInvalidCall2() {

        Constants.getEpsilon(String.class);
    }

}
