/*
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

package test.jmul.math.formula;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.formula.Formula;

import jmul.test.classification.ModuleTest;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * A set of tests for a custom dice operator.
 *
 * @author Kristian Kutin
 */
@ModuleTest
@RunWith(Parameterized.class)
public class DiceOperatorTest extends FormulaTestBase {

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "3d6", 3, 18 });
        parameters.add(new Object[] { "3d6+1", 4, 19 });

        return parameters;
    }

    /**
     * Contains a formula string.
     */
    private String formulaString;

    /**
     * Contains the expected minimum value.
     */
    private int expectedMinValue;

    /**
     * Contains the expected maximum value.
     */
    private int expectedMaxValue;

    /**
     * Constructs this test object.
     *
     * @param formulaString
     *        a formula string
     * @param expectedMinValue
     *        the expected minimum value
     * @param expectedMaxValue
     *        the expected maximum value
     */
    public DiceOperatorTest(String formulaString, int expectedMinValue, int expectedMaxValue) {

        this.formulaString = formulaString;
        this.expectedMinValue = expectedMinValue;
        this.expectedMaxValue = expectedMaxValue;
    }

    /**
     * The actual test method. The specified formula string is evaluated and the
     * result is compared with the specified result.
     */
    @Test
    public void testFormula() {

        Formula formula = getFormulaParser().parseString(formulaString);
        int result = formula.calculate();

        assertTrue(formulaString, (result >= expectedMinValue) && (result <= expectedMaxValue));
    }

}
