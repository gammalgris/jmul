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

package jmul.persistence;


import java.util.Collection;

import jmul.misc.id.ID;

import jmul.xml.query.XPathQuery;


/**
 * This interface describes an entity which manages low level persistence for
 * a specific type of objects.
 *
 * @author Kristian Kutin
 */
public interface PersistenceContainer<T> {

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
    ID store(T anObject) throws InvalidRootNodeException, PersistenceException;

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
    ID commit(T anObject) throws InvalidRootNodeException, PersistenceException;

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
    ID commit(ID anID, T anObject) throws InvalidRootNodeException, PersistenceException;

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
    void delete(ID anID) throws InvalidIDException, PersistenceException;

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
    T get(ID anID) throws PersistenceException;

    /**
     * Prepares the persistence manager for shutdown (i.e. running threads will
     * be stopped).
     */
    void shutdown();

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
    Collection<ID> findByXpathExpression(XPathQuery... someQueries);

}
