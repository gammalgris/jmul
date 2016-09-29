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

import java.util.ArrayList;
import java.util.Collection;

import jmul.io.FileHelper;

import jmul.persistence.file.FileManager;
import jmul.persistence.file.FileManagerImpl;
import jmul.persistence.id.ID;
import jmul.persistence.id.IDGenerator;
import jmul.persistence.id.StringIDGenerator;

import jmul.string.StringConcatenator;

import jmul.time.Stopwatch;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import test.jmul.persistence.legacy.threads.ConcurrencyHelper;


/**
 * A collection of test cases to test the functionalities of a file manager
 * under concurrent test scenarios.
 *
 * @author Kristian Kutin
 */
public class TestConcurrentFileManager extends SerializationBase {

    /**
     * The file separator for this operating system.
     */
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = DRIVE + "\\Test\\Concurrent\\FileManager";

    /**
     * A file manager.
     */
    private FileManager fileManager;

    /**
     * Preparations before a test.
     */
    @Override
    @Before
    public void setUp() {

        File baseDirectory = new File(BASEDIR);

        if (baseDirectory.exists()) {

            FileHelper.delete(baseDirectory);
        }

        fileManager = new FileManagerImpl(BASEDIR);
    }

    /**
     * Cleanup after a test.
     */
    @Override
    @After
    public void tearDown() {

        fileManager.shutdown();
    }

    /**
     * Create the specified number of files using a file manager.
     *
     * @param aNumber
     *        the number of files to be created
     */
    private void createTestFiles(int aNumber) {

        int feedbackThreshold = 10000;

        String idFile = BASEDIR + FILE_SEPARATOR + "id";
        IDGenerator generator = StringIDGenerator.getAlternativeGenerator(idFile);

        for (int a = 0; a < aNumber; a++) {

            ID id = generator.generateID();
            File newFile = fileManager.newFile(id.toString());

            try {

                newFile.createNewFile();

            } catch (IOException e) {

                // Ignore
            }

            if ((a % feedbackThreshold) == 0) {

                System.out.print(".");
            }
        }

        System.out.println();
    }

    /**
     * Tests other functionalities of a file manager in a concurrent scenario.
     */
    @Test
    public void testConcurrentOperationsOnSmallDatapool() {

        System.out.print("create datapool");

        // Creates some dummy data.

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.startCount();
        createTestFiles(5000);
        stopwatch.stopCount();
        System.out.println(stopwatch);

        System.out.println();
        System.out.println();


        int maxThreads = 200;
        Collection<Thread> activeThreads = new ArrayList<Thread>();

        for (int a = 0; a < maxThreads; a++) {

            Runnable r = new FileManagerRequestThread(fileManager);
            Thread t = new Thread(r);
            activeThreads.add(t);
            t.start();
        }

        boolean loop = true;
        while (loop) {

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

            loop = !activeThreads.isEmpty();

            try {

                Thread.currentThread().sleep(100L);

            } catch (InterruptedException e) {

                // Ignore this exception.
            }
        }
    }

    /**
     * Tests other functionalities of a file manager in a concurrent scenario.
     */
    @Test
    public void testConcurrentOperationsOnSmallDatapool2() {

        System.out.print("create datapool");

        // Creates some dummy data.

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.startCount();
        createTestFiles(5000);
        stopwatch.stopCount();
        System.out.println(stopwatch);

        System.out.println();
        System.out.println();


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

                        Runnable r = new FileManagerRequestThread(fileManager);
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

        TestConcurrentFileManager test = new TestConcurrentFileManager();
        test.setUp();

        try {

            test.testConcurrentOperationsOnSmallDatapool2();

        } catch (Throwable e) {

            test.tearDown();
            throw new RuntimeException(e);
        }

        test.tearDown();

        System.out.println("done.");
    }

}

/**
 * This class implements a specific scenario for requests to a file manager.
 *
 * @author Kristian Kutin
 */
class FileManagerRequestThread implements Runnable {

    /**
     * The file separator for this operating system.
     */
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = SerializationBase.DRIVE + "\\Test\\Concurrent\\FileManager";

    /**
     * The line feed character for this operating system.
     */
    private static final String LINE_FEED = System.getProperty("line.separator");

    /**
     * A reference to a file manager.
     */
    private FileManager fileManager;

    /**
     * Constructs a thread.
     *
     * @param aFileManager
     *        a reference to a file manager
     */
    public FileManagerRequestThread(FileManager aFileManager) {

        if (aFileManager == null) {

            String message = "No file manager was specified!";
            throw new IllegalArgumentException(message);
        }

        fileManager = aFileManager;
    }

    /**
     * The method checks if a file exists for the specified identifier.
     *
     * @param anIdentifier
     *        a unique identifier
     *
     * @return <code>true</code> if a file exists, else <code>false</code>
     */
    private boolean existsFile(String anIdentifier) {

        Stopwatch stopwatch = new Stopwatch();

        StringConcatenator message = new StringConcatenator("Exists ", anIdentifier, "? ");

        stopwatch.startCount();
        boolean result = fileManager.existsFile(anIdentifier);
        stopwatch.stopCount();
        message.append(stopwatch, LINE_FEED, result, LINE_FEED);

        ConcurrencyHelper.println(message);

        return result;
    }

    /**
     * Returns the file which is associated with the specified identifier.
     *
     * @param anIdentifier
     *        a unique identifier
     *
     * @return a file or <code>null</code> if no such file exists
     */
    private File getFile(String anIdentifier) {

        Stopwatch stopwatch = new Stopwatch();

        StringConcatenator message = new StringConcatenator("Get ", anIdentifier, "? ");

        stopwatch.startCount();
        File file = fileManager.getFile(anIdentifier);
        stopwatch.stopCount();
        message.append(stopwatch, LINE_FEED, file, LINE_FEED);

        ConcurrencyHelper.println(message);

        return file;
    }

    /**
     * The method implements a scenario of requests.
     */
    public void run() {

        // The file which is associated with the below identifier is expected in
        // bucket 3.
        // The next idenntifier is not associated with any file.

        String[] identifiers = { "#z72_", "Hello World!" };
        boolean[] expectedResults = { true, false };
        int entries = Math.min(identifiers.length, expectedResults.length);


        // Test existance first

        for (int a = 0; a < entries; a++) {

            String identifier = identifiers[a];
            boolean expectedResult = expectedResults[a];

            boolean result = existsFile(identifier);
            assertEquals(expectedResult, result);
        }


        // Get the file which is associated with an identifier.

        for (int a = 0; a < entries; a++) {

            String identifier = identifiers[a];
            boolean expectedResult = expectedResults[a];

            File file = getFile(identifier);

            if (expectedResult) {

                assertTrue(file != null);

            } else {

                assertTrue(file == null);
            }
        }


        File[] files = {
            new File(SerializationBase.DRIVE + "\\HelloWorld.txt"),
            new File(BASEDIR + FILE_SEPARATOR + "bucket4" + FILE_SEPARATOR + "#z104_.dat"),
            new File(BASEDIR + FILE_SEPARATOR + "bucket4" + FILE_SEPARATOR + "#z35_.dat")
        };
        String[] expectedIdentifiers = { null, "#z104_", null };
        entries = Math.min(files.length, expectedIdentifiers.length);

        for (int a = 0; a < entries; a++) {

            File file = files[a];
            String expectedIdentifier = expectedIdentifiers[a];

            String identifier = fileManager.getUniqueIdentifier(file);

            StringConcatenator message =
                new StringConcatenator(file, " -> ", identifier, " (expected: ", expectedIdentifier, ")", LINE_FEED);
            ConcurrencyHelper.println(message);

            assertEquals(identifier, expectedIdentifier);
        }
    }

}

