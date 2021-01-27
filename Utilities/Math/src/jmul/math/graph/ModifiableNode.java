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
 * This interface describes a node which is part of a tree. The tree and all its nodes can be
 * changed at any time.
 *
 * @param <T>
 *        the content type of the node
 *
 * @author Kristian Kutin
 */
public interface ModifiableNode<T> extends Node<T>, ModifiableContent<T> {

    /**
     * Sets the parent node of this node. In order to maintain a consistent tree structure
     * you have to make sure that the previous and new parent's children are updated accordingly.
     *
     * @param aParent
     *        a parent node
     *
     * @return the previous parent node
     */
    ModifiableNode<T> setParent(ModifiableNode<T> aParent);

    /**
     * Removes the child node at the specified index position. In order to maintain a consistent
     * tree structure you have to make sure that the removed node is disposed or it's parent node
     * is updated accordingly.
     *
     * @param anIndex
     *        an index position, i.e. a number greater than or equal to zero
     *        or lesser than the number of child nodes
     *
     * @return the removed child node
     */
    ModifiableNode<T> removeChild(int anIndex);

    /**
     * Replaces the child node at the specified index position. In order to maintain a consistent
     * tree structure you have to make sure that the removed node is disposed or it's parent node
     * is updated accordingly. The new node has to have it's parent node updated as well.
     *
     * @param anIndex
     *        an index position, i.e. a number greater than or equal to zero
     *        or lesser than the number of child nodes
     * @param aNode
     *        the new child node
     *
     * @return the removed child node
     */
    ModifiableNode<T> replaceChild(int anIndex, ModifiableNode<T> aNode);

    /**
     * Adds a new child node to this node. In order to maintain a consistent  tree structure you
     * have to make sure that the new node has to have it's parent node updated.
     *
     * @param aNode
     *        the new child node
     */
    void addChild(ModifiableNode<T> aNode);

}
