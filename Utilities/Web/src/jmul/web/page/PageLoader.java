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


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jmul.io.NestedStreams;

import jmul.logging.Logger;

import jmul.network.http.ResponseCodes;


/**
 * This class represents an entity which loads web content from the file
 * system.
 *
 * @author Kristian Kutin
 */
public class PageLoader {

    /**
     * A reference to a logger.
     */
    private final Logger logger;

    /**
     * Details about the actual page and the underlying file.
     */
    private final Page page;

    /**
     * Creates a new instance of a content loader.
     *
     * @param aLogger
     *        a logger instance
     * @param aPage
     *        details about the page and the underlying file
     */
    public PageLoader(Logger aLogger, Page aPage) {

        super();

        if (aLogger == null) {

            throw new IllegalArgumentException("No logger reference (null) was passed!");
        }

        if (aPage == null) {

            throw new IllegalArgumentException("No page (null) was specified!");
        }

        logger = aLogger;
        page = aPage;
    }

    /**
     * Loads the web content.
     *
     * @return web content
     */
    public PageLoadingResult loadContent() {

        String path;
        try {

            path = getPath();

        } catch (PageLoaderException e) {

            logger.logError(e);
            return new PageLoadingResult(getPath(), ResponseCodes.RC500);
        }

        NestedStreams nestedStreams = null;
        try {

            nestedStreams = page.openStream();

        } catch (FileNotFoundException e) {

            String message = "Unable to load the web content (\"" + page.filePath() + "\")!";
            logger.logError(message);
            return new PageLoadingResult(path, ResponseCodes.RC500);
        }

        long fileSize = page.filePath().length();
        try {

            return loadContent(path, nestedStreams, fileSize);

        } catch (IOException e) {

            try {

                nestedStreams.close();

            } catch (IOException f) {

                logger.logError(f.getMessage());
            }

            String message = "Error while reading from file (\"" + page.filePath() + "\")!";
            logger.logError(message);
            return new PageLoadingResult(path, ResponseCodes.RC500);
        }
    }

    /**
     * Tries to load the web content from the specified file.
     *
     * @param path
     *        a file path
     * @param someNestedStreams
     *        the input stream
     * @param pageSize
     *        the size of the underlying file
     *
     * @return some web content
     *
     * @throws IOException
     *         is thrown if an error occurred while reading from the file
     */
    private static PageLoadingResult loadContent(String path, NestedStreams someNestedStreams,
                                                 long pageSize) throws IOException {

        InputStream reader = (InputStream) someNestedStreams.getOuterStream();

        long maxSize = Integer.MAX_VALUE;
        if (pageSize > maxSize) {

            return new PageLoadingResult(path, ResponseCodes.RC500);
        }

        int size = (int) pageSize;
        byte[] content = new byte[size];

        int index = 0;
        while (true) {

            int next = reader.read();
            if (next == -1) {

                break;
            }

            content[index] = (byte) next;
            index++;
        }

        return new PageLoadingResult(path, content);
    }

    /**
     * Returns the path of the web page.
     *
     * @return a path
     */
    public String getPath() {

        String path = null;

        try {

            path = page.determineWebPath();

        } catch (IOException e) {

            String message =
                "Unable to resolve paths (\"" + page.baseDirectory() + "\" & \"" + page.filePath() + "\")!";
            throw new PageLoaderException(message, e);
        }

        return path;
    }

}
