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

package jmul.math.markov;


import jmul.misc.exceptions.EmptyStringParameterException;
import jmul.misc.exceptions.NullParameterException;

import jmul.string.TextHelper;


/**
 * The class implements a single rule for use with a markov algorithm.
 *
 * @author Kristian Kutin
 */
public class Rule {

    /**
     * The class member contains the left side of the rule.
     */
    private final String leftSide;

    /**
     * The class member contains the right side of the rule.
     */
    private final String rightSide;

    /**
     * The default constructor.
     *
     * @param aLeftSide
     *        the left side of the rule
     * @param aRightSide
     *        the right side of the rule
     */
    public Rule(String aLeftSide, String aRightSide) {

        checkLeftSide(aLeftSide);
        checkRightSide(aRightSide);

        leftSide = aLeftSide;
        rightSide = aRightSide;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aParameter
     *
     * @throws IllegalArgumentException
     *         if the specified parameter is invalid
     */
    private static void checkLeftSide(String aParameter) {

        if (aParameter == null) {

            throw new NullParameterException();
        }

        if (aParameter.length() == 0) {

            throw new EmptyStringParameterException();
        }

    }

    /**
     * Checks the specified parameter.
     *
     * @param aParameter
     *
     * @throws IllegalArgumentException
     *         if the specified parameter is invalid
     */
    private static void checkRightSide(String aParameter) {

        if (aParameter == null) {

            throw new NullParameterException();
        }
    }

    /**
     * The method applies this rule on a string.
     *
     * @param aString
     *        the string on which this rules is to be applied
     *
     * @return a modified string
     */
    public String applyRule(String aString) {

        checkParameter(aString);

        return aString.replaceFirst(leftSide, rightSide);
    }

    /**
     * Checks the specified parameter.
     *
     * @param aString
     *
     * @throws IllegalArgumentException
     *         if the specified parameter is invalid
     */
    private static void checkParameter(String aString) {

        if (aString == null) {

            throw new NullParameterException();
        }
    }

    /**
     * The method returns a string representation of this entity.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return TextHelper.concatenateStrings("\"", leftSide, "\" -> \"", rightSide, "\"");
    }

}
