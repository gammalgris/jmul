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

package jmul.io;


import java.io.File;
import java.io.IOException;


/**
 * A wrapper class for path details. It extends {@link java.io.File#} in order
 * to be compatible with the existing standard library, but adds additional
 * informations and functionalities.<br>
 * <br>
 * <i>Note:<br>
 * Immutable class members may not have accessors and may be even public
 * accessible.</i>.<br>
 *
 * @author Kristian Kutin
 */
public final class PathDetails extends File {

    /**
     * A relative path.
     */
    private static final String CURRENT_WORKING_DIRECTORY;

    /*
     * The static initializer.
     */
    static {

        CURRENT_WORKING_DIRECTORY = ".";
    }

    /**
     * A normalized path (i.e. canonical path). This class member provides the canonical
     * path as provided by the member function {@link java.io.File#getCanonicalPath}.
     * This class member is initialized once and thus avoids having to repeatedly handle
     * the exception which the member function can throw.
     */
    public final String normalizedPath;

    /**
     * A path as specified when invoking the constructor.
     */
    public final String path;

    /**
     * The default constructor.
     */
    public PathDetails() {

        this(CURRENT_WORKING_DIRECTORY);
    }

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param path
     *        a file or directory path
     */
    public PathDetails(String path) {

        super(checkParameter(path));

        this.path = path;

        try {

            this.normalizedPath = getCanonicalPath();

        } catch (IOException e) {

            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Checks the specified path parameter.
     *
     * @param path
     *        a file or directory path
     *
     * @return the specified file or directory path
     */
    private static String checkParameter(String path) {

        if (path == null) {

            throw new IllegalArgumentException("No path (null) was specified!");
        }

        return path;
    }

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param file
     *        a file or directory path
     */
    public PathDetails(File file) {

        this(checkParameterAndExtractPath(file));
    }

    /**
     * Checks the specified file parameter and extracts the path.
     *
     * @param file
     *        a file or directory path
     *
     * @return the specified file or directory path
     */
    private static String checkParameterAndExtractPath(File file) {

        if (file == null) {

            throw new IllegalArgumentException("No file (null) was specified!");
        }

        return file.getPath();
    }

}
