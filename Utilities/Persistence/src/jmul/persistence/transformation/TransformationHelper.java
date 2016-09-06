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

package jmul.persistence.transformation;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.Collection;

import jmul.persistence.annotations.Exempted;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationParametersImpl;
import jmul.transformation.TransformationPath;

import jmul.reflection.classes.AccessorHelper;
import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.string.StringConcatenator;


/**
 * A utlity class.
 *
 * @author Kristian Kutin
 */
public final class TransformationHelper {

    /**
     * The default constructor.
     */
    private TransformationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates basic transformation parameters. Additional informations must be
     * provided.
     *
     * @param aTransformationPath
     *        informations about the transformation path
     * @param anObject
     *        the object which is to be transformed
     *
     * @return transformation parameters without additional informations
     */
    public static TransformationParameters newTransformationParameters(TransformationPath aTransformationPath,
                                                                       Object anObject) {

        TransformationParameters parameters = new TransformationParametersImpl(aTransformationPath, anObject);

        return parameters;
    }

    /**
     * Creates basic transformation parameters. Additional informations must be
     * provided.
     *
     * @param aTransformationPath
     *        informations about the transformation path
     * @param anObject
     *        the object which is to be transformed
     * @param aDeclaredType
     *        the declared type of this object
     *
     * @return transformation parameters without additional informations
     */
    public static TransformationParameters newTransformationParameters(TransformationPath aTransformationPath,
                                                                       Object anObject, Class aDeclaredType) {

        TransformationParameters parameters =
            new TransformationParametersImpl(aTransformationPath, anObject, aDeclaredType);

        return parameters;
    }

    /**
     * The method returns all fields of this class which are persistable. Fields
     * which are not persistable are supposed to be marked with an annotation.
     *
     * @param aClass
     *        the class which is to be examined
     *
     * @return all declared fields
     */
    public static Collection<Field> getAllPersistableFields(Class aClass) {

        return getAllPersistableFields(aClass, null);
    }

    /**
     * The method returns all fields of this class which are persistable. Fields
     * which are not persistable are supposed to be marked with an annotation.
     *
     * @param aClass
     *        the class which is to be examined
     * @param anExemptedSuperclass
     *        the specified class is examined only up to the specified
     *        superclass (excluding the superclass)
     *
     * @return all declared fields
     */
    public static Collection<Field> getAllPersistableFields(Class aClass, Class anExemptedSuperclass) {

        // Check parameter.
        if (aClass == null) {

            String message = "No class was specified!";
            throw new IllegalArgumentException(message);
        }

        if (aClass.isInterface()) {

            StringConcatenator message = new StringConcatenator("The specified class is an interface (", aClass, ")!");
            throw new IllegalArgumentException(message.toString());
        }


        Collection<Field> result = new ArrayList<Field>();


        // This method will recurse through this class and all parent classes
        // to identify declared persistable fields. If we reach
        // <code>java.lang.Object</code> we are done.
        if (aClass.equals(Object.class)) {

            return result;
        }


        // Identify locally declared persistable fields.
        Field[] locallyDeclaredFields = aClass.getDeclaredFields();

        for (Field field : locallyDeclaredFields) {

            int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers) && !field.isAnnotationPresent(Exempted.class)) {

                result.add(field);
            }
        }


        // Recurse into the parent class. Interfaces only provide static members
        // and thus are not considered.

        Class superclass = aClass.getSuperclass();

        if (!superclass.equals(anExemptedSuperclass)) {

            result.addAll(TransformationHelper.getAllPersistableFields(superclass, anExemptedSuperclass));
        }

        return result;
    }


    /**
     * Determines if the specified class represents a composite type (i.e.
     * a class which provides getter and setter methods for its persistable
     * fields).
     *
     * @param aClass
     *        the class which is to be examined
     *
     * @return <code>true</code> if this class represents a composite type, else
     *         <code>false</code>
     */
    public static boolean isComposite(Class aClass) {

        return isComposite(aClass, null);
    }

    /**
     * Determines if the specified class represents a composite type (i.e.
     * a class which provides getter and setter methods for its persistable
     * fields).
     *
     * @param aClass
     *        the class which is to be examined
     * @param anExemptedSuperclass
     *        the specified class is examined only up to the specified
     *        superclass (excluding the superclass)
     *
     * @return <code>true</code> if this class represents a composite type, else
     *         <code>false</code>
     */
    public static boolean isComposite(Class aClass, Class anExemptedSuperclass) {

        // Check the specified parameter.

        ClassDefinition definition = null;

        try {

            definition = ClassHelper.getClass(aClass);

        } catch (ClassNotFoundException e) {

            StringConcatenator message =
                new StringConcatenator("An invalid class has been specified (", aClass.getName(), ")!");
            throw new IllegalArgumentException(message.toString(), e);
        }


        // Check each persistable field.


        Collection<Field> fields = getAllPersistableFields(aClass, anExemptedSuperclass);

        boolean result = !fields.isEmpty();

        for (Field field : fields) {

            String fieldName = field.getName();

            Method getter = null;
            Method setter = null;

            try {

                getter = definition.getAccessor(AccessorHelper.GETTER_PREFIX, fieldName, true);

            } catch (NoSuchMethodException e) {

                // Ignore this exception. In this case no getter method exists.
            }

            try {

                setter = definition.getAccessor(AccessorHelper.SETTER_PREFIX, fieldName, true);

            } catch (NoSuchMethodException e) {

                // Ignore this exception. In this case no setter method exists.
            }

            boolean existAccessors = (getter != null) && (setter != null);
            result = existAccessors && result;

            if (!result) {

                break;
            }
        }

        return result;
    }

}
