/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.xml.writer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.nio.charset.Charset;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import jmul.misc.exceptions.InitializationException;

import org.w3c.dom.Document;


/**
 * An implementation of an xml document writer.
 *
 * @author Kristian Kutin
 */
public class XmlDocumentWriter2Impl implements XmlDocumentWriter {

    /**
     * A transformer instance.
     */
    private final Transformer transformer;

    /**
     * The default constructor.
     */
    public XmlDocumentWriter2Impl() {

        super();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try {

            transformer = transformerFactory.newTransformer();

        } catch (TransformerConfigurationException e) {

            throw new InitializationException("The XML document reader couldn't be initialized!", e);
        }
    }

    /**
     * Writes the specified document to the specified file.
     *
     * @param aFilename
     *        the name of the output file
     * @param aDocument
     *        a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to write to the file
     */
    @Override
    public void writeTo(String aFilename, Document aDocument) throws IOException {

        writeTo(new File(aFilename), aDocument);
    }

    /**
     * Writes the specified document to the specified file.
     *
     * @param aFile
     *        the output file
     * @param aDocument
     *        a document object
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to write to the file
     */
    @Override
    public void writeTo(File aFile, Document aDocument) throws IOException {

        DOMSource domSource = new DOMSource(aDocument);
        String encoding = aDocument.getXmlEncoding();
        Charset charset = Charset.forName(encoding);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aFile), charset));
        StreamResult streamResult = new StreamResult(writer);

        try {

            transformer.transform(domSource, streamResult);

        } catch (TransformerException e) {

            throw new WriteXmlException(e);
        }

        try {

            writer.flush();

        } finally {

            writer.flush();
        }
    }

}
