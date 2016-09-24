/*
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
 * An implementation of a rule which checks that an ability score meets a minimum value requirement.
 *
 * @author Kristian Kutin
 */
public class MinimumValueRule implements Rule {

    /**
     * A threshold for a minim value.
     */
    private final int minimumValue;

    /**
     * Creates a new rule.
     *
     * @param aMinimumValue
     */
    public MinimumValueRule(int aMinimumValue) {

        minimumValue = aMinimumValue;
    }

    /**
     * A getter method.
     *
     * @return a minim value
     */
    public int getMinimumValue() {

        return minimumValue;
    }

    /**
     * Checks if the specified numeric value adheres to this rule.
     *
     * @param aNumericValue
     *
     * @return <code>true</code> the numeric value adheres to this rule, else <code>false</code>
     */
    @Override
    public boolean checkNumericValue(int aNumericValue) {

        return aNumericValue >= getMinimumValue();
    }

    /**
     * Returns a string representation of this rule.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append("Rule{");
        buffer.append("minimum value=");
        buffer.append(getMinimumValue());
        buffer.append("}");

        return String.valueOf(buffer);
    }

}
