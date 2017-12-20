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

package test.jmul.web;


import java.util.ArrayList;
import java.util.Collection;

import jmul.misc.state.IllegalStateTransitionException;
import jmul.misc.state.State;

import jmul.test.classification.UnitTest;

import static jmul.web.WebServerStates.ERROR;
import static jmul.web.WebServerStates.INITIALIZATION;
import static jmul.web.WebServerStates.INITIALIZED;
import static jmul.web.WebServerStates.RUNNING;
import static jmul.web.WebServerStates.STARTING;
import static jmul.web.WebServerStates.STOPPED;
import static jmul.web.WebServerStates.STOPPING;
import static jmul.web.WebServerStates.UNINITIALIZED;

import org.junit.After;
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
public class InvalidStateTransitionsTest {

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


        parameters.add(new Object[] { UNINITIALIZED, UNINITIALIZED });
        parameters.add(new Object[] { UNINITIALIZED, INITIALIZED });
        parameters.add(new Object[] { UNINITIALIZED, STARTING });
        parameters.add(new Object[] { UNINITIALIZED, RUNNING });
        parameters.add(new Object[] { UNINITIALIZED, STOPPING });
        parameters.add(new Object[] { UNINITIALIZED, STOPPED });
        parameters.add(new Object[] { UNINITIALIZED, ERROR });

        parameters.add(new Object[] { INITIALIZATION, UNINITIALIZED });
        parameters.add(new Object[] { INITIALIZATION, INITIALIZATION });
        parameters.add(new Object[] { INITIALIZATION, STARTING });
        parameters.add(new Object[] { INITIALIZATION, RUNNING });
        parameters.add(new Object[] { INITIALIZATION, STOPPING });
        parameters.add(new Object[] { INITIALIZATION, STOPPED });

        parameters.add(new Object[] { INITIALIZED, UNINITIALIZED });
        parameters.add(new Object[] { INITIALIZED, INITIALIZATION });
        parameters.add(new Object[] { INITIALIZED, INITIALIZED });
        parameters.add(new Object[] { INITIALIZED, RUNNING });
        parameters.add(new Object[] { INITIALIZED, STOPPING });
        parameters.add(new Object[] { INITIALIZED, STOPPED });
        parameters.add(new Object[] { INITIALIZED, ERROR });

        parameters.add(new Object[] { STARTING, UNINITIALIZED });
        parameters.add(new Object[] { STARTING, INITIALIZATION });
        parameters.add(new Object[] { STARTING, INITIALIZED });
        parameters.add(new Object[] { STARTING, STARTING });
        parameters.add(new Object[] { STARTING, STOPPING });
        parameters.add(new Object[] { STARTING, STOPPED });

        parameters.add(new Object[] { RUNNING, UNINITIALIZED });
        parameters.add(new Object[] { RUNNING, INITIALIZATION });
        parameters.add(new Object[] { RUNNING, INITIALIZED });
        parameters.add(new Object[] { RUNNING, STARTING });
        parameters.add(new Object[] { RUNNING, RUNNING });
        parameters.add(new Object[] { RUNNING, STOPPED });

        parameters.add(new Object[] { STOPPING, UNINITIALIZED });
        parameters.add(new Object[] { STOPPING, INITIALIZATION });
        parameters.add(new Object[] { STOPPING, INITIALIZED });
        parameters.add(new Object[] { STOPPING, STARTING });
        parameters.add(new Object[] { STOPPING, RUNNING });
        parameters.add(new Object[] { STOPPING, STOPPING });

        parameters.add(new Object[] { STOPPED, UNINITIALIZED });
        parameters.add(new Object[] { STOPPED, INITIALIZATION });
        parameters.add(new Object[] { STOPPED, INITIALIZED });
        parameters.add(new Object[] { STOPPED, STARTING });
        parameters.add(new Object[] { STOPPED, RUNNING });
        parameters.add(new Object[] { STOPPED, STOPPING });
        parameters.add(new Object[] { STOPPED, STOPPED });
        parameters.add(new Object[] { STOPPED, ERROR });

        parameters.add(new Object[] { ERROR, UNINITIALIZED });
        parameters.add(new Object[] { ERROR, INITIALIZATION });
        parameters.add(new Object[] { ERROR, INITIALIZED });
        parameters.add(new Object[] { ERROR, STARTING });
        parameters.add(new Object[] { ERROR, RUNNING });
        parameters.add(new Object[] { ERROR, STOPPING });
        parameters.add(new Object[] { ERROR, STOPPED });
        parameters.add(new Object[] { ERROR, ERROR });


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
    public InvalidStateTransitionsTest(State aCurrentState, State aNewState) {

        currentState = aCurrentState;
        newState = aNewState;
    }

    /**
     * Test a state transition.
     */
    @Test(expected = IllegalStateTransitionException.class)
    public void testTransition() {

        currentState.transitionTo(newState);
    }

}
