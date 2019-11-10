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
 * The class is a simple thread implementation for testing purposes.
 *
 * @author Kristian Kutin
 */
public class IdlingTestThread implements StoppableRunnable {

    /**
     * The duration of a cycle.
     */
    private final long sleepTime;

    /**
     * A flag which indicates that the thread should stop or not.
     */
    private volatile boolean stop;

    /**
     * The default constructor.
     */
    public IdlingTestThread() {

        this(100L);
    }

    /**
     * Creates a new thread with the specified sleep time.
     *
     * @param aSleepTime
     *        the duration of a cycle
     */
    public IdlingTestThread(long aSleepTime) {

        sleepTime = aSleepTime;
        stop = false;
    }

    /**
     * The actual thread body. In regular cycles the tread checks if it can be
     * stopped or not.
     */
    @Override
    public void run() {

        while (true) {

            ThreadHelper.sleep(sleepTime);

            if (stop) {

                break;
            }
        }
    }

    /**
     * Invoking this method is sending a stop signal which will stop the thread
     * after the current cycle ends. This method is usually invoked from another thread.
     */
    @Override
    public void stop() {

        synchronized (this) {

            stop = true;
        }
    }

}
