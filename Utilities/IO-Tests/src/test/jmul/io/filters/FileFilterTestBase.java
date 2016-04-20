/*
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

package test.jmul.io.filters;

import java.io.FileFilter;


/**
 * A base class for testing file filters.
 *
 * @author Kristian Kutin
 */
public class FileFilterTestBase {

    /**
     * A file filter.
     */
    private FileFilter fileFilter;

    /**
     * The default constructor.
     */
    protected FileFilterTestBase() {
    }

    /**
     * A setter method.
     *
     * @param aFileFilter
     */
    public void setFileFilter(FileFilter aFileFilter) {

        fileFilter = aFileFilter;
    }

    /**
     * A getter method.
     *
     * @return a file filter
     */
    public FileFilter getFileFilter() {

        return fileFilter;
    }

}
