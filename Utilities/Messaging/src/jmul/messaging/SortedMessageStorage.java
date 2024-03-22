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
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


/**
 * This class represents a sorted message storage. Messages are sorted by their topic.
 *
 * @author Kristian Kutin
 */
public class SortedMessageStorage {

    /**
     * The actual storage container.
     */
    private final Map<String, Queue<Message>> storageMap;

    /**
     * The default constructor.
     */
    public SortedMessageStorage() {

        super();

        storageMap = new HashMap<>();
    }

    /**
     * Puts the specified message into this message storage. The message is stored until
     * someone picks up the message.
     *
     * @param message
     *        a message
     */
    public void put(Message message) {

        if (message == null) {

            throw new IllegalArgumentException("No message (null) was specified!");
        }

        String topic = message.topic();

        Queue<Message> queue = storageMap.get(topic);
        if (queue == null) {

            queue = new LinkedList<>();
            storageMap.put(topic, queue);
        }

        queue.add(message);
    }

    /**
     * This method allows a receiver to fetch the latest message for the specified topic.
     *
     * @param topic
     *        messages are sorted by topic thus you have to provide a topic
     *
     * @return a wrapper containing the query result
     */
    public MessageQueryResult fetch(String topic) {

        Queue<Message> queue = storageMap.get(topic);

        Message message = null;
        if (queue != null) {

            message = queue.poll();
        }

        return new MessageQueryResult(message);
    }

    /**
     * This method allows a receiver to fetch the latest message for the specified topics.
     * The topics are provided as array that represent the priority of the topics. The lower
     * the element index of the topic the hgher the priority of the topic. The first message
     * found is returned.
     *
     * @param topicsByPriority
     *        an array with all topics sorted by priority
     *
     * @return a wrapper containing the query result
     */
    public MessageQueryResult fetch(String... topicsByPriority) {

        for (String topic : topicsByPriority) {

            MessageQueryResult result = fetch(topic);
            if (result.existsResult()) {

                return result;
            }
        }

        return new MessageQueryResult();
    }

    /**
     * Returns the toal count of messages within this message storage.
     *
     * @return the total count of messages
     */
    public int size() {

        int size = 0;

        for (Map.Entry<String, Queue<Message>> entry : storageMap.entrySet()) {

            Queue<Message> queue = entry.getValue();
            size += queue.size();
        }

        return size;
    }

}
