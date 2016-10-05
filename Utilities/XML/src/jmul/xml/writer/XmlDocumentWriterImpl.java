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

package jmul.xml.writer;


import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Document;


/**
 * An implementation of an xml document writer.
 *
 * //TODO
 * Check if the specified file names are indeed file names and no directories.
 *
 * @author Kristian Kutin
 */
public class XmlDocumentWriterImpl implements XmlDocumentWriter {

    /**
     * The default constructor.
     */
    public XmlDocumentWriterImpl() {

        super();
    }

    /**
     * The method writes an xml document.
     *
     * @param aFilename
     *        filename of the XML file
     * @param aDocument
     *        a document object
     *
     * @throws IOException
     *         This exception can be thrown if IO operations fail
     */
    @Override
    public void writeDocument(String aFilename, Document aDocument) throws IOException {

        writeDocument(new File(aFilename), aDocument);
    }

    /**
     * The method writes an xml document.
     *
     * @param aFile
     *        the output file
     * @param aDocument
     *        a document object
     *
     * @throws IOException
     *         This exception can be thrown if IO operations fail
     */
    @Override
    public void writeDocument(File aFile, Document aDocument) throws IOException {

        OutputFormat format = new OutputFormat(aDocument);
        format.setIndenting(true);

        FileOutputStream fos = new FileOutputStream(aFile);

        XMLSerializer serializer = new XMLSerializer(fos, format);
        serializer.serialize(aDocument);

        fos.close();
    }

}
