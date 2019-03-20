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

package jmul.math.notation.comparators;


import jmul.math.notation.NotationProperties;
import jmul.math.notation.Signs;


/**
 * An implementation of a comparator which checks the equality of two numbers.
 *
 * @author Kristian Kutin
 */
public class IntegerEqualsComparator extends NumberComparatorBase {

    /**
     * The default constructor.
     */
    public IntegerEqualsComparator() {

        super();
    }

    /**
     * A comparison according to the specified parameters is made.
     *
     * @param firstNumber
     * @param firstProperties
     * @param secondNumber
     * @param secondProperties
     *
     * @return <code>true</code> if the statement is true, else <code>false</code>
     */
    @Override
    public boolean compare(String firstNumber, NotationProperties firstProperties, String secondNumber,
                           NotationProperties secondProperties) {

        Signs firstSign = firstProperties.getSign();
        int firstLength = firstNumber.length();

        Signs secondSign = secondProperties.getSign();
        int secondLength = secondNumber.length();

        if ((firstSign == secondSign) && (firstLength == secondLength)) {

            return firstNumber.compareTo(secondNumber) == 0;
        }

        return false;
    }

}
