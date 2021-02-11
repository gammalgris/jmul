/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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


import jmul.math.formula.parser.components.Component;
import jmul.math.graph.Tree;


/**
 * An implementation of a formula.
 *
 * @author Kristian Kutin
 */
public class FormulaImpl implements Formula {

    /**
     * The original formula string.
     */
    private final String formula;

    /**
     * The component tree.
     */
    private final Tree<Component> componentTree;

    /**
     * The variable manager.
     */
    private final VariableManager variableManager;

    /**
     * Creates a new formula entity
     *
     * @param aFormula
     *        a string cotnaining the original formula
     * @param aComponentTree
     *        a component tree derived from the formula
     * @param aVariableManager
     *        a variable manager
     */
    public FormulaImpl(String aFormula, Tree<Component> aComponentTree, VariableManager aVariableManager) {

        super();

        formula = aFormula;
        componentTree = aComponentTree;
        variableManager = aVariableManager;
    }

    /**
     * The method calculates a result for the formula. If a formula
     * contains variables these variables have to be resolved, i.e.
     * values should be provided for all of them.
     *
     * @return a result
     */
    @Override
    public int calculate() {

        Component root = componentTree.getRoot();

        if (root == null) {

            return 0;

        } else {

            return root.calculate();
        }
    }

    /**
     * The method allows to change a variable at runtime.
     *
     * @param aVariableName
     *        the name of a variable
     * @param aReference
     *        the object which contains the variable value
     */
    @Override
    public void setVariable(String aVariableName, Number aReference) {

        variableManager.setVariable(aVariableName, aReference);
    }

    /**
     * Returns a string representation of the formula entity.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return formula;
    }

}
