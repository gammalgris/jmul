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

import jmul.persistence.xml.XmlSerializer;

import static jmul.string.Constants.FILE_SEPARATOR;

import jmul.test.classification.ModuleTest;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.jmul.datatypes.company.CompanyDetails;
import test.jmul.datatypes.company.CompanyDetailsImpl3;
import test.jmul.datatypes.contractor.Contractor;
import test.jmul.datatypes.contractor.ContractorImpl;
import test.jmul.datatypes.department.DepartmentDetails;
import test.jmul.datatypes.department.DepartmentDetailsImpl2;
import test.jmul.datatypes.department.DepartmentGenderDetails;
import test.jmul.datatypes.department.DepartmentGenderDetailsImpl2;
import test.jmul.datatypes.employee.Employee;
import test.jmul.datatypes.employee.EmployeeImpl6;
import test.jmul.datatypes.employee.EmployeeImpl7;
import test.jmul.datatypes.person.Person;
import test.jmul.datatypes.person.PersonImpl5;


/**
 * This class contains tests to check the serialization of objects.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class SerializationTest extends SerializationTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ".\\Test\\Serialization";

    /**
     * The file where the generated IDs are persisted.
     */
    private static final String OUTPUT_FILE = BASEDIR + FILE_SEPARATOR + "output.xml";

    /**
     * An XML serializer.
     */
    private XmlSerializer serializer;

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
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDownTest() {

        serializer = null;
    }

    /**
     * Tests the serialization of a person entity (i.e. the root node possesses several
     * class members).
     */
    @Test
    public void testSerializePerson() {

        Person person = new PersonImpl5("John", "Doe", "1.1.2000", "male");

        try {

            serializer.serialize(getOutputFileName(OUTPUT_FILE), person);

        } catch (IOException e) {

            fail(e.toString());
        }
    }

    /**
     * Tests the serialization of an employee entity (i.e. the root node possesses several
     * class members, some of which are complex objects).
     */
    @Test
    public void testSerializeEmployee() {

        Employee employee = new EmployeeImpl7("John", "Doe", "1.1.2000", "male", 1000.0f, "trainee");

        try {

            serializer.serialize(getOutputFileName(OUTPUT_FILE), employee);

        } catch (IOException e) {

            fail(e.toString());
        }
    }

    /**
     * Tests the serialization of company details (i.e. the root node possesses several
     * class members, some of which are collections).
     */
    @Test
    public void testSerializeCompanyDetails() {

        CompanyDetails details = new CompanyDetailsImpl3();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        try {

            serializer.serialize(getOutputFileName(OUTPUT_FILE), details);

        } catch (IOException e) {

            fail(e.toString());
        }
    }

    /**
     * Tests the serialization of a contractor (i.e. the root node annotation is inherited from
     * a parent class).
     */
    @Test
    public void testSerializeContractor() {

        Contractor contractor = new ContractorImpl("John", "Doe", "1.1.2000", "male");

        try {

            serializer.serialize(getOutputFileName(OUTPUT_FILE), contractor);

        } catch (IOException e) {

            fail(e.toString());
        }
    }

    /**
     * Tests the serialization of company details (i.e. the root node possesses several
     * class members, some of which are collections and maps).
     */
    @Test
    public void testSerializeCompanyDetails2() {

        DepartmentDetails details = new DepartmentDetailsImpl2();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        try {

            serializer.serialize(getOutputFileName(OUTPUT_FILE), details);

        } catch (IOException e) {

            fail(e.toString());
        }
    }

    /**
     * Tests the serialization of company details (i.e. the root node possesses several
     * class members, some of which are collections and maps).
     */
    @Test
    public void testSerializeDepartmentGenderDetails() {
        
        DepartmentGenderDetails details = new DepartmentGenderDetailsImpl2();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        try {

            serializer.serialize(getOutputFileName(OUTPUT_FILE), details);

        } catch (IOException e) {

            fail(e.toString());
        }
    }

}
