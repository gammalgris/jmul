/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package jmul.io.filters;


import java.io.File;
import java.io.FileFilter;

import jmul.string.TextHelper;


/**
 * This class implements a file filter.
 *
 * @author Kristian Kutin
 */
public final class FileExtensionFilter implements FileFilter {

    /**
     * The class member contains a string constant.
     */
    private static final String SEPARATOR = ".";

    /**
     * The class member contains a file extension.
     */
    private final String fileExtensionWithSeparator;

    /**
     * The default constructor.
     *
     * @param aFileExtension
     *        a file extension (without separator '.')
     */
    public FileExtensionFilter(String aFileExtension) {

        checkParameter(aFileExtension);

        fileExtensionWithSeparator = SEPARATOR + aFileExtension;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aFileExtension
     *
     * @throws IllegalArgumentException
     *         if the specified parameter is invalid
     */
    private static void checkParameter(String aFileExtension) {

        if (aFileExtension == null) {

            String message = "No file extension (null) has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (aFileExtension.trim().isEmpty()) {

            String message = "No file extension (empty string) has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (aFileExtension.indexOf(SEPARATOR) > -1) {

            String message =
                TextHelper.concatenateStrings("The file extension contains a separator character \"", SEPARATOR, "\"!");
            throw new IllegalArgumentException(message);
        }

        if (!aFileExtension.equals(aFileExtension.trim())) {

            String message =
                TextHelper.concatenateStrings("The file extension (\"", aFileExtension,
                                              "\") contains leading or trailing spaces!");
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * The method accept tests whether or not the path is a file with the
     * given file extension.
     *
     * @param pathname
     *        a path
     *
     * @return true, if the path is a file with the given file extension,
     *         else false
     */
    @Override
    public boolean accept(File pathname) {

        if (pathname.isFile()) {

            String filename = pathname.getAbsolutePath();
            return filename.endsWith(fileExtensionWithSeparator);
        }

        return false;
    }

}
