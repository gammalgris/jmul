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

package jmul.xml;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;


/**
 * This entity contains subelements of an xml element. Similar to a map
 * subelements are accessed by their names.
 *
 * @author Kristian Kutin
 */
public class SubelementMap {

    /**
     * The name of the parent node.
     */
    private final String parentName;

    /**
     * The actual container for all subelements.
     */
    private final Map<String, Node> subelements;

    /**
     * Creates a new container and populates it with the subelements of the
     * specified xml element.
     *
     * @param node
     *        the xml element that is checked for subelements
     */
    public SubelementMap(Node node) {

        parentName = node.getNodeName();

        List<Node> tmpList = XmlHelper.extractChildElementNodes(node);
        Map<String, Node> tmpMap = XmlHelper.nodeList2Map(tmpList);
        subelements = Collections.unmodifiableMap(tmpMap);
    }

    /**
     * Returns the subelement which matches the specified markup details. If no
     * such subelement exists an exception is thrown.
     *
     * @param markup
     *        an xml markup
     *
     * @return a subelement
     */
    public Node getSubelement(XmlMarkup markup) {

        XmlParserHelper.assertDescribesXmlElement(markup);

        String subelementName = markup.getTagName();
        Node subelement = subelements.get(subelementName);

        boolean notExistsSubelement = subelement == null;
        if (notExistsSubelement) {

            StringBuilder message = new StringBuilder();

            message.append("The element \"");
            message.append(parentName);
            message.append("\" doesn't have a subelement \"");
            message.append(subelementName);
            message.append("\"!");

            throw new ParsingException(message);
        }

        return subelement;
    }

    /**
     * Returns the number of subelements
     *
     * @return the number of subelements
     */
    public int size() {

        return subelements.size();
    }

    /**
     * Returns the name of the parent xml element.
     *
     * @return the name of the parent xml element
     */
    public String getParentName() {

        return parentName;
    }

    /**
     * Checks if a subelement, which matches the specified markup details, exists.
     *
     * @param markup
     *        an xml markup
     *
     * @return <code>true</code> if a subelement exists, else <code>false</code>
     */
    public boolean hasSubelement(XmlMarkup markup) {

        XmlParserHelper.assertDescribesXmlElement(markup);
        String subelementName = markup.getTagName();

        return subelements.containsKey(subelementName);
    }

    /**
     * Returns the text node content of the specified subelement.
     *
     * @param markup
     *        an xml markup
     *
     * @return a text node content
     */
    public String getSubelementTextValue(XmlMarkup markup) {

        Node subelement = getSubelement(markup);

        return subelement.getTextContent();
    }

}
