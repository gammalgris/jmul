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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.web;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * This class represents a starter for a web server.
 *
 * @author Kristian Kutin
 */
public final class WebServerRunner {

    /**
     * A string constant.
     */
    private static final String PARAMETER_SIGNATURE_STRING = "(String)";

    /**
     * A parameter index.
     */
    private static final int CLASS_PARAMETER_INDEX = 0;

    /**
     * A parameter index.
     */
    private static final int CONFIGURATION_PARAMETER_INDEX = 1;

    /**
     * The default constructor.
     */
    private WebServerRunner() {

        throw new UnsupportedOperationException();
    }

    /**
     * Starts the web server. The specified command line arguments are not processed.
     *
     * @param args
     *        some command line arguments
     */
    public static void main(String[] args) {

        Class clazz = extractClass(args);
        String configuration = extractConfiguration(args);

        Thread.currentThread().setName(clazz.getSimpleName());

        WebServer webServer = instantiateWebServer(clazz, configuration);
        webServer.startServer();
    }

    /**
     * Checks the specified parameter.
     *
     * @param allCommandLineParameters
     *        all command line parameters
     */
    private static void checkCommandLineParameters(String[] allCommandLineParameters) {

        if (allCommandLineParameters == null) {

            String message = "No command line parameters were specified (null)!";
            throw new IllegalArgumentException(message);
        }

        if (allCommandLineParameters.length == 0) {

            String message = "No command line parameters were specified (empty array)!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks if a parameter at the specified index exists.
     *
     * @param allCommandLineParameters
     *        all command line parameters
     * @param anIndex
     *        the index of a particular parameter
     *
     * @return <code>true</code> if a parameter exists at the specified index,
     *         else <code>false</code>
     */
    private static boolean existsParameterAtIndex(String[] allCommandLineParameters, int anIndex) {

        checkCommandLineParameters(allCommandLineParameters);

        return anIndex < allCommandLineParameters.length;
    }

    /**
     * Checks the existence of a particlar parameter.
     *
     * @param allCommandLineParameters
     *        all command line parameters
     * @param anIndex
     *        the index of a particular parameter
     */
    private static void checkCommandLineParameter(String[] allCommandLineParameters, int anIndex) {

        int parameterNumber = anIndex + 1;

        if (!existsParameterAtIndex(allCommandLineParameters, anIndex)) {

            String message = "Parameter " + parameterNumber + " is missing! No class has been specified (null).";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Extracts a class from the specified command line parameters.
     *
     * @param allCommandLineParameters
     *        all command line parameters
     *
     * @return a class
     */
    private static Class extractClass(String[] allCommandLineParameters) {

        checkCommandLineParameter(allCommandLineParameters, CLASS_PARAMETER_INDEX);

        String className = allCommandLineParameters[CLASS_PARAMETER_INDEX];
        Class clazz = null;

        try {

            clazz = Class.forName(className);

        } catch (ClassNotFoundException e) {

            String message = "An invalid class name was specified \"" + className + "\"!";
            throw new IllegalArgumentException(message, e);
        }

        return clazz;
    }

    /**
     * Extracts a configuration name from the specified command line parameters.
     *
     * @param allCommandLineParameters
     *        all command line parameters
     *
     * @return the name of a configuration or <code>null</code> if no configuration
     *         was specified
     */
    private static String extractConfiguration(String[] allCommandLineParameters) {

        if (existsParameterAtIndex(allCommandLineParameters, CONFIGURATION_PARAMETER_INDEX)) {

            return allCommandLineParameters[CONFIGURATION_PARAMETER_INDEX];

        } else {

            return null;
        }
    }

    /**
     * Instantiates a web server according to the specified parameters.
     *
     * @param aClass
     * @param aConfiguration
     *
     * @return a web server instance
     */
    private static WebServer instantiateWebServer(Class aClass, String aConfiguration) {

        Object instance;


        if (aConfiguration == null) {

            instance = instantiateWithDefaultConfiguration(aClass);

        } else {

            instance = instantiateWithAlternateConfiguration(aClass, aConfiguration);
        }


        Class actualClass = instance.getClass();
        Class expectedClass = WebServer.class;

        if (!expectedClass.isAssignableFrom(actualClass)) {

            String message = "The specified class is no web server implementation (" + actualClass.getName() + ")!";
            throw new IllegalArgumentException(message);
        }


        return (WebServer) instance;
    }

    /**
     * Instantiates the web server with the default configuration.
     *
     * @param aClass
     *
     * @return a web server instance
     *
     * @deprecated This way doesn't work since the implementation classes don't have a default
     *             constructor.
     */
    @Deprecated
    private static Object instantiateWithDefaultConfiguration(Class aClass) {

        Object instance = null;

        try {

            instance = aClass.newInstance();

        } catch (IllegalAccessException e) {

            String message = "The default constructor is not accessible (" + aClass.getName() + ")!";
            throw new IllegalArgumentException(message, e);

        } catch (InstantiationException e) {

            String message = "The class cannot be instantiated  (" + aClass.getName() + ")!";
            throw new IllegalArgumentException(message, e);
        }

        return instance;
    }

    /**
     * Instantiates the web server with the specified configuration.
     *
     * @param aClass
     * @param aConfiguration
     *
     * @return a web server instance
     */
    private static Object instantiateWithAlternateConfiguration(Class aClass, String aConfiguration) {

        Object instance = null;

        Class[] parameterSignature = new Class[] { String.class };
        Object[] parameters = new Object[] { aConfiguration };


        try {

            Constructor constructor = aClass.getConstructor(parameterSignature);
            instance = constructor.newInstance(parameters);

        } catch (NoSuchMethodException e) {

            String message = "No such constructor exists (" + getConstructorSignature(aClass) + ")!";
            throw new IllegalArgumentException(message, e);

        } catch (InvocationTargetException e) {

            String message =
                "An error occurred while invoking the constructor (" + getConstructorSignature(aClass) + ")!";
            throw new IllegalArgumentException(message, e);

        } catch (IllegalAccessException e) {

            String message = "The constructor is not accessible (" + getConstructorSignature(aClass) + ")!";
            throw new IllegalArgumentException(message, e);

        } catch (InstantiationException e) {

            String message = "The class cannot be instantiated (" + aClass.getName() + ")!";
            throw new IllegalArgumentException(message, e);
        }

        return instance;
    }

    /**
     * Returns a constructor signature as string.
     *
     * @param aClass
     *
     * @return a string
     */
    private static String getConstructorSignature(Class aClass) {

        return "" + aClass.getName() + "." + aClass.getSimpleName() + PARAMETER_SIGNATURE_STRING;
    }

}
