/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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

package test.jmul.string;


import java.util.ArrayList;
import java.util.Collection;

import jmul.string.TextNormalizationHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the text normalization helper, i.e. the normalization of an XML string with valid
 * test data.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NormalizeJavaStringValidParametersTest {

    /**
     * The actual input.
     */
    private String input;

    /**
     * The expected result.
     */
    private String expectedResult;

    /**
     * Creates a new test case according to the specified characters.
     *
     * @param anInput
     *        the string which is to be normalized
     * @param anExpectedResult
     *        the expected normalization result
     */
    public NormalizeJavaStringValidParametersTest(String anInput, String anExpectedResult) {

        super();

        input = anInput;
        expectedResult = anExpectedResult;
    }

    /**
     * Tests the normalization and checks the results.
     */
    @Test
    public void testNormalizeXmlString() {

        String actualResult = TextNormalizationHelper.toNormalizedJavaString(input);

        String message =
            String.format("The input (\"%s\") has been transformed (\"%s\") but another result is expected (\"%s\")!",
                          input, actualResult, expectedResult);
        assertEquals(message, expectedResult, actualResult);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, null });
        parameters.add(new Object[] { "", "" });
        parameters.add(new Object[] { "a", "a" });
        parameters.add(new Object[] { "ab", "ab" });
        parameters.add(new Object[] { "a\"b", "a\"b" });
        parameters.add(new Object[] { "a'b", "a'b" });
        parameters.add(new Object[] { "B\u00e4umb\u00f6rd\u00fcre", "B\\u00e4umb\\u00f6rd\\u00fcre" });
        parameters.add(new Object[] { "\u00c4", "\\u00c4" });
        parameters.add(new Object[] { "\u00e4", "\\u00e4" });
        parameters.add(new Object[] { "\u00d6", "\\u00d6" });
        parameters.add(new Object[] { "\u00f6", "\\u00f6" });
        parameters.add(new Object[] { "\u00dc", "\\u00dc" });
        parameters.add(new Object[] { "\u00fc", "\\u00fc" });
        parameters.add(new Object[] { "\u00df", "\\u00df" });
        parameters.add(new Object[] { "<", "<" });
        parameters.add(new Object[] { ">", ">" });
        parameters.add(new Object[] { "--", "--" });
        parameters.add(new Object[] { "-", "-" });
        parameters.add(new Object[] { "Hello\r\nWorld!", "Hello\r\nWorld!" });
        parameters.add(new Object[] { "Hello\rWorld!", "Hello\rWorld!" });
        parameters.add(new Object[] { "Hello\nWorld!", "Hello\nWorld!" });

        return parameters;
    }

}
