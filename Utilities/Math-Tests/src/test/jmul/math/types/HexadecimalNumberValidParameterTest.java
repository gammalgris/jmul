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

package test.jmul.math.types;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.types.HexadecimalNumber;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests if the integer value is correctly calculated for certain
 * inputs.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class HexadecimalNumberValidParameterTest {

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "00", 0 });
        parameters.add(new Object[] { "01", 1 });
        parameters.add(new Object[] { "02", 2 });
        parameters.add(new Object[] { "0A", 10 });
        parameters.add(new Object[] { "0F", 15 });
        parameters.add(new Object[] { "10", 16 });
        parameters.add(new Object[] { "1F", 31 });
        parameters.add(new Object[] { "20", 32 });
        parameters.add(new Object[] { "FE", 254 });
        parameters.add(new Object[] { "FF", 255 });
        parameters.add(new Object[] { "1010", 4112 });
        parameters.add(new Object[] { "101010", 1052688 });
        parameters.add(new Object[] { "10101010", 269488144 });

        return parameters;
    }

    /**
     * An input string.
     */
    private String input;

    /**
     * The expected integer result.
     */
    private int expectedResult;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anInput
     * @param anExpectedResult
     */
    public HexadecimalNumberValidParameterTest(String anInput, int anExpectedResult) {

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
     * Checks if the created hexadecimal number corresponds to the specified
     * expected result.
     */
    @Test
    public void testInteger() {

        HexadecimalNumber h = new HexadecimalNumber(input);
        int actualResult = h.intValue();

        {
            String message =
                "The hexadecimal number \"" + input + "\" doesn't correspond to the expected result (expected=" +
                expectedResult + ";actual=" + actualResult + ")!";
            assertEquals(message, expectedResult, actualResult);
        }
    }

}
