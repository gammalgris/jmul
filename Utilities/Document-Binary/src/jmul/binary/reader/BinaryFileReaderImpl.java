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

package jmul.binary.reader;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.ByteOrder;

import java.util.ArrayList;
import java.util.List;

import jmul.document.binary.BinaryFile;
import jmul.document.binary.BinaryFileImpl;
import jmul.document.binary.structure.WordLengths;
import jmul.document.type.DocumentType;
import jmul.document.type.DocumentTypes;

import static jmul.io.Constants.END_OF_FILE;
import jmul.io.StreamsHelper;

import jmul.misc.annotations.Modified;


/**
 * An implementation of a binary file reader.
 *
 * @author Kristian Kutin
 */
public class BinaryFileReaderImpl extends BinaryFileReaderBase {

    /**
     * A default byte order.
     */
    private static final ByteOrder DEFAULT_BYTE_ORDER = ByteOrder.BIG_ENDIAN;

    /**
     * A default word length.
     */
    private static final WordLengths DEFAULT_WORD_LENGTH = WordLengths.WORD_32_BIT;

    /**
     * A default value for initializing or resetting a byte array.
     */
    private static final byte DEFAULT_BYTE_VALUE = (byte) 0b00000000;

    /**
     * The default constructor.
     */
    public BinaryFileReaderImpl() {

        this(DEFAULT_BYTE_ORDER, DEFAULT_WORD_LENGTH);
    }

    /**
     * Creates a new reader instance according to the specified parameters.
     *
     * @param aByteOrder
     *        the assumed byte order
     */
    public BinaryFileReaderImpl(ByteOrder aByteOrder) {

        this(aByteOrder, DEFAULT_WORD_LENGTH);
    }

    /**
     * Creates a new reader instance according to the specified parameters.
     *
     * @param aWordLength
     *        the assumed word length
     */
    public BinaryFileReaderImpl(WordLengths aWordLength) {

        super(DEFAULT_BYTE_ORDER, aWordLength);
    }

    /**
     * Creates a new reader instance according to the specified parameters.
     *
     * @param aByteOrder
     *        the assumed byte order
     * @param aWordLength
     *        the assumed word length
     */
    public BinaryFileReaderImpl(ByteOrder aByteOrder, WordLengths aWordLength) {

        super(aByteOrder, aWordLength);
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
    public BinaryFile readFrom(String aFilename) throws IOException {

        return readFrom(new File(aFilename));
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
    public BinaryFile readFrom(File aFile) throws IOException {

        DocumentType documentType = DocumentTypes.getDocumentType(aFile.getName());

        byte[] readBuffer = newBuffer(getWordLength());
        List<Byte> contentBuffer = new ArrayList<Byte>();


        InputStream inputStream = new FileInputStream(aFile);

        while (true) {


            int bytes = -1;
            try {

                bytes = inputStream.read(readBuffer);

            } catch (IOException e) {

                StreamsHelper.closeStreamAfterException(inputStream, e);
            }

            if (bytes == END_OF_FILE) {

                break;
            }

            for (int a = 0; a < bytes; a++) {

                contentBuffer.add(readBuffer[a]);
            }

            clearBuffer(readBuffer);
        }

        StreamsHelper.closeStream(inputStream);


        int size = contentBuffer.size();
        byte[] content = new byte[size];

        for (int a = 0; a < size; a++) {

            content[a] = contentBuffer.get(a);
        }


        return new BinaryFileImpl(documentType, getByteOrder(), getWordLength(), content);
    }

    /**
     * Creates a new buffer for reading the file.
     *
     * @param aWordLength
     *        the word length which determines the buffer size
     *
     * @return a buffer
     */
    private static byte[] newBuffer(WordLengths aWordLength) {

        return new byte[aWordLength.getBufferSize()];
    }

    /**
     * Clears the specified buffer.
     *
     * @param aBuffer
     *        the buffer which is to be cleared
     */
    private static void clearBuffer(@Modified byte[] aBuffer) {

        for (int a = 0; a < aBuffer.length; a++) {

            aBuffer[a] = DEFAULT_BYTE_VALUE;
        }
    }

}
