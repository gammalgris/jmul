/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package test.jmul.math.markov;


import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

import jmul.math.markov.Rule;

import jmul.test.classification.UnitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests a {@link jmul.math.markov.Rule}.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RuleValidApplicationTest {

    /**
     * The expected result of a rule application.
     */
    private String expectedResult;

    /**
     * The string on which the rule is applied.
     */
    private String string;

    /**
     * A rule.
     */
    private Rule rule;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param anExpectedResult
     * @param aString
     * @param aRule
     */
    public RuleValidApplicationTest(String anExpectedResult, String aString, Rule aRule) {

        expectedResult = anExpectedResult;
        string = aString;
        rule = aRule;
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Tests the application of a rule.
     */
    @Test
    public void testApplication() {

        String actualResult = rule.applyRule(string);
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

        parameters.add(new Object[] { "bb", "ab", new Rule("a", "b") });
        parameters.add(new Object[] { "bba", "aba", new Rule("a", "b") });

        return parameters;
    }

}
