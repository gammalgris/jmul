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

package test.jmul.math.random;


import java.util.ArrayList;
import java.util.Collection;

import static jmul.math.random.DiceFactory.createDie;
import jmul.math.random.Die;
import static jmul.math.rules.RuleFactory.createMaximumValueRule;
import static jmul.math.rules.RuleFactory.createMinimumValueRule;
import static jmul.math.rules.RuleFactory.createRuleSet;
import jmul.math.rules.RuleSet;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Performs tests which check the results of a die roll.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DieRollTest {

    /**
     * Returns a matrix of input parameters.
     *
     * @return a matrix of input parameters
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] {
                       createRuleSet(createMinimumValueRule(1), createMaximumValueRule(4)), createDie(4), 10000 });
        parameters.add(new Object[] {
                       createRuleSet(createMinimumValueRule(1), createMaximumValueRule(6)), createDie(6), 10000 });
        parameters.add(new Object[] {
                       createRuleSet(createMinimumValueRule(1), createMaximumValueRule(8)), createDie(8), 10000 });
        parameters.add(new Object[] {
                       createRuleSet(createMinimumValueRule(1), createMaximumValueRule(10)), createDie(10), 10000
        });
        parameters.add(new Object[] {
                       createRuleSet(createMinimumValueRule(1), createMaximumValueRule(12)), createDie(12), 10000
        });
        parameters.add(new Object[] {
                       createRuleSet(createMinimumValueRule(1), createMaximumValueRule(20)), createDie(20), 10000
        });
        parameters.add(new Object[] {
                       createRuleSet(createMinimumValueRule(1), createMaximumValueRule(100)), createDie(100), 10000
        });

        return parameters;
    }

    /**
     * The value range for the result of a die roll.
     */
    private final RuleSet allowedValueRange;

    /**
     * A die.
     */
    private final Die die;

    /**
     * The number of times the die is rolled.
     */
    private final int repetitions;

    /**
     * Creates an error message for a failed test according to the specified parameters.
     *
     * @param anAllowedValueRange
     * @param aDie
     * @param repetitions
     */
    public DieRollTest(RuleSet anAllowedValueRange, Die aDie, int repetitions) {

        allowedValueRange = anAllowedValueRange;
        die = aDie;
        this.repetitions = repetitions;
    }

    /**
     * Tests if the result of a die roll stays within the range of allowed values.
     */
    @Test
    public void testRolls() {

        for (int a = 0; a < repetitions; a++) {

            int result = die.roll();

            assertTrue(createErrorMessage(allowedValueRange, die, result), allowedValueRange.checkNumericValue(result));
        }
    }

    /**
     * Creates an error message for a failed test according to the specified parameters.
     *
     * @param anAllowedValueRange
     * @param aDie
     * @param aResult
     *
     * @return an error message for a failed test
     */
    private static String createErrorMessage(RuleSet anAllowedValueRange, Die aDie, int aResult) {

        StringBuffer buffer = new StringBuffer();

        buffer.append("The test has failed because the result of a die roll is not within the allowed boundaries (");
        buffer.append(aDie);
        buffer.append(", ");
        buffer.append("result=");
        buffer.append(aResult);
        buffer.append(", ");
        buffer.append(anAllowedValueRange);
        buffer.append(")!");

        return String.valueOf(buffer);
    }

}
