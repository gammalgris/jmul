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


import java.text.DecimalFormat;

import java.util.HashMap;
import java.util.Map;

import jmul.persistence.id.ID;


/**
 * An implementation of an object cache.
 *
 * @author Kristian Kutin
 */
public class ObjectCacheImpl<T> implements ExpirationListener, ObjectCache<T> {

    /**
     * The name prefix for an expiration monitor.
     */
    private static final String EXPIRATION_MONITOR_NAME_PREFIX = "";

    /**
     * If a new expiration monitor is started this ID will be assigned to it. After assignment this member's value
     * will be increased by 1.
     */
    private static int NEXT_EXPIRATION_MONITOR_ID = 1;

    /**
     * References to all monitors in case the cache needs to be cleaned.
     */
    private Map<ID, ExpirationMonitor> monitors;

    /**
     * All objects according to their ids.
     */
    private Map<ID, T> id2Object;

    /**
     * All ids according to the objects they belong to.
     */
    private Map<T, ID> object2ID;

    /**
     * The default constructor.
     */
    public ObjectCacheImpl() {

        monitors = new HashMap<ID, ExpirationMonitor>();

        id2Object = new HashMap<ID, T>();
        object2ID = new HashMap<T, ID>();
    }

    /**
     * The method checks if this object container knows the specified object.
     *
     * @param anObject
     *        an object
     *
     * @return <code>true</code> if this object container knows the specified
     *         object, else <code>false</code>
     */
    public boolean existsObject(T anObject) {

        boolean result = false;

        synchronized (this) {

            result = object2ID.containsKey(anObject);
        }

        return result;
    }

    /**
     * The method checks if this object container knows the specified object. To
     * avoid ambiguities the delcared type is provided.
     *
     * @param anObject
     *        an object
     * @param aDeclaredType
     *        the declared type of the object
     *
     * @return <code>true</code> if this object container knows the specified
     *         object, else <code>false</code>
     */
    public boolean existsObject(T anObject, Class aDeclaredType) {

        throw new UnsupportedOperationException();
    }

    /**
     * The method checks if the object container knows the specified ID.
     *
     * @param anID
     *        an ID
     *
     * @return <code>true</code> if the object container knows the specified ID,
     *         else <code>false</code>
     */
    public boolean existsID(ID anID) {

        boolean result = false;

        synchronized (this) {

            result = id2Object.containsKey(anID);
        }

        return result;
    }

    /**
     * The method adds an object to this object container and the
     * specified object is assigned the specified ID.
     *
     * The method adds an object and internally an ID is assigned to it.
     *
     * @param anID
     *        the ID of the specified object
     * @param anObject
     *        an object
     *
     * @return the ID which was assigned to the specified object
     */
    public ID addObject(ID anID, T anObject) {

        if (!existsID(anID)) {

            synchronized (this) {

                // Add new entries for the specified object.
                id2Object.put(anID, anObject);
                object2ID.put(anObject, anID);

                // Initialize a new monitor.
                ExpirationMonitor monitor = new ExpirationMonitorImpl(anID);
                monitor.addExpirationListener(this);
                monitors.put(anID, monitor);

                String digits = String.valueOf(NEXT_EXPIRATION_MONITOR_ID);
                String patternString = digits.replaceAll(".", "0");
                DecimalFormat pattern = new DecimalFormat(patternString);

                Thread thread = new Thread(monitor);
                int newID = getNextExpirationMonitorID();
                String newThreadName =
                    EXPIRATION_MONITOR_NAME_PREFIX + pattern.format(newID) + "-" + anObject.getClass().getName() + "@" +
                    anObject.hashCode();
                thread.setName(newThreadName);

                thread.start();
            }

            return anID;
        }


        String message = "The specified object was already added to this object container!";
        throw new IllegalArgumentException(message);
    }

    /**
     * Returns the next ID for an expiration monitor.
     *
     * @return an ID
     */
    private static int getNextExpirationMonitorID() {

        int newID = NEXT_EXPIRATION_MONITOR_ID;
        NEXT_EXPIRATION_MONITOR_ID++;

        return newID;
    }

    /**
     * The method adds an object to this object container and the
     * specified object is assigned the specified ID.
     *
     * The method adds an object and internally an ID is assigned to it.
     *
     * @param anID
     *        the ID of the specified object
     * @param anObject
     *        an object
     * @param aDeclaredType
     *        the declared type of the object
     *
     * @return the ID which was assigned to the specified object
     */
    public ID addObject(ID anID, T anObject, Class aDeclaredType) {

        throw new UnsupportedOperationException();
    }

    /**
     * Every object is assigned an ID. The method returns the object which is
     * associated with the specified ID.
     *
     * @param anID
     *        an ID
     *
     * @return the object which is associated with the specified id or
     *         <code>null</code> if no such object exists
     */
    public T getObject(ID anID) {

        T result = null;

        synchronized (this) {

            result = id2Object.get(anID);
        }

        return result;
    }

    /**
     * Every object is assigned an ID. The method returns the declared type of the
     * object which is associated with the specified ID.
     *
     * @param anID
     *        an ID
     *
     * @return the declared type of the object which is associated with the
     *         specified ID
     */
    public Class getDeclaredType(ID anID) {

        throw new UnsupportedOperationException();
    }

    /**
     * The method returns the ID for the specified object.
     *
     * @param anObject
     *        an object
     * @param aDeclaredType
     *        the declared type of the object
     *
     * @return the ID which was assigned to the specified object or
     *         <code>null</code> if the object container doesn't know the
     *         specified object
     */
    public ID getID(T anObject, Class aDeclaredType) {

        throw new UnsupportedOperationException();
    }

    /**
     * The method returns the id of the specified object
     *
     * @param anObject
     *        an object
     *
     * @return the ID which was assigned to the specified object or
     *         <code>null</code> if the object container doesn't know the
     *         specified object
     */
    public ID getID(T anObject) {

        ID result = null;

        synchronized (this) {

            result = object2ID.get(anObject);
        }

        return result;
    }

    /**
     * With this method the listener is informed about an expiration.
     *
     * @param id
     *        the id of the object which has expired
     */
    public void expirationNotification(ID id) {

        synchronized (this) {

            T object = id2Object.get(id);
            id2Object.remove(id);
            object2ID.remove(object);

            monitors.remove(id);
        }
    }

    /**
     * The method cleans the cache.
     */
    public void clear() {

        synchronized (this) {

            for (ExpirationMonitor monitor : monitors.values()) {

                monitor.stop();
            }
        }
    }

}
