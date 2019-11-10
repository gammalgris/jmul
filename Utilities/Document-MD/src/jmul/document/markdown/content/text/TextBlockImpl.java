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


import jmul.document.markdown.content.node.TextNodeImpl;


/**
 * An implementation of a text block.
 *
 * @author Kristian Kutin
 */
public class TextBlockImpl extends TextNodeImpl implements TextBlock {

    /**
     * The default constructor.
     */
    TextBlockImpl() {

        super();
    }

    /**
     * Creates a new text block according to the specified parameters.
     *
     * @param aText
     *        the text content
     */
    public TextBlockImpl(CharSequence aText) {

        this();

        setText(aText);
    }

}
