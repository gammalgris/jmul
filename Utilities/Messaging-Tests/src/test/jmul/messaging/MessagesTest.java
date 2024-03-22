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

package test.jmul.messaging;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import jmul.messaging.PropertyKey;
import jmul.messaging.StandardMessage;
import jmul.messaging.StandardPropertyKeys;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests the various messages.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class MessagesTest {

    /**
     * Tests instantiating a standard message without data.<br>
     * <br>
     * A message's content is arranged as key value pairs. The content can be accessed
     * with the proper key. For the default message properties exists an enumeration
     * with keys.<br>
     * There also exist methods to access the standard properties without key.
     */
    @Test
    public void testInstantiatingStandardMessageWithNoData() {

        StandardMessage message = new StandardMessage("sender", "receiver", "topic");
        Date date = new Date();

        assertEquals("sender", message.senderName());
        assertEquals("receiver", message.receiverName());
        assertEquals("topic", message.topic());
        assertEquals(date, message.date());

        assertEquals("sender", message.get(StandardPropertyKeys.SENDER));
        assertEquals("receiver", message.get(StandardPropertyKeys.RECEIVER));
        assertEquals("topic", message.get(StandardPropertyKeys.TOPIC));
        assertEquals(date, message.get(StandardPropertyKeys.CREATION_DATE));
    }

    /**
     * Tests instantiating a standard message without data.<br>
     * <br>
     * A message's content is arranged as key value pairs. The content can be accessed
     * with the proper key. For the default message properties exists an enumeration
     * with keys. The rest of the data must be accessed by individual keys which may
     * or may not be defined in an enumeration.<br>
     * There also exist methods to access the standard properties without key.
     */
    @Test
    public void testInstantiatingStandardMessageWithData() {

        PropertyKey key = new PropertyKey() {

            @Override
            public String toString() {

                return "data";
            }
        };

        Map.Entry<PropertyKey, Object> data = new Map.Entry<PropertyKey, Object>() {
            @Override
            public PropertyKey getKey() {

                return key;
            }

            @Override
            public Object getValue() {

                return "data";
            }

            @Override
            public Object setValue(Object value) {

                return null;
            }
        };

        StandardMessage message = new StandardMessage("sender", "receiver", "topic", data);
        Date date = new Date();

        assertEquals("sender", message.senderName());
        assertEquals("receiver", message.receiverName());
        assertEquals("topic", message.topic());
        assertEquals(date, message.date());

        assertEquals("sender", message.get(StandardPropertyKeys.SENDER));
        assertEquals("receiver", message.get(StandardPropertyKeys.RECEIVER));
        assertEquals("topic", message.get(StandardPropertyKeys.TOPIC));
        assertEquals(date, message.get(StandardPropertyKeys.CREATION_DATE));
        assertEquals("data", message.get(key));
    }

    /**
     * Test the iterator on the message content.<br>
     * <br>
     * A message's content is arranged as key value pairs. The iterator iterates through the
     * keys. The actual order will depend on the actual map implementation.
     */
    @Test
    public void testIterator() {

        PropertyKey key = new PropertyKey() {

            @Override
            public String toString() {

                return "data";
            }
        };

        Map.Entry<PropertyKey, Object> data = new Map.Entry<PropertyKey, Object>() {
            @Override
            public PropertyKey getKey() {

                return key;
            }

            @Override
            public Object getValue() {

                return "data";
            }

            @Override
            public Object setValue(Object value) {

                return null;
            }
        };

        StandardMessage message = new StandardMessage("sender", "receiver", "topic");

        Collection<PropertyKey> remainingKeys = new ArrayList<>();
        remainingKeys.addAll(Arrays.asList(StandardPropertyKeys.values()));
        Iterator<PropertyKey> iterator = message.iterator();

        while (iterator.hasNext()) {

            PropertyKey actualKey = iterator.next();
            assertEquals(true, remainingKeys.contains(actualKey));
            boolean result = remainingKeys.remove(actualKey);
            assertEquals(true, result);
        }

        assertEquals(0, remainingKeys.size());
    }

}
