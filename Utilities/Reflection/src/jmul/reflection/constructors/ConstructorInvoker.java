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

package jmul.reflection.constructors;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * This class implements the invocation of a specific constructor.
 *
 * @param <T> the class whose constructors should be invoked
 */
public class ConstructorInvoker<T> {

    /**
     * An exception  clazz.
     */
    private final Class<T> clazz;

    /**
     * A constructor signature.
     */
    private final Class[] signature;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aClazz
     *        a class
     * @param aSignature
     *        a parameter signature
     */
    public ConstructorInvoker(Class<T> aClazz, Class... aSignature) {

        clazz = aClazz;
        signature = aSignature;
    }

    /**
     * Returns a constructor according to internal specifications.
     *
     * @return a constructor
     *
     * @throws NoSuchMethodException
     *         is thrown if no constructor exists that matches a specified
     *         signature
     */
    Constructor<T> getConstructor() throws NoSuchMethodException {

        return clazz.getConstructor(signature);
    }

    /**
     * Invokes a constructor with the specified parameters.
     *
     * @param someParameters
     *        all parameters
     *
     * @return a new exception instance
     *
     * @throws NoSuchMethodException
     *         is thrown if no constructor exists that matches a specified
     *         signature
     * @throws InstantiationException
     *         is thrown if the instantiation fails
     * @throws IllegalAccessException
     *         is thrown if the constructor has restricted access
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the constructor
     */
    public T invoke(Object... someParameters) throws NoSuchMethodException, InstantiationException,
                                                     IllegalAccessException, InvocationTargetException {

        return getConstructor().newInstance(someParameters);
    }

    /**
     * Returns the associated class.
     *
     * @return a class
     */
    public Class getAssociatedClass() {

        return clazz;
    }

}
