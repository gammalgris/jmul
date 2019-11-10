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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.math.formula.parser.tokens;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jmul.math.formula.parser.tokens.TokenParserHelper;
import jmul.math.formula.parser.tokens.TokenPatternSets;
import jmul.math.formula.parser.tokens.TokenSequence;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class SplitLineValidParametersTest {

    /**
     * The string which is to be parsed.
     */
    private String string;

    /**
     * The expected number of identified token sequences.
     */
    private int expectedTokenSequenceCount;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aString
     * @param theExpectedTokenSequenceCount
     */
    public SplitLineValidParametersTest(String aString, int theExpectedTokenSequenceCount) {

        super();

        string = aString;
        expectedTokenSequenceCount = theExpectedTokenSequenceCount;
    }

    /**
     * Tests splitting a string and checks the results.
     */
    @Test
    public void testSplitLine() {

        List<TokenSequence> results =
            TokenParserHelper.splitString(string, TokenPatternSets.getDefaultMathTokenPatterns());

        {
            String message = "Splitting the string didn't return any results!";
            assertNotNull(message, results);
        }

        {
            String message = "Splitting the string (" + string + ") didn't return the epxected results!";
            assertEquals(message, expectedTokenSequenceCount, results.size());
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

        parameters.add(new Object[] { "1+1", 4 });
        parameters.add(new Object[] { "((1 + 2) - 3) + 1", 1024 });
        parameters.add(new Object[] { "3d6", 4 });
        parameters.add(new Object[] { "3d6+1", 16 });
        parameters.add(new Object[] { "4+a", 4 });
        parameters.add(new Object[] { "a*b", 4 });
        parameters.add(new Object[] { "4+5", 4 });
        parameters.add(new Object[] { "   4     +    5       ", 4 });
        parameters.add(new Object[] { "4++5", 8 });
        parameters.add(new Object[] { "4+ -5", 8 });
        parameters.add(new Object[] { "4+ (-5)", 32 });
        parameters.add(new Object[] { "-4 +5", 8 });
        parameters.add(new Object[] { "(-4) +5", 32 });
        parameters.add(new Object[] { "10-7", 8 });
        parameters.add(new Object[] { "5*5", 4 });
        parameters.add(new Object[] { "5*5*5", 16 });
        parameters.add(new Object[] { "25/5", 8 });
        parameters.add(new Object[] { "21/5", 8 });
        parameters.add(new Object[] { "1 +2 - ( 1 + 2 )", 256 });
        parameters.add(new Object[] { "100+205-15+234", 8192 });
        parameters.add(new Object[] { "((100+205)-15)+234", 131072 });
        parameters.add(new Object[] { "100+205+234-15", 8192 });
        parameters.add(new Object[] { "(1+2)+(3+4)", 1024 });
        parameters.add(new Object[] { "(1+2)*(3+4)", 1024 });
        parameters.add(new Object[] { "(1-2)*(3-4)", 1024 });
        parameters.add(new Object[] { "(1+2)/(3-4)", 1024 });
        parameters.add(new Object[] { "1", 1 });
        parameters.add(new Object[] { "variable", 128 });
        parameters.add(new Object[] { "(1+1", 8 });
        parameters.add(new Object[] { "1+1)", 8 });
        parameters.add(new Object[] { "(1+2(", 16 });
        parameters.add(new Object[] { ")1+2)", 16 });
        parameters.add(new Object[] { "((1 + 2() - 3) + 1", 2048 });
        parameters.add(new Object[] { ")))((((", 64 });
        parameters.add(new Object[] { "-(1", 4 });
        parameters.add(new Object[] { "d-1)(", 16 });
        parameters.add(new Object[] { "1+[", 4 });
        parameters.add(new Object[] { "1+$", 4 });

        return parameters;
    }

}
