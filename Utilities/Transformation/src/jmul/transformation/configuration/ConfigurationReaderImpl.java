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

package jmul.transformation.configuration;


import java.io.IOException;

import java.lang.reflect.Constructor;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.string.StringConcatenator;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationResources;
import jmul.transformation.TransformationRule;
import static jmul.transformation.configuration.ConfigurationMarkups.ClassnameAttribute;
import static jmul.transformation.configuration.ConfigurationMarkups.ClasspathAttribute;
import static jmul.transformation.configuration.ConfigurationMarkups.DestinationAttribute;
import static jmul.transformation.configuration.ConfigurationMarkups.ImplementationElement;
import static jmul.transformation.configuration.ConfigurationMarkups.OriginAttribute;
import static jmul.transformation.configuration.ConfigurationMarkups.PriorityAttribute;
import static jmul.transformation.configuration.ConfigurationMarkups.RuleElement;
import static jmul.transformation.configuration.ConfigurationMarkups.TransformationPathElement;

import jmul.xml.ParsingException;
import jmul.xml.SubelementMap;
import static jmul.xml.XmlParserHelper.assertHasXmlSubelements;
import static jmul.xml.XmlParserHelper.assertMatchesXmlElement;
import static jmul.xml.XmlParserHelper.getXmlAttribute;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;


/**
 * An implementation of a configuration reader.
 *
 * @author Kristian Kutin
 */
public class ConfigurationReaderImpl implements ConfigurationReader {

    /**
     * The default constructor.
     */
    public ConfigurationReaderImpl() {

        super();
    }

    /**
     * Parses the configuration of a transformation rule.
     *
     * @param aFilename
     *        the filename of the transformation rule configuration
     *
     * @return a transformation rule
     *
     * @throws SAXException
     *         This exception is thrown when the configuration file is invalid
     * @throws IOException
     *         This exception is thrown when the configuration file couldn't be
     *         read
     */
    @Override
    public TransformationRule parseConfiguration(String aFilename) throws SAXException, IOException {

        ConfigurationData data = new ConfigurationData(aFilename);
        TransformationRule rule = newTransformationRule(data);

        return rule;
    }

    /**
     * Parses the configuration of a transformation rule.
     *
     * @param aDocument
     *        an xml document
     *
     * @return a transformation rule
     *
     * @throws SAXException
     *         This exception is thrown when the configuration file is invalid
     * @throws IOException
     *         This exception is thrown when the configuration file couldn't be
     *         read
     */
    @Override
    public TransformationRule parseConfiguration(Document aDocument) throws SAXException, IOException {

        ConfigurationData data = new ConfigurationData(aDocument);
        TransformationRule rule = newTransformationRule(data);

        return rule;
    }

    /**
     * The method creates a transformation rule according to the specified
     * configuration.
     *
     * @param someData
     *        some configuration data
     *
     * @return a transformation rule
     */
    private TransformationRule newTransformationRule(ConfigurationData someData) {

        ClassDefinition definition = null;

        try {

            if (someData.existsClasspath()) {

                definition = ClassHelper.getClass(someData.getClassname(), someData.getClasspath());

            } else {

                definition = ClassHelper.getClass(someData.getClassname());
            }

        } catch (ClassNotFoundException e) {

            StringConcatenator message =
                new StringConcatenator("Couldn't find the class " + someData.getClassname() + "!");
            throw new TransformationException(message.toString(), e);
        }


        Class[] constructorParameterSignature = { String.class, String.class, Integer.TYPE };
        Class c = definition.getType();
        Constructor constructor = null;

        try {

            constructor = c.getConstructor(constructorParameterSignature);

        } catch (NoSuchMethodException e) {

            StringConcatenator buffer =
                new StringConcatenator("The class ", c.getName(),
                                       " doesn't have a constructor with the specified parameter signature {");

            int length = constructorParameterSignature.length;
            for (int a = 0; a < length; a++) {

                buffer.append(constructorParameterSignature[a].getName());

                if (a < (length - 1)) {
                    buffer.append(", ");
                }
            }

            buffer.append("}!");
            throw new TransformationException(buffer.toString(), e);
        }


        Object[] parameters = { someData.getOrigin(), someData.getDestination(), someData.getPriority() };

        TransformationRule rule = null;
        try {

            rule = (TransformationRule) constructor.newInstance(parameters);

        } catch (Exception e) {

            StringConcatenator message =
                new StringConcatenator("An exception was thrown while invoking the constructor ", constructor,
                                       " of the class ", c, "!");
            throw new TransformationException(message.toString(), e);
        }

        return rule;
    }

    /**
     * This inner class is responsible for parsing the configuration file.
     *
     * @author Kristian Kutin
     */
    public class ConfigurationData {

        /**
         * The class member contains informations about the transformation path.
         */
        private final String origin;

        /**
         * The class member contains informations about the transformation path.
         */
        private final String destination;

        /**
         * The class name of the transformation rule.
         */
        private final String classname;

        /**
         * The classpath of the transformation rule class.
         */
        private final String classpath;

        /**
         * The priority of the transformation rule.
         */
        private final int priority;

        /**
         * Parses the configuration file.
         *
         * @param aFilename
         *        a filename
         *
         * @throws SAXException
         *         This exception is thrown when the configuration file is
         *         invalid
         * @throws IOException
         *         This exception is thrown when the configuration file couldn't
         *         be read
         */
        ConfigurationData(String aFilename) throws SAXException, IOException {

            this(TransformationResources.getXmlDocumentReader().parseDocument(aFilename));
        }

        /**
         * Parses the configuration.
         *
         * @param aDocument
         *        the xml document containing configuration data
         */
        ConfigurationData(Document aDocument) {

            this(aDocument.getDocumentElement());
        }

        /**
         * Parses the configuration.
         *
         * @param aRootNode
         *        the root node of the xml document which contains the
         *        configuration data
         */
        private ConfigurationData(Node aRootNode) {

            assertMatchesXmlElement(aRootNode, RuleElement);


            Node priorityAttribute = getXmlAttribute(aRootNode, PriorityAttribute);
            priority = Integer.parseInt(priorityAttribute.getTextContent());


            SubelementMap subelements = new SubelementMap(aRootNode);
            assertHasXmlSubelements(subelements);


            Node transformationPathElement = subelements.getSubelement(TransformationPathElement);

            Node originAttribute = getXmlAttribute(transformationPathElement, OriginAttribute);
            origin = originAttribute.getTextContent();

            Node destinationAttribute = getXmlAttribute(transformationPathElement, DestinationAttribute);
            destination = destinationAttribute.getTextContent();


            Node implementationElement = subelements.getSubelement(ImplementationElement);

            Node classnameAttribute = getXmlAttribute(implementationElement, ClassnameAttribute);
            classname = classnameAttribute.getTextContent();


            String tmpClasspath = null;

            try {

                Node classpathAttribute = getXmlAttribute(implementationElement, ClasspathAttribute);
                tmpClasspath = classpathAttribute.getTextContent();

            } catch (ParsingException e) {

                tmpClasspath = null;
            }

            classpath = tmpClasspath;
        }

        /**
         * A getter-method.
         *
         * @return the origin of a transformation path
         */
        public String getOrigin() {

            return origin;
        }

        /**
         * A getter-method.
         *
         * @return the destination of a transformation path
         */
        public String getDestination() {

            return destination;
        }

        /**
         * A getter-method.
         *
         * @return the class name of a transformation rule
         */
        public String getClassname() {

            return classname;
        }

        /**
         * A getter-method.
         *
         * @return the classpath of a transformation rule
         */
        public String getClasspath() {

            return classpath;
        }

        /**
         * Checks if a classpath has been specified.
         *
         * @return <code>true</code> if a classpath has been specified,
         *         else <code>false</code>
         */
        public boolean existsClasspath() {

            return classpath != null;
        }

        /**
         * A getter-method.
         *
         * @return the priority of a transformation rule
         */
        public int getPriority() {

            return priority;
        }

    }

}
