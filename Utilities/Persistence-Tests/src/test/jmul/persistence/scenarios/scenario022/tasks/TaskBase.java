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


import jmul.concurrent.threads.ThreadHelper;

import jmul.misc.id.ID;
import jmul.misc.state.State;

import jmul.persistence.PersistenceContainer;

import jmul.time.Stopwatch;
import jmul.time.StopwatchImpl;

import test.jmul.datatypes.scenarios.interfaces.Person;
import test.jmul.persistence.scenarios.scenario022.TaskResult;
import test.jmul.persistence.scenarios.scenario022.TaskResultCollector;
import static test.jmul.persistence.scenarios.scenario022.tasks.TaskStates.INITIALIZATION;
import static test.jmul.persistence.scenarios.scenario022.tasks.TaskStates.INITIALIZED;
import static test.jmul.persistence.scenarios.scenario022.tasks.TaskStates.STARTING;
import static test.jmul.persistence.scenarios.scenario022.tasks.TaskStates.UNINITIALIZED;


/**
 * A base implementation of a future task.
 *
 * @author Kristian Kutin
 */
abstract class TaskBase implements Task {

    /**
     * A reference to a persistence cotnainer.
     */
    private final PersistenceContainer<Person> container;

    /**
     * A reference to an entity which collects task results.
     */
    private final TaskResultCollector collector;

    /**
     * A sleep time after which the actual processing is done.
     */
    private final long sleepTime;

    /**
     * The ID of a data entry in the persistence container.
     */
    private final ID id;

    /**
     * The expected result of the processing.
     */
    private final boolean expectedResult;

    /**
     * A stopwatch to measure the actual execution time per task.
     */
    private final Stopwatch stopwatch;

    /**
     * The state of the task.
     */
    private volatile State state;

    /**
     * Creates a new task according to the specified parameters.
     *
     * @param aContainer
     * @param aCollector
     * @param aClerk
     * @param aSleepTime
     * @param anID
     */
    public TaskBase(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector, long aSleepTime, ID anID,
                    boolean anExpectedResult) {

        state = UNINITIALIZED;

        transitionTo(INITIALIZATION);
        container = aContainer;
        collector = aCollector;
        sleepTime = aSleepTime;
        id = anID;
        expectedResult = anExpectedResult;
        stopwatch = new StopwatchImpl();
        transitionTo(INITIALIZED);
    }

    /**
     * A base implementation of the run method.
     */
    @Override
    public void run() {

        transitionTo(STARTING);

        ThreadHelper.sleep(sleepTime);

        performTask();
    }

    /**
     * In a derived class the actual computing is implemented in this method.
     */
    abstract void performTask();

    /**
     * A getter method.
     *
     * @return a reference to a persistence container
     */
    public PersistenceContainer<Person> getContainer() {

        return container;
    }

    /**
     * A getter method.
     *
     * @return a reference to a task collector
     */
    public TaskResultCollector getCollector() {

        return collector;
    }

    /**
     * A getter method.
     *
     * @return a sleep time
     */
    @Override
    public long getSleepTime() {

        return sleepTime;
    }

    /**
     * A getter method.
     *
     * @return an ID
     */
    @Override
    public ID getID() {

        return id;
    }

    /**
     * A getter method.
     *
     * @return the expected result
     */
    @Override
    public boolean isExpectedResult() {

        return expectedResult;
    }

    /**
     * Reports the specified task result to a collector.
     *
     * @param aResult
     */
    protected void reportResult(TaskResult aResult) {

        collector.addResult(aResult);
    }

    /**
     * Returns the actual duration of the task.
     *
     * @return a duration
     */
    @Override
    public long getMeasuredTime() {

        return stopwatch.getMeasuredTime();
    }

    /**
     * Starts measuring the task duration.
     */
    protected void startCount() {

        stopwatch.startMeasurement();
    }

    /**
     * Stops measuring the task duration.
     */
    protected void stopCount() {

        stopwatch.stopMeasurement();
    }

    /**
     * Returns the state of the task.
     *
     * @return a state
     */
    public State getState() {

        State currentState;

        synchronized (this) {

            currentState = state;
        }

        return currentState;
    }

    /**
     * Changes the state of this task to the specified new state.
     *
     * @param newState
     */
    protected void transitionTo(State newState) {

        synchronized (this) {

            state = state.transitionTo(newState);

        }
    }

}
