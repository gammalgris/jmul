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

package jmul.math.rules;


/**
 * An implementation of a rule which checks that an ability score meets a maximum value requirement.
 *
 * @author Kristian Kutin
 */
public class MaximumValueRule implements Rule {

    /**
     * A threshold for a maximum value.
     */
    private final int maximumValue;

    /**
     * Creates a new rule.
     *
     * @param aMaximumValue
     *        a threshold value for this rule
     */
    public MaximumValueRule(int aMaximumValue) {

        maximumValue = aMaximumValue;
    }

    /**
     * A getter method.
     *
     * @return a maximum value
     */
    public int getMaximumValue() {

        return maximumValue;
    }

    /**
     * Checks if the specified numeric value adheres to this rule.
     *
     * @param aNumericValue
     *        the number which is to be checked
     *
     * @return <code>true</code> the numeric value adheres to this rule, else <code>false</code>
     */
    @Override
    public boolean checkNumericValue(int aNumericValue) {

        return aNumericValue <= getMaximumValue();
    }

    /**
     * Returns a string representation of this rule.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("Rule{");
        buffer.append("maximum value=");
        buffer.append(getMaximumValue());
        buffer.append("}");

        return String.valueOf(buffer);
    }

}
