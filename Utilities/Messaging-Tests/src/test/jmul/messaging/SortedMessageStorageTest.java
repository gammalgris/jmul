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

package test.jmul.messaging;


import jmul.messaging.Message;
import jmul.messaging.MessageQueryResult;
import jmul.messaging.SortedMessageStorage;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests a sorted message storage.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SortedMessageStorageTest {

    /**
     * Test insatantiating the storage with the default constructor.
     */
    @Test
    public void testInstantiatingStorage() {

        SortedMessageStorage storage = new SortedMessageStorage();
        assertEquals(0, storage.size());
    }

    /**
     * Tests adding a message to the storage.
     */
    @Test
    public void testAddingMessageToStorage() {

        Message<String> message = new Message<String>() {
            @Override
            public String topic() {

                return "a";
            }

            @Override
            public String content() {

                return "b";
            }

            @Override
            public String senderName() {

                return "c";
            }

            @Override
            public String receiverName() {

                return "d";
            }
        };

        SortedMessageStorage storage = new SortedMessageStorage();
        assertEquals(0, storage.size());

        storage.put(message);
        assertEquals(1, storage.size());
    }

    /**
     * Tests fetching a message from an empty storage.
     */
    @Test
    public void testFetchingMessageFromEmptyStorage() {
        
        SortedMessageStorage storage = new SortedMessageStorage();
        assertEquals(0, storage.size());

        MessageQueryResult result =  storage.fetch("any");
        assertEquals(false, result.existsResult());
    }

    /**
     * Tests fetching a message from the storage. The storage contains a message.
     */
    @Test
    public void testFetchingMessageFromTheStorage() {

        Message<String> message = new Message<String>() {
            @Override
            public String topic() {

                return "a";
            }

            @Override
            public String content() {

                return "b";
            }

            @Override
            public String senderName() {

                return "c";
            }

            @Override
            public String receiverName() {

                return "d";
            }
        };

        SortedMessageStorage storage = new SortedMessageStorage();
        assertEquals(0, storage.size());

        storage.put(message);
        assertEquals(1, storage.size());

        MessageQueryResult result = storage.fetch("a");
        assertEquals(true, result.existsResult());
    }

    /**
     * Tests fetching a message for a topic that has no messages. The storage contains a message for
     * a different topic.
     */
    @Test
    public void testFetchingMessageWithWrongTopic() {

        Message<String> message = new Message<String>() {
            @Override
            public String topic() {

                return "a";
            }

            @Override
            public String content() {

                return "b";
            }

            @Override
            public String senderName() {

                return "c";
            }

            @Override
            public String receiverName() {

                return "d";
            }
        };

        SortedMessageStorage storage = new SortedMessageStorage();
        assertEquals(0, storage.size());

        storage.put(message);
        assertEquals(1, storage.size());

        MessageQueryResult result = storage.fetch("b");
        assertEquals(false, result.existsResult());
    }

    /**
     * Tests fetching messages by priority. The storage contains two messages.
     */
    @Test
    public void testFetchingMessagesByPriority() {

        Message<String> message1 = new Message<String>() {
            @Override
            public String topic() {

                return "a";
            }

            @Override
            public String content() {

                return "b";
            }

            @Override
            public String senderName() {

                return "c";
            }

            @Override
            public String receiverName() {

                return "d";
            }
        };

        Message<String> message2 = new Message<String>() {
            @Override
            public String topic() {

                return "e";
            }

            @Override
            public String content() {

                return "f";
            }

            @Override
            public String senderName() {

                return "g";
            }

            @Override
            public String receiverName() {

                return "h";
            }
        };

        SortedMessageStorage storage = new SortedMessageStorage();
        assertEquals(0, storage.size());

        storage.put(message1);
        assertEquals(1, storage.size());

        storage.put(message2);
        assertEquals(2, storage.size());


        String[] priorities = new String[] {"e", "a"};

        MessageQueryResult result;
        
        result= storage.fetch(priorities);
        assertEquals(1, storage.size());
        assertEquals(true, result.existsResult());
        assertEquals("e", result.result().topic());


        result= storage.fetch(priorities);
        assertEquals(0, storage.size());
        assertEquals(true, result.existsResult());
        assertEquals("a", result.result().topic());
    }

    /**
     * Tests fetching order for messages with the same topic.
     */
    @Test
    public void testFetchingOrder() {

        Message<String> message1 = new Message<String>() {
            @Override
            public String topic() {

                return "a";
            }

            @Override
            public String content() {

                return "1";
            }

            @Override
            public String senderName() {

                return "c";
            }

            @Override
            public String receiverName() {

                return "d";
            }
        };

        Message<String> message2 = new Message<String>() {
            @Override
            public String topic() {

                return "a";
            }

            @Override
            public String content() {

                return "2";
            }

            @Override
            public String senderName() {

                return "c";
            }

            @Override
            public String receiverName() {

                return "d";
            }
        };

        SortedMessageStorage storage = new SortedMessageStorage();
        assertEquals(0, storage.size());

        storage.put(message1);
        assertEquals(1, storage.size());

        storage.put(message2);
        assertEquals(2, storage.size());


        MessageQueryResult result;
        
        result= storage.fetch("a");
        assertEquals(1, storage.size());
        assertEquals(true, result.existsResult());
        assertEquals("1", result.result().content());


        result= storage.fetch("a");
        assertEquals(0, storage.size());
        assertEquals(true, result.existsResult());
        assertEquals("2", result.result().content());
    }

}
