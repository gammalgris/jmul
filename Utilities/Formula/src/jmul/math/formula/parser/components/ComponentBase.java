/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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

package jmul.math.formula.parser.components;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * An base implementation of a component.
 *
 * @author Kristian Kutin
 */
abstract class ComponentBase implements Component {

    /**
     * Contains all children.
     */
    private final List<Component> children;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param allChildren
     *        all children
     */
    protected ComponentBase(Component... allChildren) {

        super();

        children = Collections.unmodifiableList(toList(allChildren));
    }

    /**
     * Converts the specified array to a list.
     *
     * @param anArray
     *
     * @return a list
     */
    private List<Component> toList(Component... anArray) {

        if (anArray == null) {

            return new ArrayList<Component>();

        } else {

            return Arrays.asList(anArray);
        }
    }

    /**
     * Checks if this component has child components.
     *
     * @return <code>true</code> if this component has child components, else <code>false</code>
     */
    @Override
    public boolean hasChildren() {

        return !children.isEmpty();
    }

    /**
     * Returns the number of children.
     *
     * @return the number of children
     */
    @Override
    public int children() {

        return children.size();
    }

    /**
     * Returns the child at the specified index position
     *
     * @param anIndex
     *        an index position, i.e. a number greater than or equal to zero and
     *        lesser than the number of children
     *
     * @return a child
     */
    @Override
    public Component getChild(int anIndex) {

        return children.get(anIndex);
    }

}
