/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package jmul.transformation.xml.rules.xml2object;


import jmul.transformation.TransformationParameters;
import static jmul.transformation.xml.rules.PersistenceMarkups.OBJECT_ELEMENT;
import static jmul.transformation.xml.rules.PersistenceMarkups.TYPE_ATTRIBUTE;

import jmul.xml.XmlParserHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


/**
 * A utility class containing helper methods.
 *
 * @author Kristian Kutin
 */
public final class RuleHelper {

    /**
     * The default constructor.
     */
    private RuleHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks the applicability of a rule according to the specified parameters.
     *
     * @param someParameters
     *        some transformation parameters
     * @param anExpectedTypeName
     *        the expected type name
     *
     * @return <code>true</code> if the rule is applicable, else <code>false</code>
     */
    public static boolean isApplicable(TransformationParameters someParameters, String anExpectedTypeName) {

        Object target = someParameters.getObject();

        if (!Document.class.isInstance(target) && Node.class.isInstance(target)) {

            Node objectElement = (Node) target;

            if (XmlParserHelper.matchesXmlElement(objectElement, OBJECT_ELEMENT) &&
                XmlParserHelper.existsXmlAttribute(objectElement, TYPE_ATTRIBUTE)) {

                String typeName = XmlParserHelper.getXmlAttributeValue(objectElement, TYPE_ATTRIBUTE);
                return typeName.equals(anExpectedTypeName);
            }
        }

        return false;
    }

}
