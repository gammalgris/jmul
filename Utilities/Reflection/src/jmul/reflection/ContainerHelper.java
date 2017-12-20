/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import static jmul.string.Constants.COMMA;
import jmul.string.TextHelper;


/**
 * A utility class.
 *
 * @author Kristian Kutin
 */
public final class ContainerHelper {

    /**
     * A property key.
     */
    private static final String KNOWN_COLLECTION_IMPLEMENTATIONS_KEY = "knownCollectionImplementations";

    /**
     * A property key.
     */
    private static final String KNOWN_MAP_IMPLEMENTATIONS_KEY = "knownMapImplementations";

    /**
     * A list of all known collection implementations.
     */
    private static final Collection<Class> KNOWN_COLLECTION_IMPLEMENTATIONS;

    /**
     * A list of all known collection implementations.
     */
    private static final Collection<Class> KNOWN_MAP_IMPLEMENTATIONS;

    /*
     * The static initializer.
     */
    static {

        ResourceBundle bundle = ResourceBundle.getBundle(ContainerHelper.class.getName());

        String value = null;
        String[] classnames = null;


        value = bundle.getString(KNOWN_COLLECTION_IMPLEMENTATIONS_KEY);
        classnames = value.split(COMMA);

        Collection<Class> tmp1 = new ArrayList<>();

        for (String classname : classnames) {

            ClassDefinition definition = null;

            try {

                definition = ClassHelper.getClass(classname);

            } catch (ClassNotFoundException e) {

                String message = TextHelper.concatenateStrings("Unknown class (", classname, ")!");
                throw new IllegalArgumentException(message, e);
            }

            tmp1.add(definition.getType());
        }

        KNOWN_COLLECTION_IMPLEMENTATIONS = Collections.unmodifiableCollection(tmp1);


        value = bundle.getString(KNOWN_MAP_IMPLEMENTATIONS_KEY);
        classnames = value.split(COMMA);

        Collection<Class> tmp2 = new ArrayList<>();

        for (String classname : classnames) {

            ClassDefinition definition = null;

            try {

                definition = ClassHelper.getClass(classname);

            } catch (ClassNotFoundException e) {

                String message = TextHelper.concatenateStrings("Unknown class (", classname, ")!");
                throw new IllegalArgumentException(message, e);
            }

            tmp2.add(definition.getType());
        }

        KNOWN_MAP_IMPLEMENTATIONS = Collections.unmodifiableCollection(tmp2);
    }

    /**
     * The default constructor.
     */
    private ContainerHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * The method determines if the specified object is inheriting from a known
     * collection implementation.
     *
     * @param anObject
     *        an object
     *
     * @return the class of a known collection implementation or
     *         <code>null</code> if no such inheritance relation exists
     */
    public static Class getConcreteCollectionImplementation(Object anObject) {

        return getConcreteCollectionImplementation(anObject.getClass());
    }

    /**
     * The method determines if the specified class is inheriting from a known
     * collection implementation.
     *
     * @param aClass
     *        a class
     *
     * @return the class of a known collection implementation or
     *         <code>null</code> if no such inheritance relation exists
     */
    public static Class getConcreteCollectionImplementation(Class aClass) {

        // If the specified parameter is null then by default we return null.
        // This is the exit condition for recursively calling this method.

        if (aClass == null) {

            return null;
        }


        // Check if the specified class matches one of the known implementation
        // classes.

        Class match = null;

        for (Class expectedClass : KNOWN_COLLECTION_IMPLEMENTATIONS) {

            if (expectedClass == aClass) {

                match = expectedClass;
            }

            if (match != null) {

                break;
            }
        }


        // If no match was found we recurse into the inheritance tree.

        if (match == null) {

            match = getConcreteCollectionImplementation(aClass.getSuperclass());
        }

        return match;
    }

    /**
     * The method determines if the specified object is inheriting from a known
     * map implementation.
     *
     * @param anObject
     *        an object
     *
     * @return the class of a known map implementation or <code>null</code> if
     *         no such inheritance relation exists
     */
    public static Class getConcreteMapImplementation(Object anObject) {

        return getConcreteMapImplementation(anObject.getClass());
    }

    /**
     * The method determines if the specified class is inheriting from a known
     * map implementation.
     *
     * @param aClass
     *        a class
     *
     * @return the class of a known map implementation or <code>null</code> if
     *         no such inheritance relation exists
     */
    public static Class getConcreteMapImplementation(Class aClass) {

        // If the specified parameter is null then by default we return null.
        // This is the exit condition for recursively calling this method.

        if (aClass == null) {

            return null;
        }


        // Check if the specified class matches one of the known implementation
        // classes.

        Class match = null;

        for (Class expectedClass : KNOWN_MAP_IMPLEMENTATIONS) {

            if (expectedClass == aClass) {

                match = expectedClass;
            }

            if (match != null) {

                break;
            }
        }


        // If no match was found we recurse into the inheritance tree.

        if (match == null) {

            match = getConcreteMapImplementation(aClass.getSuperclass());
        }

        return match;
    }

}
