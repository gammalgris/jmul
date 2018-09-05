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


import java.io.File;
import java.io.IOException;

import jmul.external.Command;
import jmul.external.CommandImpl;
import jmul.external.CommandInvoker;
import jmul.external.CommandInvokerImpl;
import jmul.external.InvocationResult;

import static jmul.string.Constants.NEW_LINE;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.FailedTestException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains tests to check the execution of powershell skripts with a batch runner.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class PowershellExecutionTest {

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
     * Invokes a simple powershell script which prints "Hello World!" to the console.
     */
    @Test
    public void testBatchExample01() {

        String baseDirectoryPath = "testdata-external\\powershell\\";
        String runnerPath = baseDirectoryPath + "runner.bat";
        String scriptPath = "example01.ps1";
        String scriptPath2 = baseDirectoryPath + scriptPath;

        Command command = new CommandImpl(runnerPath, scriptPath);

        String fullRunnerPath = null;
        try {

            File f = new File(scriptPath2);
            fullRunnerPath = f.getCanonicalPath();

        } catch (IOException e) {

            throw new FailedTestException("Unable to resolve path!", e);
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append("running ");
        buffer.append(fullRunnerPath);
        buffer.append("...");
        buffer.append(NEW_LINE);
        buffer.append("Hello World!");
        String expectedOutput = buffer.toString();

        InvocationResult result = invoker.invoke(command);
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
