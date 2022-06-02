/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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

package jmul.text.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.Charset;

import jmul.checks.ParameterCheckHelper;

import jmul.document.text.TextDocument;
import jmul.document.text.TextDocumentImpl;
import jmul.document.type.DocumentType;
import jmul.document.type.DocumentTypes;

import static jmul.io.Constants.END_OF_FILE;

import jmul.misc.text.ModifiableText;
import jmul.misc.text.ModifiableTextImpl;

import static jmul.string.Constants.NEW_LINE;
import static jmul.string.TextHelper.getDefaultCharset;


/**
 * An implementation of a document reader which normalizes the contnet (i.e. replaces certain
 * replacement codes).
 *
 * @author Kristian Kutin
 */
public class NormalizingTextDocumentReaderImpl implements TextDocumentReader {

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
    public NormalizingTextDocumentReaderImpl() {

        this(getDefaultCharset(), NEW_LINE);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aCharset
     *        the assumed charset of the file
     */
    public NormalizingTextDocumentReaderImpl(Charset aCharset) {

        this(aCharset, NEW_LINE);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aLineSeparator
     *        the assumed line separator
     */
    public NormalizingTextDocumentReaderImpl(String aLineSeparator) {

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
    public NormalizingTextDocumentReaderImpl(Charset aCharset, String aLineSeparator) {

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

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(aFile), charset)) {

            while (true) {

                int result = END_OF_FILE;
                result = isr.read();

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
        }

        DocumentType documentType = DocumentTypes.getDocumentType(aFile.getAbsolutePath());
        return new TextDocumentImpl(documentType, charset, lineSeparator, content);
    }

}
