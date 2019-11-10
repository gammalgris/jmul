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
public class ExtendedClassEquivalenceComparatorImpl implements ClassEquivalenceComparator {

    /**
     * The default constructor.
     */
    public ExtendedClassEquivalenceComparatorImpl() {

        super();
    }

    /**
     * The method checks if the specified classes are considered equal (i.e. several
     * comparison methods are applied).
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

        boolean matches = false;

        ClassEquivalenceComparator[] comparators = {
            new ClassEquivalenceComparatorImpl(), new PrimitiveTypeComparator(), new PrimitiveWrapperComparator(),
            new InterfaceRelationComparator(), new ParentClassRelationComparator()
        };

        for (ClassEquivalenceComparator comparator : comparators) {

            matches = comparator.compareClasses(firstClass, secondClass);

            if (matches) {

                break;
            }
        }

        return matches;
    }

}
