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

package test.jmul.persistence.threads;


import jmul.concurrent.threads.ObservableThreadBase;

import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceException;
import jmul.persistence.id.ID;

import jmul.string.StringConcatenator;

import jmul.time.Stopwatch;


/**
 * A thread implementation which retrieves a specified object by calling a
 * persistence manager.
 *
 * @param <T>
 *        the type of object the specified persistence manager handles
 */
public class GetObjectThread<T> extends ObservableThreadBase {

    /**
     * The id of an object which is to be retrieved.
     */
    private ID id;

    /**
     * The persistence manager which is to be used.
     */
    private PersistenceContainer<T> persistenceManager;

    /**
     * The default constructor.
     */
    public GetObjectThread() {

        this(null, null);
    }

    /**
     * Constructs a thread and provides all required informations.
     *
     * @param aPersistenceManager
     *        the persistence manager which is to be used
     * @param anID
     *        the id of an object which is to be retrieved
     */
    public GetObjectThread(PersistenceContainer<T> aPersistenceManager, ID anID) {

        super();

        persistenceManager = aPersistenceManager;
        id = anID;
    }

    /**
     * The actual thread implementation.
     */
    protected void performAction() {

        {
            //TODO
            // For debugging purposes only.

            StringConcatenator message = new StringConcatenator(Thread.currentThread().getName(), " retrieves ", id);
            ConcurrencyHelper.println(message);
        }


        Stopwatch stopwatch = new Stopwatch();
        T object = null;

        try {

            stopwatch.startCount();
            object = persistenceManager.get(id);
            stopwatch.stopCount();

        } catch (PersistenceException e) {

            if (stopwatch.isActiveCount()) {

                stopwatch.stopCount();
            }

            // Ignore this exception.

            StringConcatenator message =
                new StringConcatenator("Could not retrieve an object with the id ", id, "! (", stopwatch, ")");
            ConcurrencyHelper.println(message);
            return;
        }

        StringConcatenator message =
            new StringConcatenator("Could retrieve the object with the id ", id, "! (", stopwatch, ")");
        ConcurrencyHelper.println(message);
    }

    /**
     * A setter method.
     *
     * @param id
     *        the ID of the object which is to be retrieved
     */
    public void setId(ID id) {

        this.id = id;
    }

    /**
     * A getter method.
     *
     * @return the ID of the object which is to be retrieved
     */
    public ID getId() {

        return id;
    }

    /**
     * A setter method.
     *
     * @param persistenceManager
     *        a reference to a persistence manager
     */
    public void setPersistenceManager(PersistenceContainer<T> persistenceManager) {

        this.persistenceManager = persistenceManager;
    }

    /**
     * A getter method.
     *
     * @return a reference to a persistence manager
     */
    public PersistenceContainer<T> getPersistenceManager() {

        return persistenceManager;
    }

}
