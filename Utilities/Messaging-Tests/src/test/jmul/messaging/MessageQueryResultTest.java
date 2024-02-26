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

import jmul.query.NoResultException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests instantiating a message query result.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class MessageQueryResultTest {

    /**
     * Tests instantiating a message query result with the default constructor.
     * Checks some plausibilites first and then tries to access the result.
     */
    @Test(expected = NoResultException.class)
    public void instantiateMessageQueryResultWithNoParameters() {

        MessageQueryResult result = new MessageQueryResult();
        assertEquals(false, result.existsResult());
        result.result();
    }

    /**
     * Tests instantiating a message query result with a <code>null</code> parameter.
     * Checks some plausibilites first and then tries to access the result.
     */
    @Test(expected = NoResultException.class)
    public void instantiateMessageQueryResultWithNullParameter() {

        MessageQueryResult result = new MessageQueryResult(null);
        assertEquals(false, result.existsResult());
        result.result();
    }

    /**
     * Tests instantiating a message query result with a valid message. Checks
     * some plausibilities first and then tries to access the result.
     */
    @Test
    public void instantiateMessageQueryResultWithValidResult() {

        Message message = new Message() {

            @Override
            public String topic() {

                return "";
            }

            @Override
            public Object content() {

                return "";
            }

            @Override
            public String senderName() {

                return "";
            }

            @Override
            public String receiverName() {

                return "";
            }
        };

        MessageQueryResult result = new MessageQueryResult(message);
        assertEquals(true, result.existsResult());
        Message actualMessage = result.result();
        assertEquals(message, actualMessage);
    }

}
