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

import java.nio.charset.Charset;

import jmul.document.csv.CsvDocument;
import jmul.document.csv.CsvDocumentImpl;
import jmul.document.csv.structure.HeaderType;
import jmul.document.type.DocumentType;
import jmul.document.type.DocumentTypes;

import jmul.io.NestedStreams;
import static jmul.io.text.TextFileHelper.closeFile;
import static jmul.io.text.TextFileHelper.openFile;

import jmul.misc.annotations.Modified;
import jmul.misc.exceptions.MultipleCausesException;
import jmul.misc.table.ModifiableTable;
import jmul.misc.table.ModifiableTableImpl;


/**
 * A base implementation of a document reader.
 *
 * @author Kristian Kutin
 */
abstract class CsvDocumentReaderBase implements CsvDocumentReader {

    /**
     * The charset which will be used to read from files.
     */
    private final Charset charset;

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
     * Creates a new document reader according to the specified parameters.
     *
     * @param aCharset
     * @param aHeaderType
     * @param aColumnSeparator
     * @param aRowSeparator
     */
    protected CsvDocumentReaderBase(Charset aCharset, HeaderType aHeaderType, String aColumnSeparator,
                                    String aRowSeparator) {

        super();

        charset = aCharset;
        headerType = aHeaderType;
        columnSeparator = aColumnSeparator;
        rowSeparator = aRowSeparator;
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

        ModifiableTable<String> table = new ModifiableTableImpl<>();

        NestedStreams ns = openFile(aFile, charset);

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
     * A getter method.
     *
     * @return the expected charset
     */
    public Charset getCharset() {

        return charset;
    }

    /**
     * A getter method.
     *
     * @return the expected column separator
     */
    public String getColumnSeparator() {

        return columnSeparator;
    }

    /**
     * A getter method.
     *
     * @return the expected row separator
     */
    public String getRowSeparator() {

        return rowSeparator;
    }

    /**
     * A getter method.
     *
     * @return the expected header type
     */
    public HeaderType getHeaderType() {

        return headerType;
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
    protected abstract void parseHeader(NestedStreams someStreams,
                                        @Modified ModifiableTable<String> aTable) throws IOException;

    /**
     * The content of a CSV file is parsed and the specified table is updated accordingly.
     *
     * @param someStreams
     * @param aTable
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the CSV file
     */
    protected abstract void parseContent(NestedStreams someStreams,
                                         @Modified ModifiableTable<String> aTable) throws IOException;

}
