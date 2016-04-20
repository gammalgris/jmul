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


/**
 * This enumeration defines a set of xml elements and xml attributes which are
 * used in files which contain persisted objects.
 *
 * @author Kristian Kutin
 */
public enum PersistenceMarkups implements XmlMarkup {


    DECLARED_TYPE_ATTRIBUTE("declaredType", XmlMarkupType.XmlAttribute),
    DECLARED_ELEMENT_TYPE_ATTRIBUTE("declaredElementType", XmlMarkupType.XmlAttribute),
    DECLARED_KEY_TYPE_ATTRIBUTE("declaredKeyType", XmlMarkupType.XmlAttribute),
    DECLARED_VALUE_TYPE_ATTRIBUTE("declaredValueType", XmlMarkupType.XmlAttribute),
    FORMAT_ATTRIBUTE("format", XmlMarkupType.XmlAttribute),
    ID_ATTRIBUTE("id", XmlMarkupType.XmlAttribute),
    NAME_ATTRIBUTE("name", XmlMarkupType.XmlAttribute),
    REFERENCED_ELEMENT_ATTRIBUTE("referencedElement", XmlMarkupType.XmlAttribute),
    REFERENCED_KEY_ATTRIBUTE("referencedKey", XmlMarkupType.XmlAttribute),
    REFERENCED_OBJECT_ATTRIBUTE("referencedObject", XmlMarkupType.XmlAttribute),
    REFERENCED_VALUE_ATTRIBUTE("referencedValue", XmlMarkupType.XmlAttribute),
    TYPE_ATTRIBUTE("type", XmlMarkupType.XmlAttribute),
    VALUE_ATTRIBUTE("value", XmlMarkupType.XmlAttribute),

    ELEMENT_ELEMENT("element", XmlMarkupType.XmlElement),
    ENTRY_ELEMENT("entry", XmlMarkupType.XmlElement),
    FIELD_ELEMENT("field", XmlMarkupType.XmlElement),
    OBJECT_ELEMENT("object", XmlMarkupType.XmlElement),
    OBJECTS_ELEMENT("objects", XmlMarkupType.XmlElement), ;


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
