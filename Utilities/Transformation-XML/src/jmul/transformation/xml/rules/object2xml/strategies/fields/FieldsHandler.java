/*
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

package jmul.transformation.xml.rules.object2xml.strategies.fields;


import org.w3c.dom.Element;

import jmul.transformation.TransformationParameters;


/**
 * This interface describes a strategy for handling an object's fields.
 *
 * @author Kristian Kutin
 */
public interface FieldsHandler {

    /**
     * Processes an object's fields and attaches informations to the specified
     * parent element.
     *
     * @param someParameters
     *        the transformation parameters which were specified for the parent
     *        element
     * @param aParentElement
     *        a parent element
     * @param anObject
     *        the object whose fields are to be processed
     */
    void processFields(TransformationParameters someParameters, Element aParentElement, Object anObject);

    /**
     * Processes an object's fields and attaches informations to the specified
     * parent element. Only fields up to a specified parent class are processed
     * (excluding the specified parent class).
     *
     * @param someParameters
     *        the transformation parameters which were specified for the parent
     *        element
     * @param aParentElement
     *        a parent element
     * @param anObject
     *        the object whose fields are to be processed
     * @param anExemptedSuperclass
     *        the specified class is examined only up to the specified
     *        superclass (excluding the superclass)
     */
    void processFields(TransformationParameters someParameters, Element aParentElement, Object anObject,
                       Class anExemptedSuperclass);

}
