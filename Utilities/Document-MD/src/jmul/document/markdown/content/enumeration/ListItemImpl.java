/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package jmul.document.markdown.content.enumeration;


import jmul.document.markdown.content.node.ContentNode;
import jmul.document.markdown.content.node.ListItemNode;
import jmul.document.markdown.content.node.ListItemNodeImpl;
import jmul.document.markdown.content.node.TreeHelper;


/**
 * An implementation of a list item.
 *
 * @author Kristian Kutin
 */
public class ListItemImpl extends ListItemNodeImpl implements ListItem {

    /**
     * The default constructor.
     */
    public ListItemImpl() {

        super();
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aText
     *        the new text for this list item
     */
    public ListItemImpl(CharSequence aText) {

        super();

        setText(aText);
    }

    /**
     * Returns the symbol which is associated with this list item (e.g.
     * a number for ordered lists or <code>*</code> for unordered lists).
     *
     * @return a symbol
     */
    @Override
    public String getSymbol() {

        ContentNode parent = TreeHelper.getParentNode(this);

        if (parent == null) {

            String message = "This node is not yet embedded in a document tree!";
            throw new UnsupportedOperationException(message);
        }

        if (TreeHelper.isUnorderedList(parent)) {

            return "*";

        } else if (TreeHelper.isOrderedList(parent)) {

            int predecessors = 1;
            while (true) {

                ListItemNode predecessor = getPredecessor();

                if (predecessor == null) {

                    break;
                }

                predecessors++;
            }

            return Integer.toString(predecessors) + ".";

        } else {

            String message = "An unsupported enumeration type was encountered!";
            throw new UnsupportedOperationException(message);
        }
    }

    /**
     * Returns a string representation of this list item.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getText();
    }

}
