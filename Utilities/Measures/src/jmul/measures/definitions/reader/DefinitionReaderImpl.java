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

package jmul.measures.definitions.reader;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import static jmul.measures.definitions.reader.DefinitionMarkups.AbbreviationAttribute;
import static jmul.measures.definitions.reader.DefinitionMarkups.CategoryAttribute;
import static jmul.measures.definitions.reader.DefinitionMarkups.DefinitionElement;
import static jmul.measures.definitions.reader.DefinitionMarkups.NameAttribute;
import static jmul.measures.definitions.reader.DefinitionMarkups.NormalizedValueAttribute;
import static jmul.measures.definitions.reader.DefinitionMarkups.UnitElement;

import jmul.xml.SubelementList;
import static jmul.xml.XmlParserHelper.assertHasXmlSubelements;
import static jmul.xml.XmlParserHelper.assertMatchesXmlElement;
import static jmul.xml.XmlParserHelper.getXmlAttribute;
import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;


/**
 * An implementation of DefinitionReader.
 */
public class DefinitionReaderImpl implements DefinitionReader {

    /**
     * The class member contains a reader for xml documents.
     */
    private XmlDocumentReader reader;

    /**
     * The default constructor.
     */
    public DefinitionReaderImpl() {

        reader = new XmlDocumentReaderImpl();
    }

    /**
     * The method reads the definitions from a definition file and updates a
     * system of measurement accordingly.
     *
     * @param aFilename
     *        the filename of a definition file
     *
     * @return a reference to the updated system of measurement
     *
     * @throws SAXException
     *         This exception is thrown if the xml structure is invalid
     * @throws IOException
     *         This exception is thrown if the IO operation couldn't be executed
     */
    @Override
    public Definition readDefinition(String aFilename) throws SAXException, IOException {

        Document document = loadDocument(aFilename);
        return parseDocument(document);
    }

    /**
     * The method reads the definitions from a definition file and updates a
     * system of measurement accordingly.
     *
     * @param aFile
     *        a definition file
     *
     * @return a reference to the updated system of measurement
     *
     * @throws SAXException
     *         This exception is thrown if the xml structure is invalid
     * @throws IOException
     *         This exception is thrown if the IO operation couldn't be executed
     */
    @Override
    public Definition readDefinition(File aFile) throws SAXException, IOException {

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

        return reader.parseDocument(aFilename);
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

        return reader.parseDocument(aFile);
    }

    /**
     * Parses the content of a definition file.
     *
     * @param aDocument
     *
     * @return a definition of units of measurement
     */
    private Definition parseDocument(Document aDocument) {

        Node rootNode = aDocument.getDocumentElement();
        assertMatchesXmlElement(rootNode, DefinitionElement);


        Node categoryAttribute = getXmlAttribute(rootNode, CategoryAttribute);
        String category = categoryAttribute.getTextContent();


        SubelementList rootSubelements = new SubelementList(rootNode);
        assertHasXmlSubelements(rootSubelements);

        for (Node unitElement : rootSubelements) {

            assertMatchesXmlElement(unitElement, UnitElement);
        }


        List<Unit> unitEntries = new ArrayList<Unit>();

        for (Node unitElement : rootSubelements) {

            Node nameAttribute = getXmlAttribute(unitElement, NameAttribute);
            String unitName = nameAttribute.getTextContent();

            Node abbreviationAttribute = getXmlAttribute(unitElement, AbbreviationAttribute);
            String unitAbbreviation = abbreviationAttribute.getTextContent();

            Node normalizedValueAttribute = getXmlAttribute(unitElement, NormalizedValueAttribute);
            double normalizedValue = Double.parseDouble(normalizedValueAttribute.getTextContent());

            Unit unit = new Unit(unitName, unitAbbreviation, normalizedValue);

            unitEntries.add(unit);
        }


        return new Definition(category, unitEntries);
    }

}
