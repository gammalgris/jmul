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

import jmul.math.formula.parser.patterns.TokenPattern;
import jmul.math.formula.parser.tokens.TokenFactory;
import jmul.math.formula.parser.tokens.TokenParser;
import jmul.math.formula.parser.tokens.TokenPatternSets;
import jmul.math.formula.parser.tokens.TokenSequence;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests to check a token parser.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class TokenParserInvalidInputTest {

    /**
     * A token parser.
     */
    private TokenParser parser;

    /**
     * The string which is to be parsed.
     */
    private String string;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param aParser
     * @param aString
     */
    public TokenParserInvalidInputTest(TokenParser aParser, String aString) {

        super();

        parser = aParser;
        string = aString;
    }

    /**
     * Steps before a test.
     */
    @Before
    public void setUp() {

    }

    /**
     * Steps after a test.
     */
    @After
    public void tearDown() {

    }

    /**
     * Tests parsing a string and checks the parsing result.
     */
    @Test
    public void testParsing() {

        TokenSequence sequence = parser.parseExpression(string);

        {
            String message = "The specified string (\"" + string + "\") could be parsed!";
            assertNull(message, sequence);
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

        parameters.add(new Object[] { newTokenParser(), "(1+1" });
        parameters.add(new Object[] { newTokenParser(), "1+1)" });
        parameters.add(new Object[] { newTokenParser(), "(1+2(" });
        parameters.add(new Object[] { newTokenParser(), ")1+2)" });
        parameters.add(new Object[] { newTokenParser(), "((1 + 2() - 3) + 1" });
        parameters.add(new Object[] { newTokenParser(), ")))((((" });
        parameters.add(new Object[] { newTokenParser(), "-(1" });
        parameters.add(new Object[] { newTokenParser(), "d-1)(" });
        parameters.add(new Object[] { newTokenParser(), "1+[" });
        parameters.add(new Object[] { newTokenParser(), "1+$" });

        return parameters;
    }

    /**
     * Returns a new token parser.
     *
     * @return a token parser
     */
    private static TokenParser newTokenParser() {

        TokenParser parser = TokenFactory.newTokenParser();

        for (TokenPattern pattern : TokenPatternSets.getDefaultMathTokenPatterns()) {

            parser.addTokenPattern(pattern);
        }

        return parser;
    }

}
