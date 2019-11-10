/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package jmul.math.formula.operations;


/**
 * An implementation of an operator (negative sign).
 *
 * @author Kristian Kutin
 */
class NegativeSign extends UnaryOperatorBase implements Operator {

    /**
     * The class member contains the symbol for associated with the operator.
     */
    private static final String SYMBOL = "-";

    /**
     * The default constructor.
     */
    public NegativeSign() {

        super(SYMBOL, 6, Arity.UNARY, Position.IN_FRONT_OF_OPERAND);
    }

    /**
     * The method performs the actual operation
     *
     * @param someOperands
     *        all required operands
     *
     * @return the operation result
     */
    @Override
    public int performOperation(int[] someOperands) {

        checkOperands(someOperands);

        return -someOperands[0];
    }

}
