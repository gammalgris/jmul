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


import jmul.external.Command;
import jmul.external.CommandImpl;
import jmul.external.CommandInvoker;
import jmul.external.CommandInvokerImpl;
import jmul.external.InvocationResult;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains tests to check the execution of batch skripts.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class BatchExecutionTest {

    /**
     * The command invoker.
     */
    private CommandInvoker invoker;

    /**
     * Preparations before a test.
     */
    @Before
    public void setUp() {

        invoker = new CommandInvokerImpl();
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDown() {

        invoker = null;
    }

    /**
     * Invokes a simple batch script which prints "Hello World!" to the console.
     */
    @Test
    public void testBatchExample01() {

        Command command = new CommandImpl("testdata-external\\batch\\example01.bat");

        InvocationResult result = invoker.invoke(command);

        String expectedOutput = "Hello World!";
        String actualOutput = result.getStandardOutput();

        printString(expectedOutput);
        printString(actualOutput);

        assertEquals(0, result.getExitValue());
        assertTrue(result.wasSuccessful());
        assertEquals(expectedOutput.length(), actualOutput.length());
        assertEquals(expectedOutput, actualOutput);
        assertEquals("", result.getErrorOutput());
    }

    /**
     * Invokes a simple batch script which exits with an error and the error message is
     * sent to the error output.
     */
    @Test
    public void testBatchExample02() {

        Command command = new CommandImpl("testdata-external\\batch\\example02.bat");

        InvocationResult result = invoker.invoke(command);

        String expectedErrorOutput = "An error occurred!";
        String actualErrorOutput = result.getErrorOutput();

        printString(expectedErrorOutput);
        printString(actualErrorOutput);

        assertEquals(1, result.getExitValue());
        assertTrue(result.hasFailed());
        assertEquals("", result.getStandardOutput());
        assertEquals(expectedErrorOutput.length(), actualErrorOutput.length());
        assertEquals(expectedErrorOutput, actualErrorOutput);
    }

    /**
     * This script prints the specified string in two ways. First the string itself and
     * then as a sequence of char codes.
     *
     * @param s
     *        a string
     */
    private static void printString(String s) {

        System.out.println(s);
        for (int a = 0; a < s.length(); a++) {

            System.out.print((byte) s.charAt(a));
            System.out.print(" ");
        }
        System.out.println();
    }

}
