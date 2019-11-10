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
 * A utility class for XML markup enumeration elements.
 *
 * @author Kristian Kutin
 */
public final class XmlMarkupHelper {

    /**
     * The default constructor.
     */
    private XmlMarkupHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks the tag names of the specified XML markups.
     *
     * @param anXmlMarkup1
     *        an xml markup
     * @param anXmlMarkup2
     *        an xml markup
     *
     * @return <code>true</code> if the tag names are equal,
     *         else <code>false</code>
     */
    public static boolean equalTagNames(XmlMarkup anXmlMarkup1, XmlMarkup anXmlMarkup2) {

        return equalTagNames(anXmlMarkup1.getTagName(), anXmlMarkup2.getTagName());
    }

    /**
     * Checks the specified tag names.
     *
     * @param name1
     *        a tag name
     * @param name2
     *        a tag name
     *
     * @return <code>true</code> if the tag names are equal,
     *         else <code>false</code>
     */
    public static boolean equalTagNames(String name1, String name2) {

        return (name1 != null) && (name2 != null) && (name1.equals(name2));
    }

    /**
     * Checks the specified XML markups.
     *
     * @param anXmlMarkup1
     *        an xml markup
     * @param anXmlMarkup2
     *        an xml markup
     *
     * @return <code>true</code> if both markups are attributes of an XML element and
     *         have the same tag name, else <code>false</code>
     */
    public static boolean isSameXmlAttribute(XmlMarkup anXmlMarkup1, XmlMarkup anXmlMarkup2) {

        return anXmlMarkup1.isXmlAttribute() && anXmlMarkup2.isXmlAttribute() &&
               equalTagNames(anXmlMarkup1, anXmlMarkup2);
    }

    /**
     * Checks the specified XML markups.
     *
     * @param anXmlMarkup1
     *        an xml markup
     * @param anXmlMarkup2
     *        an xml markup
     *
     * @return <code>true</code> if both markups are XML elements and have the same tag name,
     *         else <code>false</code>
     */
    public static boolean isSameXmlElement(XmlMarkup anXmlMarkup1, XmlMarkup anXmlMarkup2) {

        return anXmlMarkup1.isXmlElement() && anXmlMarkup2.isXmlElement() && equalTagNames(anXmlMarkup1, anXmlMarkup2);
    }

    /**
     * Checks the specified XML markups.
     *
     * @param anXmlMarkup1
     *        an xml markup
     * @param anXmlMarkup2
     *        an xml markup
     *
     * @return <code>true</code> if both markups are either the same XML Element or the
     *         same attribute, else <code>false</code>
     */
    public static boolean equalXmlMarkups(XmlMarkup anXmlMarkup1, XmlMarkup anXmlMarkup2) {

        return isSameXmlAttribute(anXmlMarkup1, anXmlMarkup2) || isSameXmlElement(anXmlMarkup1, anXmlMarkup2);
    }

}
