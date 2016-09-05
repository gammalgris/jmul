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

package jmul.misc.error;

import java.util.Date;


/**
 * This interface describes an entity which keeps track of the current
 * error status.
 *
 * @author Kristian Kutin
 */
public interface ErrorStatus {

    /**
     * The method determines if an error has occurred.
     *
     * @return <code>true</code>, if an error has occurred, else
     *         <code>false</code>
     */
    boolean hasOccurredError();

    /**
     * Returns the cause of the error (i.e. the actual exception).
     *
     * @return the cause for an error or <code>null</code> if no error
     *         has occurred
     */
    Throwable getError();

    /**
     * The date when the error occurred.
     *
     * @return a date or <code>null</code> if no error has occurred
     */
    Date getDate();

    /**
     * Reports an error and changes the error status accordingly.
     *
     * @param anError
     */
    void reportError(Throwable anError);

}
