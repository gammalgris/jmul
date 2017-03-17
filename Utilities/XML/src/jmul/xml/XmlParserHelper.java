/*
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

package jmul.xml;


import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


/**
 * A utility class.
 *
 * @author Kristian Kutin
 */
public final class XmlParserHelper {

    /**
     * The default constructor.
     */
    private XmlParserHelper() {
    }

    /**
     * Checks if the specified xml element matches the specified markup details.
     *
     * @param node
     * @param markup
     *
     * @return <code>true</code> if the specified parameters match, else <code>false</code>
     */
    public static boolean matchesXmlElement(Node node, XmlMarkup markup) {

        assertDescribesXmlElement(markup);

        String name = node.getNodeName();
        return markup.equalsTagName(name);
    }

    /**
     * Checks if the specified xml element matches the specified markup details.
     * An exception is thrown if the specified informations do not match.
     *
     * @param node
     * @param markup
     */
    public static void assertMatchesXmlElement(Node node, XmlMarkup markup) {

        assertDescribesXmlElement(markup);

        if (!matchesXmlElement(node, markup)) {

            StringBuilder message = new StringBuilder();

            message.append("Unexpected xml element found (expected name= \"");
            message.append(markup);
            message.append("\" / found name=\"");
            message.append(node.getNodeName());
            message.append("\"!");

            throw new ParsingException(message);
        }
    }

    /**
     * Checks if the specified markup details describe an xml element.
     *
     * @param markup
     */
    protected static void assertDescribesXmlElement(XmlMarkup markup) {

        if (markup.isXmlAttribute()) {

            StringBuilder message = new StringBuilder();

            message.append("The specified markup details are describing an xml attribute (");
            message.append(markup);
            message.append(")!");

            throw new ParsingException(message);
        }
    }

    /**
     * Checks if the specified xml element possesses an attribute with the
     * specified markup details.
     *
     * @param node
     * @param markup
     *
     * @return <code>true</code> if such an attribute exists, else
     *         <code>false</code>
     */
    public static boolean existsXmlAttribute(Node node, XmlMarkup markup) {

        assertDescribesXmlAttribute(markup);

        NamedNodeMap attributes = node.getAttributes();
        Node attribute = attributes.getNamedItem(markup.getTagName());

        return attribute != null;
    }

    /**
     * Checks if the specified xml element possesses an attribute with the
     * specified markup details. If no such attribute exists an exception is
     * thrown.
     *
     * @param node
     * @param markup
     */
    public static void assertExistsXmlAttribute(Node node, XmlMarkup markup) {

        getXmlAttribute(node, markup);
    }

    /**
     * Examines the specified xml element and returns the xml attribute which
     * matches the specified markup details.
     *
     * @param node
     * @param markup
     *
     * @return an xml attribute
     */
    public static Node getXmlAttribute(Node node, XmlMarkup markup) {

        assertDescribesXmlAttribute(markup);

        String name = node.getNodeName();
        NamedNodeMap attributes = node.getAttributes();
        Node attribute = attributes.getNamedItem(markup.getTagName());

        if (attribute == null) {

            StringBuilder message = new StringBuilder();

            message.append("The xml element \"");
            message.append(name);
            message.append("\" is missing the attribute \"");
            message.append(markup);
            message.append("\"!");

            throw new ParsingException(message);
        }

        return attribute;
    }

    /**
     * Returns the value of the speicifed  xml attribute which matches the specified
     * markup details.
     *
     * @param node
     * @param markup
     *
     * @return an attribute value
     */
    public static String getXmlAttributeValue(Node node, XmlMarkup markup) {

        Node attribute = getXmlAttribute(node, markup);

        return attribute.getTextContent();
    }

    /**
     * Checks if the specified markup details describe an xml attribute.
     *
     * @param markup
     */
    protected static void assertDescribesXmlAttribute(XmlMarkup markup) {

        if (markup.isXmlElement()) {

            StringBuilder message = new StringBuilder();

            message.append("The specified markup details are describing an xml element (");
            message.append(markup);
            message.append(")!");

            throw new ParsingException(message);
        }
    }

    /**
     * Checks if the specified container contains subelements.
     *
     * @param subelements
     *
     * @return <code>true</code> if the specified container contains
     *         subelements, else <code>false</code>
     */
    public static boolean hasXmlSubelements(SubelementMap subelements) {

        return subelements.size() > 0;
    }

    /**
     * Checks if specified container contains subelements.
     *
     * @param subelements
     */
    public static void assertHasXmlSubelements(SubelementMap subelements) {

        if (!hasXmlSubelements(subelements)) {

            throw createNoSubelementsException(subelements.getParentName());
        }
    }

    /**
     * Checks if the specified container contains subelements.
     *
     * @param subelements
     *
     * @return <code>true</code> if the specified container contains
     *         subelements, else <code>false</code>
     */
    public static boolean hasXmlSubelements(SubelementList subelements) {

        return subelements.size() > 0;
    }

    /**
     * Checks if specified container contains subelements.
     *
     * @param subelements
     */
    public static void assertHasXmlSubelements(SubelementList subelements) {

        if (!hasXmlSubelements(subelements)) {

            throw createNoSubelementsException(subelements.getParentName());
        }
    }

    /**
     * Checks if the specified container contains subelements of the specified
     * type.
     *
     * @param subelements
     * @param markup
     *
     * @return <code>true</code> if the specified container contains
     *         subelements, else <code>false</code>
     */
    public static boolean hasXmlSubelements(SubelementList subelements, XmlMarkup markup) {

        List<Node> sublist = subelements.getSubelements(markup);

        return !sublist.isEmpty();
    }

    /**
     * Checks if specified container contains subelements of the specified type.
     *
     * @param subelements
     * @param markup
     */
    public static void assertHasXmlSubelements(SubelementList subelements, XmlMarkup markup) {

        if (!hasXmlSubelements(subelements, markup)) {

            throw createNoSpecificSubelementsException(subelements.getParentName(), markup);
        }
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param anElementName
     *
     * @return an exception
     */
    private static ParsingException createNoSubelementsException(String anElementName) {

        StringBuilder message = new StringBuilder();

        message.append("The element \"");
        message.append(anElementName);
        message.append("\" has no subelements!");

        return new ParsingException(message);
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param anElementName
     *
     * @return an exception
     */
    private static ParsingException createNoSpecificSubelementsException(String anElementName, XmlMarkup markup) {

        StringBuilder message = new StringBuilder();

        message.append("The element \"");
        message.append(anElementName);
        message.append("\" has no subelements of the type \"");
        message.append(markup);
        message.append("\"!");

        return new ParsingException(message);
    }

}
