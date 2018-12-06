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

package jmul.io;


import java.io.File;

import static jmul.checks.ParameterCheckHelper.checkExceptionMessage;
import static jmul.checks.ParameterCheckHelper.checkFileParameter;


/**
 * This exception is thrown if the deletion of a file or directory has failed.
 *
 * @author Kristian Kutin
 */
public class FileDeletionException extends RuntimeException {

    /**
     * Creates an exception.
     *
     * @param message
     *        the error message
     * @param file
     *        the file which couldn't be deleted
     */
    public FileDeletionException(String message, File file) {

        super(createMessage(message, file));
    }

    /**
     * Creates an exception.
     *
     * @param message
     *        the error message
     * @param file
     *        the file which couldn't be deleted
     * @param cause
     *        the cause of this exception
     */
    public FileDeletionException(String message, File file, Throwable cause) {

        super(createMessage(message, file), cause);
    }

    /**
     * Creates an exception message according to the specified paremeters.
     *
     * @param message
     *        the error message
     * @param file
     *        the file which couldn't be deleted
     *
     * @return an exception message
     */
    private static String createMessage(String message, File file) {

        checkExceptionMessage(message);
        checkFileParameter(file);

        return message + " : " + file.getAbsolutePath();
    }

}
