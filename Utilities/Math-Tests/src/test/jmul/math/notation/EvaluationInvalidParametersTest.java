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

import jmul.checks.exceptions.EmptyStringParameterException;
import jmul.checks.exceptions.NullParameterException;

import jmul.math.notation.Comparators;
import static jmul.math.notation.Comparators.EQUALS;
import jmul.math.notation.NotationHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests to compare numbers which are provided as strings. The utility methods
 * are invoked with invalid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class EvaluationInvalidParametersTest {

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
     * The expected exception type.
     */
    private Class exceptionType;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param aFirstNumber
     * @param aComparator
     * @param aSecondNumber
     * @param anExceptionType
     */
    public EvaluationInvalidParametersTest(String aFirstNumber, Comparators aComparator, String aSecondNumber,
                                           Class anExceptionType) {

        super();

        firstNumber = aFirstNumber;
        comparator = aComparator;
        secondNumber = aSecondNumber;
        exceptionType = anExceptionType;
    }

    /**
     * Tests the evaluation of a number comparison.
     */
    @Test
    public void testEvaluation() {

        try {

            NotationHelper.evaluate(firstNumber, comparator, secondNumber);

            fail("An exception is expected but non was thrown!");

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();

            assertEquals(exceptionType, actualExceptionType);
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

        parameters.add(new Object[] { null, EQUALS, "1", NullParameterException.class });
        parameters.add(new Object[] { "", EQUALS, "1", EmptyStringParameterException.class });
        parameters.add(new Object[] { "1", EQUALS, null, NullParameterException.class });
        parameters.add(new Object[] { "1", EQUALS, "", EmptyStringParameterException.class });
        parameters.add(new Object[] { "1", null, "1", NullParameterException.class });

        return parameters;
    }

}
