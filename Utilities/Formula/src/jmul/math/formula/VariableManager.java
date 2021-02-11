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


import java.util.Set;


/**
 * This interface describes an entity which manages all variables that appear within a formula.
 * Every variable can occur one or more times within a formula.
 *
 * @author Kristian Kutin
 */
public interface VariableManager {

    /**
     * Returns the names of all variables found within the formula.
     *
     * @return all variable names
     */
    Set<String> getAllVariableNames();

    /**
     * Returns the names of all variables for which no value was specified.
     *
     * @return all unresolved variable names
     */
    Set<String> getUnresolvedVariableNames();

    /**
     * Checks if the specified variable name is found within the formula.
     *
     * @param aVariableName
     *        the name of a variable
     *
     * @return <code>true</code> if the specified variable name is found within the formula,
     *         else <code>false</code>
     */
    boolean containsVariable(String aVariableName);

    /**
     * Returns the value or reference assigned to the specified variable.
     *
     * @param aVariableName
     *        the name of a variable
     *
     * @return the value or reference or <code>null</code> if no value or reference
     *         was assigned
     */
    Number getVariable(String aVariableName);

    /**
     * Assigns a value to the specified variable.
     *
     * @param aVariableName
     *        the name of a variable
     * @param aReference
     *        a value or a reference to an entity which provides a value
     *
     * @return the previous value or reference or <code>null</code> if no value or reference
     *         was assigned
     */
    Number setVariable(String aVariableName, Number aReference);

    /**
     * Checks if there are unresolved variables.
     *
     * @return <code>true</code> if there are unresolved variables, else <code>false</code>
     */
    boolean existUnresolvedVariables();

    /**
     * Checks if the specified variable is unresolved (i.e. has no value or reference assigned to it).
     *
     * @param aVariableName
     *        a variable name
     *
     * @return <code>true</code> if the variable is unresolved, else <code>false</code>
     */
    boolean isUnresolvedVariable(String aVariableName);

}
