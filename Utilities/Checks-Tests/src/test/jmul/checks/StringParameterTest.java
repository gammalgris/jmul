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


import static jmul.checks.ParameterCheckHelper.checkBufferParameter;
import static jmul.checks.ParameterCheckHelper.checkExceptionMessage;
import static jmul.checks.ParameterCheckHelper.checkLineSeparatorParameter;
import static jmul.checks.ParameterCheckHelper.checkStringBuilderParameter;
import static jmul.checks.ParameterCheckHelper.checkStringParameter;
import jmul.checks.exceptions.EmptyStringParameterException;
import jmul.checks.exceptions.NullParameterException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests to check string parameter check methods.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class StringParameterTest {

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testNullExceptionMessageParameter() {

        String input = null;
        checkExceptionMessage(input);
    }

    /**
     * Tests the check method with an empty parameter.
     */
    @Test(expected = EmptyStringParameterException.class)
    public void testEmptyExceptionMessageParameter() {

        String input = "";
        checkExceptionMessage(input);
    }

    /**
     * Tests the check method with a string consisting of only spaces.
     */
    @Test(expected = EmptyStringParameterException.class)
    public void testEmptyExceptionMessageParameter2() {

        String input = "  ";
        checkExceptionMessage(input);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testNullStringBuilderParameter() {

        StringBuilder input = null;
        checkStringBuilderParameter(input);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testNullLineSeparatorParameter() {

        String input = null;
        checkLineSeparatorParameter(input);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidExceptionMessageParameter() {

        String input = "Test";
        String output = checkExceptionMessage(input);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidStringBuilderParameter() {

        StringBuilder input = new StringBuilder("Test");
        StringBuilder output = checkStringBuilderParameter(input);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testNullStringParameter() {

        String input = null;
        checkStringParameter(input);
    }

    /**
     * Tests the check method with an empty parameter.
     */
    @Test(expected = EmptyStringParameterException.class)
    public void testEmptyStringParameter() {

        String input = "";
        checkStringParameter(input);
    }

    /**
     * Tests the check method with a string consisting of only spaces.
     */
    @Test(expected = EmptyStringParameterException.class)
    public void testEmptyStringParameter2() {

        String input = "  ";
        checkStringParameter(input);
    }

    /**
     * Tests the check method with a string consisting of only spaces.
     */
    @Test(expected = EmptyStringParameterException.class)
    public void testEmptyLineSeparatorParameter() {

        String input = "";
        checkLineSeparatorParameter(input);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidStringParameter() {

        String input = "Test";
        String output = checkStringParameter(input);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testNullBufferParameter() {

        String input = null;
        checkBufferParameter(input);
    }

    /**
     * Tests the check method with an empty parameter.
     */
    @Test
    public void testEmptyBufferParameter() {

        String input = "";
        String output = checkBufferParameter(input);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a string consisting of only spaces.
     */
    @Test
    public void testEmptyBufferParameter2() {

        String input = "  ";
        String output = checkBufferParameter(input);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidBufferParameter() {

        String input = "Test";
        String output = checkBufferParameter(input);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidLineSeparatorParameter() {

        String input = ";";
        String output = checkLineSeparatorParameter(input);
        assertEquals(input, output);
    }

}
