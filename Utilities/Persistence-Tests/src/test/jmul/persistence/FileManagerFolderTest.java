/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

import static jmul.math.Constants.SECOND;

import jmul.misc.id.ID;
import jmul.misc.id.IDGenerator;

import jmul.persistence.file.FileManager;

import static jmul.string.Constants.FILE_SEPARATOR;
import static jmul.string.Constants.fileSeparator2Regex;

import jmul.test.classification.ModuleTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains tests to check the creation of subfolders via a file manager.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class FileManagerFolderTest extends FileManagerTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ".\\Test\\File-Manager2";

    /**
     * The file where the generated IDs are persisted.
     */
    private static final String ID_FILE = BASEDIR + FILE_SEPARATOR + "idtest";

    /**
     * A time threshold for the expected duration.
     */
    private static final long MAX_DURATION = SECOND * 10L;

    /**
     * A file manager.
     */
    private FileManager fileManager;

    /**
     * Preparations before a test.
     */
    @Before
    public void setUp() {

        initBaseDirectory(BASEDIR);
        fileManager = initFileManager(BASEDIR);
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDown() {

        fileManager.shutdown();
        fileManager = null;
    }

    /**
     * Tests if a file manager creates a new subfolder when enough
     */
    @Test
    public void testFolderCreation() {

        IDGenerator generator = initIDGenerator(ID_FILE);

        String previousFile = null;
        String currentFile = null;

        long startTime = System.currentTimeMillis();

        while (true) {

            currentFile = createFile(fileManager, generator);

            if (previousFile != null) {

                if (!isSameSubfolder(previousFile, currentFile)) {

                    break;
                }
            }

            previousFile = currentFile;

            checkDuration(startTime);
        }
    }

    /**
     * Checks that the test is not running longer than expected.
     *
     * @param aStartTime
     */
    private void checkDuration(long aStartTime) {

        long duration = System.currentTimeMillis() - aStartTime;

        String message = "The test has exceeded a duration of " + MAX_DURATION + " ms!";
        assertFalse(message, duration > MAX_DURATION);
    }

    /**
     * Creates a new File and returns the path of the created file.
     *
     * @param aFileManager
     * @param aGenerator
     *
     * @return a file path
     */
    private String createFile(FileManager aFileManager, IDGenerator aGenerator) {

        ID id = aGenerator.generateID();
        File newFile = aFileManager.newFile(id.toString());


        try {

            boolean result = newFile.createNewFile();
            assertTrue(result);

        } catch (IOException e) {

            fail(e.getMessage());
        }


        String path = null;

        try {

            path = newFile.getCanonicalPath();

        } catch (IOException e) {

            fail(e.getMessage());
        }


        return path;
    }

    /**
     * Checks if both files are within the same subfolder (i.e. second to last
     * entry).
     *
     * @param firstPath
     * @param secondPath
     *
     * @return <code>true</code> if both files are within the same subfolder,
     *         else <code>false</code>
     */
    private boolean isSameSubfolder(String firstPath, String secondPath) {

        String fileSeparator = fileSeparator2Regex();
        String[] array1 = firstPath.split(fileSeparator);
        String[] array2 = secondPath.split(fileSeparator);
        return isSameSubfolder(array1, array2);
    }

    /**
     * Checks if both files are within the same subfolder (i.e. second to last
     * entry).
     *
     * @param firstPath
     * @param secondPath
     *
     * @return <code>true</code> if both files are within the same subfolder,
     *         else <code>false</code>
     */
    private boolean isSameSubfolder(String[] firstPath, String[] secondPath) {

        assertTrue(firstPath.length > 2);
        assertEquals(firstPath.length, secondPath.length);

        int secondToLastIndex = firstPath.length - 2;

        return firstPath[secondToLastIndex].equals(secondPath[secondToLastIndex]);
    }

}
