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


import java.util.HashMap;
import java.util.Map;

import jmul.measures.rules.ConversionRule;

import jmul.string.TextHelper;


/**
 * An implementation of a unit of measurement.
 *
 * @author Kristian Kutin
 */
public class MeasurementUnitImpl implements MeasurementUnit {

    /**
     * The class member references the system of measurement this unit of
     * measurement belongs to.
     */
    private MeasurementSystem system;

    /**
     * The class member contains the name of this unit of measurement.
     */
    private final String name;

    /**
     * The class member contains the abbreviation of this unit of measurement.
     */
    private final String abbreviation;

    /**
     * The class member contains the categroy this unit of measurement belongs
     * to.
     */
    private final String category;

    /**
     * The class member contains all conversion rules concerning this unit of
     * measurement.
     */
    private Map<MeasurementUnit, ConversionRule> conversionRules;

    /**
     * The default constructor.
     *
     * @param aSystem
     *        a reference to the system of measurement this unit of measurement
     *        belongs to
     * @param aName
     *        a name for this unit of measurement
     * @param anAbbreviation
     *        an abbreviation for this unit of measurement
     * @param aCategory
     *        a category this unit of measurement belongs to.
     */
    public MeasurementUnitImpl(MeasurementSystem aSystem, String aName, String anAbbreviation, String aCategory) {

        system = aSystem;

        name = aName;
        abbreviation = anAbbreviation;
        category = aCategory;

        conversionRules = new HashMap<>();
    }

    /**
     * The method returns the name of this unit of measurement.
     *
     * @return a name
     */
    @Override
    public String getName() {

        return name;
    }

    /**
     * The method returns the abbreviation of this unit of measurement.
     *
     * @return an abbreviation
     */
    @Override
    public String getAbbreviation() {

        return abbreviation;
    }

    /**
     * The method returns the category to which this unit of measurement
     * belongs.
     *
     * @return a category
     */
    @Override
    public String getCategory() {

        return category;
    }

    /**
     * The method adds a conversion rule to this unit of measurement.
     *
     * @param aRule
     *        a conversion rule
     */
    @Override
    public void addConversionRule(ConversionRule aRule) {

        conversionRules.put(aRule.getTargetUnit(), aRule);
    }

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
    @Override
    public Number convert(Number aQuantity, String aTargetUnit) {

        MeasurementUnit unit = system.getUnit(aTargetUnit);

        if (unit == null) {

            String message =
                TextHelper.concatenateStrings("There exists no rule to convert ", getAbbreviation(), " to ",
                                              aTargetUnit, "!");
            throw new IllegalArgumentException(message);
        }

        return convert(aQuantity, unit);
    }

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
    @Override
    public Number convert(Number aQuantity, MeasurementUnit aTargetUnit) {

        ConversionRule rule = conversionRules.get(aTargetUnit);

        boolean notExistsRule = rule == null;
        if (notExistsRule) {

            String message =
                TextHelper.concatenateStrings("There exists no rule to convert ", getAbbreviation(), " to ",
                                              aTargetUnit.getAbbreviation(), "!");
            throw new IllegalArgumentException(message);
        }

        return rule.convert(aQuantity);
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getAbbreviation();
    }

}
