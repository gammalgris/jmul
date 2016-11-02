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

import test.jmul.datatypes.legacy.department.DepartmentGenderDetails;
import test.jmul.datatypes.legacy.department.DepartmentGenderDetailsImpl2;
import test.jmul.datatypes.legacy.employee.EmployeeImpl6;
import test.jmul.persistence.legacy.file.FilenameCreator;
import test.jmul.persistence.legacy.file.NameCreator;
import test.jmul.persistence.legacy.threads.DefaultSerializationThread;

import jmul.io.FileHelper;

import jmul.concurrent.threads.ObservableThread;
import jmul.concurrent.threads.ThreadPool;
import jmul.concurrent.threads.ThreadPoolImpl1;

import org.junit.Before;
import org.junit.Test;


/**
 * A collection of concurrent test cases.
 *
 * @author Kristian Kutin
 */
public class TestConcurrentSerialization extends SerializationBase {

    /**
     * The directory where tests are performed.
     */
    private static final String BASE_DIRECTORY = DRIVE + "\\Test\\Concurrent\\Serialization";

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
    }

    /**
     * Test serialization with an object graph of medium depth.
     */
    @Test
    public void testConcurrentSerialization() {

        NameCreator nameCreator = new FilenameCreator(BASE_DIRECTORY);
        ThreadPool threadPool = new ThreadPoolImpl1();


        final long feedbackThreshold = 5000;
        final int maxObjects = 150000;

        int objectCounter = 0;
        int threadCounter = Thread.activeCount();

        while (objectCounter < maxObjects) {

            for (int a = 0; a < 100; a++) {

                String filename = nameCreator.createName();

                ObservableThread t = new DefaultSerializationThread(filename, newTestObject());
                threadPool.addThread(t);

                objectCounter++;
                threadCounter = Thread.activeCount();
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

        threadPool.stop();
        System.out.println();
    }

    /**
     * The main method is only provided for purposes of manual testing.
     *
     * @param args
     *        command line arguments
     */
    public static void main(String[] args) {

        TestConcurrentSerialization test = new TestConcurrentSerialization();
        test.setUp();

        long startTime = System.currentTimeMillis();

        test.testConcurrentSerialization();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("duration: " + duration + " ms");

        test.tearDown();
        System.out.println("done.");
    }

}
