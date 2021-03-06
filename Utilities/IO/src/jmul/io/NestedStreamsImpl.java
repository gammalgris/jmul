/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.io;


import java.io.Closeable;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A class that manages nested streams.<br>
 * <br>
 * See <a href="http://stackoverflow.com/questions/884007/best-way-to-close-nested-streams-in-java-6">Best way to close nested streams in Java 6</a>.
 */
public class NestedStreamsImpl implements NestedStreams {

    /**
     * The index for the outer stream.
     */
    private static final int OUTER_STREAM_INDEX = 0;

    /**
     * Contains references to all streams (outer to inner) as list.
     */
    private final List<Closeable> nestedStreams;

    /**
     * The index for the inner stream.
     */
    private final int innerStreamIndex;

    /**
     * Creates a new manager for nested streams.
     *
     * @param someStreams
     *        all streams (from outer to inner)
     */
    public NestedStreamsImpl(Closeable... someStreams) {

        nestedStreams = Arrays.asList(someStreams);
        innerStreamIndex = nestedStreams.size() - 1;
    }

    /**
     * Returns the stream at the specified index.
     *
     * @param anIndex
     *        the index of a specific stream
     *
     * @return a stream
     */
    @Override
    public Closeable getStream(int anIndex) {

        return nestedStreams.get(anIndex);
    }

    /**
     * Returns the outer stream.
     *
     * @return a stream
     */
    @Override
    public Closeable getOuterStream() {

        return getStream(OUTER_STREAM_INDEX);
    }

    /**
     * Closes all nested streams.
     *
     * @throws IOException
     *         is thrown if at least one of the streams cannot be closed properly
     */
    @Override
    public void close() throws IOException {

        List<Throwable> nestedExceptions = new ArrayList<>();
        Closeable outerStream = getOuterStream();

        try {

            outerStream.close();

        } catch (IOException e) {

            nestedExceptions.add(e);

            for (int a = 1; a < innerStreamIndex; a++) {

                Closeable innerStream = getStream(a);

                try {

                    innerStream.close();
                    nestedExceptions.add(null);

                } catch (IOException t) {

                    nestedExceptions.add(t);
                }
            }

            throw createException(nestedExceptions);
        }
    }

    /**
     * Creates an exception according to the specified list of nested exceptions.
     *
     * @param someNestedExceptions
     *        a list of exceptions
     *
     * @return an exception
     */
    private NestedStreamsException createException(List<Throwable> someNestedExceptions) {

        return new NestedStreamsException(createExceptionMessage(someNestedExceptions),
                                          someNestedExceptions.get(OUTER_STREAM_INDEX));
    }

    /**
     * Creates an exception message according to the specified list of nested
     * exceptions.
     *
     * @param someNestedExceptions
     *        a list of all exceptions
     *
     * @return an error message
     */
    private String createExceptionMessage(List<Throwable> someNestedExceptions) {

        StringBuilder buffer = new StringBuilder();

        for (int a = 0; a < someNestedExceptions.size(); a++) {

            Closeable stream = getStream(a);
            Throwable exception = someNestedExceptions.get(a);

            StreamsHelper.addStreamEntry(buffer, stream.getClass().getName(), exception);
        }

        return String.valueOf(buffer);
    }

    /**
     * Returns the sum of all nested streams.
     *
     * @return a sum
     */
    @Override
    public int getNestedStreamCount() {

        return nestedStreams.size();
    }

}
