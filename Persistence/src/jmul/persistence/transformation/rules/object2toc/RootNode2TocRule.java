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

package jmul.persistence.transformation.rules.object2toc;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jmul.transformation.TransformationRuleBase;

import jmul.persistence.annotations.AnnotationHelper;
import jmul.persistence.annotations.RootNode;
import jmul.persistence.transformation.TransformationHelper;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;

import jmul.classes.ClassDefinition;
import jmul.classes.ClassHelper;
import jmul.reflection.ReflectionHelper;
import jmul.string.StringConcatenator;


/**
 * A transformation rule which handles a root node. A root node is expected to
 * be a composite object.<br>
 * <br>
 * <i>A TOC (table of contents) contains informations about a graph which
 * supports advanced querying mechanisms.</i>
 *
 * @author Kristian Kutin
 */
public class RootNode2TocRule extends TransformationRuleBase {

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
    public RootNode2TocRule(String anOrigin, String aDestination,
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

        Class realType = someParameters.getObject().getClass();

        boolean result =
            AnnotationHelper.isAnnotationPresent(realType, RootNode.class,
                                                 true);
        return result;
    }

    /**
     * The method performs a transformation.<br>
     * <br>
     * <i>The specified parameters must contain the xml document which is used
     * as output. When the transformation is finished then the specified xml
     * document contains the actual transformation result.</i>
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return an object cointaining the TOC
     */
    public Object transform(TransformationParameters someParameters) {

        Map<String, String> toc = new HashMap<String, String>();


        // Step 1
        //
        // Retrieve some required informations about the object which is to be
        // transformed.

        Object object = someParameters.getObject();
        Class realType = someParameters.getRealType();


        // Step 2
        //
        // In the next step all class members which are persisted need to be
        // processed (i.e. all non static class members which are not marked
        // with the annotation @Exempted).
        //
        // The fields which meet the criteria are used to build the TOC.

        Collection<Field> persistableFields =
            TransformationHelper.getAllPersistableFields(realType);

        for (Field field : persistableFields) {

            // Get informations about a field.

            String fieldName = field.getName();
            Object fieldValue = null;
            Class declaredFieldType = field.getType();


            // Check if this field is a primitive type.

            ClassDefinition definition = null;
            ClassDefinition stringType = null;

            try {

                definition = ClassHelper.getClass(declaredFieldType);
                stringType = ClassHelper.getClass(CharSequence.class);

            } catch (ClassNotFoundException e) {

                StringConcatenator message =
                    new StringConcatenator("Unknown class (",
                                           declaredFieldType.getName(), ")!");
                throw new IllegalArgumentException(message.toString());
            }

            // TODO
            // Actually the field's real type must be checked and not the
            // declared type!
            // The same applies for Object->XML transformation

            if (!(definition.isPrimitiveType() ||
                  definition.isPrimitiveWrapper() ||
                  (definition.isInterface() &&
                   definition.extendsInterface(stringType, true)) ||
                  (!definition.isInterface() &&
                   definition.implementsInterface(stringType, true)))) {

                // The field is not of a primitive type. Ignore it.

                continue;
            }


            // Check if the field has a final modifier.

            int fieldModifiers = field.getModifiers();

            if (Modifier.isFinal(fieldModifiers)) {

                // Ignore this field. Final fields are not going to be
                // persisted. Only informations from persisted fields will
                // stored in the TOC.

                continue;
            }


            // Access the field's value.

            try {

                fieldValue = ReflectionHelper.invokeGetter(object, fieldName);

            } catch (NoSuchMethodException e) {

                StringConcatenator message =
                    new StringConcatenator("No public getter method could be identified for ",
                                           realType.getName(), "#", fieldName,
                                           "!");
                throw new TransformationException(message.toString(), e);

            } catch (IllegalAccessException e) {

                StringConcatenator message =
                    new StringConcatenator("Couldn't access getter method for ",
                                           realType.getName(), "#", fieldName,
                                           "!");
                throw new TransformationException(message.toString(), e);

            } catch (InvocationTargetException e) {

                StringConcatenator message =
                    new StringConcatenator("Invoking the getter method for ",
                                           realType.getName(), "#", fieldName,
                                           " caused an exception!");
                throw new TransformationException(message.toString(), e);
            }


            toc.put(fieldName, String.valueOf(fieldValue));
        }


        return toc;
    }

}
