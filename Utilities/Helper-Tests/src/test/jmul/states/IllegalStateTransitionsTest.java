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

package test.jmul.states;


import java.util.ArrayList;
import java.util.Collection;

import jmul.misc.state.State;

import jmul.test.classification.UnitTest;

import jmul.time.StopwatchStates;

import jmul.web.WebServerStates;

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
public class IllegalStateTransitionsTest {

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


        parameters.add(new Object[] { StopwatchStates.UNINITIALIZED, WebServerStates.UNINITIALIZED });
        parameters.add(new Object[] { StopwatchStates.UNINITIALIZED, WebServerStates.INITIALIZATION });
        parameters.add(new Object[] { StopwatchStates.UNINITIALIZED, WebServerStates.INITIALIZED });
        parameters.add(new Object[] { StopwatchStates.UNINITIALIZED, WebServerStates.STARTING });
        parameters.add(new Object[] { StopwatchStates.UNINITIALIZED, WebServerStates.RUNNING });
        parameters.add(new Object[] { StopwatchStates.UNINITIALIZED, WebServerStates.STOPPING });
        parameters.add(new Object[] { StopwatchStates.UNINITIALIZED, WebServerStates.STOPPED });
        parameters.add(new Object[] { StopwatchStates.UNINITIALIZED, WebServerStates.ERROR });

        parameters.add(new Object[] { StopwatchStates.INITIALIZATION, WebServerStates.UNINITIALIZED });
        parameters.add(new Object[] { StopwatchStates.INITIALIZATION, WebServerStates.INITIALIZATION });
        parameters.add(new Object[] { StopwatchStates.INITIALIZATION, WebServerStates.INITIALIZED });
        parameters.add(new Object[] { StopwatchStates.INITIALIZATION, WebServerStates.STARTING });
        parameters.add(new Object[] { StopwatchStates.INITIALIZATION, WebServerStates.RUNNING });
        parameters.add(new Object[] { StopwatchStates.INITIALIZATION, WebServerStates.STOPPING });
        parameters.add(new Object[] { StopwatchStates.INITIALIZATION, WebServerStates.STOPPED });
        parameters.add(new Object[] { StopwatchStates.INITIALIZATION, WebServerStates.ERROR });

        parameters.add(new Object[] { StopwatchStates.INITIALIZED, WebServerStates.UNINITIALIZED });
        parameters.add(new Object[] { StopwatchStates.INITIALIZED, WebServerStates.INITIALIZATION });
        parameters.add(new Object[] { StopwatchStates.INITIALIZED, WebServerStates.INITIALIZED });
        parameters.add(new Object[] { StopwatchStates.INITIALIZED, WebServerStates.STARTING });
        parameters.add(new Object[] { StopwatchStates.INITIALIZED, WebServerStates.RUNNING });
        parameters.add(new Object[] { StopwatchStates.INITIALIZED, WebServerStates.STOPPING });
        parameters.add(new Object[] { StopwatchStates.INITIALIZED, WebServerStates.STOPPED });
        parameters.add(new Object[] { StopwatchStates.INITIALIZED, WebServerStates.ERROR });

        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_IN_PROGRESS, WebServerStates.UNINITIALIZED });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_IN_PROGRESS, WebServerStates.INITIALIZATION });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_IN_PROGRESS, WebServerStates.INITIALIZED });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_IN_PROGRESS, WebServerStates.STARTING });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_IN_PROGRESS, WebServerStates.RUNNING });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_IN_PROGRESS, WebServerStates.STOPPING });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_IN_PROGRESS, WebServerStates.STOPPED });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_IN_PROGRESS, WebServerStates.ERROR });

        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_STOPPED, WebServerStates.UNINITIALIZED });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_STOPPED, WebServerStates.INITIALIZATION });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_STOPPED, WebServerStates.INITIALIZED });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_STOPPED, WebServerStates.STARTING });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_STOPPED, WebServerStates.RUNNING });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_STOPPED, WebServerStates.STOPPING });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_STOPPED, WebServerStates.STOPPED });
        parameters.add(new Object[] { StopwatchStates.MEASUREMENT_STOPPED, WebServerStates.ERROR });

        parameters.add(new Object[] { StopwatchStates.RESETTING, WebServerStates.UNINITIALIZED });
        parameters.add(new Object[] { StopwatchStates.RESETTING, WebServerStates.INITIALIZATION });
        parameters.add(new Object[] { StopwatchStates.RESETTING, WebServerStates.INITIALIZED });
        parameters.add(new Object[] { StopwatchStates.RESETTING, WebServerStates.STARTING });
        parameters.add(new Object[] { StopwatchStates.RESETTING, WebServerStates.RUNNING });
        parameters.add(new Object[] { StopwatchStates.RESETTING, WebServerStates.STOPPING });
        parameters.add(new Object[] { StopwatchStates.RESETTING, WebServerStates.STOPPED });
        parameters.add(new Object[] { StopwatchStates.RESETTING, WebServerStates.ERROR });

        parameters.add(new Object[] { StopwatchStates.RESET, WebServerStates.UNINITIALIZED });
        parameters.add(new Object[] { StopwatchStates.RESET, WebServerStates.INITIALIZATION });
        parameters.add(new Object[] { StopwatchStates.RESET, WebServerStates.INITIALIZED });
        parameters.add(new Object[] { StopwatchStates.RESET, WebServerStates.STARTING });
        parameters.add(new Object[] { StopwatchStates.RESET, WebServerStates.RUNNING });
        parameters.add(new Object[] { StopwatchStates.RESET, WebServerStates.STOPPING });
        parameters.add(new Object[] { StopwatchStates.RESET, WebServerStates.STOPPED });
        parameters.add(new Object[] { StopwatchStates.RESET, WebServerStates.ERROR });

        parameters.add(new Object[] { StopwatchStates.ERROR, WebServerStates.UNINITIALIZED });
        parameters.add(new Object[] { StopwatchStates.ERROR, WebServerStates.INITIALIZATION });
        parameters.add(new Object[] { StopwatchStates.ERROR, WebServerStates.INITIALIZED });
        parameters.add(new Object[] { StopwatchStates.ERROR, WebServerStates.STARTING });
        parameters.add(new Object[] { StopwatchStates.ERROR, WebServerStates.RUNNING });
        parameters.add(new Object[] { StopwatchStates.ERROR, WebServerStates.STOPPING });
        parameters.add(new Object[] { StopwatchStates.ERROR, WebServerStates.STOPPED });
        parameters.add(new Object[] { StopwatchStates.ERROR, WebServerStates.ERROR });


        parameters.add(new Object[] { WebServerStates.UNINITIALIZED, StopwatchStates.UNINITIALIZED });
        parameters.add(new Object[] { WebServerStates.UNINITIALIZED, StopwatchStates.INITIALIZATION });
        parameters.add(new Object[] { WebServerStates.UNINITIALIZED, StopwatchStates.INITIALIZED });
        parameters.add(new Object[] { WebServerStates.UNINITIALIZED, StopwatchStates.MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { WebServerStates.UNINITIALIZED, StopwatchStates.MEASUREMENT_STOPPED });
        parameters.add(new Object[] { WebServerStates.UNINITIALIZED, StopwatchStates.RESETTING });
        parameters.add(new Object[] { WebServerStates.UNINITIALIZED, StopwatchStates.RESET });
        parameters.add(new Object[] { WebServerStates.UNINITIALIZED, StopwatchStates.ERROR });

        parameters.add(new Object[] { WebServerStates.INITIALIZATION, StopwatchStates.UNINITIALIZED });
        parameters.add(new Object[] { WebServerStates.INITIALIZATION, StopwatchStates.INITIALIZATION });
        parameters.add(new Object[] { WebServerStates.INITIALIZATION, StopwatchStates.INITIALIZED });
        parameters.add(new Object[] { WebServerStates.INITIALIZATION, StopwatchStates.MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { WebServerStates.INITIALIZATION, StopwatchStates.MEASUREMENT_STOPPED });
        parameters.add(new Object[] { WebServerStates.INITIALIZATION, StopwatchStates.RESETTING });
        parameters.add(new Object[] { WebServerStates.INITIALIZATION, StopwatchStates.RESET });
        parameters.add(new Object[] { WebServerStates.INITIALIZATION, StopwatchStates.ERROR });

        parameters.add(new Object[] { WebServerStates.INITIALIZED, StopwatchStates.UNINITIALIZED });
        parameters.add(new Object[] { WebServerStates.INITIALIZED, StopwatchStates.INITIALIZATION });
        parameters.add(new Object[] { WebServerStates.INITIALIZED, StopwatchStates.INITIALIZED });
        parameters.add(new Object[] { WebServerStates.INITIALIZED, StopwatchStates.MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { WebServerStates.INITIALIZED, StopwatchStates.MEASUREMENT_STOPPED });
        parameters.add(new Object[] { WebServerStates.INITIALIZED, StopwatchStates.RESETTING });
        parameters.add(new Object[] { WebServerStates.INITIALIZED, StopwatchStates.RESET });
        parameters.add(new Object[] { WebServerStates.INITIALIZED, StopwatchStates.ERROR });

        parameters.add(new Object[] { WebServerStates.STARTING, StopwatchStates.UNINITIALIZED });
        parameters.add(new Object[] { WebServerStates.STARTING, StopwatchStates.INITIALIZATION });
        parameters.add(new Object[] { WebServerStates.STARTING, StopwatchStates.INITIALIZED });
        parameters.add(new Object[] { WebServerStates.STARTING, StopwatchStates.MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { WebServerStates.STARTING, StopwatchStates.MEASUREMENT_STOPPED });
        parameters.add(new Object[] { WebServerStates.STARTING, StopwatchStates.RESETTING });
        parameters.add(new Object[] { WebServerStates.STARTING, StopwatchStates.RESET });
        parameters.add(new Object[] { WebServerStates.STARTING, StopwatchStates.ERROR });

        parameters.add(new Object[] { WebServerStates.RUNNING, StopwatchStates.UNINITIALIZED });
        parameters.add(new Object[] { WebServerStates.RUNNING, StopwatchStates.INITIALIZATION });
        parameters.add(new Object[] { WebServerStates.RUNNING, StopwatchStates.INITIALIZED });
        parameters.add(new Object[] { WebServerStates.RUNNING, StopwatchStates.MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { WebServerStates.RUNNING, StopwatchStates.MEASUREMENT_STOPPED });
        parameters.add(new Object[] { WebServerStates.RUNNING, StopwatchStates.RESETTING });
        parameters.add(new Object[] { WebServerStates.RUNNING, StopwatchStates.RESET });
        parameters.add(new Object[] { WebServerStates.RUNNING, StopwatchStates.ERROR });

        parameters.add(new Object[] { WebServerStates.STOPPING, StopwatchStates.UNINITIALIZED });
        parameters.add(new Object[] { WebServerStates.STOPPING, StopwatchStates.INITIALIZATION });
        parameters.add(new Object[] { WebServerStates.STOPPING, StopwatchStates.INITIALIZED });
        parameters.add(new Object[] { WebServerStates.STOPPING, StopwatchStates.MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { WebServerStates.STOPPING, StopwatchStates.MEASUREMENT_STOPPED });
        parameters.add(new Object[] { WebServerStates.STOPPING, StopwatchStates.RESETTING });
        parameters.add(new Object[] { WebServerStates.STOPPING, StopwatchStates.RESET });
        parameters.add(new Object[] { WebServerStates.STOPPING, StopwatchStates.ERROR });

        parameters.add(new Object[] { WebServerStates.STOPPED, StopwatchStates.UNINITIALIZED });
        parameters.add(new Object[] { WebServerStates.STOPPED, StopwatchStates.INITIALIZATION });
        parameters.add(new Object[] { WebServerStates.STOPPED, StopwatchStates.INITIALIZED });
        parameters.add(new Object[] { WebServerStates.STOPPED, StopwatchStates.MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { WebServerStates.STOPPED, StopwatchStates.MEASUREMENT_STOPPED });
        parameters.add(new Object[] { WebServerStates.STOPPED, StopwatchStates.RESETTING });
        parameters.add(new Object[] { WebServerStates.STOPPED, StopwatchStates.RESET });
        parameters.add(new Object[] { WebServerStates.STOPPED, StopwatchStates.ERROR });

        parameters.add(new Object[] { WebServerStates.ERROR, StopwatchStates.UNINITIALIZED });
        parameters.add(new Object[] { WebServerStates.ERROR, StopwatchStates.INITIALIZATION });
        parameters.add(new Object[] { WebServerStates.ERROR, StopwatchStates.INITIALIZED });
        parameters.add(new Object[] { WebServerStates.ERROR, StopwatchStates.MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { WebServerStates.ERROR, StopwatchStates.MEASUREMENT_STOPPED });
        parameters.add(new Object[] { WebServerStates.ERROR, StopwatchStates.RESETTING });
        parameters.add(new Object[] { WebServerStates.ERROR, StopwatchStates.RESET });
        parameters.add(new Object[] { WebServerStates.ERROR, StopwatchStates.ERROR });


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
    public IllegalStateTransitionsTest(State aCurrentState, State aNewState) {

        currentState = aCurrentState;
        newState = aNewState;
    }

    /**
     * Test a state transition.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTransition() {

        currentState.transitionTo(newState);
    }

}
