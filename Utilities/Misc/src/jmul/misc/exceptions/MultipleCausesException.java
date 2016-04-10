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

package jmul.misc.exceptions;


import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static jmul.misc.checks.ParameterCheckHelper.checkExceptionCauses;
import static jmul.misc.checks.ParameterCheckHelper.checkExceptionMessage;


/**
 * This exception contains several related exceptions.
 *
 * @author Kristian Kutin
 */
public class MultipleCausesException extends Exception implements Iterable<Throwable> {

    /**
     * All related exceptions.
     */
    private final List<Throwable> relatedExceptions;

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param someRelatedExceptions
     */
    public MultipleCausesException(Throwable... someRelatedExceptions) {

        super();

        relatedExceptions = Collections.unmodifiableList(Arrays.asList(checkExceptionCauses(someRelatedExceptions)));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     * @param someRelatedExceptions
     */
    public MultipleCausesException(String aMessage, Throwable... someRelatedExceptions) {

        super(checkExceptionMessage(aMessage));

        relatedExceptions = Collections.unmodifiableList(Arrays.asList(checkExceptionCauses(someRelatedExceptions)));
    }

    /**
     * Returns an iterator to access all related exceptions.
     *
     * @return an iterator
     */
    @Override
    public Iterator<Throwable> iterator() {

        return relatedExceptions.iterator();
    }

}
