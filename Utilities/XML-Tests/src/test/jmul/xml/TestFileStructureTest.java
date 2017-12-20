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

package test.jmul.xml;


import jmul.test.exceptions.SetUpException;

import jmul.xml.XmlParserHelper;
import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import static test.jmul.xml.TestMarkups.INFO1_ATTRIBUTE;
import static test.jmul.xml.TestMarkups.INFO2_ATTRIBUTE;
import static test.jmul.xml.TestMarkups.INFO3_ATTRIBUTE;


/**
 * The class contains tests to verify the structure of an XML test file.
 *
 * @author Kristian Kutin
 */
public class TestFileStructureTest {

    /**
     * An XML document.
     */
    private Document document;

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        XmlDocumentReader reader = new XmlDocumentReaderImpl();

        try {

            document = reader.readFrom("testdata-xml/test.xml");

        } catch (Exception e) {

            throw new SetUpException(e);
        }
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        document = null;
    }

    /**
     * Checks if the XML test file has the expected attributes.
     */
    @Test
    public void testAttributes() {

        Node root = document.getDocumentElement();

        assertTrue(XmlParserHelper.existsXmlAttribute(root, INFO1_ATTRIBUTE));
        assertEquals("1", XmlParserHelper.getXmlAttributeValue(root, INFO1_ATTRIBUTE));

        assertTrue(XmlParserHelper.existsXmlAttribute(root, INFO2_ATTRIBUTE));
        assertEquals("2", XmlParserHelper.getXmlAttributeValue(root, INFO2_ATTRIBUTE));

        assertTrue(XmlParserHelper.existsXmlAttribute(root, INFO3_ATTRIBUTE));
        assertEquals("3", XmlParserHelper.getXmlAttributeValue(root, INFO3_ATTRIBUTE));
    }

}
