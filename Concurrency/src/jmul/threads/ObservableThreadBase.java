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


import java.util.ArrayList;
import java.util.Collection;


/**
 * A base implementation of an observable thread.
 *
 * @author Kristian Kutin
 */
public abstract class ObservableThreadBase implements ObservableThread {

    /**
     * All listeners of this thread.
     */
    private Collection<ThreadListener> listeners;

    /**
     * The default constructor.
     */
    protected ObservableThreadBase() {

        listeners = new ArrayList<ThreadListener>();
    }

    /**
     * Informs the thread about a listener.
     *
     * @param aListener
     *        a thread listener
     */
    public void addListener(ThreadListener aListener) {

        if (!listeners.contains(aListener)) {

            listeners.add(aListener);
        }
    }

    /**
     * Inform all listeners about the specified event.
     *
     * @param anEvent
     *        an event
     */
    protected void informListeners(ThreadEvent anEvent) {

        for (ThreadListener listener : listeners) {

            listener.informOnEvent(anEvent);
        }
    }

    /**
     * Inform all listeners that this thread has finished.
     */
    protected void finishedThread() {

        ThreadEvent event = new ThreadFinishedEvent(this);
        informListeners(event);
    }

    /**
     * A thread implementation contains some thread specific actions. After the
     * thread specific actions are done all listeners have to be informed.
     */
    public void run() {

        performAction();

        finishedThread();
    }

    /**
     * The actual thread implementation which is to be provided by an
     * implementation class.
     */
    protected abstract void performAction();

}
