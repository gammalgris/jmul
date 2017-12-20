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

package jmul.io.archives;


import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static jmul.io.Constants.END_OF_FILE;
import jmul.io.CoupledStreams;
import jmul.io.CoupledStreamsImpl;
import jmul.io.NestedStreams;
import jmul.io.NestedStreamsImpl;
import jmul.io.StreamEntry;

import jmul.misc.exceptions.MultipleCausesException;

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

        NestedStreams out = createArchive(anArchiveName);

        StreamEntry archiveEntry = new StreamEntry(anArchiveName, out);

        for (String fileName : someFileNames) {

            String relativeFilePath = null;
            try {

                relativeFilePath = getRelativeFilePath(aBaseDirectory, fileName);

            } catch (IOException e) {

                closeArchiveOnError(e, out);
            }


            StreamEntry fileEntry = null;
            try {

                fileEntry = openFile(relativeFilePath, fileName);

            } catch (IOException e) {

                closeArchiveOnError(e, out);
            }


            CoupledStreams streams = new CoupledStreamsImpl(archiveEntry, fileEntry);


            try {

                addFile(anArchiveName, relativeFilePath, streams);
                closeFile(fileEntry);

            } catch (StreamWrapperException e) {

                closeStreamsOnError(e.getStreamName(), streams, e);
            }
        }


        try {

            closeArchive(out);

        } catch (IOException e) {

            closeArchiveOnError(e, out);
        }
    }

    /**
     * Creates a new archive.
     *
     * @param anArchiveName
     *        the file name of the archive (i.e. file path)
     *
     * @return an output stream
     *
     * @throws FileNotFoundException
     *         is thrown if the archive cannot be created
     */
    private static NestedStreams createArchive(String anArchiveName) throws FileNotFoundException {

        FileOutputStream fos = new FileOutputStream(anArchiveName);
        ZipOutputStream zos = new ZipOutputStream(fos);

        return new NestedStreamsImpl(zos, fos);
    }

    /**
     * Closes the archive file.
     *
     * @param anArchiveStream
     *        the archive stream
     *
     * @throws IOException
     *         is thrown if an error occurs while closing the stream
     */
    private static void closeArchive(Closeable anArchiveStream) throws IOException {

        anArchiveStream.close();
    }

    /**
     * Closes the archive on an error.
     *
     * @param aCause
     *        the error which causes the stream to be closed
     * @param anArchiveStream
     *        the archive stream
     *
     * @throws IOException
     *         is always thrown on invocation
     */
    private static void closeArchiveOnError(IOException aCause, Closeable anArchiveStream) throws IOException {

        IOException followupException = null;

        try {

            anArchiveStream.close();

        } catch (IOException e) {

            followupException = e;
        }


        if (followupException == null) {

            throw aCause;

        } else {

            throw new IOException(aCause.getMessage(), new MultipleCausesException(aCause, followupException));
        }
    }

    /**
     * Closes all streams after an error occurred.
     *
     * @param aStreamName
     *        the name of the streasm that caused the error
     * @param someCoupledStreams
     *        a container with all streams
     * @param aCause
     *        the actual cause
     *
     * @throws IOException
     *         is always thrown on invocation
     */
    private static void closeStreamsOnError(String aStreamName, CoupledStreams someCoupledStreams,
                                            IOException aCause) throws IOException {

        IOException followupException = null;

        try {

            someCoupledStreams.closeOnError(aStreamName);

        } catch (IOException e) {

            followupException = e;
        }


        if (followupException == null) {

            throw aCause;

        } else {

            throw new IOException(aCause.getMessage(), new MultipleCausesException(aCause, followupException));
        }
    }

    /**
     * Opens a file for reading its content.
     *
     * @param anEntryName
     *        the name of an entry
     * @param aFileName
     *        the file name (i.e. file path)
     *
     * @return a wrapper for a file stream
     *
     * @throws FileNotFoundException
     *         is thrown if the specified file doesn't exist
     */
    private static StreamEntry openFile(String anEntryName, String aFileName) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(aFileName);
        return new StreamEntry(anEntryName, fis);
    }

    /**
     * Closes the file after reading is done.
     *
     * @param aFileStream
     *        a wrapper for a file stream
     *
     * @throws IOException
     *         is thrown if an error occurs while closing the stream
     */
    private static void closeFile(StreamEntry aFileStream) throws IOException {

        aFileStream.getStream().close();
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
     * @param anArchiveStreamReference
     *        the reference name for the archive stream
     * @param aFileStreamReference
     *        the reference name for the file stream
     * @param someCoupledStreams
     *        a container with all streams
     *
     * @throws StreamWrapperException
     *         is thrown if an error occurs while adding the file to the archive
     */
    private static void addFile(String anArchiveStreamReference, String aFileStreamReference,
                                CoupledStreams someCoupledStreams) throws StreamWrapperException {

        NestedStreams outWrapper = (NestedStreams) someCoupledStreams.getStream(anArchiveStreamReference);
        ZipOutputStream out = (ZipOutputStream) outWrapper.getOuterStream();

        FileInputStream in = (FileInputStream) someCoupledStreams.getStream(aFileStreamReference);

        ZipEntry entry = new ZipEntry(aFileStreamReference);

        try {

            out.putNextEntry(entry);

        } catch (IOException e) {

            handleIOException(anArchiveStreamReference, e);
        }


        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        while (true) {

            int byteCount = END_OF_FILE;
            try {

                byteCount = in.read(buffer);

            } catch (IOException e) {

                handleIOException(aFileStreamReference, e);
            }


            if (byteCount == END_OF_FILE) {

                break;
            }

            try {

                out.write(buffer, 0, byteCount);

            } catch (IOException e) {

                handleIOException(anArchiveStreamReference, e);
            }
        }

        try {

            out.closeEntry();

        } catch (IOException e) {

            handleIOException(anArchiveStreamReference, e);
        }
    }

    /**
     * Raises an exception and preserves informations about the stream that caused
     * the exception.
     *
     * @param aStreamName
     *        a stream name
     * @param aCause
     *        the actual cause
     *
     * @throws StreamWrapperException
     *         is always thrown on invocation
     */
    private static void handleIOException(String aStreamName, Throwable aCause) throws StreamWrapperException {

        throw new StreamWrapperException(aStreamName, aCause);
    }

}


/**
 * A custom exception that transports a stream name.
 *
 * @author Kristian Kutin
 */
class StreamWrapperException extends IOException {

    /**
     * A stream name.
     */
    private final String streamName;

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aStreamName
     *        the stream name
     * @param aCause
     *        the actual error
     */
    public StreamWrapperException(String aStreamName, Throwable aCause) {

        super(aCause);

        streamName = aStreamName;
    }

    /**
     * Returns a stream name which was the cause of the exception.
     *
     * @return a stream name
     */
    public String getStreamName() {

        return streamName;
    }

}
