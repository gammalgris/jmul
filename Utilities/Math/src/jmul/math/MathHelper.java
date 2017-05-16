/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2014  Kristian Kutin
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

package jmul.math;

import java.util.Set;
import java.util.TreeSet;

import jmul.misc.exceptions.EmptyArrayParameterException;
import jmul.misc.exceptions.NullArrayParameterException;


/**
 * A utility class which represents an extension of {@link java.lang.Math}.
 *
 * @author Kristian Kutin
 */
public final class MathHelper {

    /**
     * The default constructor.
     */
    private MathHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Determines the highest number among the specified numbers.
     *
     * @param someNumbers
     *
     * @return the highest number
     */
    public static int max(int... someNumbers) {

        checkArray(someNumbers);


        int numbers = someNumbers.length;

        if (numbers == 1) {

            return someNumbers[0];

        } else {

            int maxNumber = someNumbers[0];

            for (int a = 1; a < numbers; a++) {

                int number = someNumbers[a];
                maxNumber = Math.max(maxNumber, number);
            }

            return maxNumber;
        }
    }

    /**
     * Checks the specified parameter.
     *
     * @param someNumbers
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    private static void checkArray(int[] someNumbers) {

        if (someNumbers == null) {

            throw new NullArrayParameterException();
        }

        if (someNumbers.length == 0) {

            throw new EmptyArrayParameterException();
        }
    }

    /**
     * Calculates all divisors (including 1 and the number itself)
     * for the specified number.
     *
     * @param n
     *
     * @return all divisors
     */
    public static Set<Integer> calculateAllDivisors(int n) {

        if (n < 1) {

            String message = "Only positive integers are allowed (actual parameter=" + n + ")!";
            throw new IllegalArgumentException(message);
        }

        Set<Integer> result = new TreeSet<Integer>();

        int sqrt = (int) (Math.sqrt(n));

        for (int a = 1; a <= sqrt; a++) {

            int b = n / a;
            if ((n % a) == 0) {

                result.add(a);
                result.add(b);
            }
        }

        return result;
    }

}
