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

package jmul.io.metadata;


import java.io.IOException;

import java.util.Date;


/**
 * This interface describes which meta data a file can provide (see also
 * <a href="https://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html">tutorial</a>).
 * The meta data is only available if the document was saved to the file
 * system at an earlier point in time.
 *
 * @author Kristian Kutin
 */
public interface MetaData {

    /**
     * Returns the canonical file name.
     *
     * @return a canonical file name
     */
    String getFileName();

    /**
     * Returns the name of the document (i.e. without path and file extension).
     *
     * @return a document name
     */
    String getName();

    /**
     * Returns the path of the document.
     *
     * @return a document path
     */
    String getPath();

    /**
     * Returns the file extension of the document file.
     *
     * @return a file extension
     */
    String getFileExtension();

    /**
     * Returns the owner of the document file.
     *
     * @return a file owner
     */
    String getOwner();

    /**
     * Returns the document file's last modified time.
     *
     * @return the last modified time
     */
    Date getLastModifiedTime();

    /**
     * Returns the value of the specified file attribute.
     *
     * @param anAttributeName
     *        the name of a file attribute
     *
     * @return the value of the specified attribute
     *
     * @throws IOException
     *         is thrown if the file attribute cannot be read
     */
    Object getAttribute(String anAttributeName) throws IOException;

}
