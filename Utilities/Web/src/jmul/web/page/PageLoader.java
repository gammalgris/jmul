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

package jmul.web.page;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import jmul.io.NestedStreams;
import jmul.io.NestedStreamsImpl;

import jmul.misc.exceptions.MultipleCausesException;

import static jmul.string.Constants.FILE_SEPARATOR;
import static jmul.string.Constants.SLASH;


/**
 * This class represents an entity which loads web content from the file
 * system.
 *
 * @author Kristian Kutin
 */
public class PageLoader {

    /**
     * The base directory of the web content.
     */
    private final File baseDirectory;

    /**
     * The file with the page content.
     */
    private final File file;

    /**
     * Creates a new instance of a content loader.
     *
     * @param aBaseDirectory
     *        a base directory
     * @param aFile
     *        a file (i.e. file path)
     */
    public PageLoader(File aBaseDirectory, File aFile) {

        baseDirectory = aBaseDirectory;
        file = aFile;
    }

    /**
     * Loads the web content.
     *
     * @return web content
     */
    public PublishedPage loadContent() {

        String path = getPath();


        NestedStreams nestedStreams = null;

        try {

            nestedStreams = openStreams(file);

        } catch (FileNotFoundException e) {

            String message = "Unable to load the web content (\"" + file + "\")!";
            throw new PageLoaderException(message, e);
        }


        byte[] content = null;

        try {

            content = loadContent(nestedStreams);

        } catch (IOException e) {

            Throwable followupError = null;

            try {

                nestedStreams.close();

            } catch (IOException f) {

                followupError = f;
            }

            String message = "Error while reading from file (\"" + file + "\")!";

            if (followupError != null) {

                throw new PageLoaderException(message, new MultipleCausesException(e, followupError));

            } else {

                throw new PageLoaderException(message, followupError);
            }
        }


        return new PublishedPage(path, content);
    }

    /**
     * Determines the web path for this file relative to the base directory.
     *
     * @param aBaseDirectory
     * @param aFile
     *
     * @return a path
     *
     * @throws IOException
     *         is thrown if the specified directory or file cannot be resolved to
     *         absolute paths
     */
    private static String determinePath(File aBaseDirectory, File aFile) throws IOException {

        String directory = aBaseDirectory.getCanonicalPath();
        String fileName = aFile.getCanonicalPath();

        String path = fileName.replace(directory, "");
        path = path.replace(FILE_SEPARATOR, SLASH);

        return path;
    }

    /**
     * Opens a stream to read from the specified file.
     *
     * @param aFile
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

    /**
     * Tries to load the web content from the specified file.
     *
     * @param someNestedStreams
     *
     * @return some web content
     *
     * @throws IOException
     *         is thrown if an error occurred while reading from the file
     */
    private static byte[] loadContent(NestedStreams someNestedStreams) throws IOException {

        InputStream reader = (InputStream) someNestedStreams.getOuterStream();
        List<Byte> buffer = new ArrayList<>();

        while (true) {

            int next = reader.read();
            if (next == -1) {

                break;
            }

            buffer.add((byte) next);
        }


        int size = buffer.size();
        byte[] bytes = new byte[size];

        for (int a = 0; a < size; a++) {

            Byte b = buffer.get(a);
            bytes[a] = b;
        }

        return bytes;
    }

    /**
     * Returns the path of the web page.
     *
     * @return a path
     */
    public String getPath() {

        String path = null;

        try {

            path = determinePath(baseDirectory, file);

        } catch (IOException e) {

            String message = "Unable to resolve paths (\"" + baseDirectory + "\" & \"" + file + "\")!";
            throw new PageLoaderException(message, e);
        }

        return path;
    }

}
