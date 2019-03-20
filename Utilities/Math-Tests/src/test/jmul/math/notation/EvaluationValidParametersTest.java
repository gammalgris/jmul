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
import jmul.math.notation.Comparators;
import static jmul.math.notation.Comparators.EQUALS;
import static jmul.math.notation.Comparators.GREATER_THAN;
import static jmul.math.notation.Comparators.LESSER_THAN;
import jmul.math.notation.NotationHelper;
import static jmul.math.random.StandardDice.D2;
import static jmul.math.random.StandardDice.D4;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests to compare numbers which are provided as strings.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class EvaluationValidParametersTest {

    /**
     * An epsilon value.
     */
    private static final float EPSILON_FLOAT = getEpsilon(Float.TYPE).floatValue();

    /**
     * An epsilon value.
     */
    private static final double EPSILON_DOUBLE = getEpsilon(Float.TYPE).doubleValue();

    /**
     * A number string.
     */
    private String firstNumber;

    /**
     * A comparator.
     */
    private Comparators comparator;

    /**
     * A number string.
     */
    private String secondNumber;

    /**
     * The expected result of the comparison.
     */
    private boolean expectedResult;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param aFirstNumber
     * @param aComparator
     * @param aSecondNumber
     * @param theExpectedResult
     */
    public EvaluationValidParametersTest(String aFirstNumber, Comparators aComparator, String aSecondNumber,
                                         boolean theExpectedResult) {

        super();

        firstNumber = aFirstNumber;
        comparator = aComparator;
        secondNumber = aSecondNumber;
        expectedResult = theExpectedResult;
    }

    /**
     * Tests the evaluation of a number comparison.
     */
    @Test
    public void testEvaluation() {

        boolean actualResult = NotationHelper.evaluate(firstNumber, comparator, secondNumber);

        String message = "Expression: " + firstNumber + " " + comparator + " " + secondNumber;

        assertEquals(message, expectedResult, actualResult);
    }

    /**
     * Returns a random sign.
     *
     * @return a sign
     */
    private static double randomSign() {

        int result = D2.roll();

        if (result == 1) {

            return -1D;

        } else {

            return 1D;
        }
    }

    /**
     * Returns a random number.
     *
     * @return a number (integer)
     */
    private static int randomInteger() {

        int sign = (int) randomSign();
        double result = Math.random() * Integer.MAX_VALUE * sign;

        return (int) result;
    }

    /**
     * Returns a random number.
     *
     * @return a number (long)
     */
    private static long randomLong() {

        int sign = (int) randomSign();
        double result = Math.random() * Long.MAX_VALUE * sign;

        return (long) result;
    }

    /**
     * Returns a random number.
     *
     * @return a number (float)
     */
    private static float randomFloat() {

        int sign = (int) randomSign();
        double result = Math.random() * Float.MAX_VALUE * sign;

        return (float) result;
    }

    /**
     * Returns a random number.
     *
     * @return a number (double)
     */
    private static double randomDouble() {

        int sign = (int) randomSign();
        double result = Math.random() * Long.MAX_VALUE * sign;

        return result;
    }

    /**
     * Determine the expected result for the specified expression.
     *
     * @param n
     *        a number
     * @param comparator
     *        a comparator
     * @param m
     *        a number
     *
     * @return the expected result
     */
    private static boolean determineResult(int n, Comparators comparator, int m) {

        switch (comparator) {
        case LESSER_THAN:
            {
                return n < m;
            }
        case EQUALS:
            {
                return n == m;
            }
        case GREATER_THAN:
            {
                return n > m;
            }
        default:
            {
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Determine the expected result for the specified expression.
     *
     * @param n
     *        a number
     * @param comparator
     *        a comparator
     * @param m
     *        a number
     *
     * @return the expected result
     */
    private static boolean determineResult(long n, Comparators comparator, long m) {

        switch (comparator) {
        case LESSER_THAN:
            {
                return n < m;
            }
        case EQUALS:
            {
                return n == m;
            }
        case GREATER_THAN:
            {
                return n > m;
            }
        default:
            {
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Determine the expected result for the specified expression.
     *
     * @param n
     *        a number
     * @param comparator
     *        a comparator
     * @param m
     *        a number
     *
     * @return the expected result
     */
    private static boolean determineResult(float n, Comparators comparator, float m) {

        switch (comparator) {
        case LESSER_THAN:
            {
                return n < m;
            }
        case EQUALS:
            {
                return (n >= m - EPSILON_FLOAT) && (n <= m + EPSILON_FLOAT);
            }
        case GREATER_THAN:
            {
                return n > m;
            }
        default:
            {
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Determine the expected result for the specified expression.
     *
     * @param n
     *        a number
     * @param comparator
     *        a comparator
     * @param m
     *        a number
     *
     * @return the expected result
     */
    private static boolean determineResult(double n, Comparators comparator, double m) {

        switch (comparator) {
        case LESSER_THAN:
            {
                return n < m;
            }
        case EQUALS:
            {
                return (n >= m - EPSILON_DOUBLE) && (n <= m + EPSILON_DOUBLE);
            }
        case GREATER_THAN:
            {
                return n > m;
            }
        default:
            {
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Adds a randomly determined expression to the test cases.
     *
     * @param testParameters
     */
    private static void randomExpression(Collection<Object[]> testParameters) {

        int result = D4.roll();

        switch (result) {
        case 1:
            {
                int n = randomInteger();
                int m = randomInteger();

                for (Comparators comparator : Comparators.values()) {

                    testParameters.add(new Object[] { String.valueOf(n), comparator, String.valueOf(m),
                                                      determineResult(n, comparator, m) });
                }

                break;
            }
        case 2:
            {
                long n = randomLong();
                long m = randomLong();

                for (Comparators comparator : Comparators.values()) {

                    testParameters.add(new Object[] { String.valueOf(n), comparator, String.valueOf(m),
                                                      determineResult(n, comparator, m) });
                }

                break;
            }
        case 3:
            {
                float n = randomFloat();
                float m = randomFloat();

                for (Comparators comparator : Comparators.values()) {

                    testParameters.add(new Object[] { String.valueOf(n), comparator, String.valueOf(m),
                                                      determineResult(n, comparator, m) });
                }

                break;
            }
        case 4:
            {
                double n = randomDouble();
                double m = randomDouble();

                for (Comparators comparator : Comparators.values()) {

                    testParameters.add(new Object[] { String.valueOf(n), comparator, String.valueOf(m),
                                                      determineResult(n, comparator, m) });
                }

                break;
            }
        default:
            {
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Returns the next lowest number (with a certain amount of fuzziness) which differs from the
     * specified number.
     *
     * @param f
     *        a number
     *
     * @return the next lowest number
     */
    private static float nextLowestNumber(float f) {

        float g = f;
        float step = 1F;
        float step2 = 1F;
        float factor = 4F;

        while (f == g) {

            step2 = step;
            System.out.println("DEBUG:: step=" + step);
            g = f - factor * step;
            step = step * 2F;
        }

        g = f - factor * step2;

        return g;
    }

    /**
     * Returns the next lowest number (with a certain amount of fuzziness) which differs from the
     * specified number.
     *
     * @param f
     *        a number
     *
     * @return the next lowest number
     */
    private static double nextLowestNumber(double f) {

        double g = f;
        double step = 1D;
        double step2 = 1D;
        double factor = 4D;

        while (f == g) {

            step2 = step;
            System.out.println("DEBUG:: step=" + step);
            g = f - factor * step;
            step = step * 2D;
        }

        g = f - factor * step2;

        return g;
    }

    /**
     * Returns the next highest number (with a certain amount of fuzziness) which differs from the
     * specified number.
     *
     * @param f
     *        a number
     *
     * @return the next lowest number
     */
    private static float nextHighestNumber(float f) {

        float g = f;
        float step = 1F;
        float step2 = 1F;
        float factor = 4F;

        while (f == g) {

            step2 = step;
            System.out.println("DEBUG:: step=" + step);
            g = f + factor * step;
            step = step * 2F;
        }

        g = f + factor * step2;

        return g;
    }

    /**
     * Returns the next highest number (with a certain amount of fuzziness) which differs from the
     * specified number.
     *
     * @param f
     *        a number
     *
     * @return the next lowest number
     */
    private static double nextHighestNumber(double f) {

        double g = f;
        double step = 1D;
        double step2 = 1D;
        double factor = 4D;

        while (f == g) {

            step2 = step;
            System.out.println("DEBUG:: step=" + step);
            g = f + factor * step;
            step = step * 2D;
        }

        g = f + factor * step2;

        return g;
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "1", EQUALS, "1", true }); // [0]
        parameters.add(new Object[] { "1", EQUALS, "0", false });
        parameters.add(new Object[] { "0", EQUALS, "1", false });
        parameters.add(new Object[] { "1", GREATER_THAN, "1", false });
        parameters.add(new Object[] { "1", GREATER_THAN, "0", true });
        parameters.add(new Object[] { "0", GREATER_THAN, "1", false });
        parameters.add(new Object[] { "1", LESSER_THAN, "1", false });
        parameters.add(new Object[] { "1", LESSER_THAN, "0", false });
        parameters.add(new Object[] { "0", LESSER_THAN, "1", true });
        parameters.add(new Object[] { "9", EQUALS, "10", false });
        parameters.add(new Object[] { "9", GREATER_THAN, "10", false }); // [10]
        parameters.add(new Object[] { "9", LESSER_THAN, "10", true });
        parameters.add(new Object[] { String.valueOf(Integer.MAX_VALUE), EQUALS, String.valueOf(Integer.MIN_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Integer.MAX_VALUE), GREATER_THAN,
                                      String.valueOf(Integer.MIN_VALUE), true });
        parameters.add(new Object[] { String.valueOf(Integer.MAX_VALUE), LESSER_THAN, String.valueOf(Integer.MIN_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Integer.MAX_VALUE), EQUALS, String.valueOf(Integer.MAX_VALUE - 1),
                                      false });
        parameters.add(new Object[] { String.valueOf(Integer.MAX_VALUE), GREATER_THAN,
                                      String.valueOf(Integer.MAX_VALUE - 1), true });
        parameters.add(new Object[] { String.valueOf(Integer.MAX_VALUE), LESSER_THAN,
                                      String.valueOf(Integer.MAX_VALUE - 1), false });
        parameters.add(new Object[] { String.valueOf(Integer.MAX_VALUE - 1), EQUALS, String.valueOf(Integer.MAX_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Integer.MAX_VALUE - 1), GREATER_THAN,
                                      String.valueOf(Integer.MAX_VALUE), false });
        parameters.add(new Object[] { String.valueOf(Integer.MAX_VALUE - 1), LESSER_THAN,
                                      String.valueOf(Integer.MAX_VALUE), true }); // [20]
        parameters.add(new Object[] { String.valueOf(Integer.MIN_VALUE), EQUALS, String.valueOf(Integer.MIN_VALUE + 1),
                                      false });
        parameters.add(new Object[] { String.valueOf(Integer.MIN_VALUE), GREATER_THAN,
                                      String.valueOf(Integer.MIN_VALUE + 1), false });
        parameters.add(new Object[] { String.valueOf(Integer.MIN_VALUE), LESSER_THAN,
                                      String.valueOf(Integer.MIN_VALUE + 1), true });
        parameters.add(new Object[] { String.valueOf(Integer.MIN_VALUE + 1), EQUALS, String.valueOf(Integer.MIN_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Integer.MIN_VALUE + 1), GREATER_THAN,
                                      String.valueOf(Integer.MIN_VALUE), true });
        parameters.add(new Object[] { String.valueOf(Integer.MIN_VALUE + 1), LESSER_THAN,
                                      String.valueOf(Integer.MIN_VALUE), false });

        parameters.add(new Object[] { String.valueOf(Long.MAX_VALUE), EQUALS, String.valueOf(Long.MIN_VALUE), false });
        parameters.add(new Object[] { String.valueOf(Long.MAX_VALUE), GREATER_THAN, String.valueOf(Long.MIN_VALUE),
                                      true });
        parameters.add(new Object[] { String.valueOf(Long.MAX_VALUE), LESSER_THAN, String.valueOf(Long.MIN_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Long.MAX_VALUE), EQUALS, String.valueOf(Long.MAX_VALUE - 1),
                                      false }); // [30]
        parameters.add(new Object[] { String.valueOf(Long.MAX_VALUE), GREATER_THAN, String.valueOf(Long.MAX_VALUE - 1),
                                      true });
        parameters.add(new Object[] { String.valueOf(Long.MAX_VALUE), LESSER_THAN, String.valueOf(Long.MAX_VALUE - 1),
                                      false });
        parameters.add(new Object[] { String.valueOf(Long.MAX_VALUE - 1), EQUALS, String.valueOf(Long.MAX_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Long.MAX_VALUE - 1), GREATER_THAN, String.valueOf(Long.MAX_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Long.MAX_VALUE - 1), LESSER_THAN, String.valueOf(Long.MAX_VALUE),
                                      true });
        parameters.add(new Object[] { String.valueOf(Long.MIN_VALUE), EQUALS, String.valueOf(Long.MIN_VALUE + 1),
                                      false });
        parameters.add(new Object[] { String.valueOf(Long.MIN_VALUE), GREATER_THAN, String.valueOf(Long.MIN_VALUE + 1),
                                      false });
        parameters.add(new Object[] { String.valueOf(Long.MIN_VALUE), LESSER_THAN, String.valueOf(Long.MIN_VALUE + 1),
                                      true });
        parameters.add(new Object[] { String.valueOf(Long.MIN_VALUE + 1), EQUALS, String.valueOf(Long.MIN_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Long.MIN_VALUE + 1), GREATER_THAN, String.valueOf(Long.MIN_VALUE),
                                      true }); // [40]
        parameters.add(new Object[] { String.valueOf(Long.MIN_VALUE + 1), LESSER_THAN, String.valueOf(Long.MIN_VALUE),
                                      false });


        parameters.add(new Object[] { "1.0", EQUALS, "1.0", true });
        parameters.add(new Object[] { "1.0", EQUALS, "1,0", true });
        parameters.add(new Object[] { "1,0", EQUALS, "1,0", true });
        parameters.add(new Object[] { "1,0", EQUALS, "1.0", true });
        parameters.add(new Object[] { "1.0", EQUALS, "0.0", false });
        parameters.add(new Object[] { "1.0", EQUALS, "0,0", false });
        parameters.add(new Object[] { "1,0", EQUALS, "0,0", false });
        parameters.add(new Object[] { "1,0", EQUALS, "0.0", false });
        parameters.add(new Object[] { "0.0", EQUALS, "1.0", false }); // [50]
        parameters.add(new Object[] { "0.0", EQUALS, "1,0", false });
        parameters.add(new Object[] { "0,0", EQUALS, "1,0", false });
        parameters.add(new Object[] { "0.0", EQUALS, "1,0", false });
        parameters.add(new Object[] { "1.0", GREATER_THAN, "1.0", false });
        parameters.add(new Object[] { "1.0", GREATER_THAN, "1,0", false });
        parameters.add(new Object[] { "1,0", GREATER_THAN, "1,0", false });
        parameters.add(new Object[] { "1,0", GREATER_THAN, "1.0", false });
        parameters.add(new Object[] { "1.0", GREATER_THAN, "0.0", true });
        parameters.add(new Object[] { "1.0", GREATER_THAN, "0,0", true });
        parameters.add(new Object[] { "1,0", GREATER_THAN, "0,0", true }); // [60]
        parameters.add(new Object[] { "1,0", GREATER_THAN, "0.0", true });
        parameters.add(new Object[] { "0.0", GREATER_THAN, "1.0", false });
        parameters.add(new Object[] { "0.0", GREATER_THAN, "1,0", false });
        parameters.add(new Object[] { "0,0", GREATER_THAN, "1,0", false });
        parameters.add(new Object[] { "0,0", GREATER_THAN, "1.0", false });
        parameters.add(new Object[] { "1.0", LESSER_THAN, "1.0", false });
        parameters.add(new Object[] { "1.0", LESSER_THAN, "1,0", false });
        parameters.add(new Object[] { "1,0", LESSER_THAN, "1,0", false });
        parameters.add(new Object[] { "1,0", LESSER_THAN, "1.0", false });
        parameters.add(new Object[] { "1.0", LESSER_THAN, "0.0", false }); // [70]
        parameters.add(new Object[] { "1.0", LESSER_THAN, "0,0", false });
        parameters.add(new Object[] { "1,0", LESSER_THAN, "0,0", false });
        parameters.add(new Object[] { "1,0", LESSER_THAN, "0.0", false });
        parameters.add(new Object[] { "0.0", LESSER_THAN, "1.0", true });
        parameters.add(new Object[] { "0.0", LESSER_THAN, "1,0", true });
        parameters.add(new Object[] { "0,0", LESSER_THAN, "1,0", true });
        parameters.add(new Object[] { "0,0", LESSER_THAN, "1.0", true });
        parameters.add(new Object[] { "9.0", EQUALS, "10.0", false });
        parameters.add(new Object[] { "9.0", EQUALS, "10,0", false });
        parameters.add(new Object[] { "9,0", EQUALS, "10,0", false }); // [80]
        parameters.add(new Object[] { "9,0", EQUALS, "10.0", false });
        parameters.add(new Object[] { "9.0", GREATER_THAN, "10.0", false });
        parameters.add(new Object[] { "9.0", GREATER_THAN, "10,0", false });
        parameters.add(new Object[] { "9,0", GREATER_THAN, "10,0", false });
        parameters.add(new Object[] { "9,0", GREATER_THAN, "10.0", false });
        parameters.add(new Object[] { "9.0", LESSER_THAN, "10.0", true });
        parameters.add(new Object[] { "9.0", LESSER_THAN, "10,0", true });
        parameters.add(new Object[] { "9,0", LESSER_THAN, "10,0", true });
        parameters.add(new Object[] { "9,0", LESSER_THAN, "10.0", true });

        parameters.add(new Object[] { String.valueOf(Float.MAX_VALUE), EQUALS, String.valueOf(-Float.MAX_VALUE),
                                      false }); // [90]
        parameters.add(new Object[] { String.valueOf(Float.MAX_VALUE), GREATER_THAN, String.valueOf(-Float.MAX_VALUE),
                                      true });
        parameters.add(new Object[] { String.valueOf(Float.MAX_VALUE), LESSER_THAN, String.valueOf(-Float.MAX_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Float.MAX_VALUE), EQUALS,
                                      String.valueOf(nextLowestNumber(Float.MAX_VALUE)), false });
        parameters.add(new Object[] { String.valueOf(Float.MAX_VALUE), GREATER_THAN,
                                      String.valueOf(nextLowestNumber(Float.MAX_VALUE)), true });
        parameters.add(new Object[] { String.valueOf(Float.MAX_VALUE), LESSER_THAN,
                                      String.valueOf(nextLowestNumber(Float.MAX_VALUE)), false });
        parameters.add(new Object[] { String.valueOf(nextLowestNumber(Float.MAX_VALUE)), EQUALS,
                                      String.valueOf(Float.MAX_VALUE), false });
        parameters.add(new Object[] { String.valueOf(nextLowestNumber(Float.MAX_VALUE)), GREATER_THAN,
                                      String.valueOf(Float.MAX_VALUE), false });
        parameters.add(new Object[] { String.valueOf(nextLowestNumber(Float.MAX_VALUE)), LESSER_THAN,
                                      String.valueOf(Float.MAX_VALUE), true });
        parameters.add(new Object[] { String.valueOf(-Float.MAX_VALUE), EQUALS,
                                      String.valueOf(nextHighestNumber(-Float.MAX_VALUE)), false });
        parameters.add(new Object[] { String.valueOf(-Float.MAX_VALUE), GREATER_THAN,
                                      String.valueOf(nextHighestNumber(-Float.MAX_VALUE)), false }); // [100]
        parameters.add(new Object[] { String.valueOf(-Float.MAX_VALUE), LESSER_THAN,
                                      String.valueOf(nextHighestNumber(-Float.MAX_VALUE)), true });
        parameters.add(new Object[] { String.valueOf(nextHighestNumber(-Float.MAX_VALUE)), EQUALS,
                                      String.valueOf(-Float.MAX_VALUE), false });
        parameters.add(new Object[] { String.valueOf(nextHighestNumber(-Float.MAX_VALUE)), GREATER_THAN,
                                      String.valueOf(-Float.MAX_VALUE), true });
        parameters.add(new Object[] { String.valueOf(nextHighestNumber(-Float.MAX_VALUE)), LESSER_THAN,
                                      String.valueOf(-Float.MAX_VALUE), false });

        parameters.add(new Object[] { String.valueOf(Double.MAX_VALUE), EQUALS, String.valueOf(-Double.MAX_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Double.MAX_VALUE), GREATER_THAN, String.valueOf(-Double.MAX_VALUE),
                                      true });
        parameters.add(new Object[] { String.valueOf(Double.MAX_VALUE), LESSER_THAN, String.valueOf(-Double.MAX_VALUE),
                                      false });
        parameters.add(new Object[] { String.valueOf(Double.MAX_VALUE), EQUALS,
                                      String.valueOf(nextLowestNumber(Double.MAX_VALUE)), false });
        parameters.add(new Object[] { String.valueOf(Double.MAX_VALUE), GREATER_THAN,
                                      String.valueOf(nextLowestNumber(Double.MAX_VALUE)), true });
        parameters.add(new Object[] { String.valueOf(Double.MAX_VALUE), LESSER_THAN,
                                      String.valueOf(nextLowestNumber(Double.MAX_VALUE)), false }); // [110]
        parameters.add(new Object[] { String.valueOf(nextLowestNumber(Double.MAX_VALUE)), EQUALS,
                                      String.valueOf(Double.MAX_VALUE), false });
        parameters.add(new Object[] { String.valueOf(nextLowestNumber(Double.MAX_VALUE)), GREATER_THAN,
                                      String.valueOf(Double.MAX_VALUE), false });
        parameters.add(new Object[] { String.valueOf(nextLowestNumber(Double.MAX_VALUE)), LESSER_THAN,
                                      String.valueOf(Double.MAX_VALUE), true });
        parameters.add(new Object[] { String.valueOf(-Double.MAX_VALUE), EQUALS,
                                      String.valueOf(nextHighestNumber(-Double.MAX_VALUE)), false });
        parameters.add(new Object[] { String.valueOf(-Double.MAX_VALUE), GREATER_THAN,
                                      String.valueOf(nextHighestNumber(-Double.MAX_VALUE)), false });
        parameters.add(new Object[] { String.valueOf(-Double.MAX_VALUE), LESSER_THAN,
                                      String.valueOf(nextHighestNumber(-Double.MAX_VALUE)), true });
        parameters.add(new Object[] { String.valueOf(nextHighestNumber(-Double.MAX_VALUE)), EQUALS,
                                      String.valueOf(-Double.MAX_VALUE), false });
        parameters.add(new Object[] { String.valueOf(nextHighestNumber(-Double.MAX_VALUE)), GREATER_THAN,
                                      String.valueOf(-Double.MAX_VALUE), true }); // [118] = X
        parameters.add(new Object[] { String.valueOf(nextHighestNumber(-Double.MAX_VALUE)), LESSER_THAN,
                                      String.valueOf(-Double.MAX_VALUE), false });


        for (int a = 0; a < 200; a++) {

            randomExpression(parameters);
        }

        return parameters;
    }

}
