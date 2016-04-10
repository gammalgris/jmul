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

package jmul.xml;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;
import jmul.xml.writer.XmlDocumentWriter;
import jmul.xml.writer.XmlDocumentWriterImpl;


/**
 * A utility class which contains required resources.
 *
 * @author Kristian Kutin
 */
public final class XmlResources {

    /**
     * A singleton.
     */
    private static XmlDocumentReader xmlDocumentReaderSingleton;

    /**
     * A singleton.
     */
    private static XmlDocumentWriter xmlDocumentWriterSingleton;

    /**
     * A singleton.
     */
    private static DocumentBuilderFactory documentBuilderFactorySingleton;

    /**
     * The default constructor.
     */
    private XmlResources() {
    }

    /**
     * Returns a reference to an xml document reader.
     *
     * @return an xml document reader
     */
    public static XmlDocumentReader getXmlDocumentReader() {

        if (xmlDocumentReaderSingleton == null) {

            xmlDocumentReaderSingleton = new XmlDocumentReaderImpl();
        }

        return xmlDocumentReaderSingleton;
    }

    /**
     * Returns a reference to an xml document writer.
     *
     * @return an xml document reader
     */
    public static XmlDocumentWriter getXmlDocumentWriter() {

        if (xmlDocumentWriterSingleton == null) {

            xmlDocumentWriterSingleton = new XmlDocumentWriterImpl();
        }

        return xmlDocumentWriterSingleton;
    }

    /**
     * Returns a new xml document.
     *
     * @return a new document
     *
     * @throws ParserConfigurationException
     *         The exception is thrown if an error occurs while creating the
     *         new document
     */
    public static Document newDocument() throws ParserConfigurationException {

        if (documentBuilderFactorySingleton == null) {

            documentBuilderFactorySingleton =
                    DocumentBuilderFactory.newInstance();
        }

        DocumentBuilder builder =
            documentBuilderFactorySingleton.newDocumentBuilder();

        return builder.newDocument();
    }

}
