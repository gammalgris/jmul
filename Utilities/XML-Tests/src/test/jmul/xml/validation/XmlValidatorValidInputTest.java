/*
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


import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.UnitTest;

import jmul.xml.validation.SchemaArchive;
import jmul.xml.validation.SchemaArchiveImpl;
import jmul.xml.validation.XmlValidator;
import jmul.xml.validation.XmlValidatorImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains tests to check if the XMl validator works correctly.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class XmlValidatorValidInputTest {

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "testdata-xml", "testdata-xml/test-valid.xml" });
        parameters.add(new Object[] { "testdata-xml", "testdata-xml/test-valid2.xml" });

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
     * Creates a new test according to the specified parameters.
     *
     * @param aSchemaDirectory
     * @param anXmlFile
     */
    public XmlValidatorValidInputTest(String aSchemaDirectory, String anXmlFile) {

        schemaDirectory = aSchemaDirectory;
        xmlFile = anXmlFile;

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
     * Tests if a XML file adheres to an XML schema.
     */
    @Test
    public void testXML() {

        validator.validate(xmlFile);
    }

}
