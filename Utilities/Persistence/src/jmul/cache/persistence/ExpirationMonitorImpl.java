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

package jmul.cache.persistence;


import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import jmul.persistence.id.ID;


/**
 * An implementation of an expiration monitor.
 *
 * @author Kristian Kutin
 */
public class ExpirationMonitorImpl implements ExpirationMonitor {

    /**
     * A property key.
     */
    private static final String SLEEP_TIME = "sleepTime";

    /**
     * A property key.
     */
    private static final String EXPIRATION_INTERVAL = "expirationInterval";

    /**
     * After the monitor has checked the expiration for the specified object it
     * is put to sleep for a specified duration.
     */
    private final long sleepTime;

    /**
     * The expiration interval.
     */
    private final long expirationInterval;

    /**
     * The time at which the specified object can be considered as expired.
     */
    private long expirationTime;

    /**
     * The id the monitored object.
     */
    private final ID id;

    /**
     * All known listeners.
     */
    private Collection<ExpirationListener> listeners;

    /**
     * Constructs an expiration monitor.
     *
     * @param anID
     *        the id of the monitored object
     */
    public ExpirationMonitorImpl(ID anID) {

        ResourceBundle bundle =
            ResourceBundle.getBundle(ExpirationMonitor.class.getName());

        sleepTime = Long.parseLong(bundle.getString(SLEEP_TIME));
        expirationInterval =
                1000L * 60L * Long.parseLong(bundle.getString(EXPIRATION_INTERVAL));

        id = anID;

        listeners = new ArrayList<ExpirationListener>();

        setExpirationTime();
    }

    /**
     * The method sets the expiration time.
     */
    private void setExpirationTime() {

        synchronized (this) {

            expirationTime = System.currentTimeMillis() + expirationInterval;
        }
    }

    /**
     * The method checks if the expiration for the specified object is due.
     *
     * @return <code>true</code> if the expiration is due, else
     *         <code>false</code>
     */
    private boolean hasExpired() {

        boolean result = false;

        synchronized (this) {

            result = (System.currentTimeMillis() > expirationTime);
        }

        return result;
    }

    /**
     * The method returns the id of the object for which the expiration is
     * monitored.
     *
     * @return the id of the monitored object
     */
    public ID getMonitoredObjectID() {

        return id;
    }

    /**
     * The method is used to inform the expiration monitor about entities which
     * need to be informed when the expiration is due.
     *
     * @param aListener
     *        an expiration listener
     */
    public void addExpirationListener(ExpirationListener aListener) {

        if (!listeners.contains(aListener)) {

            listeners.add(aListener);
        }
    }

    /**
     * The method is used to delay the expiration.
     */
    public void delayExpiration() {

        setExpirationTime();
    }

    /**
     * The method implements the thread logic.
     */
    public void run() {

        while (!hasExpired()) {

            Thread currentThread = Thread.currentThread();
            
            try {

                Thread.sleep(sleepTime);

            } catch (InterruptedException e) {

                e.printStackTrace();
                currentThread.interrupt();

                // leave the loop and let the monitored object expire                
                break;
            }
        }

        informListeners();
    }

    /**
     * The method informs all listeners about the expiration.
     */
    private void informListeners() {

        for (ExpirationListener listener : listeners) {

            listener.expirationNotification(getMonitoredObjectID());
        }
    }

    /**
     * The method stops the monitor.
     */
    public void stop() {

        synchronized (this) {

            expirationTime = System.currentTimeMillis();
        }
    }

}
