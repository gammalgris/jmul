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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.persistence.scenarios.scenario007;


import java.io.IOException;

import java.util.Iterator;

import jmul.io.deserialization.Deserializer;
import jmul.io.serialization.Serializer;

import static jmul.math.Constants.getEpsilon;

import jmul.test.classification.ModuleTest;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.jmul.datatypes.scenarios.interfaces.Employee;
import test.jmul.datatypes.scenarios.scenario007.Company;
import test.jmul.datatypes.scenarios.scenario007.EmployeeImpl;
import test.jmul.persistence.SerializationTestBase;


/**
 * This class contains tests to check the serialization and deserialization of objects.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class Scenario007SerializationTest extends SerializationTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ROOT_DIRECTORY + "Serialization\\Scenario-007";

    /**
     * The file where the generated IDs are persisted.
     */
    private static final String OUTPUT_FILE = "output";

    /**
     * An XML serializer.
     */
    private Serializer serializer;

    /**
     * An XML deserializer.
     */
    private Deserializer deserializer;

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

        serializer = initXmlSerializer();
        deserializer = initXmlDeserializer();
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDownTest() {

        serializer = null;
        deserializer = null;
    }

    /**
     * Tests the serialization of an employee entity (i.e. the root node possesses several
     * class members).
     */
    @Test
    public void testSerializeEmployee() {

        String fileName = getOutputFileName(BASEDIR, OUTPUT_FILE);

        Employee employee1 = newEmployee("John", "Doe", "1.1.2000", "male", "salesperson", 2500.0f);
        Employee employee2 = newEmployee("Jane", "Doe", "2.2.1999", "female", "salesperson", 2000.0f);
        Company company = newCompany("Acme Corp", employee1, employee2);
        Company copy = null;

        try {

            serializer.serialize(fileName, company);
            copy = (Company) deserializer.deserialize(fileName);

        } catch (IOException e) {

            fail(e.toString());
        }

        compareCompanies(company, copy);
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
     * Creates a new company according to the specified parameters.
     *
     * @param aCompanyName
     * @param someEmployees
     *
     * @return a new company
     */
    private static Company newCompany(String aCompanyName, Employee... someEmployees) {

        Company c = new Company();
        c.setCompanyName(aCompanyName);

        for (Employee e : someEmployees) {

            c.addEmployee(e);
        }

        return c;
    }

    /**
     * Compares two companies via assertions.
     *
     * @param c1
     * @param c2
     */
    private static void compareCompanies(Company c1, Company c2) {

        assertEquals("The companies' names don't match!", c1.getCompanyName(), c2.getCompanyName());

        Iterator<Employee> i1 = c1.getEmployees().iterator();
        Iterator<Employee> i2 = c2.getEmployees().iterator();

        while (i1.hasNext() && i2.hasNext()) {

            compareEmployees(i1.next(), i2.next());

            assertTrue((i1.hasNext() && i2.hasNext()) || (!i1.hasNext() && !i2.hasNext()));
        }
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
        assertEquals("The employees' salaries don't match!", e1.getSalary(), e2.getSalary(),
                     getEpsilon(Float.TYPE).floatValue());
    }

}
