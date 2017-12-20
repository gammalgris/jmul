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

package jmul.misc.id;


/**
 * An implementation of an ID.<br>
 * <br>
 * Unlike with a numeric ID a string ID in theory has no limits (except for
 * machine limitations, i.e. memory limitations).
 *
 * @author Kristian Kutin
 */
public class StringID implements ID {

    /**
     * The class member contains the id.
     */
    private final String id;

    /**
     * Constructs an id.
     *
     * @param anID
     *        a string which contains the id
     */
    public StringID(String anID) {

        id = anID;
    }

    /**
     * The method determines if this id and the specified object are equal.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if both objects are equal, else
     *         <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof StringID) {

            StringID other = (StringID) o;

            return this.id.equals(other.id);
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

        return id.hashCode();
    }

    /**
     * The method returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return id;
    }

}
