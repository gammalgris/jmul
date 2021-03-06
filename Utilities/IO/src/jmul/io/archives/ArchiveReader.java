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

package jmul.io.archives;


import java.io.Closeable;
import java.io.IOException;


/**
 * This interface describes a reader for archives (e.g. .zip files, .jar files).
 *
 * @author Kristian Kutin
 */
public interface ArchiveReader extends Closeable {

    /**
     * Loads the specified entry from the archive.
     *
     * @param anEntryName
     *        the name of a file inside the archive
     *
     * @return the raw data representing the specified entry or <code>null</code>
     *         if no such entry exists
     *
     * @throws IOException
     *         The exception is thrown if an error occurs while reading from the
     *         specified archive.
     */
    byte[] loadEntry(String anEntryName) throws IOException;

}
