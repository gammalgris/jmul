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

package test.jmul.persistence.scenarios.scenario022;


import java.util.ArrayList;
import java.util.List;

import jmul.concurrent.threads.ThreadHelper;

import static jmul.math.Constants.SECOND;
import jmul.math.random.Dice;
import jmul.math.random.DiceImpl;
import jmul.math.random.DieImpl;

import jmul.misc.id.ID;
import jmul.misc.state.State;

import jmul.persistence.PersistenceContainer;

import test.jmul.datatypes.scenarios.interfaces.Person;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.EXECUTING_FIRST_TASK;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.EXECUTING_FOLLOWUP_TASKS;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.FINISHED_FIRST_TASK;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.FINISHED_FOLLOWUP_TASKS;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.INITIALIZED_FIRST_TASK;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.INITIALIZED_FOLLOWUP_TASKS;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.INITIALIZING_FIRST_TASK;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.INITIALIZING_FOLLOWUP_TASKS;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.STARTING_FIRST_TASK;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.STARTING_FOLLOWUP_TASKS;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.UNINITIALIZED;
import test.jmul.persistence.scenarios.scenario022.tasks.CreationTask;
import test.jmul.persistence.scenarios.scenario022.tasks.DeletionTask;
import test.jmul.persistence.scenarios.scenario022.tasks.ReadTask;
import test.jmul.persistence.scenarios.scenario022.tasks.Task;
import static test.jmul.persistence.scenarios.scenario022.tasks.TaskStates.ERROR;
import static test.jmul.persistence.scenarios.scenario022.tasks.TaskStates.STOPPED;
import test.jmul.persistence.scenarios.scenario022.tasks.UpdateTask;


/**
 * This object manages various tasks within an object's lifecycle and beyond.
 *
 * @author Kristian Kutin
 */
public class ObjectLifecycle {

    /**
     * A prefix for a thread name.
     */
    private static final String TASK_PREFIX = "task.";

    /**
     * A prefix for a thread name.
     */
    private static final String LIFECYCLE_PREFIX = "lifecycle.";

    /**
     * A numeric value representing a task type.
     */
    private static final int READ_TASK = 1;

    /**
     * A numeric value representing a task type.
     */
    private static final int UPDATE_TASK = 2;

    /**
     * A numeric value representing a task type.
     */
    private static final int DELETE_TASK = 3;

    /**
     * The state of the lifecycle.
     */
    private volatile State lifecycleState;

    /**
     * The ID of an object (only available after the creation task).
     */
    private ID id;

    /**
     * The first task (i.e. a creation task).
     */
    private Task firstTask;

    /**
     * All followup tasks regarding a specific object.
     */
    private List<Task> followupTasks;

    /**
     * A persistence container.
     */
    private volatile PersistenceContainer<Person> container;

    /**
     * A result collector.
     */
    private volatile TaskResultCollector collector;

    /**
     * An intervall count.
     */
    private final int intervalCount;

    /**
     * The starting intervall for followup tasks.
     */
    private final int startingIntervallFollowupTasks;

    /**
     * An intervall duration (in ms).
     */
    private final long intervalDuration;

    /**
     * Creates a set of tasks which are performed during the lifecycle of a specific
     * object.
     *
     * @param aContainer
     * @param aCollector
     * @param anIntervalCount
     * @param anIntervalDuration
     */
    public ObjectLifecycle(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector, int anIntervalCount,
                           long anIntervalDuration) {

        super();

        lifecycleState = UNINITIALIZED;

        transitionTo(INITIALIZING_FIRST_TASK);

        container = aContainer;
        collector = aCollector;

        intervalCount = anIntervalCount;
        intervalDuration = anIntervalDuration;

        followupTasks = new ArrayList<Task>();

        Dice randomStartInterval = new DiceImpl(new DieImpl(anIntervalCount));

        int startingInterval = randomStartInterval.roll();
        initFirstTask(aContainer, aCollector, startingInterval, anIntervalDuration);

        startingIntervallFollowupTasks = startingInterval + 1;

        transitionTo(INITIALIZED_FIRST_TASK);
    }

    /**
     * Initializes the first task according to the specified parameters.
     *
     * @param aContainer
     * @param aCollector
     * @param anInterval
     * @param anIntervalDuration
     */
    private void initFirstTask(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector, int anInterval,
                               long anIntervalDuration) {

        firstTask = createCreationTask(aContainer, aCollector, anInterval, anIntervalDuration, true);
    }

    /**
     * Initializes all followup tasks according to the specified parameters.
     *
     * @param aContainer
     * @param aCollector
     * @param aStartingInterval
     * @param anIntervalCount
     * @param anIntervalDuration
     */
    private void initFollowupTasks(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector,
                                   int aStartingInterval, int anIntervalCount, long anIntervalDuration) {

        Dice randomTask = new DiceImpl(new DieImpl(3));

        int delta = anIntervalCount - aStartingInterval;
        boolean successFlag = true;
        for (int a = 1; a <= delta; a++) {

            int taskType = randomTask.roll();
            if (taskType == READ_TASK) {

                Task task = createReadTask(aContainer, aCollector, id, a, anIntervalDuration, successFlag);
                followupTasks.add(task);

            } else if (taskType == UPDATE_TASK) {

                Task task = createUpdateTask(aContainer, aCollector, id, a, anIntervalDuration, successFlag);
                followupTasks.add(task);

            } else if (taskType == DELETE_TASK) {

                Task task = createDeletionTask(aContainer, aCollector, id, a, anIntervalDuration, successFlag);
                followupTasks.add(task);
                successFlag = false;
            }
        }
    }

    /**
     * Returns a creation task.
     *
     * @param aContainer
     * @param aCollector
     * @param anInterval
     * @param anIntervalDuration
     * @param aSuccessFlag
     *
     * @return a creation task
     */
    private static Task createCreationTask(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector,
                                           int anInterval, long anIntervalDuration, boolean aSuccessFlag) {

        return new CreationTask(aContainer, aCollector, randomSleepTime(anInterval, anIntervalDuration), aSuccessFlag);
    }

    /**
     * Returns a deletion task.
     *
     * @param aContainer
     * @param aCollector
     * @param anID
     * @param anInterval
     * @param anIntervalDuration
     * @param aSuccessFlag
     *
     * @return a creation task
     */
    private static Task createDeletionTask(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector,
                                           ID anID, int anInterval, long anIntervalDuration, boolean aSuccessFlag) {

        return new DeletionTask(aContainer, aCollector, randomSleepTime(anInterval, anIntervalDuration), anID,
                                aSuccessFlag);
    }

    /**
     * Returns a read task.
     *
     * @param aContainer
     * @param aCollector
     * @param anID
     * @param anInterval
     * @param anIntervalDuration
     * @param aSuccessFlag
     *
     * @return a creation task
     */
    private static Task createReadTask(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector, ID anID,
                                       int anInterval, long anIntervalDuration, boolean aSuccessFlag) {

        return new ReadTask(aContainer, aCollector, randomSleepTime(anInterval, anIntervalDuration), anID,
                            aSuccessFlag);
    }

    /**
     * Returns an update task.
     *
     * @param aContainer
     * @param aCollector
     * @param anID
     * @param anInterval
     * @param anIntervalDuration
     * @param aSuccessFlag
     *
     * @return a creation task
     */
    private static Task createUpdateTask(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector,
                                         ID anID, int anInterval, long anIntervalDuration, boolean aSuccessFlag) {

        return new UpdateTask(aContainer, aCollector, randomSleepTime(anInterval, anIntervalDuration), anID,
                              aSuccessFlag);
    }

    /**
     * Determines a random sleep time according to the specified parameters.
     *
     * @param anInterval
     * @param anIntervalDuration (in ms)
     *
     * @return a sleep time
     */
    private static long randomSleepTime(int anInterval, long anIntervalDuration) {

        Dice dice = new DiceImpl(new DieImpl((int) anIntervalDuration));
        long result = ((anInterval - 1) * anIntervalDuration) + dice.roll();

        return result;
    }

    /**
     * Starts all threads within this object's lifecycle.
     */
    public void startLifecycle() {

        Thread t = new Thread(new LifecycleThread());
        t.setName(LIFECYCLE_PREFIX + t.getName());
        t.start();

        ThreadHelper.sleep(SECOND);
    }

    /**
     * Checks if the followup tasks have finished.
     *
     * @return <code>true</code> if the followup tasks have finished,
     *         else <code>false</code>
     */
    private boolean areFinishedFollowupTasks() {

        if (FINISHED_FOLLOWUP_TASKS == getLifecycleState()) {

            return true;
        }

        if (EXECUTING_FOLLOWUP_TASKS == getLifecycleState()) {

            for (Task task : followupTasks) {

                if ((STOPPED == task.getState()) || (ERROR == task.getState())) {

                    continue;

                } else {

                    return false;
                }
            }

            return true;
        }

        return false;
    }

    /**
     * Returns the state of the task.
     *
     * @return a state
     */
    public State getLifecycleState() {

        State currentState;

        synchronized (this) {

            currentState = lifecycleState;
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

            lifecycleState = lifecycleState.transitionTo(newState);

        }
    }


    /**
     * This inner class takes care of starting all tasks regarding a specific object.
     */
    class LifecycleThread implements Runnable {

        /**
         * Creates an object and starts all followup tasks.
         */
        @Override
        public void run() {

            transitionTo(STARTING_FIRST_TASK);

            try {

                Thread t = new Thread(firstTask);
                t.setName(TASK_PREFIX + t.getName());
                t.start();

                transitionTo(EXECUTING_FIRST_TASK);
                t.join();
                id = firstTask.getID();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }

            transitionTo(FINISHED_FIRST_TASK);


            ThreadHelper.sleep(SECOND);


            transitionTo(INITIALIZING_FOLLOWUP_TASKS);

            initFollowupTasks(container, collector, startingIntervallFollowupTasks, intervalCount, intervalDuration);

            transitionTo(INITIALIZED_FOLLOWUP_TASKS);


            transitionTo(STARTING_FOLLOWUP_TASKS);

            for (Task task : followupTasks) {

                Thread t = new Thread(task);
                t.setName(TASK_PREFIX + t.getName());
                t.start();
            }

            transitionTo(EXECUTING_FOLLOWUP_TASKS);

            ThreadHelper.sleep(SECOND);


            while (!areFinishedFollowupTasks()) {

                ThreadHelper.sleep(SECOND);
            }

            transitionTo(FINISHED_FOLLOWUP_TASKS);
        }
    }

}
