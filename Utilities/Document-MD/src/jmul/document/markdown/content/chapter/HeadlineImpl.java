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

package jmul.document.markdown.content.chapter;


import jmul.document.markdown.content.node.ContentNode;
import jmul.document.markdown.content.node.TextNodeImpl;
import jmul.document.markdown.content.node.TreeHelper;


/**
 * An implementation of a headline.
 *
 * @author Kristian Kutin
 */
public class HeadlineImpl extends TextNodeImpl implements Headline {

    /**
     * The default constructor.
     */
    public HeadlineImpl() {

        super();
    }

    /**
     * Creates a new headline according to the specified parameters.
     *
     * @param aText
     *        the headline text
     */
    public HeadlineImpl(CharSequence aText) {

        super();

        setText(aText.toString());
    }

    /**
     * Returns the type of the headline (e.g. type 1 is a headline on the
     * topmost level, type 2 is a headline below the topmost level, etc.)
     * as a numeric value.
     *
     * @return a headline type
     */
    @Override
    public int getHeadlineType() {

        int level = 1;
        ContentNode node = (ContentNode) this;
        ContentNode parent = node.getParent();

        while (true) {

            if (parent == null) {

                break;
            }

            if (TreeHelper.isChapterNode(parent)) {

                level++;
            }

            parent = parent.getParent();
        }

        return level;
    }

}
