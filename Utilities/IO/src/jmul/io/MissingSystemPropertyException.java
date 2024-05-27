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

package jmul.io;


/**
 * A custom exception class for cases when system properties are msising.
 *
 * @author Kristian Kutin
 */
public class MissingSystemPropertyException extends RuntimeException {

    /**
     * Creates a new exception according to the specified parameter.
     *
     * @param key
     *        the property key for a missing system property
     */
    public MissingSystemPropertyException(String key) {

        super(createErrorMessage(key));
    }

    /**
     * Creates an error message for the missing system property.
     *
     * @param key
     *        a key for a system property
     *
     * @return an error message
     */
    private static String createErrorMessage(String key) {

        checkKey(key);

        String message = String.format("There is no system property %s!", key);
        return message;
    }

    /**
     * Checks the specified key.
     *
     * @param key
     *        a key for a system property
     *
     * @return the specified key
     */
    private static String checkKey(String key) {

        if (key == null) {

            throw new IllegalArgumentException("No key (null) has been specified!");
        }

        if (key.trim().isEmpty()) {

            throw new IllegalArgumentException("No key (empty string) has been specified!");
        }

        return key;
    }

}
