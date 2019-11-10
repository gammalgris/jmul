/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package test.jmul.math.formula.operations;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.formula.Constant;
import jmul.math.formula.Operand;
import jmul.math.formula.operations.Operation;
import jmul.math.formula.operations.Operator;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * A set of tests for the division operator.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class Division2Test {

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 1, new Operand[] { new Constant(1), new Constant(1) } });
        parameters.add(new Object[] { -1, new Operand[] { new Constant(1), new Constant(-1) } });
        parameters.add(new Object[] { -1, new Operand[] { new Constant(-1), new Constant(1) } });
        parameters.add(new Object[] { 1, new Operand[] { new Constant(1), new Constant(1), new Constant(1) } });
        parameters.add(new Object[] { 1,
                                      new Operand[] { new Constant(1), new Constant(1), new Constant(1),
                                                      new Constant(1) } });

        parameters.add(new Object[] { 1, new Operand[] { new Constant(2), new Constant(2) } });
        parameters.add(new Object[] { -1, new Operand[] { new Constant(2), new Constant(-2) } });
        parameters.add(new Object[] { -1, new Operand[] { new Constant(-2), new Constant(2) } });
        parameters.add(new Object[] { 1, new Operand[] { new Constant(2), new Constant(2), new Constant(2) } });
        parameters.add(new Object[] { -1, new Operand[] { new Constant(2), new Constant(-2), new Constant(2) } });
        parameters.add(new Object[] { 0,
                                      new Operand[] { new Constant(2), new Constant(2), new Constant(2),
                                                      new Constant(2) } });
        parameters.add(new Object[] { 0,
                                      new Operand[] { new Constant(2), new Constant(-2), new Constant(2),
                                                      new Constant(-2) } });

        return parameters;
    }

    /**
     * The operator which is tested.
     */
    private Operator operator;

    /**
     * The expected result.
     */
    private int expectedResult;

    /**
     * The operands.
     */
    private Operand[] operands;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param anExpectedResult
     *        the expected result
     * @param someOperands
     *        all operands
     */
    public Division2Test(int anExpectedResult, Operand... someOperands) {

        super();

        expectedResult = anExpectedResult;
        operands = someOperands;
    }

    /**
     * Steps before a test.
     */
    @Before
    public void setUp() {

        operator = Operation.DIVISION.getOperator();
    }

    /**
     * Steps after a test.
     */
    @After
    public void tearDown() {

        operator = null;
    }

    /**
     * Tests the operator.
     */
    @Test
    public void testAddition() {

        int actualResult = operator.performOperation(operands);

        assertEquals(expectedResult, actualResult);
    }

}
