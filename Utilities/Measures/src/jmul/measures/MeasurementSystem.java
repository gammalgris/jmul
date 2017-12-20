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

package jmul.measures;


import java.util.Set;


/**
 * The interface describes an entity which contains a set of defined units of
 * measurement.
 * The units of measurement are grouped by categories (e.g. weight, length,
 * time, etc.).
 *
 * @author Kristian Kutin
 */
public interface MeasurementSystem {

    /**
     * The method checks if this system of measurement contains a specific
     * category of units of measurement.
     *
     * @param aCategory
     *        a name or abbreviation
     *
     * @return true, if the category exists, else false
     */
    boolean hasCategory(String aCategory);

    /**
     * The method returns a set containing all units of measurement which belong
     * to a cetain category.
     *
     * @param aCategory
     *        a category
     *
     * @return a set containing units of measurement
     */
    Set<MeasurementUnit> getUnitsByCategory(String aCategory);

    /**
     * The method checks if this system of measurement contains a specific unit
     * of measurement.
     *
     * @param aName
     *        a name or abbreviation
     *
     * @return true, if this unit of measurement exists, else false
     */
    boolean hasUnit(String aName);

    /**
     * The method returns a unit of measurement with a specific name.
     *
     * @param aName
     *        a name or abbreviation
     *
     * @return a unit of measurement
     */
    MeasurementUnit getUnit(String aName);

    /**
     * The method returns a set containing all defined categories.
     *
     * @return a set containing all categories
     */
    Set<String> getCategories();

}
