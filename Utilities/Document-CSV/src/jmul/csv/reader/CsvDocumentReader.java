/*
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

package jmul.csv.reader;


import java.io.File;
import java.io.IOException;

import jmul.document.csv.CsvDocument;


/**
 * This interface describes an entity that reads CSV files.
 *
 * @author Kristian Kutin
 */
public interface CsvDocumentReader {

    /**
     * Parses the specified file and returns a document that contains the
     * file content.
     *
     * @param aFilename
     *        filename of the CSV file
     *
     * @return a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the file
     */
    CsvDocument parseDocument(String aFilename) throws IOException;

    /**
     * Parses the specified file and returns a document that contains the
     * file content.
     *
     * @param aFile
     *        the CSV file
     *
     * @return a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the file
     */
    CsvDocument parseDocument(File aFile) throws IOException;

}
