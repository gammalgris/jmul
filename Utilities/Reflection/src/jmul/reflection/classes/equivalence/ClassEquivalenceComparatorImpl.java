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

package jmul.reflection.classes.equivalence;


import jmul.reflection.classes.ClassDefinition;


/**
 * An implementation of a class equivalence comparator.
 *
 * @author Kristian Kutin
 */
public class ClassEquivalenceComparatorImpl implements ClassEquivalenceComparator {

    /**
     * The default constructor.
     */
    public ClassEquivalenceComparatorImpl() {

        super();
    }

    /**
     * The method checks if the specified classes are considered equal (i.e. the second class
     * is equal to the first class).
     *
     * @param firstClass
     *        a class
     * @param secondClass
     *        a class
     *
     * @return <code>true</code> if the specified classes are considered equal,
     *         else <code>false</code>
     */
    @Override
    public boolean compareClasses(ClassDefinition firstClass, ClassDefinition secondClass) {

        ParameterCheckHelper.checkClassDefinitionParameter(firstClass);
        ParameterCheckHelper.checkClassDefinitionParameter(secondClass);


        boolean matches = false;

        if (firstClass.isInterface() && secondClass.isInterface()) {

            matches = firstClass.equalsInterface(secondClass);

        } else if (firstClass.isClass() && secondClass.isClass()) {

            matches = firstClass.equalsClass(secondClass);

        } else if (firstClass.isPrimitiveType() && secondClass.isPrimitiveType()) {

            matches = firstClass.equalsClass(secondClass);
        }

        return matches;
    }

}
