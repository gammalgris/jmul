/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package test.jmul.csv.reader;


import java.io.IOException;

import java.util.List;

import jmul.csv.reader.CsvDocumentReader;
import jmul.csv.reader.CsvDocumentReaderImpl2;

import jmul.document.csv.CsvDocument;
import jmul.document.csv.structure.HeaderType;
import static jmul.document.csv.structure.HeaderType.NO_HEADER;
import jmul.document.csv.structure.StructureType;
import static jmul.document.csv.structure.StructureType.FLEXIBLE;
import static jmul.document.csv.structure.StructureType.RIGID;
import jmul.document.type.DocumentTypes;

import jmul.misc.table.Table;

import static jmul.string.Constants.COMMA;
import static jmul.string.Constants.NEW_LINE_UNIX;
import static jmul.string.Constants.NEW_LINE_WINDOWS;
import static jmul.string.Constants.SEMICOLON;

import jmul.test.classification.ModuleTest;
import jmul.test.exceptions.FailedTestException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This class contains tests to check if a specific document reader works correctly.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class CsvDocumentReader2Test {

    /**
     * The table data is checked.
     *
     * @param aTable
     *        a reference to a table
     */
    private void checkTableData(Table<String> aTable) {

        int columns = aTable.columns();
        int rows = aTable.rows();

        assertEquals(3, columns);
        assertEquals(3, rows);

        for (int a = 0; a < columns; a++) {

            for (int b = 0; b < rows; b++) {

                int expectedNumber = (b * 3) + (a + 1);
                String expectedString = "" + expectedNumber;
                String actualString = aTable.getCell(a, b);
                String message =
                    "expected: " + expectedString + "; row=" + b + "; column=" + a + "; actual=" + actualString;

                assertEquals(message, expectedString, actualString);
            }
        }
    }

    /**
     * Tests parsing a CSV file. The first line is a header line and the contains
     * 3 data lines. The column separator is a semicolon. The row separator is
     * a windows style line break.
     */
    @Test
    public void testParseDocument() {

        String filename = "testdata-csv\\example01.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl2();

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.FIRST_LINE_IS_HEADER, document.getStructure().getHeaderType());
        assertEquals(StructureType.RIGID, document.getStructure().getStructureType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        checkTableData(table);
    }

    /**
     * Tests parsing a CSV file. The first line is a header line and the contains
     * 3 data lines. The column separator is a comma. The row separator is
     * a windows style line break.
     */
    @Test
    public void testParseDocument2() {

        String filename = "testdata-csv\\example02.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl2(COMMA);

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(COMMA, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.FIRST_LINE_IS_HEADER, document.getStructure().getHeaderType());
        assertEquals(StructureType.RIGID, document.getStructure().getStructureType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        checkTableData(table);
    }

    /**
     * Tests parsing a CSV file. The first line is a header line and the contains
     * 3 data lines. The column separator is a semicolon. The row separator is
     * a unix style line break.
     */
    @Test
    public void testParseDocument3() {

        String filename = "testdata-csv\\example03.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl2(SEMICOLON, NEW_LINE_UNIX);

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_UNIX, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.FIRST_LINE_IS_HEADER, document.getStructure().getHeaderType());
        assertEquals(StructureType.RIGID, document.getStructure().getStructureType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        checkTableData(table);
    }

    /**
     * Tests parsing a CSV file. The first line is a header line and the contains
     * 3 data lines. The column separator is a semicolon. The row separator is
     * a windows style line break. Data cells are quoted.
     */
    @Test
    public void testParseDocument4() {

        String filename = "testdata-csv\\example04.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl2();

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.FIRST_LINE_IS_HEADER, document.getStructure().getHeaderType());
        assertEquals(StructureType.RIGID, document.getStructure().getStructureType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        assertEquals(3, columns);
        assertEquals(3, rows);


        for (int a = 0; a < columns; a++) {

            for (int b = 0; b < rows; b++) {

                int expectedNumber = (b * 3) + (a + 1);
                String expectedString = "\"" + expectedNumber + "\"";
                String actualString = table.getCell(a, b);
                String message =
                    "expected: " + expectedString + "; row=" + b + "; column=" + a + "; actual=" + actualString;

                assertEquals(message, expectedString, actualString);
            }
        }
    }

    /**
     * Tests parsing a CSV file. The csv file contains a table row which spans two lines.
     */
    @Test
    public void testParseDocument5() {

        String filename = "testdata-csv\\example05.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl2();

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.FIRST_LINE_IS_HEADER, document.getStructure().getHeaderType());
        assertEquals(StructureType.RIGID, document.getStructure().getStructureType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        assertEquals(3, columns);
        assertEquals(3, rows);


        for (int a = 0; a < columns; a++) {

            for (int b = 0; b < rows; b++) {

                int expectedNumber = (b * 3) + (a + 1);
                String expectedString;

                if (expectedNumber == 5) {

                    expectedString = "HalloWelt";

                } else {

                    expectedString = "" + expectedNumber;
                }

                String actualString = table.getCell(a, b);
                String message =
                    "expected: " + expectedString + "; row=" + b + "; column=" + a + "; actual=" + actualString;

                assertEquals(message, expectedString, actualString);
            }
        }
    }

    /**
     * Tests parsing a CSV file. The csv file contains a table row which spans two lines and contains
     * a quoted cell.
     */
    @Test
    public void testParseDocument6() {

        String filename = "testdata-csv\\example06.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl2();

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.FIRST_LINE_IS_HEADER, document.getStructure().getHeaderType());
        assertEquals(StructureType.RIGID, document.getStructure().getStructureType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        assertEquals(3, columns);
        assertEquals(3, rows);


        for (int a = 0; a < columns; a++) {

            for (int b = 0; b < rows; b++) {

                int expectedNumber = (b * 3) + (a + 1);
                String expectedString;

                if (expectedNumber == 6) {

                    expectedString = "\"HalloWelt\"";

                } else {

                    expectedString = "" + expectedNumber;
                }

                String actualString = table.getCell(a, b);
                String message =
                    "expected: " + expectedString + "; row=" + b + "; column=" + a + "; actual=" + actualString;

                assertEquals(message, expectedString, actualString);
            }
        }
    }

    /**
     * Tests parsing a CSV file. The file doesn't have a header and contains
     * 3 data lines. The column separator is a semicolon. The row separator is
     * a windows style line break.
     */
    @Test
    public void testParseDocument7() {

        String filename = "testdata-csv\\example07.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl2(NO_HEADER, RIGID);

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.NO_HEADER, document.getStructure().getHeaderType());
        assertEquals(StructureType.RIGID, document.getStructure().getStructureType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        checkTableData(table);
    }

    /**
     * Tests parsing a CSV file. The file doesn't have a header and contains
     * 3 data lines with different column counts. The column separator is a semicolon.
     * The row separator is a windows style line break.
     */
    @Test
    public void testParseDocument8() {

        String filename = "testdata-csv\\example08.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl2(NO_HEADER, FLEXIBLE);

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.NO_HEADER, document.getStructure().getHeaderType());
        assertEquals(StructureType.FLEXIBLE, document.getStructure().getStructureType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        String[][] expectedData = { { "1", "2", null, null }, { "3", "4", "5", null }, { "6", "7", "8", "9" } };

        assertEquals(4, columns);
        assertEquals(3, rows);

        for (int a = 0; a < columns; a++) {

            for (int b = 0; b < rows; b++) {

                String expectedString = expectedData[b][a];
                String actualString = table.getCell(a, b);
                String message =
                    "expected: " + expectedString + "; row=" + b + "; column=" + a + "; actual=" + actualString;

                assertEquals(message, expectedString, actualString);
            }
        }
    }

    /**
     * Tests parsing a CSV file. The file doesn't have a header and contains
     * 3 data lines. The column separator is a semicolon. The row separator is
     * a windows style line break.
     */
    @Test
    public void testParseDocument9() {

        String filename = "testdata-csv\\example09.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl2(NO_HEADER, RIGID);

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.NO_HEADER, document.getStructure().getHeaderType());
        assertEquals(StructureType.RIGID, document.getStructure().getStructureType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        assertEquals(3, columns);
        assertEquals(3, rows);

        for (int a = 0; a < columns; a++) {

            for (int b = 0; b < rows; b++) {

                String expectedString = null;
                String actualString = table.getCell(a, b);
                String message =
                    "expected: " + expectedString + "; row=" + b + "; column=" + a + "; actual=" + actualString;

                assertEquals(message, expectedString, actualString);
                assertTrue("The cell is not empty as expected!", table.isEmptyCell(a, b));
            }
        }
    }

}
