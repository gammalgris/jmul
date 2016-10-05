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

package jmul.xml.reader;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

import jmul.io.JarResources;

import jmul.string.StringConcatenator;


/**
 * The class XMLDocumentReaderImpl is an implmenentation for the
 * XMLDocumentReader interface.<br>
 * <br>
 * <i>More informations:<br>
 * <ul>
 *   <li><a href="http://stackoverflow.com/questions/3439485/java-and-xml-jaxp-what-about-caching-and-thread-safety">StackOverflow</a></li>
 * </ul>
 * </i>
 *
 * @author Kristian Kutin
 */
public class XmlDocumentReaderImpl implements XmlDocumentReader {

    /**
     * A document builder factory.
     */
    private DocumentBuilderFactory factory;

    /**
     * The constructor requires a reference on a ResourceManager to access
     * loggers.
     */
    public XmlDocumentReaderImpl() {

        factory = DocumentBuilderFactory.newInstance();
    }

    /**
     * Returns a new instance of a document builder.
     *
     * @return a document builder
     */
    private DocumentBuilder newDocumentBuilder() {

        DocumentBuilder builder = null;

        try {

            builder = factory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {

            StringConcatenator message =
                new StringConcatenator("Couldn't instantiate ", this.getClass().getName(), "!");
            throw new ReaderException(message.toString(), e);
        }

        return builder;
    }

    /**
     * The method parseDocument reads one XML file and transforms the content
     * into a Document object.
     *
     * @param aFilename
     *        filename of the XML file
     *
     * @return a Document object
     *
     * @throws SAXException
     *         This exception can be thrown if the xml file is malformed
     * @throws IOException
     *         This exception can be thrown if IO operations fail
     */
    @Override
    public Document parseDocument(String aFilename) throws SAXException, IOException {

        File file = new File(aFilename);
        return parseDocument(file);
    }

    /**
     * The method parseDocument reads one XML file and transforms the content
     * into a Document object.
     *
     * @param aFile
     * the XML file
     *
     * @return a Document object
     *
     * @throws SAXException
     * This exception can be thrown if the xml file is malformed
     * @throws IOException
     * This exception can be thrown if IO operations fail
     * @throws jmul.xml.reader.EmptyFileException
     * This  exception is thrown if the specified file is empty
     */
    @Override
    public Document parseDocument(File aFile) throws SAXException, IOException {

        boolean empty = !aFile.exists() || (aFile.length() == 0);
        if (empty) {

            throw new EmptyFileException(aFile);
        }

        DocumentBuilder builder = newDocumentBuilder();
        return builder.parse(aFile);
    }

    /**
     * The method reads an XML file from an archive and transforms the content
     * into a Document object.
     *
     * @param archiveName
     *        the name of the archive file
     * @param filename
     *        filename of the XML file
     *
     * @return a Document object
     *
     * @throws SAXException
     *         This exception can be thrown if the xml file is malformed
     * @throws IOException
     *         This exception can be thrown if IO operations fail
     */
    @Override
    public Document parseArchivedDocument(String archiveName, String filename) throws SAXException, IOException {

        DocumentBuilder builder = newDocumentBuilder();

        JarResources resources = new JarResources(archiveName);

        ByteArrayInputStream byteStream = new ByteArrayInputStream(resources.getResource(filename));
        Document xmlDocument = builder.parse(byteStream);

        byteStream.close();

        return xmlDocument;
    }

}
