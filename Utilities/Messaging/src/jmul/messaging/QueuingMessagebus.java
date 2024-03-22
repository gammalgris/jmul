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


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * An implementation of a messagebus which stores the messages in individual queues by
 * receiver. The messages are not stored persistently. The messagebus must handle multi
 * threaded access.
 *
 * @author Kristian Kutin
 */
public class QueuingMessagebus implements Messagebus {

    /**
     * This messagebus implementation puts the messages into individual queues for
     * every receiver. This way the queuing mechanism isn't blocked when one receiver
     * doesn't fetch his messages.
     */
    private volatile Map<String, Queue<Message>> queueMap;

    /**
     * The default constructor.
     */
    public QueuingMessagebus() {

        super();

        queueMap = new HashMap<>();
    }

    /**
     * Sends the specified message. The message is stored until the receiver
     * picks up the message.
     *
     * @param message
     *        a message for another component
     */
    @Override
    public void send(Message message) {

        String receiver = message.receiverName();

        Queue<Message> queue;
        synchronized (queueMap) {

            queue = queueMap.get(receiver);

            if (queue == null) {

                queue = new ConcurrentLinkedQueue<>();
                queueMap.put(receiver, queue);
            }
        }

        queue.add(message);
    }

    /**
     * This method allows a receiver to fetch the latest message.
     *
     * @param query
     *        the actual query (i.e. wrapped query string)
     *
     * @return a wrapper containing the query result
     */
    @Override
    public MessageQueryResult fetch(MessageQuery query) {

        String receiver = query.queryString();

        Queue<Message> queue;
        synchronized (queueMap) {

            queue = queueMap.get(receiver);
        }

        if (queue == null) {

            return new MessageQueryResult();

        } else {

            Message message = queue.poll();
            return new MessageQueryResult(message);
        }
    }

    /**
     * Returns the toal count of messages on the message bus.
     *
     * @return the total count of messages
     */
    @Override
    public int size() {

        int size = 0;

        Set<Map.Entry<String, Queue<Message>>> entries;
        synchronized (queueMap) {

            entries = new HashSet<>(queueMap.entrySet());
        }

        for (Map.Entry<String, Queue<Message>> entry : entries) {

            Queue<Message> queue = entry.getValue();
            size += queue.size();
        }

        return size;
    }

}
