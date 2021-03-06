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

package jmul.io;


import java.io.Closeable;
import java.io.IOException;

import jmul.metainfo.annotations.Modified;

import jmul.misc.exceptions.MultipleCausesException;

import static jmul.string.Constants.NEW_LINE;


/**
 * A base class for custom stream implementations.
 *
 * @author Kristian Kutin
 *
 * @deprecated Stream handling is being reviewed and where possible the try-with-resources
 *             statement will be used. This may make some utility functions obsolete.
 */
@Deprecated
public final class StreamsHelper {

    /**
     * The default constructor.
     */
    private StreamsHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Adds an entry with details of the specified stream to the specified buffer.
     *
     * @param aBuffer
     *        the buffer containing the whole error message
     * @param aStreamType
     *        the type of a stream (i.e. class name)
     * @param anException
     *        an exception or <code>null</code> if no exception occurred on closing the
     *        stream
     */
    public static void addStreamEntry(@Modified StringBuilder aBuffer, String aStreamType, Throwable anException) {

        aBuffer.append(aStreamType);

        if (anException == null) {

            aBuffer.append(" was closed regularly.");

        } else {

            aBuffer.append(" ");
            aBuffer.append(anException.getClass().getName());
            aBuffer.append(" ");
            aBuffer.append(anException.getMessage());
        }

        aBuffer.append(NEW_LINE);
    }

    /**
     * Tries to close the specified stream.
     *
     * @param aStream
     *        an input or output stream
     *
     * @throws IOException
     *         is thrown if the specified stream couldn't be closed properly
     */
    public static void closeStream(Closeable aStream) throws IOException {

        aStream.close();
    }

    /**
     * Tries to close the specified stream after an exception occurred on a
     * previous action.
     *
     * @param aStream
     *        an input or output stream
     * @param aPreviousException
     *        the exception of the previous stream operation
     *
     * @throws IOException
     *         is always thrown because an error has occured before
     */
    public static void closeStreamAfterException(Closeable aStream, IOException aPreviousException) throws IOException {

        try {

            aStream.close();

        } catch (IOException e) {

            throw new IOException(new MultipleCausesException(aPreviousException, e));
        }

        throw aPreviousException;
    }

}
