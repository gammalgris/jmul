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

package jmul.test.gui.types;


import java.awt.Component;


/**
 * A base implementation for a specific data type which contains a
 * GUI component.
 *
 * @author Kristian Kutin
 */
abstract class AbstractComponentEntity {

    /**
     * A GUI component.
     */
    private final Component component;

    /**
     * Creates a new instance of this data structure according to the specified parameters.
     *
     * @param aComponent
     *        a GUI component
     */
    protected AbstractComponentEntity(Component aComponent) {

        super();

        component = aComponent;
    }

    /**
     * A getter method.
     *
     * @return the GUI component or <code>null</code> if none was specified
     */
    public Component getComponent() {

        return component;
    }

}
