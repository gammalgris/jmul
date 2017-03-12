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

package test.jmul.persistence.scenarios.scenario022.tasks;


import static jmul.math.bool.BooleanHelper.xor;

import jmul.misc.id.ID;

import jmul.persistence.InvalidRootNodeException;
import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceException;

import test.jmul.datatypes.scenarios.interfaces.Person;
import test.jmul.datatypes.scenarios.scenario022.PersonImpl;
import static test.jmul.persistence.scenarios.scenario022.RandomizationHelper.randomBirthday;
import static test.jmul.persistence.scenarios.scenario022.RandomizationHelper.randomFirstName;
import static test.jmul.persistence.scenarios.scenario022.RandomizationHelper.randomGender;
import static test.jmul.persistence.scenarios.scenario022.RandomizationHelper.randomLastName;
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
public class CreationTask extends TaskBase {

    /**
     * The ID of a new data entry in the persistence container.
     */
    private ID newID;

    /**
     * Creates a new task according to the specified parameters.
     *
     * @param aContainer
     * @param aCollector
     * @param aSleepTime
     * @param anExpectedResult
     */
    public CreationTask(PersistenceContainer<Person> aContainer, TaskResultCollector aCollector, long aSleepTime,
                        boolean anExpectedResult) {

        super(aContainer, aCollector, aSleepTime, null, anExpectedResult);
    }

    /**
     * The actual deletion is implemented here.
     */
    @Override
    void performTask() {

        Throwable exception;


        startCount();
        transitionTo(RUNNING);

        try {

            Person person = newPerson();
            ID personID = getContainer().store(person);
            setID(personID);
            exception = null;
            transitionTo(STOPPING);

        } catch (PersistenceException e) {

            exception = e;

        } catch (InvalidRootNodeException e) {

            exception = e;
        }

        stopCount();


        TaskResult result;
        String message = createMessage(getID(), exception);
        boolean actualResult = (exception == null);

        if (xor(isExpectedResult(), actualResult)) {

            result = TaskResult.createSuccessfulTaskReport(getID(), message, getMeasuredTime());
            transitionTo(STOPPED);

        } else {

            result = TaskResult.createFailedTaskReport(getID(), message, getMeasuredTime());
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

        buffer.append("Trying to create a new data entry with the ID \"");
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

    /**
     * A getter method.
     *
     * @return an ID
     */
    @Override
    public ID getID() {

        return newID;
    }

    /**
     * A setter method.
     *
     * @param anID
     */
    public void setID(ID anID) {

        newID = anID;
    }

    /**
     * Returns a new person with randomly generated attributes.
     *
     * @return a new person
     */
    private Person newPerson() {

        Person person = new PersonImpl();

        person.setFirstName(randomFirstName());
        person.setLastName(randomLastName());
        person.setGender(randomGender());
        person.setBirthDate(randomBirthday());

        return person;
    }

}
