/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package test.jmul.persistence.legacy.threads;


import java.io.IOException;

import jmul.io.deserialization.Deserializer;

import jmul.persistence.xml.XmlDeserializerImpl;

import jmul.concurrent.threads.ObservableThreadBase;


/**
 * An implementation of a deserialization thread.
 *
 * @author Kristian Kutin
 */
public class DefaultDeserializationThread extends ObservableThreadBase {

    /**
     * The name of the file which will contain the serialized object.
     */
    private final String filename;

    /**
     * Constructs a serialization thread.
     *
     * @param aFilename
     *        the name of the file which will contain the serialized object
     */
    public DefaultDeserializationThread(String aFilename) {

        super();

        filename = aFilename;
    }

    /**
     * The actual thread implementation.
     */
    protected void performAction() {

        Deserializer deserializer = new XmlDeserializerImpl();

        try {

            Object result = deserializer.deserialize(filename);

        } catch (IOException e) {

            // This exception is ignored.
        }
    }

}
