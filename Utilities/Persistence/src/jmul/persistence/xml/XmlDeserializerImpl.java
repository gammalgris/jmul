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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.persistence.xml;


import java.io.File;
import java.io.IOException;

import jmul.io.deserialization.Deserializer;

import jmul.string.TextHelper;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationFactory;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationResources;
import jmul.transformation.xml.TransformationHelper;

import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;


/**
 * An implementation of an xml deserializer.
 *
 * @author Kristian Kutin
 */
public class XmlDeserializerImpl implements Deserializer {

    /**
     * The default transformation path for this serializer entity.
     */
    private static final TransformationPath DEFAULT_TRANSFORMATION_PATH;

    /*
   * The static initializer.
   */
    static {

        DEFAULT_TRANSFORMATION_PATH = new TransformationPath("XML", "Object");
    }

    /**
     * A XML document reader.
     */
    private final XmlDocumentReader documentReader;

    /**
     * The default constructor.
     */
    public XmlDeserializerImpl() {

        super();

        documentReader = new XmlDocumentReaderImpl();
    }

    /**
     * Deserialize the specified object from an xml file.
     *
     * @param aFilename
     *        the name of the output file
     *
     * @throws IOException
     *         the exception is thrown if an error occurs while reading from the
     *         file
     */
    @Override
    public Object deserialize(String aFilename) throws IOException {

        return deserialize(new File(aFilename));
    }

    /**
     * Deserialize the specified object from an xml file.
     *
     * @param aFile
     *        the input file
     *
     * @throws IOException
     *         the exception is thrown if an error occurs while reading from the
     *         file
     */
    @Override
    public Object deserialize(File aFile) throws IOException {

        Document document;
        try {

            document = documentReader.readFrom(aFile);

        } catch (SAXException e) {

            String message =
                TextHelper.concatenateStrings("The specified file (", aFile.getName(), ") couldn't be parsed!");
            throw new TransformationException(message, e);
        }

        TransformationParameters parameters =
            TransformationHelper.newTransformationParameters(DEFAULT_TRANSFORMATION_PATH, document);

        TransformationFactory factory = TransformationResources.getTransformationFactory();
        return factory.transform(parameters);
    }

}
