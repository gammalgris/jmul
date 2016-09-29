/*
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

package jmul.time;


/**
 * This class is an implementation of a stopwatch.
 *
 * @author Kristian Kutin
 */
public class Stopwatch {

    /**
     * A flag which indicates if the stopwatch is currently being used to
     * measure the elapsed time.
     */
    private boolean isMeasuringElapsedTime;

    /**
     * A flag which indicates that a measurement was completed.
     */
    private boolean hasCompletedMeasurement;

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
    public Stopwatch() {

        reset();
    }

    /**
     * The stopwatch is being reset. An ongoing measurement is aborted.
     */
    public final void reset() {

        startTime = 0L;
        stopTime = 0L;

        isMeasuringElapsedTime = false;
        hasCompletedMeasurement = false;
    }

    /**
     * Starts a new measurement. If there is an ongoing measurement
     * then invoking this method has no effect.
     */
    public void startCount() {

        if (isMeasuringElapsedTime) {

            return;
        }

        if (hasCompletedMeasurement) {

            reset();
        }

        isMeasuringElapsedTime = true;
        startTime = System.currentTimeMillis();
    }

    /**
     * Stops an ongoing measurement. If there is no ongoing measurement
     * then invoking this method has no effect.
     */
    public void stopCount() {

        if (hasCompletedMeasurement || !isMeasuringElapsedTime) {

            return;
        }

        stopTime = System.currentTimeMillis();
        isMeasuringElapsedTime = false;
        hasCompletedMeasurement = true;
    }

    /**
     * Returns the elapsed time of the last measurement.
     *
     * @return the elapsed time
     *
     * @throws StopwatchException
     *         is thrown if there is no finished measurement
     */
    public long getMeasuredTime() {

        if (!isMeasuringElapsedTime && !hasCompletedMeasurement) {

            String message = "There is no finished measurement!";
            throw new StopwatchException(message);

        } else if (isMeasuringElapsedTime && !hasCompletedMeasurement) {

            String message = "A measurement is still being made!";
            throw new StopwatchException(message);

        }

        return stopTime - startTime;
    }

    /**
     * Checks if the stopwatch is currntly being used.
     *
     * @return <code>true</code> if a measurement is taking place, else
     *         <code>false</code>
     */
    public boolean isActiveCount() {

        return isMeasuringElapsedTime;
    }

    /**
     * Returns a string representation of the stopwatch's current state.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        String message = null;

        try {

            long result = getMeasuredTime();
            message = "" + result + " ms";

        } catch (StopwatchException e) {

            message = e.getMessage();
        }

        return message;
    }

}
