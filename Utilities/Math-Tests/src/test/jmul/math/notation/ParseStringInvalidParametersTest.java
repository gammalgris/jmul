/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package test.jmul.math.notation;


import java.util.ArrayList;
import java.util.Collection;

import jmul.checks.exceptions.EmptyStringParameterException;
import jmul.checks.exceptions.NullParameterException;

import jmul.math.notation.NotationHelper;
import jmul.math.notation.PrecisionTypes;
import static jmul.math.notation.PrecisionTypes.SIGNED_FLOATING_POINT_32_BIT;
import static jmul.math.notation.PrecisionTypes.SIGNED_INTEGER_32_BIT;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests check if numerical values are parsed correctly from string. The
 * utility methods are invoked with invalid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ParseStringInvalidParametersTest {

    /**
     * The specified numerical precision.
     */
    private PrecisionTypes precision;

    /**
     * A number string.
     */
    private String string;

    /**
     * The expected exception type.
     */
    private Class exceptionType;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param aString
     * @param anExceptionType
     */
    public ParseStringInvalidParametersTest(PrecisionTypes aPrecision, String aString, Class anExceptionType) {

        super();

        precision = aPrecision;
        string = aString;
        exceptionType = anExceptionType;
    }

    /**
     * Tests translating a string to a numerical value.
     */
    @Test
    public void testParseString() {

        try {

            NotationHelper.parseString(precision, string);

            fail("An exception is expected but none was thrown!");

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();

            assertEquals(exceptionType, actualExceptionType);
        }
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, "0", NullParameterException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, null, NullParameterException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "", EmptyStringParameterException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "Hello", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1.", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1,", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1.", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1,", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1.", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1,", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1'000", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1'000", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1'000", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1,01E", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1,01E", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1,01E", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1.01E", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1.01E", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1.01E", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1,23457E+", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1,23457E+", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1,23457E+", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1.23457E+", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1.23457E+", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1.23457E+", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1,23457E-", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1,23457E-", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1,23457E-", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1.23457E-", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1.23457E-", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1.23457E-", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1.234.100", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1.234.100", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1.234.100", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "1,234,100", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "+1,234,100", IllegalArgumentException.class });
        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, "-1,234,100", IllegalArgumentException.class });

        parameters.add(new Object[] { SIGNED_INTEGER_32_BIT, String.valueOf(1L + Integer.MAX_VALUE),
                                      NumberFormatException.class });
        parameters.add(new Object[] { SIGNED_FLOATING_POINT_32_BIT, String.valueOf(1.1D * Float.MAX_VALUE),
                                      NumberFormatException.class });

        return parameters;
    }

}
