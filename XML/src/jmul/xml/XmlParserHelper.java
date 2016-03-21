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
     * An exception is thrown if the specified informations do not match.
     *
     * @param node
     * @param markup
     */
    public static void assertMatchingXmlElement(Node node, XmlMarkup markup) {

        assertDescribesXmlElement(markup);

        String name = node.getNodeName();
        boolean differentNames = !markup.equalsXmlMarkup(name);

        if (differentNames) {

            StringBuffer message = new StringBuffer();

            message.append("Unexpected xml element found (expected name= \"");
            message.append(markup);
            message.append("\" / found name=\"");
            message.append(name);
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

            StringBuffer message = new StringBuffer();

            message.append("The specified markup details are describing an xml attribute (");
            message.append(markup);
            message.append(")!");

            throw new ParsingException(message);
        }
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
        Node attribute = attributes.getNamedItem(markup.getTagname());

        if (attribute == null) {

            StringBuffer message = new StringBuffer();

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
     * Checks if the specified markup details describe an xml attribute.
     *
     * @param markup
     */
    protected static void assertDescribesXmlAttribute(XmlMarkup markup) {

        if (markup.isXmlElement()) {

            StringBuffer message = new StringBuffer();

            message.append("The specified markup details are describing an xml element (");
            message.append(markup);
            message.append(")!");

            throw new ParsingException(message);
        }
    }

    /**
     * Checks if specified container contains subelements.
     *
     * @param subelements
     */
    public static void assertHasXmlSubelements(SubelementMap subelements) {

        boolean hasNoSubelements = (subelements.size() == 0);

        if (hasNoSubelements) {

            StringBuffer message = new StringBuffer();

            message.append("The element \"");
            message.append(subelements.getParentName());
            message.append("\" has no subelements!");

            throw new ParsingException(message);
        }
    }

    /**
     * Checks if specified container contains subelements.
     *
     * @param subelements
     */
    public static void assertHasXmlSubelements(SubelementList subelements) {

        boolean hasNoSubelements = (subelements.size() == 0);

        if (hasNoSubelements) {

            StringBuffer message = new StringBuffer();

            message.append("The element \"");
            message.append(subelements.getParentName());
            message.append("\" has no subelements!");

            throw new ParsingException(message);
        }
    }

}
