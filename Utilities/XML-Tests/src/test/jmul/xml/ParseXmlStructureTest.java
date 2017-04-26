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


import jmul.test.classification.UnitTest;
import jmul.test.exceptions.SetUpException;

import jmul.xml.SubelementList;
import jmul.xml.SubelementMap;
import jmul.xml.XmlMarkup;
import jmul.xml.XmlMarkupType;
import static jmul.xml.XmlMarkupType.XML_ATTRIBUTE;
import static jmul.xml.XmlMarkupType.XML_ELEMENT;
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


/**
 * The class contains tests to check an XML document structure.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ParseXmlStructureTest {

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

            document = reader.parseDocument("testdata-xml/valid.xml");

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
     * Test to check an XML document structure.
     */
    @Test
    public void testValidStructure() {

        Node root = document.getDocumentElement();


        assertTrue(XmlParserHelper.matchesXmlElement(root, TestStructureMarkups.PERSONS_ELEMENT));

        assertTrue(XmlParserHelper.existsXmlAttribute(root, TestStructureMarkups.COUNT_ATTTRIBUTE));
        assertEquals("1", XmlParserHelper.getXmlAttributeValue(root, TestStructureMarkups.COUNT_ATTTRIBUTE));

        SubelementMap subelementMapLevel1 = new SubelementMap(root);
        assertTrue(XmlParserHelper.hasXmlSubelements(subelementMapLevel1));

        SubelementList subelementListLevel1 = new SubelementList(root);
        assertTrue(XmlParserHelper.hasXmlSubelements(subelementListLevel1, TestStructureMarkups.PERSON_ELEMENT));
        assertEquals(1, subelementListLevel1.size());


        Node node = subelementMapLevel1.getSubelement(TestStructureMarkups.PERSON_ELEMENT);
        assertTrue(XmlParserHelper.matchesXmlElement(node, TestStructureMarkups.PERSON_ELEMENT));

        SubelementMap subelementMapLevel2 = new SubelementMap(node);
        assertTrue(XmlParserHelper.hasXmlSubelements(subelementMapLevel2));

        SubelementList subelementListLevel2 = new SubelementList(node);
        assertTrue(XmlParserHelper.hasXmlSubelements(subelementListLevel2, TestStructureMarkups.FIRST_NAME_ELEMENT));
        assertTrue(XmlParserHelper.hasXmlSubelements(subelementListLevel2, TestStructureMarkups.LAST_NAME_ELEMENT));
        assertEquals(2, subelementListLevel2.size());


        Node leaf;

        leaf = subelementMapLevel2.getSubelement(TestStructureMarkups.FIRST_NAME_ELEMENT);
        assertTrue(XmlParserHelper.matchesXmlElement(leaf, TestStructureMarkups.FIRST_NAME_ELEMENT));
        assertEquals("John", leaf.getTextContent());

        leaf = subelementMapLevel2.getSubelement(TestStructureMarkups.LAST_NAME_ELEMENT);
        assertTrue(XmlParserHelper.matchesXmlElement(leaf, TestStructureMarkups.LAST_NAME_ELEMENT));
        assertEquals("Doe", leaf.getTextContent());
    }

}


/**
 * This enumeration defines all markups that exist within the valid test
 * document.
 *
 * @author Kristian Kutin
 */
enum TestStructureMarkups implements XmlMarkup {


    COUNT_ATTTRIBUTE("count", XML_ATTRIBUTE),

    PERSONS_ELEMENT("persons", XML_ELEMENT),
    PERSON_ELEMENT("person", XML_ELEMENT),
    FIRST_NAME_ELEMENT("firstName", XML_ELEMENT),
    LAST_NAME_ELEMENT("lastName", XML_ELEMENT), ;


    /**
     * The markup's name.
     */
    private final String name;

    /**
     * The type of this enum element.
     */
    private final XmlMarkupType type;


    /**
     * Creates an enumeration element.
     *
     * @param aName
     *        a markup name
     * @param aType
     *        the type of this enumeration element
     */
    private TestStructureMarkups(String aName, XmlMarkupType aType) {

        name = aName;
        type = aType;
    }

    /**
     * Returns the name of the xml element or xml attribute which this entity
     * represents.
     *
     * @return a name
     */
    @Override
    public String getTagName() {

        return name;
    }

    /**
     * Checks if this entity represents an xml element.
     *
     * @return <code>true</code> if this entity represents an xml element, else
     *         <code>false</code>
     */
    @Override
    public boolean isXmlElement() {

        return XML_ELEMENT.equals(type);
    }

    /**
     * Checks if this entity represents an xml attribute.
     *
     * @return <code>true</code> if this entity represents an xml attribute,
     *         else <code>false</code>
     */
    @Override
    public boolean isXmlAttribute() {

        return XML_ATTRIBUTE.equals(type);
    }

    /**
     * Checks if the specified name matches the name of this markup.
     *
     * @param aName
     *
     * @return <code>true</code> if the specified name matches the name of this
     *         markup, else <code>false</code>
     */
    @Override
    public boolean equalsTagName(String aName) {

        return getTagName().equals(aName);
    }

    /**
     * Checks if the specified xml markup this markup.
     *
     * @param anXmlMarkup
     *
     * @return <code>true</code> if the specified xml markup matches this
     *         markup, else <code>false</code>
     */
    @Override
    public boolean equalsXmlMarkup(XmlMarkup anXmlMarkup) {

        return (this.isXmlAttribute() && anXmlMarkup.isXmlAttribute() && equalsTagName(anXmlMarkup.getTagName())) ||
               (this.isXmlElement() && anXmlMarkup.isXmlElement() && equalsTagName(anXmlMarkup.getTagName()));
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getTagName();
    }

}
