/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2014  Kristian Kutin
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


import jmul.xml.XmlMarkup;
import jmul.xml.XmlMarkupType;


/**
 * This enumeration defines a set of xml elements and xml attributes which are
 * found in a definition configuration file.
 *
 * @author Kristian Kutin
 */
public enum DefinitionMarkups implements XmlMarkup {


    CategoryAttribute("category", XmlMarkupType.XmlAttribute),
    NameAttribute("name", XmlMarkupType.XmlAttribute),
    NormalizedValueAttribute("normalized-value", XmlMarkupType.XmlAttribute),
    AbbreviationAttribute("abbreviation", XmlMarkupType.XmlAttribute),

    DefinitionElement("definition", XmlMarkupType.XmlElement),
    UnitElement("unit", XmlMarkupType.XmlElement);


    /**
     * The markup's name.
     */
    private final String name;

    /**
     * The type of this enum element.
     */
    private final XmlMarkupType type;


    /**
     * Creates an enumeration element.
     *
     * @param aName
     *        a markup name
     * @param aType
     *        the type of this enumeration element
     */
    private DefinitionMarkups(String aName, XmlMarkupType aType) {

        name = aName;
        type = aType;
    }

    /**
     * Returns the name of the xml element or xml attribute which this entity
     * represents.
     *
     * @return a name
     */
    @Override
    public String getTagName() {

        return name;
    }

    /**
     * Checks if this entity represents an xml element.
     *
     * @return <code>true</code> if this entity represents an xml element, else
     *         <code>false</code>
     */
    @Override
    public boolean isXmlElement() {

        return XmlMarkupType.XmlElement.equals(type);
    }

    /**
     * Checks if this entity represents an xml attribute.
     *
     * @return <code>true</code> if this entity represents an xml attribute,
     *         else <code>false</code>
     */
    @Override
    public boolean isXmlAttribute() {

        return XmlMarkupType.XmlAttribute.equals(type);
    }

    /**
     * Checks if the specified name matches the name of this markup.
     *
     * @param aName
     *
     * @return <code>true</code> if the specified name matches the name of this
     *         markup, else <code>false</code>
     */
    @Override
    public boolean equalsTagName(String aName) {

        boolean result = getTagName().equals(aName);
        return result;
    }

    /**
     * Checks if the specified xml markup this markup.
     *
     * @param aName
     *
     * @return <code>true</code> if the specified xml markup matches this
     *         markup, else <code>false</code>
     */
    @Override
    public boolean equalsXmlMarkup(XmlMarkup anXmlMarkup) {

        boolean result =
            (this.isXmlAttribute() && anXmlMarkup.isXmlAttribute() && equalsTagName(anXmlMarkup.getTagName())) ||
            (this.isXmlElement() && anXmlMarkup.isXmlElement() && equalsTagName(anXmlMarkup.getTagName()));
        return result;
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getTagName();
    }

}
