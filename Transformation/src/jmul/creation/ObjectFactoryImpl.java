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

package jmul.creation;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import jmul.classes.ClassDefinition;
import jmul.classes.ClassHelper;
import jmul.creation.creators.ObjectCreator;
import jmul.reflection.Initializer;
import jmul.string.StringConcatenator;


/**
 * An implementation of a rule based object factory.
 *
 * @author Kristian Kutin
 */
public class ObjectFactoryImpl implements ObjectFactory {

    /**
     * Contains and manages all creation rules.
     */
    private Map<String, ObjectCreator> objectMap;

    /**
     * The default ocnstructor.
     */
    public ObjectFactoryImpl() {

        objectMap = new HashMap<String, ObjectCreator>();

        initFactory();
    }

    /**
     * Initializes this factory.
     */
    private void initFactory() {

        ResourceBundle resource =
            ResourceBundle.getBundle(ObjectFactory.class.getName());

        Enumeration<String> entries = resource.getKeys();
        while (entries.hasMoreElements()) {

            String key = entries.nextElement();
            String value = resource.getString(key);
            ClassDefinition creatorClass = null;

            try {

                creatorClass = ClassHelper.getClass(value);

            } catch (ClassNotFoundException e) {

                StringConcatenator message =
                    new StringConcatenator("Unknown class (", value, ")!");
                throw new IllegalArgumentException(message.toString());
            }


            Initializer initializer = new Initializer(creatorClass);
            ObjectCreator creator =
                (ObjectCreator)initializer.newInitializedInstance();


            objectMap.put(key, creator);
        }
    }

    /**
     * Instantiates a new object according to the provided informations. If the
     * object is of a primitive type (e.g. int, boolean, etc.) then it is
     * initialized with a default value, else its content is initialized with
     * <code>null</code>.
     *
     * @param someClassInformations
     *        some class informations
     *
     * @return a new object
     */
    public Object newInstance(ClassDefinition someClassInformations) {

        Object result = null;

        Class clazz = someClassInformations.getType();
        String className = clazz.getName();
        boolean existsRule = objectMap.containsKey(className);


        // Does a rule exist?

        if (existsRule) {

            ObjectCreator creator = objectMap.get(className);
            return creator.createObject(null, null);

        }


        // If not then try the default constructor.

        try {

            result = clazz.newInstance();

        } catch (IllegalAccessException e) {

            StringConcatenator message =
                new StringConcatenator("The class ", className,
                                       " doesn't have a public default constructor!");
            throw new IllegalArgumentException(message.toString());

        } catch (InstantiationException e) {

            StringConcatenator message =
                new StringConcatenator("Couldn't create a new instance of ",
                                       className, "!");
            throw new IllegalArgumentException(message.toString());
        }

        return result;
    }

    /**
     * Instantiates a new object according to the provided informations.
     *
     * @param someClassInformations
     *        some class informations
     * @param anInitialValue
     *        a string which contains the initial value
     *
     * @return a new object
     */
    public Object newInstance(ClassDefinition someClassInformations,
                              String anInitialValue) {

        Class clazz = someClassInformations.getType();
        String className = clazz.getName();
        boolean existsRule = objectMap.containsKey(className);


        // Does a rule exist?

        if (existsRule) {

            ObjectCreator creator = objectMap.get(className);
            return creator.createObject(anInitialValue, null);

        }


        // If not then throw an exception.

        StringConcatenator message =
            new StringConcatenator("No rule exists to create a new instance of ",
                                   className, " with the initial value \"",
                                   anInitialValue, "\"!");
        throw new IllegalArgumentException(message.toString());
    }

    /**
     * Instantiates a new object according to the provided informations.
     *
     * @param someClassInformations
     *        some class informations
     * @param anInitialValue
     *        a string which contains the initial value
     * @param aPattern
     *        a pattern which tells how to parse the string which contains the
     *        inital value
     *
     * @return a new object
     */
    public Object newInstance(ClassDefinition someClassInformations,
                              String anInitialValue, String aPattern) {


        Class clazz = someClassInformations.getType();
        String className = clazz.getName();
        boolean existsRule = objectMap.containsKey(className);


        // Does a rule exist?

        if (existsRule) {

            ObjectCreator creator = objectMap.get(className);
            return creator.createObject(anInitialValue, aPattern);

        }


        // If not then throw an exception.

        StringConcatenator message =
            new StringConcatenator("No rule exists to create a new instance of ",
                                   className, " with the initial value \"",
                                   anInitialValue, "\" and the pattern \"",
                                   aPattern, "\"!");
        throw new IllegalArgumentException(message.toString());
    }

}
