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

package jmul.measures.rules;


import jmul.measures.MeasurementUnit;


/**
 * An implementation of a conversion rule.
 *
 * @author Kristian Kutin
 */
public class ConversionRuleImpl implements ConversionRule {

    /**
     * The class member contains the target unit.
     */
    private final MeasurementUnit target;

    /**
     * The class member contains the conversion ratio.
     */
    private final Number ratio;

    /**
     * The default constructor.
     *
     * @param aTarget
     *        a target unit
     * @param aRatio
     *        a conversion ratio
     */
    public ConversionRuleImpl(MeasurementUnit aTarget, Number aRatio) {

        target = aTarget;
        ratio = aRatio;
    }

    /**
     * The method return the target unit.
     *
     * @return a unit of measurement
     */
    @Override
    public MeasurementUnit getTargetUnit() {

        return target;
    }

    /**
     * The method performs the actual conversion.
     *
     * @param aQuantity
     *        a quantity
     *
     * @return the converted quantity
     */
    @Override
    public Number convert(Number aQuantity) {

        return Double.valueOf(aQuantity.doubleValue() * ratio.doubleValue());
    }

}
