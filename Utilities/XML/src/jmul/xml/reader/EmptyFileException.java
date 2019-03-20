/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package jmul.xml.reader;


import java.io.File;

/**
 * This exception is thrown if a document reader tries to parse an empty file.
 *
 * @author Kristian Kutin
 */
public class EmptyFileException extends RuntimeException {

    /**
     * The serial UID as required by java's serialization mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new eception.
     *
     * @param aFile
     *        the parsed xml file (i.e. file path)
     */
    public EmptyFileException(File aFile) {

        super(createErrorMessage(aFile));
    }

    /**
     * An error message is created with the specified parameters.
     *
     * @param aFile
     *        the parsed xml file (i.e. file path)
     *
     * @return an error message
     */
    private static String createErrorMessage(File aFile) {

        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("The file \"");
        errorMessage.append(aFile.getAbsolutePath());
        errorMessage.append("\" is empty!");

        return String.valueOf(errorMessage);
    }

}
