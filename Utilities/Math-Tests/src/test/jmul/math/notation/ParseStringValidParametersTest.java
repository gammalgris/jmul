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

package test.jmul.math.notation;


import java.util.ArrayList;
import java.util.Collection;

import static jmul.math.Constants.getEpsilon;
import jmul.math.notation.NotationHelper;
import jmul.math.notation.PrecisionTypes;
import static jmul.math.notation.PrecisionTypes.SIGNED_FLOATING_POINT_32_BIT;
import static jmul.math.notation.PrecisionTypes.SIGNED_FLOATING_POINT_64_BIT;
import static jmul.math.notation.PrecisionTypes.SIGNED_INTEGER_32_BIT;
import static jmul.math.notation.PrecisionTypes.SIGNED_INTEGER_64_BIT;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests check if numerical values are parsed correctly from string.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ParseStringValidParametersTest {

    /**
     * The specified numerical precision.
     */
    private PrecisionTypes precision;

    /**
     * The specified string.
     */
    private String string;

    /**
     * The expected result.
     */
    private Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param aPrecision
     * @param aString
     * @param theExpectedResult
     */
    public ParseStringValidParametersTest(PrecisionTypes aPrecision, String aString, Number theExpectedResult) {

        super();

        precision = aPrecision;
        string = aString;
        expectedResult = theExpectedResult;
    }

    /**
     * Tests translating a string to a numerical value.
     */
    @Test
    public void testParseString() {

        Number result = NotationHelper.parseString(precision, string);
        checkResult(precision, result, expectedResult);
    }

    /**
     * Checks the result versus the expected result.
     *
     * @param aPrecision
     * @param theActualResult
     * @param theExpectedResult
     */
    private void checkResult(PrecisionTypes aPrecision, Number theActualResult, Number theExpectedResult) {

        switch (aPrecision) {
        case SIGNED_INTEGER_32_BIT:
            {
                int expected = theExpectedResult.intValue();
                int actual = theActualResult.intValue();

                assertEquals(expected, actual);

                break;
            }
        case SIGNED_INTEGER_64_BIT:
            {
                long expected = theExpectedResult.longValue();
                long actual = theActualResult.longValue();

                assertEquals(expected, actual);

                break;
            }
        case SIGNED_FLOATING_POINT_32_BIT:
            {
                float expected = theExpectedResult.floatValue();
                float actual = theActualResult.floatValue();
                float epsilon = getEpsilon(Float.TYPE).floatValue();

                assertEquals(expected, actual, epsilon);

                break;
            }
        case SIGNED_FLOATING_POINT_64_BIT:
            {
                double expected = theExpectedResult.doubleValue();
                double actual = theActualResult.doubleValue();
                double epsilon = getEpsilon(Float.TYPE).doubleValue();

                assertEquals(expected, actual, epsilon);

                break;
            }
        default:
            {
                String message = "An unknown precision type was specified (" + aPrecision + ")!";
                throw new UnsupportedOperationException(message);
            }
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

        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "0", new Integer(0) });
        parameters.add(new Object[] { SIGNED_INTEGER_64_BIT, "0", new Integer(0) });
        parameters.add(new Object[] { SIGNED_FLOATING_POINT_32_BIT, "0", new Integer(0) });
        parameters.add(new Object[] { SIGNED_FLOATING_POINT_64_BIT, "0", new Integer(0) });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "0.0", new Integer(0) });
        parameters.add(new Object[] { SIGNED_INTEGER_64_BIT, "0.0", new Integer(0) });
        parameters.add(new Object[] { SIGNED_FLOATING_POINT_32_BIT, "0.0", new Integer(0) });
        parameters.add(new Object[] { SIGNED_FLOATING_POINT_64_BIT, "0.0", new Integer(0) });

        return parameters;
    }

}
