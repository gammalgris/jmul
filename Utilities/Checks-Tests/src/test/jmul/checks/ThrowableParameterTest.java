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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.checks;


import static jmul.checks.ParameterCheckHelper.checkExceptionCause;
import static jmul.checks.ParameterCheckHelper.checkExceptionCauses;
import jmul.checks.exceptions.EmptyArrayParameterException;
import jmul.checks.exceptions.NullArrayParameterException;
import jmul.checks.exceptions.NullParameterException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;


/**
 * This class contains tests to check exception parameter check methods.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ThrowableParameterTest {


    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testNullExceptionParameter() {

        Throwable input = null;
        checkExceptionCause(input);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidExceptionParameter() {

        Throwable input = new RuntimeException();
        Throwable output = checkExceptionCause(input);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullArrayParameterException.class)
    public void testNullExceptionArrayParameter() {

        Throwable[] input = null;
        checkExceptionCauses(input);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullArrayParameterException.class)
    public void testNullExceptionArrayParameter2() {

        Throwable[] input1 = null;
        int input2 = 1;
        checkExceptionCauses(input1, input2);
    }

    /**
     * Tests the check method with an empty array parameter.
     */
    @Test(expected = EmptyArrayParameterException.class)
    public void testEmptyExceptionArrayParameter() {

        Throwable[] input = new Throwable[] { };
        checkExceptionCauses(input);
    }

    /**
     * Tests the check method with an empty array parameter.
     */
    @Test(expected = EmptyArrayParameterException.class)
    public void testEmptyExceptionArrayParameter2() {

        Throwable[] input1 = new Throwable[] { };
        int input2 = 1;
        checkExceptionCauses(input1, input2);
    }

    /**
     * Tests the check method with a too small array parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionArrayParameterSize() {

        Throwable[] input1 = new Throwable[] { new RuntimeException() };
        int input2 = 2;
        checkExceptionCauses(input1, input2);
    }

    /**
     * Tests the check method with a negative size parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionArrayParameterSize2() {

        Throwable[] input1 = new Throwable[] { new RuntimeException() };
        int input2 = -1;
        checkExceptionCauses(input1, input2);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidExceptionArrayParameter() {

        Throwable[] input = new Throwable[] { new RuntimeException() };
        Throwable[] output = checkExceptionCauses(input);
        assertArrayEquals(input, output);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidExceptionArrayParameter2() {

        Throwable[] input = new Throwable[] { new RuntimeException(), new RuntimeException() };
        Throwable[] output = checkExceptionCauses(input);
        assertArrayEquals(input, output);
    }

    /**
     * Tests the check method with a valid parameters.
     */
    @Test
    public void testValidExceptionArrayParameter3() {

        Throwable[] input1 = new Throwable[] { new RuntimeException() };
        int input2 = 0;
        Throwable[] output = checkExceptionCauses(input1, input2);
        assertArrayEquals(input1, output);
    }

}
