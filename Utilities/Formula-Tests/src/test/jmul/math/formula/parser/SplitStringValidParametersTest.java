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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jmul.math.formula.parser.ParserHelper;
import jmul.math.formula.parser.patterns.TokenPattern;
import jmul.math.formula.parser.patterns.TokenPatternHelper;
import jmul.math.formula.parser.tokens.Token;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests trimming strings with valid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SplitStringValidParametersTest {

    /**
     * All patterns to identify tokens.
     */
    private Map<TokenPattern, Pattern> patterns;

    /**
     * A string which is to be split up.
     */
    private final String input;

    /**
     * The expected tokens.
     */
    private final String[] expectedTokens;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param anInput
     * @param theExpectedTokens
     */
    public SplitStringValidParametersTest(String anInput, String... theExpectedTokens) {

        super();

        input = anInput;
        expectedTokens = theExpectedTokens;
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

        List<Token> tokenSequence = ParserHelper.splitString(patterns, input);
        assertNotNull(tokenSequence);

        int expectedTokenCount = expectedTokens.length;
        {
            String message =
                String.format("Sizes of token sequences don't match (input=\"%s\";expected=%s;actual=%s)!", input,
                              Arrays.asList(expectedTokens), tokenSequence);
            assertEquals(message, expectedTokenCount, tokenSequence.size());
        }

        for (int a = 0; a < expectedTokenCount; a++) {

            Token actualToken = tokenSequence.get(a);
            String expectedToken = expectedTokens[a];

            String message =
                String.format("Tokens don't match (string=\"%s\";expected=\"%s\";actual=\"%s\")!", input, expectedToken,
                              actualToken);
            assertEquals(message, expectedToken.toString(), actualToken.toString());
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

        parameters.add(new Object[] { "", new String[] { } });
        parameters.add(new Object[] { "1", new String[] { "1" } });
        parameters.add(new Object[] { "-1", new String[] { "-", "1" } });
        parameters.add(new Object[] { "1+1", new String[] { "1", "+", "1" } });

        parameters.add(new Object[] { "((1+2)-3)+1",
                                      new String[] { "(", "(", "1", "+", "2", ")", "-", "3", ")", "+", "1" } });
        parameters.add(new Object[] { "3d6", new String[] { "3", "d", "6" } });
        parameters.add(new Object[] { "3d6+1", new String[] { "3", "d", "6", "+", "1" } });
        parameters.add(new Object[] { "4+a", new String[] { "4", "+", "a" } });
        parameters.add(new Object[] { "a*b", new String[] { "a", "*", "b" } });
        parameters.add(new Object[] { "4+5", new String[] { "4", "+", "5" } });
        parameters.add(new Object[] { "4++5", new String[] { "4", "+", "+", "5" } });
        parameters.add(new Object[] { "4+-5", new String[] { "4", "+", "-", "5" } });
        parameters.add(new Object[] { "4+(-5)", new String[] { "4", "+", "(", "-", "5", ")" } });
        parameters.add(new Object[] { "-4+5", new String[] { "-", "4", "+", "5" } });
        parameters.add(new Object[] { "(-4)+5", new String[] { "(", "-", "4", ")", "+", "5" } });
        parameters.add(new Object[] { "10-7", new String[] { "10", "-", "7" } });
        parameters.add(new Object[] { "5*5", new String[] { "5", "*", "5" } });
        parameters.add(new Object[] { "5*5*5", new String[] { "5", "*", "5", "*", "5" } });
        parameters.add(new Object[] { "25/5", new String[] { "25", "/", "5" } });
        parameters.add(new Object[] { "21/5", new String[] { "21", "/", "5" } });
        parameters.add(new Object[] { "1+2-(1+2)", new String[] { "1", "+", "2", "-", "(", "1", "+", "2", ")" } });
        parameters.add(new Object[] { "(", new String[] { "(" } });
        parameters.add(new Object[] { "100+205-15+234", new String[] { "100", "+", "205", "-", "15", "+", "234" } });
        parameters.add(new Object[] { "((100+205)-15)+234",
                                      new String[] { "(", "(", "100", "+", "205", ")", "-", "15", ")", "+", "234" } });
        parameters.add(new Object[] { "100+205+234-15", new String[] { "100", "+", "205", "+", "234", "-", "15" } });
        parameters.add(new Object[] { "(1+2)+(3+4)",
                                      new String[] { "(", "1", "+", "2", ")", "+", "(", "3", "+", "4", ")" } });
        parameters.add(new Object[] { "(1+2)*(3+4)",
                                      new String[] { "(", "1", "+", "2", ")", "*", "(", "3", "+", "4", ")" } });
        parameters.add(new Object[] { "(1-2)*(3-4)",
                                      new String[] { "(", "1", "-", "2", ")", "*", "(", "3", "-", "4", ")" } });
        parameters.add(new Object[] { "(1+2)/(3-4)",
                                      new String[] { "(", "1", "+", "2", ")", "/", "(", "3", "-", "4", ")" } });
        parameters.add(new Object[] { "variable", new String[] { "variable" } });

        parameters.add(new Object[] { "(1+1", new String[] { "(", "1", "+", "1" } });
        parameters.add(new Object[] { "1+1)", new String[] { "1", "+", "1", ")" } });
        parameters.add(new Object[] { "(1+2(", new String[] { "(", "1", "+", "2", "(" } });
        parameters.add(new Object[] { ")1+2)", new String[] { ")", "1", "+", "2", ")" } });
        parameters.add(new Object[] { ")))((((", new String[] { ")", ")", ")", "(", "(", "(", "(" } });
        parameters.add(new Object[] { "-(1", new String[] { "-", "(", "1" } });

        return parameters;
    }

}
