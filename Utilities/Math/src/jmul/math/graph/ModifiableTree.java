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
 * This interface describes a simple tree structure with implicit edges between nodes.
 * The tree and all its nodes can be changed at any time.
 *
 * @param <T>
 *        the content type of a node
 *
 * @author Kristian Kutin
 */
public interface ModifiableTree<T> {

    /**
     * Returns the root node of the tree.
     *
     * @return a root node
     */
    T getRoot();

    /**
     * Replaces the current root node with the specified root node thus replacing the
     * whole tree.
     *
     * @param aNode
     *        the root node of the new tree
     *
     * @return the root node of the previous tree
     */
    T setRoot(T aNode);

}
