/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.io.archives;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static jmul.io.Constants.END_OF_FILE;

import static jmul.string.Constants.BACKSLASH;
import static jmul.string.Constants.SLASH;


/**
 * This utility class contains operation regarding archive files.
 *
 * @author Kristian Kutin
 */
public final class ArchiveHelper {

    /**
     * A buffer size.
     */
    private static final int DEFAULT_BUFFER_SIZE = 256;

    /**
     * The default constructor.
     */
    private ArchiveHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates an archive which includes all specified files.
     *
     * @param anArchiveName
     *        the file name of the archive (i.e. file path)
     * @param aBaseDirectory
     *        the common base directory of all files which are to be included (i.e. directory path)
     * @param someFileNames
     *        the file names of all included files (i.e. file paths)
     *
     * @throws IOException
     *         is thrown if an error occurs while creating the archive
     */
    public static void createArchive(String anArchiveName, String aBaseDirectory,
                                     String... someFileNames) throws IOException {

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(anArchiveName))) {

            for (String fileName : someFileNames) {

                String relativeFilePath = getRelativeFilePath(aBaseDirectory, fileName);
                try (FileInputStream fis = new FileInputStream(fileName)) {

                    addFile(zos, relativeFilePath, fis);
                }
            }
        }
    }

    /**
     * Determines the relative file path according to the specified base directory.
     *
     * @param aBaseDirectory
     *        a base directory (i.e. directory path)
     * @param aFileName
     *        a file name (i.e. file path)
     *
     * @return a relative file path
     *
     * @throws IOException
     *         is thrown if an error occurs while resolving file or directory paths
     */
    private static String getRelativeFilePath(String aBaseDirectory, String aFileName) throws IOException {

        File directory = new File(aBaseDirectory);
        File file = new File(aFileName);

        String resolvedDirectoryPath = directory.getCanonicalPath();
        String resolvedFilePath = file.getCanonicalPath();

        String result = resolvedFilePath.replace(resolvedDirectoryPath, "");

        if (result.startsWith(SLASH)) {

            result = result.substring(1);
        }

        if (result.startsWith(BACKSLASH)) {

            result = result.substring(1);
        }

        return result;
    }

    /**
     * Adds the specified file to the archive.
     *
     * @param aZipOutputStream
     *        a handle on the archive
     * @param aFileStreamReference
     *        the reference name for the file stream
     * @param aFileInputStream
     *        a handle on the file
     *
     * @throws IOException
     *         is thrown if an error occurs while adding the file to the archive
     */
    private static void addFile(ZipOutputStream aZipOutputStream, String aFileStreamReference,
                                FileInputStream aFileInputStream) throws IOException {

        ZipEntry entry = new ZipEntry(aFileStreamReference);
        aZipOutputStream.putNextEntry(entry);


        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        while (true) {

            int byteCount = END_OF_FILE;
            byteCount = aFileInputStream.read(buffer);

            if (byteCount == END_OF_FILE) {

                break;
            }

            aZipOutputStream.write(buffer, 0, byteCount);
        }
    }

}
