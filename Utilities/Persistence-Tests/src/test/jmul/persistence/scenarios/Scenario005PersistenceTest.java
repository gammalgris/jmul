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

package test.jmul.persistence.scenarios;


import static jmul.math.Constants.EPSILON;

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

import test.jmul.datatypes.scenarios.interfaces.Employee;
import test.jmul.datatypes.scenarios.scenario005.EmployeeImpl;
import test.jmul.persistence.PersistenceTestBase;


/**
 * This class contains tests to check a persistence container.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class Scenario005PersistenceTest extends PersistenceTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ".\\Test\\Persistence\\Scenario-005";

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
     * Tests the serialization of a person entity (i.e. the root node possesses several
     * class members).
     */
    @Test
    public void testPersistEmployee() {

        PersistenceContainer<Employee> container = new PersistenceContainerImpl<Employee>(Employee.class, BASEDIR);

        Employee employee = newEmployee("John", "Doe", "1.1.2000", "male", "salesperson", 2000.0f);
        Employee copy = null;

        try {

            ID id = container.store(employee);

            waitForEmptyCache();

            copy = container.get(id);

            container.shutdown();

        } catch (PersistenceException e) {

            fail(e.toString());

        } catch (InvalidRootNodeException e) {

            fail(e.toString());
        }

        compareEmployees(employee, copy);
    }

    /**
     * Creates a new person according to the specified parameters.
     *
     * @param aFirstName
     * @param aLastName
     * @param aBirthDate
     * @param aGender
     * @param aJobTitle
     * @param aSalary
     *
     * @return a new person
     */
    private static Employee newEmployee(String aFirstName, String aLastName, String aBirthDate, String aGender,
                                        String aJobTitle, float aSalary) {

        Employee e = new EmployeeImpl();
        e.setFirstName(aFirstName);
        e.setLastName(aLastName);
        e.setBirthDate(aBirthDate);
        e.setGender(aGender);
        e.setJobTitle(aJobTitle);
        e.setSalary(aSalary);

        return e;
    }

    /**
     * Compares two persons via assertions.
     *
     * @param e1
     * @param e2
     */
    private static void compareEmployees(Employee e1, Employee e2) {

        assertEquals("The employees' first names don't match!", e1.getFirstName(), e2.getFirstName());
        assertEquals("The employees' last names don't match!", e1.getLastName(), e2.getLastName());
        assertEquals("The employees' birthdates don't match!", e1.getBirthDate(), e2.getBirthDate());
        assertEquals("The employees' genders don't match!", e1.getGender(), e2.getGender());
        assertEquals("The employees' job titles don't match!", e1.getJobTitle(), e2.getJobTitle());
        assertEquals("The employees' salaries don't match!", e1.getSalary(), e2.getSalary(), EPSILON);
    }

}
