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

package jmul.io.text;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.nio.charset.Charset;

import static jmul.io.Constants.END_OF_FILE;
import jmul.io.NestedStreams;
import jmul.io.NestedStreamsImpl;

import static jmul.string.Constants.EMPTY_STRING;
import static jmul.string.Constants.STARTING_INDEX;


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
     *        a string containing an absolute or relative file path
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
     *        a file object
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

        return new NestedStreamsImpl(br, isr, fis);
    }

    /**
     * Opens the specified file for subsequent reading operations.
     *
     * @param aFileName
     *        a string containing an absolute or relative file path
     * @param aCharset
     *        the assumed charset of the file
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
     *        a file object
     * @param aCharset
     *        the assumed charset of the file
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

        return new NestedStreamsImpl(br, isr, fis);
    }

    /**
     * Creates the specified file for subsequent writing operations.
     *
     * @param aFileName
     *        a string containing an absolute or relative file path
     *
     * @return an input stream
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file cannot be created
     */
    public static NestedStreams createFile(String aFileName) throws FileNotFoundException {

        return createFile(new File(aFileName));
    }

    /**
     * Creates the specified file for subsequent writing operations.
     *
     * @param aFile
     *        a file object
     *
     * @return an output stream
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file cannot be created
     */
    public static NestedStreams createFile(File aFile) throws FileNotFoundException {

        FileOutputStream fos = new FileOutputStream(aFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);

        return new NestedStreamsImpl(bw, osw, fos);
    }

    /**
     * Closes all input streams.
     *
     * @param someNestedStreams
     *        all streams
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
     * @param aCharset
     *        the assumed charset of the file
     * @param someNestedStreams
     *        the input stream
     * @param aLineSeparator
     *        the assumed line separator
     *
     * @return a line
     *
     * @throws IOException
     *         is thrown if an error occurs while reading from the input stream
     */
    public static ReadBuffer readLine(Charset aCharset, NestedStreams someNestedStreams,
                                      String aLineSeparator) throws IOException {

        BufferedReader br = (BufferedReader) someNestedStreams.getOuterStream();


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean endOfFile = false;

        while (true) {

            int i = br.read();

            if (i == END_OF_FILE) {

                endOfFile = true;
                break;
            }

            baos.write(i);
            String line = baos.toString(aCharset.name());

            if (line.endsWith(aLineSeparator)) {

                break;
            }
        }

        String line = baos.toString(aCharset.name());
        line = line.replace(aLineSeparator, EMPTY_STRING);

        return new ReadBuffer(line, endOfFile);
    }

    /**
     * Writes a line to the specified output stream according to the specified line
     * separator.
     *
     * @param someNestedStreams
     *        the output stream
     * @param aString
     *        a string
     * @param aLineSeparator
     *        a line separator
     *
     * @throws IOException
     *         is thrown if an error occurs while writing to the output stream
     */
    public static void writeLine(NestedStreams someNestedStreams, String aString,
                                 String aLineSeparator) throws IOException {

        BufferedWriter bw = (BufferedWriter) someNestedStreams.getOuterStream();

        bw.write(aString, STARTING_INDEX, aString.length());
        bw.write(aLineSeparator, STARTING_INDEX, aLineSeparator.length());
    }

}
