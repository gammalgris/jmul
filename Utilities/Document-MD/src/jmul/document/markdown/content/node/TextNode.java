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

package jmul.document.markdown.content.node;


/**
 * This interface describes a text node.
 *
 * @author Kristian Kutin
 *
 * @deprecated Remove dependencies and delete this interface and its implementation.
 */
@Deprecated
public interface TextNode extends CharSequence {

    /**
     * Clears the text content of this text node.
     */
    void clear();

    /**
     * Sets the specified text as the text content of this text node.
     *
     * @param aText
     */
    void setText(CharSequence aText);

    /**
     * Returns the text content of this text node.
     *
     * @return a text
     */
    String getText();

    /**
     * Appends the specified text to this text node.
     *
     * @param charSequence
     *        a text
     *
     * @return this text node
     */
    TextNode append(CharSequence charSequence);

    /**
     * Appends a subsequence of the specified text to this text node.
     *
     * @param charSequence
     *        a subsequence
     * @param i
     *        a start index
     * @param i2
     *        an end index
     *
     * @return this text node
     */
    TextNode append(CharSequence charSequence, int i, int i2);

    /**
     * Appends the specified character to this text node.
     *
     * @param c
     *        a character
     *
     * @return this text node
     */
    TextNode append(char c);

}
