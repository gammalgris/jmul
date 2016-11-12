/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package test.jmul.persistence;


import static jmul.concurrent.threads.ThreadHelper.sleep;

import static jmul.math.Constants.SECOND;

import jmul.persistence.InvalidIDException;
import jmul.persistence.InvalidRootNodeException;
import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceContainerImpl;
import jmul.persistence.PersistenceException;
import jmul.persistence.id.ID;

import jmul.test.classification.ModuleTest;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.jmul.datatypes.scenarios.scenario001.Person;


/**
 * This class contains tests to check the correct behaviour of a
 * persistence container.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class PersistenceLifecycleTest extends PersistenceTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ".\\Test\\Persistence\\Lifecycle";

    /**
     * Preparations before this test suite.
     */
    @BeforeClass
    public static void setUp() {

        initBaseDirectory(BASEDIR);
    }

    /**
     * Cleanup after this test suite.
     */
    @AfterClass
    public static void tearDown() {

    }

    /**
     * Preparations before a test.
     */
    @Before
    public void setUpTest() {

    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDownTest() {

    }

    /**
     * The test will use different operations provided by a persistence container
     * which will be used at one time or another within a typical object's lifecycle.
     */
    @Test
    public void testLifecycle1() {

        PersistenceContainer<Person> container = new PersistenceContainerImpl<Person>(Person.class, BASEDIR);

        Person person = newPerson("John", "Doe", "1.1.2000", "male");
        Person personCopy = null;

        ID currentID;
        ID newID;


        // 1. persist and retrieve an object.

        try {

            currentID = container.store(person);

            sleep(SECOND);

            personCopy = container.get(currentID);

        } catch (PersistenceException e) {

            throw new RuntimeException(e);

        } catch (InvalidRootNodeException e) {

            throw new RuntimeException(e);
        }

        comparePersons(person, personCopy);


        // 2. commit changes and retrieve an object

        String newFirstName = person.getFirstName() + " F.";
        person.setFirstName(newFirstName);

        try {

            newID = container.commit(person);

            sleep(SECOND);

            personCopy = container.get(newID);

        } catch (PersistenceException e) {

            throw new RuntimeException(e);

        } catch (InvalidRootNodeException e) {

            throw new RuntimeException(e);
        }

        comparePersons(person, personCopy);
        compareIDs(currentID, newID);


        // 3. commit changes and retrieve an object

        person.setBirthDate("01.01.1900");

        try {

            sleep(SECOND);

            newID = container.commit(currentID, person);

            sleep(SECOND);

            personCopy = container.get(newID);

        } catch (PersistenceException e) {

            throw new RuntimeException(e);

        } catch (InvalidRootNodeException e) {

            throw new RuntimeException(e);
        }

        comparePersons(person, personCopy);
        compareIDs(currentID, newID);


        // 4. delete an object

        try {

            sleep(SECOND);

            container.delete(currentID);

            sleep(SECOND);

            personCopy = container.get(newID);

        } catch (PersistenceException e) {

            throw new RuntimeException(e);

        } catch (InvalidIDException e) {

        }

        container.shutdown();
        System.out.println("DEBUG:: shutdown done");
    }

    /**
     * The test will use different operations provided by a persistence container
     * which will be used at one time or another within a typical object's lifecycle.
     */
    @Test
    public void testLifecycle2() {

        PersistenceContainer<Person> container = new PersistenceContainerImpl<Person>(Person.class, BASEDIR);

        Person person = newPerson("John", "Doe", "1.1.2000", "male");
        Person personCopy = null;

        ID currentID;
        ID newID;


        // 1. persist and retrieve an object.

        try {

            currentID = container.store(person);

            waitForEmptyCache();

            personCopy = container.get(currentID);

        } catch (PersistenceException e) {

            throw new RuntimeException(e);

        } catch (InvalidRootNodeException e) {

            throw new RuntimeException(e);
        }

        comparePersons(person, personCopy);
        person = personCopy;


        // 2. commit changes and retrieve an object

        String newFirstName = person.getFirstName() + " F.";
        person.setFirstName(newFirstName);

        try {

            newID = container.commit(person);

            waitForEmptyCache();

            personCopy = container.get(newID);

        } catch (PersistenceException e) {

            throw new RuntimeException(e);

        } catch (InvalidRootNodeException e) {

            throw new RuntimeException(e);
        }

        comparePersons(person, personCopy);
        compareIDs(currentID, newID);
        person = personCopy;


        // 3. commit changes and retrieve an object

        person.setBirthDate("01.01.1900");

        try {

            waitForEmptyCache();

            newID = container.commit(currentID, person);

            waitForEmptyCache();

            personCopy = container.get(newID);

        } catch (PersistenceException e) {

            throw new RuntimeException(e);

        } catch (InvalidRootNodeException e) {

            throw new RuntimeException(e);
        }

        comparePersons(person, personCopy);
        compareIDs(currentID, newID);


        // 4. delete an object

        try {

            waitForEmptyCache();

            container.delete(currentID);

            waitForEmptyCache();

        } catch (PersistenceException e) {

            throw new RuntimeException(e);

        } catch (InvalidIDException e) {

            throw new RuntimeException(e);
        }

        try {

            personCopy = container.get(newID);
            fail("The object was not deleted!");

        } catch (PersistenceException e) {

        }

        container.shutdown();
    }

    /**
     * Creates a new person according to the specified parameters.
     *
     * @param aFirstName
     * @param aLastName
     * @param aBirthDate
     * @param aGender
     *
     * @return a new person
     */
    private static Person newPerson(String aFirstName, String aLastName, String aBirthDate, String aGender) {

        Person p = new Person();
        p.setFirstName(aFirstName);
        p.setLastName(aLastName);
        p.setBirthDate(aBirthDate);
        p.setGender(aGender);

        return p;
    }

    /**
     * Compares two persons via assertions.
     *
     * @param p1
     * @param p2
     */
    private static void comparePersons(Person p1, Person p2) {

        assertEquals("The persons' first names don't match!", p1.getFirstName(), p2.getFirstName());
        assertEquals("The persons' last names don't match!", p1.getLastName(), p2.getLastName());
        assertEquals("The persons' birthdates don't match!", p1.getBirthDate(), p2.getBirthDate());
        assertEquals("The persons' genders don't match!", p1.getGender(), p2.getGender());
    }

    /**
     * Compares two IDs via assertions.
     *
     * @param id1
     * @param id2
     */
    private static void compareIDs(ID id1, ID id2) {

        assertEquals("The IDs don't match!", id1, id2);
    }

}
