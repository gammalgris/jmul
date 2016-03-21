/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.markov;


import jmul.string.StringConcatenator;


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

        leftSide = aLeftSide;
        rightSide = aRightSide;
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

        return aString.replace(leftSide, rightSide);
    }

    /**
     * The method returns a string representation of this entity.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringConcatenator representation =
            new StringConcatenator(leftSide, " -> ", rightSide);
        return representation.toString();
    }

}
