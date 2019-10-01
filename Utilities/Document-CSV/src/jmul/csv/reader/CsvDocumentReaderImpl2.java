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

package jmul.csv.reader;


import java.io.BufferedReader;
import java.io.IOException;

import java.nio.charset.Charset;

import java.util.List;

import static jmul.document.csv.CsvHelper.normalizeValue;
import jmul.document.csv.structure.HeaderType;
import static jmul.document.csv.structure.HeaderType.FIRST_LINE_IS_HEADER;
import static jmul.document.csv.structure.HeaderType.NO_HEADER;
import jmul.document.csv.structure.StructureType;
import static jmul.document.csv.structure.StructureType.FLEXIBLE;
import static jmul.document.csv.structure.StructureType.RIGID;

import jmul.io.text.ReadBuffer;
import jmul.io.text.TextFileHelper;

import jmul.metainfo.annotations.Modified;

import jmul.misc.table.ModifiableTable;

import jmul.string.QuoteNotClosedException;
import jmul.string.TextHelper;


/**
 * An implementation for a CSV document reader. The reader expects a uniformly
 * structured CSV file with
 * <ul>
 * <li>a defined header line,</li>
 * <li>a defined column separator,</li>
 * <li>in each line exists the same number of columns as indicated by the header line</li>
 * <li>and a table row may be spread along several lines</li>
 * </ul>
 *
 * @author Kristian Kutin
 */
public class CsvDocumentReaderImpl2 extends CsvDocumentReaderBase {

    /**
     * The default constructor.
     */
    public CsvDocumentReaderImpl2() {

        super(DEFAULT_CHARSET, DEFAULT_HEADER_TYPE, DEFAULT_STRUCTURE_TYPE, DEFAULT_COLUMN_SEPARATOR,
              DEFAULT_ROW_SEPARATOR);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset
     */
    public CsvDocumentReaderImpl2(Charset aCharset) {

        super(aCharset, DEFAULT_HEADER_TYPE, DEFAULT_STRUCTURE_TYPE, DEFAULT_COLUMN_SEPARATOR, DEFAULT_ROW_SEPARATOR);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aColumnSeparator
     *        the assumed column separator
     */
    public CsvDocumentReaderImpl2(String aColumnSeparator) {

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
    public CsvDocumentReaderImpl2(Charset aCharset, String aColumnSeparator) {

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
    public CsvDocumentReaderImpl2(String aColumnSeparator, String aRowSeparator) {

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
    public CsvDocumentReaderImpl2(HeaderType aHeaderType, StructureType aStructureType) {

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
    public CsvDocumentReaderImpl2(Charset aCharset, HeaderType aHeaderType, StructureType aStructureType) {

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
    public CsvDocumentReaderImpl2(Charset aCharset, String aColumnSeparator, String aRowSeparator) {

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
    public CsvDocumentReaderImpl2(HeaderType aHeaderType, String aColumnSeparator, String aRowSeparator) {

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
    public CsvDocumentReaderImpl2(Charset aCharset, HeaderType aHeaderType, StructureType aStructureType,
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

        StringBuilder buffer = new StringBuilder();
        List<String> substrings;

        while (true) {

            ReadBuffer result = TextFileHelper.readLine(aReader, getRowSeparator());

            if (result.isEndOfFile() && result.isEmpty()) {

                return;
            }

            String line = result.getLine();
            buffer.append(line);

            try {

                substrings = TextHelper.splitLine(buffer.toString(), getColumnSeparator());
                break;

            } catch (QuoteNotClosedException e) {

                // This exception indicates that the processed line is not complete because the
                // content of a table cell is quoted but the quote closing character is not
                // reached yet. This exception should not be preserved. It is assumed that the
                // table row is spread over several text lines.
                // If there is an issue with the table structure (i.e. number of identified
                // columns) then an exception will be thrown.
                continue;
            }
        }


        int maxColumns = substrings.size();
        HeaderType actualHeaderType = getHeaderType();

        if (actualHeaderType == FIRST_LINE_IS_HEADER) {

            resizeTable(aTable, maxColumns, 0);

            for (int a = 0; a < maxColumns; a++) {

                aTable.setColumnName(a, substrings.get(a));
            }

        } else if (actualHeaderType == NO_HEADER) {

            resizeTable(aTable, maxColumns, 1);

            for (int a = 0; a < maxColumns; a++) {

                aTable.updateCell(a, 0, normalizeValue(substrings.get(a)));
            }

        } else {

            throw new UnsupportedOperationException();
        }
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
        int expectedColumns = aTable.columns();

        StringBuilder buffer = new StringBuilder();
        List<String> substrings;

        while (true) {

            ReadBuffer result = TextFileHelper.readLine(aReader, getRowSeparator());

            if (result.isEndOfFile() && result.isEmpty()) {

                return;
            }

            String line = result.getLine();
            buffer.append(line);

            try {

                substrings = TextHelper.splitLine(buffer.toString(), getColumnSeparator());

                if (substrings.size() < expectedColumns) {

                    continue;
                }

                buffer = new StringBuilder();

            } catch (QuoteNotClosedException e) {

                // This exception indicates that the processed line is not complete because the
                // content of a table cell is quoted but the quote closing character is not
                // reached yet. This exception should not be preserved. It is assumed that the
                // table row is spread over several text lines.
                // If there is an issue with the table structure (i.e. number of identified
                // columns) then an exception will be thrown.
                continue;
            }


            currentRow++;

            int nextRowIndex = aTable.rows();
            int actualColumns = substrings.size();

            StructureType actualStructureType = getStructureType();

            if (actualStructureType == RIGID) {

                if (actualColumns != expectedColumns) {

                    String message =
                        "The table structure is invalid (current row=" + nextRowIndex + "; expected columns=" +
                        expectedColumns + "; actual columns=" + actualColumns + ")!";
                    throw new CsvStructureException(message);

                } else {

                    resizeTable(aTable, actualColumns, currentRow);

                    for (int a = 0; a < actualColumns; a++) {

                        aTable.updateCell(a, nextRowIndex, normalizeValue(substrings.get(a)));
                    }
                }

            } else if (actualStructureType == FLEXIBLE) {

                resizeTable(aTable, actualColumns, currentRow);

                for (int a = 0; a < actualColumns; a++) {

                    aTable.updateCell(a, nextRowIndex, normalizeValue(substrings.get(a)));
                }

            } else {

                throw new UnsupportedOperationException();
            }
        }
    }

}
