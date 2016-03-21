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
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.string.StringConcatenator;


/**
 * A utility class for file operations.
 *
 * @author Kristian Kutin
 */
public final class FileHelper {

    /**
     * The default constructor.
     */
    private FileHelper() {
    }

    /**
     * Returns a file filter which identifies files with the specified file extension.
     *
     * @param fileExtension
     *        a file extension (without separator character)
     *
     * @return a file filter
     */
    public static FileFilter getFileFilter(String fileExtension) {

        return new FileExtensionFilter(fileExtension);
    }

    /**
     * Returns a file filter which identifies directories.
     *
     * @return a directory filter
     */
    public static FileFilter getDirectoryFilter() {

        return new DirectoryFilter();
    }

    /**
     * Identifies all files with the specified file extension within the specified directory.
     *
     * @param aBasedir
     *        the directory where to look for the files
     * @param aFileExtension
     *        a file extension (without separator character)
     * @param recurse
     *        <code>true</code> to search the specified directory and all its subdirectories or <code>false</code> to
     *        only search the specified directory
     *
     * @return all files with the specified file extension
     */
    public static Collection<File> getFiles(String aBasedir, String aFileExtension, boolean recurse) {

        File basedir = new File(aBasedir);
        return getFiles(basedir, aFileExtension, recurse);
    }

    /**
     * Identifies all files with the specified file extension within the specified directory.
     *
     * @param aBasedir
     *        the directory where to look for the files
     * @param aFileExtension
     *        a file extension (without separator character)
     * @param recurse
     *        <code>true</code> to search the specified directory and all its subdirectories or <code>false</code> to
     *        only search the specified directory
     *
     * @return all files with the specified file extension
     */
    public static Collection<File> getFiles(File aBasedir, String aFileExtension, boolean recurse) {

        Collection<File> fileCollection = new ArrayList<File>();
        File dir = aBasedir;

        try {

            dir = dir.getCanonicalFile();

        } catch (IOException e) {

            StringConcatenator message = new StringConcatenator("Unable to resolve the directory \"", aBasedir, "\"!");
            throw new IllegalArgumentException(message.toString(), e);
        }

        FileFilter extensionFilter = getFileFilter(aFileExtension);
        File[] fileArray = dir.listFiles(extensionFilter);

        if (fileArray == null) {

            StringConcatenator message = new StringConcatenator("The directory \"", aBasedir, "\" doesn't exist!");
            throw new IllegalArgumentException(message.toString());
        }

        int files = fileArray.length;
        for (int a = 0; a < files; a++) {
            fileCollection.add(fileArray[a]);
        }

        if (recurse) {

            FileFilter directoryFilter = getDirectoryFilter();
            File[] directoryArray = dir.listFiles(directoryFilter);

            if (directoryArray == null) {

                StringConcatenator message = new StringConcatenator("The directory \"", aBasedir, "\" doesn't exist!");
                throw new IllegalArgumentException(message.toString());
            }

            int directories = directoryArray.length;
            for (int a = 0; a < directories; a++) {

                String relativeBasedir = directoryArray[a].getAbsolutePath();
                fileCollection.addAll(getFiles(relativeBasedir, aFileExtension, recurse));
            }
        }

        return fileCollection;
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
     * exception if the deletion was not possible.<br />
     * <br />
     * <i>Note:<br />
     * The code has been taken from
     * <a href="http://forums.codeguru.com/showthread.php?42548-File-delete%28%29-not-working&p=113634#post113634">forums.codeguru.com</a>
     * and modified.</i>
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

            boolean directoryIsNotEmpty = (files.length > 0);
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
