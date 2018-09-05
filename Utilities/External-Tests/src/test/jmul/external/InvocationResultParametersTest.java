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

package test.jmul.external;


import jmul.external.InvocationResult;
import jmul.external.InvocationResultImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This class contains tests to check the invocation of an invocation result.
 *
 * @author Kristian Kutin
 */
public class InvocationResultParametersTest {

    /**
     * Tests the instantiation with invalid parameters.
     */
    @Test(expected = NullPointerException.class)
    public void testNullExitValue() {

        Integer exitCode = null;
        String standardOutput = null;
        String errorOutput = null;

        new InvocationResultImpl(exitCode, standardOutput, errorOutput);
    }

    /**
     * Tests the instantiation with valid parameters (successful invocation without output).
     */
    @Test
    public void testValidParameters() {

        int exitCode = 0;
        String standardOutput = null;
        String errorOutput = null;

        InvocationResult result = new InvocationResultImpl(exitCode, standardOutput, errorOutput);

        assertEquals(exitCode, result.getExitValue());
        assertEquals(standardOutput, result.getStandardOutput());
        assertEquals(errorOutput, result.getErrorOutput());
        assertTrue(result.wasSuccessful());
        assertFalse(result.hasFailed());
    }

    /**
     * Tests the instantiation with valid parameters (successful invocation with output).
     */
    @Test
    public void testValidParameters2() {

        int exitCode = 0;
        String standardOutput = "Test";
        String errorOutput = null;

        InvocationResult result = new InvocationResultImpl(exitCode, standardOutput, errorOutput);

        assertEquals(exitCode, result.getExitValue());
        assertEquals(standardOutput, result.getStandardOutput());
        assertEquals(errorOutput, result.getErrorOutput());
        assertTrue(result.wasSuccessful());
        assertFalse(result.hasFailed());
    }

    /**
     * Tests the instantiation with valid parameters (failed invocation without output).
     */
    @Test
    public void testValidParameters3() {

        int exitCode = -1;
        String standardOutput = null;
        String errorOutput = null;

        InvocationResult result = new InvocationResultImpl(exitCode, standardOutput, errorOutput);

        assertEquals(exitCode, result.getExitValue());
        assertEquals(standardOutput, result.getStandardOutput());
        assertEquals(errorOutput, result.getErrorOutput());
        assertFalse(result.wasSuccessful());
        assertTrue(result.hasFailed());
    }

    /**
     * Tests the instantiation with valid parameters (failed invocation with output).
     */
    @Test
    public void testValidParameters4() {

        int exitCode = -1;
        String standardOutput = "Test";
        String errorOutput = "Doh";

        InvocationResult result = new InvocationResultImpl(exitCode, standardOutput, errorOutput);

        assertEquals(exitCode, result.getExitValue());
        assertEquals(standardOutput, result.getStandardOutput());
        assertEquals(errorOutput, result.getErrorOutput());
        assertFalse(result.wasSuccessful());
        assertTrue(result.hasFailed());
    }

}
