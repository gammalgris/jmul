/*
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


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * This class represents a starter for a web server.
 *
 * @author Kristian Kutin
 */
public final class WebServerRunner {

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

        WebServer webServer = instantiateWebServer(clazz, configuration);
        webServer.startServer();
    }

    /**
     * Checks the specified parameter.
     *
     * @param args
     */
    private static void checkCommandLineParameters(String[] args) {

        if (args == null) {

            String message = "No command line parameters were specified (null)!";
            throw new IllegalArgumentException(message);
        }

        if (args.length == 0) {

            String message = "No command line parameters were specified (empty array)!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Extracts a class from the specified command line parameters.
     *
     * @param args
     *
     * @return a class
     */
    private static Class extractClass(String[] args) {

        checkCommandLineParameters(args);


        String className = args[0];
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
     * @param args
     *
     * @return the name of a configuration or <code>null</code> if no configuration
     *         was specified
     */
    private static String extractConfiguration(String[] args) {

        checkCommandLineParameters(args);


        if (args.length == 2) {

            return args[1];
        }

        return null;
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

        Object instance = null;


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


        WebServer webServer = (WebServer) instance;

        return webServer;
    }

    /**
     * Instantiates the web server with the default configuration.
     *
     * @param aClass
     *
     * @return a web server instance
     */
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

            String message =
                "No such constructor exists (" + aClass.getName() + "." + aClass.getSimpleName() + "(String))!";
            throw new IllegalArgumentException(message, e);

        } catch (InvocationTargetException e) {

            String message =
                "An error occurred while invoking the constructor (" + aClass.getName() + "." + aClass.getSimpleName() +
                "(String))!";
            throw new IllegalArgumentException(message, e);

        } catch (IllegalAccessException e) {

            String message =
                "The constructor is not accessible (" + aClass.getName() + "." + aClass.getSimpleName() + "(String))!";
            throw new IllegalArgumentException(message, e);

        } catch (InstantiationException e) {

            String message = "The class cannot be instantiated (" + aClass.getName() + ")!";
            throw new IllegalArgumentException(message, e);
        }

        return instance;
    }

}
