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

package test.jmul.math;


import jmul.math.Counter;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests a {@link jmul.math.Counter}.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CounterTest {

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
     * Tests the initialization of a counter.
     */
    @Test
    public void testInitialization() {

        Counter counter = new Counter();
        assertNotNull(counter);
        assertEquals(0, counter.getCount());
    }

    /**
     * Tests incrementing the counter.
     */
    @Test
    public void testIncrement() {

        Counter counter = newCounter(0);

        assertEquals(0, counter.getCount());
        counter.increment();
        assertEquals(1, counter.getCount());
    }

    /**
     * Tests resetting the counter.
     */
    @Test
    public void testReset() {

        Counter counter = newCounter(1);

        assertEquals(1, counter.getCount());
        counter.reset();
        assertEquals(0, counter.getCount());
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {

        Counter counter = newCounter(0);

        assertEquals("0", counter.toString());
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString2() {

        Counter counter = newCounter(10);

        assertEquals("10", counter.toString());
    }

    /**
     * Creates a new counter and sets it to the specified count.
     *
     * @param anInitialValue
     *
     * @return a counter
     */
    private static Counter newCounter(int anInitialValue) {

        Counter counter = new Counter();

        for (int a = 0; a < anInitialValue; a++) {

            counter.increment();
        }

        return counter;
    }

}
