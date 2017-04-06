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

package jmul.csv.reader;


import java.io.File;
import java.io.IOException;

import java.util.List;

import jmul.document.csv.CsvDocument;
import jmul.document.csv.CsvDocumentImpl;
import jmul.document.csv.content.HeaderType;
import static jmul.document.csv.content.HeaderType.NO_HEADER;
import jmul.document.type.DocumentType;
import jmul.document.type.DocumentTypes;

import jmul.io.NestedStreams;
import jmul.io.text.ReadBuffer;
import jmul.io.text.TextFileHelper;
import static jmul.io.text.TextFileHelper.closeFile;
import static jmul.io.text.TextFileHelper.openFile;

import jmul.misc.annotations.Modified;
import jmul.misc.exceptions.MultipleCausesException;
import jmul.misc.table.ModifiableTable;
import jmul.misc.table.ModifiableTableImpl;

import static jmul.string.Constants.NEW_LINE_WINDOWS;
import static jmul.string.Constants.SEMICOLON;
import jmul.string.TextHelper;


/**
 * An implementation for a CSV document reader.
 *
 * @author Kristian Kutin
 */
public class CsvDocumentReaderImpl implements CsvDocumentReader {

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
     * The default constructor.
     */
    public CsvDocumentReaderImpl() {

        super();

        columnSeparator = DEFAULT_COLUMN_SEPARATOR;
        rowSeparator = DEFAULT_ROW_SEPARATOR;
        headerType = DEFAULT_HEADER_TYPE;
    }

    /**
     * Parses the specified file and returns a document that contains the
     * file content.
     *
     * @param aFilename
     *        filename of the CSV file
     *
     * @return a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the file
     */
    @Override
    public CsvDocument parseDocument(String aFilename) throws IOException {

        return parseDocument(new File(aFilename));
    }

    /**
     * Parses the specified file and returns a document that contains the
     * file content.
     *
     * @param aFile
     *        the CSV file
     *
     * @return a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the file
     */
    @Override
    public CsvDocument parseDocument(File aFile) throws IOException {

        DocumentType documentType = DocumentTypes.getDocumentType(aFile.getName());

        ModifiableTable<String> table = new ModifiableTableImpl<String>();

        NestedStreams ns = openFile(aFile);

        try {

            parseHeader(ns, table);
            parseContent(ns, table);

        } catch (IOException e) {

            try {

                closeFile(ns);

            } catch (IOException f) {

                throw new IOException(new MultipleCausesException(e, f));
            }
        }

        closeFile(ns);

        return new CsvDocumentImpl(documentType, headerType, columnSeparator, rowSeparator, table);
    }

    /**
     * The header of a CSV file is parsed and the specified table is updated accordingly.
     *
     * @param someStreams
     * @param aTable
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the CSV file
     */
    private void parseHeader(NestedStreams someStreams, @Modified ModifiableTable<String> aTable) throws IOException {

        if (headerType == NO_HEADER) {

            return;
        }


        ReadBuffer result = TextFileHelper.readLine(someStreams, rowSeparator);

        if (result.isEndOfFile()) {

            return;
        }


        String line = result.getLine();
        List<String> substrings = TextHelper.splitLine(line, columnSeparator);


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
     * @param aTable
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the CSV file
     */
    private void parseContent(NestedStreams someStreams, @Modified ModifiableTable<String> aTable) throws IOException {

        int currentRow = 0;

        while (true) {

            ReadBuffer result = TextFileHelper.readLine(someStreams, rowSeparator);

            if (result.isEndOfFile()) {

                return;
            }


            String line = result.getLine();
            List<String> substrings = TextHelper.splitLine(line, columnSeparator);

            currentRow++;

            if (currentRow > 1) {

                aTable.addRow();
            }

            int newRowIndex = currentRow - 1;
            int columns = substrings.size();

            for (int a = 0; a < columns; a++) {

                String newValue = substrings.get(a);
                aTable.updateCell(a, newRowIndex, newValue);
            }
        }
    }

}
