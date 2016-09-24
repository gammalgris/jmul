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

package jmul.math.formula;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import jmul.math.formula.operations.Operator;

import jmul.string.StringConcatenator;


/**
 * An implementation of an operand which includes other operands and the
 * details of the operation that is to be performed on the operands.
 *
 * @author Kristian Kutin
 */
public class Term implements Operand {

    /**
     * The class member references an instance which manages all variables
     * which are used within the formula.
     */
    private VariableManager variableManager;

    /**
     * The class member contains all operands which are part of this term.
     */
    private Collection<Operand> operands;

    /**
     * The class member contains the operator which is applied on the operands.
     */
    private final Operator operator;

    /**
     * The default constructor.
     *
     * @param aFormula
     *        the formula this term is part of
     * @param anOperator
     *        an operator
     */
    public Term(Formula aFormula, Operator anOperator) {

        operands = new ArrayList<Operand>();
        operator = anOperator;
        variableManager = aFormula.getVariableManager();
    }

    /**
     * The method adds an operand.
     *
     * @param anOperand
     *        an operand
     */
    public void addOperand(Operand anOperand) {

        if (anOperand instanceof Variable) {

            variableManager.addVariable((Variable) anOperand);
        }

        operands.add(anOperand);
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringConcatenator representation = new StringConcatenator("(");

        Iterator i = operands.iterator();
        while (i.hasNext()) {

            representation.append(i.next().toString());

            if (i.hasNext()) {
                representation.append(" ", getOperator(), " ");
            }
        }

        representation.append(")");

        return representation.toString();
    }

    /**
     * The method calculates the value of the term.
     *
     * @return the value of the term
     */
    public int getValue() {

        Operand[] operandArray = { };
        operandArray = operands.toArray(operandArray);

        return operator.performOperation(operandArray);
    }

    /**
     * The method will return the operator.
     *
     * @return an operator
     */
    public Operator getOperator() {

        return operator;
    }

}
