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

package jmul.transformation.xml.cache;


import java.util.HashMap;
import java.util.Map;

import jmul.misc.id.ID;
import jmul.misc.id.IDGenerator;

import jmul.transformation.xml.id.IntegerIDGenerator;


/**
 * An implementation of an object container.
 *
 * @author Kristian Kutin
 */
public class Object2XmlCacheImpl implements Object2XmlCache {

    /**
     * Manages and creates IDs.
     */
    private IDGenerator idGenerator;

    /**
     * This container manages IDs and their associated objects.
     */
    private Map<ID, CacheEntry> entries;

    /**
     * This container amanages objects and their assigned IDs.
     */
    private Map<CacheEntry, ID> objects;

    /**
     * The default constructor.
     */
    public Object2XmlCacheImpl() {

        idGenerator = new IntegerIDGenerator();

        entries = new HashMap<ID, CacheEntry>();
        objects = new HashMap<CacheEntry, ID>();
    }

    /**
     * The method checks if the specified object was already added. To avoid
     * ambiguities the delcared type is provided.
     *
     * @param anEntry
     *        a cache entry
     *
     * @return <code>true</code> if the object was already added, else
     *         <code>false</code>
     */
    private boolean existsObject(CacheEntry anEntry) {

        return objects.containsKey(anEntry);
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
    @Override
    public boolean existsObject(Object anObject) {

        throw new UnsupportedOperationException();
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
    @Override
    public boolean existsObject(Object anObject, Class aDeclaredType) {

        return existsObject(new CacheEntry(anObject, aDeclaredType));
    }

    /**
     * Adds an entry.
     *
     * @param anObject
     *        an object
     * @param aDeclaredType
     *        the declared type of the object
     *
     * @return the ID which was assigned to the specified object
     */
    private ID addEntry(Object anObject, Class aDeclaredType) {

        return addEntry(new CacheEntry(anObject, aDeclaredType));
    }

    /**
     * The method adds an object and assigns an ID to it.
     *
     * @param anEntry
     *        a cache entry
     *
     * @return the ID which was assigned to the specified object
     */
    private ID addEntry(CacheEntry anEntry) {

        if (existsObject(anEntry)) {

            String message = "Duplicate entries are not allowed!";
            throw new IllegalArgumentException(message);
        }


        ID id = idGenerator.generateID();

        entries.put(id, anEntry);
        objects.put(anEntry, id);

        return id;
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
    public ID addObject(ID anID, Object anObject) {

        throw new UnsupportedOperationException();
    }

    /**
     * The method adds an object to this object container and internally the
     * specified object is assigned an ID.
     *
     * @param anObject
     *        an object
     * @param aDeclaredType
     *        the declared type of the object
     *
     * @return the ID which was assigned to the specified object
     */
    @Override
    public ID addObject(Object anObject, Class aDeclaredType) {

        return addEntry(anObject, aDeclaredType);
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
    @Override
    public boolean existsID(ID anID) {

        return entries.containsKey(anID);
    }

    /**
     * The method returns the ID for the specified object.
     *
     * @param anEntry
     *        a cache entry
     *
     * @return the ID which was assigned to the specified object
     */
    private ID getID(CacheEntry anEntry) {

        if (!existsObject(anEntry)) {

            String message = "The specified object is unknown!";
            throw new IllegalArgumentException(message);
        }

        return objects.get(anEntry);
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
    @Override
    public ID getID(Object anObject, Class aDeclaredType) {

        return getID(new CacheEntry(anObject, aDeclaredType));
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
    @Override
    public ID getID(Object anObject) {

        throw new UnsupportedOperationException();
    }

    /**
     * Every object is assigned an ID. The method returns the object which is
     * associated with the specified ID.
     *
     * @param anID
     *        an ID
     *
     * @return the object which is associated with the specified ID
     */
    @Override
    public Object getObject(ID anID) {

        if (!existsID(anID)) {

            String message = "An invalid ID was specified!";
            throw new IllegalArgumentException(message);
        }


        return entries.get(anID).getObject();
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
    @Override
    public Class getDeclaredType(ID anID) {

        if (!existsID(anID)) {

            String message = "An invalid ID was specified!";
            throw new IllegalArgumentException(message);
        }


        return entries.get(anID).getDeclaredType();
    }

}
