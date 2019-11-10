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

import jmul.io.serialization.Serializer;

import jmul.persistence.xml.XmlSerializerImpl;

import jmul.concurrent.threads.ObservableThreadBase;


/**
 * An implementation of a serialization thread.
 *
 * @author Kristian Kutin
 */
public class DefaultSerializationThread extends ObservableThreadBase {

    /**
     * The name of the file which will contain the serialized object.
     */
    private final String filename;

    /**
     * The object which is to be serializd.
     */
    private final Object object;

    /**
     * Constructs a serialization thread.
     *
     * @param aFilename
     *        the name of the file which will contain the serialized object
     * @param anObject
     *        the object which is to be serializd
     */
    public DefaultSerializationThread(String aFilename, Object anObject) {

        super();

        filename = aFilename;
        object = anObject;
    }

    /**
     * The actual thread implementation.
     */
    protected void performAction() {

        Serializer serializer = new XmlSerializerImpl();

        try {

            serializer.serialize(filename, object);

        } catch (IOException e) {

            // This exception is ignored.
        }
    }

}
