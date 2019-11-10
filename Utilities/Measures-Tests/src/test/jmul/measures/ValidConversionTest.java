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

package test.jmul.measures;


import java.util.ArrayList;
import java.util.Collection;

import jmul.measures.MeasurementSystem;
import jmul.measures.MeasurementSystemImpl;
import jmul.measures.MeasurementUnit;

import jmul.string.TextHelper;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * A set of tests for converting measures.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ValidConversionTest {

    /**
     * The maximum deviation regarding rounding.
     */
    private static final double DELTA = 0.00000001d;

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 15d, "kg", 15000d, "g" });
        parameters.add(new Object[] { 100d, "g", 0.1d, "kg" });
        parameters.add(new Object[] { 0.01d, "kg", 10000d, "mg" });

        return parameters;
    }

    /**
     * The entity which performs unit conversions.
     */
    private MeasurementSystem measurementSystem;

    /**
     * The original measurment unit (as String).
     */
    private String origin;

    /**
     * The targeted measurement unit (as String).
     */
    private String destination;

    /**
     * The initial value in the original measurement unit.
     */
    private double initialValue;

    /**
     * The expected value in the targeted measurement unit.
     */
    private double expectedValue;

    /**
     * Constructs this test object.
     *
     * @param initialValue
     *        the initial value in the original measurement unit
     * @param origin
     *        the original measurment unit
     * @param expectedValue
     *        the expected value in the targeted measurement unit
     * @param destination
     *        the targeted measurement unit
     */
    public ValidConversionTest(double initialValue, String origin, double expectedValue, String destination) {

        this.initialValue = initialValue;
        this.origin = origin;
        this.expectedValue = expectedValue;
        this.destination = destination;
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        measurementSystem = new MeasurementSystemImpl();
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        measurementSystem = null;
    }

    /**
     * Performs the actual conversion and checks if result equals the expected
     * result.
     */
    @Test
    public void testConversion() {

        StringBuilder message = new StringBuilder();
        TextHelper.append2StringBuilder(message, "original value := ", initialValue, " ", origin,
                                        "; expected value := ", expectedValue, " ", destination);

        MeasurementUnit omu = measurementSystem.getUnit(origin);

        double result = omu.convert(initialValue, destination).doubleValue();
        TextHelper.append2StringBuilder(message, "; actual result := ", result, " ", destination);

        assertEquals(message.toString(), expectedValue, result, DELTA);
    }

}
