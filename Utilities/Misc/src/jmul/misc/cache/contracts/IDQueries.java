/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.misc.cache.contracts;


import jmul.misc.id.ID;


/**
 * This interface declares queries for an object container.
 *
 * @param <T>
 *        the type of objects which this object container handles
 *
 * @author Kristian Kutin
 */
public interface IDQueries<T> {

    /**
     * The method checks if the object container knows the specified ID.
     *
     * @param anID
     *        an ID
     *
     * @return <code>true</code> if the object container knows the specified ID,
     *         else <code>false</code>
     */
    boolean existsID(ID anID);

    /**
     * The method returns the ID for the specified object.
     *
     * @param anObject
     *        an object
     * @param aDeclaredType
     *        the declared type of the object
     *
     * @return the ID which was assigned to the specified object or
     *         <code>null</code> if the object container doesn't know the
     *         specified object
     */
    ID getID(T anObject, Class aDeclaredType);

    /**
     * The method returns the id of the specified object
     *
     * @param anObject
     *        an object
     *
     * @return the ID which was assigned to the specified object or
     *         <code>null</code> if the object container doesn't know the
     *         specified object
     */
    ID getID(T anObject);

}
