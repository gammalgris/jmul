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


/**
 * The interfacce describes an entity that copies files.
 *
 * @author Kristian Kutin
 */
public interface FileCopier {

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
    void copyFile(String aSourceFileName, String aDestinationFileName) throws CopyFileException;

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
    void copyFile(File aSourceFile, File aDestinationFile) throws CopyFileException;

}
