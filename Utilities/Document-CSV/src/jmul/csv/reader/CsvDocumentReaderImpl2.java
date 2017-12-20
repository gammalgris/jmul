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


import java.io.IOException;

import java.nio.charset.Charset;

import java.util.List;

import jmul.document.csv.structure.HeaderType;
import static jmul.document.csv.structure.HeaderType.NO_HEADER;

import jmul.io.NestedStreams;
import jmul.io.text.ReadBuffer;
import jmul.io.text.TextFileHelper;

import jmul.misc.annotations.Modified;
import jmul.misc.table.ModifiableTable;

import static jmul.string.Constants.NEW_LINE_WINDOWS;
import static jmul.string.Constants.SEMICOLON;
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
     * The default charset.
     */
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();

    /**
     * The default column separator.
     */
    private static final String DEFAULT_COLUMN_SEPARATOR = SEMICOLON;

    /**
     * The default row separator.
     */
    private static final String DEFAULT_ROW_SEPARATOR = NEW_LINE_WINDOWS;

    /**
     * The default header type.
     */
    private static final HeaderType DEFAULT_HEADER_TYPE = HeaderType.RIGID;

    /**
     * The default constructor.
     */
    public CsvDocumentReaderImpl2() {

        super(DEFAULT_CHARSET, DEFAULT_HEADER_TYPE, DEFAULT_COLUMN_SEPARATOR, DEFAULT_ROW_SEPARATOR);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset
     */
    public CsvDocumentReaderImpl2(Charset aCharset) {

        super(aCharset, DEFAULT_HEADER_TYPE, DEFAULT_COLUMN_SEPARATOR, DEFAULT_ROW_SEPARATOR);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aColumnSeparator
     *        the assumed column separator
     */
    public CsvDocumentReaderImpl2(String aColumnSeparator) {

        super(DEFAULT_CHARSET, DEFAULT_HEADER_TYPE, aColumnSeparator, DEFAULT_ROW_SEPARATOR);
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

        super(aCharset, DEFAULT_HEADER_TYPE, aColumnSeparator, DEFAULT_ROW_SEPARATOR);
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

        super(DEFAULT_CHARSET, DEFAULT_HEADER_TYPE, aColumnSeparator, aRowSeparator);
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

        super(aCharset, DEFAULT_HEADER_TYPE, aColumnSeparator, aRowSeparator);
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

        super(DEFAULT_CHARSET, aHeaderType, aColumnSeparator, aRowSeparator);
    }

    /**
     * Creates a new document reader according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset
     * @param aHeaderType
     *        the assumed header type
     * @param aColumnSeparator
     *        the assumed column separator
     * @param aRowSeparator
     *        the assumed row separator
     */
    public CsvDocumentReaderImpl2(Charset aCharset, HeaderType aHeaderType, String aColumnSeparator,
                                  String aRowSeparator) {

        super(aCharset, aHeaderType, aColumnSeparator, aRowSeparator);
    }

    /**
     * The header of a CSV file is parsed and the specified table is updated accordingly.
     *
     * @param someStreams
     *        the input streams
     * @param aTable
     *        the table which is filled with the file content
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the CSV file
     */
    @Override
    protected void parseHeader(NestedStreams someStreams, @Modified ModifiableTable<String> aTable) throws IOException {

        if (getHeaderType() == NO_HEADER) {

            return;
        }


        ReadBuffer result = TextFileHelper.readLine(getCharset(), someStreams, getRowSeparator());

        if (result.isEndOfFile()) {

            return;
        }


        String line = result.getLine();
        List<String> substrings = TextHelper.splitLine(line, getColumnSeparator());


        int minColumns = aTable.columns();
        int maxColumns = substrings.size();

        for (int a = minColumns; a < maxColumns; a++) {

            aTable.addColumn();
            aTable.setColumnName(a, substrings.get(a));
        }
    }

    /**
     * The content of a CSV file is parsed and the specified table is updated accordingly.
     *
     * @param someStreams
     *        the input streams
     * @param aTable
     *        the table which is filled with the file content
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the CSV file
     */
    @Override
    protected void parseContent(NestedStreams someStreams,
                                @Modified ModifiableTable<String> aTable) throws IOException {

        int currentRow = 0;

        while (true) {

            StringBuilder buffer = new StringBuilder();
            int expectedColumns = aTable.columns();
            List<String> substrings;

            while (true) {

                ReadBuffer result = TextFileHelper.readLine(getCharset(), someStreams, getRowSeparator());

                if (result.isEndOfFile() && result.isEmpty()) {

                    return;
                }

                String line = result.getLine();
                buffer.append(line);

                try {

                    substrings = TextHelper.splitLine(buffer.toString(), getColumnSeparator());

                } catch (QuoteNotClosedException e) {

                    continue;
                }

                int actualColumns = substrings.size();

                if (actualColumns == expectedColumns) {

                    break;

                } else if (actualColumns > expectedColumns) {

                    String message =
                        "The table structure is invalid (current row=" + currentRow + "; expected columns=" +
                        expectedColumns + "; actual columns=" + actualColumns + ")!";
                    throw new CsvStructureException(message);
                }
            }

            currentRow++;

            if (currentRow > 1) {

                aTable.addRow();
            }

            int newRowIndex = currentRow - 1;
            int columns = substrings.size();

            for (int a = 0; a < columns; a++) {

                String newValue = substrings.get(a);

                if (newValue.isEmpty()) {

                    newValue = null;
                }

                aTable.updateCell(a, newRowIndex, newValue);
            }
        }
    }

}
