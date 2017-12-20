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

package jmul.math.formula;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * The class manages known variables.
 *
 * @author Kristian Kutin
 */
class VariableManager {

    /**
     * The class member contains all variables.
     */
    Map<String, Variable> variables;

    /**
     * The default constructor.
     */
    protected VariableManager() {

        variables = new HashMap<>();
    }

    /**
     * The method adds a variable.
     *
     * @param aVariable
     *        a variable
     */
    public void addVariable(Variable aVariable) {

        variables.put(aVariable.getLabel(), aVariable);
    }

    /**
     * The method returns a variable.
     *
     * @param aVariableName
     *        a variable name
     *
     * @return a variable
     */
    public Variable getVariable(String aVariableName) {

        return variables.get(aVariableName);
    }

    /**
     * The method returns the names of all known variables.
     *
     * @return the names of all known variables
     */
    public Collection<String> getKnownVariableNames() {

        return variables.keySet();
    }

}
