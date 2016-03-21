/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.cache.contracts;


import jmul.id.ID;


/**
 * This interface describes how to add objects to an object container.
 *
 * @param <T>
 *        the type of objects which this object container handles
 *
 * @author Kristian Kutin
 */
public interface ImplicitIDAppender<T> {

    /**
     * The method adds an object to this object container and internally the
     * specified object is assigned an ID.
     *
     * @param anObject
     *        an object
     * @param aDeclaredType
     *        the declared type of the object
     *
     * @return the ID which was assigned to the specified object
     */
    ID addObject(Object anObject, Class aDeclaredType);

}
