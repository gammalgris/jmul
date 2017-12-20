/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static jmul.misc.checks.ParameterCheckHelper.checkFileNameParameter;
import static jmul.misc.checks.ParameterCheckHelper.checkFileParameter;
import jmul.misc.exceptions.MultipleCausesException;


/**
 * An implementation of a file copier.
 *
 * @author Kristian Kutin
 *
 * @deprecated see {@link java.nio.file.Files#copy(InputStream, OutputStream)
 */
@Deprecated
public class FileCopierImpl implements FileCopier {

    /**
     * Represents the end of a file.
     */
    private static final int END_OF_FILE = -1;

    /**
     * A buffer size.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    /**
     * The name of a stream.
     */
    private static final String IN = "in";

    /**
     * The name of a stream.
     */
    private static final String OUT = "out";

    /**
     * Creates a new file copier.
     */
    public FileCopierImpl() {

        super();
    }

    /**
     * Copies the specified source file to the specified destination file.
     *
     * @param aSourceFileName
     *        a file name (i.e. file path)
     * @param aDestinationFileName
     *        a file name (i.e. file path)
     *
     * @throws CopyFileException
     *         The exception is thrown if an error occurs during the process
     *         of copying the file
     */
    @Override
    public void copyFile(String aSourceFileName, String aDestinationFileName) throws CopyFileException {

        checkFileNameParameter(aSourceFileName);
        checkFileNameParameter(aDestinationFileName);

        copyFile(new File(aSourceFileName), new File(aDestinationFileName));
    }

    /**
     * Copies the specified source file to the specified destination file.
     *
     * @param aSourceFile
     *        a file (i.e. file path)
     * @param aDestinationFile
     *        a file (i.e. file path)
     *
     * @throws CopyFileException
     *         The exception is thrown if an error occurs during the process
     *         of copying the file
     */
    @Override
    public void copyFile(File aSourceFile, File aDestinationFile) throws CopyFileException {

        checkFileParameter(aSourceFile);
        checkFileParameter(aDestinationFile);

        CoupledStreams coupledStreams = createStreams(aSourceFile, aDestinationFile);

        InputStream in = (InputStream) coupledStreams.getStream(IN);
        OutputStream out = (OutputStream) coupledStreams.getStream(OUT);


        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        boolean endOfFile = false;
        Throwable exception = null;
        String faultyStreamName = null;

        while (!endOfFile) {

            int bytesRead = 0;

            try {

                bytesRead = in.read(buffer);

            } catch (IOException e) {

                String message = "Unable to read from source file " + aSourceFile + "!";
                exception = new CopyFileException(message, e);
                faultyStreamName = IN;
                break;
            }

            endOfFile = bytesRead == END_OF_FILE;
            if (endOfFile) {

                break;
            }

            try {

                out.write(buffer, 0, bytesRead);
                out.flush();

            } catch (IOException e) {

                String message = "Unable to write to destination file " + aDestinationFile + "!";
                exception = new CopyFileException(message, e);
                faultyStreamName = OUT;
                break;
            }
        }


        if (exception != null) {

            try {

                coupledStreams.closeOnError(faultyStreamName);

            } catch (IOException e) {

                String message =
                    "An error occurred while closing streams (" + aSourceFile + " -> " + aDestinationFile + ")!";
                throw new CopyFileException(message, new MultipleCausesException(exception, e));
            }

        } else {

            try {

                coupledStreams.close();

            } catch (IOException e) {

                String message =
                    "An error occurred while closing streams (" + aSourceFile + " -> " + aDestinationFile + ")!";
                throw new CopyFileException(message, e);
            }
        }
    }

    /**
     * Opens an input and an output stream which are required for copying the file.
     *
     * @param aSourceFile
     *        a file (i.e. file path)
     * @param aDestinationFile
     *        a file (i.e. file path)
     *
     * @return an input and output stream
     *
     * @throws CopyFileException
     */
    private static CoupledStreams createStreams(File aSourceFile, File aDestinationFile) throws CopyFileException {

        InputStream in = null;


        try {

            in = new FileInputStream(aSourceFile);

        } catch (FileNotFoundException e) {

            String message = "The specified source file " + aSourceFile + " doesn't exist!";
            throw new CopyFileException(message, e);
        }


        OutputStream out = null;
        Throwable failure = null;

        try {

            out = new FileOutputStream(aDestinationFile);

        } catch (FileNotFoundException e) {

            String message = "Unable to create the destination file " + aDestinationFile + "!";
            failure = new CopyFileException(message, e);
        }


        if (failure != null) {

            try {

                in.close();

            } catch (IOException e) {

                String message = "Unable to close streams after an error!";

                if (failure == null) {

                    throw new CopyFileException(message, e);

                } else {

                    throw new CopyFileException(message, new MultipleCausesException(failure, e));
                }
            }
        }


        return new CoupledStreamsImpl(new StreamEntry(IN, in), new StreamEntry(OUT, out));
    }

}
