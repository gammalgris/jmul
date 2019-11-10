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

import jmul.math.formula.operations.Arity;
import jmul.math.formula.operations.Operation;
import jmul.math.formula.operations.Operator;
import jmul.math.formula.operations.Position;

import jmul.test.classification.ConfigurationTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * A set of tests for the addition operator.
 *
 * @author Kristian Kutin
 */
@ConfigurationTest
@RunWith(Parameterized.class)
public class OperatorTest {

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { Operation.ADDITION.getOperator(), Position.BETWEEN_OPERANDS, Arity.BINARY });
        parameters.add(new Object[] { Operation.DICE_OPERATOR.getOperator(), Position.BETWEEN_OPERANDS, Arity.BINARY });
        parameters.add(new Object[] { Operation.DIVISION.getOperator(), Position.BETWEEN_OPERANDS, Arity.BINARY });
        parameters.add(new Object[] { Operation.MULTIPLICATION.getOperator(), Position.BETWEEN_OPERANDS,
                                      Arity.BINARY });
        parameters.add(new Object[] { Operation.NEGATIVE_SIGN.getOperator(), Position.IN_FRONT_OF_OPERAND,
                                      Arity.UNARY });
        parameters.add(new Object[] { Operation.POSITIVE_SIGN.getOperator(), Position.IN_FRONT_OF_OPERAND,
                                      Arity.UNARY });
        parameters.add(new Object[] { Operation.SUBTRACTION.getOperator(), Position.BETWEEN_OPERANDS, Arity.BINARY });

        return parameters;
    }

    /**
     * The operator which is tested.
     */
    private Operator operator;

    /**
     * The expected position of the operator.
     */
    private Position expectedPosition;

    /**
     * The arity of the operator.
     */
    private Arity expectedArity;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anOperator
     *        an operator
     * @param anExpectedPosition
     *        the oprator's expected position
     * @param anExpectedArity
     *        the operator's expected arity
     */
    public OperatorTest(Operator anOperator, Position anExpectedPosition, Arity anExpectedArity) {

        operator = anOperator;
        expectedPosition = anExpectedPosition;
        expectedArity = anExpectedArity;
    }

    /**
     * Tests the properties of an operator.
     */
    @Test
    public void testOperator() {

        assertEquals(expectedPosition, expectedPosition);
        assertEquals(expectedArity, expectedArity);
    }

}
