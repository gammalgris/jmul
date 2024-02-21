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

package jmul.csv.reader;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.Charset;

import java.util.List;

import jmul.document.csv.CsvDocument;
import jmul.document.csv.CsvDocumentImpl;
import static jmul.document.csv.CsvHelper.normalizeValue;
import jmul.document.csv.structure.HeaderType;
import static jmul.document.csv.structure.HeaderType.FIRST_LINE_IS_HEADER;
import static jmul.document.csv.structure.HeaderType.NO_HEADER;
import jmul.document.csv.structure.StructureType;
import jmul.document.type.DocumentType;
import jmul.document.type.DocumentTypes;

import jmul.metainfo.annotations.Modified;

import jmul.misc.table.ModifiableTable;
import jmul.misc.table.ModifiableTableImpl;

import static jmul.string.Constants.NEW_LINE_WINDOWS;
import static jmul.string.Constants.SEMICOLON;


/**
 * A base implementation of a document reader.
 *
 * @author Kristian Kutin
 */
abstract class CsvDocumentReaderBase implements CsvDocumentReader {

    /**
     * The default charset.
     */
    protected static final Charset DEFAULT_CHARSET = Charset.defaultCharset();

    /**
     * The default column separator.
     */
    protected static final String DEFAULT_COLUMN_SEPARATOR = SEMICOLON;

    /**
     * The default row separator.
     */
    protected static final String DEFAULT_ROW_SEPARATOR = NEW_LINE_WINDOWS;

    /**
     * The default header type.
     */
    protected static final HeaderType DEFAULT_HEADER_TYPE = HeaderType.FIRST_LINE_IS_HEADER;

    /**
     * The default structure type.
     */
    protected static final StructureType DEFAULT_STRUCTURE_TYPE = StructureType.RIGID;

    /**
     * The charset which will be used to read from files.
     */
    private final Charset charset;

    /**
     * The column separator which is expected.
     */
    private final String columnSeparator;

    /**
     * The row separator which is expected.
     */
    private final String rowSeparator;

    /**
     * The header type which is expected.
     */
    private final HeaderType headerType;

    /**
     * The structure type which is expected.
     */
    private final StructureType structureType;

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aCharset
     *        the expected charset
     * @param aHeaderType
     *        the expected header type
     * @param aStructureType
     *        the expected structure type
     * @param aColumnSeparator
     *        the expected column separator
     * @param aRowSeparator
     *        the expected row separator
     */
    protected CsvDocumentReaderBase(Charset aCharset, HeaderType aHeaderType, StructureType aStructureType,
                                    String aColumnSeparator, String aRowSeparator) {

        super();

        charset = aCharset;
        headerType = aHeaderType;
        structureType = aStructureType;
        columnSeparator = aColumnSeparator;
        rowSeparator = aRowSeparator;
    }

    /**
     * Reads from the specified file and returns a document that
     * contains the file content.
     *
     * @param aFilename
     *        the name of the input file
     *
     * @return a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the file
     */
    @Override
    public CsvDocument readFrom(String aFilename) throws IOException {

        return readFrom(new File(aFilename));
    }

    /**
     * Reads from the specified file and returns a document that
     * contains the file content.
     *
     * @param aFile
     *        the input file
     *
     * @return a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the file
     */
    @Override
    public CsvDocument readFrom(File aFile) throws IOException {

        DocumentType documentType = DocumentTypes.getDocumentType(aFile.getName());
        ModifiableTable<String> table = new ModifiableTableImpl<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(aFile), charset))) {

            parseFirstLine(br, table);
            parseRemainingContent(br, table);
        }

        return new CsvDocumentImpl(documentType, charset, headerType, structureType, columnSeparator, rowSeparator,
                                   table);
    }

    /**
     * A getter method.
     *
     * @return the expected charset
     */
    public Charset getCharset() {

        return charset;
    }

    /**
     * A getter method.
     *
     * @return the expected column separator
     */
    public String getColumnSeparator() {

        return columnSeparator;
    }

    /**
     * A getter method.
     *
     * @return the expected row separator
     */
    public String getRowSeparator() {

        return rowSeparator;
    }

    /**
     * A getter method.
     *
     * @return the expected header type
     */
    public HeaderType getHeaderType() {

        return headerType;
    }

    /**
     * A getter method.
     *
     * @return the assumed structure type
     */
    public StructureType getStructureType() {

        return structureType;
    }

    /**
     * The first line is parsed for identifying the number of columns in a CSV file. Additionally the first line
     * may contain data or a header line. The specified table is updated accordingly.
     *
     * @param aReader
     *        a handle on the actual file
     * @param aTable
     *        a modifiable table
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the CSV file
     */
    protected abstract void parseFirstLine(BufferedReader aReader,
                                           @Modified ModifiableTable<String> aTable) throws IOException;

    /**
     * The remaining content of a CSV file is parsed and the specified table is updated accordingly.
     *
     * @param aReader
     *        a handle on the actual file
     * @param aTable
     *        a modifiable table
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the CSV file
     */
    protected abstract void parseRemainingContent(BufferedReader aReader,
                                                  @Modified ModifiableTable<String> aTable) throws IOException;

    /**
     * Resizes the specified table according to the specified column and row numbers. If the table should be
     * larger than the specified column and row count then the table won't be changed.
     *
     * @param aTable
     *        a modifiable table
     * @param newColumns
     *        the new number of columns
     * @param newRows
     *        the new number of rows
     */
    protected static void resizeTable(@Modified ModifiableTable<String> aTable, int newColumns, int newRows) {

        int columns = aTable.columns();
        int rows = aTable.rows();

        for (int a = columns; a < newColumns; a++) {

            aTable.addColumn();
        }

        for (int a = rows; a < newRows; a++) {

            aTable.addRow();
        }
    }

    /**
     * Parses a line. The caller has to make sure that the line is complete.
     *
     * @param aTable
     *        the table which is filled
     * @param allSubstrings
     *        the line (i.e. its components) which is to be parsed
     */
    protected void parseLine(@Modified ModifiableTable<String> aTable, List<String> allSubstrings) {

        int maxColumns = allSubstrings.size();
        HeaderType actualHeaderType = getHeaderType();

        if (actualHeaderType == FIRST_LINE_IS_HEADER) {

            resizeTable(aTable, maxColumns, 0);

            for (int a = 0; a < maxColumns; a++) {

                aTable.setColumnName(a, allSubstrings.get(a));
            }

        } else if (actualHeaderType == NO_HEADER) {

            resizeTable(aTable, maxColumns, 1);

            for (int a = 0; a < maxColumns; a++) {

                aTable.updateCell(a, 0, normalizeValue(allSubstrings.get(a)));
            }

        } else {

            throw new UnsupportedOperationException();
        }
    }

}
