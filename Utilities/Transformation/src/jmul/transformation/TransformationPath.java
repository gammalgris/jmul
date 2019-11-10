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

package jmul.transformation;


import jmul.string.TextHelper;


/**
 * The class desribes a transformatio path.
 *
 * @author Kristian Kutin
 */
public final class TransformationPath {

    /**
     * A description of the transformation origin.
     */
    private final String origin;

    /**
     * A description of the transformation destination.
     */
    private final String destination;

    /**
     * Constructs a transformation path.
     *
     * @param anOrigin
     *        a description of the transformation origin
     * @param aDestination
     *        a description of the transformation destination
     */
    public TransformationPath(String anOrigin, String aDestination) {

        origin = anOrigin;
        destination = aDestination;
    }

    /**
     * Calculates a hash value for this entity.
     *
     * @return a hash value
     */
    @Override
    public int hashCode() {

        //TODO bad hash function
        return origin.hashCode() + destination.hashCode();
    }

    /**
     * Checks if this object is equal to the specified object.
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

        if (o == this) {

            return true;
        }

        if (o instanceof TransformationPath) {

            TransformationPath other = (TransformationPath) o;

            return this.origin.equals(other.origin) && this.destination.equals(other.destination);
        }

        return false;
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return TextHelper.concatenateStrings(origin, " -> ", destination);
    }

    /**
     * A getter method.
     *
     * @return a description of the transformation origin
     */
    public String getOrigin() {

        return origin;
    }

    /**
     * A getter method.
     *
     * @return a description of the transformation destination
     */
    public String getDestination() {

        return destination;
    }

}
