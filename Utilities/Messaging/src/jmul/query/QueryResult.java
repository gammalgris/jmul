/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.query;


/**
 * This interface describes an entity which contains the result of a query. Classes which
 * inherit this interface hide handling <code>null</code> values directly.
 *
 * @param <T>
 *        the specific type of the actual query result
 *
 * @author Kristian Kutin
 */
public interface QueryResult<T> {

    /**
     * This method allows to check if there is a result or not.
     *
     * @return <code>true</code> if there is a result, else <code>false</code>
     */
    boolean existsResult();

    /**
     * Returns the actual query result or throws a {@link jmul.query.NoResultException} if there
     * is no result.
     *
     * @return a query result, otherwise a {@link jmul.query.NoResultException}
     */
    T result();

}
