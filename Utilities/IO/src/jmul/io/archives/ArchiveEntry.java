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

package jmul.io.archives;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static jmul.checks.ParameterCheckHelper.checkFileNameParameter;

import static jmul.string.Constants.FILE_SEPARATOR;
import static jmul.string.Constants.SLASH;


/**
 * This class represents an entry from within a jar archive.<br>
 * <br>
 * See <a href="http://www.javaworld.com/javaworld/javatips/jw-javatip49.html">original code example</a>
 */
public final class ArchiveEntry {

    /**
     * The archive name.
     */
    private final String archiveName;

    /**
     * Informations about this archive entry.
     */
    private final ZipEntry entry;

    /**
     * The short name of this entry. (e.g. the filename without the directory
     * path for files).
     */
    private String shortName;

    /**
     * Contains the raw data of this entry.
     */
    private byte[] data;

    /**
     * Constructs an archive entry.
     *
     * @param anArchiveName
     *        the name of this archive
     * @param anEntry
     *        informations about this archive entry
     */
    private ArchiveEntry(String anArchiveName, ZipEntry anEntry) {

        archiveName = anArchiveName;
        entry = anEntry;
        shortName = null;
        data = null;
    }

    /**
     * The method returns the name of the archive (i.e. the filename of the
     * archive).
     *
     * @return the name of this archive
     */
    public String getArchiveName() {

        return archiveName;
    }

    /**
     * The method checks if this archive entry is a directory.
     *
     * @return <code>true</code> if this entry represents a directory, else
     *         <code>false</code>
     */
    public boolean isDirectory() {

        return entry.isDirectory();
    }

    /**
     * The method checks if this archive entry is a file.
     *
     * @return <code>true</code> if this entry represents a file, else
     *         <code>false</code>
     */
    public boolean isFile() {

        return !isDirectory();
    }

    /**
     * The methode returns the name of the archive entry (i.e. the filename of
     * the archive entry).
     *
     * @return the name of this archive entry
     */
    public String getName() {

        return entry.getName();
    }

    /**
     * The method returns the short name of this archive entry.
     *
     * @return the short name of this archive entry
     */
    public String getShortName() {

        if (shortName == null) {

            String name = getName();

            // A backslash in strings is interpreted as escape character. To
            // avoid nasty side effects we simply replace it with a slash.
            if (!FILE_SEPARATOR.equals(SLASH)) {

                name = name.replace(FILE_SEPARATOR, SLASH);
            }

            String[] substrings = name.split(SLASH);

            int last = substrings.length - 1;
            shortName = substrings[last];
        }

        return shortName;
    }

    /**
     * The method returns the size of this entry (in bytes).
     *
     * @return size of this entry
     */
    public long getSize() {

        return entry.getSize();
    }

    /**
     * The method returns the raw content of this entry as byte array.
     *
     * @return the raw content of this entry as byte array
     */
    public byte[] getData() {

        boolean wasntLoaded = data == null;
        if (wasntLoaded) {

            try {

                data = loadResource(getArchiveName(), getName());

            } catch (IOException e) {

                throw new ArchiveException(e);
            }
        }

        return data;
    }

    /**
     * The method loads the contant of an archive entry from the specified
     * archive.
     *
     * @param anArchiveFilename
     *        the filename of an archive
     * @param aResourceName
     *        the filename of the archive entry
     *
     * @return the raw content of this entry as byte array
     *
     * @throws IOException
     *         is thrown if an error occurs when reading the archive.
     */
    private static byte[] loadResource(String anArchiveFilename, String aResourceName) throws IOException {

        byte[] rawData = null;

        try (ArchiveReader reader = new ArchiveReaderImpl(anArchiveFilename)) {

            rawData = reader.loadEntry(aResourceName);

        } catch (FileNotFoundException e) {

            String message = "The specified archive \"" + anArchiveFilename + "\" doesn't exist!";
            throw new ArchiveException(message, e);
        }

        return rawData;
    }

    /**
     * The method scans an archive and returns a directory.
     *
     * @param anArchiveName
     *        the filename of an archive
     *
     * @return a directory of all archive entries
     */
    public static Collection<ArchiveEntry> scanArchive(String anArchiveName) {

        checkFileNameParameter(anArchiveName);

        ZipFile zipFile;

        try {

            zipFile = new ZipFile(anArchiveName);

        } catch (IOException e) {

            String message = "Unable to open the specified archive (" + anArchiveName + ")!";
            throw new IllegalArgumentException(message);
        }

        Collection<ArchiveEntry> archiveEntries = new ArrayList<>();
        Enumeration enumeration = zipFile.entries();

        while (enumeration.hasMoreElements()) {

            ZipEntry zipEntry = (ZipEntry) enumeration.nextElement();
            archiveEntries.add(new ArchiveEntry(anArchiveName, zipEntry));
        }

        try {

            zipFile.close();

        } catch (IOException e) {

            String message = "Unable to close the specified archive (" + anArchiveName + ") properly!";
            throw new IllegalArgumentException(message, e);
        }

        return archiveEntries;
    }

}
