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

package jmul.math.hash;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static jmul.math.hash.Constants.DEFAULT_LIST_SIZE;
import jmul.math.hash.archive.PrimeNumberArchive;
import jmul.math.hash.archive.PrimeNumberArchiveImpl;
import jmul.math.prime.PrimeNumberHelper;
import static jmul.math.random.StandardDice.D20;


/**
 * A utility class for calculating hash values.
 *
 * @author Kristian Kutin
 */
public final class HashHelper {

    /**
     * The archive stores prime numbers for calculating hash codes.
     */
    private static PrimeNumberArchive archive;

    /*
     * The static initializer.
     */
    static {

        archive = new PrimeNumberArchiveImpl();
    }

    /**
     * The default constructor.
     */
    private HashHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Calculates a hash code according to the specified parameters.
     *
     * @param aClass
     *        the class
     * @param someObjects
     *        all field values of an instance of the class
     *
     * @return a hash value
     */
    public static int calculateHashCode(Class aClass, Object... someObjects) {

        List<Integer> primeNumbers = getTwoPrimeNumbers(aClass);

        return calculateHashCode(primeNumbers, someObjects);
    }

    /**
     * Associates two random prime numbers with the specified class. In some cases the method
     * {@link #calculateHashCode} cannot be used because the underlying data structure cannot easily
     * be put in an object array or would lead to some unneccessary calculation overhead.<br>
     * In those cases use this method to always work with the same prime numbers and do your own
     * hash calculation.
     *
     * @param aClass
     *        a class object
     *
     * @return a list containing two prime numbers
     */
    public static List<Integer> getTwoPrimeNumbers(Class aClass) {

        if (!archive.existsEntry(aClass)) {

            archive.addEntry(aClass, determineTwoPrimeNumbers());
        }

        return archive.getEntry(aClass);
    }

    /**
     * Calculates a hash code according to the specified parameters.
     *
     * @param twoPrimeNumbers
     *        a list with two prime numbers
     * @param someObjects
     *        all field values of an instance of the class
     *
     * @return a hash value
     */
    public static int calculateHashCode(List<Integer> twoPrimeNumbers, Object... someObjects) {

        if (twoPrimeNumbers.size() != DEFAULT_LIST_SIZE) {

            String message = "No two numbers were specified!";
            throw new IllegalArgumentException(message);
        }

        int firstPrime = twoPrimeNumbers.get(0);
        int secondPrime = twoPrimeNumbers.get(1);

        int hash = firstPrime;

        for (Object object : someObjects) {

            int n;

            if (object == null) {

                n = 0;

            } else {

                n = object.hashCode();
            }

            hash = secondPrime * hash + n;
        }

        return hash;
    }

    /**
     * Determines two prime numbers and returns them as a list.
     *
     * @return a list with two prime numbers
     */
    public static List<Integer> determineTwoPrimeNumbers() {

        Set<Integer> primeNumbers = new TreeSet<>();

        while (primeNumbers.size() < DEFAULT_LIST_SIZE) {

            int primeNumber = PrimeNumberHelper.getNextPrimeNumber(D20.roll());
            primeNumbers.add(primeNumber);
        }

        return new ArrayList<>(primeNumbers);
    }

}
