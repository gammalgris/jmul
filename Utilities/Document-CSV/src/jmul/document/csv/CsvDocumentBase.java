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

package jmul.document.csv;


import java.nio.charset.Charset;

import jmul.document.DocumentBase;
import jmul.document.csv.structure.CsvStructure;
import jmul.document.csv.structure.HeaderType;
import jmul.document.csv.structure.StructureType;
import jmul.document.type.DocumentType;


/**
 * A base implementation of a CSV style document.
 *
 * @author Kristian Kutin
 */
abstract class CsvDocumentBase extends DocumentBase<CsvStructure> implements CsvDocument {

    /**
     * Details about the document's structure.
     */
    private final CsvStructure structure;

    /**
     * Creates a new instance according to the specified parmaeters.
     *
     * @param aDocumentType
     *        the document type
     * @param aCharset
     *        the charset
     * @param aHeaderType
     *        the header type
     * @param aStructureType
     *        the structure type
     * @param aColumnSeparator
     *        the column separator
     * @param aRowSeparator
     *        the row separator
     * @param someColumnNames
     *        the column names
     */
    protected CsvDocumentBase(DocumentType aDocumentType, Charset aCharset, HeaderType aHeaderType,
                              StructureType aStructureType, String aColumnSeparator, String aRowSeparator,
                              String... someColumnNames) {

        super(aDocumentType);

        structure =
            new CsvStructure(aCharset, aHeaderType, aStructureType, aColumnSeparator, aRowSeparator, someColumnNames);
    }

    /**
     * Creates a new instance according to the specified parmaeters.
     *
     * @param aDocumentType
     *        the document type
     * @param aHeaderType
     *        the header type
     * @param aStructureType
     *        the structure type
     * @param aColumnSeparator
     *        the column separator
     * @param aRowSeparator
     *        the row separator
     * @param someColumnNames
     *        the column names
     */
    protected CsvDocumentBase(DocumentType aDocumentType, HeaderType aHeaderType, StructureType aStructureType,
                              String aColumnSeparator, String aRowSeparator, String... someColumnNames) {

        this(aDocumentType, Charset.defaultCharset(), aHeaderType, aStructureType, aColumnSeparator, aRowSeparator,
             someColumnNames);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset
     * @param aDocumentType
     *        the document type
     * @param aColumnSeparator
     *        the column separator
     * @param aRowSeparator
     *        the row separator
     */
    protected CsvDocumentBase(Charset aCharset, DocumentType aDocumentType, String aColumnSeparator,
                              String aRowSeparator) {

        super(aDocumentType);

        structure = new CsvStructure(aCharset, aColumnSeparator, aRowSeparator);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aDocumentType
     *        the document type
     * @param aColumnSeparator
     *        the column separator
     * @param aRowSeparator
     *        the row separator
     */
    protected CsvDocumentBase(DocumentType aDocumentType, String aColumnSeparator, String aRowSeparator) {

        this(Charset.defaultCharset(), aDocumentType, aColumnSeparator, aRowSeparator);
    }

    /**
     * Returns a document's structure.
     *
     * @return a document structure
     */
    @Override
    public CsvStructure getStructure() {

        return structure;
    }

}
