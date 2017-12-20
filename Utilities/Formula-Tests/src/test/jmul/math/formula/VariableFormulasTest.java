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
 * A set of tests for formulas with variables.
 */
@ModuleTest
@RunWith(Parameterized.class)
public class VariableFormulasTest extends FormulaTestBase {


    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "4+a", new Variable[] { new Variable("a", 5) }, 9 });
        parameters.add(new Object[] { "a*b", new Variable[] { new Variable("a", 3), new Variable("b", 7) }, 21 });

        return parameters;
    }

    /**
     * Contains a formula string.
     */
    private String formulaString;

    /**
     * Contains initial values for all variables.
     */
    private Variable[] variables;

    /**
     * Contains an expected result.
     */
    private int expectedResult;

    /**
     * Constructs this test object.
     *
     * @param formulaString
     *        a formula string
     * @param variables
     *        initial values for all variables
     * @param expectedResult
     *        the expected result
     */
    public VariableFormulasTest(String formulaString, Variable[] variables, int expectedResult) {

        this.formulaString = formulaString;
        this.variables = variables;
        this.expectedResult = expectedResult;
    }

    /**
     * The actual test method. The specified formula string is evaluated and the
     * result is compared with the specified result.
     */
    @Test
    public void testFormula() {

        Formula formula = getFormulaParser().parseString(formulaString);

        for (Variable variable : variables) {

            formula.setVariable(variable.getName(), variable);
        }

        int result = formula.calculate();

        assertEquals(formulaString, result, expectedResult);
    }

}

/**
 * A utility class which contains settings for variables.
 */
class Variable extends Number {

    /**
     * The name of this variable.
     */
    private final String name;

    /**
     * The initial value of this variable.
     */
    private final int value;

    /**
     * Constructs a variable entity.
     *
     * @param aName
     * @param aValue
     */
    public Variable(String aName, int aValue) {

        name = aName;
        value = aValue;
    }

    /**
     * A getter method.
     *
     * @return the initial value of this variable
     */
    public int intValue() {

        return getValue();
    }

    /**
     * A getter method.
     *
     * @return the initial value of this variable
     */
    public long longValue() {

        return getValue();
    }

    /**
     * A getter method.
     *
     * @return the initial value of this variable
     */
    public float floatValue() {

        return getValue();
    }

    /**
     * A getter method.
     *
     * @return the initial value of this variable
     */
    public double doubleValue() {

        return getValue();
    }

    /**
     * A getter method.
     *
     * @return the name of this variable
     */
    public String getName() {

        return name;
    }

    /**
     * A getter method.
     *
     * @return the initial value of this variable
     */
    public int getValue() {

        return value;
    }

    /**
     * Returns a string representation for this variable.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getName() + "=" + getValue();
    }

}
