/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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
 * A factory class.
 *
 * @author Kristian Kutin
 */
public final class RuleFactory {

    /**
     * The default constructor.
     */
    private RuleFactory() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new rule set.
     *
     * @param someRules
     *        several rules
     *
     * @return a new rule set
     */
    public static RuleSet createRuleSet(Rule... someRules) {

        return new RuleSetImpl(someRules);
    }

    /**
     * Creates a new minimum value rule.
     *
     * @param aMinimumValue
     *        a threshold value for this rule
     *
     * @return a new minimum value rule
     */
    public static Rule createMinimumValueRule(int aMinimumValue) {

        return new MinimumValueRule(aMinimumValue);
    }

    /**
     * Creates a new equal value rule.
     *
     * @param aSpecificValue
     *        a threshold value for this rule
     *
     * @return a new equal value rule
     */
    public static Rule createEqualValueRule(int aSpecificValue) {

        return new EqualValueRule(aSpecificValue);
    }

    /**
     * Creates a new maximum value rule.
     *
     * @param aMaximumValue
     *        a threshold value for this rule
     *
     * @return a new maximum value rule
     */
    public static Rule createMaximumValueRule(int aMaximumValue) {

        return new MaximumValueRule(aMaximumValue);
    }

}
