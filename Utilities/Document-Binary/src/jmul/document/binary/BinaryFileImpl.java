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

package jmul.document.binary;


import java.nio.ByteOrder;

import jmul.document.binary.structure.WordLengths;
import jmul.document.type.DocumentType;


/**
 * An implementation of a binary file.
 *
 * @author Kristian Kutin
 */
public class BinaryFileImpl extends BinaryFileBase {

    /**
     * The byte content / raw data of a file.
     */
    private final byte[] content;

    /**
     * Creates a new document according to the specified parmaeters.
     *
     * @param aDocumentType
     * @param someContent
     * @param aWordLength
     */
    public BinaryFileImpl(DocumentType aDocumentType, ByteOrder aByteOrder, WordLengths aWordLength,
                          byte[] someContent) {

        super(aDocumentType, aByteOrder, aWordLength);

        content = someContent;
    }

    /**
     * A getter method.
     *
     * @return the byte content / raw data of a file
     */
    @Override
    public byte[] getContent() {

        return content;
    }

    /**
     * Returns the size of the file content.
     *
     * @return a content size
     */
    @Override
    public int getSize() {

        return getContent().length;
    }

}
