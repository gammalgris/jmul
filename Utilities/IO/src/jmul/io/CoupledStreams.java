/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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


/**
 * This interface describes a utility entity for working with coupled streams (e.g. an input
 * and output strem) and handles various aspects (e.g. closing all streams in case of an
 * error to one stream).
 *
 * @author Kristian Kutin
 */
public interface CoupledStreams extends Closeable {

    /**
     * Returns the stream which is associated with the specified name.
     *
     * @param aName
     *
     * @return a stream
     */
    Closeable getStream(String aName);

    /**
     * Returns the sum of all coupled streams.
     *
     * @return a sum
     */
    int getCoupledStreamCount();

    /**
     * Closes the streams after an error occurred on the specified stream.
     *
     * @param aName
     *        the name of the stream where an error occurred
     *
     * @throws IOException
     *         if an I/O error occurs
     */
    void closeOnError(String aName) throws IOException;

}
