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

package test.jmul.csv.reader;


import java.io.IOException;

import java.util.List;

import jmul.csv.reader.CsvDocumentReader;
import jmul.csv.reader.CsvDocumentReaderImpl;
import jmul.csv.reader.CsvDocumentReaderImpl2;

import jmul.document.csv.CsvDocument;
import jmul.document.csv.structure.HeaderType;
import jmul.document.type.DocumentTypes;

import jmul.misc.table.Table;

import static jmul.string.Constants.COMMA;
import static jmul.string.Constants.NEW_LINE_UNIX;
import static jmul.string.Constants.NEW_LINE_WINDOWS;
import static jmul.string.Constants.SEMICOLON;

import jmul.test.classification.ModuleTest;
import jmul.test.exceptions.FailedTestException;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests to check if a specific document reader works correctly.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class CsvDocumentReaderTest {

    /**
     * Tests parsing a CSV file.
     */
    @Test
    public void testParseDocument() {

        String filename = "testdata-csv\\example01.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl();

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.RIGID, document.getStructure().getHeaderType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        for (int a = 0; a < columns; a++) {

            for (int b = 0; b < rows; b++) {

                int expectedNumber = (b * 3) + (a + 1);
                String expectedString = "" + expectedNumber;
                String actualString = table.getCell(a, b);
                String message =
                    "expected: " + expectedString + "; row=" + b + "; column=" + a + "; actual=" + actualString;

                assertEquals(message, expectedString, actualString);
            }
        }
    }

    /**
     * Tests parsing a CSV file.
     */
    @Test
    public void testParseDocument2() {

        String filename = "testdata-csv\\example02.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(COMMA);

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(COMMA, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.RIGID, document.getStructure().getHeaderType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        for (int a = 0; a < columns; a++) {

            for (int b = 0; b < rows; b++) {

                int expectedNumber = (b * 3) + (a + 1);
                String expectedString = "" + expectedNumber;
                String actualString = table.getCell(a, b);
                String message =
                    "expected: " + expectedString + "; row=" + b + "; column=" + a + "; actual=" + actualString;

                assertEquals(message, expectedString, actualString);
            }
        }
    }

    /**
     * Tests parsing a CSV file.
     */
    @Test
    public void testParseDocument3() {

        String filename = "testdata-csv\\example03.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(SEMICOLON, NEW_LINE_UNIX);

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_UNIX, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.RIGID, document.getStructure().getHeaderType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


        for (int a = 0; a < columns; a++) {

            for (int b = 0; b < rows; b++) {

                int expectedNumber = (b * 3) + (a + 1);
                String expectedString = "" + expectedNumber;
                String actualString = table.getCell(a, b);
                String message =
                    "expected: " + expectedString + "; row=" + b + "; column=" + a + "; actual=" + actualString;

                assertEquals(message, expectedString, actualString);
            }
        }
    }

    /**
     * Tests parsing a CSV file.
     */
    @Test
    public void testParseDocument4() {

        String filename = "testdata-csv\\example04.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl();

        CsvDocument document;

        try {

            document = reader.readFrom(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(SEMICOLON, document.getStructure().getColumnSeparator());
        assertEquals(NEW_LINE_WINDOWS, document.getStructure().getRowSeparator());
        assertEquals(HeaderType.RIGID, document.getStructure().getHeaderType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


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
     * Tests parsing a CSV file.
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
        assertEquals(HeaderType.RIGID, document.getStructure().getHeaderType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


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
     * Tests parsing a CSV file.
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
        assertEquals(HeaderType.RIGID, document.getStructure().getHeaderType());

        String[] header1 = document.getStructure().getHeader();
        List<String> header2 = document.getContent().getColumnNames();

        Table<String> table = document.getContent();
        int columns = table.columns();
        int rows = table.rows();

        for (int a = 0; a < columns; a++) {

            assertEquals(header1[a], header2.get(a));
        }


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

}
