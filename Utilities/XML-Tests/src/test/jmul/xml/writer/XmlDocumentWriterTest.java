/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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

package test.jmul.xml.writer;


import java.io.IOException;

import java.util.List;

import jmul.test.classification.UnitTest;

import jmul.xml.XmlHelper;
import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;
import jmul.xml.writer.XmlDocumentWriter;
import jmul.xml.writer.XmlDocumentWriterImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import org.xml.sax.SAXException;

import static test.jmul.xml.writer.TestFileMarkups.ROOT_NODE;
import static test.jmul.xml.writer.TestFileMarkups.TEST_ATTRIBUTE;
import static test.jmul.xml.writer.TestFileMarkups.TEST_NODE;


@UnitTest
public class XmlDocumentWriterTest {

    private static String TEST_LINE_1;
    private static String TEST_LINE_2;
    private static String TEST_LINE_3;
    private static String TEST_LINE_4;
    private static String TEST_LINE_5;

    static {

        TEST_LINE_1 = "\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u00df";
        TEST_LINE_2 = "a < b; b > c";
        TEST_LINE_3 = "\"Hello World!\"";
        TEST_LINE_4 = "Hello\r\nWorld!";
        TEST_LINE_5 = "--test--";
    }

    private XmlDocumentWriter writer;

    private XmlDocumentReader reader;

    @Before
    public void setUp() {

        writer = new XmlDocumentWriterImpl();
        reader = new XmlDocumentReaderImpl();
    }

    @After
    public void tearDown() {

        writer = null;
        reader = null;
    }

    private Document createTestDocument() {

        Document document = XmlHelper.newXmlDocument();


        Node rootNode = XmlHelper.createXmlElement(document, ROOT_NODE);
        document.appendChild(rootNode);


        Node test1Node = XmlHelper.createXmlElement(document, TEST_NODE);
        rootNode.appendChild(test1Node);

        XmlHelper.createXmlAttribute(document, (Element) test1Node, TEST_ATTRIBUTE, TEST_LINE_1);


        Node test2Node = XmlHelper.createXmlElement(document, TEST_NODE);
        rootNode.appendChild(test2Node);

        XmlHelper.createXmlAttribute(document, (Element) test2Node, TEST_ATTRIBUTE, TEST_LINE_2);


        Node test3Node = XmlHelper.createXmlElement(document, TEST_NODE);
        rootNode.appendChild(test3Node);

        XmlHelper.createXmlAttribute(document, (Element) test3Node, TEST_ATTRIBUTE, TEST_LINE_3);


        Node test4Node = XmlHelper.createXmlElement(document, TEST_NODE);
        rootNode.appendChild(test4Node);

        XmlHelper.createXmlAttribute(document, (Element) test4Node, TEST_ATTRIBUTE, TEST_LINE_4);


        Node test5Node = XmlHelper.createXmlElement(document, TEST_NODE);
        rootNode.appendChild(test5Node);

        XmlHelper.createXmlAttribute(document, (Element) test5Node, TEST_ATTRIBUTE, TEST_LINE_5);


        Node test6Node = XmlHelper.createXmlElement(document, TEST_NODE);
        rootNode.appendChild(test6Node);

        Text textNode1 = document.createTextNode(TEST_LINE_1);
        test6Node.appendChild(textNode1);


        Node test7Node = XmlHelper.createXmlElement(document, TEST_NODE);
        rootNode.appendChild(test7Node);

        Text textNode2 = document.createTextNode(TEST_LINE_2);
        test7Node.appendChild(textNode2);


        Node test8Node = XmlHelper.createXmlElement(document, TEST_NODE);
        rootNode.appendChild(test8Node);

        Text textNode3 = document.createTextNode(TEST_LINE_3);
        test8Node.appendChild(textNode3);


        Node test9Node = XmlHelper.createXmlElement(document, TEST_NODE);
        rootNode.appendChild(test9Node);

        Text textNode4 = document.createTextNode(TEST_LINE_4);
        test9Node.appendChild(textNode4);


        Node test10Node = XmlHelper.createXmlElement(document, TEST_NODE);
        rootNode.appendChild(test10Node);

        Text textNode5 = document.createTextNode(TEST_LINE_5);
        test10Node.appendChild(textNode5);


        return document;
    }

    private void checkDocument(Document aDocument) {

        Node rootNode = aDocument.getDocumentElement();

        assertEquals(false, rootNode.hasAttributes());
        assertEquals(true, rootNode.hasChildNodes());

        List<Node> subelementNodes = XmlHelper.extractChildNodes(rootNode, Node.ELEMENT_NODE);

        for (int i = 0; i < subelementNodes.size(); i++) {

            Node subelement = subelementNodes.get(i);

            if (i == 0) {

                assertEquals(true, subelement.hasAttributes());
                assertEquals(false, subelement.hasChildNodes());
                assertEquals(1, subelement.getAttributes().getLength());

                Node attribute = subelement.getAttributes().item(0);
                assertEquals(TEST_LINE_1, attribute.getNodeValue());
            }

            if (i == 1) {

                assertEquals(true, subelement.hasAttributes());
                assertEquals(false, subelement.hasChildNodes());
                assertEquals(1, subelement.getAttributes().getLength());

                Node attribute = subelement.getAttributes().item(0);
                assertEquals(TEST_LINE_2, attribute.getNodeValue());
            }

            if (i == 2) {

                assertEquals(true, subelement.hasAttributes());
                assertEquals(false, subelement.hasChildNodes());
                assertEquals(1, subelement.getAttributes().getLength());

                Node attribute = subelement.getAttributes().item(0);
                assertEquals(TEST_LINE_3, attribute.getNodeValue());
            }

            if (i == 3) {

                assertEquals(true, subelement.hasAttributes());
                assertEquals(false, subelement.hasChildNodes());
                assertEquals(1, subelement.getAttributes().getLength());

                Node attribute = subelement.getAttributes().item(0);
                assertEquals(TEST_LINE_4, attribute.getNodeValue());
            }

            if (i == 4) {

                assertEquals(true, subelement.hasAttributes());
                assertEquals(false, subelement.hasChildNodes());
                assertEquals(1, subelement.getAttributes().getLength());

                Node attribute = subelement.getAttributes().item(0);
                assertEquals(TEST_LINE_5, attribute.getNodeValue());
            }

            if (i == 5) {

                assertEquals(false, subelement.hasAttributes());
                assertEquals(true, subelement.hasChildNodes());
                assertEquals(0, subelement.getAttributes().getLength());

                assertEquals(TEST_LINE_1, subelement.getTextContent());
            }

            if (i == 6) {

                assertEquals(false, subelement.hasAttributes());
                assertEquals(true, subelement.hasChildNodes());
                assertEquals(0, subelement.getAttributes().getLength());

                assertEquals(TEST_LINE_2, subelement.getTextContent());
            }

            if (i == 7) {

                assertEquals(false, subelement.hasAttributes());
                assertEquals(true, subelement.hasChildNodes());
                assertEquals(0, subelement.getAttributes().getLength());

                assertEquals(TEST_LINE_3, subelement.getTextContent());
            }

            if (i == 8) {

                assertEquals(false, subelement.hasAttributes());
                assertEquals(true, subelement.hasChildNodes());
                assertEquals(0, subelement.getAttributes().getLength());

                assertEquals(TEST_LINE_4, subelement.getTextContent());
            }

            if (i == 9) {

                assertEquals(false, subelement.hasAttributes());
                assertEquals(true, subelement.hasChildNodes());
                assertEquals(0, subelement.getAttributes().getLength());

                assertEquals(TEST_LINE_5, subelement.getTextContent());
            }
        }

    }

    @Test
    public void testWriteDocument() {

        Document document = createTestDocument();

        String filename = "./testdata-xml/test-output.xml";

        try {

            writer.writeTo(filename, document);

        } catch (IOException e) {

            fail(e.getMessage());
        }


        Document document4Verification = null;

        try {

            document4Verification = reader.readFrom(filename);

        } catch (IOException | SAXException e) {

            fail(e.getMessage());
        }


        checkDocument(document4Verification);
    }

}


