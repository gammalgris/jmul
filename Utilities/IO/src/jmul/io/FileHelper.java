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

package jmul.io;


import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import jmul.checks.ParameterCheckHelper;

import jmul.io.filters.DirectoryFilter;
import jmul.io.filters.FileExtensionFilter;

import jmul.string.TextHelper;


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

        Collection<File> fileCollection = new ArrayList<>();
        File dir = aBasedir;

        try {

            dir = dir.getCanonicalFile();

        } catch (IOException e) {

            String message = TextHelper.concatenateStrings("Unable to resolve the directory \"", aBasedir, "\"!");
            throw new IllegalArgumentException(message, e);
        }

        FileFilter extensionFilter = getFileFilter(aFileExtension);
        File[] fileArray = dir.listFiles(extensionFilter);

        if (fileArray == null) {

            String message = TextHelper.concatenateStrings("The directory \"", aBasedir, "\" doesn't exist!");
            throw new IllegalArgumentException(message);
        }

        fileCollection.addAll(Arrays.asList(fileArray));

        if (recurse) {

            FileFilter directoryFilter = getDirectoryFilter();
            File[] directoryArray = dir.listFiles(directoryFilter);

            if (directoryArray == null) {

                String message = TextHelper.concatenateStrings("The directory \"", aBasedir, "\" doesn't exist!");
                throw new IllegalArgumentException(message);
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
     * Copies the specified source file to the specified destiantion file.
     *
     * @param aSourceFileName
     *        the name of a file (i.e. file path)
     * @param aDestinationFileName
     *        the name of a file (i.e. file path)
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
     *        the name of a file (i.e. file path)
     * @param aDestinationFile
     *        the name of a file (i.e. file path)
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
     *        the name of a file (i.e. file path)
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

    /**
     * Checks if the specified file exists.
     *
     * @param aFileName
     *        a file name (i.e. file path)
     *
     * @return <code>true</code> if the file exists, else <code>false</code>
     */
    public static boolean existsFile(String aFileName) {

        ParameterCheckHelper.checkPathParameter(aFileName);

        return existsFile(new File(aFileName));
    }

    /**
     * Checks if the specified file exists.
     *
     * @param aFile
     *        a file object
     *
     * @return <code>true</code> if the file exists, else <code>false</code>
     */
    public static boolean existsFile(File aFile) {

        ParameterCheckHelper.checkPathParameter(aFile);

        if (aFile.isDirectory()) {

            return false;
        }


        try (FileInputStream fis = new FileInputStream(aFile)) {

            fis.read();

        } catch (FileNotFoundException e) {

            // This method checks the existence of a file. If trying to open
            // an input stream fails with this exception then the file doesn't exist.
            // Preserving this exception is not needed nor possible as the method
            // will only return a boolean value.

            return false;

        } catch (IOException e) {

            // If reading from the file is not possible then the file is
            // considered as not existing.
            // Preserving this exception is not needed nor possible as the method
            // will only return a boolean value.

            return false;
        }

        return true;
    }

}
