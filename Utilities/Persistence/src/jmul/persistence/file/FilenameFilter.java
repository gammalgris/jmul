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

package jmul.persistence.file;


import java.io.File;
import java.io.FileFilter;


/**
 * An implementation of a file filter.
 *
 * @author Kristian Kutin
 */
public class FilenameFilter implements FileFilter {

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
    public FilenameFilter(String aFilename) {

        expectedFilename = aFilename;
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
    public boolean accept(File pathname) {

        return pathname.isFile() &&
            pathname.getName().equals(expectedFilename);
    }

}
