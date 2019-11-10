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
 * This interface describes how to add objects to an object container.
 *
 * @param <T>
 *        the type of objects which this object container handles
 *
 * @author Kristian Kutin
 */
public interface ExplicitIDAppender<T> {

    /**
     * The method adds an object to this object container and the
     * specified object is assigned the specified ID.
     *
     * The method adds an object and internally an ID is assigned to it.
     *
     * @param anID
     *        the ID of the specified object
     * @param anObject
     *        an object
     *
     * @return the ID which was assigned to the specified object
     */
    ID addObject(ID anID, T anObject);

    /**
     * The method adds an object to this object container and the
     * specified object is assigned the specified ID.
     *
     * The method adds an object and internally an ID is assigned to it.
     *
     * @param anID
     *        the ID of the specified object
     * @param anObject
     *        an object
     * @param aDeclaredType
     *        the declared type of the object
     *
     * @return the ID which was assigned to the specified object
     */
    ID addObject(ID anID, T anObject, Class aDeclaredType);

}
