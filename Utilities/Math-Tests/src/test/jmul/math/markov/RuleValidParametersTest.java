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

import jmul.math.markov.Rule;

import jmul.string.StringConcatenator;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class RuleValidParametersTest {

    /**
     * The left side of a rule.
     */
    private String leftSide;

    /**
     * The right side of a rule.
     */
    private String rightSide;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param aLeftSide
     * @param aRightSide
     */
    public RuleValidParametersTest(String aLeftSide, String aRightSide) {

        leftSide = aLeftSide;
        rightSide = aRightSide;
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
     * Tests the instantiation of a rule.
     */
    @Test
    public void testInstantiation() {

        Rule rule = new Rule(leftSide, rightSide);
        assertNotNull(rule);
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {

        StringConcatenator expectedRepresentation = new StringConcatenator("\"", leftSide, "\" -> \"", rightSide, "\"");
        Rule rule = new Rule(leftSide, rightSide);
        assertEquals(expectedRepresentation.toString(), rule.toString());
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "a", "b" });
        parameters.add(new Object[] { "a", "" });

        return parameters;
    }

}
