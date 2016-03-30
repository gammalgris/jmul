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


import java.io.IOException;


/**
 * This exception is thrown if closing nested streams was not successful.
 *
 * @author Kristian Kutin
 */
public class NestedStreamsException extends IOException {

    /**
     * Creates a new exception.
     */
    public NestedStreamsException() {

        super();
    }

    /**
     * Creates a new exception.
     *
     * @param aMessage
     */
    public NestedStreamsException(String aMessage) {

        super(aMessage);
    }

    /**
     * Creates a new exception.
     *
     * @param aCause
     */
    public NestedStreamsException(Throwable aCause) {

        super(aCause);
    }

    /**
     * Creates a new exception.
     *
     * @param aMessage
     * @param aCause
     */
    public NestedStreamsException(String aMessage, Throwable aCause) {

        super(aMessage, aCause);
    }

}
