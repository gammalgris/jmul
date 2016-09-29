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

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceContainerImpl;

import test.jmul.datatypes.department.DepartmentGenderDetails;
import test.jmul.datatypes.employee.Employee;
import test.jmul.datatypes.employee.EmployeeImpl6;
import test.jmul.persistence.legacy.threads.GetObjectThread;
import test.jmul.persistence.legacy.threads.StoreObjectThread;

import test.jmul.persistence.legacy.threads.ConcurrencyHelper;

import jmul.persistence.id.ID;
import jmul.persistence.id.IDGenerator;
import jmul.persistence.id.StringIDGenerator;

import jmul.string.StringConcatenator;

import jmul.concurrent.threads.ObservableThread;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.jmul.datatypes.department.DepartmentGenderDetailsImpl2;


/**
 * A collection of test cases to test the functionalities of a persistence
 * manager with concurrent requests.
 *
 * @author Kristian Kutin
 */
public class TestConcurrentPersistence extends SerializationBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = DRIVE + "\\Test\\Concurrent\\Persistence";

    /**
     * A persistence manager.
     */
    private PersistenceContainer<DepartmentGenderDetails> persistenceManager;

    /**
     * An id generator to create IDs.
     */
    private IDGenerator idGenerator;

    /**
     * Preparations before a test.
     */
    @Before
    public void setUp() {

        File directory = new File(BASEDIR);

        if (directory.exists()) {

            directory.delete();
        }


        persistenceManager =
            new PersistenceContainerImpl<DepartmentGenderDetails>(DepartmentGenderDetails.class, BASEDIR);


        String filename = DRIVE + "\\randomID";
        File file = new File(filename);

        if (file.exists()) {

            file.delete();
        }

        idGenerator = StringIDGenerator.getAlternativeGenerator(filename);
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDown() {

        persistenceManager.shutdown();
        persistenceManager = null;
    }

    /**
     * Returns a random character.
     *
     * @return a character
     */
    private char getRandomCharacter() {

        String allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int randomIndex = (int) (allowedCharacters.length() * Math.random());
        char randomCharacter = allowedCharacters.charAt(randomIndex);

        return randomCharacter;
    }

    /**
     * Returns a random string.
     *
     * @return a string
     */
    private String createRandomString() {

        int size = 4 + (int) (Math.random() * 10);
        StringConcatenator randomString = new StringConcatenator();

        for (int a = 0; a < size; a++) {

            randomString.append(getRandomCharacter());
        }

        return randomString.toString();
    }

    /**
     * Creates a new employee with random values.
     *
     * @return a new employee
     */
    private Employee createRandomEmployee() {

        return new EmployeeImpl6(createRandomString(), createRandomString(), createRandomString(), createRandomString(),
                                 (float) (Math.random() * 2000.00f), createRandomString());
    }

    /**
     * Creates random organisation details.
     *
     * @return organisation details
     */
    private DepartmentGenderDetails createRandomOrganisationDetails() {

        DepartmentGenderDetails details = new DepartmentGenderDetailsImpl2();

        int employees = (int) (Math.random() * 50);

        for (int a = 0; a < employees; a++) {

            details.addEmployee(createRandomEmployee());
        }

        return details;
    }

    /**
     * Creates a random id.
     *
     * @return an id
     */
    private ID createRandomID() {

        return idGenerator.generateID();
    }

    /**
     * Creates a random persistence operation thread.
     *
     * @return a persistence operation thread
     */
    private ObservableThread createRandomThread() {

        int operationType = (int) (Math.random() * 2);

        ObservableThread operation = null;

        switch (operationType) {

        case 0:
            operation = new GetObjectThread(persistenceManager, createRandomID());
            break;
        case 1:
            operation = new StoreObjectThread(persistenceManager, createRandomOrganisationDetails());
            break;
        }

        return operation;
    }

    /**
     * The method tests concurrent persistance requests.
     */
    @Test
    public void testConcurrentPersistenceRequests() {

        long minute = (60L * 1000L);
        long twoMinutes = (2L * minute);
        long fiveMinutes = (5L * minute);
        long sevenMinutes = (7L * minute);
        long eightMinutes = (8L * minute);
        long nineMinutes = (9L * minute);
        long tenMinutes = (10L * minute);

        int[] threadCounts = { 200, 100, 200, 200, 50, 100, 25, 50 };
        long[] injectionTimes = {
            System.currentTimeMillis(), (System.currentTimeMillis() + minute),
            (System.currentTimeMillis() + twoMinutes), (System.currentTimeMillis() + fiveMinutes),
            (System.currentTimeMillis() + sevenMinutes), (System.currentTimeMillis() + eightMinutes),
            (System.currentTimeMillis() + nineMinutes), (System.currentTimeMillis() + tenMinutes)
        };
        int stopBlock = Math.min(threadCounts.length, injectionTimes.length);

        int block = 0;


        Collection<Thread> activeThreads = new ArrayList<Thread>();


        boolean loop = true;
        while (loop) {

            // inject threads.

            if (block < stopBlock) {

                int threadCount = threadCounts[block];
                long injectionTime = injectionTimes[block];

                if (System.currentTimeMillis() >= injectionTime) {

                    for (int a = 0; a < threadCount; a++) {

                        Runnable r = createRandomThread();
                        Thread t = new Thread(r);
                        activeThreads.add(t);
                        t.start();
                    }

                    block++;
                }
            }


            // Check for finished threads.

            Collection<Thread> finishedThreads = new ArrayList<Thread>();

            for (Thread t : activeThreads) {

                if (!t.isAlive()) {

                    finishedThreads.add(t);
                }
            }

            if (!finishedThreads.isEmpty()) {

                StringConcatenator message = new StringConcatenator("finished threads: ", finishedThreads);
                ConcurrencyHelper.println(message);
            }

            activeThreads.removeAll(finishedThreads);


            // Check if processing is done.

            loop = (block < stopBlock) || !activeThreads.isEmpty();


            // Slepp.

            try {

                Thread.currentThread().sleep(100L);

            } catch (InterruptedException e) {

                // Ignore this exception.
            }
        }
    }

    /**
     * The main method is only provided for purposes of manual testing.
     *
     * @param args
     *        command line arguments
     */
    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Click button to start test");

        TestConcurrentPersistence test = new TestConcurrentPersistence();
        test.setUp();

        try {

            test.testConcurrentPersistenceRequests();

        } catch (Throwable e) {

            test.tearDown();
            throw new RuntimeException(e);
        }

        test.tearDown();

        System.out.println("done.");
    }

}
