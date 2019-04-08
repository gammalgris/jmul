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


import jmul.concurrent.threads.ThreadHelper;

import static jmul.math.Constants.HOUR;
import static jmul.math.Constants.MINUTE;
import static jmul.math.Constants.SECOND;
import static jmul.math.Constants.getEpsilon;

import jmul.misc.state.State;

import jmul.test.classification.UnitTest;

import jmul.time.Stopwatch;
import jmul.time.StopwatchException;
import jmul.time.StopwatchImpl;
import static jmul.time.StopwatchStates.INITIALIZED;
import static jmul.time.StopwatchStates.MEASUREMENT_IN_PROGRESS;
import static jmul.time.StopwatchStates.MEASUREMENT_STOPPED;
import static jmul.time.StopwatchStates.RESET;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


/**
 * This class tests various aspects of a stopwatch.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class StopwatchTest {

    /**
     * A stopwatch instance.
     */
    private Stopwatch stopwatch;

    /**
     * Sets up the test environment.
     */
    @Before
    public void setUp() {

        stopwatch = new StopwatchImpl();
    }

    /**
     * Cleans up after the test.
     */
    @After
    public void tearDown() {

        stopwatch = null;
    }

    /**
     * A utility method which checks a state.
     *
     * @param anExpectedState
     * @param anActualState
     */
    private static void checkState(State anExpectedState, State anActualState) {

        String message =
            "expected state=" + anExpectedState.getStateName() + "; actual state=" + anActualState.getStateName();
        assertEquals(message, anExpectedState, anActualState);
    }

    /**
     * A utility method which fails a test because the code should have thrown an exception earlier.
     *
     * @param anExceptionClass
     */
    private static void failMissingException(Class anExceptionClass) {

        String message = "An exception of type " + anExceptionClass.getCanonicalName() + " is expected but not thrown!";
        fail(message);
    }

    /**
     * Tests if the initialization works as expected.
     */
    @Test
    public void testInitialization() {

        State actualState = stopwatch.getStopwatchState();
        State expectedState = INITIALIZED;

        checkState(expectedState, actualState);
    }

    /**
     * Tests resetting a newly initialized stopwatch. This action is allowed and the stopwatch
     * will be resetted and the state changes accordingly.
     */
    @Test
    public void testResetScenario01() {

        stopwatch.reset();

        State actualState = stopwatch.getStopwatchState();
        State expectedState = RESET;

        checkState(expectedState, actualState);
    }

    /**
     * Tests resetting a stopwatch when a measurement is in progress.
     */
    @Test
    public void testResetScenario02() {

        stopwatch.startMeasurement();

        try {

            stopwatch.reset();
            failMissingException(StopwatchException.class);

        } catch (StopwatchException e) {

            State actualState = stopwatch.getStopwatchState();
            State expectedState = MEASUREMENT_IN_PROGRESS;

            checkState(expectedState, actualState);
        }
    }

    /**
     * Tests resetting a stopwatch when a measurement has finished.
     */
    @Test
    public void testResetScenario03() {

        stopwatch.startMeasurement();
        ThreadHelper.sleep(SECOND);
        stopwatch.stopMeasurement();

        stopwatch.reset();

        {
            State actualState = stopwatch.getStopwatchState();
            State expectedState = RESET;

            checkState(expectedState, actualState);
        }

        try {

            stopwatch.getMeasuredTime();
            failMissingException(StopwatchException.class);

        } catch (StopwatchException e) {

            State actualState = stopwatch.getStopwatchState();
            State expectedState = RESET;

            checkState(expectedState, actualState);
        }
    }

    /**
     * Tests performing a successful measurement.
     */
    @Test
    public void testMeasurement() {

        stopwatch.startMeasurement();
        ThreadHelper.sleep(SECOND);
        stopwatch.stopMeasurement();


        State actualState = stopwatch.getStopwatchState();
        State expectedState = MEASUREMENT_STOPPED;

        checkState(expectedState, actualState);


        long result = stopwatch.getMeasuredTime();

        assertTrue("The result has to be positive!", result > 0L);
        long delta1 = result - SECOND;
        float delta2 = ((float) delta1) / (SECOND * MINUTE * HOUR);
        assertTrue("", delta2 < getEpsilon(Float.TYPE).floatValue());
    }

}
