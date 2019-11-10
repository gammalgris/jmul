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


/**
 * This class represents an entry consisting of a name and a stream.
 *
 * @author Kristian Kutin
 */
public final class StreamEntry {

    /**
     * The name associated with the stream.
     */
    private final String name;

    /**
     * A stream.
     */
    private final Closeable stream;

    /**
     * Creates a new entry according to the psecified parameters.
     *
     * @param aName
     *        a name associated with a stream
     * @param aStream
     *        an input or output stream
     */
    public StreamEntry(String aName, Closeable aStream) {

        checkParameter(aName);
        checkParameter(aStream);

        name = aName;
        stream = aStream;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aName
     */
    private static void checkParameter(String aName) {

        if (aName == null) {

            String message = "No name has been specified (null)!";
            throw new IllegalArgumentException(message);
        }

        if (aName.trim().isEmpty()) {

            String message = "No name has been specified (empty string)!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameter.
     *
     * @param aStream
     */
    private static void checkParameter(Closeable aStream) {

        if (aStream == null) {

            String message = "No stream has been specified (null)!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * A getter method.
     *
     * @return a name
     */
    public String getName() {

        return name;
    }

    /**
     * A getter method.
     *
     * @return a stream
     */
    public Closeable getStream() {

        return stream;
    }

}
