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

package jmul.math.formula;


/**
 * This interface describes a formula entity. Formula strings are parsed at
 * runtime and transformed into formula entities. Formulas can be used for
 * calculations at runtime. This mechanism is used for situations when
 * formulas cannot be hard coded.
 *
 * @author Kristian Kutin
 */
public interface Formula {

    /**
     * The method calculates a result for the formula. If a formula
     * contains variables these variables have to be resolved, i.e.
     * values should be provided for all of them.
     *
     * @return a result
     */
    int calculate();

    /**
     * The method allows to change a variable at runtime.
     *
     * @param aVariableName
     *        the name of a variable
     * @param aReference
     *        the object which contains the variable value
     */
    void setVariable(String aVariableName, Number aReference);

}
