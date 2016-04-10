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

package jmul.classes.filter;


import java.lang.reflect.Method;

import java.util.Collection;


/**
 * The interfaces describes an entity which filters methods of a class. The
 * criteria to filter the methods are realized in implmenting classes (see
 * strategy pattern).
 *
 * @author Kristian Kutin
 */
public interface MethodFilter {

    /**
     * The method filters a set of methods.
     *
     * @param someMethods
     *        a set of methods
     *
     * @return filtered methods
     */
    Method[] filterMethods(Method[] someMethods);

    /**
     * The method filters a set of methods.
     *
     * @param someMethods
     *        a set of methods
     *
     * @return filtered methods
     */
    Collection<Method> filterMethods(Collection<Method> someMethods);

}
