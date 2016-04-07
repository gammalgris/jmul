/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package test.jmul.math.markow;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.markow.Algorithm;
import jmul.math.markow.Rule;

import static jmul.string.Constants.NEW_LINE;
import jmul.string.StringConcatenator;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the string representation of an algorithm.
 */
@UnitTest
@RunWith(Parameterized.class)
public class AlgorithmMiscTest {

    /**
     * The rules which belong to the algorithm.
     */
    private Rule[] rules;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param someRules
     */
    public AlgorithmMiscTest(Rule... someRules) {

        rules = someRules;
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

    @Test
    public void testToString() {

        String expectedResult = buildExpectedRepresentation(rules);

        Algorithm algorithm = new Algorithm(rules);

        assertEquals(expectedResult, algorithm.toString());
    }

    /**
     * Builds a string representation according to the specified rules.
     *
     * @param someRules
     *
     * @return a string representation
     */
    private static String buildExpectedRepresentation(Rule... someRules) {

        StringConcatenator expectedRepresentation = new StringConcatenator();
        boolean first = true;

        for (Rule rule : someRules) {

            if (first) {

                first = false;

            } else {

                expectedRepresentation.append(NEW_LINE);
            }

            expectedRepresentation.append(rule);
        }

        return String.valueOf(expectedRepresentation);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { new Rule[] { new Rule("a", "b") } });
        parameters.add(new Object[] { new Rule[] { new Rule("b", "a"), new Rule("a", "b") } });

        return parameters;
    }

}
