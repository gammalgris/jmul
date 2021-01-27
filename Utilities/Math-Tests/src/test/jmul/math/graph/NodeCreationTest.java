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

package test.jmul.math.graph;


import jmul.math.graph.Node;
import jmul.math.graph.NodeImpl;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * Tests the creation (i.e. instantiation and initialization) of nodes.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class NodeCreationTest {

    /**
     * Tests creating a node without content and no child nodes.
     */
    @Test
    public void testCreation01() {

        Node node = new NodeImpl();

        assertEquals(false, node.hasContent());
        assertNull(node.getContent());

        assertEquals(false, node.hasChildren());
        assertEquals(0, node.children());
    }

    /**
     * Tests creating a node with some content and no child nodes.
     */
    @Test
    public void testCreation02() {

        String content = "Hello World!";

        Node<String> node = new NodeImpl<>(content);

        assertTrue(node.hasContent());
        assertEquals(content, node.getContent());

        assertFalse(node.hasChildren());
        assertEquals(0, node.children());
    }

    /**
     * Tests creating a node without content but a child node.
     */
    @Test
    public void testCreation03() {

        Node child = new NodeImpl();

        Node node = new NodeImpl(new Node[] { child });
        assertFalse(node.hasContent());
        assertNull(node.getContent());

        assertTrue(node.hasChildren());
        assertEquals(1, node.children());
        assertEquals(child, node.getChild(0));
    }

    /**
     * Tests creating a node with some content and a child node.
     */
    @Test
    public void testCreation04() {

        String content = "Hello World!";
        Node<String> child = new NodeImpl<>();

        Node<String> node = new NodeImpl<>(content, child);
        assertTrue(node.hasContent());
        assertEquals(content, node.getContent());

        assertTrue(node.hasChildren());
        assertEquals(1, node.children());
        assertEquals(child, node.getChild(0));
    }

    /**
     * Tests creating a node without content but two child nodes.
     */
    @Test
    public void testCreation05() {

        Node child1 = new NodeImpl();
        Node child2 = new NodeImpl();

        Node node = new NodeImpl(new Node[] { child1, child2 });
        assertFalse(node.hasContent());
        assertNull(node.getContent());

        assertTrue(node.hasChildren());
        assertEquals(2, node.children());
        assertEquals(child1, node.getChild(0));
        assertEquals(child2, node.getChild(1));
    }

}
