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

package jmul.reflection;


import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jmul.reflection.classes.ClassDefinition;

import jmul.string.StringConcatenator;


/**
 * The class will initialize a new instance of a class and initialize all
 * fields that are accessible through setter methods. The fields are initialized
 * according to the order in which they have been added to this initializer.
 *
 * @author Kristian Kutin
 */
public class Initializer {

    /**
     * The class member contains informations about the class.
     */
    private final ClassDefinition classDefinition;

    /**
     * The class member contains the fields in the order they are added to this
     * initializer.
     */
    private Collection<String> unsortedFields;

    /**
     * The class member contains field names and their contents.
     */
    private Map<String, Object> fieldMap;

    /**
     * The default constructor.
     *
     * @param aClassDefinition
     *        a class definition
     */
    public Initializer(ClassDefinition aClassDefinition) {

        classDefinition = aClassDefinition;
        unsortedFields = new ArrayList<String>();
        fieldMap = new HashMap<String, Object>();
    }

    /**
     * The method associates a field with a value.
     *
     * @param aFieldName
     *        a field name
     * @param aFieldValue
     *        a field value
     */
    public void setField(String aFieldName, Object aFieldValue) {

        // avoid duplicate field entries
        if (!unsortedFields.contains(aFieldName)) {

            unsortedFields.add(aFieldName);
        }

        // override previous entries for that field
        fieldMap.put(aFieldName, aFieldValue);
    }

    /**
     *The method removes a field and its associated value. The field will not
     * be considered when initializing a new object.
     *
     * @param aFieldName
     *        a field name
     */
    public void removeField(String aFieldName) {

        unsortedFields.remove(aFieldName);
        fieldMap.remove(aFieldName);
    }

    /**
     * The method instantiates a new instance of the class and initializes it
     * according to the informations about its fields.
     *
     * @return an initialized new instance of the class
     *
     * @throws InitializerException
     *         This exception is thrown if the initialization couldn't be
     *         finished
     */
    public Object newInitializedInstance() {

        StringConcatenator errorMessage =
            new StringConcatenator("Unable to create a new instance of the class ", classDefinition,
                                   " and to initialize the object!");

        try {

            Object object = classDefinition.getType().newInstance();

            for (String fieldName : unsortedFields) {

                Object value = fieldMap.get(fieldName);
                ReflectionHelper.invokeSetter(object, fieldName, value);
            }

            return object;

        } catch (InstantiationException e) {

            throw new InitializerException(String.valueOf(errorMessage), e);

        } catch (NoSuchMethodException e) {

            throw new InitializerException(String.valueOf(errorMessage), e);

        } catch (IllegalAccessException e) {

            throw new InitializerException(String.valueOf(errorMessage), e);

        } catch (InvocationTargetException e) {

            throw new InitializerException(String.valueOf(errorMessage), e);
        }
    }

}
