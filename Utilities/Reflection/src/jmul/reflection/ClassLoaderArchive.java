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

package jmul.reflection;


import java.util.HashMap;
import java.util.Map;

import jmul.string.TextHelper;


/**
 * The class implements a class loader archive.
 *
 * @author Kristian Kutin
 */
class ClassLoaderArchive {

    /**
     * The class member contains class loaders.
     */
    private Map<Object, ClassLoader> classLoaderMap;

    /**
     * The defrault constructor.
     */
    ClassLoaderArchive() {

        classLoaderMap = new HashMap<>();
    }

    /**
     * The method adds a class loader to the archive.
     *
     * @param aKey
     *        a key value to identify the class loader
     * @param aClassLoader
     *        a class laoder
     */
    public void addClassLoader(Object aKey, ClassLoader aClassLoader) {

        boolean exists = classLoaderMap.containsKey(aKey);
        if (exists) {

            String message =
                TextHelper.concatenateStrings("A class loader with the identifier \"", aKey, "\" already exists!");
            throw new ClassLoaderException(message);
        }

        classLoaderMap.put(aKey, aClassLoader);
    }

    /**
     * The method checks if the archive already knows a class loader with a
     * certain identifier.
     *
     * @param aKey
     *        a key value to identify a class loader
     *
     * @return boolean if the class laoder is listed in the archive, else false
     */
    public boolean isKnownClassLoader(Object aKey) {

        return classLoaderMap.containsKey(aKey);
    }

    /**
     * The method return a class loader.
     *
     * @param aKey
     *        a key value to identify a class loader
     *
     * @return a class loader
     */
    public ClassLoader getClassLoader(Object aKey) {

        ClassLoader classLoader = classLoaderMap.get(aKey);

        if (classLoader == null) {

            String message = TextHelper.concatenateStrings("The class loader archive has no entry for \"", aKey, "\"!");
            throw new ClassLoaderException(message);
        }

        return classLoader;
    }

}
