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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.io.text;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static jmul.io.Constants.END_OF_FILE;

import static jmul.string.Constants.EMPTY_STRING;
import static jmul.string.Constants.STARTING_INDEX;


/**
 * A utility class for handling text based files.<br>
 * <br>
 * <i>Note:<br>
 * Consider merging this utility class with another utility class.</i>
 *
 * @author Kristian kutin
 */
public final class TextFileHelper {

    /**
     * The default constructor.
     */
    private TextFileHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Reads a line from the specified input stream according to the specified line
     * separator.
     *
     * @param aReader
     *        the input stream
     * @param aLineSeparator
     *        the assumed line separator
     *
     * @return a line
     *
     * @throws IOException
     *         is thrown if an error occurs while reading from the input stream
     */
    public static ReadBuffer readLine(BufferedReader aReader, String aLineSeparator) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean endOfFile = false;

        while (true) {

            int i = aReader.read();

            if (i == END_OF_FILE) {

                endOfFile = true;
                break;
            }

            baos.write(i);
            String line = baos.toString();

            if (line.endsWith(aLineSeparator)) {

                break;
            }
        }

        String line = baos.toString();
        line = line.replace(aLineSeparator, EMPTY_STRING);

        return new ReadBuffer(line, endOfFile);
    }

    /**
     * Writes a line to the specified output stream according to the specified line
     * separator.
     *
     * @param aWriter
     *        the output stream
     * @param aString
     *        a string
     * @param aLineSeparator
     *        a line separator
     *
     * @throws IOException
     *         is thrown if an error occurs while writing to the output stream
     */
    public static void writeLine(BufferedWriter aWriter, String aString, String aLineSeparator) throws IOException {

        aWriter.write(aString, STARTING_INDEX, aString.length());
        aWriter.write(aLineSeparator, STARTING_INDEX, aLineSeparator.length());
    }

}
