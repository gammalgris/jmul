/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.io;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.io.CopyFileException;
import jmul.io.FileCopier;
import jmul.io.FileCopierImpl;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests to check if copying a file works correctly.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class FileCopierValidParameters2Test {

    /**
     * The actual algorithm which does the copying.
     */
    private FileCopier fileCopier;

    /**
     * The path to a source file.
     */
    private File sourceFile;

    /**
     * The path to a source file.
     */
    private File destinationFile;

    /**
     * The expected content of the copied file.
     */
    private String expectedLine;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param aFileCopier
     * @param aSourceFile
     * @param aDestinationFile
     * @param anExpectedLine
     */
    public FileCopierValidParameters2Test(FileCopier aFileCopier, File aSourceFile, File aDestinationFile,
                                          String anExpectedLine) {

        super();

        fileCopier = aFileCopier;
        sourceFile = aSourceFile;
        destinationFile = aDestinationFile;
        expectedLine = anExpectedLine;
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        if (destinationFile.exists()) {

            destinationFile.delete();
        }
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Tests copying a file.
     */
    @Test
    public void testCopying() throws CopyFileException {

        assertFalse(destinationFile.exists());

        fileCopier.copyFile(sourceFile, destinationFile);

        assertTrue(destinationFile.exists());


        try {

            String actualLine = readFirstLine(destinationFile);
            assertEquals(expectedLine, actualLine);

        } catch (FileNotFoundException e) {

            fail();

        } catch (IOException e) {

            fail();
        }
    }

    /**
     * Reads the first line from the specified file.
     *
     * @param aFile
     *
     * @return the first line
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file doesn't exist or is no file.
     * @throws IOException
     *         is thrown when an error occcurs when reading from the file.
     */
    private static String readFirstLine(File aFile) throws FileNotFoundException, IOException {

        String firstLine = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(aFile))) {

            firstLine = reader.readLine();
        }

        return firstLine;
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { new FileCopierImpl(), new File("testdata-io\\file1.txt"),
                                      new File("testdata-io\\copy.txt"), "Hello World" });

        return parameters;
    }

}
