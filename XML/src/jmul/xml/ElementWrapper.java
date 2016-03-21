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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


/**
 * A wrapper class which provides additional utility functionality.
 *
 * @author Kristian Kutin
 */
public class ElementWrapper {

    /**
     * The wrapped xml element.
     */
    private Node xmlElement;

    /**
     * References all attributes of the wrapped xml element.
     */
    private NamedNodeMap attributes;

    /**
     * Contains all subelements of the wrapped xml element. The subelements are
     * sorted according to their tagnames.
     */
    private Map<String, Collection<Node>> sortedSubelements;

    /**
     * Constructs a wrapper.
     *
     * @param anXmlElement
     *        an xml element
     */
    public ElementWrapper(Node anXmlElement) {

        xmlElement = anXmlElement;

        // Extract all attributes.

        attributes = anXmlElement.getAttributes();


        // Extracts all subelements.

        sortedSubelements = new HashMap<String, Collection<Node>>();

        for (Node node : XmlHelper.extractChildElements(anXmlElement)) {

            String tagname = node.getNodeName();
            Collection<Node> subset = sortedSubelements.get(tagname);

            if (subset == null) {

                subset = new ArrayList<Node>();
                sortedSubelements.put(tagname, subset);
            }

            subset.add(node);
        }
    }

    /**
     * Returns the tagname of the wrapped xml element.
     *
     * @return a tagname
     */
    public String getName() {

        return xmlElement.getNodeName();
    }

    /**
     * Determines if the wrapped xml element has the specified name.
     *
     * @param anElementName
     *        the alleged name of the specified xml element
     *
     * @return <code>true</code> if the names match, else <code>false</code>
     */
    public boolean equalsName(String anElementName) {

        return getName().equals(anElementName);
    }

    /**
     * Determines if the wrapped xml element possesses an attribute with the
     * specified name.
     *
     * @param anAttributeName
     *        the name of an xml attribute
     *
     * @return <code>true</code> if the specified attribute exists, else
     *         <code>false</code>
     */
    public boolean existsAttribute(String anAttributeName) {

        Node attribute = attributes.getNamedItem(anAttributeName);
        return (attribute != null);
    }

    /**
     * Returns the value of the specified xml attribute.
     *
     * @param anAttributeName
     *        the name of an xml attribute
     *
     * @return the value of the specified xml attribute or <code>null</code> if
     *         no such attribute exists
     */
    public String getAttribute(String anAttributeName) {

        Node attribute = attributes.getNamedItem(anAttributeName);

        if (attribute != null) {

            String value = attribute.getTextContent();
            return value;
        }

        return null;
    }

    /**
     * Determines if the wrapped xml element possesses subelements with the
     * specified name.
     *
     * @param aSubelementName
     *        the name of subelements
     *
     * @return <code>true</code> if the specified subelements exist, else
     *         <code>false</code>
     */
    public boolean existsSubelement(String aSubelementName) {

        return sortedSubelements.containsKey(aSubelementName);
    }

    /**
     * Returns the specified subelements.
     *
     * @param aSubelementName
     *        the name of subelements
     *
     * @return the specified subelements or <code>null</code> if no such
     *         subelements exist
     */
    public Collection<Node> getSubelement(String aSubelementName) {

        return sortedSubelements.get(aSubelementName);
    }

}
