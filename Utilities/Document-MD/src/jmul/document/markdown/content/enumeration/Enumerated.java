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


import jmul.document.markdown.content.paragraph.Paragraph;


/**
 * This interface describes an enumeration within a markdown file.
 *
 * @author Kristian Kutin
 */
public interface Enumerated extends Paragraph {

    /**
     * Adds the specified list item to this enumeration (i.e. appends the list
     * item to the end of the current list).
     *
     * @param aListItem
     */
    void addListItem(ListItem aListItem);

    /**
     * Returns the number of existing list items.
     *
     * @return the number of list items
     */
    int items();

    /**
     * Removes the list item at the specified index.
     *
     * @param anIndex
     *
     * @return the removed list item
     */
    ListItem removeListItem(int anIndex);

    /**
     * Returns the list item at the specified index.
     *
     * @param anIndex
     *
     * @return  the list item
     */
    ListItem getListItem(int anIndex);

}
