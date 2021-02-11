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
 * An implementation of a simple tree structure with implicit edges between nodes.
 * The tree and all its nodes cannot be changed after creation.
 *
 * @param <T>
 *        the class type which represents a node
 *
 * @author Kristian Kutin
 */
public class TreeImpl<T> implements Tree<T> {

    /**
     * A reference to a root node.
     */
    private final T root;

    /**
     * Creates a new tree without root node.
     */
    public TreeImpl() {

        this(null);
    }

    /**
     * Creates a new tree with thespecified node as root node.
     *
     * @param aRoot
     */
    public TreeImpl(T aRoot) {

        super();

        root = aRoot;
    }

    /**
     * Returns the root node of the tree.
     *
     * @return a root node
     */
    @Override
    public T getRoot() {

        return root;
    }

}
