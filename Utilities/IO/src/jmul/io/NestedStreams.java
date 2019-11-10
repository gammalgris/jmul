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
 * This interface describes an entity that handles nested streams.
 *
 * @author Kristian Kutin
 * 
 * @deprecated Stream handling is being reviewed and where possible the try-with-resources
 *             statement will be used. This may make some utility functions obsolete.
 */
@Deprecated
public interface NestedStreams extends Closeable {

    /**
     * Returns the stream at the specified index.
     *
     * @param anIndex
     *        the index of the requested stream
     *
     * @return a stream
     */
    Closeable getStream(int anIndex);

    /**
     * Returns the outer stream.
     *
     * @return a stream
     */
    Closeable getOuterStream();

    /**
     * Returns the sum of all nested streams.
     *
     * @return a sum
     */
    int getNestedStreamCount();

}
