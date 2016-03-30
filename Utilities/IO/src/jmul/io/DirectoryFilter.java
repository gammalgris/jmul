/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.io;


import java.io.File;
import java.io.FileFilter;


/**
 * This class implements a file filter.
 *
 * @author Kristian Kutin
 */
public final class DirectoryFilter implements FileFilter {

    /**
     * The default constructor.
     */
    public DirectoryFilter() {

        super();
    }

    /**
     * The method accept tests whether or not the path is a directory.
     *
     * @param pathname
     *        a path
     *
     * @return true, if the path is a directory, else false
     */
    public boolean accept(File pathname) {

        return pathname.isDirectory();
    }

}
