/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package jmul.reflection.classes.equivalence;


import jmul.misc.exceptions.NullParameterException;

import jmul.reflection.classes.ClassDefinition;


/**
 * A helper class for checking parameters.
 *
 * @author Kristian Kutin
 */
final class ParameterCheckHelper {

    /**
     * The default constructor.
     */
    private ParameterCheckHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks the specified parameter.
     *
     * @param aClassDefinition
     *        a class definition parameter
     *
     * @return the specified class
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    static ClassDefinition checkClassDefinitionParameter(ClassDefinition aClassDefinition) {

        if (aClassDefinition == null) {

            throw new NullParameterException();
        }

        return aClassDefinition;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aClassDefinition
     *        a class definition parameter
     *
     * @return the specified class
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    static ClassDefinition checkClassParameter(ClassDefinition aClassDefinition) {

        if (aClassDefinition == null) {

            throw new NullParameterException();
        }

        if (!aClassDefinition.isClass()) {

            throw new IllegalArgumentException("No class was specified!");
        }

        return aClassDefinition;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aClassDefinition
     *        a class definition parameter
     *
     * @return the specified class
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    static ClassDefinition checkInterfaceParameter(ClassDefinition aClassDefinition) {

        if (aClassDefinition == null) {

            throw new NullParameterException();
        }

        if (!aClassDefinition.isInterface()) {

            throw new IllegalArgumentException("No interface was specified!");
        }

        return aClassDefinition;
    }

}
