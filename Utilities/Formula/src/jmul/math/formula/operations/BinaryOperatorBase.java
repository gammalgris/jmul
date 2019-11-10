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
 * A base implementation for binary operators.
 *
 * @author Kristian Kutin
 */
abstract class BinaryOperatorBase extends OperatorBase {

    /**
     * The default constructor.
     *
     * @param aSymbol
     *        a symbol associated with the operator
     * @param aPriority
     *        the priority of the operator
     * @param anArity
     *        the arity of the operator
     * @param aPosition
     *        the position of the operator
     */
    protected BinaryOperatorBase(String aSymbol, int aPriority, Arity anArity, Position aPosition) {

        super(aSymbol, aPriority, anArity, aPosition);

        boolean valid = (anArity.equals(Arity.BINARY)) && (aPosition.equals(Position.BETWEEN_OPERANDS));
        if (!valid) {
            String message =
                "The operator " + getSymbol() + " has an invalid arity (" + getArity() + ") or position (" +
                getPosition() + ")";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * The method checks the operands.
     *
     * @param someOperands
     *        some oeprands
     */
    protected void checkOperands(int[] someOperands) {

        if ((someOperands == null) || ((someOperands != null) && (someOperands.length == 0))) {
            String message = "Cannot perform operation on an empty array of operands!";
            throw new IllegalArgumentException(message);
        }

        if (someOperands.length == 1) {
            String message = "The " + getArity() + " operator " + getSymbol() + " cannot handle just 1 operand!";
            throw new IllegalArgumentException(message);
        }
    }

}
