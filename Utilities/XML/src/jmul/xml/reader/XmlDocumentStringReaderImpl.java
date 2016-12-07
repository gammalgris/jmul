/*
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
     * The specified string is expected to contain XML content and parses it
     * accordingly.
     *
     * @param source
     *
     * @return a document
     *
     * @throws SAXException
     * @throws IOException
     */
    @Override
    public Document parseDocument(String source) throws SAXException, IOException {

        DocumentBuilder builder = newDocumentBuilder();
        InputSource input = new InputSource(new StringReader(source));

        return builder.parse(input);
    }

    /**
     * This implementation doesn't provide this operation.
     *
     * @param aFile
     *
     * @return a document
     *
     * @throws SAXException
     * @throws IOException
     */
    @Override
    public Document parseDocument(File aFile) throws SAXException, IOException {

        throw new UnsupportedOperationException();
    }

    /**
     * This implementation doesn't provide this operation.
     *
     * @param archiveName
     * @param filename
     *
     * @return a document
     *
     * @throws SAXException
     * @throws IOException
     */
    @Override
    public Document parseArchivedDocument(String archiveName, String filename) throws SAXException, IOException {

        throw new UnsupportedOperationException();
    }

}
