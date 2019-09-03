/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package test.jmul.text.reader;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.UnitTest;

import jmul.text.reader.TextDocumentReader;
import jmul.text.reader.TextDocumentReaderImpl;

import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the instantiation of a reader with various valid input parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ReadTextFileWithInvalidParameters2Test {

    /**
     * A reader instance.
     */
    private TextDocumentReader reader;

    /**
     * A file path.
     */
    private File file;

    /**
     * The expected exception type.
     */
    private Class expectedException;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aFileName
     *        a file path
     * @param anExpectedException
     *        the expected exception type
     */
    public ReadTextFileWithInvalidParameters2Test(File aFile, Class anExpectedException) {

        file = aFile;
        expectedException = anExpectedException;
    }

    /**
     * Steps before the actual test.
     */
    @Before
    public void setUp() {

        reader = new TextDocumentReaderImpl();
    }

    /**
     * Steps after the actual test.
     */
    @After
    public void tearDown() {

        reader = null;
    }

    /**
     * Tests the instantiation of a reader with invalid input parameters.
     */
    @Test
    public void testReadDocument() throws IOException {

        try {

            reader.readFrom(file);

        } catch (Exception e) {

            if (!expectedException.isAssignableFrom(e.getClass())) {

                fail(e.getMessage());
            }

            return;
        }

        fail("An exception is excepted but no exception was thrown!");
    }

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, IllegalArgumentException.class });
        parameters.add(new Object[] { new File("testdata-text"), FileNotFoundException.class });

        return parameters;
    }

}
