/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package test.jmul.misc.exceptions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import jmul.misc.exceptions.MultipleCausesException;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MultipleCausesExceptionValidParametersTest {

    /**
     * The message of an exception.
     */
    private String message;

    /**
     * The cause of an exception.
     */
    private Throwable[] causes;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aMessage
     * @param someCauses
     */
    public MultipleCausesExceptionValidParametersTest(String aMessage, Throwable... someCauses) {

        message = aMessage;
        causes = someCauses;
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Tests the instantiation of an exception with valid parameters.
     */
    @Test
    public void testInstantiation() {

        MultipleCausesException exception = new MultipleCausesException(causes);
        checkEquality(causes, exception);
    }

    /**
     * Tests the instantiation of an exception with valid parameters.
     */
    @Test
    public void testInstantiation2() {

        MultipleCausesException exception = new MultipleCausesException(message, causes);
        assertEquals(message, exception.getMessage());
        checkEquality(causes, exception);
    }

    /**
     *
     *
     * @param anArray
     *
     * @param anIterable
     */
    private static void checkEquality(Throwable[] anArray, Iterable<Throwable> anIterable) {

        Collection<Throwable> c1 = array2List(anArray);
        Collection<Throwable> c2 = iterable2List(anIterable);

        assertTrue(c1.containsAll(c2));
        assertTrue(c2.containsAll(c1));
    }

    /**
     * Transforms an array to a collection.
     *
     * @param someCauses
     *
     * @return a collection
     */
    private static Collection<Throwable> array2List(Throwable[] someCauses) {

        return Arrays.asList(someCauses);
    }

    /**
     * Transforms an iterable to a collection
     *
     * @param someCauses
     *
     * @return a collection
     */
    private static Collection<Throwable> iterable2List(Iterable<Throwable> someCauses) {

        Collection<Throwable> causes = new ArrayList<Throwable>();

        for (Throwable cause : someCauses) {

            causes.add(cause);
        }

        return causes;
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "Error", new Throwable[] { new RuntimeException() } });
        parameters.add(new Object[] {
                       "Error", new Throwable[] { new RuntimeException(), new IllegalArgumentException() } });

        return parameters;
    }

}
