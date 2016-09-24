/*
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

package jmul.math.formula.operations;


import jmul.math.formula.Operand;


/**
 * The interface describes an operation.
 *
 * @author Kristian Kutin
 */
public interface Operator {

    /**
     * The method returns the operation symbol of a given operation.
     *
     * @return an operation symbol
     */
    String getSymbol();

    /**
     * The method performs the actual operation
     *
     * @param someOperands
     *        all required operands
     *
     * @return the operation result
     */
    int performOperation(Operand[] someOperands);

    /**
     * The method performs the actual operation
     *
     * @param someOperands
     *        all required operands
     *
     * @return the operation result
     */
    int performOperation(int[] someOperands);

    /**
     * The method returns the priority of an operator. A high priority is
     * represented by a high value.
     *
     * @return a priority
     */
    int getPriority();

    /**
     * The method returns the arity of this operator.
     *
     * @return the arity of this operator
     */
    Arity getArity();

    /**
     * The method returns the position of this oeprator.
     *
     * @return the position of this operator
     */
    Position getPosition();

}
