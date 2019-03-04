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

package jmul.document.text;


import java.nio.charset.Charset;

import jmul.document.type.DocumentType;

import jmul.misc.text.Text;


/**
 * An implementation of a text document.
 *
 * @author Kristian Kutin
 */
public class TextDocumentImpl extends TextDocumentBase {

    /**
     * The actual content of the document.
     */
    private final Text content;

    /**
     * Creates a new document according to the specified parmaeters.
     *
     * @param aDocumentType
     *        the document type
     * @param aCharset
     *        the charset of the document
     * @param aLineSeparator
     *        the line separator within the document
     * @param aText
     *        the document text
     */
    public TextDocumentImpl(DocumentType aDocumentType, Charset aCharset, String aLineSeparator, Text aText) {

        super(aDocumentType, aCharset, aLineSeparator);

        content = aText;
    }

    /**
     * Returns the content as a text container.
     *
     * @return the content
     */
    @Override
    public Text getContent() {

        return content;
    }

    /**
     * Returns the size of the content.
     *
     * @return size of the content (character count)
     */
    @Override
    public int getSize() {

        return content.getContentAsString(getStructure().getLineSeparator()).length();
    }

}
