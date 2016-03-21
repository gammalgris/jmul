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
 * This enumeration lists the tagnames of all xml elements and xml attributes
 * which are used within a file which contains persisted objects.
 *
 * @author Kristian Kutin
 */
@Deprecated
enum XmlComponent {

    // XML Attributes
    DeclaredTypeAttribute("declaredType"),
    DeclaredElementTypeAttribute("declaredElementType"),
    DeclaredKeyTypeAttribute("declaredKeyType"),
    DeclaredValueTypeAttribute("declaredValueType"),
    FormatAttribute("format"),
    IDAttribute("id"),
    NameAttribute("name"),
    ReferencedElementAttribute("referencedElement"),
    ReferencedKeyAttribute("referencedKey"),
    ReferencedObjectAttribute("referencedObject"),
    ReferencedValueAttribute("referencedValue"),
    TypeAttribute("type"),
    ValueAttribute("value"),


    // XML Elements
    ElementElement("element"),
    EntryElement("entry"),
    FieldElement("field"),
    ObjectElement("object"),
    ObjectsElement("objects"),
    ;

    /**
     * The tagname of an XML element or XML attribute.
     */
    private final String tagname;

    /**
     * Constructs an enumeration element.
     *
     * @param aTagname
     *        the tagname of an XML element or XML attribute
     */
    private XmlComponent(String aTagname) {

        tagname = aTagname;
    }

    /**
     * Returns the tagname of an XML element or XML attribute.
     *
     * @return the tagname of an XML element or XML attribute
     */
    public String getTagname() {

        return tagname;
    }

    /**
     * Returns a string representation of this enumeration element.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getTagname();
    }

    /**
     * The method checks if the tagname of this enumeration element equals the
     * specified tagname.
     *
     * @param aTagname
     *        a tagname
     *
     * @return <code>true</code> if the tagnames are equal, else
     *         <code>false</code>
     */
    public boolean equalsXmlComponent(String aTagname) {

        return getTagname().equals(aTagname);
    }

}
