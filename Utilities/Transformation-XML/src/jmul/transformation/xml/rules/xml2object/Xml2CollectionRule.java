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

package jmul.transformation.xml.rules.xml2object;


import java.util.Collection;

import jmul.misc.id.ID;
import jmul.misc.id.IntegerID;

import jmul.reflection.Initializer;
import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.string.StringConcatenator;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;
import jmul.transformation.xml.cache.Xml2ObjectCache;
import static jmul.transformation.xml.rules.PersistenceMarkups.DECLARED_ELEMENT_TYPE_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.ELEMENT_ELEMENT;
import static jmul.transformation.xml.rules.PersistenceMarkups.ID_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.OBJECT_ELEMENT;
import static jmul.transformation.xml.rules.PersistenceMarkups.REFERENCED_ELEMENT_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.TYPE_ATTRIBUTE;
import static jmul.transformation.xml.rules.TransformationConstants.OBJECT_CACHE;

import jmul.xml.SubelementList;
import jmul.xml.XmlParserHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


/**
 * An implementation of a transformation rule.
 *
 * @author Kristian Kutin
 */
public class Xml2CollectionRule extends TransformationRuleBase {

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
    public Xml2CollectionRule(String anOrigin, String aDestination, int aPriority) {

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

        Object target = someParameters.getObject();

        if (!Document.class.isInstance(target) && Node.class.isInstance(target)) {

            Node objectElement = (Node) target;
            SubelementList objectSubelements = new SubelementList(objectElement);

            return XmlParserHelper.matchesXmlElement(objectElement, OBJECT_ELEMENT) &&
                   XmlParserHelper.hasXmlSubelements(objectSubelements, ELEMENT_ELEMENT) &&
                   XmlParserHelper.existsXmlAttribute(objectElement, DECLARED_ELEMENT_TYPE_ATTRIBUTE);
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
    @Override
    public Object transform(TransformationParameters someParameters) {

        // Check some plausibilites first.

        if (!someParameters.containsPrerequisite(OBJECT_CACHE)) {

            StringConcatenator message =
                new StringConcatenator("Prerequisites for the transformation are missing (", OBJECT_CACHE, ")!");
            throw new TransformationException(message.toString());
        }


        Object target = someParameters.getObject();
        Node objectElement = (Node) target;

        XmlParserHelper.assertMatchesXmlElement(objectElement, OBJECT_ELEMENT);
        XmlParserHelper.assertExistsXmlAttribute(objectElement, ID_ATTRIBUTE);
        XmlParserHelper.assertExistsXmlAttribute(objectElement, TYPE_ATTRIBUTE);
        XmlParserHelper.assertExistsXmlAttribute(objectElement, DECLARED_ELEMENT_TYPE_ATTRIBUTE);


        // Get the required informations.

        Xml2ObjectCache objectCache = (Xml2ObjectCache) someParameters.getPrerequisite(OBJECT_CACHE);

        String idString = XmlParserHelper.getXmlAttributeValue(objectElement, ID_ATTRIBUTE);
        String typeString = XmlParserHelper.getXmlAttributeValue(objectElement, TYPE_ATTRIBUTE);

        ID id = new IntegerID(idString);
        ClassDefinition type = null;

        try {

            type = ClassHelper.getClass(typeString);

        } catch (ClassNotFoundException e) {

            StringConcatenator message = new StringConcatenator("An unknown class was specified (", typeString, ")!");
            throw new TransformationException(message.toString(), e);
        }


        // Instantiate and initialize the specified object

        Initializer initializer = new Initializer(type);
        Collection collection = (Collection) initializer.newInitializedInstance();

        SubelementList objectSubelements = new SubelementList(objectElement);

        for (Node elementElement : objectSubelements.getSubelements(ELEMENT_ELEMENT)) {

            // Check some plausibilites first.

            XmlParserHelper.assertExistsXmlAttribute(elementElement, REFERENCED_ELEMENT_ATTRIBUTE);


            // Get the required field informations.

            String referencedElementString =
                XmlParserHelper.getXmlAttributeValue(elementElement, REFERENCED_ELEMENT_ATTRIBUTE);

            ID fieldID = new IntegerID(referencedElementString);
            Object entryValue = objectCache.getObject(fieldID);

            collection.add(entryValue);
        }


        // Instantiate and initialize the specified object

        objectCache.addObject(id, collection, type.getType());

        return collection;
    }

}
