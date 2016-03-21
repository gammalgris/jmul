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

package jmul.threads;


/**
 * This interface describes a thread pool.<br>
 * <br>
 * Some additional informations:<br>
 * <ul>
 *   <li><a href="http://stackoverflow.com/questions/6592976/difference-between-thread-and-threadpool">StackOverflow</a></li>
 * </ul>
 *
 * @author Kristian Kutin
 */
public interface ThreadPool {

    /**
     * Adds a thread to this thread pool. The thread is not started immediately.
     * Only if the threadpool has free capacity it is started.
     *
     * @param aThread
     *        an observable thread
     */
    void addThread(ObservableThread aThread);

    /**
     * The currently active threads.
     *
     * @return the number of active threads
     */
    int threadCount();

    /**
     * The maximum number of allowed threads.
     *
     * @return maximum threads
     */
    int getMaximumThreads();

    /**
     * Sets the maximum number of allowed threads. The threadpool will not adapt
     * immediately but after an unspecified time.
     *
     * @param aMaximum
     *        maximum threads
     */
    void setMaximumThreads(int aMaximum);

    /**
     * The current maximum queue size.
     *
     * @return the current maximum queue size
     */
    int getMaximumQueueSize();

    /**
     * Sets the current maximum queue size.
     *
     * @param aMaximum
     *        the current maximum queue size
     */
    void setMaximumQueueSize(int aMaximum);

    /**
     * Stops the thread pool (i.e. no new threads are accepted by the add method
     * and what remains in the pipeline will be executed).
     */
    void stop();

}
