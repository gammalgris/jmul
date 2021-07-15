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
public class NormalizeXmlStringValidParametersTest {

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
    public NormalizeXmlStringValidParametersTest(String anInput, String anExpectedResult) {

        super();

        input = anInput;
        expectedResult = anExpectedResult;
    }

    /**
     * Tests the normalization and checks the results.
     */
    @Test
    public void testNormalizeXmlString() {

        String actualResult = TextNormalizationHelper.toNormalizedXmlString(input);

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
        parameters.add(new Object[] { "a\"b", "a&quot;b" });
        parameters.add(new Object[] { "a'b", "a&apos;b" });
        parameters.add(new Object[] { "B\u00e4umb\u00f6rd\u00fcre", "B&#228;umb&#246;rd&#252;re" });
        parameters.add(new Object[] { "\u00c4", "&#196;" });
        parameters.add(new Object[] { "\u00e4", "&#228;" });
        parameters.add(new Object[] { "\u00d6", "&#214;" });
        parameters.add(new Object[] { "\u00f6", "&#246;" });
        parameters.add(new Object[] { "\u00dc", "&#220;" });
        parameters.add(new Object[] { "\u00fc", "&#252;" });
        parameters.add(new Object[] { "\u00df", "&#223;" });
        parameters.add(new Object[] { "<", "&lt;" });
        parameters.add(new Object[] { ">", "&gt;" });
        parameters.add(new Object[] { "--", "&#45;&#45;" });
        parameters.add(new Object[] { "-", "-" });
        parameters.add(new Object[] { "Hallo\r\nWelt!", "Hallo&#xD;&#xA;Welt!" });
        parameters.add(new Object[] { "Hallo\rWelt!", "Hallo&#xD;Welt!" });
        parameters.add(new Object[] { "Hallo\nWelt!", "Hallo&#xA;Welt!" });

        return parameters;
    }

}
