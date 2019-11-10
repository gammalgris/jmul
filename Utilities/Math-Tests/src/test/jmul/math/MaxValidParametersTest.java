/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.math;


import java.util.ArrayList;
import java.util.Collection;

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
public class MaxValidParametersTest {

    /**
     * The expected result.
     */
    private int expectedResult;

    /**
     * Some numbers.
     */
    private int[] numbers;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anExpectedResult
     * @param someNumbers
     */
    public MaxValidParametersTest(int anExpectedResult, int... someNumbers) {

        expectedResult = anExpectedResult;
        numbers = someNumbers;
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

        int actualResult = MathHelper.max(numbers);
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

        parameters.add(new Object[] { 1, new int[] { 1 } });
        parameters.add(new Object[] { -1, new int[] { -1, -2 } });
        parameters.add(new Object[] { 10, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } });

        return parameters;
    }

}
