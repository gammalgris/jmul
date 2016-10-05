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


import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;


/**
 * An implementation of a thread pool.<br>
 * <br>
 * <i>Implementation details:<br>
 * A thread (see {@link ThreadPoolImpl1}) takes care that enough threads are
 * instantiated to work on the pending tasks. This thread will check the
 * situation regularly.</i>
 *
 * @author Kristian Kutin
 */
public class ThreadPoolImpl1 implements ThreadPool, ThreadListener {

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
     * The current thread count.
     */
    private int threadCount;

    /**
     * Signalizes that the thread pool should be stooped as soon as possible.
     */
    private boolean pendingStop;

    /**
     * The default constructor.
     */
    public ThreadPoolImpl1() {

        queue = new LinkedList<ObservableThread>();


        ResourceBundle bundle = ResourceBundle.getBundle(ThreadPool.class.getName());

        sleepTime = Long.parseLong(bundle.getString(SLEEP_TIME_KEY));
        maxQueueSize = Integer.parseInt(bundle.getString(MAX_QUEUE_SIZE_KEY));
        maxActiveThreads = Integer.parseInt(bundle.getString(MAX_THREADS_KEY));

        threadCount = 0;

        active = true;
        pendingStop = false;

        (new Thread(new ThreadInvoker())).start();
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

            aThread.addListener(this);
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

        return threadCount;
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
    }

    /**
     * The listener is informed about an event.
     *
     * @param anEvent
     *        a thread event
     */
    @Override
    public void informOnEvent(ThreadEvent anEvent) {

        if (anEvent instanceof ThreadFinishedEvent) {

            threadCount--;
        }
    }

    /**
     * Start a new thread.
     */
    private void startNextThread() {

        synchronized (this) {

            if (threadCount() < getMaximumThreads()) {

                ObservableThread thread = queue.remove();

                (new Thread(thread)).start();
                threadCount++;
            }
        }
    }

    /**
     * Checks if another thread can be started.
     *
     * @return <code>true</code> if another thread can be started, else
     *         <code>false</code>
     */
    private boolean availableThreads() {

        boolean result;

        synchronized (this) {

            result = !queue.isEmpty() && (threadCount() < getMaximumThreads());
        }

        return result;
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

        pendingStop = true;
    }

    /**
     * This inner class implements a thread which regularly checks if new
     * threads are put into the pipeline and starts them.
     */
    class ThreadInvoker implements Runnable {

        /**
         * The number of threads which can be activated at the same time.
         */
        private final int step;

        /**
         * The default constructor.
         */
        public ThreadInvoker() {

            step = getMaximumThreads();
        }

        /**
         * Invoke threads.
         */
        @Override
        public void run() {

            while (active) {

                for (int a = 0; a < step; a++) {

                    if (availableThreads()) {

                        startNextThread();
                    }
                }

                ThreadHelper.sleep(sleepTime);

                if (pendingStop && !availableThreads()) {

                    active = false;
                }
            }
        }

    }

}
