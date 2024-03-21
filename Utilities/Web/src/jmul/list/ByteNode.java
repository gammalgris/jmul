/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.list;


/**
 * This is an implementation of a single linked list containing byte values.
 * The linked list is modifiable. You can change a node's content and the reference
 * to the next byte node. Be careful when you change the single linked list.
 *
 * @author Kristian Kutin
 */
public class ByteNode {

    /**
     * The byte content of this node.
     */
    private byte content;

    /**
     * A reference to the next byte node or <code>null</code> if this node is the last
     * node in the node list.
     */
    private ByteNode nextNode;

    /**
     * Creates a new byte node according to the specified parameter.
     *
     * @param content
     *        the byte content for this node
     */
    public ByteNode(byte content) {

        this(content, null);
    }

    /**
     * Creates a new byte node according to the specified parameters.
     *
     * @param content
     *        the byte content for this node
     * @param nextNode
     *        a reference to the next byte node
     */
    public ByteNode(byte content, ByteNode nextNode) {

        super();

        this.content = content;
        this.nextNode = nextNode;
    }

    /**
     * Checks if this byte node is linked to another byte node.
     *
     * @return <code>true</code> if this byte node is linked to another byte node,
     *         else <code>false</code>
     */
    public boolean hasNext() {

        return nextNode != null;
    }

    /**
     * Returns the byte content of this node.
     *
     * @return a byte value
     */
    public byte content() {

        return content;
    }

    /**
     * Changes the byte content of this node.
     *
     * @param content
     */
    public void setContent(byte content) {

        this.content = content;
    }

    /**
     * Returns a reference to the next byte node.
     *
     * @return a reference to another byte node or <code>null</code> if this
     *         node is the last byte node
     */
    public ByteNode next() {

        return nextNode;
    }

    /**
     * Sets the reference to the next byte node.
     *
     * @param nextNode
     *        a reference to the next byte node or <code>null</code> if this
     *        node is the last byte node
     */
    public void setNext(ByteNode nextNode) {

        this.nextNode = nextNode;
    }

    /**
     * Returns the size of the linked list starting from this byte node. If
     * the linked list exceeds the maximum positive integer value then this
     * method will run into an arithmetic overflow exception.
     *
     * @return the size of this linked list
     */
    public int size() {

        int count = 0;
        ByteNode currentNode = this;

        while (currentNode != null) {

            count++;
            currentNode = currentNode.next();

            if (count == Integer.MIN_VALUE) {

                throw new ArithmeticOverflowException();
            }
        }

        return count;
    }

    /**
     * Returns the byte node at the specified index relative to this byte node.
     * If the linked list is greater than the maximum positive integer value then you
     * cannot access all byte nodes with this method.
     *
     * @param index
     *        an index value which is 0 or a positive integer
     *
     * @return the byte node at the specified index position
     */
    public ByteNode nodeAt(int index) {

        if (index < 0) {

            throw new IllegalArgumentException("No negative index values are allowed!");
        }

        int count = 0;
        ByteNode currentNode = this;

        while (true) {

            if (currentNode == null) {

                String message = String.format("The specified index is out of bounds (%d)!", index);
                throw new IndexOutOfBoundsException(message);
            }

            if (index == count) {

                return currentNode;
            }

            count++;
            currentNode = currentNode.next();
        }
    }

}


/**
 * A custom exception for cases when an arithmetic operation results in an overflow.
 *
 * @author Kristian Kutin
 */
class ArithmeticOverflowException extends RuntimeException {

    /**
     * The default constructor.
     */
    public ArithmeticOverflowException() {

        super("An arithmetic operation resulted in an overflow!");
    }

}
