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


/**
 * This interface describes a component of a formula. A component
 * represents a node in the formula tree.
 *
 * @author Kristian Kutin
 */
public interface Component {

    /**
     * Checks if this component has child components.
     *
     * @return <code>true</code> if this component has child components, else <code>false</code>
     */
    boolean hasChildren();

    /**
     * Returns the number of children.
     *
     * @return the number of children
     */
    int children();

    /**
     * Returns the child at the specified index position
     *
     * @param anIndex
     *        an index position, i.e. a number greater than or equal to zero and
     *        lesser than the number of children
     *
     * @return a child
     */
    Component getChild(int anIndex);

    /**
     * Calculates and returns the formula which is represented by this node, i.e.
     * this branch of the formula tree.
     *
     * @return a calculation result
     */
    int calculate();

}
