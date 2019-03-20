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

package test.jmul.math.notation;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.notation.NotationHelper;
import jmul.math.notation.NotationProperties;
import jmul.math.notation.NotationTypes;
import static jmul.math.notation.NotationTypes.FLOATING_POINT;
import static jmul.math.notation.NotationTypes.INTEGER;
import static jmul.math.notation.NotationTypes.SCIENTIFIC_NOTATION;
import jmul.math.notation.Signs;
import static jmul.math.notation.Signs.NEGATIVE;
import static jmul.math.notation.Signs.POSITIVE;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests to check the properties of number strings.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NotationPropertiesValidParametersTest {

    /**
     * A number string.
     */
    private String string;

    /**
     * The expected decimal separator.
     */
    private String decimalSeparator;

    /**
     * The expected notation type.
     */
    private NotationTypes notationType;

    /**
     * The expected sign.
     */
    private Signs sign;

    /**
     * The expected exponent sign.
     */
    private Signs exponentSign;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param aString
     * @param aDecimalSeparator
     * @param aNotationType
     * @param aSign
     * @param anExponentSign
     */
    public NotationPropertiesValidParametersTest(String aString, String aDecimalSeparator, NotationTypes aNotationType,
                                                 Signs aSign, Signs anExponentSign) {

        super();

        string = aString;
        decimalSeparator = aDecimalSeparator;
        notationType = aNotationType;
        sign = aSign;
        exponentSign = anExponentSign;
    }

    /**
     * Tests the properties of a number string.
     */
    @Test
    public void testProperties() {

        NotationProperties properties = NotationHelper.checkString(string);

        assertNotNull(properties);
        assertEquals(decimalSeparator, properties.getDecimalSeparator());
        assertEquals(notationType, properties.getNotationType());
        assertEquals(sign, properties.getSign());

        switch (sign) {
        case POSITIVE:
            {

                assertEquals(true, properties.isPositive());
                assertEquals(false, properties.isNegative());

                break;
            }
        case NEGATIVE:
            {

                assertEquals(false, properties.isPositive());
                assertEquals(true, properties.isNegative());

                break;
            }
        default:
            {

                String message = "Unknown sign (" + sign + ")!";
                fail(message);
            }
        }

        if (exponentSign != null) {

            switch (exponentSign) {
            case POSITIVE:
                {

                    assertEquals(true, properties.hasPositiveExponent());
                    assertEquals(false, properties.hasNegativeExponent());

                    break;
                }
            case NEGATIVE:
                {

                    assertEquals(false, properties.hasPositiveExponent());
                    assertEquals(true, properties.hasNegativeExponent());

                    break;
                }
            default:
                {

                    String message = "Unknown sign (" + sign + ")!";
                    fail(message);
                }
            }
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

        parameters.add(new Object[] { "0", "", INTEGER, POSITIVE, null });
        parameters.add(new Object[] { "1", "", INTEGER, POSITIVE, null });
        parameters.add(new Object[] { "-1", "", INTEGER, NEGATIVE, null });
        parameters.add(new Object[] { "1.0", ".", FLOATING_POINT, POSITIVE, null });
        parameters.add(new Object[] { "+1.0", ".", FLOATING_POINT, POSITIVE, null });
        parameters.add(new Object[] { "-1.0", ".", FLOATING_POINT, NEGATIVE, null });
        parameters.add(new Object[] { "1,0", ",", FLOATING_POINT, POSITIVE, null });
        parameters.add(new Object[] { "+1,0", ",", FLOATING_POINT, POSITIVE, null });
        parameters.add(new Object[] { "-1,0", ",", FLOATING_POINT, NEGATIVE, null });
        parameters.add(new Object[] { "1,01E10", ",", SCIENTIFIC_NOTATION, POSITIVE, POSITIVE });
        parameters.add(new Object[] { "+1,01E10", ",", SCIENTIFIC_NOTATION, POSITIVE, POSITIVE });
        parameters.add(new Object[] { "-1,01E10", ",", SCIENTIFIC_NOTATION, NEGATIVE, POSITIVE });
        parameters.add(new Object[] { "1.01E10", ".", SCIENTIFIC_NOTATION, POSITIVE, POSITIVE });
        parameters.add(new Object[] { "+1.01E10", ".", SCIENTIFIC_NOTATION, POSITIVE, POSITIVE });
        parameters.add(new Object[] { "-1.01E10", ".", SCIENTIFIC_NOTATION, NEGATIVE, POSITIVE });
        parameters.add(new Object[] { "1,23457E+11", ",", SCIENTIFIC_NOTATION, POSITIVE, POSITIVE });
        parameters.add(new Object[] { "+1,23457E+11", ",", SCIENTIFIC_NOTATION, POSITIVE, POSITIVE });
        parameters.add(new Object[] { "-1,23457E+11", ",", SCIENTIFIC_NOTATION, NEGATIVE, POSITIVE });
        parameters.add(new Object[] { "1.23457E+11", ".", SCIENTIFIC_NOTATION, POSITIVE, POSITIVE });
        parameters.add(new Object[] { "+1.23457E+11", ".", SCIENTIFIC_NOTATION, POSITIVE, POSITIVE });
        parameters.add(new Object[] { "-1.23457E+11", ".", SCIENTIFIC_NOTATION, NEGATIVE, POSITIVE });
        parameters.add(new Object[] { "1,23457E-11", ",", SCIENTIFIC_NOTATION, POSITIVE, NEGATIVE });
        parameters.add(new Object[] { "+1,23457E-11", ",", SCIENTIFIC_NOTATION, POSITIVE, NEGATIVE });
        parameters.add(new Object[] { "-1,23457E-11", ",", SCIENTIFIC_NOTATION, NEGATIVE, NEGATIVE });
        parameters.add(new Object[] { "1.23457E-11", ".", SCIENTIFIC_NOTATION, POSITIVE, NEGATIVE });
        parameters.add(new Object[] { "+1.23457E-11", ".", SCIENTIFIC_NOTATION, POSITIVE, NEGATIVE });
        parameters.add(new Object[] { "-1.23457E-11", ".", SCIENTIFIC_NOTATION, NEGATIVE, NEGATIVE });

        return parameters;
    }

}
