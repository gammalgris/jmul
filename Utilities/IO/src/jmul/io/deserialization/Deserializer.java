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

package jmul.io.deserialization;


import java.io.File;
import java.io.IOException;


/**
 * This interface describes an entity which deserializes an object from a
 * file.
 *
 * @author Kristian Kutin
 */
public interface Deserializer {

    /**
     * Deserialize the specified object from a file.
     *
     * @param aFilename
     *        the name of the input file
     *
     * @throws IOException
     *         the exception is thrown if an error occurs while reading from the
     *         file
     */
    Object deserialize(String aFilename) throws IOException;

    /**
     * Deserialize the specified object from a file.
     *
     * @param aFile
     *        the input file
     *
     * @throws IOException
     *         the exception is thrown if an error occurs while reading from the
     *         file
     */
    Object deserialize(File aFile) throws IOException;

}
