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
 * This class implements an error status. This is a utility class for cases
 * when an exception is thrown, but the exception cannot be handled immediately.
 *
 * @author Kristian Kutin
 */
public final class ErrorStatus {

    /**
     * The class member contains the cause for the error.
     */
    private Exception cause;

    /**
     * The default constructor.
     */
    public ErrorStatus() {

        cause = null;
    }

    /**
     * The method determines if an error has occurred.
     *
     * @return true, if an error has occurred, else false
     */
    public boolean hasOccurredError() {

        return (cause != null);
    }

    /**
     * The method returns the cause for the error.
     *
     * @return the cause for an error or <code>null</code>
     */
    public Exception getCause() {

        return cause;
    }

    /**
     * The method changes the error status.
     *
     * @param aCause
     * a cause for an error
     */
    public void changeErrorStatus(Exception aCause) {

        cause = aCause;
    }

}
