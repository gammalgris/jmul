/*
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

import jmul.document.csv.CsvDocument;
import jmul.document.csv.content.HeaderType;
import jmul.document.type.DocumentTypes;

import jmul.misc.table.Table;

import static jmul.string.Constants.NEW_LINE_WINDOWS;
import static jmul.string.Constants.SEMICOLON;

import jmul.test.classification.ModuleTest;
import jmul.test.exceptions.FailedTestException;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * A test to check the general properties of a document.
 *
 * @author Kristian Kutin
 *
 * @deprecated delete before checking in
 */
@ModuleTest
public class CsvDocumentReaderTest {

    @Test
    public void testParseDocument() {

        String filename = "testdata//example01.csv";
        CsvDocumentReader reader = new CsvDocumentReaderImpl();

        CsvDocument document;

        try {

            document = reader.parseDocument(filename);

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

}
