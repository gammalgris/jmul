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
import java.io.IOException;

import java.nio.charset.Charset;

import java.util.List;

import static jmul.document.csv.CsvHelper.normalizeValue;
import jmul.document.csv.structure.HeaderType;
import jmul.document.csv.structure.StructureType;
import static jmul.document.csv.structure.StructureType.FLEXIBLE;
import static jmul.document.csv.structure.StructureType.RIGID;

import jmul.io.text.ReadBuffer;
import jmul.io.text.TextFileHelper;

import jmul.metainfo.annotations.Modified;

import jmul.misc.table.ModifiableTable;

import jmul.string.TextHelper;


/**
 * An implementation for a CSV document reader which reads simple CSV files with following
 * properties:
 * <ul>
 * <li>(optional) The first line of the CSV file can be a header line.</li>
 * <li>(optional) The CSV file doesn't need a header line.</li>
 * <li>(optional) Each row can have the same number of columns.</li>
 * <li>(optional) Each row can have a varying number of columns.</li>
 * <li>(mandatory) Cells are not allowed to contain line separators.</li>
 * <li>(mandatory) " and ' can be used to quote a cell's content (i.e. in part or whole).
 *     Thus ' cannot be used as apostrophe.
 * </li>
 * </ul>
 * The reader implements following requirements:
 * <ul>
 * <li>Any string can be used as column or row separator. The default column separator
 *     is a semicolon. The default row separator is the system's line separator.
 * </li>
 * <li>Various encodings can be used (see supported charsets by Java). The default
 *     encoding is the current system's default encoding.</li>
 * </ul>
 * <br>
 * <i>Note:<br>
 * Freely add additional constructors if some parameter variations are missing.</i>
 *
 * @author Kristian Kutin
 */
public class CsvDocumentReaderImpl extends CsvDocumentReaderBase {

    /**
     * The default constructor.
     */
    public CsvDocumentReaderImpl() {

        super(DEFAULT_CHARSET, DEFAULT_HEADER_TYPE, DEFAULT_STRUCTURE_TYPE, DEFAULT_COLUMN_SEPARATOR,
              DEFAULT_ROW_SEPARATOR);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset
     */
    public CsvDocumentReaderImpl(Charset aCharset) {

        super(aCharset, DEFAULT_HEADER_TYPE, DEFAULT_STRUCTURE_TYPE, DEFAULT_COLUMN_SEPARATOR, DEFAULT_ROW_SEPARATOR);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aColumnSeparator
     *        the assumed column separator
     */
    public CsvDocumentReaderImpl(String aColumnSeparator) {

        super(DEFAULT_CHARSET, DEFAULT_HEADER_TYPE, DEFAULT_STRUCTURE_TYPE, aColumnSeparator, DEFAULT_ROW_SEPARATOR);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset
     * @param aColumnSeparator
     *        the assumed column separator
     */
    public CsvDocumentReaderImpl(Charset aCharset, String aColumnSeparator) {

        super(aCharset, DEFAULT_HEADER_TYPE, DEFAULT_STRUCTURE_TYPE, aColumnSeparator, DEFAULT_ROW_SEPARATOR);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aColumnSeparator
     *        the assumed column separator
     * @param aRowSeparator
     *        the assumed row separator
     */
    public CsvDocumentReaderImpl(String aColumnSeparator, String aRowSeparator) {

        super(DEFAULT_CHARSET, DEFAULT_HEADER_TYPE, DEFAULT_STRUCTURE_TYPE, aColumnSeparator, aRowSeparator);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aHeaderType
     *        the assumed header type
     * @param aStructureType
     *        the assumed structure type
     */
    public CsvDocumentReaderImpl(HeaderType aHeaderType, StructureType aStructureType) {

        super(DEFAULT_CHARSET, aHeaderType, aStructureType, DEFAULT_COLUMN_SEPARATOR, DEFAULT_ROW_SEPARATOR);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset
     * @param aHeaderType
     *        the assumed header type
     * @param aStructureType
     *        the assumed structure type
     */
    public CsvDocumentReaderImpl(Charset aCharset, HeaderType aHeaderType, StructureType aStructureType) {

        super(aCharset, aHeaderType, aStructureType, DEFAULT_COLUMN_SEPARATOR, DEFAULT_ROW_SEPARATOR);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset
     * @param aColumnSeparator
     *        the assumed column separator
     * @param aRowSeparator
     *        the assumed row separator
     */
    public CsvDocumentReaderImpl(Charset aCharset, String aColumnSeparator, String aRowSeparator) {

        super(aCharset, DEFAULT_HEADER_TYPE, DEFAULT_STRUCTURE_TYPE, aColumnSeparator, aRowSeparator);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aHeaderType
     *        the assumed header type
     * @param aColumnSeparator
     *        the assumed column separator
     * @param aRowSeparator
     *        the assumed row separator
     */
    public CsvDocumentReaderImpl(HeaderType aHeaderType, String aColumnSeparator, String aRowSeparator) {

        super(DEFAULT_CHARSET, aHeaderType, DEFAULT_STRUCTURE_TYPE, aColumnSeparator, aRowSeparator);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset
     * @param aHeaderType
     *        the assumed header type
     * @param aStructureType
     *        the assumed structure type
     * @param aColumnSeparator
     *        the assumed column separator
     * @param aRowSeparator
     *        the assumed row separator
     */
    public CsvDocumentReaderImpl(Charset aCharset, HeaderType aHeaderType, StructureType aStructureType,
                                 String aColumnSeparator, String aRowSeparator) {

        super(aCharset, aHeaderType, aStructureType, aColumnSeparator, aRowSeparator);
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
    @Override
    protected void parseFirstLine(BufferedReader aReader, @Modified ModifiableTable<String> aTable) throws IOException {

        ReadBuffer result = TextFileHelper.readLine(aReader, getRowSeparator());

        if (result.isEndOfFile()) {

            return;
        }


        String line = result.getLine();
        List<String> substrings = TextHelper.splitLine(line, getColumnSeparator());


        parseLine(aTable, substrings);
    }

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
    @Override
    protected void parseRemainingContent(BufferedReader aReader,
                                         @Modified ModifiableTable<String> aTable) throws IOException {

        int currentRow = aTable.rows();

        while (true) {

            ReadBuffer result = TextFileHelper.readLine(aReader, getRowSeparator());

            if (result.isEndOfFile() && result.isEmpty()) {

                return;
            }


            String line = result.getLine();
            List<String> substrings = TextHelper.splitLine(line, getColumnSeparator());


            int actualColumns = substrings.size();

            currentRow++;


            StructureType actualStructureType = getStructureType();
            if (actualStructureType == RIGID) {

                int previousColumns = aTable.columns();

                if (previousColumns != actualColumns) {

                    String message = "A rigid structure is expected but a flexible structure was encountered!";
                    throw new CsvStructureException(message);
                }

                resizeTable(aTable, previousColumns, currentRow);

            } else if (actualStructureType == FLEXIBLE) {

                resizeTable(aTable, actualColumns, currentRow);

            } else {

                throw new UnsupportedOperationException();
            }


            int newRowIndex = currentRow - 1;

            for (int a = 0; a < actualColumns; a++) {

                aTable.updateCell(a, newRowIndex, normalizeValue(substrings.get(a)));
            }
        }
    }

}
