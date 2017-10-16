/*
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

import jmul.xml.SubelementList;
import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import static test.jmul.xml.TestMarkups.LEVEL1_ELEMENT;
import static test.jmul.xml.TestMarkups.SUB1_ELEMENT;


/**
 * The class contains tests to test a subelement list.
 *
 * @author Kristian Kutin
 */
public class SubelementListTest {

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

            document = reader.parseDocument("testdata-xml/test.xml");

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
     * Checks if the sublement list works correctly in determining sublement counts.
     */
    @Test
    public void testSubelementCount() {

        Node root = document.getDocumentElement();
        SubelementList subelements = new SubelementList(root);

        {
            String message = "The root element is supposed to have 3 subelements!";
            assertEquals(message, 3, subelements.size());
        }

        {
            String message = "The root element is supposed to have 2 subelements of type level1!";
            assertEquals(message, 2, subelements.getSubelements(LEVEL1_ELEMENT).size());
        }

        {
            String message = "The root element is supposed to have 1 subelement of type sub1!";
            assertEquals(message, 1, subelements.getSubelements(SUB1_ELEMENT).size());
        }
    }

}
