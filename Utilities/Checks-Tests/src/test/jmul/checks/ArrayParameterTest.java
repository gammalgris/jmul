/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package test.jmul.checks;


import static jmul.checks.ParameterCheckHelper.checkObjectArrayParameter;
import static jmul.checks.ParameterCheckHelper.checkStringArrayParameter;
import jmul.checks.exceptions.NullParameterException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;


/**
 * This class contains tests to check array parameter check methods.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ArrayParameterTest {

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testNullObjectArray() {

        Object[] input = null;
        checkObjectArrayParameter(input);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testNullStringArray() {

        String[] input = null;
        checkStringArrayParameter(input);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testNullStringArray2() {

        String[] input = null;
        int minimumSize = 0;
        checkStringArrayParameter(input, minimumSize);
    }

    /**
     * Tests the check method with a <code>null</code> minimum size parameter.
     */
    @Test(expected = NullPointerException.class)
    public void testNullMinimumSize() {

        String[] input = new String[] { };
        Integer minimumSize = null;
        checkStringArrayParameter(input, minimumSize);
    }

    /**
     * Tests the check method with a negative minimum size parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMinimumSize() {

        String[] input = new String[] { };
        int minimumSize = -1;
        checkStringArrayParameter(input, minimumSize);
    }

    /**
     * Tests the check method with a too small string array parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testStringArrayTooSmall() {

        String[] input = new String[] { };
        int minimumSize = 1;
        checkStringArrayParameter(input, minimumSize);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidObjectArray() {

        Object[] input = new Object[] { };
        Object[] output = checkObjectArrayParameter(input);
        assertArrayEquals(input, output);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidStringArray() {

        String[] input = new String[] { };
        String[] output = checkStringArrayParameter(input);
        assertArrayEquals(input, output);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidStringArray2() {

        String[] input = new String[] { };
        int minimumSize = 0;
        String[] output = checkStringArrayParameter(input, minimumSize);
        assertArrayEquals(input, output);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidStringArray3() {

        String[] input = new String[] { "a" };
        int minimumSize = 1;
        String[] output = checkStringArrayParameter(input, minimumSize);
        assertArrayEquals(input, output);
    }

}
