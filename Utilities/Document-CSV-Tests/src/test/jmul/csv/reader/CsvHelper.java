/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package test.jmul.csv.reader;


import java.nio.charset.Charset;

import jmul.document.csv.CsvDocument;
import jmul.document.csv.structure.HeaderType;
import jmul.document.csv.structure.StructureType;
import jmul.document.type.DocumentTypes;

import jmul.misc.table.Table;

import static org.junit.Assert.assertEquals;


/**
 * A helper class for checking CSV documents,
 *
 * @author Kristian Kutin
 */
final class CsvHelper {

    /**
     * The windows charset.
     */
    public static final Charset WINDOWS_1252;

    /**
     * The sttaic initializer.
     */
    static {

        WINDOWS_1252 = Charset.forName("windows-1252");
    }

    /**
     * The default constructor,
     */
    public CsvHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks that the specified CSV document's meta data matches the specified values. The meta data
     * is determined by the document reader instance, since this is vital information which is needed
     * to parse a file's content. This check only tests that the document reader instance passes the
     * informations correctly to the document instance.
     *
     * @param document
     *        a reference to a CSV document
     * @param charset
     *        the expected charset of the CSV file
     * @param documentType
     *        the expected document type
     * @param columnSeparator
     *        the expected column separator within the CSV table
     * @param rowSeparator
     *        the expected row separator within the CSV table
     * @param headerType
     *        the expected header type
     * @param structureType
     *        the expected structure type
     */
    public static void checkMetaData(CsvDocument document, Charset charset, DocumentTypes documentType,
                                     String columnSeparator, String rowSeparator, HeaderType headerType,
                                     StructureType structureType) {

        assertEquals(charset, document.getStructure().getCharset());
        assertEquals(documentType, document.getDocumentType());
        assertEquals(columnSeparator, document.getStructure().getColumnSeparator());
        assertEquals(rowSeparator, document.getStructure().getRowSeparator());
        assertEquals(headerType, document.getStructure().getHeaderType());
        assertEquals(structureType, document.getStructure().getStructureType());
    }

    /**
     * Checks that the specified table matches the expected size.
     *
     * @param table
     *        a reference to a table
     * @param expectedColumns
     *        the expected number of columns (i.e. a positive number including zero).
     * @param expectedRows
     *        the expected number of rows (i.e. a positive number including zero).
     */
    public static void checkTableSize(Table<String> table, int expectedColumns, int expectedRows) {

        String message;

        int actualColumns = table.columns();
        message =
            String.format("The number of columns doesn't match (expected=%d;actual=%d)!", expectedColumns,
                          actualColumns);
        assertEquals(message, expectedColumns, actualColumns);

        int actualRows = table.rows();
        message = String.format("The number of rows doesn't match (expected=%d;actual=%d)!", expectedRows, actualRows);
        assertEquals(message, expectedRows, actualRows);
    }

    /**
     * Checks if the header of the specified table (i.e. column names) matches the specified header.
     *
     * @param table
     *        a reference to a table
     * @param expectedHeader
     *        the expected header
     */
    public static void checkHeader(Table<String> table, String... expectedHeader) {

        int columns = Math.max(table.columns(), expectedHeader.length);
        for (int index = 0; index < columns; index++) {

            assertEquals(expectedHeader[index], table.getColumnName(index));
        }
    }

    /**
     * Translates the specified coordinates to a unique number.
     *
     * @param table
     *        a reference to a table
     * @param column
     *        a column index
     * @param row
     *        a row index
     *
     * @return a number
     */
    private static int coordinatesToNumber(Table<String> table, int column, int row) {

        int columns = table.columns();
        int number = columns * row + column + 1;

        return number;
    }

    /**
     * Checks if the table content matches the expectations. The test data is arranged in order
     * to easily infer a cell's content according to a cell's coordinates (i.e. column index and
     * row index) relative to the table size. Most or all cells will thus contain a numeric value.
     * Cells for which this doesn't apply will be ignored and must must be checked individually.
     *
     * @param table
     *        a reference to a table
     */
    public static void checkCellsWithNumbers(Table<String> table) {

        for (int rowIndex = 0; rowIndex < table.rows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < table.columns(); columnIndex++) {

                String cellContent = table.getCell(columnIndex, rowIndex);

                int actualNumber;
                try {

                    actualNumber = Integer.parseInt(cellContent);

                } catch (NumberFormatException e) {

                    continue;
                }

                int expectedNumber = coordinatesToNumber(table, columnIndex, rowIndex);

                assertEquals(expectedNumber, actualNumber);
            }
        }
    }

    /**
     * Checks if all cells are empty (i.e. null) in the specified table.
     *
     * @param table
     *        a reference to a table
     */
    public static void checkCellsAreEmpty(Table<String> table) {

        for (int rowIndex = 0; rowIndex < table.rows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < table.columns(); columnIndex++) {

                String cellContent = table.getCell(columnIndex, rowIndex);
                assertEquals(null, cellContent);
            }
        }
    }

}
