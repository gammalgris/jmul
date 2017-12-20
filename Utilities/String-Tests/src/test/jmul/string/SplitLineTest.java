/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

import java.util.List;

import jmul.string.TextHelper;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the method {@link jmul.string.TextHelper#nextSeparatorIndex}.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SplitLineTest {

    /**
     * An input string.
     */
    private String string;

    /**
     * A separator.
     */
    private String separator;

    /**
     * The expected index of identified quotes.
     */
    private String[] expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param aString
     * @param aSeparator
     * @param anExpectedResult
     */
    public SplitLineTest(String aString, String aSeparator, String[] anExpectedResult) {

        string = aString;
        separator = aSeparator;
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
     * The actual test concatenates all string components and compares the result
     * to the expected string.
     */
    @Test
    public void testStringConcatenator() {

        List<String> actualResult = TextHelper.splitLine(string, separator);

        assertEquals(expectedResult.length, actualResult.size());

        for (int a = 0; a < expectedResult.length; a++) {

            assertEquals(expectedResult[a], actualResult.get(a));
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

        parameters.add(new Object[] { "A;B;C;D", ";", new String[] { "A", "B", "C", "D" } });
        parameters.add(new Object[] { "ABCD", ";", new String[] { "ABCD" } });
        parameters.add(new Object[] { "ABCD;", ";", new String[] { "ABCD", "" } });
        parameters.add(new Object[] { "", ";", new String[] { "" } });

        parameters.add(new Object[] { "A;;B;;C;;D", ";;", new String[] { "A", "B", "C", "D" } });
        parameters.add(new Object[] { "ABCD", ";;", new String[] { "ABCD" } });
        parameters.add(new Object[] { "ABCD;;", ";;", new String[] { "ABCD", "" } });

        return parameters;
    }

}
