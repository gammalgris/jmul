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

package test.jmul.persistence.legacy;


import java.io.File;
import java.io.IOException;

import jmul.persistence.xml.XmlDeserializer;
import jmul.persistence.xml.XmlDeserializerImpl;
import jmul.persistence.xml.XmlSerializer;
import jmul.persistence.xml.XmlSerializerImpl;

import test.jmul.datatypes.legacy.department.DepartmentDetails;
import test.jmul.datatypes.legacy.department.DepartmentDetailsImpl2;
import test.jmul.datatypes.legacy.employee.EmployeeImpl6;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * A collection of positive test cases.
 *
 * @author Kristian Kutin
 */
public class TestDeserialization extends SerializationBase {

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
     * Test serialization with an object graph of medium depth.
     */
    @Test
    public void testDeserialization() {

        // Serialize an object first.

        DepartmentDetails original = new DepartmentDetailsImpl2();
        original.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        original.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        XmlSerializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", original);

        } catch (IOException e) {

            // This exception is ignored.
        }


        // Now deserialize it.

        XmlDeserializer deserializer = new XmlDeserializerImpl();
        DepartmentDetails clone = null;

        try {

            clone = (DepartmentDetails) deserializer.deserialize(BASE_DIR + FILE_SEPARATOR + "test.xml");

        } catch (IOException e) {

            // This exception is ignored.
        }

        assertEquals(original, clone);
    }

    /**
     * Test serialization with an object graph of medium depth.
     */
    @Test
    public void testDeserializationPerformance() {

        long start;
        long end;
        long duration;

        for (int a = 0; a < 10; a++) {

            // Serialize an object first.

            DepartmentDetails original = new DepartmentDetailsImpl2();
            original.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
            original.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));


            start = System.currentTimeMillis();

            XmlSerializer serializer = new XmlSerializerImpl();

            try {

                serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", original);

            } catch (IOException e) {

                // This exception is ignored.
            }

            end = System.currentTimeMillis();
            duration = end - start;

            System.out.println("serialization: " + duration + " ms");


            // Now deserialize it.

            XmlDeserializer deserializer = new XmlDeserializerImpl();
            DepartmentDetails clone = null;

            start = System.currentTimeMillis();

            try {

                clone = (DepartmentDetails) deserializer.deserialize(BASE_DIR + FILE_SEPARATOR + "test.xml");

            } catch (IOException e) {

                // This exception is ignored.
            }

            end = System.currentTimeMillis();
            duration = end - start;

            System.out.println("deserialization: " + duration + " ms");

            assertEquals(original, clone);
        }
    }

    /**
     * The main method is only provided for purposes of manual testing.
     *
     * @param args
     *        command line arguments
     */
    public static void main(String[] args) {

        TestDeserialization test = new TestDeserialization();
        test.setUp();

        //test.testDeserializationPerformance();
        test.testDeserialization();

        test.tearDown();

        System.out.println("done.");
    }

}
