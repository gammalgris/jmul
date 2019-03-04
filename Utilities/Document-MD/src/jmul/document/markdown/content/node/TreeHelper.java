/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package jmul.document.markdown.content.node;


import jmul.document.markdown.content.enumeration.OrderedList;
import jmul.document.markdown.content.enumeration.UnorderedList;


/**
 * A helper class for the markdown document tree.
 *
 * @author Kristian Kutin
 */
public final class TreeHelper {

    /**
     * The default constructor.
     */
    private TreeHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the specified object is a text node.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if the specified object is a text node,
     *         else <code>false</code>
     */
    public static boolean isTextNode(Object o) {

        return o instanceof TextNode;
    }

    /**
     * Checks if the specified object is a content node.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if the specified object is a content node,
     *         else <code>false</code>
     */
    public static boolean isContentNode(Object o) {

        return o instanceof ContentNode;
    }

    /**
     * Checks if the specified object is a chapter node.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if the specified object is a chapter node,
     *         else <code>false</code>
     */
    public static boolean isChapterNode(Object o) {

        return o instanceof ChapterNode;
    }

    /**
     * Checks if the specified object is a list item  node.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if the specified object is a list item node,
     *         else <code>false</code>
     */
    public static boolean isListItemNode(Object o) {

        return o instanceof ListItemNode;
    }

    /**
     * Checks if the specified object is an ordered list.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if the specified object is an ordered list,
     *         else <code>false</code>
     */
    public static boolean isOrderedList(Object o) {

        return o instanceof OrderedList;
    }

    /**
     * Checks if the specified object is an unordered list.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if the specified object is an unordered list,
     *         else <code>false</code>
     */
    public static boolean isUnorderedList(Object o) {

        return o instanceof UnorderedList;
    }

    /**
     * Returns the parent node of the specified object.
     *
     * @param o
     *        an object
     *
     * @return the parent node or <code>null</code> if the specified node
     *         has no parent
     */
    public static ContentNode getParentNode(Object o) {

        if (o == null) {

            String message = "No object was specified (null)!";
            throw new IllegalArgumentException(message);
        }

        if (!isContentNode(o)) {

            String message = "The specified object is no content node!";
            throw new IllegalArgumentException(message);
        }

        ContentNode node = (ContentNode) o;

        return node.getParent();
    }

}
