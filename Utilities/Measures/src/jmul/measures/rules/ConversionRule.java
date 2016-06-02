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

package jmul.measures.rules;


import jmul.measures.MeasurementUnit;


/**
 * The interface describes an entity which performs the conversion of units.
 * Every rule is only responsible for a single conversion operation.
 *
 * @author Kristian Kutin
 */
public interface ConversionRule {

    /**
     * The method return the target unit.
     *
     * @return a unit of measurement
     */
    MeasurementUnit getTargetUnit();

    /**
     * The method performs the actual conversion.
     *
     * @param aQuantity
     *        a quantity
     *
     * @return the converted quantity
     */
    Number convert(Number aQuantity);

}