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

package jmul.measures;


import jmul.measures.rules.ConversionRule;


/**
 * The interface describes an entity which represents a unit of measurement.
 *
 * @author Kristian Kutin
 */
public interface MeasurementUnit {

    /**
     * The method returns the name of this unit of measurement.
     *
     * @return a name
     */
    String getName();

    /**
     * The method returns the abbreviation of this unit of measurement.
     *
     * @return an abbreviation
     */
    String getAbbreviation();

    /**
     * The method returns the category to which this unit of measurement
     * belongs.
     *
     * @return a category
     */
    String getCategory();

    /**
     * The method adds a conversion rule to this unit of measurement.<br>
     * <br>
     * <i>Note:<br>
     * This rule shouldn't be publicly accessible.</i>
     *
     * @param aRule
     *        a conversion rule
     *
     * @deprecated This is an internal operation and thus should not be declared
     *             in the interface.
     */
    @Deprecated
    void addConversionRule(ConversionRule aRule);

    /**
     * The method performs a conversion of units.
     *
     * @param aQuantity
     *        a quantity of this unit of measurement
     * @param aTargetUnit
     *        a target unit of measurement
     *
     * @return a converted quantity
     */
    Number convert(Number aQuantity, String aTargetUnit);

    /**
     * The method performs a conversion of units.
     *
     * @param aQuantity
     *        a quantity of this unit of measurement
     * @param aTargetUnit
     *        a target unit of measurement
     *
     * @return a converted quantity
     */
    Number convert(Number aQuantity, MeasurementUnit aTargetUnit);

}
