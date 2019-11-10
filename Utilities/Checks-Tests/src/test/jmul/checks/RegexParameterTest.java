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


import static jmul.checks.ParameterCheckHelper.checkRegexParameter;
import jmul.checks.exceptions.InvalidRegularExpressionParameterException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests to check regex parameter check methods.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class RegexParameterTest {

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = InvalidRegularExpressionParameterException.class)
    public void testNullParameter() {

        String input = null;
        checkRegexParameter(input);
    }

    /**
     * Tests the check method with an empty parameter.
     */
    @Test(expected = InvalidRegularExpressionParameterException.class)
    public void testEmptyParameter() {

        String input = "";
        checkRegexParameter(input);
    }

    /**
     * Tests the check method with a string consisting of only spaces.
     */
    @Test(expected = InvalidRegularExpressionParameterException.class)
    public void testEmptyParameter2() {

        String input = "  ";
        checkRegexParameter(input);
    }

    /**
     * Tests the check method with an invalid parameter.
     */
    @Test(expected = InvalidRegularExpressionParameterException.class)
    public void testInvalidParameter() {

        String input = "(a";
        checkRegexParameter(input);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidParameter() {

        String input = "[a-z]{1}";
        String output = checkRegexParameter(input);
        assertEquals(input, output);
    }

}
