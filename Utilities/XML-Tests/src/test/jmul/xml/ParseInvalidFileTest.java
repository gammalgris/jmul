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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.xml;


import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.UnitTest;

import jmul.xml.reader.EmptyFileException;
import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.xml.sax.SAXParseException;


/**
 * This class tests the parsing of invalid XML files.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ParseInvalidFileTest {

    /**
     * A document reader.
     */
    private XmlDocumentReader reader;

    /**
     * A file name.
     */
    private String fileName;

    /**
     * The type of exception that is expected.
     */
    private Class expectedExceptionType;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aFileName
     *        a file name
     * @param anExpectedException
     *        the expected exception type
     */
    public ParseInvalidFileTest(String aFileName, Class anExpectedExceptionType) {

        fileName = aFileName;
        expectedExceptionType = anExpectedExceptionType;
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        reader = new XmlDocumentReaderImpl();
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        reader = null;
    }

    /**
     * Tests the parsing of an invalid XML file.
     */
    @Test
    public void testParseInvalidFile() {

        try {

            reader.readFrom(fileName);

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();
            String message =
                "fileName: \"" + fileName + "\"; " + "expected exception type: " + expectedExceptionType +
                "; actual exception type: " + actualExceptionType + "; error message=\"" + e.getMessage() + "\"";
            assertTrue(message, expectedExceptionType.isAssignableFrom(actualExceptionType));
            return;
        }

        {
            String message =
                "fileName: \"" + fileName + "\"; " + "expected exception type: " + expectedExceptionType +
                "; actual exception type: none";
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

        parameters.add(new Object[] { "testdata-xml/doesnt-exist.xml", FileNotFoundException.class });
        parameters.add(new Object[] { "testdata-xml/empty.xml", EmptyFileException.class });
        parameters.add(new Object[] { "testdata-xml/empty2.xml", SAXParseException.class });
        parameters.add(new Object[] { "testdata-xml/header-only.xml", SAXParseException.class });
        parameters.add(new Object[] { "testdata-xml/invalid-root.xml", SAXParseException.class });
        parameters.add(new Object[] { "testdata-xml/invalid-root2.xml", SAXParseException.class });
        parameters.add(new Object[] { "testdata-xml/invalid-root3.xml", SAXParseException.class });
        parameters.add(new Object[] { "testdata-xml/multi-root.xml", SAXParseException.class });

        return parameters;
    }

}
