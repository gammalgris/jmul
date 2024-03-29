/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.misc.exceptions;


import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static jmul.checks.ParameterCheckHelper.checkExceptionCauses;
import static jmul.checks.ParameterCheckHelper.checkExceptionMessage;

/**
 * This exception contains several related exceptions (e.g. an exception
 * and an exception which occurs during the exception handling).
 *
 * @author Kristian Kutin
 *
 * @deprecated obsolete, see suppressed exceptions in Throwable implementation
 */
 @Deprecated
public class MultipleCausesException extends Exception implements Iterable<Throwable> {

    /**
     * The serial UID as required by java's serialization mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The index of the first exception.
     */
    private static final int FIRST_EXCEPTION_INDEX = 0;

    /**
     * All related exceptions.
     */
    private final List<Throwable> relatedExceptions;

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param someRelatedExceptions
     *        alle related exceptions (i.e. the first exception is the actual exception
     *        and the remaining should be folloup exceptions that occur during error
     *        handling
     */
    public MultipleCausesException(Throwable... someRelatedExceptions) {

        super(getFirstExceptionMessage(checkExceptionCauses(someRelatedExceptions)),
              getFirstException(checkExceptionCauses(someRelatedExceptions)));

        relatedExceptions = Collections.unmodifiableList(Arrays.asList(checkExceptionCauses(someRelatedExceptions)));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     *        the error message
     * @param someRelatedExceptions
     *        alle related exceptions (i.e. the first exception is the actual exception
     *        and the remaining should be folloup exceptions that occur during error
     *        handling
     */
    public MultipleCausesException(String aMessage, Throwable... someRelatedExceptions) {

        super(checkExceptionMessage(aMessage), getFirstException(checkExceptionCauses(someRelatedExceptions)));

        relatedExceptions = Collections.unmodifiableList(Arrays.asList(checkExceptionCauses(someRelatedExceptions)));
    }

    /**
     * Returns an iterator to access all related exceptions.
     *
     * @return an iterator
     *         an iterator to iterate through all exceptions
     */
    @Override
    public Iterator<Throwable> iterator() {

        return relatedExceptions.iterator();
    }

    /**
     * Returns the first exception message.
     *
     * @param someRelatedExceptions
     *        alle related exceptions (i.e. the first exception is the actual exception
     *        and the remaining should be folloup exceptions that occur during error
     *        handling
     *
     * @return an exception message
     */
    private static String getFirstExceptionMessage(Throwable... someRelatedExceptions) {

        return getFirstException(someRelatedExceptions).getMessage();
    }

    /**
     * Returns the first exceptions.
     *
     * @param someRelatedExceptions
     *        alle related exceptions (i.e. the first exception is the actual exception
     *        and the remaining should be folloup exceptions that occur during error
     *        handling
     *
     * @return an exception
     */
    private static Throwable getFirstException(Throwable... someRelatedExceptions) {

        return someRelatedExceptions[FIRST_EXCEPTION_INDEX];
    }

}
