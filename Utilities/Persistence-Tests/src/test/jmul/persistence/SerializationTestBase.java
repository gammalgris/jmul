/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package test.jmul.persistence;


import jmul.io.deserialization.Deserializer;
import jmul.io.serialization.Serializer;

import jmul.persistence.xml.XmlDeserializerImpl;
import jmul.persistence.xml.XmlSerializerImpl;

import static jmul.string.Constants.FILE_SEPARATOR;


/**
 * A base implementation for tests.
 *
 * @author Kristian Kutin
 */
public abstract class SerializationTestBase extends TestBase {

    /**
     * A default file suffix.
     */
    private static final String FILE_SUFFIX = ".xml";

    /**
     * The next index to append to an output file.
     */
    private static int FILE_INDEX = 1;

    /**
     * Initializes an XML serializer.
     *
     * @return an XML serializer
     */
    protected Serializer initXmlSerializer() {

        return new XmlSerializerImpl();
    }

    /**
     * Initializes an XML deserializer.
     *
     * @return an XML deserializer
     */
    protected Deserializer initXmlDeserializer() {

        return new XmlDeserializerImpl();
    }

    /**
     * Appends a unique number to the specified file name.
     *
     * @param aBaseDir
     * @param aFileName
     *
     * @return a file name
     */
    protected String getOutputFileName(String aBaseDir, String aFileName) {

        String newFileName = aBaseDir + FILE_SEPARATOR + aFileName + FILE_INDEX + FILE_SUFFIX;
        FILE_INDEX++;

        return newFileName;
    }

}
