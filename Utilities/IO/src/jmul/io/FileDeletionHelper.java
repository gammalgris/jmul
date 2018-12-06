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

package jmul.io;


import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import jmul.checks.ParameterCheckHelper;


/**
 * This class provides a convenience method for deleting files. The code has originally been taken from
 * <a href="http://forums.codeguru.com/showthread.php?42548-File-delete%28%29-not-working&p=113634#post113634">forums.codeguru.com</a>
 * but was modified significantly.<br>
 * <br>
 * <i>Note:<br>
 * The current implementation might not work reliably with network drives which react sluggishly.</i>
 */
public final class FileDeletionHelper {

    /**
     * A flag for a deletion option.
     */
    private static final boolean WITHOUT_RECURSION = false;

    /**
     * The default constructor.
     */
    private FileDeletionHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Delete the specified file or directory. The method checks certain plausibilites (e.g.
     * file is write protected, file handles exist, etc.) and throws an exception if the
     * deletion was not possible.
     *
     * @param path
     *        an absolute or relative path to a file or directory
     */
    public static void delete(String path) {

        delete(path, WITHOUT_RECURSION);
    }

    /**
     * Delete the specified file or directory. The method checks certain plausibilites (e.g.
     * file is write protected, file handles exist, etc.) and throws an exception if the
     * deletion was not possible.
     *
     * @param path
     *        an absolute or relative path to a file or directory
     * @param recurse
     *        a flag indicating that deletion should be done with or without recursion
     */
    public static void delete(String path, boolean recurse) {

        ParameterCheckHelper.checkFileNameParameter(path);

        File f = new File(path);

        delete(f, recurse);
    }

    /**
     * Delete the specified file or directory. The method checks certain plausibilites (e.g.
     * file is write protected, file handles exist, etc.) and throws an exception if the
     * deletion was not possible.
     *
     * @param path
     *        an absolute or relative path to a file or directory
     */
    public static void delete(File path) {

        delete(path, WITHOUT_RECURSION);
    }

    /**
     * Delete the specified file or directory. The method checks certain plausibilites (e.g.
     * file is write protected, file handles exist, etc.) and throws an exception if the
     * deletion was not possible.
     *
     * @param path
     *        an absolute or relative path to a file or directory
     * @param recurse
     *        an absolute or relative path to a file or directory
     */
    public static void delete(File path, boolean recurse) {

        ParameterCheckHelper.checkFileParameter(path);

        boolean notExists = !path.exists();
        if (notExists) {

            if (Files.isSymbolicLink(path.toPath())) {

                throw new FileDeletionException("The specified path is a symbolic link", path);

            } else {

                throw new FileDeletionException("No such file or directory", path);
            }
        }

        boolean isWriteProtected = !path.canWrite();
        if (isWriteProtected) {

            throw new FileDeletionException("File is write protected", path);
        }

        boolean isDirectory = path.isDirectory();
        if (isDirectory) {

            File[] files = path.listFiles();

            if (recurse) {

                for (File f : files) {

                    delete(f, recurse);
                }

            } else {

                boolean directoryIsNotEmpty = files.length > 0;
                if (directoryIsNotEmpty) {

                    throw new FileDeletionException("Directory is not empty", path);
                }
            }
        }


        try {

            Files.delete(path.toPath());

        } catch (IOException e) {

            throw new FileDeletionException("An unexpected expection occurred!", path, e);
        }


        boolean success = !path.exists();
        if (!success) {

            throw new FileDeletionException("There may be still a handle on the file or directory", path);
        }
    }

}
