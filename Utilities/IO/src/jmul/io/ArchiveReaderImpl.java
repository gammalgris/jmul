/*
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


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * An implementation of an archive reader.
 *
 * @author Kristian Kutin
 */
public class ArchiveReaderImpl implements ArchiveReader {

    /**
     * Represents the end of an entry (similar to end of file).
     */
    private static final int END_OF_ENTRY = -1;

    /**
     * The name of an archive.
     */
    private final String archiveName;

    /**
     * An entity that manages nested streams.
     */
    private final NestedStreams nestedStreams;

    /**
     * Opens the specified archive.
     *
     * @param anArchiveName
     *
     * @throws FileNotFoundException
     *         The exception is thrown if the specified archive doesn't exist.
     */
    public ArchiveReaderImpl(String anArchiveName) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(anArchiveName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ZipInputStream zis = new ZipInputStream(bis);

        nestedStreams = new NestedStreamsImpl(zis, bis, fis);
        archiveName = anArchiveName;
    }

    /**
     * Loads the specified entry from the archive.
     *
     * @param anEntryName
     *
     * @return the raw data representing the specified entry or <code>null</code>
     *         if no such entry exists
     *
     * @throws ArchiveException
     *         The exception is thrown if an error occurs while reading from the
     *         specified archive.
     */
    public byte[] loadEntry(String anEntryName) throws ArchiveException {

        ZipInputStream zis = (ZipInputStream) nestedStreams.getOuterStream();

        byte[] rawData = null;


        boolean foundResource = false;
        boolean endOfArchive = false;
        Exception exception = null;

        while (!endOfArchive) {

            ZipEntry zipEntry = null;

            try {

                zipEntry = zis.getNextEntry();

            } catch (IOException e) {

                exception = e;
                break;
            }

            endOfArchive = (zipEntry == null);

            if (endOfArchive) {

                break;
            }

            if (zipEntry.isDirectory()) {

                continue;
            }


            String foundName = zipEntry.getName();
            long size = zipEntry.getSize();

            foundResource = (anEntryName.equals(foundName));
            boolean hasUnknownSize = (size == -1);


            if (!foundResource) {

                continue;
            }


            try {

                if (hasUnknownSize) {

                    return rawData = loadResource(zis);

                } else {

                    return rawData = loadResource(zis, size);
                }

            } catch (IOException e) {

                exception = e;
                break;
            }
        }


        String message = "Unknown resource: " + anEntryName + "!";
        throw new IllegalArgumentException(message, exception);
    }

    /**
     * The resource has been found within the archive. Now its byte content has to be
     * extracted, but the actual size is unknown.
     *
     * @param zis
     *
     * @return a byte array
     *
     * @throws IOException
     *         This exception is thrown if an error occurs while loading the next
     *         bytes of the resource.
     */
    private static byte[] loadResource(ZipInputStream zis) throws IOException {

        List<Byte> buffer = new ArrayList<Byte>();

        boolean doRead = true;
        while (doRead) {

            int readByte = zis.read();

            if (readByte == END_OF_ENTRY) {

                doRead = false;
                break;
            }

            buffer.add((byte) readByte);
        }


        // Create a byte array

        int bufferSize = buffer.size();
        byte[] byteArray = new byte[bufferSize];

        for (int a = 0; a < bufferSize; a++) {

            byte b = buffer.get(a);
            byteArray[a] = b;
        }

        return byteArray;
    }

    /**
     * The resource has been found within the archive. Now its byte content has to be
     * extracted.
     *
     * @param zis
     * @param size
     *
     * @return a byte array
     *
     * @throws IOException
     */
    private static byte[] loadResource(ZipInputStream zis, long size) throws IOException {

        byte[] b = new byte[(int) size];
        int rb = 0;
        int chunk = 0;

        while (((int) size - rb) > 0) {
            chunk = zis.read(b, rb, (int) size - rb);
            if (chunk == -1) {
                break;
            }
            rb += chunk;
        }

        return b;
    }

    /**
     * Closes the specified archive.
     *
     * @throws ArchiveException
     *         The exception is thrown if an error occurs while closing the
     *         specified archive.
     */
    public void closeArchive() throws ArchiveException {

        try {

            nestedStreams.close();

        } catch (IOException e) {

            String message = "The archive \"" + archiveName + "\" couldn't be closed!";
            throw new ArchiveException(message, e);
        }
    }

}
