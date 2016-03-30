/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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


/**
 * This exception is thrown if the deletion of a file or directory has failed.
 *
 * @author Kristian Kutin
 */
public class FileDeletionException extends RuntimeException {

    /**
     * Constructs an exception.
     *
     * @param aCause
     *        the cause for this exception
     */
    public FileDeletionException(Throwable aCause) {

        super(aCause);
    }

    /**
     * Constructs an exception.
     *
     * @param aMessage
     *        an exception message
     * @param aCause
     *        the cause for this exception
     */
    public FileDeletionException(String aMessage, Throwable aCause) {

        super(aMessage, aCause);
    }

    /**
     * Constructs an exception.
     *
     * @param aMessage
     *        an exception message
     */
    public FileDeletionException(String aMessage) {

        super(aMessage);
    }

    /**
     * The default constructor.
     */
    public FileDeletionException() {

        super();
    }

    /**
     * Creates an exception.
     *
     * @param message
     * @param file
     */
    public FileDeletionException(String message, File file) {

        super(message + " : " + file.getAbsolutePath());
    }

}
