/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
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


import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import jmul.math.MathHelper;


/**
 * An implementation of a thread pool.<br>
 * <br>
 * <i>Implementation details:<br>
 * The thread will instantiate several worker threads (see
 * {@link jmul.concurrent.threads.WorkerThread}) which regularly check the queue of pending tasks, take
 * tasks and execute tasks on their own.</i>
 *
 * @author Kristian Kutin
 *
 * @deprecated Rework the treadpool, worker threads and observable threads.
 */
@Deprecated
public class ThreadPoolImpl2 implements ThreadPool {

    /**
     * The name prefix for worker threads.
     */
    private static final String WORKER_THREAD_NAME_PREFIX = "worker-thread-";

    /**
     * If a new worker thread is started this ID will be assigned to it. After assignment this member's value will
     * be increased by 1.
     */
    private static int nextWorkerThreadId = 1;

    /**
     * A property key.
     */
    private static final String SLEEP_TIME_KEY = "sleepTime";

    /**
     * A property key.
     */
    private static final String MAX_THREADS_KEY = "maxThreads";

    /**
     * A property key.
     */
    private static final String MAX_QUEUE_SIZE_KEY = "maxQueueSize";

    /**
     * A sleep time.
     */
    private final long sleepTime;

    /**
     * Contains all threads which have yet to be executed.
     */
    private Queue<ObservableThread> queue;

    /**
     * References to all worker threads.
     */
    private Collection<WorkerThread> workerThreads;

    /**
     * A maximum stack size.
     */
    private int maxQueueSize;

    /**
     * The maximum number of allowed threads.
     */
    private int maxActiveThreads;

    /**
     * Status of this thread pool (active = true, finished = false).
     */
    private boolean active;

    /**
     * The default constructor.
     */
    public ThreadPoolImpl2() {

        ResourceBundle bundle = ResourceBundle.getBundle(ThreadPool.class.getName());

        sleepTime = Long.parseLong(bundle.getString(SLEEP_TIME_KEY));
        maxQueueSize = Integer.parseInt(bundle.getString(MAX_QUEUE_SIZE_KEY));
        maxActiveThreads = Integer.parseInt(bundle.getString(MAX_THREADS_KEY));

        active = true;

        queue = new ConcurrentLinkedQueue<ObservableThread>();
        workerThreads = new ArrayList<WorkerThread>();

        updateThreadCapacity();
    }

    /**
     * This thread will update the number of running threads.
     */
    private void updateThreadCapacity() {

        if (active) {

            int activeThreads = workerThreads.size();
            int delta = maxActiveThreads - activeThreads;

            if (delta < 0) {

                // Too many threads are running. Stop some.

                Collection<WorkerThread> stoppedWorkers = new ArrayList<WorkerThread>();
                Iterator<WorkerThread> i = workerThreads.iterator();

                for (int a = 0; a < Math.abs(delta); a++) {

                    WorkerThread next = i.next();
                    next.stop();
                    stoppedWorkers.add(next);
                }

                workerThreads.removeAll(stoppedWorkers);

            } else if (delta > 0) {

                String digits = String.valueOf(MathHelper.max(nextWorkerThreadId, activeThreads, delta));
                String patternString = digits.replaceAll(".", "0");
                DecimalFormat pattern = new DecimalFormat(patternString);

                // Too few threads are running. Start some.

                for (int a = 0; a < delta; a++) {

                    WorkerThread workerThread = new WorkerThread(queue);
                    workerThreads.add(workerThread);

                    Thread thread = new Thread(workerThread);

                    int newID = getNextWorkerThreadID();
                    String newThreadName = WORKER_THREAD_NAME_PREFIX + pattern.format(newID);
                    thread.setName(newThreadName);

                    thread.start();
                }
            }
        }
    }

    /**
     * Returns the next ID for a worker thread.
     *
     * @return an ID
     */
    private static int getNextWorkerThreadID() {

        int newID = nextWorkerThreadId;
        nextWorkerThreadId++;

        return newID;
    }

    /**
     * Adds a thread to this thread pool. The thread is not started immediately.
     * Only if the threadpool has free capacity it is started.
     *
     * @param aThread
     *        an observable thread
     */
    @Override
    public void addThread(ObservableThread aThread) {

        boolean loop = true;

        while (loop) {

            int stackSize;

            synchronized (this) {

                stackSize = queue.size();
            }

            loop = stackSize > maxQueueSize;

            if (loop) {

                ThreadHelper.sleep(sleepTime);
            }
        }

        synchronized (this) {

            queue.add(aThread);
        }
    }

    /**
     * The currently active threads.
     *
     * @return the number of active threads
     */
    @Override
    public int threadCount() {

        throw new UnsupportedOperationException();
    }

    /**
     * The maximum number of allowed threads.
     *
     * @return maximum threads
     */
    @Override
    public int getMaximumThreads() {

        return maxActiveThreads;
    }

    /**
     * Sets the maximum number of allowed threads. The threadpool will not adapt
     * immediately but after an unspecified time.
     *
     * @param aMaximum
     *        maximum threads
     */
    @Override
    public void setMaximumThreads(int aMaximum) {

        maxActiveThreads = aMaximum;
        updateThreadCapacity();
    }

    /**
     * The current maximum queue size.
     *
     * @return the current maximum queue size
     */
    @Override
    public int getMaximumQueueSize() {

        return maxQueueSize;
    }

    /**
     * Sets the current maximum queue size.
     *
     * @param aMaximum
     *        the current maximum queue size
     */
    @Override
    public void setMaximumQueueSize(int aMaximum) {

        maxQueueSize = aMaximum;
    }

    /**
     * Stops the thread pool (i.e. no new threads are accepted by the add method
     * and what remains in the pipeline will be executed).
     */
    @Override
    public void stop() {

        active = false;

        for (WorkerThread thread : workerThreads) {

            thread.stop();
        }

        workerThreads.clear();
    }

}
