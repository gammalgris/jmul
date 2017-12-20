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

package jmul.misc.text;


/**
 * This interface describes a entity that contains text that can be
 * modified.
 *
 * @author Kristian Kutin
 */
public interface ModifiableText extends Text {

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
    String replaceLine(int aLineIndex, String aReplacementLine);

    /**
     * Appends a new line at the end of the text.
     *
     * @param aNewLine
     *        the new line
     */
    void addLine(String aNewLine);

    /**
     * Inserts the specified line at the specified index.
     *
     * @param aLineIndex
     *        a line index
     * @param aNewLine
     *        the new line
     */
    void insertLine(int aLineIndex, String aNewLine);

    /**
     * Removes the line at the specified index.
     *
     * @param aLineIndex
     *        a line index
     *
     * @return the old line
     */
    String removeLine(int aLineIndex);

}
