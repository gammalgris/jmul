/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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


import java.io.File;

import java.util.ArrayList;
import java.util.Collection;

import jmul.io.CopyFileException;
import jmul.io.FileCopier;
import jmul.io.FileCopierImpl;

import jmul.test.classification.UnitTest;

import org.junit.After;
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
public class FileCopierInvalidParameters2Test {

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
     * The expected exception.
     */
    private Class expectedException;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param aFileCopier
     * @param aSourceFile
     * @param aDestinationFile
     * @param anExpectedException
     */
    public FileCopierInvalidParameters2Test(FileCopier aFileCopier, File aSourceFile, File aDestinationFile,
                                            Class anExpectedException) {

        fileCopier = aFileCopier;
        sourceFile = aSourceFile;
        destinationFile = aDestinationFile;
        expectedException = anExpectedException;
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {
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

        try {

            fileCopier.copyFile(sourceFile, destinationFile);

        } catch (Exception e) {

            assertTrue(expectedException.isAssignableFrom(e.getClass()));
            return;
        }

        {
            String message = "Copying (\"" + sourceFile + "\" -> \"" + destinationFile + "\") failed!";
            fail(message);
        }
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { new FileCopierImpl(), new File("testdata-io\\folder1"),
                                      new File("testdata-io\\copy.txt"), CopyFileException.class });
        parameters.add(new Object[] { new FileCopierImpl(), null, new File("testdata-io\\copy.txt"),
                                      IllegalArgumentException.class });
        parameters.add(new Object[] { new FileCopierImpl(), new File(""), new File("testdata-io\\copy.txt"),
                                      CopyFileException.class });
        parameters.add(new Object[] { new FileCopierImpl(), new File(" "), new File("testdata-io\\copy.txt"),
                                      CopyFileException.class });

        parameters.add(new Object[] { new FileCopierImpl(), new File("testdata-io\\file1"),
                                      new File("testdata-io\\folder1"), CopyFileException.class });
        parameters.add(new Object[] { new FileCopierImpl(), new File("testdata-io\\file1"), null,
                                      IllegalArgumentException.class });
        parameters.add(new Object[] { new FileCopierImpl(), new File("testdata-io\\file1"), new File(""),
                                      CopyFileException.class });
        parameters.add(new Object[] { new FileCopierImpl(), new File("testdata-io\\file1"), new File(" "),
                                      CopyFileException.class });

        return parameters;
    }

}
