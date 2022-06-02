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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * An implementation of a node which is part of a tree.
 *
 * @param <T>
 *        the content type of the node
 *
 * @author Kristian Kutin
 */
public class NodeImpl<T> implements Node<T> {

    /**
     * The content of the node.
     */
    private final T content;

    /**
     * Refrences to child nodes.
     */
    private final List<Node<T>> children;

    /**
     * Creates a new node with no parent or child nodes and withouth content.
     */
    public NodeImpl() {

        this(null, (Node<T>[]) null);
    }

    /**
     * Creates a new node with no parent or child nodes and with some content.
     *
     * @param someContent
     */
    public NodeImpl(T someContent) {

        this(someContent, (Node<T>[]) null);
    }

    /**
     * Creates a new node with no parent but child nodes and without content.
     *
     * @param someChildren
     */
    public NodeImpl(Node<T>... someChildren) {

        this(null, someChildren);
    }

    /**
     * Creates a new node according to the specified parameters.
     *
     * @param someContent
     * @param someChildren
     */
    public NodeImpl(T someContent, Node<T>... someChildren) {

        super();

        content = someContent;
        children = toList(someChildren);
    }

    /**
     * Converts the specified array to a list.
     *
     * @param anArray
     *
     * @return a list
     */
    private List<Node<T>> toList(Node<T>... anArray) {

        if (anArray == null) {

            return new ArrayList<Node<T>>();

        } else {

            return Arrays.asList(anArray);
        }
    }

    /**
     * Checks if this entity has content.
     *
     * @return <code>true</code> if content was assigned, else <code>false</code>
     */
    @Override
    public boolean hasContent() {

        return content != null;
    }

    /**
     * Returns the attached content.
     *
     * @return some content
     */
    @Override
    public T getContent() {

        return content;
    }

    /**
     * Checks if the node has child nodes.
     *
     * @return <code>true</code> if this node has child nodes, else <code>false</code>
     */
    @Override
    public boolean hasChildren() {

        return !children.isEmpty();
    }

    /**
     * Returns the number of child nodes of this node.
     *
     * @return the number of child nodes
     */
    @Override
    public int children() {

        return children.size();
    }

    /**
     * Returns all child nodes as array.
     *
     * @return a list of child nodes
     */
    @Override
    public List<Node<T>> getChildren() {

        return children;
    }

    /**
     * Returns the child node at the specified index position.
     *
     * @param anIndex
     *        an index position, i.e. a number greater than or equal to zero
     *        or lesser than the number of child nodes
     *
     * @return a node
     */
    @Override
    public Node<T> getChild(int anIndex) {

        return children.get(anIndex);
    }

}
