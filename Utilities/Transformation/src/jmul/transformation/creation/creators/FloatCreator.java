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

package jmul.transformation.creation.creators;


/**
 * An implementation of a creation rule.
 *
 * @author Kristian Kutin
 */
public class FloatCreator implements ObjectCreator {

    /**
     * The default constructor.
     */
    public FloatCreator() {

        super();
    }

    /**
     * Instantiates and initializes a <code>float</code>.
     *
     * @param anInitialValue
     *        a string which contains an initial value
     * @param aPattern
     *        a pattern which tells how to parse the string which contains the
     *        inital value
     *
     * @return a new object
     */
    @Override
    public Object createObject(String anInitialValue, String aPattern) {

        if (anInitialValue == null) {

            String message = "No initial value has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (aPattern != null) {

            String message = "A pattern was specified but no pattern is required!";
            throw new IllegalArgumentException(message);
        }

        String normalizedValue = NormalizationHelper.normalizeRealNumber(anInitialValue);
        return Float.parseFloat(normalizedValue);
    }

}
