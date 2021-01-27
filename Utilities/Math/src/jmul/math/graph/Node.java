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

package jmul.math.graph;


/**
 * This interface describes a node which is part of a tree.
 *
 * @param <T>
 *        the content type of the node
 *
 * @author Kristian Kutin
 */
public interface Node<T> extends Content<T> {

    /**
     * Checks if this node has a parent node.
     *
     * @return <code>true</code> if this node has a parent node, else <code>false</code>
     */
    boolean hasParent();

    /**
     * Returns the parent node of this node.
     *
     * @return a parent node or <code>null</code> if this node has no parent node
     */
    Node getParent();

    /**
     * Returns the number of child nodes of this node.
     *
     * @return the number of child nodes
     */
    int children();

    /**
     * Returns all child nodes as array.
     *
     * @return an array of nodes
     */
    Node<T> getChildren();

    /**
     * Returns the child node at the specified index position.
     *
     * @param anIndex
     *        an index position, i.e. a number greater than or equal to zero
     *        or lesser than the number of child nodes
     *
     * @return a node
     */
    Node<T> getChild(int anIndex);

}
