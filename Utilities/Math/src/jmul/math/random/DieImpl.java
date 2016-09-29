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

package jmul.math.random;


import static jmul.math.rules.RuleFactory.createMinimumValueRule;
import static jmul.math.rules.RuleFactory.createRuleSet;
import jmul.math.rules.RuleSet;


/**
 * An implementation of a standard die where each side is represented by a number.
 *
 * @author Kristian Kutin
 */
public class DieImpl implements Die {

    /**
     * A rule set which contains details about the allowed value range for the number of sides of a die.
     */
    private static final RuleSet ALLOWED_VALUE_RANGE;

    /*
     * The static initializer.
     */
    static {

        ALLOWED_VALUE_RANGE = createRuleSet(createMinimumValueRule(1));
    }

    /**
     * The number of sides of this die.
     */
    private final int sides;

    /**
     * Creates a new die according to the specified parameters.
     *
     * @param sides
     */
    public DieImpl(int sides) {

        checkParameter(sides);

        this.sides = sides;
    }

    /**
     * A getter method.
     *
     * @return the sides of this die
     */
    @Override
    public int getSides() {

        return sides;
    }

    /**
     * Performs a die roll.
     *
     * @return the result of a die roll
     */
    @Override
    public int roll() {

        return (int) (Math.random() * getSides() + 1);
    }

    /**
     * Returns a string representation of this die.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append("d");
        buffer.append(getSides());

        return String.valueOf(buffer);
    }

    /**
     * Checks if the specified numeric value is withing the allowed value range.
     *
     * @param anAbilityScore
     */
    private static void checkParameter(int aNumericValue) {

        boolean isValid = ALLOWED_VALUE_RANGE.checkNumericValue(aNumericValue);

        if (!isValid) {

            StringBuffer errorMessage = new StringBuffer();

            errorMessage.append("An invalid value was specified (value=");
            errorMessage.append(aNumericValue);
            errorMessage.append("; ");
            errorMessage.append(ALLOWED_VALUE_RANGE);
            errorMessage.append(")!");

            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

}
