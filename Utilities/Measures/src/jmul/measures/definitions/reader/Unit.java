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

package jmul.measures.definitions.reader;


/**
 * A Helper class to store informations which is read from a definition file.
 *
 * @author Kristian Kutin
 */
public final class Unit {

    /**
     * The class member contains the name of a unit of measurement.
     */
    private final String name;

    /**
     * The class member conains the abbreviated name of a unit of measurement.
     */
    private final String abbreviation;

    /**
     * The class member contains a normalized value which allows the inference
     * of transforation rules.
     */
    private final double normalizedValue;

    /**
     * The default constructor.
     *
     * @param aName
     *        name of a unit of measurement
     * @param anAbbreviation
     *        abbreviated name of a unit of measurement
     * @param aNormalizedValue
     *        a normalized value
     */
    protected Unit(String aName, String anAbbreviation,
                   double aNormalizedValue) {

        name = aName;
        abbreviation = anAbbreviation;
        normalizedValue = aNormalizedValue;
    }

    /**
     * A getter method.
     *
     * @return a unit name
     */
    public String getName() {

        return name;
    }

    /**
     * A getter method.
     *
     * @return a unit abbreviation
     */
    public String getAbbreviation() {

        return abbreviation;
    }

    /**
     * A getter method.
     *
     * @return the normalized value for this unit
     */
    public double getNormalizedValue() {

        return normalizedValue;
    }

}
