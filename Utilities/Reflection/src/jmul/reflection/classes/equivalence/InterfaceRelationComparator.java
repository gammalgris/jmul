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

package jmul.reflection.classes.equivalence;


import jmul.reflection.classes.ClassDefinition;


/**
 * An implementation of a class equivalence comparator.
 *
 * @author Kristian Kutin
 */
public class InterfaceRelationComparator implements ClassEquivalenceComparator {

    /**
     * A constant value.
     */
    private static final boolean RECURSE = true;

    /**
     * The default constructor.
     */
    public InterfaceRelationComparator() {

        super();
    }

    /**
     * The method checks if the specified classes are considered equal (i.e. the specified class
     * implements the specified interface).
     *
     * @param anInterface
     *        a class
     * @param aBaseClass
     *        a class
     *
     * @return <code>true</code> if the specified classes are considered equal,
     *         else <code>false</code>
     */
    @Override
    public boolean compareClasses(ClassDefinition anInterface, ClassDefinition aBaseClass) {

        ParameterCheckHelper.checkClassDefinitionParameter(anInterface);
        ParameterCheckHelper.checkClassDefinitionParameter(aBaseClass);


        // Check if the specified class implements the specified interface.
        boolean result =
            aBaseClass.isClass() && anInterface.isInterface() && aBaseClass.implementsInterface(anInterface, RECURSE);

        if (!result) {

            // Check if the specified class is an interface and extends the
            // specified interface.
            result =
                aBaseClass.isInterface() && anInterface.isInterface() &&
                aBaseClass.extendsInterface(anInterface, RECURSE);
        }

        return result;
    }

}
