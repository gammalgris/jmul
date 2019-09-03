/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package test.jmul.xml.validation;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.UnitTest;

import jmul.xml.validation.SchemaArchive;
import jmul.xml.validation.SchemaArchiveImpl;
import jmul.xml.validation.XmlValidationException;
import jmul.xml.validation.XmlValidationResult;
import jmul.xml.validation.XmlValidator;
import jmul.xml.validation.XmlValidatorImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.xml.sax.SAXException;


/**
 * This class contains tests to check if the XMl validator works correctly.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class XmlValidatorInvalidInputTest {

    /**
     * A constant value.
     */
    private static final Class NO_INNER_EXCEPTION = null;

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "testdata-xml", "testdata-xml/test-invalid.xml", XmlValidationException.class,
                                      SAXException.class });
        parameters.add(new Object[] { "testdata-xml", "testdata-xml/test-invalid2.xml", XmlValidationException.class,
                                      SAXException.class });
        parameters.add(new Object[] { "testdata-xml", "testdata-xml/test-invalid3.xml", XmlValidationException.class,
                                      SAXException.class });
        parameters.add(new Object[] { "testdata-xml", "testdata-xml/doesnt-exist.xml", XmlValidationException.class,
                                      IOException.class });
        parameters.add(new Object[] { "testdata-xml", "", IllegalArgumentException.class, NO_INNER_EXCEPTION });
        parameters.add(new Object[] { "testdata-xml", null, IllegalArgumentException.class, NO_INNER_EXCEPTION });

        return parameters;
    }

    /**
     * An XML validator.
     */
    private XmlValidator validator;

    /**
     * The path to a XML schema directory.
     */
    private String schemaDirectory;

    /**
     * The path of an XML file.
     */
    private String xmlFile;

    /**
     * The expected exception type.
     */
    private Class expectedExceptionType;

    /**
     * An optional inner exception.
     */
    private Class innerException;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aSchemaDirectory
     *        a directory path which contains schema files
     * @param anXmlFile
     *        an xml file
     * @param anExpectedExceptionType
     *        the expected exception type
     * @param anInnerException
     *        the expected inner exception type (optional, may be <code>null</code>)
     */
    public XmlValidatorInvalidInputTest(String aSchemaDirectory, String anXmlFile, Class anExpectedExceptionType,
                                        Class anInnerException) {

        schemaDirectory = aSchemaDirectory;
        xmlFile = anXmlFile;

        expectedExceptionType = anExpectedExceptionType;
        innerException = anInnerException;

        SchemaArchive schemaArchive = new SchemaArchiveImpl(aSchemaDirectory);
        validator = new XmlValidatorImpl(schemaArchive);
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
     * Tests if the schema check will throw the expected exception.
     */
    @Test
    public void testXML() {

        try {

            validator.validate(xmlFile);

        } catch (Exception e) {

            String message =
                "fileName: \"" + xmlFile + "\"; " + "An exception of type " + expectedExceptionType +
                " was expected but " + e.getClass() + " was thrown!";
            assertTrue(message, expectedExceptionType.isAssignableFrom(e.getClass()));

            if ((e instanceof XmlValidationException) && (innerException != NO_INNER_EXCEPTION)) {

                XmlValidationException f = (XmlValidationException) e;

                Collection<XmlValidationResult> results = f.getValidationResults();
                assertEquals(1, results.size());
                XmlValidationResult result = results.iterator().next();

                assertTrue(result.existErrorDetails());
                assertTrue(innerException.isAssignableFrom(result.getError().getClass()));
            }

            return;
        }

        {
            String message =
                "fileName: \"" + xmlFile + "\"; " + "An exception of type " + expectedExceptionType +
                " was expected but none was thrown!";
            fail(message);
        }
    }

}
