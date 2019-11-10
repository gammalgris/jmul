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
 * An implementation of a text node.
 *
 * @author Kristian Kutin
 */
public class TextNodeImpl implements TextNode {

    /**
     * The actual text content.
     */
    private StringBuilder buffer;

    /**
     * The default constructor.
     */
    public TextNodeImpl() {

        buffer = new StringBuilder();
    }

    /**
     * Returns the length of the text content.
     *
     * @return a length
     */
    @Override
    public int length() {

        return buffer.length();
    }

    /**
     * Returns the character at the specified index position.
     *
     * @param i
     *        an index position within the text content
     *
     * @return a character
     */
    @Override
    public char charAt(int i) {

        return buffer.charAt(i);
    }

    /**
     * Returns a text within the text content with the specified index positions.
     *
     * @param i
     *        a start index
     * @param i2
     *        an end index
     *
     * @return a text
     */
    @Override
    public CharSequence subSequence(int i, int i2) {

        return buffer.subSequence(i, i2);
    }

    /**
     * Clears the text content of this text node.
     */
    @Override
    public void clear() {

        buffer.delete(0, length() - 1);
    }

    /**
     * Sets the specified text as the text content of this text node.
     *
     * @param aText
     */
    @Override
    public void setText(CharSequence aText) {

        buffer.replace(0, length() - 1, aText.toString());
    }

    /**
     * Returns the text content of this text node.
     *
     * @return a text
     */
    @Override
    public String getText() {

        return buffer.toString();
    }

    /**
     * Appends the specified text to this text node.
     *
     * @param charSequence
     *        a text
     *
     * @return this text node
     */
    @Override
    public TextNode append(CharSequence charSequence) {

        buffer.append(charSequence);
        return this;
    }

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
    @Override
    public TextNode append(CharSequence charSequence, int i, int i2) {

        buffer.append(charSequence, i, i2);
        return this;
    }

    /**
     * Appends the specified character to this text node.
     *
     * @param c
     *        a character
     *
     * @return this text node
     */
    @Override
    public TextNode append(char c) {

        buffer.append(c);
        return this;
    }

    /**
     * Returns a string representation of this text node.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getText();
    }

}
