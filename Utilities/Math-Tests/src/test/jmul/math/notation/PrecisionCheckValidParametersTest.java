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

import jmul.math.notation.NotationHelper;
import jmul.math.notation.PrecisionTypes;
import static jmul.math.notation.PrecisionTypes.SIGNED_INTEGER_32_BIT;
import static jmul.math.notation.PrecisionTypes.SIGNED_INTEGER_64_BIT;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests to check if the required precision of a number string can be determined
 * correctly.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class PrecisionCheckValidParametersTest {

    /**
     * A number string.
     */
    private String string;

    /**
     * The epxected result.
     */
    private PrecisionTypes expectedPrecision;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param aString
     * @param theExpectedPrecision
     */
    public PrecisionCheckValidParametersTest(String aString, PrecisionTypes theExpectedPrecision) {

        super();

        string = aString;
        expectedPrecision = theExpectedPrecision;
    }

    /**
     * Tests determining the required precision of a number string.
     */
    @Test
    public void testCheckPrecision() {

        PrecisionTypes actualPrecision = NotationHelper.checkPrecision(string);

        assertEquals(expectedPrecision, actualPrecision);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "0", SIGNED_INTEGER_32_BIT });
        parameters.add(new Object[] { "1", SIGNED_INTEGER_32_BIT });
        parameters.add(new Object[] { "-1", SIGNED_INTEGER_32_BIT });
        parameters.add(new Object[] { "2147483647", SIGNED_INTEGER_32_BIT });
        parameters.add(new Object[] { "-2147483648", SIGNED_INTEGER_32_BIT });
        parameters.add(new Object[] { "2147483648", SIGNED_INTEGER_64_BIT });
        parameters.add(new Object[] { "-2147483649", SIGNED_INTEGER_64_BIT });
        parameters.add(new Object[] { "9223372036854775807", SIGNED_INTEGER_64_BIT });
        parameters.add(new Object[] { "-9223372036854775808", SIGNED_INTEGER_64_BIT });

        return parameters;
    }

}
