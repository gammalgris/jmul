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


import java.nio.ByteOrder;

import jmul.document.binary.structure.WordLengths;


/**
 * A base implementation of a document reader.
 *
 * @author Kristian Kutin
 */
abstract class BinaryFileReaderBase implements BinaryFileReader {

    /**
     * A byte order for reading from files.
     */
    private final ByteOrder byteOrder;

    /**
     * A word length for reading raw data from files.
     */
    private final WordLengths wordLength;

    /**
     * Creates a new reader instance according to the specified parameters.
     *
     * @param aByteOrder
     * @param aWordLength
     */
    protected BinaryFileReaderBase(ByteOrder aByteOrder, WordLengths aWordLength) {

        super();

        byteOrder = aByteOrder;
        wordLength = aWordLength;
    }

    /**
     * A getter method.
     *
     * @return a byte order
     */
    public ByteOrder getByteOrder() {

        return byteOrder;
    }

    /**
     * A getter method.
     *
     * @return a word length
     */
    public WordLengths getWordLength() {

        return wordLength;
    }

}
