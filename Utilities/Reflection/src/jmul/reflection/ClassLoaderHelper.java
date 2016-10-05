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


import java.io.File;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import jmul.string.StringConcatenator;


/**
 * The class centralizes tasks to retrieve class definitions by reflection
 * mechanisms.
 *
 * @author Kristian Kutin
 */
public final class ClassLoaderHelper {

    /**
     * The class member contains class laoders that are responsible to load
     * class definitions from alternative class paths.
     */
    private static ClassLoaderArchive additionalClassLoaders = new ClassLoaderArchive();

    /**
     * The default constructor.
     */
    private ClassLoaderHelper() {
    }

    /**
     * The method loads a class definitions for a class from the default class
     * path.
     *
     * @param aClassName
     *        a class name
     *
     * @return a class definition
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if the class cannot be found
     */
    public static Class loadClass(String aClassName) throws ClassNotFoundException {

        Class c;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        if (loader != null) {

            c = Class.forName(aClassName, true, loader);

        } else {

            c = Class.forName(aClassName);
        }

        return c;
    }

    /**
     * The method loads a class definition from an alternative class path.
     *
     * @param aClassName
     *        a class name
     * @param anAlternativeClassPath
     *        an alternative class path
     *
     * @return a class definition
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if the class cannot be found
     */
    public static Class loadClass(String aClassName, String anAlternativeClassPath) throws ClassNotFoundException {

        File file = new File(anAlternativeClassPath);
        URL url = null;

        try {

            URI uri = file.toURI();
            url = uri.toURL();

        } catch (MalformedURLException e) {

            StringConcatenator message = new StringConcatenator("Invalid URL: ", anAlternativeClassPath);
            throw new IllegalArgumentException(message.toString(), e);
        }

        return loadClass(aClassName, url);
    }

    /**
     * The method loads a class definition from an alternative class path.
     *
     * @param aClassName
     *        a class name
     * @param aUrl
     *        an alternative class path
     *
     * @return a class definition
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if the class cannot be found
     */
    public static Class loadClass(String aClassName, URL aUrl) throws ClassNotFoundException {

        URL[] urlArray = { aUrl };
        ClassLoader loader;

        boolean isKnown = additionalClassLoaders.isKnownClassLoader(aUrl);
        if (isKnown) {

            loader = additionalClassLoaders.getClassLoader(aUrl);

        } else {

            loader = URLClassLoader.newInstance(urlArray);
            additionalClassLoaders.addClassLoader(aUrl, loader);
        }

        return loader.loadClass(aClassName);
    }

}
