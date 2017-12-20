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

package test.jmul.math.formula;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.formula.Formula;

import jmul.test.classification.ModuleTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * A set of tests for the formula parser.
 *
 * @author Kristian Kutin
 */
@ModuleTest
@RunWith(Parameterized.class)
public class SimpleFormulasTest extends FormulaTestBase {

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "4+5", 9 });
        parameters.add(new Object[] { "   4     +    5       ", 9 });
        parameters.add(new Object[] { "4++5", 9 });
        parameters.add(new Object[] { "4+ -5", -1 });
        parameters.add(new Object[] { "4+ (-5)", -1 });
        parameters.add(new Object[] { "-4 +5", 1 });
        parameters.add(new Object[] { "(-4) +5", 1 });
        parameters.add(new Object[] { "10-7", 3 });
        parameters.add(new Object[] { "5*5", 25 });
        parameters.add(new Object[] { "25/5", 5 });
        parameters.add(new Object[] { "21/5", 4 });
        parameters.add(new Object[] { "1 +2 - ( 1 + 2 )", 0 });
        parameters.add(new Object[] { "100+205-15+234", 524 });
        parameters.add(new Object[] { "((100+205)-15)+234", 524 });
        parameters.add(new Object[] { "100+205+234-15", 524 });

        return parameters;
    }

    /**
     * Contains a formula string.
     */
    private String formulaString;

    /**
     * Contains an expected result.
     */
    private int expectedResult;

    /**
     * Constructs this test object.
     *
     * @param formulaString
     *        a formula string
     * @param expectedResult
     *        the expected result
     */
    public SimpleFormulasTest(String formulaString, int expectedResult) {

        this.formulaString = formulaString;
        this.expectedResult = expectedResult;
    }

    /**
     * The actual test method. The specified formula string is evaluated and the
     * result is compared with the specified result.
     */
    @Test
    public void testFormula() {

        Formula formula = getFormulaParser().parseString(formulaString);
        int result = formula.calculate();

        assertEquals(formulaString, expectedResult, result);
    }

}
