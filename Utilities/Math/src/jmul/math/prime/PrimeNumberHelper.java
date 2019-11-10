/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.math.prime;


/**
 * A utility class regarding prime numbers.
 *
 * @author Kristian kutin
 */
public final class PrimeNumberHelper {

    /**
     * This container contains the information about numbers and
     * prime numbers.
     */
    private static final NumberPropertiesList NUMBER_PROPERTIES_LIST;

    /*
     * The static initializer.
     */
    static {

        NUMBER_PROPERTIES_LIST = new NumberPropertiesListImpl();
    }

    /**
     * The default constructor.
     */
    private PrimeNumberHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Calculates and returns the nth prime number.<br>
     * <br>
     * <i>Note:<br>
     * 2 is the first prime number. 3 is the second prime number.
     * 5 ist the third prime number. And so on.</i>
     *
     * @param n
     *        a number (see type)
     *
     * @return a prime number
     */
    public static int getNextPrimeNumber(int n) {

        if (n < 1) {

            String message = "Only positive integers (without zero) are allowed (actual parameter=" + n + ")!";
            throw new IllegalArgumentException(message);
        }


        int number = 1;
        int count = 0;
        int nextPrimeNumber = -1;

        while (count < n) {

            if (NUMBER_PROPERTIES_LIST.isPrimeNumber(number)) {

                count++;
                nextPrimeNumber = number;

            }

            number++;
        }

        return nextPrimeNumber;
    }

}
