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

package jmul.transformation.xml.rules.xml2object;


import jmul.transformation.xml.cache.Xml2ObjectCache;
import jmul.transformation.xml.cache.Xml2ObjectCacheImpl;

import jmul.transformation.xml.TransformationHelper;
import static jmul.transformation.xml.rules.PersistenceMarkups.OBJECTS_ELEMENT;
import static jmul.transformation.xml.rules.TransformationConstants.OBJECT_CACHE;

import jmul.string.StringConcatenator;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationFactory;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationResources;
import jmul.transformation.TransformationRuleBase;

import jmul.xml.SubelementList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * An implementation of a transformation rule.
 *
 * @author Kristian Kutin
 */
public class Document2ObjectRule extends TransformationRuleBase {

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
    public Document2ObjectRule(String anOrigin, String aDestination, int aPriority) {

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

        return Document.class.isInstance(someParameters.getObject());
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

        // Get all required informations and objects.

        Object object = someParameters.getObject();

        Document document = (Document) object;
        Element rootElement = document.getDocumentElement();

        Xml2ObjectCache objectCache = new Xml2ObjectCacheImpl();

        TransformationFactory factory = TransformationResources.getTransformationFactory();


        // Check some plausibilities.

        String rootElementName = rootElement.getNodeName();
        if (!rootElementName.equals(OBJECTS_ELEMENT.getTagName())) {

            StringConcatenator message = new StringConcatenator("Invalid root element (", rootElementName, ")!");
            throw new TransformationException(message.toString());
        }


        // Iterate through all child nodes.

        Object deserializedObject = null;

        SubelementList childNodes = new SubelementList(rootElement);

        for (Node node : childNodes) {

            TransformationParameters newParameters =
                TransformationHelper.newTransformationParameters(getTransformationPath(), node);

            newParameters.addPrerequisite(OBJECT_CACHE, objectCache);


            // The last child node represents the serialized object. All
            // object declarations that come before represent referenced
            // objects.

            deserializedObject = factory.transform(newParameters);
        }

        return deserializedObject;
    }

}
