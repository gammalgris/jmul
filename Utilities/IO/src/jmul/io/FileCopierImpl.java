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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.io;


import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static jmul.checks.ParameterCheckHelper.checkFileNameParameter;
import static jmul.checks.ParameterCheckHelper.checkFileParameter;


/**
 * An implementation of a file copier (see {@link jmul.io.FileCopier}).<br>
 * <br>
 * <i>Note:<br>
 * This file copy implementation relies on {@link java.nio.file.Files}.</i>
 *
 * @author Kristian Kutin
 */
public class FileCopierImpl implements FileCopier {

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

        if (aSourceFile.isDirectory()) {

            String message = "The specified source file is a directory (" + aSourceFile + ")!";
            throw new CopyFileException(message);
        }

        if (aSourceFile.getName()
                       .trim()
                       .isEmpty()) {

            String message = "The specified source file is invalid (" + aDestinationFile + ")!";
            throw new CopyFileException(message);
        }

        if (aDestinationFile.isDirectory()) {

            String message = "The specified destination file is a directory (" + aDestinationFile + ")!";
            throw new CopyFileException(message);
        }

        if (aDestinationFile.getName()
                            .trim()
                            .isEmpty()) {

            String message = "The specified destination file is invalid (" + aDestinationFile + ")!";
            throw new CopyFileException(message);
        }


        try {

            Files.copy(aSourceFile.toPath(), aDestinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING,
                       StandardCopyOption.COPY_ATTRIBUTES);

        } catch (IOException e) {

            String message = "Unable to copy file \"" + aSourceFile + "\" to \"" + aDestinationFile + "\"!";
            throw new CopyFileException(message, e);
        }
    }

}
