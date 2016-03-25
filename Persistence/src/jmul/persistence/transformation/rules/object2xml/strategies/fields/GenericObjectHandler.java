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

package jmul.persistence.transformation.rules.object2xml.strategies.fields;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import jmul.cache.transformation.Object2XmlCache;

import jmul.persistence.id.ID;

import jmul.persistence.annotations.ContainerInformations;
import jmul.persistence.annotations.MapInformations;
import jmul.persistence.transformation.TransformationHelper;
import static jmul.persistence.transformation.rules.PersistenceMarkups.DECLARED_TYPE_ATTRIBUTE;
import static jmul.persistence.transformation.rules.PersistenceMarkups.FIELD_ELEMENT;
import static jmul.persistence.transformation.rules.PersistenceMarkups.NAME_ATTRIBUTE;
import static jmul.persistence.transformation.rules.PersistenceMarkups.REFERENCED_OBJECT_ATTRIBUTE;
import static jmul.persistence.transformation.rules.TransformationConstants.DECLARED_ELEMENT_TYPE;
import static jmul.persistence.transformation.rules.TransformationConstants.DECLARED_KEY_TYPE;
import static jmul.persistence.transformation.rules.TransformationConstants.DECLARED_VALUE_TYPE;
import static jmul.persistence.transformation.rules.TransformationConstants.OBJECT_CACHE;
import static jmul.persistence.transformation.rules.TransformationConstants.ROOT_ELEMENT;
import static jmul.persistence.transformation.rules.TransformationConstants.XML_DOCUMENT;

import jmul.reflection.ReflectionHelper;

import jmul.string.StringConcatenator;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationFactory;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationResources;

import jmul.xml.XmlHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * An implementation of a fields handler.
 *
 * @author Kristian Kutin
 */
public class GenericObjectHandler implements FieldsHandler {

    /**
     * The default constructor.
     */
    public GenericObjectHandler() {
    }

    /**
     * Processes an object's fields and attaches informations to the specified
     * parent element.
     *
     * @param someParameters
     *        the transformation parameters which were specified for the parent
     *        element
     * @param aParentElement
     *        a parent element
     * @param anObject
     *        the object whose fields are to be processed
     */
    public void processFields(TransformationParameters someParameters, Element aParentElement, Object anObject) {

        processFields(someParameters, aParentElement, anObject, null);
    }

    /**
     * Processes an object's fields and attaches informations to the specified
     * parent element. Only fields up to a specified parent class are processed
     * (excluding the specified parent class).
     *
     * @param someParameters
     *        the transformation parameters which were specified for the parent
     *        element
     * @param aParentElement
     *        a parent element
     * @param anObject
     *        the object whose fields are to be processed
     * @param anExemptedSuperclass
     *        the specified class is examined only up to the specified
     *        superclass (excluding the superclass)
     */
    public void processFields(TransformationParameters someParameters, Element aParentElement, Object anObject,
                              Class anExemptedSuperclass) {

        // Step 1
        //
        // Retrieve some required informations about the object which is to be
        // transformed.

        Object object = someParameters.getObject();
        Class realType = someParameters.getRealType();


        // Step 2
        //
        // Retrieve other prerequisites for the transformation of the container
        // elements.

        TransformationFactory factory = TransformationResources.getTransformationFactory();
        TransformationPath transformationPath = someParameters.getTransformationPath();
        Document document = (Document) someParameters.getPrerequisite(XML_DOCUMENT);
        Object2XmlCache cache = (Object2XmlCache) someParameters.getPrerequisite(OBJECT_CACHE);
        Element rootElement = (Element) someParameters.getPrerequisite(ROOT_ELEMENT);


        // Step 3
        //
        // In the next step all class members which have to be persisted need
        // to be processed (i.e. all non static class members which are not
        // marked with the annotation @Exempted). This composite object only
        // needs to know the ID for each processed field to properly set up
        // references. Calling the factory mechanism will return the ID for each
        // field and each call will take care that an entry for the field is
        // inserted into the xml document.
        //
        // Unlike with an ordinary composite we don't collect all persistable
        // fields but only all fields up to a certain parent class (excluding
        // the parent class). This is in order to avoid collecting internal
        // fields of a collection implementation which dont't require
        // persisting.

        Collection<Field> persistableFields =
            TransformationHelper.getAllPersistableFields(realType, anExemptedSuperclass);

        Collection<String> processedFields = new ArrayList<String>();

        for (Field field : persistableFields) {

            // Get informations about a field.

            String fieldName = field.getName();
            Object fieldValue = null;
            Class declaredFieldType = field.getType();


            // Check if the field has a final modifier.

            int fieldModifiers = field.getModifiers();

            if (Modifier.isFinal(fieldModifiers)) {

                StringConcatenator message =
                    new StringConcatenator("The field ", realType.getName(), "#", fieldName,
                                           " is final which will be an issue with deserialization!",
                                           " Consider changing the field's modifier or implement a custom transformation rule for this class.");
                throw new TransformationException(message.toString());
            }


            // Check if a field with the same name was already processed.

            if (processedFields.contains(fieldName)) {

                StringConcatenator message =
                    new StringConcatenator("The class ", realType.getName(),
                                           " possesses more than one field with the name \"", fieldName, "\"!",
                                           " This ambiguity makes it too complicated to access the field's value with getter and setter methods.");
                throw new TransformationException(message.toString());
            }


            // Access the field's value.

            try {

                fieldValue = ReflectionHelper.invokeGetter(object, fieldName);

            } catch (NoSuchMethodException e) {

                StringConcatenator message =
                    new StringConcatenator("No public getter method could be identified for ", realType.getName(), "#",
                                           fieldName, "!");
                throw new TransformationException(message.toString(), e);

            } catch (IllegalAccessException e) {

                StringConcatenator message =
                    new StringConcatenator("Couldn't access getter method for ", realType.getName(), "#", fieldName,
                                           "!");
                throw new TransformationException(message.toString(), e);

            } catch (InvocationTargetException e) {

                StringConcatenator message =
                    new StringConcatenator("Invoking the getter method for ", realType.getName(), "#", fieldName,
                                           " caused an exception!");
                throw new TransformationException(message.toString(), e);
            }


            // Ignore fields with a null value.

            if (fieldValue == null) {

                continue;
            }


            // Build the parameters to process (transform) the field.


            TransformationParameters fieldParameters =
                TransformationHelper.newTransformationParameters(transformationPath, fieldValue, field.getType());
            fieldParameters.addPrerequisite(OBJECT_CACHE, cache);
            fieldParameters.addPrerequisite(XML_DOCUMENT, document);
            fieldParameters.addPrerequisite(ROOT_ELEMENT, rootElement);


            // Provide additonal informations for container types.

            if (Collection.class.isInstance(fieldValue)) {

                Class expectedAnnotation = ContainerInformations.class;

                if (!field.isAnnotationPresent(expectedAnnotation)) {

                    StringConcatenator message =
                        new StringConcatenator("The declared element type of a container (", realType.getName(), "#",
                                               fieldName,
                                               ") was not specified! Use the annotation @ContainerInformations.");
                    throw new TransformationException(message.toString());
                }

                ContainerInformations containerInformations =
                    (ContainerInformations) field.getAnnotation(expectedAnnotation);
                fieldParameters.addPrerequisite(DECLARED_ELEMENT_TYPE, containerInformations.declaredElementType());

            } else if (Map.class.isInstance(fieldValue)) {

                Class expectedAnnotation = MapInformations.class;

                if (!field.isAnnotationPresent(expectedAnnotation)) {

                    StringConcatenator message =
                        new StringConcatenator("The declared key and value types of a container (", realType.getName(),
                                               "#", fieldName,
                                               ") were not specified!  Use the annotation @MapInformations.");
                    throw new TransformationException(message.toString());
                }

                MapInformations mapInformations = (MapInformations) field.getAnnotation(expectedAnnotation);
                fieldParameters.addPrerequisite(DECLARED_KEY_TYPE, mapInformations.declaredKeyType());
                fieldParameters.addPrerequisite(DECLARED_VALUE_TYPE, mapInformations.declaredValueType());
            }


            // Call the transformation factory to process the field.

            ID fieldID = (ID) factory.transform(fieldParameters);


            // This composite element requires references to its fields. The
            // document (i.e. this element) needs to be updated.

            Element fieldElement = XmlHelper.createXmlElement(document, FIELD_ELEMENT);
            fieldElement.setAttribute(NAME_ATTRIBUTE.getTagname(), fieldName);
            fieldElement.setAttribute(DECLARED_TYPE_ATTRIBUTE.getTagname(), declaredFieldType.getName());
            fieldElement.setAttribute(REFERENCED_OBJECT_ATTRIBUTE.getTagname(), fieldID.toString());
            aParentElement.appendChild(fieldElement);


            // Remember the name of the field which was processed.

            processedFields.add(fieldName);
        }
    }

}
