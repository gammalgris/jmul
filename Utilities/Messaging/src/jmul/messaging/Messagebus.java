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


/**
 * This interface describes a databus which manages the distribution of data (i.e. messages)
 * between various components within a monolithic application.
 *
 * @param <T>
 *        the acutal type of the message
 *
 * @author Kristian Kutin
 */
public interface Messagebus<T> {

    /**
     * Sends the specified message. The message is stored until the receiver
     * picks up the message.
     *
     * @param message
     *        a message for another component
     */
    void send(T message);

    /**
     * This method allows a receiver to fetch the latest message.
     *
     * @param query
     *        the actual query (i.e. wrapped query string)
     *
     * @return a wrapper containing the query result
     */
    MessageQueryResult fetch(MessageQuery query);

    /**
     * Returns the toal count of messages on the message bus.
     *
     * @return the toal count of messages
     */
    int size();

}
