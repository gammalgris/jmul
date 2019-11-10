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

package jmul.math.hash.archive;


import java.util.List;


/**
 * This interface describes an archive for prime numbers which are used
 * for calculating hash values.
 *
 * @author Kristian Kutin
 */
public interface PrimeNumberArchive {

    /**
     * Add a new entry (a class which is associated with two prime numbers).
     * For each class there will be a unique entry which cannot be changed during
     * runtime.
     *
     * @param aClass
     *        the class for which to store the prime numbers
     * @param somePrimeNumbers
     *        a list with prime numbers
     */
    void addEntry(Class aClass, List<Integer> somePrimeNumbers);

    /**
     * Checks if an entry exists for for the specified class.
     *
     * @param aClass
     *        the class for which to check the existence of a prime number list
     *
     * @return <code>true</code> if an entry exists, else <code>false</code>
     */
    boolean existsEntry(Class aClass);

    /**
     * Returns the list which associated with the specified class.
     *
     * @param aClass
     *        the class for which to retrieve the prime number list
     *
     * @return a list of prime numbers
     */
    List<Integer> getEntry(Class aClass);

}
