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

package jmul.io;


import java.io.Closeable;
import java.io.IOException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * An implementation of {@link jmul.io.CoupledStreams}.
 *
 * @author Kristian Kutin
 */
public class CoupledStreamsImpl implements CoupledStreams {

    /**
     * Contains all related streams.
     */
    private final Map<String, Closeable> coupledStreams;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param someEntries
     *        all related streams
     */
    public CoupledStreamsImpl(StreamEntry... someEntries) {

        Map<String, Closeable> tmp = new HashMap<>();

        for (StreamEntry entry : someEntries) {

            tmp.put(entry.getName(), entry.getStream());
        }

        coupledStreams = Collections.unmodifiableMap(tmp);
    }

    /**
     * Returns the stream which is associated with the specified name.
     *
     * @param aName
     *        the name of a specific stream
     *
     * @return a stream
     */
    @Override
    public Closeable getStream(String aName) {

        return coupledStreams.get(aName);
    }

    /**
     * Returns the sum of all coupled streams.
     *
     * @return a sum
     */
    @Override
    public int getCoupledStreamCount() {

        return coupledStreams.size();
    }

    /**
     * Close all coupled streams regularly.
     *
     * @throws IOException
     *         is thrown if at least one of the streams cannot be closed properly
     */
    @Override
    public void close() throws IOException {

        Map<String, Throwable> coupledExceptions = new HashMap<>();
        int exceptionCount = 0;

        for (String name : coupledStreams.keySet()) {

            Closeable stream = getStream(name);

            try {

                stream.close();
                coupledExceptions.put(name, null);

            } catch (IOException e) {

                coupledExceptions.put(name, e);
                exceptionCount++;
            }
        }

        if (exceptionCount > 0) {

            throw createException(coupledExceptions);
        }
    }

    /**
     * Closes the streams after an error occurred on the specified stream.
     *
     * @param aName
     *        the name of the stream where an error occurred
     *
     * @throws IOException
     *         if an I/O error occurs
     */
    @Override
    public void closeOnError(String aName) throws IOException {

        Map<String, Throwable> coupledExceptions = new HashMap<>();
        int exceptionCount = 0;

        for (String name : coupledStreams.keySet()) {

            if (name.equals(aName)) {

                continue;
            }

            Closeable stream = getStream(name);

            try {

                stream.close();
                coupledExceptions.put(name, null);

            } catch (IOException e) {

                coupledExceptions.put(name, e);
                exceptionCount++;
            }
        }

        if (exceptionCount > 0) {

            throw createException(coupledExceptions);
        }
    }

    /**
     * Creates an exception according to the specified exceptions.
     *
     * @param someCoupledExceptions
     *        a map with all exceptions
     *
     * @return an exception
     */
    private CoupledStreamsException createException(Map<String, Throwable> someCoupledExceptions) {

        return new CoupledStreamsException(createExceptionMessage(someCoupledExceptions));
    }

    /**
     * Creates an exception message according to the specified exceptions.
     *
     * @param someCoupledExceptions
     *        a map with all exceptions
     *
     * @return an error message
     */
    private String createExceptionMessage(Map<String, Throwable> someCoupledExceptions) {

        StringBuilder buffer = new StringBuilder();

        for (Map.Entry<String, Throwable> entry : someCoupledExceptions.entrySet()) {

            Closeable stream = getStream(entry.getKey());
            Throwable exception = entry.getValue();

            StreamsHelper.addStreamEntry(buffer, stream.getClass().getName(), exception);
        }

        return String.valueOf(buffer);
    }

}
