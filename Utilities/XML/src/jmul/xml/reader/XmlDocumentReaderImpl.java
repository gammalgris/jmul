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

package jmul.xml.reader;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;

import jmul.io.JarResources;

import jmul.misc.markers.Inefficient;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;


/**
 * The class XMLDocumentReaderImpl is an implmenentation for the
 * XMLDocumentReader interface.<br>
 * <br>
 * <i>More informations:</i><br>
 * <ul>
 *   <li><i><a href="http://stackoverflow.com/questions/3439485/java-and-xml-jaxp-what-about-caching-and-thread-safety">StackOverflow</a></i></li>
 * </ul>
 *
 * @author Kristian Kutin
 */
public class XmlDocumentReaderImpl extends XmlDocumentReaderBase {

    /**
     * The default constructor.
     */
    public XmlDocumentReaderImpl() {

        super();
    }

    /**
     * Reads from the specified file and returns a document that
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
     * @throws jmul.xml.reader.EmptyFileException
     *         This  exception is thrown if the specified file is empty
     */
    @Override
    public Document readFrom(String aFilename) throws SAXException, IOException {

        File file = new File(aFilename);
        return readFrom(file);
    }

    /**
     * Reads from the specified file and returns a document that
     * contains the file content.
     *
     * @param aFile
     *        the input file
     *
     * @return a document object
     *
     * @throws SAXException
     *         This exception can be thrown if the xml file is malformed
     * @throws IOException
     *         This exception can be thrown if IO operations fail
     * @throws jmul.xml.reader.EmptyFileException
     *         This  exception is thrown if the specified file is empty
     */
    @Override
    public Document readFrom(File aFile) throws SAXException, IOException {

        if (aFile.exists() && (aFile.length() == 0)) {

            throw new EmptyFileException(aFile);
        }

        DocumentBuilder builder = newDocumentBuilder();
        return builder.parse(aFile);
    }

    /**
     * Reads from the specified archive and returns a document that
     * contains the file content.
     *
     * @param anArchiveName
     *        the name of the archive file
     * @param aFilename
     *        the name of the input file
     *
     * @return a Document object
     *
     * @throws SAXException
     *         This exception can be thrown if the xml file is malformed
     * @throws IOException
     *         This exception can be thrown if IO operations fail
     */
    @Inefficient
    @Override
    public Document readFrom(String archiveName, String filename) throws SAXException, IOException {

        DocumentBuilder builder = newDocumentBuilder();
        JarResources resources = new JarResources(archiveName);

        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(resources.getResource(filename))) {

            return builder.parse(byteStream);
        }
    }

}
