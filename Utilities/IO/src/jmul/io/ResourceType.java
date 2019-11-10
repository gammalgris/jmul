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

package jmul.io;


import static jmul.string.Constants.POINT;


/**
 * A utility class which contains informations about a certain resource type.
 *
 * @author Kristian kutin
 */
public class ResourceType {

    /**
     * The file suffix which is associated with this resource type.
     */
    private final String fileSuffix;

    /**
     * Constructs a resource type entity.
     *
     * @param aFileSuffix
     *        a file suffix
     */
    public ResourceType(String aFileSuffix) {

        fileSuffix = aFileSuffix;
    }

    /**
     * The method returns the file suffix for this resource type.
     *
     * @return a file suffix
     */
    public String getFileSuffix() {

        return fileSuffix;
    }

    /**
     * The method returns the file suffix for this resource type including a
     * separator character.
     *
     * @return a file suffix
     */
    public String getFileSuffixWithSeparator() {

        return POINT + getFileSuffix();
    }

    /**
     * Returns a string representation for this entity.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getFileSuffix();
    }

}
