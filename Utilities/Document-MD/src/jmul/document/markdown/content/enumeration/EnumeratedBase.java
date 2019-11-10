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


import java.util.List;

import jmul.document.markdown.content.node.ContentNodeImpl;
import jmul.document.markdown.content.node.ListItemNode;


/**
 * A base implementation of an enumeration.
 *
 * @author Kristian Kutin
 */
abstract class EnumeratedBase extends ContentNodeImpl implements Enumerated {

    /**
     * The container contains all list items.
     */
    private List<ListItem> container;

    /**
     * The default constructor.
     */
    EnumeratedBase() {

        super();
    }

    /**
     * Adds the specified list item to this enumeration (i.e. appends the list
     * item to the end of the current list).
     *
     * @param aListItem
     */
    @Override
    public void addListItem(ListItem aListItem) {

        ListItem last = getLastListItem();

        ListItemNode predecessor = (ListItemNode) last;
        ListItemNode newLast = (ListItemNode) aListItem;

        newLast.setPredecessor(predecessor);
        container.add(aListItem);
    }

    /**
     * Returns the number of existing list items.
     *
     * @return the number of list items
     */
    @Override
    public int items() {

        return container.size();
    }

    /**
     * Removes the list item at the specified index.
     *
     * @param anIndex
     *
     * @return the removed list item
     */
    @Override
    public ListItem removeListItem(int anIndex) {

        ListItem toRemove = getListItem(anIndex);

        ListItemNode item = (ListItemNode) toRemove;
        ListItemNode predecessor = (ListItemNode) getPredecessor(anIndex);
        ListItemNode successor = (ListItemNode) getSuccessor(anIndex);

        item.setPredecessor(null);
        successor.setPredecessor(predecessor);

        container.remove(anIndex);

        return toRemove;
    }

    /**
     * Returns the list item at the specified index.
     *
     * @param anIndex
     *
     * @return  the list item
     */
    @Override
    public ListItem getListItem(int anIndex) {

        return container.get(anIndex);
    }

    /**
     * Returns the last list item.
     *
     * @return the last list item or <code>null</code> if the enumeration
     *         is empty.
     */
    public ListItem getLastListItem() {

        if (items() == 0) {

            return null;
        }

        return container.get(items() - 1);
    }

    /**
     * Returns the predecessor of the list item at the specified index.
     *
     * @param anIndex
     *
     * @return the predecessor of the list item at the specified index or
     *          <code>null</code> if no predecessor exists
     */
    public ListItem getPredecessor(int anIndex) {

        int indexPredecessor = anIndex - 1;

        if (indexPredecessor < 0) {

            return null;
        }

        return getListItem(indexPredecessor);
    }

    /**
     * Returns the successor of the list item at the specified index.
     *
     * @param anIndex
     *
     * @return the successor of the list item at the specified index or
     *          <code>null</code> if no successor exists
     */
    public ListItem getSuccessor(int anIndex) {

        int indexSuccessor = anIndex + 1;

        if (indexSuccessor >= items()) {

            return null;
        }

        return getListItem(indexSuccessor);
    }

}
