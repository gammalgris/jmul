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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jmul.io.FileHelper;

import jmul.persistence.file.FileManager;
import jmul.persistence.file.FileManagerImpl;
import jmul.persistence.id.ID;
import jmul.persistence.id.IDGenerator;
import jmul.persistence.id.StringIDGenerator;

import static jmul.string.Constants.FILE_SEPARATOR;

import jmul.test.classification.ModuleTest;

import jmul.time.Stopwatch;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * A collection of test cases to test the functionalities of a file manager.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class TestFileManager {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ".\\Test\\Sequential\\FileManager";

    /**
     * A file manager.
     */
    private FileManager fileManager;

    /**
     * Preparations before a test.
     */
    @Before
    public void setUp() {

        File baseDirectory = new File(BASEDIR);

        if (baseDirectory.exists()) {

            FileHelper.delete(baseDirectory);
        }

        baseDirectory.mkdirs();

        fileManager = new FileManagerImpl(BASEDIR);
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDown() {

        if (fileManager != null) {

            fileManager.shutdown();
        }
    }

    /**
     * Test a file manager by retrieving a filename and creating a single file.
     */
    @Test
    public void testSingleFileCreation() {

        File file = fileManager.newFile("World");
        assertTrue(file != null);

        try {

            file.createNewFile();

        } catch (IOException e) {

            // Ignore
        }
    }

    /**
     * The test method calls an ID generator and checks if there is a collision
     * among the generated IDs.
     */
    @Test
    public void testIDGenerator() {

        String idFile = BASEDIR + FILE_SEPARATOR + "idtest";
        IDGenerator generator = StringIDGenerator.getAlternativeGenerator(idFile);

        Collection<String> usedIDs = new ArrayList<String>();
        int collisions = 0;

        long idGeneration = 0;
        long start = System.currentTimeMillis();

        for (int a = 0; a <= 100000; a++) {

            long startGeneration = System.currentTimeMillis();
            ID id = generator.generateID();
            long endGeneration = System.currentTimeMillis();

            idGeneration += endGeneration - startGeneration;
            //System.out.println(id);

            if (usedIDs.contains(id.toString())) {

                //System.out.print(" -> collision");
                collisions++;

            } else {

                usedIDs.add(id.toString());
            }


            int rest = a % 10000;
            if ((a > 0) && (rest == 0)) {

                System.out.println((a / 1000) + "k");
            }
        }

        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("Total execution time: " + duration + " / Total id generation time:" + idGeneration +
                           " / collisions: " + collisions);
    }

    /**
     * Test a file manager by creating several thousands of files and thus force
     * the file manager to add new folders.
     */
    @Test
    public void testFolderCreation() {

        String idFile = BASEDIR + FILE_SEPARATOR + "id";
        IDGenerator generator = StringIDGenerator.getAlternativeGenerator(idFile);

        Map<File, String> createdFiles = new HashMap<File, String>();
        Map<String, Collection<String>> collisions = new HashMap<String, Collection<String>>();

        for (int a = 0; a < 10001; a++) {

            ID id = generator.generateID();
            File newFile = fileManager.newFile(id.toString());
            assertTrue(newFile != null);

            if (createdFiles.containsKey(newFile)) {

                String original = createdFiles.get(newFile);

                Collection<String> subset = collisions.get(original);
                if (subset == null) {

                    subset = new ArrayList<String>();
                    collisions.put(original, subset);
                }

                subset.add(newFile.getAbsolutePath());

            } else {

                createdFiles.put(newFile, newFile.getAbsolutePath());

                try {

                    newFile.createNewFile();

                } catch (IOException e) {

                    // Ignore
                }
            }
        }

        System.out.println("collisions: " + collisions.size());

        for (String original : collisions.keySet()) {

            System.out.print(original + " # ");

            for (String copy : collisions.get(original)) {

                System.out.print(copy + " ");
            }

            System.out.println();
        }

        assertTrue(collisions.size() == 0);
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
     * The method checks if a file exists for the specified identifier.
     *
     * @param anIdentifier
     *        a unique identifier
     *
     * @return <code>true</code> if a file exists, else <code>false</code>
     */
    private boolean existsFile(String anIdentifier) {

        Stopwatch stopwatch = new Stopwatch();

        System.out.print("Exists " + anIdentifier + "? ");

        stopwatch.startCount();
        boolean result = fileManager.existsFile(anIdentifier);
        stopwatch.stopCount();
        System.out.println(stopwatch);

        System.out.println(String.valueOf(result));
        System.out.println();

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

        System.out.print("Get " + anIdentifier + ". ");

        stopwatch.startCount();
        File file = fileManager.getFile(anIdentifier);
        stopwatch.stopCount();
        System.out.println(stopwatch);

        System.out.println(String.valueOf(file));
        System.out.println();

        return file;
    }

    /**
     * Tests other functionalities of a file manager.
     */
    @Test
    public void testOperationsOnSmallDatapool() {

        System.out.print("create datapool");

        // Creates some dummy data.

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.startCount();
        createTestFiles(5000);
        stopwatch.stopCount();
        System.out.println(stopwatch);

        System.out.println();
        System.out.println();


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
            new File(".\\HelloWorld.txt"),
            new File(BASEDIR + FILE_SEPARATOR + "bucket4" + FILE_SEPARATOR + "#z104_.dat"),
            new File(BASEDIR + FILE_SEPARATOR + "bucket4" + FILE_SEPARATOR + "#z35_.dat")
        };
        String[] expectedIdentifiers = { null, "#z104_", null };
        entries = Math.min(files.length, expectedIdentifiers.length);

        for (int a = 0; a < entries; a++) {

            File file = files[a];
            String expectedIdentifier = expectedIdentifiers[a];

            String identifier = fileManager.getUniqueIdentifier(file);

            System.out.println(file + " -> " + identifier + " (expected: " + expectedIdentifier + ")");
            assertEquals(identifier, expectedIdentifier);
        }
    }

    /**
     * Tests other functionalities of a file manager.
     */
    public void testOperationsOnLargeDatapool() {

        System.out.print("create datapool");

        // Creates some dummy data.

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.startCount();
        createTestFiles(250000);
        stopwatch.stopCount();
        System.out.println(stopwatch);

        System.out.println();
        System.out.println();


        System.out.println("start tests");
        System.out.println();


        String[] identifiers = {
            "#z680_", "Hello World!", "_z1784_", "_zz9220_", "#z4551_", "File not found", "#z3859_", "_zz3874_",
            "_nz3867_"
        };
        boolean[] expectedResults = { true, false, true, true, true, false, true, true, true };
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
    }

}
