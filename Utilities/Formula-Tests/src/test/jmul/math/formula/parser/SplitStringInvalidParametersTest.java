/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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

package test.jmul.math.formula.parser;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import jmul.checks.exceptions.NullParameterException;

import jmul.math.formula.parser.FormulaParsingException;
import jmul.math.formula.parser.ParserHelper;
import jmul.math.formula.parser.patterns.TokenPattern;
import jmul.math.formula.parser.patterns.TokenPatternHelper;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests trimming strings with invalid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SplitStringInvalidParametersTest {

    /**
     * All patterns to identify tokens.
     */
    private Map<TokenPattern, Pattern> patterns;

    /**
     * A string which is to be split up.
     */
    private final String input;

    /**
     * The expected exception type.
     */
    private final Class expectedExceptionType;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anInput
     * @param theExpectedExcetpionType
     */
    public SplitStringInvalidParametersTest(String anInput, Class theExpectedExcetpionType) {

        super();

        input = anInput;
        expectedExceptionType = theExpectedExcetpionType;
    }

    /**
     * Sets up needed resources and prerequisites for the tests.
     */
    @Before
    public void setUp() {

        patterns = TokenPatternHelper.toMap();
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        patterns = null;
    }

    /**
     * Tests splitting a string into tokens.
     */
    @Test
    public void testSplitting() {

        try {

            ParserHelper.splitString(patterns, input);

            String message = String.format("An exception (%s) is expected but none was thrown!", expectedExceptionType);
            fail(message);

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();
            if (!expectedExceptionType.isAssignableFrom(actualExceptionType)) {

                String message =
                    String.format("An exception (%s) is expected but a different exception (%s) none was thrown!",
                                  expectedExceptionType, actualExceptionType);
                fail(message);
            }
        }
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, NullParameterException.class });
        parameters.add(new Object[] { " ", FormulaParsingException.class });
        parameters.add(new Object[] { " 1 + 1 ", FormulaParsingException.class });
        parameters.add(new Object[] { "d-1)(", FormulaParsingException.class });
        parameters.add(new Object[] { "1+[", FormulaParsingException.class });
        parameters.add(new Object[] { "1+$", FormulaParsingException.class });

        return parameters;
    }

}
