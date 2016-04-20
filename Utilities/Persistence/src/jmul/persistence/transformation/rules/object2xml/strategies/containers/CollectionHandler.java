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

package jmul.persistence.transformation.rules.object2xml.strategies.containers;


import java.util.Collection;

import jmul.persistence.transformation.cache.Object2XmlCache;

import jmul.persistence.id.ID;
import jmul.persistence.transformation.TransformationHelper;
import static jmul.persistence.transformation.rules.PersistenceMarkups.ELEMENT_ELEMENT;
import static jmul.persistence.transformation.rules.PersistenceMarkups.REFERENCED_ELEMENT_ATTRIBUTE;
import static jmul.persistence.transformation.rules.TransformationConstants.DECLARED_ELEMENT_TYPE;
import static jmul.persistence.transformation.rules.TransformationConstants.OBJECT_CACHE;
import static jmul.persistence.transformation.rules.TransformationConstants.ROOT_ELEMENT;
import static jmul.persistence.transformation.rules.TransformationConstants.XML_DOCUMENT;

import jmul.transformation.TransformationFactory;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationResources;

import jmul.xml.XmlHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * An implementation of a container handler.
 *
 * @author Kristian Kutin
 */
public class CollectionHandler implements ContainerHandler {

    /**
     * The default constructor.
     */
    public CollectionHandler() {
    }

    /**
     * Processes a container and attaches informations to the specified parent
     * element.
     *
     * @param someParameters
     *        the transformation parameters which were specified for the parent
     *        element
     * @param aParentElement
     *        a parent element
     * @param aContainer
     *        a container
     */
    @Override
    public void processContainerContent(TransformationParameters someParameters, Element aParentElement,
                                        Object aContainer) {

        // Step 1
        //
        // Check if the specified container is of a type that can be handled
        // here.

        if (!(aContainer instanceof Collection)) {

            String message = "The specified container is no collection!";
            throw new IllegalArgumentException(message);
        }

        Collection collection = (Collection) aContainer;


        // Step 2
        //
        // Retrieve other prerequisites for the transformation of the container
        // elements.

        TransformationFactory factory = TransformationResources.getTransformationFactory();
        TransformationPath transformationPath = someParameters.getTransformationPath();
        Document document = (Document) someParameters.getPrerequisite(XML_DOCUMENT);
        Object2XmlCache cache = (Object2XmlCache) someParameters.getPrerequisite(OBJECT_CACHE);
        Element rootElement = (Element) someParameters.getPrerequisite(ROOT_ELEMENT);
        Class declaredElementType = (Class) someParameters.getPrerequisite(DECLARED_ELEMENT_TYPE);


        // Step 3
        //
        // In the next step all elements of this container need to be processed.
        // This container object only needs to know the ID for each processed
        // element to properly set up references. Calling the factory mechanism
        // will return the ID for each element and each call will take care that
        // an entry for the element is inserted into the xml document.
        // At runtime the declared element type can only be identified if it is
        // provided with the annotation @ContainerInformations.

        for (Object collectionElement : collection) {

            // Build the parameters to process (transform) the collection element.

            TransformationParameters containerElementParameters =
                TransformationHelper.newTransformationParameters(transformationPath, collectionElement,
                                                                 declaredElementType);
            containerElementParameters.addPrerequisite(OBJECT_CACHE, cache);
            containerElementParameters.addPrerequisite(XML_DOCUMENT, document);
            containerElementParameters.addPrerequisite(ROOT_ELEMENT, rootElement);


            // Call the transformation factory to process the container element.

            ID containerElementID = (ID) factory.transform(containerElementParameters);


            // This composite element requires references to its fields. The
            // document (i.e. this element) needs to be updated.

            Element collectionElementElement = XmlHelper.createXmlElement(document, ELEMENT_ELEMENT);
            collectionElementElement.setAttribute(REFERENCED_ELEMENT_ATTRIBUTE.getTagName(),
                                                  containerElementID.toString());
            aParentElement.appendChild(collectionElementElement);
        }

    }

}
