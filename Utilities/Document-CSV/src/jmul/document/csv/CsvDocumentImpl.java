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

import java.util.List;

import jmul.document.csv.structure.HeaderType;
import jmul.document.csv.structure.StructureType;
import jmul.document.type.DocumentType;

import jmul.misc.table.Table;
import jmul.misc.table.UnmodifiableTableImpl;


/**
 * This is an implementation of a CSV document where the file content
 * can be fully loaded into memory.
 *
 * @author Kristian Kutin
 */
public class CsvDocumentImpl extends CsvDocumentBase {

    /**
     * The actual content of the document.
     */
    private final Table<String> content;

    /**
     * Creates a new document according to the specified parmaeters.
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
     * @param aTable
     *        the document content
     */
    public CsvDocumentImpl(DocumentType aDocumentType, Charset aCharset, HeaderType aHeaderType,
                           StructureType aStructureType, String aColumnSeparator, String aRowSeparator,
                           Table<String> aTable) {

        super(aDocumentType, aCharset, aHeaderType, aStructureType, aColumnSeparator, aRowSeparator,
              transformColumnNamesToArray(aTable));

        content = new UnmodifiableTableImpl<>(aTable);
    }

    /**
     * Creates a new document according to the specified parmaeters.
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
     * @param aTable
     *        the document content
     */
    public CsvDocumentImpl(DocumentType aDocumentType, HeaderType aHeaderType, StructureType aStructureType,
                           String aColumnSeparator, String aRowSeparator, Table<String> aTable) {

        super(aDocumentType, aHeaderType, aStructureType, aColumnSeparator, aRowSeparator,
              transformColumnNamesToArray(aTable));

        content = new UnmodifiableTableImpl<>(aTable);
    }

    /**
     * Creates a new document according to the specified parmaeters.
     *
     * @param aCharset
     *        a charset
     * @param aDocumentType
     *        the document type
     * @param aColumnSeparator
     *        the column separator
     * @param aRowSeparator
     *        the row separator
     * @param aTable
     *        the document content
     */
    public CsvDocumentImpl(Charset aCharset, DocumentType aDocumentType, String aColumnSeparator, String aRowSeparator,
                           Table<String> aTable) {

        super(aCharset, aDocumentType, aColumnSeparator, aRowSeparator);

        content = new UnmodifiableTableImpl<>(aTable);
    }

    /**
     * Creates a new document according to the specified parmaeters.
     *
     * @param aDocumentType
     *        the document type
     * @param aColumnSeparator
     *        the column separator
     * @param aRowSeparator
     *        the row separator
     * @param aTable
     *        the document content
     */
    public CsvDocumentImpl(DocumentType aDocumentType, String aColumnSeparator, String aRowSeparator,
                           Table<String> aTable) {

        super(aDocumentType, aColumnSeparator, aRowSeparator);

        content = new UnmodifiableTableImpl<>(aTable);
    }

    /**
     * Transforms the table column names from a list into an array.
     *
     * @param aTable
     *        an input table
     *
     * @return an array of column names
     */
    private static String[] transformColumnNamesToArray(Table<String> aTable) {

        List<String> columnNames = aTable.getColumnNames();
        String[] header = new String[columnNames.size()];

        return columnNames.toArray(header);
    }

    /**
     * Returns the CSV content as table.
     *
     * @return a table
     */
    @Override
    public Table<String> getContent() {

        return content;
    }

}
