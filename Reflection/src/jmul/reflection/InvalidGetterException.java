/*
 * This software module is part of the REMABS project.
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

package jmul.reflection;


/**
 * This exception is thrown when an error occurrs while invoking a setter
 * method.
 *
 * @author Kristian Kutin
 */
public class InvalidGetterException extends RuntimeException {

    /**
     * Constructs an exception.
     *
     * @param aMessage
     *        a message which provides details about the exception
     * @param aCause
     *        the cause of this exception
     */
    public InvalidGetterException(String aMessage, Throwable aCause) {

        super(aMessage, aCause);
    }

    /**
     * Constructs an exception
     *
     * @param aMessage
     *        a message which provides details about the exception
     */
    public InvalidGetterException(String aMessage) {

        super(aMessage);
    }

}
