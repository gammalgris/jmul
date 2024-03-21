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

package test.jmul.list;


import jmul.list.ByteNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import jmul.test.classification.UnitTest;

import org.junit.Test;


/**
 * This test suite tests a single linked list of byte values.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ByteNodeTest {

    /**
     * Creates a single node and tests its properties.
     */
    @Test
    public void testCreateSingleNode() {

        byte b = (byte) 1;
        ByteNode node = new ByteNode(b);

        assertNotNull(node);
        assertEquals(false, node.hasNext());
        assertEquals(b, node.content());
        assertEquals(null, node.next());

        assertEquals(1, node.size());
        assertEquals(node, node.nodeAt(0));
    }

    /**
     * Creates a linked list with two nodes and tests their properties.
     */
    @Test
    public void testCreateTwoElementList() {

        byte b1 = (byte) 1;
        byte b2 = (byte) 2;

        ByteNode node2 = new ByteNode(b2);
        ByteNode node1 = new ByteNode(b1, node2);

        assertNotNull(node1);
        assertEquals(true, node1.hasNext());
        assertEquals(b1, node1.content());
        assertEquals(node2, node1.next());

        assertNotNull(node2);
        assertEquals(false, node2.hasNext());
        assertEquals(b2, node2.content());
        assertEquals(null, node2.next());

        assertEquals(2, node1.size());
        assertEquals(1, node2.size());

        assertEquals(node1, node1.nodeAt(0));
        assertEquals(node2, node1.nodeAt(1));
    }

    /**
     * Creates a single node then expands it to a two element list and
     * tests the nodes' properties.
     */
    @Test
    public void testCreateSingleNodeAndExpandToTwoElementList() {

        byte b1 = (byte) 1;
        ByteNode node1 = new ByteNode(b1);

        assertNotNull(node1);
        assertEquals(false, node1.hasNext());
        assertEquals(b1, node1.content());
        assertEquals(null, node1.next());

        assertEquals(1, node1.size());
        assertEquals(node1, node1.nodeAt(0));

        byte b2 = (byte) 2;
        ByteNode node2 = new ByteNode(b2);

        assertNotNull(node2);
        assertEquals(false, node2.hasNext());
        assertEquals(b2, node2.content());
        assertEquals(null, node2.next());

        assertEquals(1, node2.size());
        assertEquals(node2, node2.nodeAt(0));

        node1.setNext(node2);
        assertEquals(true, node1.hasNext());
        assertEquals(node2, node1.next());

        assertEquals(2, node1.size());
        assertEquals(1, node2.size());
        assertEquals(node1, node1.nodeAt(0));
        assertEquals(node2, node1.nodeAt(1));
    }

    /**
     * Tests navigating to a node with a negative element index.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNavigateToNegativeNodeIndex() {

        byte b = (byte) 1;
        ByteNode node = new ByteNode(b);

        node.nodeAt(-1);
    }

    /**
     * Tests navigating to a node with a positive but invalid element index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testNavigateToInvalidNodeIndex() {

        byte b = (byte) 1;
        ByteNode node = new ByteNode(b);

        node.nodeAt(1);
    }

}
