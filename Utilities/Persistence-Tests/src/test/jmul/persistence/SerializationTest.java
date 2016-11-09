/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import jmul.persistence.xml.XmlDeserializer;
import jmul.persistence.xml.XmlSerializer;

import jmul.test.classification.ModuleTest;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.jmul.datatypes.legacy.company.CompanyDetails;
import test.jmul.datatypes.legacy.company.CompanyDetailsImpl;
import test.jmul.datatypes.legacy.contractor.Contractor;
import test.jmul.datatypes.legacy.contractor.ContractorImpl;
import test.jmul.datatypes.legacy.department.DepartmentDetails;
import test.jmul.datatypes.legacy.department.DepartmentDetailsImpl2;
import test.jmul.datatypes.legacy.department.DepartmentGenderDetails;
import test.jmul.datatypes.legacy.department.DepartmentGenderDetailsImpl2;
import test.jmul.datatypes.legacy.employee.Employee;
import test.jmul.datatypes.legacy.employee.EmployeeImpl6;
import test.jmul.datatypes.legacy.employee.EmployeeImpl7;
import test.jmul.datatypes.legacy.organisation.OrganisationDetails;
import test.jmul.datatypes.legacy.person.Person;
import test.jmul.datatypes.legacy.person.PersonImpl5;


/**
 * This class contains tests to check the serialization of objects.
 *
 * @author Kristian Kutin
 */
@Deprecated
@ModuleTest
public class SerializationTest extends SerializationTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ".\\Test\\Serialization";

    /**
     * The file where the generated IDs are persisted.
     */
    private static final String OUTPUT_FILE = "output";

    /**
     * An XML serializer.
     */
    private XmlSerializer serializer;

    /**
     * An XML deserializer.
     */
    private XmlDeserializer deserializer;

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
     * Tests the serialization of a person entity (i.e. the root node possesses several
     * class members).
     */
    @Test
    public void testSerializePerson() {

        String fileName = getOutputFileName(BASEDIR, OUTPUT_FILE);

        Person person = new PersonImpl5("John", "Doe", "1.1.2000", "male");
        Person copy = null;

        try {

            serializer.serialize(fileName, person);
            copy = (Person) deserializer.deserialize(fileName);

        } catch (IOException e) {

            fail(e.toString());
        }

        comparePersons(person, copy);
    }

    /**
     * Compares two persons via assertions.
     *
     * @param p1
     * @param p2
     */
    private void comparePersons(Person p1, Person p2) {

        assertEquals("The persons' first names don't match!", p1.getFirstName(), p2.getFirstName());
        assertEquals("The persons' last names don't match!", p1.getLastName(), p2.getLastName());
        assertEquals("The persons' birthdates don't match!", p1.getBirthdate(), p2.getBirthdate());
        assertEquals("The persons' genders don't match!", p1.getGender(), p2.getGender());
    }

    /**
     * Tests the serialization of an employee entity (i.e. the root node possesses several
     * class members, some of which are complex objects).
     */
    @Test
    public void testSerializeEmployee() {

        String fileName = getOutputFileName(BASEDIR, OUTPUT_FILE);

        Employee employee = new EmployeeImpl7("John", "Doe", "1.1.2000", "male", 1000.0f, "trainee");
        Employee copy = null;

        try {

            serializer.serialize(fileName, employee);
            copy = (Employee) deserializer.deserialize(fileName);

        } catch (IOException e) {

            fail(e.toString());
        }

        compareEomployees(employee, copy);
    }

    /**
     * Compares two employees via assertions.
     *
     * @param e1
     * @param e2
     */
    private void compareEomployees(Employee e1, Employee e2) {

        assertEquals("The employees' first names don't match!", e1.getFirstName(), e2.getFirstName());
        assertEquals("The employees' last names don't match!", e1.getLastName(), e2.getLastName());
        assertEquals("The employees' birthdates don't match!", e1.getBirthdate(), e2.getBirthdate());
        assertEquals("The employees' genders don't match!", e1.getGender(), e2.getGender());
        assertEquals("The employees' salaries don't match!", e1.getSalary(), e2.getSalary(), 0.01f);
        assertEquals("The employees' titles don't match!", e1.getTitle(), e2.getTitle());
    }

    /**
     * Tests the serialization of company details (i.e. the root node possesses several
     * class members, some of which are collections).
     */
    @Test
    public void testSerializeCompanyDetails() {

        String fileName = getOutputFileName(BASEDIR, OUTPUT_FILE);

        CompanyDetails details = new CompanyDetailsImpl();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));
        CompanyDetails copy = null;

        try {

            serializer.serialize(fileName, details);
            copy = (CompanyDetails) deserializer.deserialize(fileName);

        } catch (IOException e) {

            fail(e.toString());
        }

        compareCompanyDetails(details, copy);
    }

    /**
     * Compares the details of two organisations via assertions.
     *
     * @param o1
     * @param o2
     */
    private void compareOrganisation(OrganisationDetails o1, OrganisationDetails o2) {

        assertEquals("The organisations' total salaries don't match!", o1.calculateTotalSalaries(),
                     o2.calculateTotalSalaries(), 0.01f);

        List<Employee> oe1 = new ArrayList<Employee>(o1.getAllEmployees());
        List<Employee> oe2 = new ArrayList<Employee>(o2.getAllEmployees());

        assertEquals(oe1.size(), oe2.size());

        for (int a = 0; a < oe1.size(); a++) {

            Employee e1 = oe1.get(a);
            Employee e2 = oe2.get(a);

            compareEomployees(e1, e2);
        }
    }

    /**
     * Compares the details of two companies via assertions.
     *
     * @param cd1
     * @param cd2
     */
    private void compareCompanyDetails(CompanyDetails cd1, CompanyDetails cd2) {

        compareOrganisation(cd1, cd2);
    }

    /**
     * Tests the serialization of a contractor (i.e. the root node annotation is inherited from
     * a parent class).
     */
    @Test
    public void testSerializeContractor() {

        String fileName = getOutputFileName(BASEDIR, OUTPUT_FILE);

        Contractor contractor = new ContractorImpl("John", "Doe", "1.1.2000", "male");
        Contractor copy = null;

        try {

            serializer.serialize(fileName, contractor);
            copy = (Contractor) deserializer.deserialize(fileName);

        } catch (IOException e) {

            fail(e.toString());
        }

        compareContractors(contractor, copy);
    }

    /**
     * Compares two contractors via assertions.
     *
     * @param c1
     * @param c2
     */
    private void compareContractors(Contractor c1, Contractor c2) {

        assertEquals(c1.getFirstName(), c2.getFirstName());
        assertEquals(c1.getLastName(), c2.getLastName());
        assertEquals(c1.getBirthdate(), c2.getBirthdate());
        assertEquals(c1.getGender(), c2.getGender());
        assertEquals(c1.getCurrentContracts(), c2.getCurrentContracts());
    }

    /**
     * Tests the serialization of department details (i.e. the root node possesses several
     * class members, some of which are collections and maps).
     */
    @Test
    public void testSerializeDepartmentDetails() {

        String fileName = getOutputFileName(BASEDIR, OUTPUT_FILE);
        String jobTitle = "Engineer";

        DepartmentDetails details = new DepartmentDetailsImpl2();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, jobTitle));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, jobTitle));
        DepartmentDetails copy = null;

        try {

            serializer.serialize(fileName, details);
            copy = (DepartmentDetails) deserializer.deserialize(fileName);

        } catch (IOException e) {

            fail(e.toString());
        }

        compareDepartmentDetails(details, copy);
        assertEquals(details.employed(jobTitle), copy.employed(jobTitle));
    }

    /**
     * Compares the details of two departments via assertions.
     *
     * @param dd1
     * @param dd2
     */
    private void compareDepartmentDetails(DepartmentDetails dd1, DepartmentDetails dd2) {

        compareOrganisation(dd1, dd2);
    }

    /**
     * Tests the serialization of company details (i.e. the root node possesses several
     * class members, some of which are collections and maps).
     */
    @Test
    public void testSerializeDepartmentGenderDetails() {

        String fileName = getOutputFileName(BASEDIR, OUTPUT_FILE);
        String jobTitle = "Engineer";
        String male = "male";
        String female = "female";

        DepartmentGenderDetails details = new DepartmentGenderDetailsImpl2();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", male, 1000.00f, jobTitle));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", female, 800.00f, jobTitle));
        DepartmentGenderDetails copy = null;

        try {

            serializer.serialize(fileName, details);
            copy = (DepartmentGenderDetails) deserializer.deserialize(fileName);

        } catch (IOException e) {

            fail(e.toString());
        }

        compareDepartmentGenderDetails(details, copy);
        assertEquals(details.employed(jobTitle, male), copy.employed(jobTitle, male));
        assertEquals(details.employed(jobTitle, female), copy.employed(jobTitle, female));
    }

    /**
     * Compares the details of two departments via assertions.
     *
     * @param dgd1
     * @param dgd2
     */
    private void compareDepartmentGenderDetails(DepartmentGenderDetails dgd1, DepartmentGenderDetails dgd2) {

        compareOrganisation(dgd1, dgd2);
    }

}
