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

package test.jmul.csv.reader;


import java.io.IOException;

import java.nio.charset.StandardCharsets;

import jmul.csv.reader.CsvDocumentReader;
import jmul.csv.reader.CsvDocumentReaderImpl;
import jmul.csv.reader.CsvStructureException;

import jmul.document.csv.CsvDocument;
import jmul.document.csv.structure.HeaderType;
import static jmul.document.csv.structure.HeaderType.FIRST_LINE_IS_HEADER;
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
import jmul.string.QuoteNotClosedException;

import jmul.test.classification.ModuleTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.ComparisonFailure;
import org.junit.Test;


/**
 * This class contains tests to check if a specific CSV document reader works correctly.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class CsvDocumentReaderTest {

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument() throws IOException {

        String filename = "testdata-csv\\example01.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * comma. The row separator is a windows style line break.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument2() throws IOException {

        String filename = "testdata-csv\\example02.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, COMMA);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, COMMA, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a unix style line break.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument3() throws IOException {

        String filename = "testdata-csv\\example03.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, SEMICOLON, NEW_LINE_UNIX);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_UNIX,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. Data cells are quoted.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument4() throws IOException {

        String filename = "testdata-csv\\example04.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The csv file contains a table cell which contains a line break.<br>
     * <br>
     * The CSV document reader is expected to throw an exception because it cannot handle
     * line breaks within cells.
     */
    @Test(expected = CsvStructureException.class)
    public void testParseDocument5() throws IOException {

        String filename = "testdata-csv\\example05.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8);
        reader.readFrom(filename);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The csv file contains a table cell which is quoted and contains a line break.<br>
     * <br>
     * The CSV document reader is expected to throw an exception because it cannot handle
     * line breaks within cells.
     */
    @Test(expected = QuoteNotClosedException.class)
    public void testParseDocument6() throws IOException {

        String filename = "testdata-csv\\example06.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8);
        reader.readFrom(filename);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The csv file contains a table cell which is quoted and contains a line break.<br>
     * <br>
     * The CSV document reader is expected to throw an exception because it cannot handle
     * line breaks within cells.
     */
    @Test(expected = QuoteNotClosedException.class)
    public void testParseDocument7() throws IOException {

        String filename = "testdata-csv\\example07.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8);
        reader.readFrom(filename);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The file doesn't have a header and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument8() throws IOException {

        String filename = "testdata-csv\\example08.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, NO_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.NO_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "column0", "column1", "column2");
        CsvHelper.checkCellsWithNumbers(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The file doesn't have a header and contains 3 data lines with different column counts. The
     * column separator is a semicolon. The row separator is a windows style line break.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument9a() throws IOException {

        String filename = "testdata-csv\\example09.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, NO_HEADER, FLEXIBLE);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.NO_HEADER, StructureType.FLEXIBLE);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 4, 3);
        CsvHelper.checkHeader(table, "column0", "column1", "column2", "column3");
        CsvHelper.checkCellsWithNumbers(table);

        assertEquals(null, table.getCell(2, 0));
        assertEquals(null, table.getCell(3, 0));
        assertEquals(null, table.getCell(3, 1));
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The file doesn't have a header and contains 3 data lines with different column counts. The
     * column separator is a semicolon. The row separator is a windows style line break.<br>
     * <br>
     * The CSV document reader is expected to throw an exception because it cannot handle
     * a flexible table structure.
     */
    @Test(expected = CsvStructureException.class)
    public void testParseDocument9b() throws IOException {

        String filename = "testdata-csv\\example09.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, NO_HEADER, RIGID);
        reader.readFrom(filename);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The file doesn't have a header and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. Every cell is empty.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument10() throws IOException {

        String filename = "testdata-csv\\example10.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, NO_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.NO_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "column0", "column1", "column2");
        CsvHelper.checkCellsAreEmpty(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The file doesn't have a header and contains 1 data line with a single column. The row
     * separator is a windows style line break. The cell contains several special characters.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument11() throws IOException {

        String filename = "testdata-csv\\example11.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, NO_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.NO_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 1, 1);
        CsvHelper.checkHeader(table, "column0");

        String expectedString = "\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u00df";
        String actualString = table.getCell(0, 0);

        assertEquals(expectedString, actualString);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The file doesn't have a header and contains 1 data line with a single column. The row
     * separator is a windows style line break. The cell contains several special characters.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument12a() throws IOException {

        String filename = "testdata-csv\\example12.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.ISO_8859_1, NO_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.ISO_8859_1, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.NO_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 1, 1);
        CsvHelper.checkHeader(table, "column0");

        String expectedString = "\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u00df";
        String actualString = table.getCell(0, 0);

        assertEquals(expectedString, actualString);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The file doesn't have a header and contains 1 data line with a single column. The row
     * separator is a windows style line break. The cell contains several special characters.<br>
     * <br>
     * The reader assumes a wrong encoding.
     */
    @Test
    public void testParseDocument12b() throws IOException {

        String filename = "testdata-csv\\example12.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, NO_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.NO_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 1, 1);
        CsvHelper.checkHeader(table, "column0");

        String expectedString = "\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u00df";
        String actualString = table.getCell(0, 0);

        try {

            assertEquals(expectedString, actualString);
            fail("The comparison passed unexpectedly!");

        } catch (ComparisonFailure e) {

            // Everything is OK, the comparison exception is expected.
        }
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The file has a header with gaps (i.e. missing header names) and contains 3 data lines
     * with 3 columns. The column separator is a semicolon. The row separator is a windows style
     * line break.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument13() throws IOException {

        String filename = "testdata-csv\\example13.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "", "C");
        CsvHelper.checkCellsWithNumbers(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. One cell contains a quoted
     * string containing a column separator.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument14() throws IOException {

        String filename = "testdata-csv\\example14.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);

        String actualString = table.getCell(2, 1);
        String expectedString = "\"Hallo;Welt\"";
        assertEquals(expectedString, actualString);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. One cell contains a single
     * quoted string containing a column separator.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument15() throws IOException {

        String filename = "testdata-csv\\example15.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);

        String actualString = table.getCell(2, 1);
        String expectedString = "'Hallo;Welt'";
        assertEquals(expectedString, actualString);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * Tests parsing a CSV file. The first line is a header line and contains
     * 3 data lines. The column separator is a semicolon. The row separator is
     * a windows style line break. One cell contains a quoted string containing
     * a column separator.<br>
     * <br>
     *  The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument16() throws IOException {

        String filename = "testdata-csv\\example16.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);

        String actualString = table.getCell(2, 1);
        String expectedString = "\"Hallo'Welt\"";
        assertEquals(expectedString, actualString);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. One cell contains a quoted
     * string containing a column separator.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument17() throws IOException {

        String filename = "testdata-csv\\example17.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);

        String actualString = table.getCell(2, 1);
        String expectedString = "'Hallo\"Welt'";
        assertEquals(expectedString, actualString);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. One cell contains a single
     * quoted string containing a column separator.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument18() throws IOException {

        String filename = "testdata-csv\\example18.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);

        String actualString = table.getCell(2, 1);
        String expectedString = "'Hallo;Welt'";
        assertEquals(expectedString, actualString);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. The second data line
     * contains a cell with a ' character.<br>
     * <br>
     * There is an ambiguitity which cannot be easily resolved. The CSV document reader is
     * expected to throw an exception because the ' is missing an corresponding end of the
     * quote.
     */
    @Test(expected = QuoteNotClosedException.class)
    public void testParseDocument19a() throws IOException {

        String filename = "testdata-csv\\example19.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        // In case the reader throws no exception, check some basic plausibilities.
        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. The second data line
     * contains a cell with a ' character.<br>
     * <br>
     * There is an ambiguitity which cannot be easily resolved. The CSV document reader is
     * expected to throw an exception because the ' is missing an corresponding end of the
     * quote.
     */
    @Test(expected = QuoteNotClosedException.class)
    public void testParseDocument19b() throws IOException {

        String filename = "testdata-csv\\example19.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, FLEXIBLE);
        CsvDocument document = reader.readFrom(filename);

        // In case the reader throws no exception, check some basic plausibilities.
        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.FLEXIBLE);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. The second data line
     * contains a cell with a " character.<br>
     * <br>
     * There is an ambiguitity which cannot be easily resolved. The CSV document reader is
     * expected to throw an exception because the " is missing an corresponding end of the
     * quote.
     */
    @Test(expected = QuoteNotClosedException.class)
    public void testParseDocument20a() throws IOException {

        String filename = "testdata-csv\\example20.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        // In case the reader throws no exception, check some basic plausibilities.
        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. The second data line
     * contains a cell with a " character.<br>
     * <br>
     * There is an ambiguitity which cannot be easily resolved. The CSV document reader is
     * expected to throw an exception because the " is missing an corresponding end of the
     * quote.
     */
    @Test(expected = QuoteNotClosedException.class)
    public void testParseDocument20b() throws IOException {

        String filename = "testdata-csv\\example20.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, FLEXIBLE);
        CsvDocument document = reader.readFrom(filename);

        // In case the reader throws no exception, check some basic plausibilities.
        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.FLEXIBLE);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. One cell contains a string
     * containing an apostrophe character which is not used for quoting.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument21() throws IOException {

        String filename = "testdata-csv\\example21.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);

        String actualString = table.getCell(2, 1);
        String expectedString = "l\u00b4auberge"; // using the char ´ makes the comparison fail
        assertEquals(expectedString, actualString);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. One cell contains a string
     * containing an apostrophe character which is not used for quoting.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument22() throws IOException {

        String filename = "testdata-csv\\example22.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);

        String actualString = table.getCell(2, 1);
        String expectedString = "l`auberge";
        assertEquals(expectedString, actualString);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * Tests parsing a CSV file. The first line is a header line and contains
     * 3 data lines. The column separator is a semicolon. The row separator is
     * a windows style line break. One cell contains a quoted string containing
     * a column separator.<br>
     * <br>
     *  The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument23() throws IOException {

        String filename = "testdata-csv\\example23.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);

        String actualString = table.getCell(2, 1);
        String expectedString = "Hal\"lo'We\"lt";
        assertEquals(expectedString, actualString);
    }

    /**
     * Tests parsing a CSV file (see CSV file or testdata creation script for details).<br>
     * <br>
     * The first line is a header line and contains 3 data lines. The column separator is a
     * semicolon. The row separator is a windows style line break. One cell contains a quoted
     * string containing a column separator.<br>
     * <br>
     * The CSV file is expected to be parsable.
     */
    @Test
    public void testParseDocument24() throws IOException {

        String filename = "testdata-csv\\example24.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl(StandardCharsets.UTF_8, FIRST_LINE_IS_HEADER, RIGID);
        CsvDocument document = reader.readFrom(filename);

        CsvHelper.checkMetaData(document, StandardCharsets.UTF_8, DocumentTypes.CSV, SEMICOLON, NEW_LINE_WINDOWS,
                                HeaderType.FIRST_LINE_IS_HEADER, StructureType.RIGID);

        Table<String> table = document.getContent();

        CsvHelper.checkTableSize(table, 3, 3);
        CsvHelper.checkHeader(table, "A", "B", "C");
        CsvHelper.checkCellsWithNumbers(table);

        String actualString = table.getCell(2, 1);
        String expectedString = "Hal'lo\"We'lt";
        assertEquals(expectedString, actualString);
    }

}
