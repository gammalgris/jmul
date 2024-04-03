/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.web.page;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jmul.io.NestedStreams;
import jmul.io.NestedStreamsImpl;

import static jmul.string.Constants.FILE_SEPARATOR;
import static jmul.string.Constants.SLASH;


/**
 * This data structure wraps details of a single page (i.e. underlying
 * file).
 *
 * @author Kristian Kutin
 */
public class Page {

    /**
     * The base directory of the web content folder.
     */
    private final File baseDirectory;

    /**
     * The file path for for the actual file.
     */
    private final File filePath;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aBaseDirectory
     *        a base directory for the web content
     * @param aFilePath
     *        the file path for the actual file
     */
    public Page(File aBaseDirectory, File aFilePath) {

        super();

        if (aBaseDirectory == null) {

            throw new IllegalArgumentException("No base directory (null) was specified!");
        }

        if (aFilePath == null) {

            throw new IllegalArgumentException("No file path (null) was specified!");
        }

        if (!aBaseDirectory.isDirectory()) {
            
            String message = String.format("An invalid base directory (%s) was specified!", aBaseDirectory);
            throw new IllegalArgumentException(message);
        }

        if (!aFilePath.isFile()) {
            
            String message = String.format("An invalid file path (%s) was specified!", aFilePath);
            throw new IllegalArgumentException(message);
        }

        baseDirectory = aBaseDirectory;
        filePath = aFilePath;
    }

    /**
     * Returns the base directory of the web content.
     *
     * @return a base directory path
     */
    public File baseDirectory() {

        return baseDirectory;
    }

    /**
     * Returns the file path.
     *
     * @return a file path
     */
    public File filePath() {

        return filePath;
    }

    /**
     * Returns the file size in bytes.
     *
     * @return the file size
     */
    public long length() {

        return filePath.length();
    }

    /**
     * Determines the web path for this file relative to the base directory.
     *
     * @return a normalized web path
     *
     * @throws IOException
     *         is thrown if the specified directory or file cannot be resolved to
     *         absolute paths
     */
    public String determineWebPath() throws IOException {

        return determineWebPath(baseDirectory, filePath);
    }

    /**
     * Determines the web path for this file relative to the base directory.
     *
     * @param aBaseDirectory
     *        the base directory of the web content
     * @param aFile
     *        the file path
     *
     * @return a normalized web path
     *
     * @throws IOException
     *         is thrown if the specified directory or file cannot be resolved to
     *         absolute paths
     */
    public static String determineWebPath(File aBaseDirectory, File aFile) throws IOException {

        String directory = aBaseDirectory.getCanonicalPath();
        String fileName = aFile.getCanonicalPath();

        String path = fileName.replace(directory, "");
        path = path.replace(FILE_SEPARATOR, SLASH);

        return path;
    }

    /**
     * Opens a stream to read from the specified file.
     *
     * @return an input stream
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file doesn't exist
     */
    public NestedStreams openStream() throws FileNotFoundException {

        return openStreams(filePath);
    }

    /**
     * Opens a stream to read from the specified file.
     *
     * @param aFile
     *        a file path
     *
     * @return an input stream
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file doesn't exist
     */
    private static NestedStreams openStreams(File aFile) throws FileNotFoundException {

        InputStream reader = new FileInputStream(aFile);
        return new NestedStreamsImpl(reader);
    }

}
