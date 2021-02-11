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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jmul.checks.ParameterCheckHelper;

import jmul.math.formula.parser.components.Variable;


/**
 * An implementation of a variable manager.
 *
 * @author Kristian Kutin
 */
public class VariableManagerImpl implements VariableManager {

    /**
     * The initial value for variables.
     */
    private static final Number INITIAL_VALUE;

    /**
     * Contains references to all variables within the formula (i.e. component tree).
     */
    private final Map<String, List<Variable>> variables;

    /**
     * Contains all variable names and the values or references assigned to them.
     */
    private final Map<String, Number> values;

    /*
     * The static initializer.
     */
    static {

        INITIAL_VALUE = null;
    }

    /**
     * Creates a new variable manager according to the specified parameters.
     *
     * @param allVariableReferences
     *        all variable occurrences within the formula (i.e. component tree)
     */
    public VariableManagerImpl(Collection<Variable> allVariableReferences) {

        super();

        ParameterCheckHelper.checkObjectParameter(allVariableReferences);

        Map<String, List<Variable>> tmp = new HashMap<>();

        for (Variable variable : allVariableReferences) {

            String variableName = variable.getVariableName();

            List<Variable> occurrences;
            if (tmp.containsKey(variableName)) {

                occurrences = tmp.get(variableName);

            } else {

                occurrences = new ArrayList<>();
                tmp.put(variableName, occurrences);
            }

            occurrences.add(variable);
        }

        variables = Collections.unmodifiableMap(tmp);


        Map<String, Number> tmp2 = new HashMap<>();

        for (String variableName : tmp.keySet()) {

            tmp2.put(variableName, INITIAL_VALUE);
        }

        values = tmp2;
    }

    /**
     * Returns the names of all variables found within the formula.
     *
     * @return all variable names
     */
    @Override
    public Set<String> getAllVariableNames() {

        return variables.keySet();
    }

    /**
     * Returns the names of all variables for which no value was specified.
     *
     * @return all unresolved variable names
     */
    @Override
    public Set<String> getUnresolvedVariableNames() {

        Set<String> unresolvedVariables = new HashSet<>();

        for (String variableName : values.keySet()) {

            if (isUnresolvedVariable(variableName)) {

                unresolvedVariables.add(variableName);
            }
        }

        return unresolvedVariables;
    }

    /**
     * Checks if the specified variable name is found within the formula.
     *
     * @param aVariableName
     *        the name of a variable
     *
     * @return <code>true</code> if the specified variable name is found within the formula,
     *         else <code>false</code>
     */
    @Override
    public boolean containsVariable(String aVariableName) {

        return values.containsKey(aVariableName);
    }

    /**
     * Returns the value or reference assigned to the specified variable.
     *
     * @param aVariableName
     *        the name of a variable
     *
     * @return the value or reference or <code>null</code> if no value or reference
     *         was assigned
     */
    @Override
    public Number getVariable(String aVariableName) {

        return values.get(aVariableName);
    }

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
    @Override
    public Number setVariable(String aVariableName, Number aReference) {

        Number previousValue = values.get(aVariableName);

        for (Variable variable : variables.get(aVariableName)) {

            variable.setReference(aReference);
        }

        values.put(aVariableName, aReference);

        return previousValue;
    }

    /**
     * Checks if there are unresolved variables.
     *
     * @return <code>true</code> if there are unresolved variables, else <code>false</code>
     */
    @Override
    public boolean existUnresolvedVariables() {

        if (values.isEmpty()) {

            return false;
        }

        boolean unresolved = false;

        for (String variableName : values.keySet()) {

            boolean tmp = isUnresolvedVariable(variableName);
            unresolved = unresolved || tmp;
        }

        return unresolved;
    }

    /**
     * Checks if the specified variable is unresolved (i.e. has no value or reference assigned to it).
     *
     * @param aVariableName
     *        a variable name
     *
     * @return <code>true</code> if the variable is unresolved, else <code>false</code>
     */
    @Override
    public boolean isUnresolvedVariable(String aVariableName) {

        return values.get(aVariableName) == null;
    }

}
