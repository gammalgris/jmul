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

package jmul.cache.persistence;


import jmul.persistence.id.ID;


/**
 * This interface describes an entity which monitors a single object and
 * notifies an expiration listener (see {@link ExpirationListener}).
 *
 * @author Kristian Kutin
 */
public interface ExpirationMonitor extends Runnable {

    /**
     * The method returns the id of the object for which the expiration is
     * monitored.
     *
     * @return the id of the monitored object
     */
    ID getMonitoredObjectID();

    /**
     * The method is used to inform the expiration monitor about entities which
     * need to be informed when the expiration is due.
     *
     * @param aListener
     *        an expiration listener
     */
    void addExpirationListener(ExpirationListener aListener);

    /**
     * The method is used to delay the expiration.
     */
    void delayExpiration();

    /**
     * The method stops the monitor.
     */
    void stop();

}
