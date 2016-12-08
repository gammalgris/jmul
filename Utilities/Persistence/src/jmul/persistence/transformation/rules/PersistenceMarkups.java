/*
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

package jmul.persistence.transformation.rules;


import jmul.xml.XmlMarkup;
import jmul.xml.XmlMarkupType;
import static jmul.xml.XmlMarkupType.XML_ATTRIBUTE;
import static jmul.xml.XmlMarkupType.XML_ELEMENT;


/**
 * This enumeration defines a set of xml elements and xml attributes which are
 * used in files which contain persisted objects.
 *
 * @author Kristian Kutin
 */
public enum PersistenceMarkups implements XmlMarkup {


    DECLARED_TYPE_ATTRIBUTE("declaredType", XML_ATTRIBUTE),
    DECLARED_ELEMENT_TYPE_ATTRIBUTE("declaredElementType", XML_ATTRIBUTE),
    DECLARED_KEY_TYPE_ATTRIBUTE("declaredKeyType", XML_ATTRIBUTE),
    DECLARED_VALUE_TYPE_ATTRIBUTE("declaredValueType", XML_ATTRIBUTE),
    FORMAT_ATTRIBUTE("format", XML_ATTRIBUTE),
    ID_ATTRIBUTE("id", XML_ATTRIBUTE),
    NAME_ATTRIBUTE("name", XML_ATTRIBUTE),
    REFERENCED_ELEMENT_ATTRIBUTE("referencedElement", XML_ATTRIBUTE),
    REFERENCED_KEY_ATTRIBUTE("referencedKey", XML_ATTRIBUTE),
    REFERENCED_OBJECT_ATTRIBUTE("referencedObject", XML_ATTRIBUTE),
    REFERENCED_VALUE_ATTRIBUTE("referencedValue", XML_ATTRIBUTE),
    TYPE_ATTRIBUTE("type", XML_ATTRIBUTE),
    VALUE_ATTRIBUTE("value", XML_ATTRIBUTE),

    ELEMENT_ELEMENT("element", XML_ELEMENT),
    ENTRY_ELEMENT("entry", XML_ELEMENT),
    FIELD_ELEMENT("field", XML_ELEMENT),
    OBJECT_ELEMENT("object", XML_ELEMENT),
    OBJECTS_ELEMENT("objects", XML_ELEMENT), ;


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
    private PersistenceMarkups(String aName, XmlMarkupType aType) {

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

        return XML_ELEMENT.equals(type);
    }

    /**
     * Checks if this entity represents an xml attribute.
     *
     * @return <code>true</code> if this entity represents an xml attribute,
     *         else <code>false</code>
     */
    @Override
    public boolean isXmlAttribute() {

        return XML_ATTRIBUTE.equals(type);
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

        return getTagName().equals(aName);
    }

    /**
     * Checks if the specified xml markup this markup.
     *
     * @param anXmlMarkup
     *
     * @return <code>true</code> if the specified xml markup matches this
     *         markup, else <code>false</code>
     */
    @Override
    public boolean equalsXmlMarkup(XmlMarkup anXmlMarkup) {

        return (this.isXmlAttribute() && anXmlMarkup.isXmlAttribute() && equalsTagName(anXmlMarkup.getTagName())) ||
               (this.isXmlElement() && anXmlMarkup.isXmlElement() && equalsTagName(anXmlMarkup.getTagName()));
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
