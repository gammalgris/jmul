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


import jmul.messaging.MessageQuery;
import jmul.messaging.Receiver;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests instantiating a message query.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class MessageQueryTest {

    /**
     * Tests instantiating a message query with an invalid parameter (i.e. <code>null</code>).
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateMessageQueryWithNullParameter() {

        new MessageQuery(null);
    }

    /**
     * Tests instantiating a message query with a valid parameter.
     */
    @Test
    public void instantiateMessageQueryWithReceiver() {

        String receiverName = "Test";

        Receiver receiver = new Receiver() {

            @Override
            public String receiverName() {

                return receiverName;
            }
        };

        MessageQuery query = new MessageQuery(receiver);
        assertEquals(receiverName, query.queryString());
    }

}
