/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.persistence;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.io.FileDeletionHelper;
import jmul.io.deserialization.Deserializer;
import jmul.io.serialization.Serializer;

import jmul.misc.id.ID;
import jmul.misc.id.IDGenerator;
import jmul.misc.id.StringID;

import jmul.persistence.cache.ObjectCache;
import jmul.persistence.cache.ObjectCacheImpl;
import jmul.persistence.file.FileManager;
import jmul.persistence.file.FileManagerImpl;
import jmul.persistence.id.StringIDGenerator;
import jmul.persistence.xml.XmlDeserializerImpl;
import jmul.persistence.xml.XmlSerializerImpl;

import jmul.string.TextHelper;

import jmul.transformation.xml.annotations.RootNode;

import jmul.xml.query.XPathQuery;


/**
 * An implementation of a persistence manager.<br>
 * <br>
 * Some implementation details:<br>
 * <ul>
 *   <li>The persistance manager only handles one type of objects.</li>
 *   <li>The persistence manager stores all persisted objects within a specified
 *     directory.</li>
 *   <li>Every persisted object is saved in an xml file.</li>
 *   <li>The id of a persisted object is used as filename</li>
 * </ul>
 * Thus searching, loading and storing specific objects is partly delegated to
 * the filesystem.<br>
 * <br>
 * <i>More informations:</i><br>
 * <ul>
 *   <li><i><a href="http://stackoverflow.com/questions/1239918/what-is-the-optimal-number-of-threads-for-performing-io-operations-in-java">StackOverflow</a></i></li>
 * </ul>
 *
 * @param <T>
 *        the object type this persistance manager handles
 *
 * @author Kristian Kutin
 */
public class PersistenceContainerImpl<T> implements PersistenceContainer<T> {

    /**
     * The file separator for this operating system.
     */
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * The expected type.
     */
    private final Class<T> expectedType;

    /**
     * An id generator.
     */
    private IDGenerator idGenerator;

    /**
     * A cache containing loaded objects.
     */
    private ObjectCache<T> cache;

    /**
     * The file manager handles where an object will be stored.
     */
    private FileManager fileManager;

    /**
     * Constructs a persistence manager.
     *
     * @param aType
     *        this persistence manager will only handle objects of this
     *        specified type
     * @param aBaseDirectory
     *        the base directory where data is stored
     */
    public PersistenceContainerImpl(Class<T> aType, String aBaseDirectory) {

        expectedType = aType;

        String baseDirectory = aBaseDirectory + FILE_SEPARATOR + expectedType.getName();

        // Prepare the directory if it doesn't exist yet.
        File directory = new File(baseDirectory);
        directory.mkdirs();

        String idFile = baseDirectory + FILE_SEPARATOR + expectedType.getName();
        idGenerator = StringIDGenerator.getAlternativeGenerator(idFile);

        cache = new ObjectCacheImpl<>();

        fileManager = new FileManagerImpl(baseDirectory);
    }

    /**
     * The method stores the specified object.
     *
     * @param anObject
     *        the object which is to be persisted
     *
     * @return the ID which was assigned to the specified object upon
     *         persisiting
     *
     * @throws InvalidRootNodeException
     *         the exception is thrown if the specified object couldn't be
     *         identified as a valid root node
     * @throws PersistenceException
     *         the exception is thrown if storing the object was not possible
     */
    @Override
    public ID store(T anObject) throws InvalidRootNodeException, PersistenceException {

        checkParameter(anObject);


        if (cache.existsObject(anObject)) {

            String message = "The object was already stored. Use commit to store the object's current state!";
            throw new PersistenceException(message);
        }


        // Get the next available ID. Under normal circumstances this case
        // should not occur since all IDs are supposed to be unique and the next
        // available ID is persistently stored in a file.

        ID id = null;
        boolean loop = true;

        while (loop) {

            synchronized (idGenerator) {

                id = idGenerator.generateID();
            }

            loop = fileManager.existsFile(id.toString());
        }


        // Associate the specified object with an ID

        cache.addObject(id, anObject);


        // Determine the name of the file which will contain the serialized
        // object.

        File file;

        synchronized (this) {

            file = fileManager.newFile(id.toString());
        }


        // Serialize the object now.

        Serializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(file, anObject);

        } catch (IOException e) {

            throw createUnsuccessfulSerializationException(e, anObject, file);
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
    @Override
    public ID commit(T anObject) throws InvalidRootNodeException, PersistenceException {

        checkParameter(anObject);


        if (!cache.existsObject(anObject)) {

            String message = "The specified object must be retrieved first before it can be committed!";
            throw new PersistenceException(message);
        }


        // Retrieve the object's ID first.

        ID id = cache.getID(anObject);


        // Serialize the object now.

        File file = fileManager.getFile(id.toString());
        Serializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(file, anObject);

        } catch (IOException e) {

            throw createUnsuccessfulSerializationException(e, anObject, file);
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
    @Override
    public ID commit(ID anID, T anObject) throws InvalidRootNodeException, PersistenceException {

        checkParameter(anObject);


        // Serialize the object now.

        File file = fileManager.getFile(anID.toString());
        Serializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(file, anObject);

        } catch (IOException e) {

            throw createUnsuccessfulSerializationException(e, anObject, file);
        }


        return anID;
    }

    /**
     * The method deletes a persisted object. The specified object still resides
     * in memory but is no longer stored within the persistence layer.
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
    @Override
    public void delete(ID anID) throws InvalidIDException, PersistenceException {

        // Check some plausibilities first.

        if (anID == null) {

            String message = "No valid ID has been specified (null)!";
            throw new InvalidIDException(message);
        }


        // Delete the file if it exists.

        if (fileManager.existsFile(anID.toString())) {

            // It may happen that a concurring deletion already deleted the file.

            File file = fileManager.getFile(anID.toString());
            FileDeletionHelper.delete(file);
        }


        // Delete the object from the cache

        if (cache.existsID(anID)) {

            // It may happen that a concurring deletion already cleaned the cache.

            cache.removeObject(anID);
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
    @Override
    public T get(ID anID) throws PersistenceException {

        // Check some plausibilities first.

        if (anID == null) {

            String message = "No valid ID has been specified (null)!";
            throw new IllegalArgumentException(message);
        }


        // Check if the object which is associated with the specified ID resides
        // in memory already (i.e. known to cache).

        if (cache.existsID(anID)) {

            return cache.getObject(anID);
        }


        // If it doesn't reside in memory then it must be loaded from disk.

        if (!fileManager.existsFile(anID.toString())) {

            String message = "There is not object associated with the specified ID!";
            throw new PersistenceException(message);
        }


        // Deserialize the object now.

        File file = fileManager.getFile(anID.toString());
        Deserializer deserializer = new XmlDeserializerImpl();
        T restoredObject = null;

        try {

            restoredObject = (T) deserializer.deserialize(file);

        } catch (IOException e) {

            String message =
                TextHelper.concatenateStrings("Deserialization of an object (", file.getName(),
                                              " -> X) was not successful!");
            throw new PersistenceException(message, e);
        }


        // Associate the specified object with an ID. Consider that another
        // thread might have already added the same object.

        if (!cache.existsID(anID)) {

            cache.addObject(anID, restoredObject);
        }


        return restoredObject;
    }

    /**
     * Prepares the persistence manager for shutdown (i.e. running threads will
     * be stopped).
     */
    @Override
    public void shutdown() {

        fileManager.shutdown();
        cache.clear();
    }

    /**
     * The method applies an XPath expression on all datafiles of persisted
     * objects and checks if the result matches the expected value. If there are
     * no matches then the list is empty.
     *
     * @param someQueries
     *        one or more XPath queries
     *
     * @return a list of IDs of objects which match the specified criteria
     */
    @Override
    public Collection<ID> findByXpathExpression(XPathQuery... someQueries) {

        if (someQueries.length == 0) {

            String message = "No XPath queries have been specified!";
            throw new IllegalArgumentException(message);
        }

        Collection<File> foundFiles = fileManager.findFiles(someQueries);


        Collection<ID> results = new ArrayList<>();

        for (File file : foundFiles) {

            String uniqueIdentifier;

            try {

                uniqueIdentifier = fileManager.getUniqueIdentifier(file);

            } catch (IOException e) {

                // Throwing this exception is not possible. Actually the query
                // and all associated exceptions shuld be logged.

                // TODO
                // Log the query and all associated exceptions.

                continue;
            }

            if (uniqueIdentifier == null) {

                // TODO
                // Log the query and all files without a unique identifier.

                continue;
            }

            ID id = new StringID(uniqueIdentifier);

            results.add(id);
        }

        return results;
    }

    /**
     * Checks the specified parameters.
     *
     * @param anObject
     */
    private void checkParameter(T anObject) throws PersistenceException, InvalidRootNodeException {

        if (anObject == null) {

            String message = "No valid object has been specified (null)!";
            throw new IllegalArgumentException(message);
        }

        Class foundType = anObject.getClass();

        if (!expectedType.isInstance(anObject)) {

            String message =
                TextHelper.concatenateStrings("Invalid object (expected:" + expectedType + " / found:" + foundType +
                                              ")!");
            throw new PersistenceException(message);
        }

        if (!foundType.isAnnotationPresent(RootNode.class)) {

            String message = "The specified object is not marked as root node!";
            throw new InvalidRootNodeException(message);
        }
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aCause
     * @param anObject
     * @param aFile
     *
     * @return an exception
     */
    private static PersistenceException createUnsuccessfulSerializationException(Throwable aCause, Object anObject,
                                                                                 File aFile) {

        String message =
            TextHelper.concatenateStrings("Serialization of an object (", anObject, " -> ", aFile.getName(),
                                          ") was not successful!");
        return new PersistenceException(message, aCause);
    }

}
