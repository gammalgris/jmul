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


import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.UnitTest;

import jmul.text.reader.TextDocumentReader;
import jmul.text.reader.TextDocumentReaderImpl;

import org.junit.After;
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
public class ReadTextFileWithInvalidParametersTest {

    /**
     * A reader instance.
     */
    private TextDocumentReader reader;

    /**
     * An input parameter.
     */
    private String fileName;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aFileName
     */
    public ReadTextFileWithInvalidParametersTest(String aFileName) {

        fileName = aFileName;
    }

    @Before
    public void setUp() {

        reader = new TextDocumentReaderImpl();
    }

    @After
    public void tearDown() {

        reader = null;
    }

    /**
     * Tests the instantiation of a reader with valid input parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReadDocument() throws IOException {

        reader.readFrom(fileName);
    }

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null });
        parameters.add(new Object[] { "" });
        parameters.add(new Object[] { "  " });

        return parameters;
    }

}
