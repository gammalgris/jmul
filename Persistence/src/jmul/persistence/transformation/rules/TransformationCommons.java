/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.persistence.transformation.rules;


/**
 * This interface contains only constants which are required for a certain
 * transformation path.
 *
 * @author Kristian Kutin
 */
@Deprecated
public interface TransformationCommons {

    /**
     * The name of a prerequisite.
     */
    String OBJECT_CACHE = "object cache";

    /**
     * The name of a prerequisite.
     */
    String XML_DOCUMENT = "xml document";

    /**
     * The name of a prerequisite.
     */
    String ROOT_ELEMENT = "root element";

    /**
     * The name of a prerequisite.
     */
    String DECLARED_ELEMENT_TYPE = "declared element type";

    /**
     * The name of a prerequisite.
     */
    String DECLARED_KEY_TYPE = "declared key type";

    /**
     * The name of a prerequisite.
     */
    String DECLARED_VALUE_TYPE = "declared value type";


    /* ----- XML Elements ----- */

    /**
     * The tagname of an xml element.
     */
    String ELEMENT_ELEMENT_TAGNAME = XmlComponent.ElementElement.getTagname();

    /**
     * The tagname of an xml element.
     */
    String ENTRY_ELEMENT_TAGNAME = XmlComponent.EntryElement.getTagname();

    /**
     * The tagname of an xml element.
     */
    String FIELD_ELEMENT_TAGNAME = XmlComponent.FieldElement.getTagname();

    /**
     * The tagname of an xml element.
     */
    String OBJECT_ELEMENT_TAGNAME = XmlComponent.ObjectElement.getTagname();

    /**
     * The tagname of an xml element.
     */
    String OBJECTS_ELEMENT_TAGNAME = XmlComponent.ObjectsElement.getTagname();


    /* ----- XML Attributes ----- */

    /**
     * The tagname of an xml attribute.
     */
    String DECLARED_TYPE_ATTRIBUTE_TAGNAME =
        XmlComponent.DeclaredTypeAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String DECLARED_ELEMENT_TYPE_ATTRIBUTE_TAGNAME =
        XmlComponent.DeclaredElementTypeAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String DECLARED_KEY_TYPE_ATTRIBUTE_TAGNAME =
        XmlComponent.DeclaredKeyTypeAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String DECLARED_VALUE_TYPE_ATTRIBUTE_TAGNAME =
        XmlComponent.DeclaredValueTypeAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String FORMAT_ATTRIBUTE_TAGNAME =
        XmlComponent.FormatAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String ID_ATTRIBUTE_TAGNAME = XmlComponent.IDAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String NAME_ATTRIBUTE_TAGNAME = XmlComponent.NameAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String REFERENCED_ELEMENT_ATTRIBUTE_TAGNAME =
        XmlComponent.ReferencedElementAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String REFERENCED_KEY_ATTRIBUTE_TAGNAME =
        XmlComponent.ReferencedKeyAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String REFERENCED_OBJECT_ATTRIBUTE_TAGNAME =
        XmlComponent.ReferencedObjectAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String REFERENCED_VALUE_ATTRIBUTE_TAGNAME =
        XmlComponent.ReferencedValueAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String TYPE_ATTRIBUTE_TAGNAME = XmlComponent.TypeAttribute.getTagname();

    /**
     * The tagname of an xml attribute.
     */
    String VALUE_ATTRIBUTE_TAGNAME = XmlComponent.ValueAttribute.getTagname();


    /* ----- Misc ----- */

    /**
     * A date format string.
     */
    String DEFAULT_DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";

}
