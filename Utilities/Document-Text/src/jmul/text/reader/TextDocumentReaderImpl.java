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

package jmul.text.reader;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.Charset;

import jmul.checks.ParameterCheckHelper;

import jmul.document.text.TextDocument;
import jmul.document.text.TextDocumentImpl;
import jmul.document.type.DocumentType;
import jmul.document.type.DocumentTypes;

import static jmul.io.Constants.END_OF_FILE;
import jmul.io.NestedStreams;
import jmul.io.NestedStreamsImpl;
import jmul.io.StreamsHelper;

import jmul.misc.text.ModifiableText;
import jmul.misc.text.ModifiableTextImpl;

import static jmul.string.Constants.NEW_LINE;
import static jmul.string.TextHelper.getDefaultCharset;


/**
 * An implementation of a document reader.
 *
 * @author Kristian Kutin
 */
public class TextDocumentReaderImpl implements TextDocumentReader {

    /**
     * The assumed charset.
     */
    private Charset charset;

    /**
     * The assumed line separator.
     */
    private String lineSeparator;

    /**
     * The default constructor.
     */
    public TextDocumentReaderImpl() {

        this(getDefaultCharset(), NEW_LINE);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset of the file
     */
    public TextDocumentReaderImpl(Charset aCharset) {

        this(aCharset, NEW_LINE);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aLineSeparator
     *        the assumed line separator
     */
    public TextDocumentReaderImpl(String aLineSeparator) {

        this(getDefaultCharset(), aLineSeparator);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset of the file
     * @param aLineSeparator
     *        the assumed line separator
     */
    public TextDocumentReaderImpl(Charset aCharset, String aLineSeparator) {

        charset = ParameterCheckHelper.checkCharsetParameter(aCharset);
        lineSeparator = ParameterCheckHelper.checkLineSeparatorParameter(aLineSeparator);
    }

    /**
     * Reads from the specified file and returns a document that
     * contains the file content.
     *
     * @param aFilename
     *        the name of the input file
     *
     * @return a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the file
     */
    @Override
    public TextDocument readFrom(String aFilename) throws IOException {

        return readFrom(new File(ParameterCheckHelper.checkFileNameParameter(aFilename)));
    }

    /**
     * Reads from the specified file and returns a document that
     * contains the file content.
     *
     * @param aFile
     *        the input file
     *
     * @return a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the file
     */
    @Override
    public TextDocument readFrom(File aFile) throws IOException {

        ParameterCheckHelper.checkFileParameter(aFile);

        ModifiableText content = new ModifiableTextImpl();
        StringBuilder buffer = new StringBuilder();

        NestedStreams ns = openFile(aFile, charset);
        InputStreamReader is = (InputStreamReader) ns.getOuterStream();

        while (true) {

            int result = 0;

            try {

                result = is.read();

            } catch (IOException e) {

                StreamsHelper.closeStreamAfterException(ns, e);
            }

            if (result == END_OF_FILE) {

                content.addLine(buffer.toString());
                break;
            }

            char b = (char) result;

            buffer.append(b);
            String line = buffer.toString();

            if (line.endsWith(lineSeparator)) {

                line = line.replace(lineSeparator, "");
                content.addLine(line);
                buffer = new StringBuilder();
            }
        }

        StreamsHelper.closeStream(ns);

        DocumentType documentType = DocumentTypes.getDocumentType(aFile.getAbsolutePath());
        return new TextDocumentImpl(documentType, charset, lineSeparator, content);
    }

    /**
     * Opens a stream to the specified file.
     *
     * @param aFile
     *        a file
     * @param aCharset
     *        the assumed charset
     *
     * @return all streams
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file doesn't exist
     */
    private static NestedStreams openFile(File aFile, Charset aCharset) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(aFile);
        InputStreamReader isr = new InputStreamReader(fis, aCharset);

        return new NestedStreamsImpl(isr, fis);
    }

}
