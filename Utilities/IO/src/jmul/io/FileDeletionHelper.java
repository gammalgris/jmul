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

package jmul.io;


import java.io.File;


/**
 * This class provides a convenience method for deleting files. The code has
 * been taken from
 * <a href="http://forums.codeguru.com/showthread.php?42548-File-delete%28%29-not-working&p=113634#post113634">forums.codeguru.com</a>
 * and modified.
 */
public final class FileDeletionHelper {

    /**
     * The default constructor.
     */
    private FileDeletionHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Delete the specified file. The method checks certain plausibilites (e.g.
     * file is write protected, file handles exist, etc.) and throws an
     * exception if the deletion was not possible.
     *
     * @param filename
     */
    public static void delete(String filename) {

        File f = new File(filename);

        delete(f);
    }

    /**
     * Delete the specified file. The method checks certain plausibilites (e.g.
     * file is write protected, file handles exist, etc.) and throws an
     * exception if the deletion was not possible.
     *
     * @param file
     */
    public static void delete(File file) {

        boolean notExists = !file.exists();
        if (notExists) {

            throw new FileDeletionException("No such file or directory", file);
        }

        boolean isWriteProtected = !file.canWrite();
        if (isWriteProtected) {

            throw new FileDeletionException("File is write protected", file);
        }

        boolean isDirectory = file.isDirectory();
        if (isDirectory) {

            String[] files = file.list();

            boolean directoryIsNotEmpty = files.length > 0;
            if (directoryIsNotEmpty) {

                throw new FileDeletionException("Directory is not empty", file);
            }
        }


        boolean success = file.delete();

        if (!success) {

            throw new FileDeletionException("There may be still a handle on the file or directory", file);
        }
    }

}
