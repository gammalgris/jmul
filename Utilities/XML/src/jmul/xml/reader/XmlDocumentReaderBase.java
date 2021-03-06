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


import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jmul.misc.exceptions.InstantiationException;

import jmul.string.TextHelper;


/**
 * A base implementation of an XML document reader.
 *
 * @author Kristian Kutin
 */
public abstract class XmlDocumentReaderBase implements XmlDocumentReader {

    /**
     * A document builder factory.
     */
    private DocumentBuilderFactory factory;

    /**
     * The default constructor.
     */
    protected XmlDocumentReaderBase() {

        factory = DocumentBuilderFactory.newInstance();

        try {

            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        } catch (ParserConfigurationException e) {

            String message = "Couldn't instantiate the XML document reader!";
            throw new InstantiationException(message, e);
        }
    }

    /**
     * A getter method.
     *
     * @return a document builder factory
     */
    protected DocumentBuilderFactory getDocumentBuilderFactory() {

        return factory;
    }

    /**
     * Returns a new instance of a document builder.
     *
     * @return a document builder
     */
    protected DocumentBuilder newDocumentBuilder() {

        DocumentBuilder builder = null;

        try {

            builder = getDocumentBuilderFactory().newDocumentBuilder();

        } catch (ParserConfigurationException e) {

            String message = TextHelper.concatenateStrings("Couldn't instantiate ", this.getClass().getName(), "!");
            throw new ReaderException(message, e);
        }

        return builder;
    }

}
