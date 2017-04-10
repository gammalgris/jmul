/*
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

package jmul.document.markdown.content.paragraph;


import jmul.document.markdown.content.internal.ContentNodeImpl;

import static jmul.misc.checks.ParameterCheckHelper.checkStringParameter;


/**
 * A base implementation for citations.
 *
 * @author Kristian Kutin
 */
public class CitationBase extends ContentNodeImpl implements Citation {

    /**
     * The actual text.
     */
    private String text;

    /**
     * Creates a new citation block according to the specified parameters.
     *
     * @param aText
     */
    protected CitationBase(String aText) {

        super();

        checkStringParameter(aText);

        text = aText;
    }

    /**
     * Returns the length of the headline.
     *
     * @return a length
     */
    @Override
    public int length() {

        return text.length();
    }

    /**
     * Returns the character at the specified index.
     *
     * @param index
     *
     * @return a character
     */
    @Override
    public char charAt(int index) {

        return text.charAt(index);
    }

    /**
     * Returns a substring within the specified bounds.
     *
     * @param start
     * @param end
     *
     * @return a substring
     */
    @Override
    public CharSequence subSequence(int start, int end) {

        return text.subSequence(start, end);
    }

    /**
     * Returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return text;
    }

    /**
     * A setter method.
     *
     * @param aText
     */
    public void setText(String aText) {

        text = aText;
    }

    /**
     * A getter method.
     *
     * @return a text
     */
    public String getText() {

        return text;
    }

}
