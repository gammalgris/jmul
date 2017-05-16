/*
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

package test.jmul.math.prime;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.MathHelper;
import jmul.math.prime.PrimeNumberHelper;

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
public class NextPrimeNumberValidParmetersTest {

    /**
     * The input.
     */
    private int input;

    /**
     * The expected result.
     */
    private int expectedResult;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anInput
     * @param anExpectedResult
     */
    public NextPrimeNumberValidParmetersTest(int anInput, int anExpectedResult) {

        input = anInput;
        expectedResult = anExpectedResult;
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
    public void testNextPrimeNumber() {

        int actualResult = PrimeNumberHelper.getNextPrimeNumber(input);
        assertEquals("The result (" + actualResult + ") has more than 2 divisors!", 2,
                     MathHelper.calculateAllDivisors(actualResult).size());
        assertEquals("The result doesn't match the expected value!", expectedResult, actualResult);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 1, 2 });
        parameters.add(new Object[] { 2, 3 });
        parameters.add(new Object[] { 3, 5 });
        parameters.add(new Object[] { 4, 7 });
        parameters.add(new Object[] { 5, 11 });
        parameters.add(new Object[] { 6, 13 });
        parameters.add(new Object[] { 7, 17 });
        parameters.add(new Object[] { 8, 19 });
        parameters.add(new Object[] { 9, 23 });
        parameters.add(new Object[] { 10, 29 });

        return parameters;
    }

}
