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

package jmul.reflection.classes.filter;


import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collection;


/**
 * A implementation of a filter that filters methods with a specified parameter
 * count.
 *
 * @author Kristian Kutin
 */
public class SignatureLengthFilter implements MethodFilter {

    /**
     * The class member contains the number of expected parameters.
     */
    private final int parameterCount;

    /**
     * The default constructor.
     *
     * @param aParameterCount
     *        the number of expected parameters
     */
    public SignatureLengthFilter(int aParameterCount) {

        super();

        parameterCount = aParameterCount;
    }

    /**
     * The method filters a set of methods. Inherited methods are ignored.
     *
     * @param someMethods
     *        a set of methods
     *
     * @return filtered methods
     */
    @Override
    public Method[] filterMethods(Method[] someMethods) {

        Collection<Method> matchingMethods = new ArrayList<Method>();

        for (Method foundMethod : someMethods) {

            int foundParameterCount = foundMethod.getParameterTypes().length;
            boolean matchesSignatureSize = foundParameterCount == parameterCount;

            if (matchesSignatureSize) {

                matchingMethods.add(foundMethod);
            }
        }


        Method[] result = { };
        result = matchingMethods.toArray(result);

        return result;
    }

    /**
     * The method filters a set of methods. Inherited methods are ignored.
     *
     * @param someMethods
     *        a set of methods
     *
     * @return filtered methods
     */
    @Override
    public Collection<Method> filterMethods(Collection<Method> someMethods) {

        Collection<Method> matchingMethods = new ArrayList<Method>();

        for (Method foundMethod : someMethods) {

            int foundParameterCount = foundMethod.getParameterTypes().length;

            if (foundParameterCount == parameterCount) {

                matchingMethods.add(foundMethod);
            }
        }

        return matchingMethods;
    }

}
