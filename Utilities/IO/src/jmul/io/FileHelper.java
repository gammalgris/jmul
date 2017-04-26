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
import java.io.FileFilter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import jmul.io.filters.DirectoryFilter;
import jmul.io.filters.FileExtensionFilter;

import jmul.string.StringConcatenator;


/**
 * A utility class for file operations.
 */
public final class FileHelper {

    /**
     * A singleton which takes care of copying files.
     */
    private static FileCopier fileCopierSingleton = new FileCopierImpl();

    /**
     * The default constructor.
     */
    private FileHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * The method returns a reference to a FileFilter.
     *
     * @param fileExtension
     *        a file extension
     *
     * @return a reference to a FileFilter
     */
    public static FileFilter getFileFilter(String fileExtension) {

        return new FileExtensionFilter(fileExtension);
    }

    /**
     * The method returns a reference to a FileFilter
     *
     * @return a reference to a file filter
     */
    public static FileFilter getDirectoryFilter() {

        return new DirectoryFilter();
    }

    /**
     * The method getFiles determines all files with a given file extension.
     *
     * @param aBasedir
     *        the directory where to look for the files
     * @param aFileExtension
     *        a file extension
     * @param recurse
     *        a flag whether or not to recurse into subdiredctories
     *
     * @return a collection of files
     */
    public static Collection<File> getFiles(String aBasedir, String aFileExtension, boolean recurse) {

        File basedir = new File(aBasedir);
        return getFiles(basedir, aFileExtension, recurse);
    }

    /**
     * The method getFiles determines all files with a given file extension.
     *
     * @param aBasedir
     *        the directory where to look for the files
     * @param aFileExtension
     *        a file extension
     * @param recurse
     *        a flag whether or not to recurse into subdiredctories
     *
     * @return a collection of files
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

        fileCollection.addAll(Arrays.asList(fileArray));

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
     * The method recursively deletes the specified file or directory.
     *
     * TODO
     * Consolidate with FileDeletionHelper.
     *
     * @param aFile
     *        a file or directory
     *
     * @deprecated
     */
    @Deprecated
    public static void delete(File aFile) {

        if (!aFile.exists()) {

            StringConcatenator message =
                new StringConcatenator("The specified file or directory doesn't exist (", aFile, "!");
            throw new IllegalArgumentException(message.toString());
        }

        if (aFile.isDirectory()) {

            for (File f : aFile.listFiles()) {

                delete(f);
            }
        }

        if (!aFile.delete()) {

            throw new FileDeletionException("Failed to delete file!", aFile);
        }
    }

    /**
     * Copies the specified source file to the specified destiantion file.
     *
     * @param aSourceFileName
     * @param aDestinationFileName
     *
     * @throws CopyFileException
     *         is thrown when an exception occurs during copying
     */
    public static void copyFile(String aSourceFileName, String aDestinationFileName) throws CopyFileException {

        fileCopierSingleton.copyFile(aSourceFileName, aDestinationFileName);
    }

    /**
     * Copies the specified source file to the specified destiantion file.
     *
     * @param aSourceFile
     * @param aDestinationFile
     *
     * @throws CopyFileException
     *         is thrown when an exception occurs during copying
     */
    public static void copyFile(File aSourceFile, File aDestinationFile) throws CopyFileException {

        fileCopierSingleton.copyFile(aSourceFile, aDestinationFile);
    }

    /**
     * Loads the content of the specified file and returns it as string.
     *
     * @param aFileName
     *
     * @return a string containing a file content
     *
     * @throws IOException
     *         is thrown if an error occurrs while reading from a file
     */
    public static String loadFileContentAsString(String aFileName) throws IOException {

        byte[] content = Files.readAllBytes(Paths.get(aFileName));
        return new String(content);
    }

}
