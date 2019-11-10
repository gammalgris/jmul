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

package jmul.transformation.message.rules;


import jmul.string.TextHelper;

import org.w3c.dom.Document;


/**
 * A specific implementation for a message creator for XML
 * Documents.
 *
 * @author Kristian Kutin
 */
public class DocumentMessageCreator extends MessageCreatorBase {

    /**
     * The default constructor.
     */
    public DocumentMessageCreator() {

        super(Document.class);
    }

    /**
     * Creates a new message for the specified object.
     *
     * @param anObject
     *        an object for which to create a message
     *
     * @return a message
     */
    @Override
    public String newMessage(Object anObject) {

        return TextHelper.concatenateStrings(getParentClass().getName(), " (check the corresponding XML file)");
    }

}
