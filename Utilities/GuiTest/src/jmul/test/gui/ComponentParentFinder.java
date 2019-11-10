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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.test.gui;


import java.awt.Component;


/**
 * A utility class which encapsules the functionality of finding a parent of
 * a GUI component which belongs to a specific type.
 *
 * @param <T> the owner type
 *
 * @author Kristian Kutin
 */
public class ComponentParentFinder<T> {

    /**
     * A class object representing the parent type.
     */
    private Class<T> parentClass;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aParentClass
     *
     */
    public ComponentParentFinder(Class<T> aParentClass) {

        parentClass = aParentClass;
    }

    /**
     * Looks for a parent of a specific type.
     *
     * @param component
     *        a GUI component
     *
     * @return a matching parent or <code>null</code> if no such parent exists
     */
    public T findParent(Component component) {

        if (component == null) {

            return null;

        } else if (parentClass.isInstance(component)) {

            return (T) component;

        } else {

            return findParent(component.getParent());
        }
    }

}
