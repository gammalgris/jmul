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

package jmul.document.csv;


import java.nio.charset.Charset;

import jmul.document.DocumentBase;
import jmul.document.csv.structure.CsvStructure;
import jmul.document.csv.structure.HeaderType;
import static jmul.document.csv.structure.HeaderType.NO_HEADER;
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
     * @param aHeaderType
     * @param aColumnSeparator
     * @param aRowSeparator
     * @param someColumnNames
     */
    protected CsvDocumentBase(DocumentType aDocumentType, HeaderType aHeaderType, String aColumnSeparator,
                              String aRowSeparator, String... someColumnNames) {

        super(aDocumentType);

        structure =
            new CsvStructure(Charset.defaultCharset(), aHeaderType, aColumnSeparator, aRowSeparator, someColumnNames);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aFileName
     * @param aColumnSeparator
     * @param aRowSeparator
     */
    protected CsvDocumentBase(DocumentType aDocumentType, String aColumnSeparator, String aRowSeparator) {

        super(aDocumentType);

        structure = new CsvStructure(Charset.defaultCharset(), aColumnSeparator, aRowSeparator);
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
