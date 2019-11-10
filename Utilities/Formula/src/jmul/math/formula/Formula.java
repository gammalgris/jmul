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

package jmul.math.formula;


import jmul.math.formula.parser.FormulaParser;
import jmul.math.formula.parser.FormulaParserImpl;


/**
 * An implementation for a generic formula.
 *
 * @author Kristian Kutin
 */
public class Formula {

    /**
     * The class member references a parser.
     */
    private static FormulaParser parser;

    /**
     * The class member manages all known variables. Each formula has its own
     * variable manager.
     */
    private VariableManager variableManager;

    /**
     * The class member contains an operand.
     */
    private Operand operand;

    /**
     * The default constructor.
     */
    public Formula() {

        variableManager = new VariableManager();
    }

    /**
     * The method return the variable manager of this formula.
     *
     * @return a variable manager
     */
    protected VariableManager getVariableManager() {

        return variableManager;
    }

    /**
     * The method calculates a result for the formula. The result depends on
     * the current values of the variables and evetnually some random values.
     *
     * @return a result for the formula
     */
    public int calculate() {

        return operand.getValue();
    }

    /**
     * The method allows to change a variable at any time.
     *
     * @param aLabel
     *        the name of a variable
     * @param aReference
     *        the object which contains the variable value
     */
    public void setVariable(String aLabel, Object aReference) {

        Variable variable = variableManager.getVariable(aLabel);

        if (variable == null) {

            String message = "The formula (\"" + this + "\") doesn't have a variable with the name \"" + aLabel + "\"!";
            throw new IllegalArgumentException(message);
        }

        variable.setVariableReference(aReference);
    }

    /**
     * The method sets the operand. Every formula consist of only one operand.
     *
     * @param anOperand
     *        an operand
     */
    public void setOperand(Operand anOperand) {

        operand = anOperand;
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return operand.toString();
    }

    /**
     * The method parses a string and instantiates a formula. Variables are not
     * initialised and have to be set up accordingly. <br>
     * This is a convenience method which uses a default parser to parse
     * strings. If instead another parser is to be used then calling the
     * parseString method of the desired parser is recommended.
     *
     * @param aString
     *        a string
     *
     * @return a formula
     */
    public static Formula parseString(String aString) {

        if (parser == null) {

            parser = new FormulaParserImpl();
        }

        return parser.parseString(aString);
    }

}
