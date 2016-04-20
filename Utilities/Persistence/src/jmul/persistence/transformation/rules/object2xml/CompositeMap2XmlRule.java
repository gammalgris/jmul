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


import java.util.Map;

import jmul.persistence.transformation.cache.Object2XmlCache;

import jmul.persistence.annotations.AnnotationHelper;
import jmul.persistence.annotations.MapInformations;
import jmul.persistence.id.ID;
import jmul.persistence.transformation.TransformationHelper;
import static jmul.persistence.transformation.rules.PersistenceMarkups.DECLARED_KEY_TYPE_ATTRIBUTE;
import static jmul.persistence.transformation.rules.PersistenceMarkups.DECLARED_VALUE_TYPE_ATTRIBUTE;
import static jmul.persistence.transformation.rules.PersistenceMarkups.ID_ATTRIBUTE;
import static jmul.persistence.transformation.rules.PersistenceMarkups.OBJECT_ELEMENT;
import static jmul.persistence.transformation.rules.PersistenceMarkups.TYPE_ATTRIBUTE;
import static jmul.persistence.transformation.rules.TransformationConstants.DECLARED_KEY_TYPE;
import static jmul.persistence.transformation.rules.TransformationConstants.DECLARED_VALUE_TYPE;
import static jmul.persistence.transformation.rules.TransformationConstants.OBJECT_CACHE;
import static jmul.persistence.transformation.rules.TransformationConstants.ROOT_ELEMENT;
import static jmul.persistence.transformation.rules.TransformationConstants.XML_DOCUMENT;
import jmul.persistence.transformation.rules.object2xml.strategies.containers.ContainerHandler;
import jmul.persistence.transformation.rules.object2xml.strategies.containers.MapHandler;
import jmul.persistence.transformation.rules.object2xml.strategies.fields.FieldsHandler;
import jmul.persistence.transformation.rules.object2xml.strategies.fields.GenericObjectHandler;

import jmul.reflection.ContainerHelper;

import jmul.string.StringConcatenator;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;

import jmul.xml.XmlHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * A transformation rule which handles objects which are a map and a composite
 * object at the same time.
 *
 * @author Kristian Kutin
 */
public class CompositeMap2XmlRule extends TransformationRuleBase {

    /**
     * A container handler.
     */
    private static final FieldsHandler FIELDS_HANDLER_SINGLETON;

    /**
     * A container handler.
     */
    private static final ContainerHandler MAP_HANDLER_SINGLETON;

    /*
     * The static initializer.
     */
    static {

        MAP_HANDLER_SINGLETON = new MapHandler();
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
    public CompositeMap2XmlRule(String anOrigin, String aDestination, int aPriority) {

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

        // Step 1
        //
        // Check if the object which is to be transformed is a map.

        Class expectedType = Map.class;
        Object object = someParameters.getObject();
        Class realType = someParameters.getRealType();

        if (expectedType.isInstance(object)) {

            // Step 2
            //
            // The object which is to be transformed is a map. Now it is
            // necessary to identify if there are fields which need to be
            // persisted as well.
            //
            // Following problems need to be considered:
            //
            // 1) A concrete implementation of a map provided by the Java
            //    framework may contain fields for internal purposes. These
            //    fields need not be persisted.
            // 2) It is necessary to identify which map implementation class is
            //    present (i.e. the inheritance structure must be considered).

            Class mapImplementation = ContainerHelper.getConcreteMapImplementation(object);

            boolean result =
                (mapImplementation != null) && TransformationHelper.isComposite(realType, mapImplementation);
            return result;
        }

        return false;
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

        Class declaredKeyType = null;
        Class declaredValueType = null;

        if (someParameters.containsPrerequisite(DECLARED_KEY_TYPE) &&
            someParameters.containsPrerequisite(DECLARED_VALUE_TYPE)) {

            // A field declaration was marked with an annotation where the
            // key and value types for this map were specified.

            declaredKeyType = (Class) someParameters.getPrerequisite(DECLARED_KEY_TYPE);
            declaredValueType = (Class) someParameters.getPrerequisite(DECLARED_VALUE_TYPE);

        } else {

            // A field declaration was not marked with an annotation. Thus the
            // key and value types for this map are unknown. Now the class
            // definition must be checked for the annotation.

            MapInformations annotation =
                (MapInformations) AnnotationHelper.getAnnotation(someParameters.getRealType(), MapInformations.class,
                                                                 true);

            if (annotation == null) {

                StringConcatenator message =
                    new StringConcatenator("Cannot determine the key and value types for this map (",
                                           someParameters.getRealType().getName(), "<?,?>)!");
                throw new TransformationException(message.toString());
            }

            declaredKeyType = annotation.declaredKeyType();
            declaredValueType = annotation.declaredValueType();
            someParameters.addPrerequisite(DECLARED_KEY_TYPE, declaredKeyType);
            someParameters.addPrerequisite(DECLARED_VALUE_TYPE, declaredValueType);
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
        element.setAttribute(DECLARED_KEY_TYPE_ATTRIBUTE.getTagName(), declaredKeyType.getName());
        element.setAttribute(DECLARED_VALUE_TYPE_ATTRIBUTE.getTagName(), declaredValueType.getName());


        // Step 3
        //
        // In the next step all class members which have to be persisted need
        // to be processed.

        Class exemptedSuperclass = ContainerHelper.getConcreteMapImplementation(object);
        FIELDS_HANDLER_SINGLETON.processFields(someParameters, element, object, exemptedSuperclass);


        // Step 4
        //
        // In the next step all elements of this container need to be processed.

        MAP_HANDLER_SINGLETON.processContainerContent(someParameters, element, object);


        // Step 5
        //
        // Add the new element to the document.

        rootElement.appendChild(element);

        return id;
    }

}
