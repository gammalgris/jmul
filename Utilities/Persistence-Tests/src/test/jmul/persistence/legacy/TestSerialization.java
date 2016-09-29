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

import jmul.persistence.xml.XmlSerializer;
import jmul.persistence.xml.XmlSerializerImpl;

import test.jmul.datatypes.company.CompanyDetails;
import test.jmul.datatypes.company.CompanyDetailsImpl;
import test.jmul.datatypes.company.CompanyDetailsImpl3;
import test.jmul.datatypes.contractor.Contractor;
import test.jmul.datatypes.contractor.ContractorImpl;
import test.jmul.datatypes.department.DepartmentDetails;
import test.jmul.datatypes.department.DepartmentDetailsImpl2;
import test.jmul.datatypes.department.DepartmentGenderDetails;
import test.jmul.datatypes.department.DepartmentGenderDetailsImpl2;
import test.jmul.datatypes.employee.EmployeeImpl6;

import jmul.string.StringConcatenator;

import org.junit.Before;
import org.junit.Test;


/**
 * A collection of positive test cases.
 *
 * @author Kristian Kutin
 */
public class TestSerialization extends SerializationBase {

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
    public void testSerialization() {

        CompanyDetails details = new CompanyDetailsImpl();
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
    public void testMeasuredSerialization() {

        CompanyDetails details = new CompanyDetailsImpl();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        XmlSerializer serializer = new XmlSerializerImpl();


        try {

            long start = System.currentTimeMillis();

            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", details);

            long end = System.currentTimeMillis();
            long duration = end - start;

            StringConcatenator message = new StringConcatenator("serialization duration: ", duration, " ms");
            System.out.println(message);

        } catch (IOException e) {

            // This exception is ignored.
        }
    }

    /**
     * Test the performance of the serialization with an object graph of medium
     * depth.
     */
    @Test
    public void testSerializationPerformance() {

        String lineFeed = System.getProperty("line.separator");

        final long max = 150000;
        final long feedbackThreshold = 5000;
        long counter = 0;
        long start = System.currentTimeMillis();

        while (counter < max) {

            testSerialization();
            counter++;

            if ((counter % feedbackThreshold) == 0) {

                System.out.print(".");
            }

        }

        System.out.println();

        long end = System.currentTimeMillis();
        long duration = end - start;

        StringConcatenator message =
            new StringConcatenator("serialization performance", lineFeed, "-------------------------", lineFeed,
                                   "repetitions: ", counter, lineFeed, "duration: ", duration, " ms", lineFeed,
                                   "single invocation: ", ((double) duration / (double) counter), " ms");
        System.out.println(message);
    }

    /* *
     * Test serialization with an object graph where the root node information
     * is inherited.
     * /
    @Test
    public void testInheritedRootNode() {

        Contractor contractor = new ContractorImpl("John", "Doe", "1.1.2000", "male");

        try {

            XmlSerializer serializer = new XmlSerializerImpl();
            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", contractor);

        } catch (IOException e) {

            // This exception is ignored.
        }
    }*/

    /* *
     * Test serialization with an object graph of medium depth.
     * /
    @Test
    public void testCompositeCollection() {

        CompanyDetails details = new CompanyDetailsImpl3();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        XmlSerializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", details);

        } catch (IOException e) {

            // This exception is ignored.
        }
    }*/

    /* *
     * Test serialization with an object graph of medium depth.
     * /
    @Test
    public void testMapWithCollectionValue() {

        DepartmentDetails details = new DepartmentDetailsImpl2();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        XmlSerializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", details);

        } catch (IOException e) {

            // This exception is ignored.
        }
    }*/

    /* *
     * Test serialization with an object graph of medium depth.
     * /
    @Test
    public void testMapWithMapValue() {

        DepartmentGenderDetails details = new DepartmentGenderDetailsImpl2();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        XmlSerializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(BASE_DIR + FILE_SEPARATOR + "test.xml", details);

        } catch (IOException e) {

            // This exception is ignored.
        }
    }*/

    /**
     * The main method is only provided for purposes of manual testing.
     *
     * @param args
     *        command line arguments
     */
    public static void main(String[] args) {

        TestSerialization test = new TestSerialization();
        test.setUp();
        //test.testSerialization();
        //test.testSerializationPerformance();
        //test.testCompositeCollection();
        //test.testMap();
        //test.testMapWithCollectionValue();
        //test.testMapWithMapValue();

        /*for (int a = 0; a < 100; a++) {

            test.testMeasuredSerialization();
        }*/

        //test.testMapWithCollectionValue();
        //test.testMapWithMapValue();
        test.testSerializationPerformance();

        test.tearDown();

        System.out.println("done.");
    }

}
