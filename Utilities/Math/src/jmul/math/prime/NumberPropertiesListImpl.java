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

package jmul.math.prime;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * An implementation of a number property list. This implementation
 * caches previous results in order to avoid duplicate calculations.
 *
 * @author Kristian Kutin
 */
class NumberPropertiesListImpl implements NumberPropertiesList {

    /**
     * The actual container for storing the number and its properties.<br />
     * <br />
     * <i>Note:<br />
     * The index represents an integer and the associated boolean
     * value indicates that the number is a prime number (<code>true</code>)
     * or not (<code>false</code>).</i>
     */
    private List<Boolean> properties;

    /**
     * The default constructor.
     */
    public NumberPropertiesListImpl() {

        properties = new ArrayList<>();

        properties.add(false); // index = 0 => no prime number
        properties.add(false); // index = 1 => no prime number
        properties.add(true); // index = 2 => prime number
    }

    /**
     * Checks if the specified number is a prime number.
     *
     * @param n
     *
     * @return <code>true</code> if the specified number is
     *         a prime number, else <code>false</code>
     */
    @Override
    public boolean isPrimeNumber(int n) {

        if (n < 0) {

            String message = "Only positive integers (including zero) are allowed (actual parameter=" + n + ")!";
            throw new IllegalArgumentException(message);
        }

        if (n >= properties.size()) {

            addNumber(n);
        }

        return properties.get(n);
    }

    /**
     * Adds the specified number to the list and identifies
     * its properties.
     *
     * @param n
     */
    private void addNumber(int n) {

        if (n < 1) {

            String message = "Only positive integers (without zero) are allowed (actual parameter=" + n + ")!";
            throw new IllegalArgumentException(message);
        }


        if (n < properties.size()) {

            return;
        }


        addNumber(n - 1);

        properties.add(checkPropertyPrimeNumber(n));
    }

    /**
     * Cehcks if the specified number is a prime number.
     *
     * @param n
     *
     * @return all divisors
     */
    private static boolean checkPropertyPrimeNumber(int n) {

        if (n < 1) {

            String message = "Only positive integers (without zero) are allowed (actual parameter=" + n + ")!";
            throw new IllegalArgumentException(message);
        }

        Set<Integer> result = new TreeSet<>();

        int sqrt = (int) (Math.sqrt(n));

        for (int a = 1; a <= sqrt; a++) {

            if (result.size() > 2) {

                return false;
            }

            int b = n / a;
            if ((n % a) == 0) {

                result.add(a);
                result.add(b);
            }
        }

        return result.size() == 2;
    }

}
