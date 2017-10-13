/*
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

package jmul.time;


import jmul.misc.state.State;

import static jmul.time.StopwatchStates.ERROR;
import static jmul.time.StopwatchStates.INITIALIZATION;
import static jmul.time.StopwatchStates.INITIALIZED;
import static jmul.time.StopwatchStates.MEASUREMENT_IN_PROGRESS;
import static jmul.time.StopwatchStates.MEASUREMENT_STOPPED;
import static jmul.time.StopwatchStates.RESET;
import static jmul.time.StopwatchStates.RESETTING;
import static jmul.time.StopwatchStates.UNINITIALIZED;


/**
 * An implementation of a stopwatch.
 *
 * @author Kristian Kutin
 */
public class StopwatchImpl implements Stopwatch {

    /**
     * Initial values for a start and stop time.
     */
    private static final long INITIAL_VALUE = 0L;

    /**
     * The state of the stopwatch.
     */
    private State state;

    /**
     * The start time.
     */
    private long startTime;

    /**
     * The stop time.
     */
    private long stopTime;

    /**
     * The default constructor.
     */
    public StopwatchImpl() {

        state = UNINITIALIZED;

        state = state.transitionTo(INITIALIZATION);

        startTime = INITIAL_VALUE;
        stopTime = INITIAL_VALUE;

        state = state.transitionTo(INITIALIZED);
    }

    /**
     * Returns the stopwatch's current state.
     *
     * @return a state
     */
    @Override
    public State getStopwatchState() {

        return state;
    }

    /**
     * Creates an exception according to the specified parameters..
     *
     * @param anOperationName
     * @param aState
     *
     * @return an exception
     */
    private static RuntimeException createIllegalStateException(String anOperationName, State aState) {

        String message =
            "The operation " + anOperationName + " (current state: " + aState + ") has reached an illegal state!";
        return new IllegalStateException(message);
    }

    /**
     * The stopwatch is being reset. An ongoing measurement is aborted.
     */
    @Override
    public void reset() {

        if (state.equals(INITIALIZED) || state.equals(MEASUREMENT_STOPPED) || state.equals(ERROR)) {

            state = state.transitionTo(RESETTING);

            startTime = INITIAL_VALUE;
            stopTime = INITIAL_VALUE;

            state = state.transitionTo(RESET);

        } else if (state.equals(MEASUREMENT_IN_PROGRESS)) {

            String message = "Cannot reset a stopwatch while a measurement is in progress!";
            throw new StopwatchException(message);

        } else {

            throw createIllegalStateException("reset()", getStopwatchState());
        }
    }

    /**
     * Starts a new measurement. If there is an ongoing measurement
     * then invoking this method has no effect.
     */
    @Override
    public void startMeasurement() {

        if (state.equals(INITIALIZED) || state.equals(RESET)) {

            state = state.transitionTo(MEASUREMENT_IN_PROGRESS);
            startTime = System.currentTimeMillis();

        } else if (state.equals(MEASUREMENT_IN_PROGRESS)) {

            String message = "Cannot start a measurement while a measurement is already in progress!";
            throw new StopwatchException(message);

        } else if (state.equals(ERROR)) {

            String message = "Reset the stopwatch first before starting a new measurement!";
            throw new StopwatchException(message);

        } else {

            throw createIllegalStateException("startMeasurement()", getStopwatchState());
        }
    }

    /**
     * Stops an ongoing measurement. If there is no ongoing measurement
     * then invoking this method has no effect.
     */
    @Override
    public void stopMeasurement() {

        if (state.equals(MEASUREMENT_IN_PROGRESS)) {

            stopTime = System.currentTimeMillis();
            state = state.transitionTo(MEASUREMENT_STOPPED);

            if (stopTime < startTime) {

                state = state.transitionTo(ERROR);
                String message = "There has been an overflow while the measurement was in progress!";
                throw new StopwatchException(message);
            }

        } else if (state.equals(INITIALIZED) || state.equals(ERROR) || state.equals(MEASUREMENT_STOPPED)) {

            String message = "There is no measurement in progress!";
            throw new StopwatchException(message);

        } else {

            throw createIllegalStateException("stopMeasurement()", getStopwatchState());
        }
    }

    /**
     * Returns the elapsed time of the last measurement.
     *
     * @return the elapsed time
     *
     * @throws StopwatchException
     *         is thrown if there is no finished measurement
     */
    @Override
    public long getMeasuredTime() {

        if (state.equals(INITIALIZED)) {

            String message = "No measurement has been performed yet!";
            throw new StopwatchException(message);

        } else if (state.equals(MEASUREMENT_IN_PROGRESS)) {

            String message =
                "There is a measurement already in progress! Stop it first before asking for the duration.";
            throw new StopwatchException(message);

        } else if (state.equals(MEASUREMENT_STOPPED)) {

            return stopTime - startTime;

        } else if (state.equals(ERROR)) {

            String message = "The stopwatch stopped with an error. No times are available.";
            throw new StopwatchException(message);

        } else if (state.equals(RESET)) {

            String message = "The stopwatch was resetted. No times are available.";
            throw new StopwatchException(message);

        } else {

            throw createIllegalStateException("getMeasuredTime()", getStopwatchState());
        }
    }

    /**
     * Returns a string representation of the stopwatch's current state.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        if (state.equals(INITIALIZED)) {

            return "No measurement has been performed yet!";

        } else if (state.equals(MEASUREMENT_IN_PROGRESS)) {

            return "There is a measurement already in progress! Stop it first before asking for the duration.";

        } else if (state.equals(MEASUREMENT_STOPPED)) {

            return "" + getMeasuredTime() + " ms";

        } else if (state.equals(ERROR)) {

            return "The stopwatch stopped with an error. No times are available.";

        } else {

            throw createIllegalStateException("toString()", getStopwatchState());
        }
    }

}
