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

package jmul.misc.id;


/**
 * An implementation of an ID.
 *
 * @author Kristian Kutin
 */
public class IntegerID implements ID {

    /**
     * The class member contains the id.
     */
    private final int id;

    /**
     * A hash value.
     */
    private final int hash;

    /**
     * Constructs an id.
     *
     * @param anID
     *        the id
     */
    public IntegerID(int anID) {

        id = anID;
        hash = (new Integer(id)).hashCode();
    }

    /**
     * Constructs an id.
     *
     * @param anID
     *        the id
     */
    public IntegerID(String anID) {

        this(Integer.parseInt(anID));
    }

    /**
     * The method determines if this id and the specified object are equal.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if both objects are equal, else
     * <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof IntegerID) {

            IntegerID other = (IntegerID) o;

            return this.id == other.id;
        }

        return false;
    }

    /**
     * The method calculates a hash code for this object.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        return hash;
    }

    /**
     * The method returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.valueOf(id);
    }

}
