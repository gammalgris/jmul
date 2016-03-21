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
import jmul.transformation.TransformationResources;
import jmul.transformation.TransformationRuleBase;

import jmul.cache.transformation.Xml2ObjectCache;
import jmul.classes.ClassDefinition;
import jmul.classes.ClassHelper;
import jmul.creation.ObjectFactory;
import jmul.id.ID;
import jmul.id.IntegerID;
import jmul.string.StringConcatenator;
import jmul.xml.ElementWrapper;


/**
 * An implementation of a transformation rule.
 *
 * @author Kristian Kutin
 */
public class Xml2PrimitiveRule extends TransformationRuleBase implements TransformationCommons {

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
    public Xml2PrimitiveRule(String anOrigin, String aDestination,
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

            if (element.equalsName(OBJECT_ELEMENT_TAGNAME)) {

                if (element.existsAttribute(TYPE_ATTRIBUTE_TAGNAME)) {

                    String classname =
                        element.getAttribute(TYPE_ATTRIBUTE_TAGNAME);
                    ClassDefinition type = null;

                    try {

                        type = ClassHelper.getClass(classname);

                    } catch (ClassNotFoundException e) {

                        StringConcatenator message =
                            new StringConcatenator("An unknown class was specified (",
                                                   classname, ")!");
                        throw new TransformationException(message.toString());
                    }

                    return (type.isPrimitiveType() ||
                            type.isPrimitiveWrapper());
                }
            }
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


        if (!element.existsAttribute(VALUE_ATTRIBUTE_TAGNAME)) {

            StringConcatenator message =
                new StringConcatenator("The element \"", element.getName(),
                                       "\" is missing the attribute \"",
                                       VALUE_ATTRIBUTE_TAGNAME, "\"!");
            throw new TransformationException(message.toString());
        }


        // Get the required informations.

        Xml2ObjectCache objectCache =
            (Xml2ObjectCache)someParameters.getPrerequisite(OBJECT_CACHE);

        String idString = element.getAttribute(ID_ATTRIBUTE_TAGNAME);
        String typeString = element.getAttribute(TYPE_ATTRIBUTE_TAGNAME);
        String valueString = element.getAttribute(VALUE_ATTRIBUTE_TAGNAME);

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

        ObjectFactory factory = TransformationResources.getObjectFactory();
        Object object = factory.newInstance(type, valueString);


        // Instantiate and initialize the specified object

        objectCache.addObject(id, object, type.getType());

        return object;
    }

}
