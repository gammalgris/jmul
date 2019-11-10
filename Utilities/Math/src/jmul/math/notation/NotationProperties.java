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

package jmul.math.notation;


/**
 * This interface describes the properties for number strings.
 *
 * @author Kristian Kutin
 */
public interface NotationProperties {

    /**
     * Returns the decimal separator for a given number string. If no decimal separator
     * exists an empty string is returned.
     *
     * @return a decimal separator character
     */
    String getDecimalSeparator();

    /**
     * Returns the notation type for a given number string.
     *
     * @return a notation type
     */
    NotationTypes getNotationType();

    /**
     * Checks the sign of the given number string.
     *
     * @return <code>true</code> for a positive number, else <code>false</code>
     */
    boolean isPositive();

    /**
     * Checks the sign of the given number string.
     *
     * @return <code>true</code> for a negative number, else <code>false</code>
     */
    boolean isNegative();

    /**
     * Returns the sign of the given number string.
     *
     * @return a sign
     */
    Signs getSign();

    /**
     * Checks if the given number string has an exponent (i.e. this only applies for certain notations
     * like the scientific notation).
     *
     * @return <code>true</code> if an exponent exists, else <code>false</code>
     */
    boolean hasExponent();

    /**
     * Checks the exponent sign of the given string.
     *
     * @return <code>true</code> for a positive exponent, else <code>false</code>
     */
    boolean hasPositiveExponent();

    /**
     * Checks the exponent sign of the given string.
     *
     * @return <code>true</code> for a negative exponent, else <code>false</code>
     */
    boolean hasNegativeExponent();

    /**
     * Returns the exponent sign of the given number string.
     *
     * @return a sign
     */
    Signs getExponentSign();

}
