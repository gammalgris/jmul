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

package jmul.csv.writer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.nio.charset.Charset;

import java.util.Arrays;
import java.util.List;

import jmul.document.csv.CsvDocument;
import jmul.document.csv.structure.CsvStructure;
import jmul.document.csv.structure.HeaderType;
import static jmul.document.csv.structure.HeaderType.NO_HEADER;

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
     * Writes the specified document to the specified file.
     *
     * @param aFilename
     *        the name of the output file
     * @param aDocument
     *        a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to write to the file
     */
    @Override
    public void writeTo(String aFilename, CsvDocument aDocument) throws IOException {

        writeTo(new File(aFilename), aDocument);
    }

    /**
     * Writes the specified document to the specified file.
     *
     * @param aFile
     *        the output file
     * @param aDocument
     *        a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to write to the file
     */
    @Override
    public void writeTo(File aFile, CsvDocument aDocument) throws IOException {

        Charset charset = aDocument.getStructure().getCharset();
        CsvStructure documentStructure = aDocument.getStructure();
        String columnSeparator = documentStructure.getColumnSeparator();
        String rowSeparator = documentStructure.getRowSeparator();
        HeaderType headerType = documentStructure.getHeaderType();

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aFile), charset))) {

            if (!NO_HEADER.equals(headerType)) {

                String line = row2String(documentStructure.getHeader(), columnSeparator);
                TextFileHelper.writeLine(bw, line, rowSeparator);
            }

            Table<String> content = aDocument.getContent();

            for (int a = 0; a < content.rows(); a++) {

                String line = row2String(content.getRow(a), columnSeparator);
                TextFileHelper.writeLine(bw, line, rowSeparator);
            }
        }
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
