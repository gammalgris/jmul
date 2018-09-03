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


import java.io.File;

import static jmul.checks.ParameterCheckHelper.checkFileNameParameter;
import static jmul.checks.ParameterCheckHelper.checkFileParameter;
import jmul.checks.exceptions.NullFileNameParameterException;
import jmul.checks.exceptions.NullFileParameterException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests to check file parameter and file name parameter check methods.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class FileParameterTest {

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullFileParameterException.class)
    public void testNullFileParameter() {

        File input = null;
        checkFileParameter(input);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidFileParameter() {

        File input = new File(".");
        File output = checkFileParameter(input);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullFileNameParameterException.class)
    public void testNullFileNameParameter() {

        String input = null;
        checkFileNameParameter(input);
    }

    /**
     * Tests the check method with an empty parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyFileNameParameter() {

        String input = "";
        checkFileNameParameter(input);
    }

    /**
     * Tests the check method with a string consisting of only spaces.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyFileNameParameter2() {

        String input = "  ";
        checkFileNameParameter(input);
    }

    /**
     * Tests the check method with an invalid string (i.e. trailing and leading spaces).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFileNameParameter() {

        String input = " . ";
        checkFileNameParameter(input);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidFileNameParameter() {

        String input = "Test";
        String output = checkFileNameParameter(input);
        assertEquals(input, output);
    }

}
