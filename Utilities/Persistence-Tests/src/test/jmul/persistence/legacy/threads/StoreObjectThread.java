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

package test.jmul.persistence.legacy.threads;


import jmul.concurrent.threads.ObservableThreadBase;

import jmul.persistence.InvalidRootNodeException;
import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceException;

import jmul.misc.id.ID;

import jmul.string.StringConcatenator;

import jmul.time.Stopwatch;


/**
 * A thread implementation which stores a specified object by calling a
 * persistence manager.
 *
 * @param <T>
 *        the type of object the specified persistence manager handles
 *
 * @author Kristian Kutin
 */
public class StoreObjectThread<T> extends ObservableThreadBase {

    /**
     * The current count of created objects.
     */
    private static ConcurrentCounter objectCount;

    /*
     * The static initializer.
     */
    static {

        objectCount = new ConcurrentCounter();
    }

    /**
     * The object which is to be stored.
     */
    private T object;

    /**
     * The persistence manager which is to be used.
     */
    private PersistenceContainer<T> persistenceManager;

    /**
     * The default constructor.
     */
    public StoreObjectThread() {

        this(null, null);
    }

    /**
     * Constructs a thread and provides all required informations.
     *
     * @param aPersistenceManager
     *        the persistence manager which is to be used
     * @param anObject
     *        the object which is to be stored
     */
    public StoreObjectThread(PersistenceContainer<T> aPersistenceManager, T anObject) {

        super();

        persistenceManager = aPersistenceManager;
        object = anObject;
    }

    /**
     * The actual thread implementation.
     */
    protected void performAction() {

        Stopwatch stopwatch = new Stopwatch();
        ID id = null;

        try {

            stopwatch.startCount();
            id = persistenceManager.store(object);
            stopwatch.stopCount();

        } catch (InvalidRootNodeException e) {

            if (stopwatch.isActiveCount()) {

                stopwatch.stopCount();
            }

            // Ignore this exception.
            StringConcatenator message = new StringConcatenator("Could not store an object! (", stopwatch, ")");
            ConcurrencyHelper.println(message);

        } catch (PersistenceException e) {

            if (stopwatch.isActiveCount()) {

                stopwatch.stopCount();
            }

            // Ignore this exception.
            StringConcatenator message = new StringConcatenator("Could not store an object! (", stopwatch, ")");
            ConcurrencyHelper.println(message);
            return;
        }

        StringConcatenator message =
            new StringConcatenator("Could store the object with the id ", id, "! (", stopwatch, ")");
        ConcurrencyHelper.println(message);

        objectCount.increment();
        message = new StringConcatenator("#", objectCount.value());
        ConcurrencyHelper.println(message);
    }

    /**
     * A setter method.
     *
     * @param object
     *        the object which is to be stored
     */
    public void setObject(T object) {

        this.object = object;
    }

    /**
     * A getter method.
     *
     * @return the object which is to be stored
     */
    public T getObject() {

        return object;
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

/**
 * This is a custom counter class for cuncurrent access.
 *
 * @author Kristian Kutin
 */
class ConcurrentCounter {

    /**
     * A counter.
     */
    private int counter;

    /**
     * The default constructor.
     */
    public ConcurrentCounter() {

        counter = 0;
    }

    /**
     * Increments the counter.
     */
    public void increment() {

        synchronized (this) {

            counter++;
        }
    }

    /**
     * Returns the counter's current value.
     *
     * @return the counter's current value
     */
    public int value() {

        int currentValue = 0;

        synchronized (this) {

            currentValue = counter;
        }

        return currentValue;
    }

}
