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

package jmul.persistence.transformation.rules.object2xml;


import java.util.Collection;

import jmul.persistence.transformation.cache.Object2XmlCache;

import jmul.persistence.annotations.AnnotationHelper;
import jmul.persistence.annotations.ContainerInformations;
import jmul.persistence.id.ID;
import static jmul.persistence.transformation.rules.PersistenceMarkups.DECLARED_ELEMENT_TYPE_ATTRIBUTE;
import static jmul.persistence.transformation.rules.PersistenceMarkups.ID_ATTRIBUTE;
import static jmul.persistence.transformation.rules.PersistenceMarkups.OBJECT_ELEMENT;
import static jmul.persistence.transformation.rules.PersistenceMarkups.TYPE_ATTRIBUTE;
import static jmul.persistence.transformation.rules.TransformationConstants.DECLARED_ELEMENT_TYPE;
import static jmul.persistence.transformation.rules.TransformationConstants.OBJECT_CACHE;
import static jmul.persistence.transformation.rules.TransformationConstants.ROOT_ELEMENT;
import static jmul.persistence.transformation.rules.TransformationConstants.XML_DOCUMENT;
import jmul.persistence.transformation.rules.object2xml.strategies.containers.CollectionHandler;
import jmul.persistence.transformation.rules.object2xml.strategies.containers.ContainerHandler;

import jmul.string.StringConcatenator;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;

import jmul.xml.XmlHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * A transformation rule which handles collections.
 *
 * @author Kristian Kutin
 */
public class Collection2XmlRule extends TransformationRuleBase {

    /**
     * A container handler.
     */
    private static final ContainerHandler HANDLER_SINGLETON;

    /*
     * The static initializer.
     */
    static {

        HANDLER_SINGLETON = new CollectionHandler();
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
    public Collection2XmlRule(String anOrigin, String aDestination, int aPriority) {

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

        Class expectedType = Collection.class;
        boolean result = expectedType.isInstance(someParameters.getObject());
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


        Class declaredElementType = null;
        if (someParameters.containsPrerequisite(DECLARED_ELEMENT_TYPE)) {

            // A field declaration was marked with an annotation where the
            // element type for this collection was specified.

            declaredElementType = (Class) someParameters.getPrerequisite(DECLARED_ELEMENT_TYPE);

        } else {

            // A field declaration was not marked with an annotation. Thus the
            // element type for this collection is unknown. Now the class
            // definition must be checked for the annotation.

            ContainerInformations annotation =
                (ContainerInformations) AnnotationHelper.getAnnotation(someParameters.getRealType(),
                                                                       ContainerInformations.class, true);

            if (annotation == null) {

                StringConcatenator message =
                    new StringConcatenator("Cannot determine the element type for this collection (",
                                           someParameters.getRealType().getName(), "<?>)!");
                throw new TransformationException(message.toString());
            }

            declaredElementType = annotation.declaredElementType();
            someParameters.addPrerequisite(DECLARED_ELEMENT_TYPE, declaredElementType);
        }


        // Step 1
        //
        // Check if this object was already processed. If yes then we're done
        // and simply return its ID. With this information the caller can
        // correctly set up references.

        if (cache.existsObject(object, declaredType)) {

            ID id = cache.getID(object, declaredType);
            return id;
        }


        // Step 2
        //
        // Build an xml skeleton for this container object and insert it into
        // the document.

        ID id = cache.addObject(object, declaredType);

        Element element = XmlHelper.createXmlElement(document, OBJECT_ELEMENT);

        element.setAttribute(ID_ATTRIBUTE.getTagName(), id.toString());
        element.setAttribute(TYPE_ATTRIBUTE.getTagName(), realType.getName());
        element.setAttribute(DECLARED_ELEMENT_TYPE_ATTRIBUTE.getTagName(), declaredElementType.getName());


        // Step 3
        //
        // In the next step all elements of this container need to be processed.

        HANDLER_SINGLETON.processContainerContent(someParameters, element, object);


        // Step 4
        //
        // Add the new element to the document.

        rootElement.appendChild(element);


        return id;
    }

}
