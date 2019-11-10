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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.math.formula;


import jmul.math.formula.Formula;

import jmul.test.classification.ModuleTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * A set of tests for formulas containing brackets.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class BracketsTest extends FormulaTestBase {

    /**
     * The actual test method. The specified formula string is evaluated and the
     * result is compared with the specified result.
     */
    @Test
    public void testFormula() {

        String formulaString = "((1 + 2) - 3) + 1";
        int expectedResult = 1;


        Formula formula = getFormulaParser().parseString(formulaString);
        int result = formula.calculate();

        assertEquals(formulaString, expectedResult, result);
    }

}
