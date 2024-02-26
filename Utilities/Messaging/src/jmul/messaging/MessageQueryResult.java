/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.messaging;


import jmul.query.NoResultException;
import jmul.query.QueryResult;


/**
 * An implementation of a message query result.
 *
 * @author Kristian Kutin
 */
public class MessageQueryResult implements QueryResult<Message<? extends Object>> {

    /**
     * The actual query result.
     */
    private final Message<? extends Object> message;

    /**
     * The default constructor.
     */
    public MessageQueryResult() {

        this(null);
    }

    /**
     * Creates a new message query result according to the specified parameter.
     *
     * @param message
     *        a message or no message (i.e. <code>null</code>)
     */
    public MessageQueryResult(Message<? extends Object> message) {

        super();

        this.message = message;
    }

    /**
     * This method allows to check if there is a result or not.
     *
     * @return <code>true</code> if there is a result, else <code>false</code>
     */
    @Override
    public boolean existsResult() {

        return message != null;
    }

    /**
     * Returns the actual query result or throws a {@link jmul.query.NoResultException} if there
     * is no result.
     *
     * @return a query result, otherwise a {@link jmul.query.NoResultException}
     */
    @Override
    public Message<? extends Object> result() {

        if (message == null) {

            throw new NoResultException();
        }

        return message;
    }

}
