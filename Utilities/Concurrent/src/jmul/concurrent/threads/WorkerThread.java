/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package jmul.concurrent.threads;


import java.util.Queue;


/**
 * An implementation of a worker thread. This worker regularly checks if a queue
 * contains tasks which have to be done, takes a remaining tasks, executes it
 * and then checks again for more tasks.
 *
 * @author Kristian Kutin
 */
public class WorkerThread implements Runnable {

    /**
     * A sleep time.
     */
    private static final long SLEEP_TIME = 20L;

    /**
     * A reference to a queue which contains pending tasks (i.e. tasks which
     * have to be executed). The queue must support concurrent access.
     */
    private Queue<ObservableThread> pendingTasks;

    /**
     * A flag which indicates that this worker thread will stop execution.
     */
    private boolean stop;

    /**
     * Constructs a worjer thread.
     *
     * @param aTaskQueue
     *        a reference to a queue which contains pending tasks
     */
    public WorkerThread(Queue<ObservableThread> aTaskQueue) {

        if (aTaskQueue == null) {

            String message = "No task queue has been specified!";
            throw new IllegalArgumentException(message);
        }

        pendingTasks = aTaskQueue;
        stop = false;
    }

    /**
     * Stops this worker thread as soon as possible.
     */
    public void stop() {

        synchronized (this) {

            stop = true;
        }
    }

    /**
     * Checks if this worker thread has been signalized to stop as soon as
     * possible.
     *
     * @return <code>true</code> if this thread has been signalized to stop,
     *         else <code>false</code>
     */
    public boolean isNotStopped() {

        boolean result = false;

        synchronized (this) {

            result = !stop;
        }

        return result;
    }

    /**
     * The actual worker algorithm.
     */
    public void run() {

        while (isNotStopped()) {

            ObservableThread task = null;

            while (isNotStopped() && (task == null)) {

                task = pendingTasks.poll();

                ThreadHelper.sleep(SLEEP_TIME);
            }

            if (task != null) {

                task.run();
            }
        }
    }

}
