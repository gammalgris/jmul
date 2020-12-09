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

package jmul.document.markdown.content.text;


import java.util.List;

import jmul.document.markdown.content.node.ContentNodeImpl;

import jmul.misc.text.ModifiableText;
import jmul.misc.text.ModifiableTextImpl;


/**
 * An implementation of a text block.
 *
 * @author Kristian Kutin
 */
public class TextBlockImpl extends ContentNodeImpl implements TextBlock {

    /**
     * Contains the actual text.
     */
    private ModifiableText text;

    /**
     * The default constructor.
     */
    public TextBlockImpl() {

        super();

        text = new ModifiableTextImpl();
    }

    /**
     * Returns the text as list of all lines.
     *
     * @return a list of all lines
     */
    @Override
    public List<String> getContent() {

        return text.getContent();
    }

    /**
     * Returns the line with the specified line index.
     *
     * @param aLineIndex
     *        the index of a line
     *
     * @return a line
     */
    @Override
    public String getLine(int aLineIndex) {

        return text.getLine(aLineIndex);
    }

    /**
     * Returns the line count.
     *
     * @return a line count
     */
    @Override
    public int lines() {

        return text.lines();
    }

    /**
     * Returns the content as a string. The default line separator for
     * the current operating is used.
     *
     * @return the whole content
     */
    @Override
    public String getContentAsString() {

        return text.getContentAsString();
    }

    /**
     * Returns the content as a string. The specified line separator is
     * used.
     *
     * @param aLineSeparator
     *        a single character or several characters that represent
     *        the line seperator
     *
     * @return the whole content
     */
    @Override
    public String getContentAsString(String aLineSeparator) {

        return text.getContentAsString(aLineSeparator);
    }

    /**
     * Replaces the line at the specified line index with the specified
     * line.
     *
     * @param aLineIndex
     *        a line index
     * @param aReplacementLine
     *        the new line
     *
     * @return the old line
     */
    @Override
    public String replaceLine(int aLineIndex, String aReplacementLine) {

        return text.replaceLine(aLineIndex, aReplacementLine);
    }

    /**
     * Appends a new line at the end of the text.
     *
     * @param aNewLine
     *        the new line
     */
    @Override
    public void addLine(String aNewLine) {

        text.addLine(aNewLine);
    }

    /**
     * Inserts the specified line at the specified index.
     *
     * @param aLineIndex
     *        a line index
     * @param aNewLine
     *        the new line
     */
    @Override
    public void insertLine(int aLineIndex, String aNewLine) {

        text.insertLine(aLineIndex, aNewLine);
    }

    /**
     * Removes the line at the specified index.
     *
     * @param aLineIndex
     *        a line index
     *
     * @return the old line
     */
    @Override
    public String removeLine(int aLineIndex) {

        return text.removeLine(aLineIndex);
    }

}
