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


import java.util.Collections;
import java.util.HashMap;
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
     */
    public SubelementMap(Node node) {

        parentName = node.getNodeName();
        subelements = Collections.unmodifiableMap(extractChildElements(node));
    }

    /**
     * Returns the subelement which matches the specified markup details. If no
     * such subelement exists an exception is thrown.
     *
     * @param markup
     *
     * @return a subelement
     */
    public Node getSubelement(XmlMarkup markup) {

        XmlParserHelper.assertDescribesXmlElement(markup);

        String subelementName = markup.getTagname();
        boolean existsSubelement = subelements.containsKey(subelementName);

        if (!existsSubelement) {

            StringBuffer message = new StringBuffer();

            message.append("The element \"");
            message.append(parentName);
            message.append("\" doesn't have a subelement \"");
            message.append(subelementName);
            message.append("\"!");

            throw new ParsingException(message);
        }

        Node subelement = subelements.get(subelementName);
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
     * Extracts all subelements of the specified XML element.<br />
     * <br />
     * <i>Note:<br />
     * The specified XML element should not have more than one subelement with
     * the same tagname. If that should be the case an exception is thrown.</i>
     *
     * @param parentNode
     *
     * @return a map which contains all subelements
     */
    private static Map<String, Node> extractChildElements(Node parentNode) {

        Map<String, Node> map = new HashMap<String, Node>();

        StringBuffer debugDetails = new StringBuffer();
        boolean first = true;

        for (Node node : XmlHelper.extractChildElements(parentNode)) {

            String tagname = node.getNodeName();
            String value = node.getTextContent();

            if (first) {

                first = false;

            } else {

                debugDetails.append(" // ");
            }

            debugDetails.append(tagname);
            debugDetails.append("=\"");
            debugDetails.append(value);
            debugDetails.append("\"");

            if (map.containsKey(tagname)) {

                String message =
                    "The specified node \"" + parentNode.getNodeName() + "\" has more than one subelement of type \"" +
                    tagname + "\"! " + String.valueOf(debugDetails);
                throw new ParsingException(message);
            }

            map.put(tagname, node);
        }

        return map;
    }

}
