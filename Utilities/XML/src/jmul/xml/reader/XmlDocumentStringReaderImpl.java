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

package jmul.xml.reader;


import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * An implementation of a XML document reader that parses a string.
 *
 * @author Kristian Kutin
 */
public class XmlDocumentStringReaderImpl extends XmlDocumentReaderBase {

    /**
     * The default constructor.
     */
    public XmlDocumentStringReaderImpl() {

        super();
    }

    /**
     * Reads from the specified string and returns a document that
     * contains the file content.
     *
     * @param aFilename
     *        the name of the input file
     *
     * @return a document object
     *
     * @throws SAXException
     *         This exception can be thrown if the xml file is malformed
     * @throws IOException
     *         This exception can be thrown if IO operations fail
     */
    @Override
    public Document readFrom(String source) throws SAXException, IOException {

        DocumentBuilder builder = newDocumentBuilder();
        InputSource input = new InputSource(new StringReader(source));

        return builder.parse(input);
    }

    /**
     * This implementation doesn't provide this operation.<br>
     * <br>
     * <i>Note:<br>
     * This operation is not supported in this implementation.</i>
     *
     * @param aFile
     *        the input file
     *
     * @return a document object
     *
     * @throws SAXException
     *         is thrown if the xml structure is invalid
     * @throws IOException
     *         is thrown if an error occurs while reading from the file
     */
    @Override
    public Document readFrom(File aFile) throws SAXException, IOException {

        throw new UnsupportedOperationException();
    }

    /**
     * This implementation doesn't provide this operation.<br>
     * <br>
     * <i>Note:<br>
     * This operation is not supported in this implementation.</i>
     *
     * @param anArchiveName
     *        the name of the archive file
     * @param aFilename
     *        the name of the input file
     *
     * @return a Document object
     *
     * @throws SAXException
     *         is thrown if the xml structure is invalid
     * @throws IOException
     *         is thrown if an error occurs while reading from the archive
     */
    @Override
    public Document readFrom(String anArchiveName, String aFilename) throws SAXException, IOException {

        throw new UnsupportedOperationException();
    }

}
