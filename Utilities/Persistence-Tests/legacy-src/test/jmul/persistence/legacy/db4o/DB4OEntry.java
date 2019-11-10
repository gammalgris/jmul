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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.persistence.legacy.db4o;


import jmul.misc.id.ID;


/**
 * An implementation of a DB4O entry which associates an object with an id.
 *
 * @param <T>
 * an object type
 *
 * @author Kristian Kutin
 */
public class DB4OEntry<T> {

    /**
     * The id for this entry.
     */
    private final ID id;

    /**
     * The object which is associated with this entry.
     */
    private final T object;

    /**
     * Constructs an entry.
     *
     * @param anID
     * an id
     * @param anObject
     * an object
     */
    public DB4OEntry(ID anID, T anObject) {

        id = anID;
        object = anObject;
    }

    /**
     * A getter method.
     *
     * @return the id for this entry
     */
    public ID getId() {

        return id;
    }

    /**
     * A getter method.
     *
     * @return the object which is associated with this entry
     */
    public T getObject() {

        return object;
    }

    /**
     * Checks if this object equals the specified object.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if both objects are equal, else
     *         <code>false</code>.
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (o == this) {

            return true;
        }

        if (o instanceof DB4OEntry) {

            DB4OEntry other = (DB4OEntry) o;

            return this.id.equals(other.id) && this.object.equals(other.object);
        }

        return false;
    }

    /**
     * Calculates a hash value for this object.
     *
     * @return a hash value
     */
    @Override
    public int hashCode() {

        int hash = 17;
        hash = hash * id.hashCode();
        hash = hash * object.hashCode();
        return hash;
    }

}
