/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package test.jmul.math.notation;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.notation.NotationHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests to check the correct expansion of number strings with
 * the scientific notation.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ExpandScientificNotationValidParametersTest {

    /**
     * A number string.
     */
    private String string;

    /**
     * The expected result.
     */
    private String expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     * 
     * @param aString
     * @param theExpectedResult
     */
    public ExpandScientificNotationValidParametersTest(String aString, String theExpectedResult) {

        super();

        string = aString;
        expectedResult = theExpectedResult;
    }

    /**
     * Tests the expansion of a number string with a scientific notation.
     */
    @Test
    public void testExpansion() {

        String actualResult = NotationHelper.expandScientificNotation(string);

        assertEquals(expectedResult, actualResult);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "1.0E2", "100.0" });
        parameters.add(new Object[] { "1,0E2", "100,0" });
        parameters.add(new Object[] { "+1.0E2", "+100.0" });
        parameters.add(new Object[] { "+1,0E2", "+100,0" });
        parameters.add(new Object[] { "-1.0E2", "-100.0" });
        parameters.add(new Object[] { "-1,0E2", "-100,0" });

        parameters.add(new Object[] { "1.000E2", "100.0" });
        parameters.add(new Object[] { "1,000E2", "100,0" });

        parameters.add(new Object[] { "1.0001E2", "100.01" });
        parameters.add(new Object[] { "1,0001E2", "100,01" });

        parameters.add(new Object[] { "1.0E-2", "0.01" });
        parameters.add(new Object[] { "1,0E-2", "0,01" });
        parameters.add(new Object[] { "+1.0E-2", "+0.01" });
        parameters.add(new Object[] { "+1,0E-2", "+0,01" });
        parameters.add(new Object[] { "-1.0E-2", "-0.01" });
        parameters.add(new Object[] { "-1,0E-2", "-0,01" });

        parameters.add(new Object[] { "1,23457E+11", "123457000000,0" });

        return parameters;
    }

}
