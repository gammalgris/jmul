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

package test.jmul.math.rules;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.rules.Rule;
import static jmul.math.rules.RuleFactory.createEqualValueRule;
import static jmul.math.rules.RuleFactory.createMaximumValueRule;
import static jmul.math.rules.RuleFactory.createMinimumValueRule;
import static jmul.math.rules.RuleFactory.createRuleSet;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Perform tests with various rules which are supposed to pass without errors.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RulesFailureTest {

    /**
     * Returns a matrix of input parameters.
     *
     * @return a matrix of input parameters
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { createMinimumValueRule(3), 2 });

        parameters.add(new Object[] { createMaximumValueRule(18), 19 });

        parameters.add(new Object[] { createEqualValueRule(18), 19 });

        parameters.add(new Object[] { createRuleSet(createMinimumValueRule(3), createMaximumValueRule(18)), 2 });
        parameters.add(new Object[] { createRuleSet(createMinimumValueRule(3), createMaximumValueRule(18)), 19 });

        return parameters;
    }

    /**
     * A rule.
     */
    private final Rule rule;

    /**
     * A value that is checked for validity by the rule.
     */
    private final int value;

    /**
     * Creates a new test according to the specified paramters.
     *
     * @param aRule
     * @param aValue
     */
    public RulesFailureTest(Rule aRule, int aValue) {

        rule = aRule;
        value = aValue;
    }

    /**
     * Checks if a numeric value adheres to a rule.
     */
    @Test
    public void checkValue() {

        boolean result = rule.checkNumericValue(value);

        assertFalse(createErrorMessage(rule, value), result);
    }

    /**
     * Creates an error message for a failed test according to the specified parameters.
     *
     * @param aRule
     * @param aValue
     *
     * @return an error message for a failed test
     */
    private static String createErrorMessage(Rule aRule, int aValue) {

        StringBuffer buffer = new StringBuffer();

        buffer.append("The test has failed (");
        buffer.append(aRule);
        buffer.append(", ");
        buffer.append("value=");
        buffer.append(aValue);
        buffer.append(")!");

        return String.valueOf(buffer);
    }

}
