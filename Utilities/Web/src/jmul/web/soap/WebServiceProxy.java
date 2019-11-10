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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.web.soap;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import jmul.io.ResourceType;

import jmul.checks.ParameterCheckHelper;


/**
 * This class helps to call a web service.
 *
 * @author Kristian Kutin
 */
public class WebServiceProxy {

    /**
     * A regular expression to identify a specific getter method.
     */
    private static final String GET_PORT_PATTERN = "(get){1}(.){1,}(Port){1}";

    /**
     * A regular expression to identify getter methods.
     */
    private static final String GETTER_PATTERN = "(get){1}(.){1,}";

    /**
     * A service class.
     */
    private Class serviceClass;

    /**
     * An instance of a service class.
     */
    private Object serviceInstance;

    /**
     * The actual proxy class.
     */
    private Class proxyClass;

    /**
     * An instance of the actual proxy class.
     */
    private Object proxyInstance;

    /**
     * The directory where the generated classes are located.
     */
    private String outputDirectory;

    /**
     * Creates a web server procxy according to the specified parameters.
     *
     * @param anOutputDirectory
     *        the output directory
     * @param aFileExtension
     *        the file extension of generated code
     */
    public WebServiceProxy(String anOutputDirectory, String aFileExtension) {

        ParameterCheckHelper.checkDirectoryNameParameter(anOutputDirectory);
        ParameterCheckHelper.checkStringParameter(aFileExtension);


        outputDirectory = anOutputDirectory;

        serviceClass = getServiceClass(outputDirectory, aFileExtension);

        Method getPortMethod = getGetPortMethod(serviceClass);
        proxyClass = getPortMethod.getReturnType();


        try {

            serviceInstance = serviceClass.newInstance();

        } catch (InstantiationException e) {

            String message = "Unable to create a new instance of the service class (" + serviceClass.getName() + ")!";
            throw new WebServiceProxyException(message, e);

        } catch (IllegalAccessException e) {

            String message =
                "The identified service class (" + serviceClass.getName() +
                ") doesn't have a public default constructor!";
            throw new WebServiceProxyException(message, e);
        }


        try {

            Object[] parameters = { };
            proxyInstance = getPortMethod.invoke(serviceInstance, parameters);

        } catch (IllegalAccessException e) {

            String message =
                "The service class (" + serviceClass.getName() + ") has a method " + getPortMethod.getName() +
                " which is not public!";
            throw new WebServiceProxyException(message, e);

        } catch (InvocationTargetException e) {

            String message =
                "An error occurred while invoking the method " + getPortMethod.getName() + " of the service class " +
                serviceClass.getName() + "!";
            throw new WebServiceProxyException(message, e);
        }
    }

    /**
     * Returns a service class which could be identified according to the specified
     * parameters.<br>
     * <br>
     * <i>Note:<br>
     * The service class can be located outside the known class path.</i>
     *
     * @param anOutputDirectory
     *        the output directory
     * @param aFileExtension
     *        the file extension of generated code
     *
     * @return a service class
     */
    private static Class getServiceClass(String anOutputDirectory, String aFileExtension) {

        ResourceType fileType = new ResourceType(aFileExtension);

        Class c = null;

        try {

            ClassScanner scanner = new ClassScanner(fileType, anOutputDirectory);
            c = scanner.getServiceClassDefinition().getType();

        } catch (ClassNotFoundException e) {

            String message =
                "No service class could be found at the specified location (\"" + anOutputDirectory + "\")!";
            throw new WebServiceProxyException(message, e);
        }

        return c;
    }

    /**
     * Scans a service class and returns a specific getter method.
     *
     * @param anOutputDirectory
     *        the output directory
     *
     * @return a getter method
     */
    private static Method getGetPortMethod(Class aServiceClass) {

        Collection<Method> getPortMethods = new ArrayList<>();
        Collection<Method> getterMethods = new ArrayList<>();

        Method[] allMethods = aServiceClass.getDeclaredMethods();
        for (Method method : allMethods) {

            boolean isPublicMethod = Modifier.isPublic(method.getModifiers());

            String name = method.getName();
            boolean isPortGetter = Pattern.matches(GET_PORT_PATTERN, name);
            boolean isGetter = Pattern.matches(GETTER_PATTERN, name);

            Class[] parameterSignature = method.getParameterTypes();
            int parameters = parameterSignature.length;
            boolean noParameters = parameters == 0;

            if (isPublicMethod && isPortGetter && noParameters) {

                getPortMethods.add(method);

            } else if (isPublicMethod && isGetter && noParameters) {

                getterMethods.add(method);
            }
        }


        boolean foundOneGetPortMethod = getPortMethods.size() == 1;
        if (foundOneGetPortMethod) {

            return getPortMethods.iterator().next();
        }

        boolean foundMoreThanOneGetPortMethod = getPortMethods.size() > 1;
        if (foundMoreThanOneGetPortMethod) {

            throw createTooManyGetterMethodsException(aServiceClass, GET_PORT_PATTERN);
        }


        Collection<Method> validGetterMethods = new ArrayList<>();
        for (Method getter : getterMethods) {

            Class returnType = getter.getReturnType();

            boolean returnTypeIsInterface = returnType.isInterface();
            if (returnTypeIsInterface) {
                validGetterMethods.add(getter);
            }
        }

        boolean foundOneRelevantGetterMethod = validGetterMethods.size() == 1;
        if (foundOneRelevantGetterMethod) {

            return validGetterMethods.iterator().next();
        }

        boolean foundMoreThanOneRelevantGetterMethod = validGetterMethods.size() > 1;
        if (foundMoreThanOneRelevantGetterMethod) {

            throw createTooManyGetterMethodsException(aServiceClass, GETTER_PATTERN);
        }

        throw createNoGetterMethodException(aServiceClass, GET_PORT_PATTERN);
    }

    /**
     * Returns a service class.
     *
     * @return a service class
     */
    public Class getServiceClass() {

        return serviceClass;
    }

    /**
     * Returns an instance of a service class.
     *
     * @return an instance of a service class
     */
    public Object getServiceInstance() {

        return serviceInstance;
    }

    /**
     * Returns a proxy class.
     *
     * @return a proxy class
     */
    public Class getProxyClass() {

        return proxyClass;
    }

    /**
     * Die Methode getProxyInstance liefert eine Instanz der Proxy Klasse.
     *
     * @return eine Instanz der Proxy Klasse
     */

    /**
     * Returns an instance of a service class.
     *
     * @return an instance of a service cass
     */
    public Object getProxyInstance() {

        return proxyInstance;
    }

    /**
     * Die Methode getOutputDirectory liefert die Informationin welchem
     * Verzeichnis die Proxy-Klassen abgelegt sind.
     *
     * @return ein Verzeichnis
     */

    /**
     * Returns the location where the generatred code can be found.
     *
     * @return a directory path
     */
    public String getOutputDirectory() {

        return outputDirectory;
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aServiceClass
     *        a service class
     * @param aPattern
     *        a name pattern
     *
     * @return an exception
     */
    private static WebServiceProxyException createTooManyGetterMethodsException(Class aServiceClass, String aPattern) {

        String message =
            "The service class " + aServiceClass.getName() +
            " has more than one matching getter method (see pattern \"" + aPattern + "\")!";
        return new WebServiceProxyException(message);
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aServiceClass
     *        a service class
     * @param aPattern
     *        a name pattern
     *
     * @return an exception
     */
    private static WebServiceProxyException createNoGetterMethodException(Class aServiceClass, String aPattern) {

        String message =
            "The service class " + aServiceClass.getName() + " has no matching getter method (see pattern \"" +
            aPattern + "\")";
        return new WebServiceProxyException(message);
    }

}
