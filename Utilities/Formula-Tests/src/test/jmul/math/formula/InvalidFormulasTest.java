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


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.formula.parser.FormulaParserException;

import jmul.test.classification.ModuleTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * A set of tests for formulas containing brackets.
 *
 * @author Kristian Kutin
 */
@ModuleTest
@RunWith(Parameterized.class)
public class InvalidFormulasTest extends FormulaTestBase {

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "((1 + 2() - 3) + 1" });
        parameters.add(new Object[] { ")))((((" });
        parameters.add(new Object[] { "-(1" });
        parameters.add(new Object[] { "d-1)(" });

        return parameters;
    }

    /**
     * Contains a formula string.
     */
    private String formulaString;

    /**
     * Constructs this test object.
     *
     * @param formulaString
     *        a formula string
     */
    public InvalidFormulasTest(String formulaString) {

        this.formulaString = formulaString;
    }

    /**
     * The actual test method. The specified formula string is evaluated and the
     * result is compared with the specified result.
     */
    @Test(expected = FormulaParserException.class)
    public void testFormula() {

        getFormulaParser().parseString(formulaString);
    }

}
