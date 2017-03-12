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
import java.util.List;

import jmul.concurrent.threads.ThreadHelper;

import static jmul.math.Constants.SECOND;

import jmul.persistence.PersistenceContainer;

import test.jmul.datatypes.scenarios.interfaces.Person;
import static test.jmul.persistence.scenarios.scenario022.LifecycleStates.FINISHED_FOLLOWUP_TASKS;


/**
 * Creates a test setup for szenario 22.
 *
 * @author Kristian Kutin
 */
public class TestSetup {

    /**
     * A default interval length.
     */
    private static final int DEFAULT_INTERVAL_LENGTH = 1000;

    /**
     * An interval count which corresponds to the a specified test duration.
     */
    private final int intervalCount;

    /**
     * All object lifecycles of the test setup.
     */
    private List<ObjectLifecycle> lifecycles;

    /**
     * Creates a new test setup according to the specified parmaeters.
     * @param aContainer
     * @param aCollector
     * @param aTestDuration
     * @param aMaxObjectsCount
     */
    public TestSetup(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector, long aTestDuration,
                     int aMaxObjectsCount) {

        super();

        lifecycles = new ArrayList<ObjectLifecycle>();

        intervalCount = (int) (aTestDuration / DEFAULT_INTERVAL_LENGTH);

        for (int a = 1; a <= aMaxObjectsCount; a++) {

            ObjectLifecycle lifecycle =
                new ObjectLifecycle(aContainer, aCollector, intervalCount, DEFAULT_INTERVAL_LENGTH);

            lifecycles.add(lifecycle);
        }
    }

    /**
     * Starts the actual test (concurrent read write operations on a persistence container).
     */
    public void startTest() {

        for (ObjectLifecycle lifecycle : lifecycles) {

            lifecycle.startLifecycle();
        }


        long sleepTime = SECOND * 30;

        boolean stop = false;
        while (!stop) {

            ThreadHelper.sleep(sleepTime);

            stop = true;
            for (ObjectLifecycle lifecycle : lifecycles) {

                stop = stop && (FINISHED_FOLLOWUP_TASKS == lifecycle.getLifecycleState());
            }
        }
    }

}
