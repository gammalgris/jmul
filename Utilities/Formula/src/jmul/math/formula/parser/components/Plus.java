/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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


import java.util.Arrays;

import jmul.checks.exceptions.NullParameterException;


/**
 * An implementation of an operator.
 *
 * @author Kristian Kutin
 */
public class Plus extends ComponentBase implements Operator {

    /**
     * Creates a new operator according to the specified parameters.
     *
     * @param someComponents
     *        all child components
     */
    public Plus(Component... someComponents) {

        super(check(someComponents));
    }

    /**
     * Checks the specified parameter.
     *
     * @param someComponents
     *
     * @return the specified parameter
     */
    private static Component[] check(Component... someComponents) {

        if (someComponents == null) {

            throw new NullParameterException();

        } else if ((someComponents.length < 1) || (someComponents.length > 2)) {

            String message =
                String.format("The parameter array is expected to have one or two elements only (%s)!",
                              Arrays.asList(someComponents));
            throw new IllegalArgumentException(message);
        }

        return someComponents;
    }

    /**
     * Calculates and returns the formula which is represented by this node, i.e.
     * this branch of the formula tree.
     *
     * @return a calculation result
     */
    @Override
    public int calculate() {

        if (children() == 1) {

            Component left = getChild(0);

            return left.calculate();

        } else {

            Component left = getChild(0);
            Component right = getChild(1);

            return left.calculate() + right.calculate();
        }
    }

    /**
     * Returns a string representation for this component.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return "+";
    }

}
