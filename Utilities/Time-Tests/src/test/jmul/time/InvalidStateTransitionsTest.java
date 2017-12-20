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

package test.jmul.time;


import java.util.ArrayList;
import java.util.Collection;

import jmul.misc.state.IllegalStateTransitionException;
import jmul.misc.state.State;

import jmul.test.classification.UnitTest;

import static jmul.time.StopwatchStates.ERROR;
import static jmul.time.StopwatchStates.INITIALIZATION;
import static jmul.time.StopwatchStates.INITIALIZED;
import static jmul.time.StopwatchStates.MEASUREMENT_IN_PROGRESS;
import static jmul.time.StopwatchStates.MEASUREMENT_STOPPED;
import static jmul.time.StopwatchStates.RESET;
import static jmul.time.StopwatchStates.RESETTING;
import static jmul.time.StopwatchStates.UNINITIALIZED;

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
        parameters.add(new Object[] { UNINITIALIZED, MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { UNINITIALIZED, MEASUREMENT_STOPPED });
        parameters.add(new Object[] { UNINITIALIZED, RESETTING });
        parameters.add(new Object[] { UNINITIALIZED, RESET });
        parameters.add(new Object[] { UNINITIALIZED, ERROR });

        parameters.add(new Object[] { INITIALIZATION, UNINITIALIZED });
        parameters.add(new Object[] { INITIALIZATION, INITIALIZATION });
        parameters.add(new Object[] { INITIALIZATION, MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { INITIALIZATION, MEASUREMENT_STOPPED });
        parameters.add(new Object[] { INITIALIZATION, RESETTING });
        parameters.add(new Object[] { INITIALIZATION, RESET });

        parameters.add(new Object[] { INITIALIZED, UNINITIALIZED });
        parameters.add(new Object[] { INITIALIZED, INITIALIZATION });
        parameters.add(new Object[] { INITIALIZED, INITIALIZED });
        parameters.add(new Object[] { INITIALIZED, MEASUREMENT_STOPPED });
        parameters.add(new Object[] { INITIALIZED, RESET });
        parameters.add(new Object[] { INITIALIZED, ERROR });

        parameters.add(new Object[] { MEASUREMENT_IN_PROGRESS, UNINITIALIZED });
        parameters.add(new Object[] { MEASUREMENT_IN_PROGRESS, INITIALIZATION });
        parameters.add(new Object[] { MEASUREMENT_IN_PROGRESS, INITIALIZED });
        parameters.add(new Object[] { MEASUREMENT_IN_PROGRESS, MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { MEASUREMENT_IN_PROGRESS, RESETTING });
        parameters.add(new Object[] { MEASUREMENT_IN_PROGRESS, RESET });

        parameters.add(new Object[] { MEASUREMENT_STOPPED, UNINITIALIZED });
        parameters.add(new Object[] { MEASUREMENT_STOPPED, INITIALIZATION });
        parameters.add(new Object[] { MEASUREMENT_STOPPED, INITIALIZED });
        parameters.add(new Object[] { MEASUREMENT_STOPPED, MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { MEASUREMENT_STOPPED, MEASUREMENT_STOPPED });
        parameters.add(new Object[] { MEASUREMENT_STOPPED, RESET });
        parameters.add(new Object[] { MEASUREMENT_STOPPED, ERROR });

        parameters.add(new Object[] { RESETTING, UNINITIALIZED });
        parameters.add(new Object[] { RESETTING, INITIALIZATION });
        parameters.add(new Object[] { RESETTING, INITIALIZED });
        parameters.add(new Object[] { RESETTING, MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { RESETTING, MEASUREMENT_STOPPED });
        parameters.add(new Object[] { RESETTING, RESETTING });

        parameters.add(new Object[] { RESET, UNINITIALIZED });
        parameters.add(new Object[] { RESET, INITIALIZATION });
        parameters.add(new Object[] { RESET, INITIALIZED });
        parameters.add(new Object[] { RESET, MEASUREMENT_STOPPED });
        parameters.add(new Object[] { RESET, RESET });
        parameters.add(new Object[] { RESET, ERROR });

        parameters.add(new Object[] { ERROR, UNINITIALIZED });
        parameters.add(new Object[] { ERROR, INITIALIZATION });
        parameters.add(new Object[] { ERROR, INITIALIZED });
        parameters.add(new Object[] { ERROR, MEASUREMENT_IN_PROGRESS });
        parameters.add(new Object[] { ERROR, MEASUREMENT_STOPPED });
        parameters.add(new Object[] { ERROR, RESET });
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
