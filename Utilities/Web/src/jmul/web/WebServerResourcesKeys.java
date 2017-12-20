/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package jmul.web;


import java.util.ResourceBundle;

import jmul.misc.management.ResourceIdentifier;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.string.TextHelper;


/**
 * This enumeration class contains entries for various web server resources.
 *
 * @author Krsitian Kutin
 */
public enum WebServerResourcesKeys implements ResourceIdentifier {


    LOGGER("logger"),
    SERVER("server"), ;


    /**
     * A suffix.
     */
    private static final String CLASS_SUFFIX = ".resource-class";

    /**
     * A suffix.
     */
    private static final String IMPLEMENTATION_SUFFIX = ".implementation-class";

    /**
     * The name for this resource.
     */
    private final String resourceName;

    /**
     * A class definition for this resource (i.e. this information is taken from a configuration
     * file.
     */
    private final ClassDefinition classDefinition;

    /**
     * The concrete implementation class for this resource (i.e. this information is taken from a configuration
     * file.
     */
    private final ClassDefinition implementationDefinition;

    /**
     * Creates a new enumeration element.
     *
     * @param aKey
     */
    private WebServerResourcesKeys(String aKey) {

        resourceName = aKey;

        ResourceBundle bundle = ResourceBundle.getBundle(WebServerResourcesKeys.class.getName());
        String classname = bundle.getString(resourceName + CLASS_SUFFIX);

        try {

            classDefinition = ClassHelper.getClass(classname);

        } catch (ClassNotFoundException e) {

            String message = "Unknown resource class " + classname + "!";
            throw new IllegalArgumentException(message, e);
        }


        classname = bundle.getString(resourceName + IMPLEMENTATION_SUFFIX);

        try {

            implementationDefinition = ClassHelper.getClass(classname);

        } catch (ClassNotFoundException e) {

            String message = "Unknown resource class " + classname + "!";
            throw new IllegalArgumentException(message, e);
        }


        boolean isParent = classDefinition.getType().isAssignableFrom(implementationDefinition.getType());

        if (!isParent) {

            String message =
                TextHelper.concatenateStrings("The class ", classDefinition,
                                              " is not a parent class of the specified implementation class ",
                                              implementationDefinition, "!");
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Return the name of a resource.
     *
     * @return a resource name
     */
    @Override
    public String getResourceName() {

        return resourceName;
    }

    /**
     * Returns a class definition for this resource.
     *
     * @return a class definition
     */
    public ClassDefinition getClassDefinition() {

        return classDefinition;
    }

    /**
     * Returns the implementation class definition for this resource.
     *
     * @return a class definition
     */
    public ClassDefinition getImplementationDefinition() {

        return implementationDefinition;
    }

    /**
     * Returns a string representation of this enumeration element.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getResourceName();
    }

}
