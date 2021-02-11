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

package jmul.math.formula.parser.components;

import jmul.math.formula.FormulaException;


/**
 * An implementation of an operand.
 *
 * @author Kristian Kutin
 */
public class Variable extends ComponentBase implements Operand {

    /**
     * The variable name.
     */
    private final String name;

    /**
     * A reference to an object which contains
     * the variable value.
     */
    private Number reference;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aName
     *        the variable name
     */
    public Variable(CharSequence aName) {

        this(aName, null);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aName
     *        the variable name
     * @param aReference
     *        a reference to the actual variable value
     */
    public Variable(CharSequence aName, Number aReference) {

        super();

        name = aName.toString();
        reference = aReference;
    }

    /**
     * Returns the variable name.
     *
     * @return the nam of the variable
     */
    public String getVariableName() {

        return name;
    }

    /**
     * sets a refrence to the actual variable value.
     *
     * @param aReference
     *        a refrenece to a value
     */
    public void setReference(Number aReference) {

        reference = aReference;
    }

    /**
     * Returns the value of the operand.
     *
     * @return a value
     */
    @Override
    public int getValue() {

        if (reference == null) {

            String message = String.format("The variable (%s) has no assigned value!", name);
            throw new FormulaException(message);

        } else {

            return reference.intValue();
        }
    }

    /**
     * Calculates and returns the formula which is represented by this node, i.e.
     * this branch of the formula tree.
     *
     * @return a calculation result
     */
    @Override
    public int calculate() {

        return getValue();
    }

    /**
     * Returns a string representation for this component.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getVariableName();
    }

}
