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

package jmul.measures.definitions.reader;


import java.io.File;
import java.io.IOException;

import org.xml.sax.SAXException;


/**
 * The interface describes an entity which reads from a definition file.
 */
public interface DefinitionReader {

    /**
     * The method reads the definitions from a definition file and updates a
     * system of measurement accordingly.
     *
     * @param aFilename
     *        the filename of a definition file
     *
     * @return a reference to the updated system of measurement
     *
     * @throws SAXException
     *         This exception is thrown if the xml structure is invalid
     * @throws IOException
     *         This exception is thrown if the IO operation couldn't be executed
     */
    Definition readDefinition(String aFilename) throws SAXException, IOException;

    /**
     * The method reads the definitions from a definition file and updates a
     * system of measurement accordingly.
     *
     * @param aFile
     *        a definition file
     *
     * @return a reference to the updated system of measurement
     *
     * @throws SAXException
     *         This exception is thrown if the xml structure is invalid
     * @throws IOException
     *         This exception is thrown if the IO operation couldn't be executed
     */
    Definition readDefinition(File aFile) throws SAXException, IOException;

}
