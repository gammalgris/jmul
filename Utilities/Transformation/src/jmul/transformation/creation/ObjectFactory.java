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

package jmul.transformation.creation;


import jmul.reflection.classes.ClassDefinition;


/**
 * This interface describes a factory mechanism which is used to instantiate and
 * initialize objects.
 *
 * @author Kristian Kutin
 */
public interface ObjectFactory {

    /**
     * Instantiates a new object according to the provided informations. If the
     * object is of a primitive type (e.g. int, boolean, etc.) then it is
     * initialized with a default value, else its content is initialized with
     * <code>null</code>.
     *
     * @param someClassInformations
     *        some class informations
     *
     * @return a new object
     */
    Object newInstance(ClassDefinition someClassInformations);

    /**
     * Instantiates a new object according to the provided informations.
     *
     * @param someClassInformations
     *        some class informations
     * @param anInitialValue
     *        a string which contains the initial value
     *
     * @return a new object
     */
    Object newInstance(ClassDefinition someClassInformations,
                       String anInitialValue);

    /**
     * Instantiates a new object according to the provided informations.
     *
     * @param someClassInformations
     *        some class informations
     * @param anInitialValue
     *        a string which contains the initial value
     * @param aPattern
     *        a pattern which tells how to parse the string which contains the
     *        inital value
     *
     * @return a new object
     */
    Object newInstance(ClassDefinition someClassInformations,
                       String anInitialValue, String aPattern);

}
