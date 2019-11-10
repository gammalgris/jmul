/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package jmul.math.notation.parser;


import jmul.math.notation.PrecisionTypes;


/**
 * An implementation of a number parser that parses integer numbers provided as string.
 *
 * @author Kristian Kutin
 */
public class IntegerParser implements NumberParser {

    /**
     * The default constructor.
     */
    public IntegerParser() {

        super();
    }

    /**
     * Parses the specified string. If the actual precision doesn't match the expected
     * precision then an exception is thrown.
     *
     * @param aPrecisionType
     *        the expected precision
     * @param aString
     *        a number string
     *
     * @return a parsed number
     */
    @Override
    public Number parseString(PrecisionTypes aPrecisionType, String aString) {

        Number result;

        switch (aPrecisionType) {
        case SIGNED_INTEGER_32_BIT:
            result = Integer.parseInt(aString);
            break;
        case SIGNED_INTEGER_64_BIT:
            result = Long.parseLong(aString);
            break;
        case SIGNED_FLOATING_POINT_32_BIT:
            result = Float.parseFloat(aString);
            break;
        case SIGNED_FLOATING_POINT_64_BIT:
            result = Double.parseDouble(aString);
            break;
        default:
            String message = "An unknown precision was specified (" + aPrecisionType + ")!";
            throw new UnsupportedOperationException(message);
        }

        return result;
    }

}
