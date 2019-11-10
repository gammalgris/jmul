/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package jmul.math.rules;


/**
 * This interface describes a rule.
 *
 * @author Kristian Kutin
 */
public interface Rule {

    /**
     * Checks if the specified numeric value adheres to this rule.
     *
     * @param aNumericValue
     *        the number which is to be checked
     *
     * @return <code>true</code> the numeric value adheres to this rule, else <code>false</code>
     */
    boolean checkNumericValue(int aNumericValue);

    /**
     * Returns a string representation of this rule.
     *
     * @return a string representation
     */
    @Override
    String toString();

}
