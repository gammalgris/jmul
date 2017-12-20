/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

package test.jmul.persistence.scenarios.scenario018;


import jmul.misc.id.ID;

import jmul.persistence.InvalidRootNodeException;
import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceContainerImpl;
import jmul.persistence.PersistenceException;

import jmul.test.classification.ModuleTest;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.jmul.datatypes.scenarios.scenario001.Person;
import test.jmul.persistence.PersistenceTestBase;


/**
 * This class contains tests to check the serialization of objects.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class Scenario018PersistenceTest extends PersistenceTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ".\\Test\\Serialization\\Scenario-018";

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
     * Tests committing changes to an object.
     */
    @Test
    public void testCommitChanges() {

        PersistenceContainer<Person> container = new PersistenceContainerImpl<Person>(Person.class, BASEDIR);

        Person person = newPerson("John", "Doe", "1.1.2000", "male");
        Person personCopy = null;

        try {

            ID id = container.store(person);
            personCopy = container.get(id);
            comparePersons(person, personCopy);

            person.setFirstName("John F.");
            ID id2 = container.commit(person);
            compareIDs(id, id2);

            personCopy = container.get(id);
            comparePersons(person, personCopy);

        } catch (PersistenceException e) {

            fail(e.getMessage());

        } catch (InvalidRootNodeException e) {

            fail(e.getMessage());
        }

        container.shutdown();
    }

    /**
     * Tests committing changes to an object.
     */
    @Test
    public void testCommitChanges2() {

        PersistenceContainer<Person> container = new PersistenceContainerImpl<Person>(Person.class, BASEDIR);

        Person person = newPerson("John", "Doe", "1.1.2000", "male");
        Person personCopy = null;

        try {

            ID id = container.store(person);
            personCopy = container.get(id);
            comparePersons(person, personCopy);

            person.setFirstName("John F.");
            ID id2 = container.commit(person);
            compareIDs(id, id2);

            waitForEmptyCache();

            personCopy = container.get(id);
            comparePersons(person, personCopy);

        } catch (PersistenceException e) {

            fail(e.getMessage());

        } catch (InvalidRootNodeException e) {

            fail(e.getMessage());
        }

        container.shutdown();
    }

    /**
     * Tests committing changes to an object.
     */
    @Test
    public void testCommitChanges3() {

        PersistenceContainer<Person> container = new PersistenceContainerImpl<Person>(Person.class, BASEDIR);

        Person person = newPerson("John", "Doe", "1.1.2000", "male");
        Person personCopy = null;

        try {

            ID id = container.store(person);
            personCopy = container.get(id);
            comparePersons(person, personCopy);

            waitForEmptyCache();

            person = container.get(id);
            person.setFirstName("John F.");
            ID id2 = container.commit(person);
            compareIDs(id, id2);

            personCopy = container.get(id);
            comparePersons(person, personCopy);

        } catch (PersistenceException e) {

            fail(e.getMessage());

        } catch (InvalidRootNodeException e) {

            fail(e.getMessage());
        }

        container.shutdown();
    }

    /**
     * Tests committing changes to an object.
     */
    @Test
    public void testCommitChanges4() {

        PersistenceContainer<Person> container = new PersistenceContainerImpl<Person>(Person.class, BASEDIR);

        Person person = newPerson("John", "Doe", "1.1.2000", "male");
        Person personCopy = null;

        try {

            ID id = container.store(person);
            personCopy = container.get(id);
            comparePersons(person, personCopy);

            person.setFirstName("John F.");
            ID id2 = container.commit(id, person);
            compareIDs(id, id2);

            personCopy = container.get(id);
            comparePersons(person, personCopy);

        } catch (PersistenceException e) {

            fail(e.getMessage());

        } catch (InvalidRootNodeException e) {

            fail(e.getMessage());
        }

        container.shutdown();
    }

    /**
     * Tests committing changes to an object.
     */
    @Test
    public void testCommitChanges5() {

        PersistenceContainer<Person> container = new PersistenceContainerImpl<Person>(Person.class, BASEDIR);

        Person person = newPerson("John", "Doe", "1.1.2000", "male");
        Person personCopy = null;

        try {

            ID id = container.store(person);
            personCopy = container.get(id);
            comparePersons(person, personCopy);

            waitForEmptyCache();

            person.setFirstName("John F.");
            ID id2 = container.commit(id, person);
            compareIDs(id, id2);

            personCopy = container.get(id);
            comparePersons(person, personCopy);

        } catch (PersistenceException e) {

            fail(e.getMessage());

        } catch (InvalidRootNodeException e) {

            fail(e.getMessage());
        }

        container.shutdown();
    }

    /**
     * Tests committing changes to an object.
     */
    @Test
    public void testCommitChanges6() {

        PersistenceContainer<Person> container = new PersistenceContainerImpl<Person>(Person.class, BASEDIR);

        Person person = newPerson("John", "Doe", "1.1.2000", "male");
        Person personCopy = null;

        try {

            ID id = container.store(person);
            personCopy = container.get(id);
            comparePersons(person, personCopy);

            waitForEmptyCache();

            person.setFirstName("John F.");
            ID id2 = container.commit(id, person);
            compareIDs(id, id2);

            personCopy = container.get(id);
            comparePersons(person, personCopy);


            person.setBirthDate("01.01.2000");
            id2 = container.commit(person);

            personCopy = container.get(id);
            comparePersons(person, personCopy);

        } catch (PersistenceException e) {

            fail(e.getMessage());

        } catch (InvalidRootNodeException e) {

            fail(e.getMessage());
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
