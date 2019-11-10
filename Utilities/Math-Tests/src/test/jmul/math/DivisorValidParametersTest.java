/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package test.jmul.math;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import jmul.math.MathHelper;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the {@link jmul.math.MathHelper#max} method.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DivisorValidParametersTest {

    /**
     * The input.
     */
    private int input;

    /**
     * The expected results.
     */
    private int[] expectedResults;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anInput
     * @param allExpectedResults
     */
    public DivisorValidParametersTest(int anInput, int... allExpectedResults) {

        input = anInput;
        expectedResults = allExpectedResults;
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
     * Invokes the max method and checks if the result matches the expected result.
     */
    @Test
    public void testMax() {

        Set<Integer> expectedResultSet = toSet(expectedResults);
        Set<Integer> actualResultSet = MathHelper.calculateAllDivisors(input);

        assertEquals(expectedResultSet, actualResultSet);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 1, new int[] { 1 } });
        parameters.add(new Object[] { 2, new int[] { 1, 2 } });
        parameters.add(new Object[] { 3, new int[] { 1, 3 } });
        parameters.add(new Object[] { 4, new int[] { 1, 2, 4 } });
        parameters.add(new Object[] { 5, new int[] { 1, 5 } });
        parameters.add(new Object[] { 6, new int[] { 1, 2, 3, 6 } });
        parameters.add(new Object[] { 7, new int[] { 1, 7 } });
        parameters.add(new Object[] { 8, new int[] { 1, 2, 4, 8 } });
        parameters.add(new Object[] { 9, new int[] { 1, 3, 9 } });
        parameters.add(new Object[] { 10, new int[] { 1, 2, 5, 10 } });
        parameters.add(new Object[] { 100, new int[] { 1, 2, 4, 5, 10, 20, 25, 50, 100 } });

        return parameters;
    }

    /**
     * Transforms the specified array of integers into a set of integers.
     *
     * @param someNumbers
     *
     * @return a set
     */
    private static Set<Integer> toSet(int... someNumbers) {

        Set<Integer> result = new TreeSet<Integer>();

        for (int number : someNumbers) {

            result.add(number);
        }

        return result;
    }

}
