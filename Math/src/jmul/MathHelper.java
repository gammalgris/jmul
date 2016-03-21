/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul;


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
    }

    /**
     * Determines the highest number among the specified numbers.
     *
     * @param someNumbers
     *
     * @return the highest number
     */
    public static int max(int... someNumbers) {

        int numbers = someNumbers.length;


        if (numbers == 0) {

            String message = "No numbers have been specified!";
            throw new IllegalArgumentException(message);

        } else if (numbers == 1) {

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

}
