/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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
package test.jmul.string;


import java.util.ArrayList;
import java.util.Collection;

import jmul.string.TextHelper;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests string normalization.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class StringNormalizationTest {

    /**
     * An input string.
     */
    private String inputString;

    /**
     * The expected result.
     */
    private String expectedResult;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param anInputString
     * @param anExpectedResult
     */
    public StringNormalizationTest(String anInputString, String anExpectedResult) {

        inputString = anInputString;
        expectedResult = anExpectedResult;
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {
    }

    /**
     * The actual test.
     */
    @Test
    public void testNomralization() {

        String actualResult = TextHelper.normalizeString(inputString);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "Hello World!", "Hello World!" });
        parameters.add(new Object[] { " Hello World!", "Hello World!" });
        parameters.add(new Object[] { "Hello World! ", "Hello World!" });
        parameters.add(new Object[] { " Hello \n World! ", "Hello World!" });
        parameters.add(new Object[] { " Hello \r World! ", "Hello World!" });
        parameters.add(new Object[] { " Hello \r\n World! ", "Hello World!" });

        return parameters;
    }

}
