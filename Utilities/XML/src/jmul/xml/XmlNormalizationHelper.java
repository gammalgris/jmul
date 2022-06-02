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

package jmul.xml;


import java.util.List;

import jmul.metainfo.annotations.Modified;

import jmul.string.TextNormalizationHelper;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


/**
 * This utility class contains operations to normalize an XML document.
 *
 * @author Kristian Kutin
 *
 * @deprecated The XML document should not be modified. Instead change the writer implementation!
 */
@Deprecated
public final class XmlNormalizationHelper {

    /**
     * The default constructor.
     */
    private XmlNormalizationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * This operation normalizes an XML document, i.e. all text content and comments.
     *
     * @param aDocument
     *        the XMl document which is to be normalized
     *
     * @return a normalized XML document (i.e. the specified document which has been normalized)
     */
    public static Document toNormalizedXmlDocument(@Modified Document aDocument) {

        Node root = aDocument.getDocumentElement();
        normalizeNode(root);

        return aDocument;
    }

    /**
     * This method recursively normalizes the XML document.
     *
     * @param aNode
     *        a node in the XML document tree
     */
    private static void normalizeNode(@Modified Node aNode) {

        normalizeTextNode(aNode);
        normalizeAttributes(aNode);
        normalizeSubelements(aNode);
    }

    /**
     * This method normalizes the text node of the specified node.
     * If there is no text node no normalization is needed.
     *
     * @param aNode
     *        a node in the XML document tree
     */
    private static void normalizeTextNode(@Modified Node aNode) {

        List<Node> textNodes = XmlHelper.extractChildNodes(aNode, Node.TEXT_NODE);

        for (Node textNode : textNodes) {

            normalizeNodeValue(textNode);
        }
    }

    /**
     * This method normalizes the node value of the specified node.
     * If there is no node value no normalization is needed.
     *
     * @param aNode
     *        a node in the XML document tree
     */
    private static void normalizeNodeValue(@Modified Node aNode) {

        String nodeValue = aNode.getNodeValue();

        if (nodeValue == null) {

            return;
        }

        if (nodeValue.isEmpty()) {

            return;
        }

        String normalizedNodeValue = TextNormalizationHelper.toNormalizedXmlString(nodeValue);
        aNode.setNodeValue(normalizedNodeValue);
    }

    /**
     * The method normalizes all attributes of the specified node.
     *
     * @param aNode
     *        a node in the XML document tree
     */
    private static void normalizeAttributes(@Modified Node aNode) {

        NamedNodeMap attributes = aNode.getAttributes();

        for (int i = 0; i < attributes.getLength(); i++) {

            Node attribute = attributes.item(i);
            normalizeAttribute(attribute);
        }
    }

    /**
     * The method normalizes the attribute value of the specified attribute.
     *
     * @param aNode
     *        a node in the XML document tree
     */
    private static void normalizeAttribute(@Modified Node aNode) {


        normalizeNodeValue(aNode);
    }

    /**
     * The method normalizes all subelements of the specified node.
     *
     * @param aNode
     *        a node in the XML document tree
     */
    private static void normalizeSubelements(@Modified Node aNode) {

        SubelementList subelements = new SubelementList(aNode);

        for (Node subelement : subelements) {

            normalizeNode(subelement);
        }
    }

}
