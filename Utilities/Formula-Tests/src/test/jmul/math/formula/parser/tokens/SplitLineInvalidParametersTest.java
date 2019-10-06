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

package test.jmul.math.formula.parser.tokens;


import java.util.ArrayList;
import java.util.Collection;

import jmul.checks.exceptions.NullParameterException;

import jmul.math.formula.parser.tokens.TokenParserHelper;
import jmul.math.formula.parser.tokens.TokenPatternSets;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests to check the splitting up of a string.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SplitLineInvalidParametersTest {

    /**
     * The string which is to be parsed.
     */
    private String string;

    /**
     * The expected exception type.
     */
    private Class expectedExceptionType;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aString
     * @param theExpectedExceptionType
     */
    public SplitLineInvalidParametersTest(String aString, Class theExpectedExceptionType) {

        super();

        string = aString;
        expectedExceptionType = theExpectedExceptionType;
    }

    /**
     * Tests splitting a string and checks the results.
     */
    @Test
    public void testSplitLine() {

        try {

            TokenParserHelper.splitString(string, TokenPatternSets.getDefaultMathTokenPatterns());

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();

            String message =
                "The expected exception type (" + expectedExceptionType +
                ") doesn't match the actual exception type (" + actualExceptionType + ")!";
            assertTrue(message, expectedExceptionType.isAssignableFrom(actualExceptionType));
            return;
        }

        {
            String message = "An exception is expected (" + expectedExceptionType + ") but none was thrown!";
            fail(message);
        }
    }

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, NullParameterException.class });

        return parameters;
    }

}
