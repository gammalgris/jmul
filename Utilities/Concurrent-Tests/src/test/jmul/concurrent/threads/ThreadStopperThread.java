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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.concurrent.threads;


import jmul.concurrent.threads.StoppableRunnable;
import jmul.concurrent.threads.ThreadHelper;


/**
 * This class is an implementation of a thread which will send a stop signal to another
 * thread after s specified amount of time.
 *
 * @author Kristian Kutin
 */
public class ThreadStopperThread implements Runnable {

    /**
     * A handle on another thread.
     */
    private StoppableRunnable handle;

    /**
     * A sleep time.
     */
    private long sleepTime;

    /**
     * Creates a new thread according to the specified parameters.
     *
     * @param aHandle
     *        a handle on another thread
     * @param aSleepTime
     *        a sleep time
     */
    public ThreadStopperThread(StoppableRunnable aHandle, long aSleepTime) {

        handle = aHandle;
        sleepTime = aSleepTime;
    }

    /**
     * The actual thread body. Waits for a specified amount of time and sends a stop
     * signal to a specified thread.
     */
    @Override
    public void run() {

        ThreadHelper.sleep(sleepTime);

        handle.stop();
    }

}
