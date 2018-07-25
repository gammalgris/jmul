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


package jmul.xml;


import static jmul.xml.XmlMarkupType.XML_ATTRIBUTE;
import static jmul.xml.XmlMarkupType.XML_ELEMENT;


/**
 * An implementation of an XML markup entity. This is used when XML markups are not defined
 * in enumeration classes.
 *
 * @author Kristian Kutin
 */
public class XmlMarkupImpl implements XmlMarkup {

    /**
     * The markup's name.
     */
    private final String name;

    /**
     * The type of this enum element.
     */
    private final XmlMarkupType type;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aName
     *        a tag name
     * @param aType
     *        a markup type
     */
    public XmlMarkupImpl(String aName, XmlMarkupType aType) {

        super();

        name = aName;
        type = aType;
    }

    /**
     * Returns the tagname of the xml element or xml attribute which this entity
     * represents.
     *
     * @return a tagname
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
     *        the tag name of an xml markup
     *
     * @return <code>true</code> if the specified name matches the name of this
     *         markup, else <code>false</code>
     */
    @Override
    public boolean equalsTagName(String aName) {

        return XmlMarkupHelper.equalTagNames(this.getTagName(), aName);
    }

    /**
     * Checks if the specified xml markup this markup.
     *
     * @param anXmlMarkup
     *        an xml markup
     *
     * @return <code>true</code> if the specified xml markup matches this
     *         markup, else <code>false</code>
     */
    @Override
    public boolean equalsXmlMarkup(XmlMarkup anXmlMarkup) {

        return XmlMarkupHelper.equalXmlMarkups(this, anXmlMarkup);
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
