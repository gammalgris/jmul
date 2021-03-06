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

import jmul.io.serialization.Serializer;

import jmul.transformation.TransformationFactory;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationResources;
import jmul.transformation.xml.TransformationHelper;

import jmul.xml.writer.XmlDocumentWriter;
import jmul.xml.writer.XmlDocumentWriterImpl;

import org.w3c.dom.Document;


/**
 * An implementation of an xml serializer.
 *
 * @author Kristian Kutin
 */
public class XmlSerializerImpl implements Serializer {

    /**
     * The default transformation path for this serializer entity.
     */
    private static final TransformationPath DEFAULT_TRANSFORMATION_PATH;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_TRANSFORMATION_PATH = new TransformationPath("Object", "XML");
    }

    /**
     * A XML document writer.
     */
    private final XmlDocumentWriter writer;

    /**
     * The default constructor.
     */
    public XmlSerializerImpl() {

        super();

        writer = new XmlDocumentWriterImpl();
    }

    /**
     * Serialize the specified object in an xml file.
     *
     * @param aFilename
     *        the name of the output file
     * @param anObject
     *        the object which is to be serialized
     *
     * @throws IOException
     *         the exception is thrown if an error occurs while writing to the
     *         file
     */
    @Override
    public void serialize(String aFilename, Object anObject) throws IOException {

        serialize(new File(aFilename), anObject);
    }

    /**
     * Serialize the specified object in an xml file.
     *
     * @param aFile
     *        the output file
     * @param anObject
     *        the object which is to be serialized
     *
     * @throws IOException
     *         the exception is thrown if an error occurs while writing to the
     *         file
     */
    @Override
    public void serialize(File aFile, Object anObject) throws IOException {

        TransformationParameters parameters =
            TransformationHelper.newTransformationParameters(DEFAULT_TRANSFORMATION_PATH, anObject);

        TransformationFactory factory = TransformationResources.getTransformationFactory();
        Document document = (Document) factory.transform(parameters);

        writer.writeTo(aFile, document);
    }

}
