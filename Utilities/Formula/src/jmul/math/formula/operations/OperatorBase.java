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

package jmul.math.formula.operations;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import jmul.math.formula.Operand;


/**
 * A base implementation of an operator.
 *
 * @author Kristian Kutin
 */
abstract class OperatorBase implements Operator {

    /**
     * The class member contains the symbol which is associated with the
     * operator.
     */
    private final String symbol;

    /**
     * The class member contains the priority for the operator.
     */
    private final int priority;

    /**
     * The class member contains the arity of this operator.
     */
    private final Arity arity;

    /**
     * The class member contains the position of this operator.
     */
    private final Position position;

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
    protected OperatorBase(String aSymbol, int aPriority, Arity anArity, Position aPosition) {

        symbol = aSymbol;
        priority = aPriority;
        arity = anArity;
        position = aPosition;
    }

    /**
     * The method returns the operation symbol of a given operation.
     *
     * @return an operation symbol
     */
    @Override
    public String getSymbol() {

        return symbol;
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
    public int performOperation(Operand[] someOperands) {

        int length = someOperands.length;
        int[] operands = new int[length];

        for (int a = 0; a < length; a++) {

            Operand operand = someOperands[a];
            operands[a] = operand.getValue();
        }

        return performOperation(operands);
    }

    /**
     * The method returns the priority of an operator. A high priority is
     * represented by a high value.
     *
     * @return a priority
     */
    @Override
    public int getPriority() {

        return priority;
    }

    /**
     * The method returns the arity of this operator.
     *
     * @return the arity of this operator
     */
    @Override
    public Arity getArity() {

        return arity;
    }

    /**
     * The method returns the position of this oeprator.
     *
     * @return the position of this operator
     */
    @Override
    public Position getPosition() {

        return position;
    }

    /**
     * The overridden equals-method.
     *
     * @param o
     *        another object
     *
     * @return true, if this object equals the other object, else false
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof Operator) {

            Operator other = (Operator) o;

            EqualsBuilder builder = new EqualsBuilder();

            builder.append(this.getSymbol(), other.getSymbol());
            builder.append(this.getArity(), other.getArity());

            return builder.isEquals();
        }

        return false;
    }

    /**
     * The overridden hashCode-method.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getSymbol());
        builder.append(getArity());

        return builder.toHashCode();
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getSymbol();
    }

}
