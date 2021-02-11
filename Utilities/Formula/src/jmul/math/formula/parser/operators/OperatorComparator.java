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

package jmul.math.formula.parser.operators;


import java.util.Comparator;


/**
 * A comparator for operators.
 *
 * @author Kristian Kutin
 */
public class OperatorComparator implements Comparator<Operator> {

    /**
     * The default constructor.
     */
    public OperatorComparator() {

        super();
    }

    /**
     * Compares the specified operators and returns a negative integer, zero or a positive
     * integer as the first operator is less than, equal to, or greater than the second
     * operator.
     *
     * @param o1
     *        the first operator
     * @param o2
     *        the second operator
     *
     * @return a negative integer, zero or a positive integer as the first operator is
     *         less than, equal to, or greater than the second operator
     */
    @Override
    public int compare(Operator o1, Operator o2) {

        if (o1.getPrecedence() < o2.getPrecedence()) {

            return -1;

        } else if (o1.getPrecedence() > o2.getPrecedence()) {

            return 1;

        } else {

            return 0;
        }
    }

}
