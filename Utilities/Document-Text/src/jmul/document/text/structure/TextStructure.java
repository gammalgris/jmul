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

package jmul.document.text.structure;


import java.nio.charset.Charset;

import jmul.document.structure.Structure;


/**
 * This class describes a document structure for text files.
 *
 * @author Kristian Kutin
 */
public class TextStructure implements Structure {

    /**
     * The charset of the text file.
     */
    private final Charset charset;

    /**
     * The line separator used in the text file.
     */
    private final String lineSeparator;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aCharset
     *        the charset of the text document
     * @param aLineSeparator
     *        the line separator which is used within the text document
     */
    public TextStructure(Charset aCharset, String aLineSeparator) {

        charset = aCharset;
        lineSeparator = aLineSeparator;
    }

    /**
     * Returns the document's charset.
     *
     * @return a charset
     */
    public Charset getCharset() {

        return charset;
    }

    /**
     * Return the line separator used in the text file.
     *
     * @return a line separator
     */
    public String getLineSeparator() {

        return lineSeparator;
    }

}
