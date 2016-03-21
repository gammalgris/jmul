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

package jmul.persistence.transformation.rules.xml2object;


import org.w3c.dom.Document;
import org.w3c.dom.Node;

import jmul.persistence.transformation.rules.TransformationCommons;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;

import jmul.cache.transformation.Xml2ObjectCache;
import jmul.classes.ClassDefinition;
import jmul.classes.ClassHelper;
import jmul.id.ID;
import jmul.id.IntegerID;
import jmul.reflection.Initializer;
import jmul.string.StringConcatenator;
import jmul.xml.ElementWrapper;


/**
 * An implementation of a transformation rule.
 *
 * @author Kristian Kutin
 */
public class Xml2CompositeRule extends TransformationRuleBase implements TransformationCommons {

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
    public Xml2CompositeRule(String anOrigin, String aDestination,
                             int aPriority) {

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
    public boolean isApplicable(TransformationParameters someParameters) {

        Object target = someParameters.getObject();

        if (!Document.class.isInstance(target) &&
            Node.class.isInstance(target)) {

            ElementWrapper element = new ElementWrapper((Node)target);

            return (element.existsSubelement(FIELD_ELEMENT_TAGNAME) &&
                    !element.existsSubelement(ELEMENT_ELEMENT_TAGNAME));
        }

        return false;
    }

    /**
     * The method performs the actual transformation.
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return the transformed object
     */
    public Object transform(TransformationParameters someParameters) {

        // Check some plausibilites first.

        if (!someParameters.containsPrerequisite(OBJECT_CACHE)) {

            StringConcatenator message =
                new StringConcatenator("Prerequisites for the transformation are missing (",
                                       OBJECT_CACHE, ")!");
            throw new TransformationException(message.toString());
        }


        Object target = someParameters.getObject();
        ElementWrapper element = new ElementWrapper((Node)target);

        if (!element.equalsName(OBJECT_ELEMENT_TAGNAME)) {

            StringConcatenator message =
                new StringConcatenator("Invalid element (", element.getName(),
                                       ")!");
            throw new TransformationException(message.toString());
        }


        if (!element.existsAttribute(ID_ATTRIBUTE_TAGNAME)) {

            StringConcatenator message =
                new StringConcatenator("The element \"", element.getName(),
                                       "\" is missing the attribute \"",
                                       ID_ATTRIBUTE_TAGNAME, "\"!");
            throw new TransformationException(message.toString());
        }


        if (!element.existsAttribute(TYPE_ATTRIBUTE_TAGNAME)) {

            StringConcatenator message =
                new StringConcatenator("The element \"", element.getName(),
                                       "\" is missing the attribute \"",
                                       TYPE_ATTRIBUTE_TAGNAME, "\"!");
            throw new TransformationException(message.toString());
        }


        // Get the required informations.

        Xml2ObjectCache objectCache =
            (Xml2ObjectCache)someParameters.getPrerequisite(OBJECT_CACHE);

        String idString = element.getAttribute(ID_ATTRIBUTE_TAGNAME);
        String typeString = element.getAttribute(TYPE_ATTRIBUTE_TAGNAME);

        ID id = new IntegerID(idString);
        ClassDefinition type = null;

        try {

            type = ClassHelper.getClass(typeString);

        } catch (ClassNotFoundException e) {

            StringConcatenator message =
                new StringConcatenator("An unknown class was specified (",
                                       typeString, ")!");
            throw new TransformationException(message.toString());
        }


        // Instantiate and initialize the specified object

        Initializer initializer = new Initializer(type);

        for (Node subelement : element.getSubelement(FIELD_ELEMENT_TAGNAME)) {

            // Check some plausibilites first.

            ElementWrapper field = new ElementWrapper(subelement);

            if (!field.existsAttribute(DECLARED_TYPE_ATTRIBUTE_TAGNAME)) {

                StringConcatenator message =
                    new StringConcatenator("The element \"", field.getName(),
                                           "\" is missing the attribute \"",
                                           DECLARED_TYPE_ATTRIBUTE_TAGNAME,
                                           "\"!");
                throw new TransformationException(message.toString());
            }

            if (!field.existsAttribute(NAME_ATTRIBUTE_TAGNAME)) {

                StringConcatenator message =
                    new StringConcatenator("The element \"", field.getName(),
                                           "\" is missing the attribute \"",
                                           NAME_ATTRIBUTE_TAGNAME, "\"!");
                throw new TransformationException(message.toString());
            }

            if (!field.existsAttribute(REFERENCED_OBJECT_ATTRIBUTE_TAGNAME)) {

                StringConcatenator message =
                    new StringConcatenator("The element \"", field.getName(),
                                           "\" is missing the attribute \"",
                                           REFERENCED_OBJECT_ATTRIBUTE_TAGNAME,
                                           "\"!");
                throw new TransformationException(message.toString());
            }


            // Get the required field informations.

            // String declaredFieldType = field.getAttribute(DECLARED_TYPE_ATTRIBUTE_TAGNAME);
            String fieldName = field.getAttribute(NAME_ATTRIBUTE_TAGNAME);
            String referencedObject =
                field.getAttribute(REFERENCED_OBJECT_ATTRIBUTE_TAGNAME);

            ID fieldID = new IntegerID(referencedObject);
            Object fieldValue = objectCache.getObject(fieldID);

            initializer.setField(fieldName, fieldValue);
        }


        // Instantiate and initialize the specified object

        Object object = initializer.newInitializedInstance();
        objectCache.addObject(id, object, type.getType());

        return object;
    }

}
