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
import jmul.math.graph.Tree;
import jmul.math.graph.TreeImpl;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;


/**
 * Tests the creation (i.e. instantiation and initialization) of trees.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TreeCreationTest {

    /**
     * Tests creating a tree without a root node.
     */
    @Test
    public void testCreation01() {

        Tree tree = new TreeImpl();
        assertNull(tree.getRoot());
    }

    /**
     * Tests creating a tree without a root node.
     */
    @Test
    public void testCreation02() {

        Tree tree = new TreeImpl(null);
        assertNull(tree.getRoot());
    }

    /**
     * Tests creating a tree with a root node.
     */
    @Test
    public void testCreation03() {

        Node root = new NodeImpl();

        Tree tree = new TreeImpl(root);
        assertNotNull(tree.getRoot());
    }

    /**
     * Tests creating a tree with a root node.
     */
    @Test
    public void testCreation04() {

        Node<String> root = new NodeImpl<>();

        Tree<String> tree = new TreeImpl<>(root);
        assertNotNull(tree.getRoot());
    }

}
