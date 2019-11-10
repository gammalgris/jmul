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

package jmul.io.metadata;


import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Date;

import static jmul.string.Constants.NEW_LINE;
import static jmul.string.Constants.POINT;


/**
 * An implementation of an entity which provides access to a file's meta data.
 *
 * @author Kristian Kutin
 */
public class MetaDataImpl implements MetaData {

    /**
     * The canonical file name.
     */
    private final String fileName;

    /**
     * A handle to the actual file.
     */
    private final Path fileHandle;

    /**
     * The name of the file (without path and file extension).
     */
    private final String name;

    /**
     * The file's file extension.
     */
    private final String fileExtension;

    /**
     * The file's location in the file system.
     */
    private final String path;

    /**
     * The owner of the file.
     */
    private final String owner;

    /**
     * The time of last modification.
     */
    private final Date lastModifiedDate;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aFileName
     *        an absolute or relative path to a file
     *
     * @throws IOException
     *         is thrown if there is an error while trying to read the file's metadata
     */
    public MetaDataImpl(String aFileName) throws IOException {

        File file = new File(aFileName);
        file = file.getCanonicalFile();
        fileName = file.getCanonicalPath();

        String nameWithSuffix = file.getName();
        int index = nameWithSuffix.lastIndexOf(POINT);

        name = nameWithSuffix.substring(0, index);
        fileExtension = nameWithSuffix.substring(index);
        path = file.getParent();
        fileHandle = file.toPath();

        owner = Files.getOwner(fileHandle).getName();

        lastModifiedDate = new Date(Files.getLastModifiedTime(fileHandle).toMillis());
    }

    /**
     * Returns the canonical file name.
     *
     * @return a canonical file name
     */
    @Override
    public String getFileName() {

        return fileName;
    }

    /**
     * Returns the name of the document (i.e. without path and file extension).
     *
     * @return a document name
     */
    @Override
    public String getName() {

        return name;
    }

    /**
     * Returns the path of the document.
     *
     * @return a document path
     */
    @Override
    public String getPath() {

        return path;
    }

    /**
     * Returns the file extension of the document file.
     *
     * @return a file extension
     */
    @Override
    public String getFileExtension() {

        return fileExtension;
    }

    /**
     * Returns the owner of the document file.
     *
     * @return a file owner
     */
    @Override
    public String getOwner() {

        return owner;
    }

    /**
     * Returns the document file's last modified time.
     *
     * @return the last modified time
     */
    @Override
    public Date getLastModifiedTime() {

        return lastModifiedDate;
    }

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
    @Override
    public Object getAttribute(String anAttributeName) throws IOException {

        return Files.getAttribute(fileHandle, anAttributeName);
    }

    /**
     * Returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("name=");
        buffer.append(getName());
        buffer.append(NEW_LINE);

        buffer.append("path=");
        buffer.append(getPath());
        buffer.append(NEW_LINE);

        buffer.append("file extension=");
        buffer.append(getFileExtension());
        buffer.append(NEW_LINE);

        buffer.append("owner=");
        buffer.append(getOwner());
        buffer.append(NEW_LINE);

        buffer.append("last modified time=");
        buffer.append(getLastModifiedTime());

        return buffer.toString();
    }

}
