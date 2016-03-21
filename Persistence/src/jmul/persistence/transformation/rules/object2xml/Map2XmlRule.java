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

package jmul.persistence.transformation.rules.object2xml;


import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jmul.persistence.annotations.AnnotationHelper;
import jmul.persistence.annotations.MapInformations;
import jmul.persistence.transformation.rules.TransformationCommons;
import jmul.persistence.transformation.rules.object2xml.strategies.containers.ContainerHandler;
import jmul.persistence.transformation.rules.object2xml.strategies.containers.MapHandler;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;

import jmul.cache.transformation.Object2XmlCache;
import jmul.id.ID;
import jmul.string.StringConcatenator;
import jmul.xml.XmlHelper;


/**
 * A transformation rule which handles maps.
 *
 * @author Kristian Kutin
 */
public class Map2XmlRule extends TransformationRuleBase implements TransformationCommons {

    /**
     * A container handler.
     */
    private static final ContainerHandler HANDLER_SINGLETON;

    /*
     * The static initializer.
     */
    static {

        HANDLER_SINGLETON = new MapHandler();
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
    public Map2XmlRule(String anOrigin, String aDestination, int aPriority) {

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
    public boolean isApplicable(TransformationParameters someParameters) {

        Class expectedType = Map.class;
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
    public Object transform(TransformationParameters someParameters) {

        // Check parameters.

        if (!(someParameters.containsPrerequisite(OBJECT_CACHE) &&
              someParameters.containsPrerequisite(ROOT_ELEMENT))) {

            String message = "No root node could be identified!";
            throw new TransformationException(message);
        }


        // Retrieve some required informations about the object which is to be
        // transformed.

        Object object = someParameters.getObject();
        Class realType = someParameters.getRealType();
        Class declaredType = someParameters.getDeclaredType();


        // Retrieve other prerequisites for the transformation.

        Document document =
            (Document)someParameters.getPrerequisite(XML_DOCUMENT);
        Object2XmlCache cache =
            (Object2XmlCache)someParameters.getPrerequisite(OBJECT_CACHE);
        Element rootElement =
            (Element)someParameters.getPrerequisite(ROOT_ELEMENT);

        Class declaredKeyType = null;
        Class declaredValueType = null;

        if (someParameters.containsPrerequisite(DECLARED_KEY_TYPE) &&
            someParameters.containsPrerequisite(DECLARED_VALUE_TYPE)) {

            // A field declaration was marked with an annotation where the
            // key and value types for this map were specified.

            declaredKeyType =
                    (Class)someParameters.getPrerequisite(DECLARED_KEY_TYPE);
            declaredValueType =
                    (Class)someParameters.getPrerequisite(DECLARED_VALUE_TYPE);

        } else {

            // A field declaration was not marked with an annotation. Thus the
            // key and value types for this map are unknown. Now the class
            // definition must be checked for the annotation.

            MapInformations annotation =
                (MapInformations)AnnotationHelper.getAnnotation(someParameters.getRealType(),
                                                                MapInformations.class,
                                                                true);

            if (annotation == null) {

                StringConcatenator message =
                    new StringConcatenator("Cannot determine the key and value types for this map (",
                                           someParameters.getRealType().getName(),
                                           "<?,?>)!");
                throw new TransformationException(message.toString());
            }

            declaredKeyType = annotation.declaredKeyType();
            declaredValueType = annotation.declaredValueType();
            someParameters.addPrerequisite(DECLARED_KEY_TYPE, declaredKeyType);
            someParameters.addPrerequisite(DECLARED_VALUE_TYPE,
                                           declaredValueType);
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

        Element element =
            XmlHelper.createXmlElement(document, OBJECT_ELEMENT_TAGNAME);

        element.setAttribute(ID_ATTRIBUTE_TAGNAME, id.toString());
        element.setAttribute(TYPE_ATTRIBUTE_TAGNAME, realType.getName());
        element.setAttribute(DECLARED_KEY_TYPE_ATTRIBUTE_TAGNAME,
                             declaredKeyType.getName());
        element.setAttribute(DECLARED_VALUE_TYPE_ATTRIBUTE_TAGNAME,
                             declaredValueType.getName());


        // Step 3
        //
        // In the next step all elements of this container need to be processed.

        HANDLER_SINGLETON.processContainerContent(someParameters, element,
                                                  object);


        // Step 4
        //
        // Add the new element to the document.

        rootElement.appendChild(element);

        return id;
    }

}
