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

package jmul.transformation.xml.rules.object2xml;


import jmul.transformation.TransformationException;
import jmul.transformation.TransformationParameters;
import jmul.transformation.xml.TransformationHelper;
import jmul.transformation.xml.annotations.AnnotationHelper;
import jmul.transformation.xml.annotations.RootNode;
import jmul.transformation.xml.cache.Object2XmlCache;
import jmul.transformation.xml.cache.Object2XmlCacheImpl;
import static jmul.transformation.xml.rules.PersistenceMarkups.OBJECTS_ELEMENT;
import static jmul.transformation.xml.rules.TransformationConstants.OBJECT_CACHE;
import static jmul.transformation.xml.rules.TransformationConstants.ROOT_ELEMENT;
import static jmul.transformation.xml.rules.TransformationConstants.XML_DOCUMENT;

import jmul.xml.XmlHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * A transformation rule which handles a root node. A root node is expected to
 * be a composite object.<br>
 * <br>
 * <i>To avoid redundant code the handling of a composite object is
 * inherited.</i>
 *
 * @author Kristian Kutin
 */
public class RootNode2XmlRule extends Composite2XmlRule {

    /**
     * Constructs a transformation rule.
     *
     * @param anOrigin
     *        a description of the transformation origin
     * @param aDestination
     *        a description of the transformation destination
     * @param aPriority
     *        a rule priority
     */
    public RootNode2XmlRule(String anOrigin, String aDestination, int aPriority) {

        super(anOrigin, aDestination, aPriority);
    }

    /**
     * The method determines if this rule can be applied to the specified
     * object.
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return <code>true</code> if the rule is applicable, else
     *         <code>false</code>
     */
    @Override
    public boolean isApplicable(TransformationParameters someParameters) {

        Class realType = someParameters.getObject().getClass();

        return AnnotationHelper.isAnnotationPresent(realType, RootNode.class, true) &&
               super.isApplicable(someParameters);
    }

    /**
     * The method performs a transformation.<br>
     * <br>
     * <i>The specified parameters must contain the xml document which is used
     * as output. When the transformation is finished then the specified xml
     * document contains the actual transformation result.</i>
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return the xml document which contains the transformed object
     */
    @Override
    public Object transform(TransformationParameters someParameters) {

        if (someParameters.containsPrerequisite(OBJECT_CACHE) || someParameters.containsPrerequisite(XML_DOCUMENT)) {

            String message = "More than one root node was detected within the object tree!";
            throw new TransformationException(message);
        }


        // Create a new xml document.

        Document document = XmlHelper.newXmlDocument();


        // A cache is required to manage IDs of fields.

        Object2XmlCache cache = new Object2XmlCacheImpl();


        // Initialize the document's root node.

        Element rootElement = XmlHelper.createXmlElement(document, OBJECTS_ELEMENT);
        document.appendChild(rootElement);


        // Detect the declared type.

        Object object = someParameters.getObject();
        Class realType = someParameters.getObject().getClass();
        Class declaredType = null;

        RootNode annotation = (RootNode) AnnotationHelper.getAnnotation(realType, RootNode.class, true);
        if (annotation != null) {

            declaredType = annotation.declaredType();
        }


        // Create new parameters (clone provided parameters and add additional
        // informations which are required for itnernal processing).

        TransformationParameters newParameters;

        if (declaredType != null) {

            newParameters =
                TransformationHelper.newTransformationParameters(getTransformationPath(), object, declaredType);

        } else {

            newParameters = TransformationHelper.newTransformationParameters(getTransformationPath(), object);
        }

        newParameters.addPrerequisite(XML_DOCUMENT, document);
        newParameters.addPrerequisite(OBJECT_CACHE, cache);
        newParameters.addPrerequisite(ROOT_ELEMENT, rootElement);


        // Transform the tree.

        super.transform(newParameters);

        return document;
    }

}
