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

import java.util.Collection;
import java.util.Iterator;

import test.jmul.datatypes.department.DepartmentGenderDetails;
import test.jmul.datatypes.department.DepartmentGenderDetailsImpl2;
import test.jmul.datatypes.employee.EmployeeImpl6;
import test.jmul.persistence.file.FilenameCreator;
import test.jmul.persistence.file.NameCreator;
import test.jmul.persistence.threads.DefaultDeserializationThread;
import test.jmul.persistence.threads.DefaultSerializationThread;

import jmul.io.FileHelper;

import jmul.concurrent.threads.ObservableThread;
import jmul.concurrent.threads.ThreadPool;
import jmul.concurrent.threads.ThreadPoolImpl2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.jmul.persistence.SerializationBase;


/**
 * A collection of concurrent test cases.
 *
 * @author Kristian Kutin
 */
public class TestConcurrentDeserialization extends SerializationBase {

    /**
     * The directory where tests are performed.
     */
    private static final String BASE_DIRECTORY = DRIVE + "\\Test\\Concurrent\\Deserialization";

    /**
     * A threadpool.
     */
    private ThreadPool threadPool;

    /**
     * The method instantiates and initializes a new test object.
     *
     * @return a test object
     */
    private static Object newTestObject() {

        DepartmentGenderDetails details = new DepartmentGenderDetailsImpl2();
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        return details;
    }

    /**
     * Preparations before a test.
     */
    @Override
    @Before
    public void setUp() {

        File basedir = new File(BASE_DIRECTORY);

        if (basedir.exists()) {

            FileHelper.delete(basedir);
        }

        basedir.mkdirs();

        threadPool = new ThreadPoolImpl2();
    }

    /**
     * Cleanup after a test.
     */
    @Override
    @After
    public void tearDown() {

        threadPool.stop();
    }

    /**
     * Test serialization with an object graph of medium depth.
     */
    @Test
    public void testConcurrentDeserialization() {

        final long feedbackThreshold = 5000;


        // Serialize some objects first.

        System.out.print("serialize test objects");

        NameCreator nameCreator = new FilenameCreator(BASE_DIRECTORY);

        final int maxObjects = 20000;
        int objectCounter = 0;


        while (objectCounter < maxObjects) {

            for (int a = 0; a < 100; a++) {

                String filename = nameCreator.createName();

                ObservableThread t = new DefaultSerializationThread(filename, newTestObject());
                threadPool.addThread(t);

                objectCounter++;
            }

            if ((objectCounter % feedbackThreshold) == 0) {

                System.out.print(".");
            }

            try {

                Thread.currentThread().sleep(50L);

            } catch (InterruptedException e) {

                // Ignore this exception.
            }
        }

        System.out.println();


        // Now deserialize some objects.

        System.out.print("deserialize test objects");

        Collection<File> files = FileHelper.getFiles(BASE_DIRECTORY, "xml", true);
        Iterator<File> iterator = files.iterator();

        final int maxFiles = files.size();
        int fileCounter = 0;

        while (fileCounter < maxFiles) {

            for (int a = 0; a < 100; a++) {

                if (iterator.hasNext()) {

                    String filename = iterator.next().getAbsolutePath();

                    ObservableThread t = new DefaultDeserializationThread(filename);
                    threadPool.addThread(t);

                    fileCounter++;
                }
            }

            if ((fileCounter % feedbackThreshold) == 0) {

                System.out.print(".");
            }

            try {

                Thread.currentThread().sleep(50L);

            } catch (InterruptedException e) {

                // Ignore this exception.
            }
        }

        System.out.println();
    }

    /**
     * The main method is only provided for purposes of manual testing.
     *
     * @param args
     *        command line arguments
     */
    public static void main(String[] args) {

        TestConcurrentDeserialization test = new TestConcurrentDeserialization();
        test.setUp();

        long startTime = System.currentTimeMillis();

        test.testConcurrentDeserialization();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("duration: " + duration + " ms");

        test.tearDown();
        System.out.println("done.");
    }

}
