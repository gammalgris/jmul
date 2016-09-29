/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package jmul.markdown;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import jmul.io.CoupledStreams;
import jmul.io.CoupledStreamsImpl;
import jmul.io.StreamEntry;


/**
 * A utility class for managing an input and output stream.
 *
 * @author Kristian Kutin
 */
public final class StreamsHelper {

    /**
     * The name of a stream.
     */
    public static final String INPUT_STREAM = "input stream";

    /**
     * The name of a stream.
     */
    public static final String OUTPUT_STREAM = "output stream";

    /**
     * The default constructor.
     */
    private StreamsHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Opens an input and output stream according to the specified parameters
     * and returns both streams.
     *
     * @param aSourceFile
     * @param anOutputFile
     *
     * @return an input and output stream
     *
     * @throws FileNotFoundException
     *         is thrown if the input file doesn't exist
     * @throws IOException
     *         is thrown if the output file cannot be opened
     */
    public static CoupledStreams openStreams(File aSourceFile, File anOutputFile) throws FileNotFoundException,
                                                                                         IOException {

        BufferedReader r = new BufferedReader(new FileReader(aSourceFile));
        BufferedWriter w = new BufferedWriter(new FileWriter(anOutputFile));

        StreamEntry in = new StreamEntry(INPUT_STREAM, r);
        StreamEntry out = new StreamEntry(OUTPUT_STREAM, w);

        return new CoupledStreamsImpl(in, out);
    }

}
