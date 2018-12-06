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


import jmul.concurrent.threads.ThreadHelper;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.TearDownException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;


/**
 * The class tests retrieving threads by name.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class GetThreadNameTest {

    /**
     * The name of a running thread.
     */
    private String name = "test thread";

    /**
     * A handle on the thread (outer).
     */
    private Thread thread;

    /**
     * A handle on the thread (inner).
     */
    private IdlingTestThread threadHandle;

    /**
     * Steps to prepare a test.
     */
    @Before
    public void setUp() {

        threadHandle = new IdlingTestThread();

        thread = new Thread(threadHandle, name);
        thread.start();
    }

    /**
     * Steps to clean up after a test.
     */
    @After
    public void tearDown() {

        threadHandle.stop();

        try {

            thread.join();

        } catch (InterruptedException e) {

            throw new TearDownException(e);
        }
    }

    /**
     * Tests retrieving a thread by name (a correpsonding thread exists).
     */
    @Test
    public void testValidName() {

        Thread t = ThreadHelper.getThreadByName(name);
        assertNotNull("No thread with the expected name exists!", t);

        String actualName = t.getName();
        assertFalse("The thread has no name!", actualName.trim().isEmpty());
        assertEquals(name, actualName);
        assertEquals(thread, t);
    }

    /**
     * Tests retrieving a thread by name (a correpsonding thread doesn't exists).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidName() {

        ThreadHelper.getThreadByName("hello world");
    }

}


