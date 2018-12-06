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
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;


@UnitTest
public class JoinThreadTest {

    /**
     * A handle on the thread (outer).
     */
    private Thread thread;

    /**
     * A handle on the thread (inner).
     */
    private IdlingTestThread threadHandle;

    /**
     * A handle on the thread (outer).
     */
    private Thread stopperThread;

    /**
     * Steps to prepare a test.
     */
    @Before
    public void setUp() {

        threadHandle = new IdlingTestThread(2000L);
        thread = new Thread(threadHandle);

        stopperThread = new Thread(new ThreadStopperThread(threadHandle, 2500L));

        thread.start();
        stopperThread.start();
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
     * Tests waiting for a thread to end.
     */
    @Test
    public void testJoin() {

        ThreadHelper.waitForThreadToEnd(thread);
        assertFalse("The thread is still alive!", thread.isAlive());
    }

}
