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
import jmul.messaging.MessageQuery;
import jmul.messaging.MessageQueryResult;
import jmul.messaging.QueuingMessagebus;
import jmul.messaging.Receiver;

import jmul.query.NoResultException;

import static org.junit.Assert.assertEquals;

import jmul.test.classification.UnitTest;

import org.junit.Test;


/**
 * This test suite tests a queing messagebus.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class QueuingMessagebusTest {

    /**
     * Tests instantiating the queing messagebus with the default constructor.
     */
    @Test
    public void testInstantiatingMessageBus() {

        QueuingMessagebus messagebus = new QueuingMessagebus();
        assertEquals(0, messagebus.size());
    }

    /**
     * Tests sending a message via the queing messagebus.
     */
    @Test
    public void testSendingMessage() {

        Message<String> message = new Message<String>() {
            @Override
            public String topic() {

                return "";
            }

            @Override
            public String content() {

                return "";
            }

            @Override
            public String senderName() {

                return "sender";
            }

            @Override
            public String receiverName() {

                return "receiver";
            }
        };

        QueuingMessagebus messagebus = new QueuingMessagebus();
        assertEquals(0, messagebus.size());

        messagebus.send(message);
        assertEquals(1, messagebus.size());
    }

    /**
     * Tests fetching a message from an empty queing messagebus.
     */
    @Test(expected = NoResultException.class)
    public void testFetchingMessageFromEmptyMessagebus() {

        QueuingMessagebus messagebus = new QueuingMessagebus();
        assertEquals(0, messagebus.size());

        Receiver receiver = new Receiver() {

            @Override
            public String receiverName() {

                return "receiver";
            }
        };

        MessageQuery query = new MessageQuery(receiver);
        MessageQueryResult result = messagebus.fetch(query);
        assertEquals(false, result.existsResult());

        result.result();
    }

    /**
     * Tests fetching a message from a queing messagebus that contains one message.
     */
    @Test
    public void testFetchingMessage() {

        Message<String> message = new Message<String>() {
            @Override
            public String topic() {

                return "";
            }

            @Override
            public String content() {

                return "";
            }

            @Override
            public String senderName() {

                return "sender";
            }

            @Override
            public String receiverName() {

                return "receiver";
            }
        };

        QueuingMessagebus messagebus = new QueuingMessagebus();
        assertEquals(0, messagebus.size());

        messagebus.send(message);
        assertEquals(1, messagebus.size());

        Receiver receiver = new Receiver() {

            @Override
            public String receiverName() {

                return "receiver";
            }
        };

        MessageQuery query = new MessageQuery(receiver);
        MessageQueryResult result = messagebus.fetch(query);
        assertEquals(true, result.existsResult());

        Message<String> fetchedMessage = (Message<String>) result.result();
        assertEquals(message, fetchedMessage);
    }

    /**
     * Tests fetching a message from a queing messagebus that contains one message but tries
     * to fetch a message with another reiver.
     */
    @Test(expected = NoResultException.class)
    public void testFetchingMessageWithDifferentReceiver() {

        Message<String> message = new Message<String>() {
            @Override
            public String topic() {

                return "";
            }

            @Override
            public String content() {

                return "";
            }

            @Override
            public String senderName() {

                return "sender";
            }

            @Override
            public String receiverName() {

                return "receiver";
            }
        };

        QueuingMessagebus messagebus = new QueuingMessagebus();
        assertEquals(0, messagebus.size());

        messagebus.send(message);
        assertEquals(1, messagebus.size());

        Receiver receiver = new Receiver() {

            @Override
            public String receiverName() {

                return "receiver2";
            }
        };

        MessageQuery query = new MessageQuery(receiver);
        MessageQueryResult result = messagebus.fetch(query);
        assertEquals(false, result.existsResult());

        result.result();
    }

    /**
     * Tests the fetching order for messages to the same receiver.
     */
    @Test
    public void testFetchingOrderForSameReceiver() {
        
        Message<String> message1 = new Message<String>() {
            @Override
            public String topic() {

                return "1";
            }

            @Override
            public String content() {

                return "";
            }

            @Override
            public String senderName() {

                return "sender";
            }

            @Override
            public String receiverName() {

                return "receiver";
            }
        };

        Message<String> message2 = new Message<String>() {
            @Override
            public String topic() {

                return "2";
            }

            @Override
            public String content() {

                return "";
            }

            @Override
            public String senderName() {

                return "sender";
            }

            @Override
            public String receiverName() {

                return "receiver";
            }
        };

        QueuingMessagebus messagebus = new QueuingMessagebus();
        assertEquals(0, messagebus.size());

        messagebus.send(message1);
        assertEquals(1, messagebus.size());

        messagebus.send(message2);
        assertEquals(2, messagebus.size());


        Receiver receiver = new Receiver() {

            @Override
            public String receiverName() {

                return "receiver";
            }
        };

        MessageQuery query = new MessageQuery(receiver);
        MessageQueryResult result;
        
        result = messagebus.fetch(query);
        assertEquals(1,messagebus.size());
        assertEquals(true, result.existsResult());
        assertEquals("1", result.result().topic());

        result = messagebus.fetch(query);
        assertEquals(0,messagebus.size());
        assertEquals(true, result.existsResult());
        assertEquals("2", result.result().topic());
    }

}
