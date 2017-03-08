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

package test.jmul.persistence.scenarios.scenario022;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import jmul.math.random.Dice;
import jmul.math.random.DiceImpl;
import jmul.math.random.DieImpl;

import jmul.misc.id.ID;

import jmul.persistence.PersistenceContainer;

import test.jmul.datatypes.scenarios.interfaces.Person;
import test.jmul.persistence.scenarios.scenario022.tasks.CreationTask;
import test.jmul.persistence.scenarios.scenario022.tasks.DeletionTask;
import test.jmul.persistence.scenarios.scenario022.tasks.ReadTask;
import test.jmul.persistence.scenarios.scenario022.tasks.Task;
import test.jmul.persistence.scenarios.scenario022.tasks.UpdateTask;


/**
 * This object manages various tasks within an object's lifecycle and beyond.
 *
 * @author Kristian Kutin
 */
public class ObjectLifecycle {

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
    private final PersistenceContainer<Person> container;

    /**
     * A result collector.
     */
    private final TaskResultCollector collector;

    /**
     * An intervall count.
     */
    private final int intervalCount;

    /**
     * The starting intervall for followup tasks.
     */
    private final int startingIntervallFollowupTasks;

    /**
     * An intervall duration.
     */
    private final int intervalDuration;

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
                           int anIntervalDuration) {

        super();

        container = aContainer;
        collector = aCollector;

        intervalCount = anIntervalCount;
        intervalDuration = anIntervalDuration;

        followupTasks = new ArrayList<Task>();

        Dice randomStartInterval = new DiceImpl(new DieImpl(anIntervalCount));

        int startingInterval = randomStartInterval.roll();
        initFirstTask(aContainer, aCollector, startingInterval, anIntervalDuration);

        startingIntervallFollowupTasks = startingInterval + 1;
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
                               int anIntervalDuration) {

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
                                   int aStartingInterval, int anIntervalCount, int anIntervalDuration) {

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
                                           int anInterval, int anIntervalDuration, boolean aSuccessFlag) {

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
                                           ID anID, int anInterval, int anIntervalDuration, boolean aSuccessFlag) {

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
                                       int anInterval, int anIntervalDuration, boolean aSuccessFlag) {

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
                                         ID anID, int anInterval, int anIntervalDuration, boolean aSuccessFlag) {

        return new UpdateTask(aContainer, aCollector, randomSleepTime(anInterval, anIntervalDuration), anID,
                              aSuccessFlag);
    }

    /**
     * Determines a random sleep time according to the specified parameters.
     *
     * @param anInterval
     * @param anIntervalDuration
     *
     * @return a sleep time
     */
    private static long randomSleepTime(int anInterval, int anIntervalDuration) {

        Dice dice = new DiceImpl(new DieImpl(anIntervalDuration));
        int result = ((anInterval - 1) * anIntervalDuration) + dice.roll();

        return result;
    }

    /**
     * Starts all threads within this object's lifecycle.
     */
    public void startLifecycle() {

        Thread t = new Thread(new LifecycleThread());
        t.start();
    }

    class LifecycleThread implements Runnable {

        @Override
        public void run() {

            try {

                Thread t = new Thread(firstTask);
                t.start();
                t.join();
                id = firstTask.getID();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }


            initFollowupTasks(container, collector, startingIntervallFollowupTasks, intervalCount, intervalDuration);


            for (Task task : followupTasks) {

                Thread t = new Thread(task);
                t.start();
            }
        }
    }

}
