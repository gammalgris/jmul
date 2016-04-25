/*
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

package jmul.io.filters;


import java.io.File;
import java.io.FileFilter;

import jmul.string.StringConcatenator;


/**
 * An implementation of a file filter.
 *
 * @author Kristian Kutin
 */
public class ExactFileNameFilter implements FileFilter {

    /**
     * The name of the file which is searched.
     */
    private final String expectedFilename;

    /**
     * Constructs a file filter.
     *
     * @param aFilename
     *        the name of the file which is searched
     */
    public ExactFileNameFilter(String aFilename) {

        checkParameter(aFilename);

        expectedFilename = aFilename;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aFilename
     *
     * @throws IllegalArgumentException
     *         if the specified parameter is invalid
     */
    private static void checkParameter(String aFilename) {

        if (aFilename == null) {

            String message = "No file name (null) has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (aFilename.trim().isEmpty()) {

            String message = "No file name (empty string) has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (!aFilename.equals(aFilename.trim())) {

            StringConcatenator message =
                new StringConcatenator("The file name (\"", aFilename, "\") contains leading or trailing spaces!");
            throw new IllegalArgumentException(String.valueOf(message));
        }
    }

    /**
     * Checks if the specified file matches the expected filename.
     *
     * @param pathname
     *        a file or directory
     *
     * @return <code>true</code> if the specified file matches the expected
     *         filename, else false
     */
    @Override
    public boolean accept(File pathname) {

        return pathname.isFile() && pathname.getName().equals(expectedFilename);
    }

}
