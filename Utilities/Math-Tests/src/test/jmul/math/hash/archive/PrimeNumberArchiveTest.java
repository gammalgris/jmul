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

package test.jmul.math.hash.archive;


import java.util.ArrayList;
import java.util.List;

import jmul.checks.exceptions.NullListParameterException;
import jmul.checks.exceptions.NullParameterException;
import jmul.checks.exceptions.UnexpectedSizeException;

import jmul.math.hash.HashHelper;
import jmul.math.hash.archive.ExistingEntryException;
import jmul.math.hash.archive.PrimeNumberArchive;
import jmul.math.hash.archive.PrimeNumberArchiveImpl;
import jmul.math.hash.archive.UnknownClassException;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * The class contains tests to check the prime number archive.
 *
 * @author Kristian Kutin
 */
public class PrimeNumberArchiveTest {

    /**
     * The actual archive.
     */
    private PrimeNumberArchive archive;

    /**
     * Steps which are executed before a test.
     */
    @Before
    public void setUp() {

        archive = new PrimeNumberArchiveImpl();
    }

    /**
     * Steps which are executed after a test.
     */
    @After
    public void tearDown() {

        archive = null;
    }

    /**
     * Tests adding a new entry with valid parameters.
     */
    @Test
    public void testSuccessfulAddEntry() {

        Class clazz = Object.class;
        List<Integer> primeNumbers = HashHelper.determineTwoPrimeNumbers();

        archive.addEntry(clazz, primeNumbers);
        assertTrue(archive.existsEntry(clazz));
    }

    /**
     * Tests adding a new entry a second time.
     */
    @Test(expected = ExistingEntryException.class)
    public void testUnsuccessfulAddEntry() {

        Class clazz = Object.class;
        List<Integer> primeNumbers = HashHelper.determineTwoPrimeNumbers();

        archive.addEntry(clazz, primeNumbers);
        archive.addEntry(clazz, primeNumbers);
    }

    /**
     * Tests adding an entry for a class <code>null</code>.
     */
    @Test(expected = NullParameterException.class)
    public void testInvalidClassAddEntry() {

        Class clazz = null;
        List<Integer> primeNumbers = HashHelper.determineTwoPrimeNumbers();

        archive.addEntry(clazz, primeNumbers);
    }

    /**
     * Tests adding an entry with an emtpy list of prime numbers.
     */
    @Test(expected = UnexpectedSizeException.class)
    public void testInvalidClassAddEntry2() {

        Class clazz = Object.class;
        List<Integer> primeNumbers = new ArrayList<>();

        archive.addEntry(clazz, primeNumbers);
    }

    /**
     * Tests adding an entry with a list that is <code>null</code>.
     */
    @Test(expected = NullListParameterException.class)
    public void testInvalidClassAddEntry3() {

        Class clazz = Object.class;
        List<Integer> primeNumbers = null;

        archive.addEntry(clazz, primeNumbers);
    }

    /**
     * Tests checking for an existing entry with valid parameters.
     */
    @Test
    public void testExistsEntry() {

        Class clazz = Object.class;

        assertFalse(archive.existsEntry(clazz));
    }

    /**
     * Tests checking for an existing entry with a <code>null</code> parameter.
     */
    @Test
    public void testExistsEntry2() {

        Class clazz = null;

        assertFalse(archive.existsEntry(clazz));
    }

    /**
     * Tests retrieving an entry from an empty archive.
     */
    @Test(expected = UnknownClassException.class)
    public void testGetEntry() {

        Class clazz = Object.class;

        archive.getEntry(clazz);
    }

    /**
     * Tests retrieving an entry with an invalid parameter.
     */
    @Test(expected = NullParameterException.class)
    public void testGetEntry2() {

        Class clazz = null;

        archive.getEntry(clazz);
    }

}
