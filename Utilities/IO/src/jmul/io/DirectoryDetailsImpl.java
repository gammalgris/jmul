/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.io;


import java.io.File;


/**
 * An implementation of directory details.
 *
 * @author Kristian Kutin
 */
public abstract class DirectoryDetailsImpl implements DirectoryDetails {

    /**
     * A directory name (i.e. directory path).
     */
    private final String directoryName;

    /**
     * A file object which contains the directory path.
     */
    private final File directory;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aDirectoryName
     *        a directory name (i.e. directory path)
     * @param create
     *        a flag which indicates that the specified directory should be
     *        created (<code>true</code>) or not (<code>false</code>)
     */
    public DirectoryDetailsImpl(String aDirectoryName, boolean create) {

        if (aDirectoryName == null) {

            String message = "No directory name was specified (null)!";
            throw new IllegalArgumentException(message);
        }


        directoryName = aDirectoryName;
        directory = new File(directoryName);


        if (directory.isFile()) {

            String message = "Instead of a directory a file was specified (" + directoryName + ")!";
            throw new IllegalArgumentException(message);
        }


        if (create) {

            directory.mkdirs();

        } else {

            if (!directory.exists()) {

                String message = "A directory was specified which doesn't exist (" + aDirectoryName + ")!";
                throw new IllegalArgumentException(message);
            }
        }
    }

    /**
     * A getter method.
     *
     * @return a directory name (i.e. directory path)
     */
    @Override
    public String getDirectoryName() {

        return directoryName;
    }

    /**
     * A getter method.
     *
     * @return a file object which contains the directory path
     */
    @Override
    public File getDirectory() {

        return directory;
    }

}
