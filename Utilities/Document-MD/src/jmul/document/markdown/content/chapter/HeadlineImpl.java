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
import jmul.document.markdown.content.node.ContentNodeImpl;

import static jmul.string.Constants.EMPTY_STRING;


/**
 * An implementation of a headline.
 *
 * @author Kristian Kutin
 */
public class HeadlineImpl extends ContentNodeImpl implements Headline {

    /**
     * Contains the actual headline.
     */
    private final CharSequence headline;

    /**
     * The default constructor.
     */
    public HeadlineImpl() {

        super();

        headline = EMPTY_STRING;
    }

    /**
     * Creates a new headline according to the specified parameters.
     *
     * @param aHeadline
     *        the headline text
     */
    public HeadlineImpl(CharSequence aHeadline) {

        super();

        if (aHeadline == null) {

            headline = EMPTY_STRING;

        } else {

            headline = aHeadline;
        }
    }

    /**
     * Returns the type of the headline (e.g. type 1 is a headline on the topmost level,
     * type 2 is a headline below the topmost level, etc.) as a numeric value. A zero
     * indicates that tghis headline has not yet been assigned to a chapter.
     *
     * @return a headline type
     */
    @Override
    public int getHeadlineType() {

        int level = 0;
        ContentNode node = this;

        while (true) {

            Class actualClass = node.getClass();
            if (Chapter.class.isAssignableFrom(actualClass)) {

                level++;
            }

            if (!node.hasParent()) {

                break;
            }

            node = node.getParent();
        }

        return level;
    }

    /**
     * Returns the length of the headline string.
     *
     * @return the length of the headline string
     */
    @Override
    public int length() {

        return headline.length();
    }

    /**
     * Returns the character at the specified index position.
     *
     * @param index
     *        an index position (i.e. a number is zero or higher and lesser than the headline length)
     *
     * @return a character
     */
    @Override
    public char charAt(int index) {

        return headline.charAt(index);
    }

    /**
     * Returns a subsequence of this headline specified by the specified index parameters.
     *
     * @param start
     *        a start index (i.e. a number is zero or higher and lesser than the end index)
     * @param end
     *        an end index (i.e. a number is higher than the start index and lesser than the headline length)
     *
     * @return a sub sequence
     */
    @Override
    public CharSequence subSequence(int start, int end) {

        return headline.subSequence(start, end);
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a string representation
     */
    public String toString() {

        return headline.toString();
    }

}
