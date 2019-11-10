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

package jmul.math.notation.precision;


import jmul.math.exceptions.NumberTooBigException;
import static jmul.math.notation.Comparators.EQUALS;
import static jmul.math.notation.Comparators.GREATER_THAN;
import static jmul.math.notation.Comparators.LESSER_THAN;
import static jmul.math.notation.NotationHelper.evaluate;
import jmul.math.notation.NotationProperties;
import jmul.math.notation.PrecisionTypes;
import static jmul.math.notation.PrecisionTypes.SIGNED_INTEGER_32_BIT;
import static jmul.math.notation.PrecisionTypes.SIGNED_INTEGER_64_BIT;
import jmul.math.notation.Signs;


/**
 * An implementation of a precision checker for integer number strings.
 *
 * @author Kristian Kutin
 */
public class IntegerPrecisionChecker extends PrecisionCheckerBase {

    /**
     * A threshold for determining 32 bit integer precision (positive numbers).
     */
    private static final String MAX_INTEGER_32_BIT = String.valueOf(Integer.MAX_VALUE);

    /**
     * A threshold for determining 64 bit integer precision (positive numbers).
     */
    private static final String MAX_INTEGER_64_BIT = String.valueOf(Long.MAX_VALUE);

    /**
     * A threshold for determining 32 bit integer precision (negative numbers).
     */
    private static final String MIN_INTEGER_32_BIT = String.valueOf(Integer.MIN_VALUE);

    /**
     * A threshold for determining 64 bit integer precision (negative numbers).
     */
    private static final String MIN_INTEGER_64_BIT = String.valueOf(Long.MIN_VALUE);

    /**
     * The default constructor.
     */
    public IntegerPrecisionChecker() {

        super();
    }

    /**
     * Checks a positive number and returns a precision type.
     *
     * @param aString
     *        a number string
     *
     * @return a precision
     */
    private static PrecisionTypes checkPositiveNumber(String aString) {

        if (evaluate(aString, LESSER_THAN, MAX_INTEGER_32_BIT) || evaluate(aString, EQUALS, MAX_INTEGER_32_BIT)) {

            return SIGNED_INTEGER_32_BIT;

        } else if (evaluate(aString, LESSER_THAN, MAX_INTEGER_64_BIT) ||
                   evaluate(aString, EQUALS, MAX_INTEGER_64_BIT)) {

            return SIGNED_INTEGER_64_BIT;

        } else {

            throw new NumberTooBigException(aString);
        }
    }

    /**
     * Checks a negative number and returns a precision type.
     *
     * @param aString
     *        a number string
     *
     * @return a precision
     */
    private static PrecisionTypes checkNegativeNumber(String aString) {

        if (evaluate(aString, GREATER_THAN, MIN_INTEGER_32_BIT) || evaluate(aString, EQUALS, MIN_INTEGER_32_BIT)) {

            return SIGNED_INTEGER_32_BIT;

        } else if (evaluate(aString, GREATER_THAN, MIN_INTEGER_64_BIT) ||
                   evaluate(aString, EQUALS, MIN_INTEGER_64_BIT)) {

            return SIGNED_INTEGER_64_BIT;

        } else {

            throw new NumberTooBigException(aString);
        }
    }

    /**
     * Returns the identified precision type (i.e. the lowest possible precision type to store
     * the number) for the specified number string.
     *
     * @param aString
     *        a number string
     * @param someProperties
     *        the properties of the number
     *
     * @return a precision
     */
    @Override
    public PrecisionTypes checkPrecision(String aString, NotationProperties someProperties) {

        Signs sign = someProperties.getSign();

        switch (sign) {
        case POSITIVE:
            return checkPositiveNumber(aString);
        case NEGATIVE:
            return checkNegativeNumber(aString);
        default:
            throw new UnsupportedOperationException();
        }
    }

}
