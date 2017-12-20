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

package jmul.binary.writer;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import jmul.document.binary.BinaryFile;

import jmul.io.StreamsHelper;


/**
 * An implementation of a document writer.
 *
 * @author Kristian Kutin
 */
public class BinaryFileWriterImpl implements BinaryFileWriter {

    /**
     * The default constructor.
     */
    public BinaryFileWriterImpl() {

        super();
    }

    /**
     * The method writes a document.
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
    public void writeDocument(String aFilename, BinaryFile aDocument) throws IOException {

        writeDocument(new File(aFilename), aDocument);
    }

    /**
     * The method writes a document.
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
    public void writeDocument(File aFile, BinaryFile aDocument) throws IOException {

        OutputStream outputStream = new FileOutputStream(aFile);

        try {

            outputStream.write(aDocument.getContent());
            outputStream.flush();

        } catch (IOException e) {

            StreamsHelper.closeStreamAfterException(outputStream, e);
        }

        StreamsHelper.closeStream(outputStream);
    }

}
