/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.document.markdown.content.node;


/**
 * An implementation of a content node.
 *
 * @author Kristian Kutin
 */
public class ContentNodeImpl implements ContentNode {

    /**
     * A reference to a parent node.
     */
    private ContentNode parent;

    /**
     * The default constructor.
     */
    protected ContentNodeImpl() {

        super();

        parent = null;
    }

    /**
     * Returns a reference to the parent text node.
     *
     * @return a reference to a parent text node or <code>null</code> if
     *         this text node is the topmost parent node
     */
    @Override
    public ContentNode getParent() {

        return parent;
    }

    /**
     * Sets (i.e. updates) the parent reference of this text node.
     *
     * @param aParent
     *        the new parent of this node
     */
    @Override
    public void setParent(ContentNode aParent) {

        parent = aParent;
    }

    /**
     * Checks if this node has a parent node.
     *
     * @return <code>true</code> if this node has ap arent node, else <code>false</code>
     */
    @Override
    public boolean hasParent() {

        return getParent() != null;
    }

}
