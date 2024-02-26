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


import jmul.query.Query;


/**
 * An implementation of a message query (i.e. query by receiver).
 *
 * @author Kristian Kutin
 */
public class MessageQuery implements Query {

    /**
     * The name of the receiver.
     */
    private final String queryString;

    /**
     * Creates a new Query according to the specified parameter.
     *
     * @param receiver
     *        the actual receiver itself
     */
    public MessageQuery(Receiver receiver) {

        super();

        if (receiver == null) {

            throw new IllegalArgumentException("No receiver (null) was specified!");
        }

        queryString = receiver.receiverName();
    }

    /**
     * Returns a query string.
     *
     * @return a query string
     */
    @Override
    public String queryString() {

        return queryString;
    }

}
