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


import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;


/**
 * This interface describes an entity that reads an XML-Document and parses its
 * content. The parsed XML-Document is returned as a Document object.<br>
 * <br>
 * For further informations about the document structure see the javadoc for
 * org.w3c.dom.Document.
 *
 * @author Kristian Kutin
 */
public interface XmlDocumentReader {

    /**
     * The method reads one XML file and transforms the content into a Document
     * object.
     *
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
    Document parseDocument(String filename) throws SAXException, IOException;

    /**
     * The method reads one XML file and transforms the content into a Document
     * object.
     *
     * @param aFile
     *        the XML file
     *
     * @return a Document object
     *
     * @throws SAXException
     *         This exception can be thrown if the xml file is malformed
     * @throws IOException
     *         This exception can be thrown if IO operations fail
     */
    Document parseDocument(File aFile) throws SAXException, IOException;

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
    Document parseArchivedDocument(String archiveName,
                                   String filename) throws SAXException,
                                                           IOException;

}
