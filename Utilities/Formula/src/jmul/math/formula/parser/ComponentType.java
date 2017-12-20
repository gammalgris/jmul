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

package jmul.math.formula.parser;


/**
 * An enumeration for the different component types.
 *
 * @author Kristian Kutin
 */
public enum ComponentType {


    SIGNED_COMPONENT(2, "signed component"),
    UNSIGNED_COMPONENT(1, "unsigned component"),
    OPERATOR(1, "operator");


    /**
     * The class member contains the number of components this component
     * is made of.
     */
    private final int numberOfComponents;

    /**
     * The class member contains a description for this component.
     */
    private final String description;

    /**
     * The default constructor.
     *
     * @param aNumberOfComponents
     *        a number of components this component is made of
     * @param aDescription
     *        a description of this component
     */
    private ComponentType(int aNumberOfComponents, String aDescription) {

        numberOfComponents = aNumberOfComponents;
        description = aDescription;
    }

    /**
     * The method returns the number of components this component is made of.
     *
     * @return the number of components this component is made of
     */
    public int getNumberOfComponents() {

        return numberOfComponents;
    }

    /**
     * The method returns a description fpr this component.
     *
     * @return a description
     */
    public String getDescription() {

        return description;
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getDescription() + " consists of " + getNumberOfComponents() + " component(s)";
    }

}
