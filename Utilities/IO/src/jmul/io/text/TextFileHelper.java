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

package jmul.io.text;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.Charset;

import static jmul.io.Constants.END_OF_FILE;
import jmul.io.NestedStreams;
import jmul.io.NestedStreamsImpl;

import static jmul.string.Constants.EMPTY_STRING;


/**
 * A utility class for handling text based files.
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
     * Opens the specified file for subsequent reading operations.
     *
     * @param aFileName
     *
     * @return an input stream
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file doesn't exist
     */
    public static NestedStreams openFile(String aFileName) throws FileNotFoundException {

        return openFile(new File(aFileName));
    }

    /**
     * Opens the specified file for subsequent reading operations.
     *
     * @param aFile
     *
     * @return an input stream
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file doesn't exist
     */
    public static NestedStreams openFile(File aFile) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(aFile);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        NestedStreams ns = new NestedStreamsImpl(br, isr, fis);

        return ns;
    }

    /**
     * Opens the specified file for subsequent reading operations.
     *
     * @param aFileName
     * @param aCharset
     *
     * @return an input stream
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file doesn't exist
     */
    public static NestedStreams openFile(String aFileName, Charset aCharset) throws FileNotFoundException {

        return openFile(new File(aFileName), aCharset);
    }

    /**
     * Opens the specified file for subsequent reading operations.
     *
     * @param aFile
     * @param aCharset
     *
     * @return an input stream
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file doesn't exist
     */
    public static NestedStreams openFile(File aFile, Charset aCharset) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(aFile);
        InputStreamReader isr = new InputStreamReader(fis, aCharset);
        BufferedReader br = new BufferedReader(isr);

        NestedStreams ns = new NestedStreamsImpl(br, isr, fis);

        return ns;
    }

    /**
     * Closes all input streams.
     *
     * @param someNestedStreams
     *
     * @throws IOException
     *         is thrown if an error occurs while closing the input streams
     */
    public static void closeFile(NestedStreams someNestedStreams) throws IOException {

        someNestedStreams.close();
    }

    /**
     * Reads a line from the specified input stream according to the specified line
     * separator.
     *
     * @param someNestedStreams
     * @param aLineSeparator
     *
     * @return a line
     *
     * @throws IOException
     *         is thrown if an error occurs while reading from the input stream
     */
    public static ReadBuffer readLine(NestedStreams someNestedStreams, String aLineSeparator) throws IOException {

        BufferedReader br = (BufferedReader) someNestedStreams.getOuterStream();


        StringBuilder buffer = new StringBuilder();
        boolean endOfFile = false;

        while (true) {

            int i = br.read();

            if (i == END_OF_FILE) {

                endOfFile = true;
                break;
            }

            char c = (char) i;

            buffer.append(c);
            String line = buffer.toString();

            if (line.endsWith(aLineSeparator)) {

                break;
            }
        }

        String line = buffer.toString();
        line = line.replace(aLineSeparator, EMPTY_STRING);

        return new ReadBuffer(line, endOfFile);
    }

}
