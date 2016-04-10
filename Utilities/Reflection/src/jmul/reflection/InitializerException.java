/*
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

package jmul.reflection;


/**
 * This exception is thrown if a object cannot be initialized by exception
 * mechanisms.
 *
 * @author Kristian Kutin
 */
public class InitializerException extends RuntimeException {

    /**
     * Constructs a new exception.
     *
     * @param aMessage
     *        a message which provides additional details about this exception
     * @param aCause
     *        the actual cause for this exception
     */
    public InitializerException(String aMessage, Exception aCause) {

        super(aMessage, aCause);
    }

}
