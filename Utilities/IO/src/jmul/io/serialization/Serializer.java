/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

package jmul.io.serialization;


import java.io.File;
import java.io.IOException;


/**
 * This interface describes an entity which serializes an object to a file.
 *
 * @author Kristian Kutin
 */
public interface Serializer {

    /**
     * Serialize the specified object to a file.
     *
     * @param aFilename
     *        the name of the output file
     * @param anObject
     *        the object which is to be serialized
     *
     * @throws IOException
     *         the exception is thrown if an error occurs while writing to the
     *         file
     */
    void serialize(String aFilename, Object anObject) throws IOException;

    /**
     * Serialize the specified object to a file.
     *
     * @param aFile
     *        the output file
     * @param anObject
     *        the object which is to be serialized
     *
     * @throws IOException
     *         the exception is thrown if an error occurs while writing to the
     *         file
     */
    void serialize(File aFile, Object anObject) throws IOException;

}
