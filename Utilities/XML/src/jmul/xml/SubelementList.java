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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;


/**
 * This entity contains subelements of an xml element. Similar to a list
 * subelements are accessed by their index.
 *
 * @author Kristian Kutin
 */
public class SubelementList implements Iterable<Node> {

    /**
     * The name of the parent node.
     */
    private final String parentName;

    /**
     * The actual container for all subelements.
     */
    private final List<Node> subelements;

    /**
     * Creates a new container and populates it with the subelements of the
     * specified xml element.
     *
     * @param node
     *        the xml element that is checked for subelements
     */
    public SubelementList(Node node) {

        parentName = node.getNodeName();

        List<Node> tmpList = XmlHelper.extractChildElementNodes(node);
        subelements = Collections.unmodifiableList(tmpList);
    }

    /**
     * Returns an iterator.
     *
     * @return an iterator
     */
    @Override
    public Iterator<Node> iterator() {

        return subelements.iterator();
    }

    /**
     * Returns the number of subelements.
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
     * Returns all subelements of the specified markup type.
     *
     * @param markup
     *        an xml markup
     *
     * @return all subelements of the specified markup type
     */
    public List<Node> getSubelements(XmlMarkup markup) {

        XmlParserHelper.assertDescribesXmlElement(markup);

        List<Node> sublist = new ArrayList<>();

        for (Node node : this) {

            if (XmlParserHelper.matchesXmlElement(node, markup)) {

                sublist.add(node);
            }
        }

        return sublist;
    }

}
