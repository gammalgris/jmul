/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package test.jmul.concurrent.threads;


import jmul.concurrent.threads.NameTypes;
import jmul.concurrent.threads.ThreadHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * The class contains several tests which test the functionality of the corresponding
 * helper class.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ThreadHelperTest {

    /**
     * Tests getting the name of the invoking method.
     */
    @Test
    public void testGetInvokingMethodName() {

        String expectedMethodName = "testGetInvokingMethodName";
        String actualMethodName = ThreadHelper.getInvokingMethodName();

        assertEquals(expectedMethodName, actualMethodName);
    }

    /**
     * Tests getting the name of the invoking method.
     */
    @Test
    public void testGetInvokingMethodName2() {

        String expectedMethodName = "test.jmul.concurrent.threads.ThreadHelperTest.testGetInvokingMethodName2";
        String actualMethodName = ThreadHelper.getInvokingMethodName(NameTypes.CANONICAL_NAME);

        assertEquals(expectedMethodName, actualMethodName);
    }

    /**
     * Tests getting the name of the invoking method.
     */
    @Test
    public void testGetInvokingMethodName3() {

        String expectedMethodName = "testGetInvokingMethodName3";
        String actualMethodName = ThreadHelper.getInvokingMethodName(NameTypes.SIMPLE_NAME);

        assertEquals(expectedMethodName, actualMethodName);
    }

    /**
     * Tests the sleep method with valid parameters.
     */
    @Test
    public void testSleepWithValidParameters() {

        long duration = 500L;

        long before = System.currentTimeMillis();
        ThreadHelper.sleep(duration);
        long after = System.currentTimeMillis();

        long actualDuration = after - before;

        assertTrue(actualDuration >= duration);
    }

    /**
     * Tests the sleep method with valid parameters.
     */
    @Test
    public void testSleepWithValidParameters2() {

        long duration = 0L;

        long before = System.currentTimeMillis();
        ThreadHelper.sleep(duration);
        long after = System.currentTimeMillis();

        long actualDuration = after - before;

        assertTrue(actualDuration >= duration);
    }

    /**
     * Tests the sleep method with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSleepWithInvalidParameters() {

        long duration = -1L;

        ThreadHelper.sleep(duration);
    }

}
