/*
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

package jmul.misc.error;


import java.util.Date;


/**
 * An implementation of an error status.<br />
 * <br />
 * <i>Note:<br />
 * This implementation will only remember the latest error
 * that has occurred.</i>
 *
 * @author Kristian Kutin
 */
public class SingleErrorStatus implements ErrorStatus {

    /**
     * The actual error.
     */
    private Throwable error;

    /**
     * The date when the error occurred (i.e. was reported).
     */
    private Date date;

    /**
     * The default constructor.
     */
    public SingleErrorStatus() {

        error = null;
    }

    /**
     * The method determines if an error has occurred.
     *
     * @return <code>true</code>, if an error has occurred, else
     *         <code>false</code>
     */
    @Override
    public boolean hasOccurredError() {

        return error != null;
    }

    /**
     * Returns the cause of the error (i.e. the actual exception).
     *
     * @return the cause for an error or <code>null</code> if no error
     *         has occurred
     */
    @Override
    public Throwable getError() {

        return error;
    }

    /**
     * The date when the error occurred.
     *
     * @return a date or <code>null</code> if no error has occurred
     */
    @Override
    public Date getDate() {

        return date;
    }

    /**
     * Reports an error and changes the error status accordingly.
     *
     * @param anError
     */
    @Override
    public void reportError(Throwable anError) {

        date = new Date();
        error = anError;
    }

    /**
     * Returns a string representation of the current error status.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuffer message = new StringBuffer();

        if (hasOccurredError()) {

            message.append(getError().getClass().getSimpleName());
            message.append("\t");
            message.append(date);

        } else {

            message.append("no error");
        }

        return message.toString();
    }

}
