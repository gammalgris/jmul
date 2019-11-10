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

package jmul.misc.text;


import java.util.List;

import static jmul.string.Constants.FILE_SEPARATOR;


/**
 * A base implementation for a text container.
 *
 * @author Kristian Kutin
 */
abstract class TextBase implements Text {

    /**
     * The actual text container.
     */
    private final List<String> content;

    /**
     * The default constructor.
     *
     * @param allLines
     *        the whole text
     */
    protected TextBase(List<String> allLines) {

        content = allLines;
    }

    /**
     * Returns the text as list of all lines.
     *
     * @return a list of all lines
     */
    @Override
    public List<String> getContent() {

        return content;
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

        return content.get(aLineIndex);
    }

    /**
     * Returns the line count.
     *
     * @return a line count
     */
    @Override
    public int lines() {

        return content.size();
    }

    /**
     * Returns the content as a string. The default line separator for
     * the current operating is used.
     *
     * @return the whole content
     */
    @Override
    public String getContentAsString() {

        return getContentAsString(FILE_SEPARATOR);
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

        StringBuilder buffer = new StringBuilder();

        for (String line : content) {

            buffer.append(line);
            buffer.append(aLineSeparator);
        }

        return buffer.toString();
    }

}
