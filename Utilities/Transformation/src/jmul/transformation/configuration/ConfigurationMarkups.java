/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.transformation.configuration;


import jmul.xml.XmlMarkup;
import jmul.xml.XmlMarkupHelper;
import jmul.xml.XmlMarkupType;
import static jmul.xml.XmlMarkupType.XML_ATTRIBUTE;
import static jmul.xml.XmlMarkupType.XML_ELEMENT;


/**
 * This enumeration defines a set of xml elements and xml attributes which are
 * found in a configuration file.
 *
 * @author Kristian Kutin
 */
public enum ConfigurationMarkups implements XmlMarkup {


    CLASSNAME_ATTRIBUTE("classname", XML_ATTRIBUTE),
    CLASSPATH_ATTRIBUTE("classpath", XML_ATTRIBUTE),
    DESTINATION_ATTRIBUTE("destination", XML_ATTRIBUTE),
    ORIGIN_ATTRIBUTE("origin", XML_ATTRIBUTE),
    PRIORITY_ATTRIBUTE("priority", XML_ATTRIBUTE),

    IMPLEMENTATION_ELEMENT("implementation", XML_ELEMENT),
    RULE_ELEMENT("rule", XML_ELEMENT),
    TRANSFORMATION_PATH_ELEMENT("transformation-path", XML_ELEMENT);


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
    private ConfigurationMarkups(String aName, XmlMarkupType aType) {

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
     *        a markup name
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
