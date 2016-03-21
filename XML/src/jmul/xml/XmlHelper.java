/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.xml;


import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * A utility class.
 *
 * @author Kristian Kutin
 */
public final class XmlHelper {

    /**
     * The default constructor.
     */
    private XmlHelper() {
    }

    /**
     * Extracts all child nodes of the specified type from the specified parent node.
     *
     * @param parentNode
     * @param expectedChildType
     *
     * @return all child nodes which match the specified criteria
     */
    private static List<Node> extractChildNodes(Node parentNode, short expectedChildType) {

        List<Node> children = new ArrayList<Node>();
        NodeList allChildren = parentNode.getChildNodes();

        for (int a = 0; a < allChildren.getLength(); a++) {

            Node child = allChildren.item(a);
            short childType = child.getNodeType();

            if (childType == expectedChildType) {

                children.add(child);
            }
        }

        return children;
    }

    /**
     * Extracts all child elements of the specified XML element.
     *
     * @param parentNode
     *
     * @return all child elements
     */
    public static List<Node> extractChildElements(Node parentNode) {

        if (parentNode.getNodeType() != Node.ELEMENT_NODE) {

            String message = "The specified node is no XML element (" + parentNode.getNodeType() + ")!";
            throw new ParsingException(message);
        }

        return extractChildNodes(parentNode, Node.ELEMENT_NODE);
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
    public static Attr createXmlAttribute(Document aDocument, String anAttributeName, String anAttributeValue) {

        Attr attribute = aDocument.createAttribute(anAttributeName);
        attribute.setNodeValue(anAttributeValue);

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
    public static Element createXmlElement(Document aDocument, String anElementName) {

        Element objectElement = aDocument.createElement(anElementName);

        return objectElement;
    }

    /**
     * Creates a new XML Document.
     *
     * @return a new XML document
     */
    public static Document newXmlDocument() {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            return doc;

        } catch (Exception e) {

            String message = "Coulnd't create a new XML document!";
            throw new RuntimeException(message, e);
        }
    }

    /**
     * Clones an xml element with all its attributes and text value, but without
     * subelements.
     *
     * @param newDocument
     * @param theOriginalElement
     *
     * @return a clone
     */
    public static Node cloneXmlElemenet(Document newDocument, Node theOriginalElement) {

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
     * @param theOriginalAttribute
     *
     * @return a clone
     */
    public static Node cloneXmlAttribute(Document newDocument, Node theOriginalAttribute) {

        String attributeName = theOriginalAttribute.getNodeName();
        String attributeValue = theOriginalAttribute.getNodeValue();

        Node newAttribute = createXmlAttribute(newDocument, attributeName, attributeValue);

        return newAttribute;
    }

}
