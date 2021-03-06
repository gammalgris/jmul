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

package jmul.document.binary;


import java.nio.ByteOrder;

import jmul.document.DocumentBase;
import jmul.document.binary.structure.BinaryFileStructure;
import jmul.document.binary.structure.WordLengths;
import jmul.document.type.DocumentType;


/**
 * A base class for binary files.
 *
 * @author Kristian Kutin
 *
 */
abstract class BinaryFileBase extends DocumentBase<BinaryFileStructure> implements BinaryFile {

    /**
     * Details about the document's structure.
     */
    private final BinaryFileStructure structure;

    /**
     * Creates a new instance according to the specified parmaeters.
     *
     * @param aDocumentType
     * @param aByteOrder
     * @param aWordLength
     */
    protected BinaryFileBase(DocumentType aDocumentType, ByteOrder aByteOrder, WordLengths aWordLength) {

        super(aDocumentType);

        structure = new BinaryFileStructure(aByteOrder, aWordLength);
    }

    /**
     * Returns a document's structure.
     *
     * @return a document structure
     */
    @Override
    public BinaryFileStructure getStructure() {

        return structure;
    }

}
