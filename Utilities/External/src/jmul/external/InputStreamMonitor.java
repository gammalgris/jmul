/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package jmul.external;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static jmul.string.Constants.NEW_LINE;


/**
 * This class monitors an input stream.
 *
 * @author Kristian Kutin
 */
public class InputStreamMonitor implements Runnable {

    /**
     * A reader for the input stream.
     */
    private BufferedReader reader;

    /**
     * The content of the input stream.
     */
    private StringBuffer streamContent;

    /**
     * A flag to indicate the end of the stream.
     */
    private volatile boolean endOfStream;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param anInputStream
     *        an input stream
     */
    InputStreamMonitor(InputStream anInputStream) {

        InputStreamReader isr = new InputStreamReader(anInputStream);
        reader = new BufferedReader(isr);

        streamContent = new StringBuffer();

        endOfStream = false;
    }

    /**
     * Reads the input stream and writes the content to a buffer.
     */
    @Override
    public void run() {

        boolean firstLine = true;

        try {

            while (!endOfStream) {

                String line = reader.readLine();

                endOfStream = line == null;
                if (!endOfStream) {

                    if (firstLine) {

                        firstLine = !firstLine;

                    } else {

                        streamContent.append(NEW_LINE);
                    }

                    streamContent.append(line);
                }
            }

            reader.close();

        } catch (IOException e) {

            endOfStream = true;
        }
    }

    /**
     * Checks if the end of the input stream has been reached.
     *
     * @return <code>true</code> if the end of the input stream has been reached,
     *         else <code>false</code>
     */
    public boolean reachedEndOfStream() {

        return endOfStream;
    }

    /**
     * Returns the content of the input stream.
     *
     * @return the content of the input stream or <code>null</code> if the end of the input stream
     *         has not been reached
     */
    public String getStreamContent() {

        if (endOfStream) {

            return streamContent.toString();

        } else {

            return null;
        }
    }

}
