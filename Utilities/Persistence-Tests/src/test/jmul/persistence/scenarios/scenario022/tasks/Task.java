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

package test.jmul.persistence.scenarios.scenario022.tasks;


import jmul.misc.id.ID;
import jmul.misc.state.State;


/**
 * This interface describes a general task.
 *
 * @author Kristian Kutin
 */
public interface Task extends Runnable {

    /**
     * A getter method.
     *
     * @return a sleep time
     */
    long getSleepTime();

    /**
     * A getter method.
     *
     * @return an ID
     */
    ID getID();

    /**
     * A getter method.
     *
     * @return the expected result
     */
    boolean isExpectedResult();

    /**
     * Returns the actual duration of the task.
     *
     * @return a duration
     */
    long getMeasuredTime();

    /**
     * Returns the state of the task.
     *
     * @return a state
     */
    State getState();

}
