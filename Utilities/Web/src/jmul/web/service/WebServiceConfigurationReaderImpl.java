/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package jmul.web.service;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import static jmul.web.service.WebServiceConfigurationMarkups.PARAMETERS_ELEMENT;
import static jmul.web.service.WebServiceConfigurationMarkups.PARAMETER_ELEMENT;
import static jmul.web.service.WebServiceConfigurationMarkups.SCRIPT_INVOCATION_ELEMENT;
import static jmul.web.service.WebServiceConfigurationMarkups.SCRIPT_PATH_ATTRIBUTE;
import static jmul.web.service.WebServiceConfigurationMarkups.WEB_PATH_ATTRIBUTE;
import static jmul.web.service.WebServiceConfigurationMarkups.WEB_SERVICE_ELEMENT;

import jmul.xml.ParsingException;
import jmul.xml.SubelementList;
import jmul.xml.SubelementMap;
import static jmul.xml.XmlParserHelper.assertHasXmlSubelements;
import static jmul.xml.XmlParserHelper.assertMatchesXmlElement;
import static jmul.xml.XmlParserHelper.getXmlAttribute;
import static jmul.xml.XmlParserHelper.hasXmlSubelement;
import static jmul.xml.XmlParserHelper.matchesXmlElement;
import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;


/**
 * An implementation of a web service confiugration reader.
 *
 * @author Kristian Kutin
 */
public class WebServiceConfigurationReaderImpl implements WebServiceConfigurationReader {

    /**
     * The class member contains a reader for xml documents.
     */
    private XmlDocumentReader reader;

    /**
     * The default constructor.
     */
    public WebServiceConfigurationReaderImpl() {

        super();

        reader = new XmlDocumentReaderImpl();
    }

    /**
     * The method reads the web service configuration from a configuration file.
     *
     * @param aFilename
     *        the filename of a definition file
     *
     * @return the web service configuration
     *
     * @throws SAXException
     *         This exception is thrown if the xml structure is invalid
     * @throws IOException
     *         This exception is thrown if the IO operation couldn't be executed
     */
    @Override
    public WebServiceConfiguration readConfiguration(String aFilename) throws SAXException, IOException {

        Document document = loadDocument(aFilename);
        return parseDocument(document);
    }

    /**
     * The method reads the web service configuration from a configuration file.
     *
     * @param aFile
     *        a definition file
     *
     * @return the web service configuration
     *
     * @throws SAXException
     *         This exception is thrown if the xml structure is invalid
     * @throws IOException
     *         This exception is thrown if the IO operation couldn't be executed
     */
    @Override
    public WebServiceConfiguration readConfiguration(File aFile) throws SAXException, IOException {

        Document document = loadDocument(aFile);
        return parseDocument(document);
    }

    /**
     * The method loads the specified xml file.
     *
     * @param aFilename
     *
     * @return the XML document
     *
     * @throws SAXException
     *         the exception is thrown if the xml structure is invalid
     * @throws IOException
     *         the exception is thrown if the xml configuration file couldn't be read
     */
    private Document loadDocument(String aFilename) throws SAXException, IOException {

        return reader.readFrom(aFilename);
    }

    /**
     * The method loads the specified xml file.
     *
     * @param aFile
     *
     * @return the XML document
     *
     * @throws SAXException
     *         the exception is thrown if the xml structure is invalid
     * @throws IOException
     *         the exception is thrown if the xml configuration file couldn't be read
     */
    private Document loadDocument(File aFile) throws SAXException, IOException {

        return reader.readFrom(aFile);
    }

    /**
     * Parses the content of a web service configuration file.
     *
     * @param aDocument
     *
     * @return a definition of units of measurement
     */
    private static WebServiceConfiguration parseDocument(Document aDocument) {

        Node rootNode = aDocument.getDocumentElement();
        assertMatchesXmlElement(rootNode, WEB_SERVICE_ELEMENT);

        Node webPathAttribute = getXmlAttribute(rootNode, WEB_PATH_ATTRIBUTE);
        String webPath = webPathAttribute.getTextContent();


        SubelementList rootSubelements = new SubelementList(rootNode);
        assertHasXmlSubelements(rootSubelements, 1);


        Node subelement = rootSubelements.iterator().next();


        if (matchesXmlElement(subelement, SCRIPT_INVOCATION_ELEMENT)) {

            Node scriptPathAttribute = getXmlAttribute(subelement, SCRIPT_PATH_ATTRIBUTE);
            String scriptPath = scriptPathAttribute.getTextContent();

            List<String> parameters;
            SubelementMap scriptInvocationSubelements = new SubelementMap(subelement);

            if (hasXmlSubelement(scriptInvocationSubelements, PARAMETERS_ELEMENT)) {

                Node parametersNode = scriptInvocationSubelements.getSubelement(PARAMETERS_ELEMENT);
                parameters = parseParameters(parametersNode);

            } else {

                parameters = new ArrayList<>();
            }

            return new WebServiceConfigurationImpl(webPath, scriptPath, parameters);

        } else {

            String message = "An unexpected XML element (" + subelement.getNodeName() + ") was encountered!";
            throw new ParsingException(message);
        }
    }

    /**
     * Parses the parameters as specified in the configuration file.
     *
     * @param aNode
     *        the local root node for parameter informations
     *
     * @return a parameter list
     */
    private static List<String> parseParameters(Node aNode) {

        assertMatchesXmlElement(aNode, PARAMETERS_ELEMENT);

        SubelementList rootSubelements = new SubelementList(aNode);

        List<Node> parameterNodes = rootSubelements.getSubelements(PARAMETER_ELEMENT);
        List<String> parameters = new ArrayList<>();

        for (Node parameterNode : parameterNodes) {

            String parameter = parameterNode.getTextContent();

            parameters.add(parameter);
        }

        return parameters;
    }

}
