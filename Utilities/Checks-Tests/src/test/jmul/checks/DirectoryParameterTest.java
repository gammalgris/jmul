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


import java.io.File;

import static jmul.checks.ParameterCheckHelper.checkDirectoryNameParameter;
import static jmul.checks.ParameterCheckHelper.checkDirectoryParameter;
import jmul.checks.exceptions.NullDirectoryParameterException;
import jmul.checks.exceptions.NullFileNameParameterException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests to check directory parameter and directory name parameter check methods.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class DirectoryParameterTest {

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullDirectoryParameterException.class)
    public void testNullDirectoryParameter() {

        File input = null;
        checkDirectoryParameter(input);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidDirectoryParameter() {

        File input = new File(".\\test");
        File output = checkDirectoryParameter(input);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullFileNameParameterException.class)
    public void testNullDirectoryNameParameter() {

        String input = null;
        checkDirectoryNameParameter(input);
    }

    /**
     * Tests the check method with an empty parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyDirectoryNameParameter() {

        String input = "";
        checkDirectoryNameParameter(input);
    }

    /**
     * Tests the check method with a string consisting of only spaces.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyDirectoryNameParameter2() {

        String input = "  ";
        checkDirectoryNameParameter(input);
    }

    /**
     * Tests the check method with an invalid string (i.e. trailing and leading spaces).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDirectoryNameParameter() {

        String input = " .\\test ";
        checkDirectoryNameParameter(input);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidDirectoryNameParameter() {

        String input = "Test";
        String output = checkDirectoryNameParameter(input);
        assertEquals(input, output);
    }

}
