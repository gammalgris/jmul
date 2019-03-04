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

package test.jmul.csv.writer;


import java.io.IOException;

import jmul.csv.writer.CsvDocumentWriter;
import jmul.csv.writer.CsvDocumentWriterImpl;

import jmul.document.csv.CsvDocument;
import jmul.document.csv.CsvDocumentImpl;
import jmul.document.csv.structure.HeaderType;
import jmul.document.csv.structure.StructureType;
import jmul.document.type.DocumentType;
import jmul.document.type.DocumentTypes;

import jmul.misc.table.ModifiableTable;
import jmul.misc.table.ModifiableTableImpl;

import static jmul.string.Constants.NEW_LINE_WINDOWS;
import static jmul.string.Constants.SEMICOLON;

import jmul.test.classification.ModuleTest;

import static org.junit.Assert.fail;
import org.junit.Test;


/**
 * This class contains tests to check if a specific document writer works correctly.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class CsvDocumentWriterTest {

    /**
     * Tests writing a simple CSV file.
     */
    @Test
    public void testWritingDocument() {

        DocumentType documentType = DocumentTypes.CSV;
        HeaderType headerType = HeaderType.FIRST_LINE_IS_HEADER;
        StructureType structureType = StructureType.RIGID;
        String columnSeparator = SEMICOLON;
        String rowSeparator = NEW_LINE_WINDOWS;

        ModifiableTable<String> table = new ModifiableTableImpl<>();
        table.addColumn();
        table.addColumn();
        table.addColumn();
        table.addRow();

        table.setColumnName(0, "A");
        table.setColumnName(1, "B");
        table.setColumnName(2, "C");

        table.updateCell(0, 0, "1");
        table.updateCell(1, 0, "2");
        table.updateCell(2, 0, "3");

        CsvDocument document =
            new CsvDocumentImpl(documentType, headerType, structureType, columnSeparator, rowSeparator, table);

        CsvDocumentWriter writer = new CsvDocumentWriterImpl();

        try {

            writer.writeTo("testdata-csv/output.csv", document);

        } catch (IOException e) {

            fail(e.getMessage());
        }
    }

}
