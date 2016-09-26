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


import java.io.File;
import java.io.IOException;

import jmul.persistence.xml.XmlSerializer;
import jmul.persistence.xml.XmlSerializerImpl;

import test.jmul.persistence.company.CompanyDetails;
import test.jmul.persistence.company.CompanyDetailsImpl2;
import test.jmul.persistence.department.DepartmentDetails;
import test.jmul.persistence.department.DepartmentDetailsImpl;
import test.jmul.persistence.department.DepartmentGenderDetails;
import test.jmul.persistence.department.DepartmentGenderDetailsImpl;
import test.jmul.persistence.employee.Employee;
import test.jmul.persistence.employee.EmployeeImpl;
import test.jmul.persistence.employee.EmployeeImpl2;
import test.jmul.persistence.employee.EmployeeImpl3;
import test.jmul.persistence.employee.EmployeeImpl4;
import test.jmul.persistence.employee.EmployeeImpl5;
import test.jmul.persistence.employee.EmployeeImpl6;
import test.jmul.persistence.person.Person;
import test.jmul.persistence.person.PersonImpl2;
import test.jmul.persistence.person.PersonImpl3;
import test.jmul.persistence.person.PersonImpl4;

import jmul.transformation.TransformationException;

import org.junit.Before;
import org.junit.Test;

import test.jmul.persistence.SerializationBase;


/**
 * A colelction of negative test cases.
 *
 * @author Kristian Kutin
 */
public class TestFailedSerialization extends SerializationBase {

    /**
     * The file separator which is used for this operating system.
     */
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * A base directory.
     */
    private static final String BASE_DIR = DRIVE + "\\Test\\";

    /**
     * Preparations before a test.
     */
    @Override
    @Before
    public void setUp() {

        (new File(BASE_DIR)).mkdirs();
    }

    /**
     * Test serialization with an object graph where no root node was marked.
     */
    @Test(expected = TransformationException.class)
    public void testInvalidRootNode() {

        Object object = new String("Hello World!");

        try {

            XmlSerializer serializer = new XmlSerializerImpl();
            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", object);

        } catch (IOException e) {

            // This exception is not handled because we expect a transformation
            // exception (see method header).
        }
    }

    /**
     * Test serialization with an object graph where there is no getter method
     * for a field.
     */
    @Test(expected = TransformationException.class)
    public void testMissingGetter() {

        Person person = new PersonImpl2("John", "Doe", "1.1.2000", "male");

        try {

            XmlSerializer serializer = new XmlSerializerImpl();
            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", person);

        } catch (IOException e) {

            // This exception is not handled because we expect a transformation
            // exception (see method header).
        }
    }

    /**
     * Test serialization with an object graph where there is no getter method
     * for a field.
     */
    @Test(expected = TransformationException.class)
    public void testMissingGetter_2() {

        Employee employee = new EmployeeImpl("John", "Doe", "1.1.2000", "male", 1000.0f, "Developer");

        try {

            XmlSerializer serializer = new XmlSerializerImpl();
            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", employee);

        } catch (IOException e) {

            // This exception is not handled because we expect a transformation
            // exception (see method header).
        }
    }

    /**
     * Test serialization with an object graph where a field appears in a class
     * and its parent class under the same name.<br>
     * <br>
     * <i>One of the two fields cannot be accessed because access is only
     * granted by setter and getter methods. This ambiguity makes handling of
     * this case more complicated. An alternative would be to ignore getter
     * and setter methods and instead access a field directly. Even if that
     * means to ignore access modifiers (e.g. protected, private).</i>
     */
    @Test(expected = TransformationException.class)
    public void testDuplicateField() {

        Person person = new PersonImpl3("John", "Doe", "1.1.2000", "male");

        try {

            XmlSerializer serializer = new XmlSerializerImpl();
            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", person);

        } catch (IOException e) {

            // This exception is not handled because we expect a transformation
            // exception (see method header).
        }
    }

    /**
     * Test serialization with an object graph which possesses a final
     * field.<br>
     * <br>
     * <i>A field is declared as final. Serialization is possible as access is
     * provided by a getter method. Deserialization on the other hand would be
     * an issue since the field can only be initialized in a constructor.</i>
     */
    @Test(expected = TransformationException.class)
    public void testFinalField() {

        Person person = new PersonImpl4("John", "Doe", "1.1.2000", "male");

        try {

            XmlSerializer serializer = new XmlSerializerImpl();
            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", person);

        } catch (IOException e) {

            // This exception is not handled because we expect a transformation
            // exception (see method header).
        }
    }

    /**
     * Test serialization with an object graph where the getter method has
     * protected visibility.
     */
    @Test(expected = TransformationException.class)
    public void testProtectedGetter() {

        Employee employee = new EmployeeImpl2("John", "Doe", "1.1.2000", "male", 1000.0f, "Developer");

        try {

            XmlSerializer serializer = new XmlSerializerImpl();
            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", employee);

        } catch (IOException e) {

            // This exception is not handled because we expect a transformation
            // exception (see method header).
        }
    }

    /**
     * Test serialization with an object graph where the getter method has
     * private visibility.
     */
    @Test(expected = TransformationException.class)
    public void testPrivateGetter() {

        Employee employee = new EmployeeImpl3("John", "Doe", "1.1.2000", "male", 1000.0f, "Developer");

        try {

            XmlSerializer serializer = new XmlSerializerImpl();
            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", employee);

        } catch (IOException e) {

            // This exception is not handled because we expect a transformation
            // exception (see method header).
        }
    }

    /**
     * Test serialization with an object graph where the getter method has
     * default visibility.
     */
    @Test(expected = TransformationException.class)
    public void testDefaultGetter() {

        Employee employee = new EmployeeImpl4("John", "Doe", "1.1.2000", "male", 1000.0f, "Developer");

        try {

            XmlSerializer serializer = new XmlSerializerImpl();
            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", employee);

        } catch (IOException e) {

            // This exception is not handled because we expect a transformation
            // exception (see method header).
        }
    }

    /**
     * Test serialization with an object graph where several root nodes have
     * been specified.
     */
    @Test(expected = TransformationException.class)
    public void testMultipleRootNodes() {

        Employee employee = new EmployeeImpl5("John", "Doe", "1.1.2000", "male", 1000.0f, "Developer");

        try {

            XmlSerializer serializer = new XmlSerializerImpl();
            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", employee);

        } catch (IOException e) {

            // This exception is not handled because we expect a transformation
            // exception (see method header).
        }
    }

    /**
     * Test serialization with an object graph of medium depth.<br>
     * <br>
     * <i>Within the graph an object is present which is a composite type and a
     * collection at the same time. The corresponding field isn't marked with
     * the appropriate annotation to identify the container types.</i>
     */
    @Test(expected = TransformationException.class)
    public void testCompositeCollection() {

        CompanyDetails details = new CompanyDetailsImpl2();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        XmlSerializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", details);

        } catch (IOException e) {

            // This exception is ignored.
        }
    }

    /**
     * Test serialization with an object graph of medium depth.<br>
     * <br>
     * <i>The map is properly annotated to declare key ({@link String}) and
     * value ({@link java.util.Collection}) types. But the
     * values are of a collection type and informations about the elements of
     * the collection are missing. An {@link IllegalArgumentException} expected
     * when the transformations tries to process the values of the map.</i>
     */
    @Test(expected = TransformationException.class)
    public void testMapWithCollectionValue() {

        DepartmentDetails details = new DepartmentDetailsImpl();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        XmlSerializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", details);

        } catch (IOException e) {

            // This exception is ignored.
        }
    }

    /**
     * Test serialization with an object graph of medium depth.
     */
    @Test(expected = TransformationException.class)
    public void testMapWithMapValue() {

        DepartmentGenderDetails details = new DepartmentGenderDetailsImpl();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        XmlSerializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", details);

        } catch (IOException e) {

            // This exception is ignored.
        }
    }

    /**
     * The main method is only provided for purposes of manual testing.
     *
     * @param args
     *        command line arguments
     */
    public static void main(String[] args) {

        TestFailedSerialization test = new TestFailedSerialization();

        test.setUp();
        test.testMapWithCollectionValue();
        test.tearDown();

        System.out.println("done.");
    }

}
