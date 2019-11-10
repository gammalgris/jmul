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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.persistence.scenarios.scenario022.tasks;


import static jmul.math.bool.BooleanHelper.xor;

import jmul.misc.id.ID;

import jmul.persistence.InvalidIDException;
import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceException;

import test.jmul.datatypes.scenarios.interfaces.Person;
import test.jmul.persistence.scenarios.scenario022.TaskResult;
import test.jmul.persistence.scenarios.scenario022.TaskResultCollector;
import static test.jmul.persistence.scenarios.scenario022.tasks.TaskStates.ERROR;
import static test.jmul.persistence.scenarios.scenario022.tasks.TaskStates.RUNNING;
import static test.jmul.persistence.scenarios.scenario022.tasks.TaskStates.STOPPED;
import static test.jmul.persistence.scenarios.scenario022.tasks.TaskStates.STOPPING;


/**
 * An implementation of a task that deletes a specific data entry.
 *
 * @author Kristian Kutin
 */
public class DeletionTask extends TaskBase {

    /**
     * Creates a new task according to the specified parameters.
     *
     * @param aContainer
     * @param aCollector
     * @param aSleepTime
     * @param anID
     * @param anExpectedResult
     */
    public DeletionTask(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector, long aSleepTime,
                        ID anID, boolean anExpectedResult) {

        super(aContainer, aCollector, aSleepTime, anID, anExpectedResult);
    }

    /**
     * The actual deletion is implemented here.
     */
    @Override
    void performTask() {

        Throwable exception;
        ID id = getID();


        startCount();
        transitionTo(RUNNING);

        try {

            getContainer().delete(id);
            exception = null;
            transitionTo(STOPPING);

        } catch (PersistenceException e) {

            exception = e;

        } catch (InvalidIDException e) {

            exception = e;
        }

        stopCount();


        TaskResult result;
        String message = createMessage(id, exception);
        boolean actualResult = (exception == null);

        if (xor(isExpectedResult(), actualResult)) {

            result = TaskResult.createSuccessfulTaskReport(id, message, getMeasuredTime());
            transitionTo(STOPPED);

        } else {

            result = TaskResult.createFailedTaskReport(id, message, getMeasuredTime());
            transitionTo(ERROR);
        }
    }

    /**
     * Returns a message which describes what this task is doing.
     *
     * @param anID
     * @param anException
     *
     * @return a task description
     */
    private static String createMessage(ID anID, Throwable anException) {

        StringBuffer buffer = new StringBuffer();

        buffer.append("Trying to delete the data entry with the ID \"");
        buffer.append(anID);
        buffer.append("\"");

        if (anException == null) {

            return String.valueOf(buffer);
        }

        buffer.append(" (");
        buffer.append(anException.getClass());
        buffer.append("::");
        buffer.append(anException.getMessage());
        buffer.append(")");

        return String.valueOf(buffer);
    }

}
