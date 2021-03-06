/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.xml;


/**
 * This interface describes an entity which represents an xml tag.
 *
 * @author Kristian Kutin
 */
public interface XmlMarkup {

    /**
     * Returns the tagname of the xml element or xml attribute which this entity
     * represents.
     *
     * @return a tagname
     */
    String getTagName();

    /**
     * Checks if this entity represents an xml element.
     *
     * @return <code>true</code> if this entity represents an xml element, else
     *         <code>false</code>
     */
    boolean isXmlElement();

    /**
     * Checks if this entity represents an xml attribute.
     *
     * @return <code>true</code> if this entity represents an xml attribute,
     *         else <code>false</code>
     */
    boolean isXmlAttribute();

    /**
     * Checks if the specified name matches the name of this markup.
     *
     * @param aName
     *        the tag name of an xml markup
     *
     * @return <code>true</code> if the specified name matches the name of this
     *         markup, else <code>false</code>
     */
    boolean equalsTagName(String aName);

    /**
     * Checks if the specified xml markup this markup.
     *
     * @param anXmlMarkup
     *        an xml markup
     *
     * @return <code>true</code> if the specified xml markup matches this
     *         markup, else <code>false</code>
     */
    boolean equalsXmlMarkup(XmlMarkup anXmlMarkup);

}
