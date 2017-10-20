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

package jmul.csv.writer;


import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;

import jmul.document.csv.CsvDocument;
import jmul.document.csv.structure.CsvStructure;
import jmul.document.csv.structure.HeaderType;
import static jmul.document.csv.structure.HeaderType.NO_HEADER;

import jmul.io.NestedStreams;
import jmul.io.text.TextFileHelper;

import jmul.misc.table.Table;


/**
 * An implementation of a document writer.
 *
 * @author Kristian Kutin
 */
public class CsvDocumentWriterImpl implements CsvDocumentWriter {

    /**
     * The default constructor.
     */
    public CsvDocumentWriterImpl() {

        super();
    }

    /**
     * The method writes a CSV document.
     *
     * @param aFilename
     *        the name of the output file
     * @param aDocument
     *        a document object
     *
     * @throws IOException
     *         This exception can be thrown if IO operations fail
     */
    @Override
    public void writeDocument(String aFilename, CsvDocument aDocument) throws IOException {

        writeDocument(new File(aFilename), aDocument);
    }

    /**
     * The method writes a CSV document.
     *
     * @param aFile
     *        the output file
     * @param aDocument
     *        a document object
     *
     * @throws IOException
     *         This exception can be thrown if IO operations fail
     */
    @Override
    public void writeDocument(File aFile, CsvDocument aDocument) throws IOException {

        NestedStreams ns = TextFileHelper.createFile(aFile);

        CsvStructure documentStructure = aDocument.getStructure();
        String columnSeparator = documentStructure.getColumnSeparator();
        String rowSeparator = documentStructure.getRowSeparator();
        HeaderType headerType = documentStructure.getHeaderType();

        if (!NO_HEADER.equals(headerType)) {

            String line = row2String(documentStructure.getHeader(), columnSeparator);
            TextFileHelper.writeLine(ns, line, rowSeparator);
        }

        Table<String> content = aDocument.getContent();

        for (int a = 0; a < content.rows(); a++) {

            String line = row2String(content.getRow(a), columnSeparator);
            TextFileHelper.writeLine(ns, line, rowSeparator);
        }

        TextFileHelper.closeFile(ns);
    }

    /**
     * Creates a string according to the specified parameters.
     *
     * @param someStrings
     *        all row components
     * @param aColumnSeparator
     *        a column separator
     *
     * @return a string
     */
    private static String row2String(String[] someStrings, String aColumnSeparator) {

        return row2String(Arrays.asList(someStrings), aColumnSeparator);
    }

    /**
     * Creates a string according to the specified parameters.
     *
     * @param someStrings
     *        all row components
     * @param aColumnSeparator
     *        a column separator
     *
     * @return a string
     */
    private static String row2String(List<String> someStrings, String aColumnSeparator) {
        StringBuilder buffer = new StringBuilder();

        boolean first = true;
        for (String s : someStrings) {

            if (first) {

                first = false;

            } else {

                buffer.append(aColumnSeparator);
            }

            buffer.append(s);
        }

        return String.valueOf(buffer);
    }

}
