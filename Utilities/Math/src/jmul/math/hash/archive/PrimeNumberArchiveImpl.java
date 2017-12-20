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

package jmul.math.hash.archive;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jmul.math.hash.Constants.DEFAULT_LIST_SIZE;

import jmul.misc.checks.ParameterCheckHelper;


/**
 * An implementation of a prime number archive.
 *
 * @author Kristian Kutin
 */
public class PrimeNumberArchiveImpl implements PrimeNumberArchive {

    /**
     * The actual archive.
     */
    private Map<Class, List<Integer>> entries;

    /**
     * The default constructor.
     */
    public PrimeNumberArchiveImpl() {

        super();

        entries = new HashMap<>();
    }

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
    @Override
    public void addEntry(Class aClass, List<Integer> somePrimeNumbers) {

        ParameterCheckHelper.checkClass(aClass);
        ParameterCheckHelper.checkList(somePrimeNumbers, DEFAULT_LIST_SIZE);

        if (entries.containsKey(aClass)) {

            throw new ExistingEntryException(aClass);
        }

        entries.put(aClass, somePrimeNumbers);
    }

    /**
     * Checks if an entry exists for for the specified class.
     *
     * @param aClass
     *        the class for which to check the existence of a prime number list
     *
     * @return <code>true</code> if an entry exists, else <code>false</code>
     */
    @Override
    public boolean existsEntry(Class aClass) {

        return entries.containsKey(aClass);
    }

    /**
     * Returns the list which associated with the specified class.
     *
     * @param aClass
     *        the class for which to retrieve the prime number list
     *
     * @return a list of prime numbers
     */
    @Override
    public List<Integer> getEntry(Class aClass) {

        if (!entries.containsKey(aClass)) {

            throw new UnknownClassException(aClass);
        }

        return entries.get(aClass);
    }

}
