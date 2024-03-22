/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2023  Kristian Kutin
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


import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * An implementation of a standard message.
 *
 * @author Kristian Kutin
 */
public class StandardMessage implements Message, Iterable<PropertyKey> {

    /**
     * The data structure that contains the message data. The reference to the content map is
     * immutable. The content map itself is immutable.
     */
    private final Map<PropertyKey, Object> contentMap;

    /**
     * Creates a new message accordign to the specified parameters.
     *
     * @param sender
     *        the name of a sender
     * @param receiver
     *        the name of a receiver
     * @param topic
     *        the topic of this message
     */
    public StandardMessage(String sender, String receiver, String topic) {

        super();

        checkSender(sender);
        checkReceiver(receiver);
        checkTopic(topic);

        Map<PropertyKey, Object> map = new HashMap<>();

        map.put(StandardPropertyKeys.SENDER, sender);
        map.put(StandardPropertyKeys.RECEIVER, receiver);
        map.put(StandardPropertyKeys.TOPIC, topic);

        Date creationDate = new Date();
        map.put(StandardPropertyKeys.CREATION_DATE, creationDate);

        contentMap = Collections.unmodifiableMap(map);
    }

    /**
     * Creates a new message according to the specified parameters.
     *
     * @param sender
     *        the name of a sender
     * @param receiver
     *        the name of a receiver
     * @param topic
     *        the topic of this message
     * @param data
     *        additional message data
     */
    public StandardMessage(String sender, String receiver, String topic, Map.Entry<PropertyKey, Object>... data) {

        super();

        checkSender(sender);
        checkReceiver(receiver);
        checkTopic(topic);

        Map<PropertyKey, Object> map = new HashMap<>();

        map.put(StandardPropertyKeys.SENDER, sender);
        map.put(StandardPropertyKeys.RECEIVER, receiver);
        map.put(StandardPropertyKeys.TOPIC, topic);

        Date creationDate = new Date();
        map.put(StandardPropertyKeys.CREATION_DATE, creationDate);

        for (Map.Entry<PropertyKey, Object> entry : data) {

            PropertyKey key = entry.getKey();
            Object value = entry.getValue();

            if (map.containsKey(key)) {

                String message = String.format("Trying to override the key %s!", key);
                throw new IllegalArgumentException(message);
            }

            map.put(key, value);
        }

        contentMap = Collections.unmodifiableMap(map);
    }

    /**
     * Checks the specified sender.
     *
     * @param sender
     *        the name of a sender
     *
     * @return the specified sender
     */
    private static String checkSender(String sender) {

        if (sender == null) {

            throw new IllegalArgumentException("No sender (null) was specified!");
        }

        String normalizedSender = sender.trim();
        if (normalizedSender.isEmpty()) {

            throw new IllegalArgumentException("No sender (empty string) was specified!");
        }

        return sender;
    }

    /**
     * Checks the specified receiver.
     *
     * @param receiver
     *        the name of a receiver
     *
     * @return the specified receiver
     */
    private static String checkReceiver(String receiver) {

        if (receiver == null) {

            throw new IllegalArgumentException("No receiver (null) was specified!");
        }

        String normalizedRecipient = receiver.trim();
        if (normalizedRecipient.isEmpty()) {

            throw new IllegalArgumentException("No recipient (empty string) was specified!");
        }

        return receiver;
    }

    /**
     * Checks the specified topic.
     *
     * @param topic
     *        the name of a topic
     *
     * @return the specified topic
     */
    private static String checkTopic(String topic) {

        if (topic == null) {

            throw new IllegalArgumentException("No topic (null) was specified!");
        }

        String normalizedTopic = topic.trim();
        if (normalizedTopic.isEmpty()) {

            throw new IllegalArgumentException("No topic (empty string) was specified!");
        }

        return topic;
    }

    /**
     * Returns the name of the sender of this message.
     *
     * @return the name of the sender
     */
    @Override
    public String senderName() {

        return (String) contentMap.get(StandardPropertyKeys.SENDER);
    }

    /**
     * Returns the name if the receiver of this message.
     *
     * @return the name of the receiver
     */
    @Override
    public String receiverName() {

        return (String) contentMap.get(StandardPropertyKeys.RECEIVER);
    }

    /**
     * Returns the topic of this message. The topic summarizes what to do with this
     * message.
     *
     * @return a topic for this message
     */
    @Override
    public String topic() {

        return (String) contentMap.get(StandardPropertyKeys.TOPIC);
    }

    /**
     * The date when the message was created.
     *
     * @return a creation date
     */
    public Date date() {

        return (Date) contentMap.get(StandardPropertyKeys.CREATION_DATE);
    }

    /**
     * Returns the data associated with the specified key. The actual content may vary
     * depending on sender, recipient and purpose (i.e. topic).<br>
     * <br>
     * @implNote
     * Using this method may return <code>null</code>. Subclasses may offer additonal getter
     * methods to access the message data without the need to check for <code>null</code>.
     *
     * @param key
     *        a key to access data within this message
     *
     * @return the data associated with the specified key or <code>null</code> if
     *         such data exists
     */
    public Object get(PropertyKey key) {

        return contentMap.get(key);
    }

    /**
     * Returns an iterator for this message.
     *
     * @return an iterator to iterate through all content keys
     */
    public Iterator<PropertyKey> iterator() {

        return contentMap.keySet().iterator();
    }

    /**
     * Checks if this message contains content which is associated with
     * the specified property key.
     *
     * @param key
     *        a property key
     *
     * @return <code>true</code> if such content exists, else <code>false</code>
     */
    @Override
    public boolean contains(PropertyKey key) {

        return contentMap.containsKey(key);
    }

    /**
     * Returns a string represetnation for this message.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        String s =
            String.format("message[sender=%s;receiver=%s;topic=%s;date=%s]", senderName(), receiverName(), topic(),
                          date().toString());
        return s;
    }

}
