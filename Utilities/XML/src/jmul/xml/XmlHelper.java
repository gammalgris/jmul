/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package jmul.xml;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import jmul.metainfo.annotations.Modified;

import jmul.misc.exceptions.InstantiationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;


/**
 * A utility class.
 *
 * @author Kristian Kutin
 */
public final class XmlHelper {

    /**
     * A string constant.
     */
    private static final String TARGET_STYLESHEET = "xml-stylesheet";

    /**
     * A string constant.
     */
    private static final String LOCATION_PLACEHOLDER = "{stylesheet.location}";

    /**
     * A string constant.
     */
    private static final String DATA_STYLESHEET = "type=\"text/xsl\" href=\"" + LOCATION_PLACEHOLDER + "\"";

    /**
     * The default constructor.
     */
    private XmlHelper() {
    }

    /**
     * Creates an xml attributes.
     *
     * @param aDocument
     *        a reference to an existing xml document
     * @param anAttributeName
     *        the name of the xml attribute
     * @param anAttributeValue
     *        the value of the xml attribute
     *
     * @return an xml attribute
     */
    private static Attr createXmlAttribute(Document aDocument, String anAttributeName, String anAttributeValue) {

        Attr attribute = aDocument.createAttribute(anAttributeName);
        attribute.setNodeValue(anAttributeValue);

        return attribute;
    }

    /**
     * Creates an xml attributes.
     *
     * @param aDocument
     *        a reference to an existing xml document
     * @param anAttribute
     *        the xml attribute
     * @param anAttributeValue
     *        the value of the xml attribute
     *
     * @return an xml attribute
     */
    public static Attr createXmlAttribute(Document aDocument, XmlMarkup anAttribute, String anAttributeValue) {

        if (anAttribute.isXmlElement()) {

            String message = "An XML element was specified, but an XML attribute is expected!";
            throw new IllegalArgumentException(message);
        }

        return createXmlAttribute(aDocument, anAttribute.getTagName(), anAttributeValue);
    }

    /**
     * Creates an xml attribute according to the specified parameters and adds it
     * to the specified xml element.
     *
     * @param aDocument
     *        a reference to an existing xml document
     * @param anElement
     *        the xml element which gets the new attribut
     * @param anAttributeName
     *        the name of the xml attribute
     * @param anAttributeValue
     *        the value of the xml attribute
     *
     * @return an xml attribute
     */
    public static Attr createXmlAttribute(Document aDocument, Element anElement, String anAttributeName,
                                          String anAttributeValue) {

        Attr attribute = createXmlAttribute(aDocument, anAttributeName, anAttributeValue);
        anElement.setAttributeNode(attribute);

        return attribute;
    }

    /**
     * Creates an xml attribute according to the specified parameters and adds it
     * to the specified xml element.
     *
     * @param aDocument
     *        a reference to an existing xml document
     * @param anElement
     *        the xml element which gets the new attribut
     * @param anAttribute
     *        the xml attribute
     * @param anAttributeValue
     *        the value of the xml attribute
     *
     * @return an xml attribute
     */
    public static Attr createXmlAttribute(Document aDocument, Element anElement, XmlMarkup anAttribute,
                                          String anAttributeValue) {

        Attr attribute = createXmlAttribute(aDocument, anAttribute, anAttributeValue);
        anElement.setAttributeNode(attribute);

        return attribute;
    }

    /**
     * Creates an xml element.
     *
     * @param aDocument
     *        a reference to an existing xml document
     * @param anElementName
     *        the name of the xml element
     *
     * @return an xml element
     */
    private static Element createXmlElement(Document aDocument, String anElementName) {

        return aDocument.createElement(anElementName);
    }

    /**
     * Creates an xml element.
     *
     * @param aDocument
     *        a reference to an existing xml document
     * @param anElement
     *        a xml element
     *
     * @return a xml element
     */
    public static Element createXmlElement(Document aDocument, XmlMarkup anElement) {

        if (anElement.isXmlAttribute()) {

            String message = "An XML attribute was specified, but an XML element is expected!";
            throw new IllegalArgumentException(message);
        }

        return createXmlElement(aDocument, anElement.getTagName());
    }

    /**
     * Creates a new XML Document.
     *
     * @return a new XML document
     */
    public static Document newXmlDocument() {

        try {

            return XmlResources.newDocument();

        } catch (ParserConfigurationException e) {

            String message = "Couldn't create a new XML document!";
            throw new InstantiationException(message, e);
        }
    }

    /**
     * Clones an xml element with all its attributes and text value, but without
     * subelements.
     *
     * @param newDocument
     *        a new xml document
     * @param theOriginalElement
     *        a reference to an existing xml document
     *
     * @return a clone
     */
    public static Node cloneXmlElement(@Modified Document newDocument, Node theOriginalElement) {

        String nodeName = theOriginalElement.getNodeName();
        String textContent = theOriginalElement.getTextContent();

        Node newElement = createXmlElement(newDocument, nodeName);
        newElement.setTextContent(textContent);

        NamedNodeMap attributes = theOriginalElement.getAttributes();
        for (int a = 0; a < attributes.getLength(); a++) {

            Node originalAttribute = attributes.item(a);
            Node newAttribute = cloneXmlAttribute(newDocument, originalAttribute);
            newElement.appendChild(newAttribute);
        }

        return newElement;
    }

    /**
     * Clones an xml attribute.
     *
     * @param newDocument
     *        a new xml document
     * @param theOriginalAttribute
     *        the original xml attribute
     *
     * @return a clone
     */
    public static Node cloneXmlAttribute(@Modified Document newDocument, Node theOriginalAttribute) {

        String attributeName = theOriginalAttribute.getNodeName();
        String attributeValue = theOriginalAttribute.getNodeValue();

        return createXmlAttribute(newDocument, attributeName, attributeValue);
    }

    /**
     * Adds stylesheet informations to an xml document. See
     * <a href='http://stackoverflow.com/questions/2651647/add-xml-stylesheet-and-get-standalone-yes'>Stack Overflow</a>.
     *
     * @param aDocument
     *        a reference to an existing xml document
     * @param aFilename
     *        the path of the stylesheet
     */
    public static void addStylesheet(Document aDocument, String aFilename) {

        aDocument.setXmlStandalone(true);

        Element root = aDocument.getDocumentElement();

        String data = DATA_STYLESHEET.replace(LOCATION_PLACEHOLDER, aFilename);
        ProcessingInstruction processingInstruction = aDocument.createProcessingInstruction(TARGET_STYLESHEET, data);

        aDocument.insertBefore(processingInstruction, root);
    }

    /**
     * Copies a document's content to another document (i.e. the root node is
     * adopted by the new document and inserted there). The new document should
     * be empty.
     *
     * @param oldDocument
     *        a reference to an existing xml document
     * @param newDocument
     *        a new xml document
     *
     * @return the new containing document
     */
    public static Document copyDocument(Document oldDocument, Document newDocument) {

        Node root = oldDocument.getDocumentElement();

        Node adoptedRoot = newDocument.adoptNode(root);

        newDocument.appendChild(adoptedRoot);

        return newDocument;
    }

    /**
     * The method extractChildElementNodes extracts all child elements of a
     * node.
     *
     * @param aParentNode
     *        a parent node
     *
     * @return a list of child elements
     */
    public static List<Node> extractChildElementNodes(Node aParentNode) {

        return extractChildNodes(aParentNode, Node.ELEMENT_NODE);
    }

    /**
     * The method extractChildNodes extracts child nodes of a specified type.
     *
     * @param aParentNode
     *        a parent node
     * @param aRequiredType
     *        a required node type
     *
     * @return a list of child nodes
     */
    public static List<Node> extractChildNodes(Node aParentNode, short aRequiredType) {

        List<Node> elementNodes = new ArrayList<>();

        NodeList childNodeList = aParentNode.getChildNodes();
        for (int a = 0; a < childNodeList.getLength(); a++) {
            Node node = childNodeList.item(a);
            short nodeType = node.getNodeType();
            if (nodeType == aRequiredType) {
                elementNodes.add(node);
            }
        }

        return elementNodes;
    }

    /**
     * The method nodeList2Map transforms a list into a node. The method only
     * works properly if there are no duplicate nodes (nodes with the same
     * tagname).
     *
     * @param someNodes
     *        a list of nodes
     *
     * @return a map containing all nodes
     */
    static Map<String, Node> nodeList2Map(List<Node> someNodes) {

        Map<String, Node> map = new HashMap<>();

        for (Node node : someNodes) {

            String tagname = node.getNodeName();
            map.put(tagname, node);
        }

        return map;
    }

}
