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

package jmul.math.formula;


/**
 * An implementation of an operand.
 *
 * @author Kristian Kutin
 */
public class Variable implements Operand {

    /**
     * The class member contains a label for the variable.
     */
    private String label;

    /**
     * The class member contains the reference to an object.
     */
    private Object reference;

    /**
     * The default constructor.
     *
     * @param aLabel
     *        a label for the variable
     * @param aReference
     *        a reference to an object
     */
    public Variable(String aLabel, Object aReference) {

        label = aLabel;
        reference = aReference;
    }

    /**
     * And alternative constructor.
     *
     * @param aLabel
     *        a label for the variable
     */
    public Variable(String aLabel) {

        label = aLabel;
        reference = null;
    }

    /**
     * The method returns the numerical value of the operand.
     *
     * @return a numerical value
     */
    @Override
    public int getValue() {

        if (reference != null) {

            if (reference instanceof IntegerValue) {

                IntegerValue entity = (IntegerValue) reference;
                return entity.getIntegerValue();

            } else if (reference instanceof Number) {

                Number number = (Number) reference;
                return number.intValue();

            } else {

                String message =
                    "The class " + reference.getClass().getName() +
                    " doesn't provide a means to get a numerical value!";
                throw new UnsupportedOperationException(message);
            }

        } else {

            String message = "The variable \"" + label + "\" has not yet been initialized!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * The method return the label of the variable.
     *
     * @return a label
     */
    public String getLabel() {

        return label;
    }

    /**
     * The method sets the reference to an external object.
     *
     * @param aReference
     *        a reference to an external object
     */
    public void setVariableReference(Object aReference) {

        reference = aReference;
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getLabel();
    }

}
