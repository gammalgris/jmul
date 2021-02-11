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

package test.jmul.math.formula;


import java.util.ArrayList;
import java.util.List;

import jmul.checks.exceptions.NullParameterException;

import jmul.math.formula.VariableManager;
import jmul.math.formula.VariableManagerImpl;
import jmul.math.formula.parser.components.Variable;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This class tests various aspects of a variable manager.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class VariableManagerTest {

    /**
     * Tests the instantiation with no variables.
     */
    @Test
    public void testInstantiation() {

        String variableA = "a";
        List<Variable> components = new ArrayList<>();

        VariableManager variableManager = new VariableManagerImpl(components);

        assertTrue(variableManager.getAllVariableNames().isEmpty());
        assertTrue(variableManager.getUnresolvedVariableNames().isEmpty());

        assertEquals(false, variableManager.existUnresolvedVariables());
        assertEquals(false, variableManager.containsVariable(variableA));
    }

    /**
     * Tests the instantiation with an invalid parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testInstantiation2() {

        List<Variable> components = null;

        new VariableManagerImpl(components);
    }

    /**
     * Tests the instantiation with one variables.
     */
    @Test
    public void testInstantiation3() {

        String variableA = "a";
        String variableB = "b";

        Variable variable = new Variable(variableA);

        List<Variable> components = new ArrayList<>();
        components.add(variable);

        VariableManager variableManager = new VariableManagerImpl(components);

        assertFalse(variableManager.getAllVariableNames().isEmpty());
        assertFalse(variableManager.getUnresolvedVariableNames().isEmpty());

        assertEquals(1, variableManager.getAllVariableNames().size());

        assertEquals(true, variableManager.existUnresolvedVariables());
        assertEquals(true, variableManager.containsVariable(variableA));
        assertEquals(false, variableManager.containsVariable(variableB));
    }

    /**
     * Tests the instantiation with two variables (i.e. twice the same and same instance).
     */
    @Test
    public void testInstantiation4() {

        String variableA = "a";
        String variableB = "b";

        Variable variable = new Variable(variableA);

        List<Variable> components = new ArrayList<>();
        components.add(variable);
        components.add(variable);

        VariableManager variableManager = new VariableManagerImpl(components);

        assertFalse(variableManager.getAllVariableNames().isEmpty());
        assertFalse(variableManager.getUnresolvedVariableNames().isEmpty());

        assertEquals(1, variableManager.getAllVariableNames().size());

        assertEquals(true, variableManager.existUnresolvedVariables());
        assertEquals(true, variableManager.containsVariable(variableA));
        assertEquals(false, variableManager.containsVariable(variableB));
    }

    /**
     * Tests the instantiation with two variables (i.e. twice the same but different instances).
     */
    @Test
    public void testInstantiation5() {

        String variableA = "a";
        String variableB = "b";

        Variable variable;

        List<Variable> components = new ArrayList<>();

        variable = new Variable(variableA);
        components.add(variable);

        variable = new Variable(variableA);
        components.add(variable);

        VariableManager variableManager = new VariableManagerImpl(components);

        assertFalse(variableManager.getAllVariableNames().isEmpty());
        assertFalse(variableManager.getUnresolvedVariableNames().isEmpty());

        assertEquals(1, variableManager.getAllVariableNames().size());

        assertEquals(true, variableManager.existUnresolvedVariables());
        assertEquals(true, variableManager.containsVariable(variableA));
        assertEquals(false, variableManager.containsVariable(variableB));
    }

    /**
     * Tests the instantiation with two variables.
     */
    @Test
    public void testInstantiation6() {

        String variableA = "a";
        String variableB = "b";
        String variableC = "c";

        Variable variable1 = new Variable(variableA);
        Variable variable2 = new Variable(variableB);

        List<Variable> components = new ArrayList<>();
        components.add(variable1);
        components.add(variable2);

        VariableManager variableManager = new VariableManagerImpl(components);

        assertFalse(variableManager.getAllVariableNames().isEmpty());
        assertFalse(variableManager.getUnresolvedVariableNames().isEmpty());

        assertEquals(2, variableManager.getAllVariableNames().size());

        assertEquals(true, variableManager.existUnresolvedVariables());
        assertEquals(true, variableManager.containsVariable(variableA));
        assertEquals(true, variableManager.containsVariable(variableB));
        assertEquals(false, variableManager.containsVariable(variableC));
    }

    /**
     * Creates a variable manager according to the specified variable names.
     * 
     * @param someVariableNames
     *        some variable names
     * 
     * @return a variable manager
     */
    private static VariableManager newVariableList(String... someVariableNames ) {

        List<Variable> variableList = new ArrayList<>();

        for (String variableName : someVariableNames) {

            Variable variable = new Variable(variableName);
            variableList.add(variable);
        }

        return new VariableManagerImpl(variableList);
    }

    /**
     * Tests resolving a variable.
     */
    @Test
    public void testResolveVariable() {

        String variableA = "a";

        VariableManager variableManager = newVariableList(variableA);
        assertNotNull(variableManager);

        assertTrue(variableManager.existUnresolvedVariables());

        Number previous;
        Integer i1 = 1;
        previous = variableManager.setVariable(variableA, i1);
        assertEquals(null, previous);
        assertFalse(variableManager.isUnresolvedVariable(variableA));
        assertFalse(variableManager.existUnresolvedVariables());

        Integer i2 = 2;
        previous = variableManager.setVariable(variableA, i2);
        assertEquals(i1, previous);
        assertFalse(variableManager.isUnresolvedVariable(variableA));
        assertFalse(variableManager.existUnresolvedVariables());
    }

    /**
     * Tests resolving a variable.
     */
    @Test
    public void testResolveVariable2() {

        String variableA = "a";

        VariableManager variableManager = newVariableList(variableA);
        assertNotNull(variableManager);

        assertTrue(variableManager.existUnresolvedVariables());

        Number previous;
        Integer i1 = null;
        previous = variableManager.setVariable(variableA, i1);
        assertEquals(null, previous);
        assertTrue(variableManager.isUnresolvedVariable(variableA));
        assertTrue(variableManager.existUnresolvedVariables());
    }

    /**
     * Tests resolving a variable.
     */
    @Test
    public void testResolveVariable3() {

        String variableA = "a";
        String variableB = "b";

        VariableManager variableManager = newVariableList(variableA, variableB);
        assertNotNull(variableManager);

        assertTrue(variableManager.existUnresolvedVariables());

        Number previous;
        Integer i1 = 1;
        previous = variableManager.setVariable(variableA, i1);
        assertEquals(null, previous);
        assertFalse(variableManager.isUnresolvedVariable(variableA));
        assertTrue(variableManager.isUnresolvedVariable(variableB));
        assertTrue(variableManager.existUnresolvedVariables());

        Integer i2 = 2;
        previous = variableManager.setVariable(variableA, i2);
        assertEquals(i1, previous);
        assertFalse(variableManager.isUnresolvedVariable(variableA));
        assertTrue(variableManager.isUnresolvedVariable(variableB));
        assertTrue(variableManager.existUnresolvedVariables());
    }

}
