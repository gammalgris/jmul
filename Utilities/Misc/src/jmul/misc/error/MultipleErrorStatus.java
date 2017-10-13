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
import java.util.SortedMap;
import java.util.TreeMap;

import jmul.misc.exceptions.MultipleCausesException;


/**
 * An implementation of an error status.<br />
 * <br />
 * <i>Note:<br />
 * This implementation will only remember all errors
 * that have occurred.</i>
 *
 * @author Kristian Kutin
 */
public class MultipleErrorStatus implements ErrorStatus {

    /**
     * The actual errors.
     */
    private SortedMap<Date, Throwable> errors;

    /**
     * The default constructor.
     */
    public MultipleErrorStatus() {

        errors = new TreeMap<>();
    }

    /**
     * The method determines if an error has occurred.
     *
     * @return <code>true</code>, if an error has occurred, else
     *         <code>false</code>
     */
    @Override
    public boolean hasOccurredError() {

        return errors.size() > 0;
    }

    /**
     * Returns the cause of the error (i.e. the actual exception).
     *
     * @return the cause for an error or <code>null</code> if no error
     *         has occurred
     */
    @Override
    public Throwable getError() {

        return new MultipleCausesException(convertMap2Array());
    }

    /**
     * The date when the error occurred.
     *
     * @return a date or <code>null</code> if no error has occurred
     */
    @Override
    public Date getDate() {

        return errors.keySet()
                     .iterator()
                     .next();
    }

    /**
     * Converts the map's values into an array.
     *
     * @return an array
     */
    private Throwable[] convertMap2Array() {

        return errors.values().toArray(new Throwable[] { });
    }

    /**
     * Reports an error and changes the error status accordingly.
     *
     * @param anError
     */
    @Override
    public void reportError(Throwable anError) {

        Date date = new Date();
        errors.put(date, anError);
    }

    /**
     * Returns a string representation of the current error status.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder message = new StringBuilder();

        if (hasOccurredError()) {

            boolean first = true;
            for (Throwable error : errors.values()) {

                if (first) {

                    first = false;

                } else {

                    message.append(", ");
                }

                message.append(error.getClass().getSimpleName());
            }

        } else {

            message.append("no error");
        }

        return message.toString();
    }

}
