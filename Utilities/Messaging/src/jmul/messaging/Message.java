/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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
 * This interface describes an generic message. A message contains
 * <ul>
 * <li>an identifier or name of the sender,</li>
 * <li>an identifier or name of the receiver,</lI>
 * <li>a topic and</li>
 * <li>some content.</li>
 * </ul>
 * The sender and the receiver may be the same entity. A sender has to be a unique
 * entity among all connected entities (i.e. must have a unique name or identifier).
 * A receiver must be a unique entity among all connected entities (i.e. must have
 * a unique name or identifier).<br>
 * <br>
 * A message has a topic by which it can be classified. A sender and a receiver
 * which exchange messages have their individual coinventions regarding topics.
 * These conventions mirror the actual purpose and of communication between
 * these entities.<br>
 * <br>
 * The content may vary depending on the actual message type and the purpose of the
 * communication between the entities.
 *
 * @author Kristian Kutin
 */
public interface Message<T> extends Sender, Receiver {

    /**
     * Returns the topic of this message.
     *
     * @return a topic
     */
    String topic();

    /**
     * Returns the content of this message.
     *
     * @return some content
     */
    T content();

}
