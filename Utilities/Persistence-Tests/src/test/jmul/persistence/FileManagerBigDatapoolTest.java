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


import jmul.persistence.file.FileManager;
import jmul.persistence.id.IDGenerator;

import static jmul.string.Constants.FILE_SEPARATOR;

import jmul.test.classification.LongRunning;
import jmul.test.classification.ModuleTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains tests to check file mangaers with existing files.
 *
 * @author Kristian Kutin
 */
@LongRunning
@ModuleTest
public class FileManagerBigDatapoolTest extends FileManagerTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ".\\Test\\File-Manager4";

    /**
     * The file where the generated IDs are persisted.
     */
    private static final String ID_FILE = BASEDIR + FILE_SEPARATOR + "idtest";

    /**
     * The number of files with which the file manager will be prepared before
     * running the actual test.
     */
    private static final int FILES_THRESHOLD = 250000;

    /**
     * A file manager.
     */
    private FileManager fileManager;

    /**
     *  An ID generator.
     */
    private IDGenerator generator;

    /**
     * Preparations before a test.
     */
    @Before
    public void setUp() {

        initBaseDirectory(BASEDIR);
        fileManager = initFileManager(BASEDIR);
        generator = initIDGenerator(ID_FILE);

        createTestFiles(fileManager, generator, FILES_THRESHOLD);
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDown() {

        fileManager.shutdown();
        fileManager = null;

        generator = null;
    }

    /**
     * Tests searching for a file within an existing data pool.
     */
    @Test
    public void testBigDatapool() {

        String[] identifiers = {
            "#z680_", "Hello World!", "_z1784_", "_zz9220_", "#z4551_", "File not found", "#z3859_", "_zz3874_",
            "_nz3867_"
        };
        boolean[] expectedResults = { true, false, true, true, true, false, true, true, true };
        assertEquals("Testdata is incorrect!", identifiers.length, expectedResults.length);

        for (int a = 0; a < identifiers.length; a++) {

            String identifier = identifiers[a];
            boolean expectedResult = expectedResults[a];

            boolean result = fileManager.existsFile(identifier);

            checkExistence(identifier, expectedResult, result);
        }
    }

}
