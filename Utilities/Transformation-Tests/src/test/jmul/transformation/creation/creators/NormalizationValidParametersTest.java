/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

package test.jmul.transformation.creation.creators;


import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.UnitTest;

import jmul.transformation.creation.creators.NormalizationHelper;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the normalization of real number string.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NormalizationValidParametersTest {

    /**
     * The input which is to be normalized.
     */
    private final String input;

    /**
     * The expected normalized output.
     */
    private final String expectedOutput;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param input
     * @param expectedOutput
     */
    public NormalizationValidParametersTest(String input, String expectedOutput) {

        super();

        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    /**
     * Tests the actual normalization and verifies the result.
     */
    @Test
    public void testNormalization() {

        String actualOutput = NormalizationHelper.normalizeRealNumber(input);
        String message = String.format("Normalise \"%s\".", input);
        assertEquals(message, expectedOutput, actualOutput);
    }

    /**
     * Returns a matrix of test data.
     *
     * @return a matrix of test data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "1", "1" });
        parameters.add(new Object[] { "1.0", "1.0" });
        parameters.add(new Object[] { "1,0", "1.0" });

        parameters.add(new Object[] { "-1", "-1" });
        parameters.add(new Object[] { "-1.0", "-1.0" });
        parameters.add(new Object[] { "-1,0", "-1.0" });

        parameters.add(new Object[] { "1000", "1000" });
        parameters.add(new Object[] { "1000.0", "1000.0" });
        parameters.add(new Object[] { "1000,0", "1000.0" });

        parameters.add(new Object[] { "-1000", "-1000" });
        parameters.add(new Object[] { "-1000.0", "-1000.0" });
        parameters.add(new Object[] { "-1000,0", "-1000.0" });

        return parameters;
    }

}
