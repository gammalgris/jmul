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


import java.util.Collection;
import java.util.Map;

import jmul.misc.id.ID;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.string.TextHelper;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;
import jmul.transformation.xml.annotations.AnnotationHelper;
import jmul.transformation.xml.annotations.RootNode;
import jmul.transformation.xml.cache.Object2XmlCache;
import static jmul.transformation.xml.rules.PersistenceMarkups.DECLARED_TYPE_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.ID_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.OBJECT_ELEMENT;
import static jmul.transformation.xml.rules.PersistenceMarkups.TYPE_ATTRIBUTE;
import static jmul.transformation.xml.rules.TransformationConstants.OBJECT_CACHE;
import static jmul.transformation.xml.rules.TransformationConstants.ROOT_ELEMENT;
import static jmul.transformation.xml.rules.TransformationConstants.XML_DOCUMENT;
import jmul.transformation.xml.rules.object2xml.strategies.fields.FieldsHandler;
import jmul.transformation.xml.rules.object2xml.strategies.fields.GenericObjectHandler;

import jmul.xml.XmlHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * A transformation rule which handles a composite object.
 *
 * @author Kristian Kutin
 */
public class Composite2XmlRule extends TransformationRuleBase {

    /**
     * A container handler.
     */
    private static final FieldsHandler FIELDS_HANDLER_SINGLETON;

    /*
     * The static initializer.
     */
    static {

        FIELDS_HANDLER_SINGLETON = new GenericObjectHandler();
    }

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
    public Composite2XmlRule(String anOrigin, String aDestination, int aPriority) {

        super(anOrigin, aDestination, aPriority);
    }

    /**
     * The method determines if this rule can be applied on the specified
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

        boolean result = false;

        try {

            ClassDefinition definition = ClassHelper.getClass(someParameters.getDeclaredType());

            result =
                !(definition.isPrimitiveType() || definition.isPrimitiveWrapper() ||
                  Collection.class.isInstance(someParameters.getObject()) ||
                  Map.class.isInstance(someParameters.getObject()));

        } catch (ClassNotFoundException e) {

            String message = TextHelper.concatenateStrings("Unknown class (", someParameters.getDeclaredType(), ")!");
            throw new IllegalArgumentException(message, e);
        }

        return result;
    }

    /**
     * The method performs a transformation.
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return the ID of the transformed object
     */
    @Override
    public Object transform(TransformationParameters someParameters) {

        // Check parameters.

        if (!(someParameters.containsPrerequisite(OBJECT_CACHE) && someParameters.containsPrerequisite(ROOT_ELEMENT))) {

            String message = "No root node could be identified!";
            throw new TransformationException(message);
        }


        // Retrieve some required informations about the object which is to be
        // transformed.

        Object object = someParameters.getObject();
        Class realType = someParameters.getRealType();
        Class declaredType = someParameters.getDeclaredType();

        // Retrieve other prerequisites for the transformation.

        Document document = (Document) someParameters.getPrerequisite(XML_DOCUMENT);
        Object2XmlCache cache = (Object2XmlCache) someParameters.getPrerequisite(OBJECT_CACHE);
        Element rootElement = (Element) someParameters.getPrerequisite(ROOT_ELEMENT);


        // Step 1
        //
        // Check if this object was already processed. If yes then we're done
        // and simply return its ID. With this information the caller can
        // correctly set up references.

        if (cache.existsObject(object, declaredType)) {

            return cache.getID(object, declaredType);
        }


        // Step 2
        //
        // Build an xml skeleton for this composite object and insert it into
        // the document.

        ID id = cache.addObject(object, declaredType);

        Element element = XmlHelper.createXmlElement(document, OBJECT_ELEMENT);

        if (AnnotationHelper.isAnnotationPresent(realType, RootNode.class, true)) {

            element.setAttribute(DECLARED_TYPE_ATTRIBUTE.getTagName(), declaredType.getName());

        } else {

            element.setAttribute(ID_ATTRIBUTE.getTagName(), id.toString());
        }

        element.setAttribute(TYPE_ATTRIBUTE.getTagName(), realType.getName());


        // Step 3
        //
        // In the next step all class members which have to be persisted need
        // to be processed.

        FIELDS_HANDLER_SINGLETON.processFields(someParameters, element, object);


        // Step 4
        //
        // Add the new element to the document.

        rootElement.appendChild(element);

        return id;
    }

}
