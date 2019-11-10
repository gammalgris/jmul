/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.transformation.message;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jmul.string.TextHelper;

import jmul.transformation.message.rules.DocumentMessageCreator;
import jmul.transformation.message.rules.GenericMessageCreator;
import jmul.transformation.message.rules.MessageCreator;
import jmul.transformation.message.rules.NodeMessageCreator;


/**
 * An implementation of a message factory.
 *
 * @author Kristian Kutin
 */
public class MessageFactoryImpl implements MessageFactory {

    /**
     * A list with all creators. The order is important (i.e. the first
     * applicable rules is applied).
     */
    private final List<MessageCreator> creators;

    /**
     * The default constructor.
     */
    public MessageFactoryImpl() {

        super();

        List<MessageCreator> tmp = new ArrayList<>();

        tmp.add(new DocumentMessageCreator());
        tmp.add(new NodeMessageCreator());
        tmp.add(new GenericMessageCreator());

        creators = Collections.unmodifiableList(tmp);
    }

    /**
     * Returns a message for the specified object.
     *
     * @param anObject
     *        an object for which to create a message
     *
     * @return a message
     */
    @Override
    public String newMessage(Object anObject) {

        for (MessageCreator creator : creators) {

            if (!creator.isApplicable(anObject)) {

                continue;
            }

            return creator.newMessage(anObject);
        }

        String message =
            TextHelper.concatenateStrings("No rule exists to create a message for the specified object (",
                                          String.valueOf(anObject), ")!");
        throw new IllegalArgumentException(message);
    }

}
