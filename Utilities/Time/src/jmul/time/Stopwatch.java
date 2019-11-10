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

package jmul.time;


import jmul.misc.state.State;


/**
 * This interface describes a stopwatch entity.
 *
 * @author Kristian Kutin
 */
public interface Stopwatch {

    /**
     * Returns the stopwatch's current state.
     *
     * @return a state
     */
    State getStopwatchState();

    /**
     * The stopwatch is being reset. An ongoing measurement is aborted.
     */
    void reset();

    /**
     * Starts a new measurement. If there is an ongoing measurement
     * then invoking this method has no effect.
     */
    void startMeasurement();

    /**
     * Stops an ongoing measurement. If there is no ongoing measurement
     * then invoking this method has no effect.
     */
    void stopMeasurement();

    /**
     * Returns the elapsed time of the last measurement.
     *
     * @return the elapsed time
     */
    long getMeasuredTime();

}
