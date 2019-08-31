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

package jmul.math.notation;


import static jmul.math.notation.Signs.NEGATIVE;
import static jmul.math.notation.Signs.POSITIVE;


/**
 * An implementation for a container with notation properties.
 *
 * @author Kristian Kutin
 */
public class NotationPropertiesImpl implements NotationProperties {

    /**
     * The decimal separator for a given number string.
     */
    private final String decimalSeparator;

    /**
     * The notation type for a given number string.
     */
    private final NotationTypes notationType;

    /**
     * The sign for a given number string.
     */
    private final Signs sign;

    /**
     * The exponent sign for a given number string.
     */
    private final Signs exponentSign;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aDecimalSeparator
     *        the decimal separator which is used
     * @param aNotationType
     *        the notation type which is used
     * @param aSign
     *        the sign of this number
     */
    public NotationPropertiesImpl(String aDecimalSeparator, NotationTypes aNotationType, Signs aSign) {

        this(aDecimalSeparator, aNotationType, aSign, null);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aDecimalSeparator
     *        the decimal separator which is used
     * @param aNotationType
     *        the notation type which is used
     * @param aSign
     *        the sign of this number
     * @param anExponentSign
     *        the sign of the exponent of this number
     */
    public NotationPropertiesImpl(String aDecimalSeparator, NotationTypes aNotationType, Signs aSign,
                                  Signs anExponentSign) {

        super();

        decimalSeparator = aDecimalSeparator;
        notationType = aNotationType;
        sign = aSign;
        exponentSign = anExponentSign;
    }

    /**
     * Returns the decimal separator for a given number string.
     *
     * @return a decimal separator character
     */
    @Override
    public String getDecimalSeparator() {

        return decimalSeparator;
    }

    /**
     * Returns the notation type for a given number string.
     *
     * @return a notation type
     */
    @Override
    public NotationTypes getNotationType() {

        return notationType;
    }

    /**
     * Checks the sign of the given number string.
     *
     * @return <code>true</code> for a positive number, else <code>false</code>
     */
    @Override
    public boolean isPositive() {

        return POSITIVE == getSign();
    }

    /**
     * Checks the sign of the given number string.
     *
     * @return <code>true</code> for a negative number, else <code>false</code>
     */
    @Override
    public boolean isNegative() {

        return NEGATIVE == getSign();
    }

    /**
     * Returns the sign of the given number string.
     *
     * @return a sign
     */
    @Override
    public Signs getSign() {

        return sign;
    }

    /**
     * Checks if the given number string has an exponent (i.e. this only applies for certain notations
     * like the scientific notation).
     *
     * @return <code>true</code> if an exponent exists, else <code>false</code>
     */
    @Override
    public boolean hasExponent() {

        return exponentSign != null;
    }

    /**
     * Checks the exponent sign of the given string.
     *
     * @return <code>true</code> for a positive exponent, else <code>false</code>
     */
    @Override
    public boolean hasPositiveExponent() {

        return POSITIVE == getExponentSign();
    }

    /**
     * Checks the exponent sign of the given string.
     *
     * @return <code>true</code> for a negative exponent, else <code>false</code>
     */
    @Override
    public boolean hasNegativeExponent() {

        return NEGATIVE == getExponentSign();
    }

    /**
     * Returns the exponent sign of the given number string.
     *
     * @return a sign
     */
    @Override
    public Signs getExponentSign() {

        return exponentSign;
    }

}
