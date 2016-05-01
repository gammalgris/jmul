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

package jmul.reflection.classes;


/**
 * A utility class.
 *
 * @author Kristian Kutin
 */
public final class AccessorHelper {

    /**
     * A prefix value.
     */
    public static final String GETTER_PREFIX = "get";

    /**
     * A prefix value.
     */
    public static final String SETTER_PREFIX = "set";

    /**
     * The default constructor.
     */
    private AccessorHelper() {
    }

    /**
     * The method determineAccessorName builds the name for an accessor method.
     *
     * @param aPrefix
     *        a prefix
     * @param aFieldName
     *        a field name
     *
     * @return an accessor name
     */
    public static String determineAccesorName(String aPrefix, String aFieldName) {

        String firstLetter = aFieldName.substring(0, 1);
        firstLetter = firstLetter.toUpperCase();
        String rest = aFieldName.substring(1);
        String accessorName = aPrefix + firstLetter + rest;

        return accessorName;
    }

}
