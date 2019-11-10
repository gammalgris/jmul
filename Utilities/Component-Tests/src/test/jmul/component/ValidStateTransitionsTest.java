/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package test.jmul.component;


import java.util.ArrayList;
import java.util.Collection;

import static jmul.component.ComponentStates.FATAL_ERROR;
import static jmul.component.ComponentStates.INITIALIZATION;
import static jmul.component.ComponentStates.INITIALIZED;
import static jmul.component.ComponentStates.RECOVERABLE_ERROR;
import static jmul.component.ComponentStates.RUNNING;
import static jmul.component.ComponentStates.STARTING;
import static jmul.component.ComponentStates.STOPPED;
import static jmul.component.ComponentStates.STOPPING;
import static jmul.component.ComponentStates.UNINITIALIZED;

import jmul.misc.state.State;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class tests the correctness of state transitions.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ValidStateTransitionsTest {

    /**
     * Sets up the test environment.
     */
    @Before
    public void setUp() {

    }

    /**
     * Cleans up after the test.
     */
    @After
    public void tearDown() {

    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();


        parameters.add(new Object[] { UNINITIALIZED, INITIALIZATION });

        parameters.add(new Object[] { INITIALIZATION, INITIALIZED });
        parameters.add(new Object[] { INITIALIZATION, FATAL_ERROR });
        parameters.add(new Object[] { INITIALIZATION, RECOVERABLE_ERROR });

        parameters.add(new Object[] { INITIALIZED, STARTING });

        parameters.add(new Object[] { STARTING, RUNNING });
        parameters.add(new Object[] { STARTING, FATAL_ERROR });
        parameters.add(new Object[] { STARTING, RECOVERABLE_ERROR });

        parameters.add(new Object[] { RUNNING, STOPPING });
        parameters.add(new Object[] { RUNNING, FATAL_ERROR });
        parameters.add(new Object[] { RUNNING, RECOVERABLE_ERROR });

        parameters.add(new Object[] { STOPPING, STOPPED });
        parameters.add(new Object[] { STOPPING, FATAL_ERROR });
        parameters.add(new Object[] { STOPPING, RECOVERABLE_ERROR });

        parameters.add(new Object[] { RECOVERABLE_ERROR, INITIALIZATION });


        return parameters;
    }

    /**
     * Input data for a current state.
     */
    private State currentState;

    /**
     * Input data for a new state (i.e. transition target).
     */
    private State newState;

    /**
     * Creates a test according to the specified input data.
     *
     * @param currentState
     * @param aNewState
     */
    public ValidStateTransitionsTest(State aCurrentState, State aNewState) {

        currentState = aCurrentState;
        newState = aNewState;
    }

    /**
     * Test a state transition.
     */
    @Test
    public void testTransition() {

        State result = currentState.transitionTo(newState);
        assertNotNull(result);
    }

}
