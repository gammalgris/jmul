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

package jmul.reflection.classes.equivalence;


import jmul.reflection.classes.ClassDefinition;


/**
 * An implementation of an equivalence matcher.
 *
 * @author Kristian Kutin
 */
public class ParentClassRelationCheck implements EquivalenceMatcher {

    /**
     * A constant value.
     */
    private static final boolean RECURSE = true;

    /**
     * The default constructor.
     */
    public ParentClassRelationCheck() {

        super();
    }

    /**
     * The method checks if the specified base class is a subclass of the
     * specified parent class.
     *
     * @param parentClass
     *        a class
     * @param baseClass
     *        a class
     *
     * @return true, if the specified base class is a subclass of the
     * specified parent class, else false
     */
    @Override
    public boolean matchingClasses(ClassDefinition parentClass, ClassDefinition baseClass) {

        return baseClass.isClass() && parentClass.isClass() && baseClass.extendsClass(parentClass, RECURSE);
    }

}
