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

package test.jmul.persistence.db4o;


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import java.io.File;

import java.util.Collection;

import jmul.persistence.InvalidIDException;
import jmul.persistence.InvalidRootNodeException;
import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceException;
import jmul.persistence.id.ID;
import jmul.persistence.id.IDGenerator;
import jmul.persistence.id.StringIDGenerator;

import jmul.string.StringConcatenator;

import jmul.xml.query.XPathQuery;


/**
 * An implementation of a persistence manager which relies on db4o as back
 * end.<br>
 * <br>
 * <i>Not:<br>
 * The main purpose of this additional implementation of a persistence manager
 * is for performance comparison.</i>
 * <br>
 * More Informations:
 * <ul>
 *   <li><a href="http://stackoverflow.com/questions/5423843/how-does-db4o-support-concurrency-and-transactions">DB4O Thread Safety #1</a></li>
 *   <li><a href="http://stackoverflow.com/questions/7712196/db4o-thread-safety-embedded-server-transactions-general-questions">DB4O Thread Safety #2</a></li>
 * </ul>
 *
 * @author Kristian Kutin
 */
public class DB4OPersistenceManagerImpl<T> implements PersistenceContainer<T> {

    /**
     * The file separator for this operating system.
     */
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * The name of the storage file.
     */
    private static final String STORAGE_FILENAME = "db4o.yap";

    /**
     * A base directory.
     */
    private final String baseDirectory;

    /**
     * The expected type.
     */
    private final Class<T> expectedType;

    /**
     * An id generator.
     */
    private IDGenerator idGenerator;

    /**
     * The actual db4o object container.
     */
    private ObjectContainer container;

    /**
     * Constructs a persistence manager.
     *
     * @param aType
     *        this persistence manager will only handle objects of this
     *        specified type
     * @param aBaseDirectory
     *        the base directory where data is stored
     */
    public DB4OPersistenceManagerImpl(Class<T> aType, String aBaseDirectory) {

        expectedType = aType;

        baseDirectory = aBaseDirectory + FILE_SEPARATOR + expectedType.getName();

        // Prepare the directory if it doesn't exist yet.
        File directory = new File(baseDirectory);
        directory.mkdirs();

        String idFile = baseDirectory + FILE_SEPARATOR + expectedType.getName();
        idGenerator = StringIDGenerator.getAlternativeGenerator(idFile);

        container = Db4oEmbedded.openFile(baseDirectory + FILE_SEPARATOR + STORAGE_FILENAME);
    }

    /**
     * The method stores the specified object.
     *
     * @param anObject
     *        the object which is to be persisted
     *
     * @return the ID which was assigned to the specified object upon
     *         persisting
     *
     * @throws InvalidRootNodeException
     *         the exception is thrown if the specified object couldn't be
     *         identified as a valid root node
     * @throws PersistenceException
     *         the exception is thrown if storing the object was not possible
     */
    public ID store(T anObject) throws InvalidRootNodeException, PersistenceException {

        // Check some plausibilities first.

        if (anObject == null) {

            String message = "No valid object has been specified (null)!";
            throw new IllegalArgumentException(message);
        }

        Class foundType = anObject.getClass();

        if (!expectedType.isInstance(anObject)) {

            StringConcatenator message =
                new StringConcatenator("Invalid object (expected:" + expectedType + " / found:" + foundType + ")!");
            throw new PersistenceException(message.toString());
        }


        Predicate<DB4OEntry> query = new EntryByObjectQuery(anObject);
        ObjectSet<DB4OEntry> result = null;

        //synchronized (this) { //TODO query unsynched for testing

        result = container.query(query);
        //}


        if (result.size() > 0) {

            String message = "The object was already stored. Use commit to store the object's current state!";
            throw new PersistenceException(message);
        }


        // Get the next available ID. Under normal circumstances this case
        // should not occur since all IDs are supposed to be unique and the next
        // available ID is persistently stored in a file.

        ID id = null;

        synchronized (idGenerator) {

            id = idGenerator.generateID();
        }


        // Associate the specified object with an ID

        DB4OEntry newEntry = new DB4OEntry(id, anObject);


        // Store the object now.

        synchronized (this) {

            container.store(newEntry);
            container.commit();
        }

        return id;
    }

    /**
     * The method stores the specified object and all changes which have been
     * made since the object was last stored (see {@link #store}) or loaded (see
     * {@link #get}).
     *
     * @param anObject
     *        the object which is to be persisted
     *
     * @return the ID which was assigned to the specified object upon
     *         persisting
     *
     * @throws InvalidRootNodeException
     *         the exception is thrown if the specified object couldn't be
     *         identified as a valid root node
     * @throws PersistenceException
     *         the exception is thrown if storing the object was not possible
     */
    public ID commit(T anObject) throws InvalidRootNodeException, PersistenceException {

        // Check some plausibilities first.

        if (anObject == null) {

            String message = "No valid object has been specified (null)!";
            throw new IllegalArgumentException(message);
        }

        Class foundType = anObject.getClass();

        if (!expectedType.isInstance(anObject)) {

            StringConcatenator message =
                new StringConcatenator("Invalid object (expected:" + expectedType + " / found:" + foundType + ")!");
            throw new PersistenceException(message.toString());
        }


        Predicate<DB4OEntry> query = new EntryByObjectQuery(anObject);
        ObjectSet<DB4OEntry> result = null;

        //synchronized (this) { //TODO query unsynched for testing

        result = container.query(query);
        //}


        if (result.size() == 0) {

            String message = "An unknown object is being committed. Store the object first!";
            throw new PersistenceException(message);

        } else if (result.size() > 1) {

            String message = "Too many object which equal the specified object have been found!";
            throw new PersistenceException(message);
        }


        // Retrieve the object's ID first.

        DB4OEntry entry = result.get(0);
        container.activate(entry, 100);
        ID id = entry.getId();

        DB4OEntry newEntry = new DB4OEntry(id, anObject);

        synchronized (this) {

            container.delete(entry);
            container.store(newEntry);
            container.commit();
        }

        return id;
    }

    /**
     * The method stores the specified object and all changes which have been
     * made since the object was last stored (see {@link #store}) or loaded (see
     * {@link #get}).<br>
     * <br>
     * <i>The standard aproach when using this persistence layer is to use
     * {@link #get} in order to load a persisted object. Then the object can be
     * modified. After the modifications are done {@link #commit(Object)} should
     * be called to persist the changes. This scenario will only work if the
     * modifications are done within a certain time frame. Otherwise
     * {@link #commit(Object)} will throw an exception.<br>
     * To circumvent this issues this alternate method is provided which
     * requires the caller to keep track of objects and their associated
     * IDs.</i>
     *
     * @param anID
     *        the ID of a persisted object
     * @param anObject
     *        the object which is to be persisted
     *
     * @return the ID which was assigned to the specified object upon
     *         persisting
     *
     * @throws InvalidRootNodeException
     *         the exception is thrown if the specified object couldn't be
     *         identified as a valid root node
     * @throws PersistenceException
     *         the exception is thrown if storing the object was not possible
     */
    public ID commit(ID anID, T anObject) throws InvalidRootNodeException, PersistenceException {

        throw new UnsupportedOperationException("A commit(ID,T) is not supported!");
    }

    /**
     * The method deletes a persisted object. The specified object may still
     * reside in memory but is no longer persisted. The specified ID is useless
     * thereafter.
     *
     * @param anID
     *        the ID of a persisted object
     *
     * @throws InvalidIDException
     *         the exception is thrown if the specified ID is invalid
     * @throws PersistenceException
     *         the exception is thrown if an error occurs while deleting
     *         persisted informations
     */
    public void delete(ID anID) throws InvalidIDException, PersistenceException {

        // Check some plausibilities first.

        if (anID == null) {

            String message = "No valid ID has been specified (null)!";
            throw new InvalidIDException(message);
        }


        // Search all entries.

        Predicate<DB4OEntry> query = new EntryByIDQuery(anID);
        ObjectSet<DB4OEntry> result = null;

        // synchronized (this) { //TODO query unsynched for testing

        result = container.query(query);
        //}


        if (result.size() == 0) {

            String message = "No object was stored with the specified ID. Cannot proceed with the delete operation!";
            throw new PersistenceException(message);

        } else if (result.size() > 1) {

            String message = "Too many object which equal the specified object have been found!";
            throw new PersistenceException(message);
        }

        DB4OEntry entry = result.get(0);
        Object object = entry.getObject();
        Class foundType = object.getClass();

        if (!expectedType.isInstance(object)) {

            StringConcatenator message =
                new StringConcatenator("Invalid object (expected:" + expectedType + " / found:" + foundType + ")!");
            throw new PersistenceException(message.toString());
        }


        // Delete the file if it exists.

        synchronized (this) {

            container.delete(entry);
        }
    }

    /**
     * The method returns the object which is identified by the specified ID.
     *
     * @param anID
     *        the ID of a persisted object
     *
     * @return the object which is identified by the specified ID or
     *         <code>null</code> if no such object exists
     *
     * @throws PersistenceException
     *         the exception is thrown if an error occurs while retrieving the
     *         persisted object
     */
    public T get(ID anID) throws PersistenceException {

        // Check some plausibilities first.

        if (anID == null) {

            String message = "No valid ID has been specified (null)!";
            throw new PersistenceException(message);
        }


        // Search all entries.

        Predicate<DB4OEntry> query = new EntryByIDQuery(anID);
        ObjectSet<DB4OEntry> result = null;

        // synchronized (this) { //TODO query unsynched for testing

        result = container.query(query);
        //}


        if (result.size() == 0) {

            String message = "No object was stored with the specified ID. Cannot proceed with the delete operation!";
            throw new PersistenceException(message);

        } else if (result.size() > 1) {

            String message = "Too many object which equal the specified object have been found!";
            throw new PersistenceException(message);
        }

        DB4OEntry entry = result.get(0);
        Object object = entry.getObject();
        Class foundType = object.getClass();

        if (!expectedType.isInstance(object)) {

            StringConcatenator message =
                new StringConcatenator("Invalid object (expected:" + expectedType + " / found:" + foundType + ")!");
            throw new PersistenceException(message.toString());
        }


        return expectedType.cast(object);
    }

    /**
     * Prepares the persistence manager for shutdown (i.e. running threads will
     * be stopped).
     */
    public void shutdown() {

        container.close();
    }

    /**
     * This query operation is not implemented!
     *
     * @param someQueries
     *
     * @return
     */
    @Override
    public Collection<ID> findByXpathExpression(XPathQuery[] someQueries) {

        throw new UnsupportedOperationException();
    }

}
