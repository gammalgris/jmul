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

package jmul.transformation;


import jmul.transformation.configuration.ConfigurationReader;
import jmul.transformation.configuration.ConfigurationReaderImpl;

import jmul.transformation.creation.ObjectFactory;
import jmul.transformation.creation.ObjectFactoryImpl;
import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;


/**
 * A utility class which contains required resources.
 *
 * @author Kristian Kutin
 */
public final class TransformationResources {

    /**
     * A singleton.
     */
    private static XmlDocumentReader xmlDocumentReaderSingleton;

    /**
     * A singleton.
     */
    private static ConfigurationReader configurationReaderSingleton;

    /**
     * A singleton.
     */
    private static TransformationFactory transformationFactorySingleton;

    /**
     * A singleton.
     */
    private static ObjectFactory objectFactorySingleton;

    /**
     * The default constructor.
     */
    private TransformationResources() {
    }

    /**
     * Returns a reference to an xml document reader.
     *
     * @return an xml document reader
     */
    public static synchronized XmlDocumentReader getXmlDocumentReader() {

        if (xmlDocumentReaderSingleton == null) {

            xmlDocumentReaderSingleton = new XmlDocumentReaderImpl();
        }

        return xmlDocumentReaderSingleton;
    }

    /**
     * Returns a reference to a configuration reader.
     *
     * @return a configuration reader
     */
    public static synchronized ConfigurationReader getConfigurationReader() {

        if (configurationReaderSingleton == null) {

            configurationReaderSingleton = new ConfigurationReaderImpl();
        }

        return configurationReaderSingleton;
    }

    /**
     * Returns a reference to a transformation factory.
     *
     * @return a transformation factory
     */
    public static synchronized TransformationFactory getTransformationFactory() {

        if (transformationFactorySingleton == null) {

            transformationFactorySingleton = new TransformationFactoryImpl();
        }

        return transformationFactorySingleton;
    }

    /**
     * Returns a reference to an object factory.
     *
     * @return an object factory
     */
    public static synchronized ObjectFactory getObjectFactory() {

        if (objectFactorySingleton == null) {

            objectFactorySingleton = new ObjectFactoryImpl();
        }

        return objectFactorySingleton;
    }

}
