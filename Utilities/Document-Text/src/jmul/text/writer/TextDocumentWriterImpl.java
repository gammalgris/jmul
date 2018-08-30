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

package jmul.text.writer;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.nio.charset.Charset;

import jmul.document.text.TextDocument;
import jmul.document.text.structure.TextStructure;

import jmul.io.NestedStreams;
import jmul.io.NestedStreamsImpl;
import jmul.io.StreamsHelper;

import jmul.checks.ParameterCheckHelper;

import jmul.string.TextHelper;


/**
 * An implementation of a document writer.
 *
 * @author Kristian Kutin
 */
public class TextDocumentWriterImpl implements TextDocumentWriter {

    /**
     * The default constructor.
     */
    public TextDocumentWriterImpl() {

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
    public void writeTo(String aFilename, TextDocument aDocument) throws IOException {

        writeTo(new File(ParameterCheckHelper.checkFileNameParameter(aFilename)), aDocument);
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
    public void writeTo(File aFile, TextDocument aDocument) throws IOException {

        ParameterCheckHelper.checkFileParameter(aFile);
        ParameterCheckHelper.checkObjectParameter(aDocument);

        TextStructure structure = aDocument.getStructure();
        Charset charset = structure.getCharset();
        String lineSeparator = structure.getLineSeparator();

        NestedStreams ns = openFile(aFile, charset);
        OutputStreamWriter os = (OutputStreamWriter) ns.getOuterStream();


        boolean firstLine = true;

        for (String line : aDocument.getContent().getContent()) {

            String nextLine;
            if (firstLine) {

                firstLine = false;
                nextLine = line;

            } else {

                nextLine = TextHelper.concatenateStrings(lineSeparator, line);
            }


            try {

                os.write(nextLine);

            } catch (IOException e) {

                StreamsHelper.closeStreamAfterException(ns, e);
            }
        }

        StreamsHelper.closeStream(ns);
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

        FileOutputStream fos = new FileOutputStream(aFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos, aCharset);

        return new NestedStreamsImpl(osw, fos);
    }

}
