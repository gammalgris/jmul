/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package jmul.math.notation.comparators;


import jmul.math.notation.NotationHelper;
import jmul.math.notation.NotationProperties;


/**
 * A base implementation of a comparator.
 *
 * @author Kristian Kutin
 */
abstract class NumberComparatorBase implements NumberComparator {

    /**
     * The default constructor.
     */
    public NumberComparatorBase() {

        super();
    }

    /**
     * A comparison according to the specified parameters is made.
     *
     * @param firstNumber
     *        the first number as string
     * @param secondNumber
     *        the second number as string
     *
     * @return <code>true</code> if the statement is true, else <code>false</code>
     */
    @Override
    public boolean compare(String firstNumber, String secondNumber) {

        NotationProperties firstProperties = NotationHelper.checkString(firstNumber);
        NotationProperties secondProperties = NotationHelper.checkString(secondNumber);

        return compare(firstNumber, firstProperties, secondNumber, secondProperties);
    }

}
