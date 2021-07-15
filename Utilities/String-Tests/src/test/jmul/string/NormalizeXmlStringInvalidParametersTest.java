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

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the text normalization helper, i.e. the normalization of an XML string with invalid
 * test data.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NormalizeXmlStringInvalidParametersTest {

    /**
     * The actual input.
     */
    private String input;

    /**
     * The expected exception.
     */
    private Class expectedExceptionClass;

    /**
     * Creates a new test case according to the specified characters.
     *
     * @param anInput
     *        the string which is to be normalized
     * @param theExpectedExceptionClass
     *        the exception which is expected
     */
    public NormalizeXmlStringInvalidParametersTest(String anInput, Class theExpectedExceptionClass) {

        super();

        input = anInput;
        expectedExceptionClass = theExpectedExceptionClass;
    }

    /**
     * Tests the normalization and checks the results.
     */
    @Test
    public void testNormalizeXmlString() {

        try {

            TextNormalizationHelper.toNormalizedXmlString(input);

        } catch (Exception e) {

            Class actualExceptionClass = e.getClass();

            if (actualExceptionClass.isAssignableFrom(expectedExceptionClass)) {

                return;

            } else {

                String message =
                    String.format("An exception is epxected (%s) but a different exception was thrown (%s)!",
                                  expectedExceptionClass, actualExceptionClass);
                fail(message);
            }
        }

        String message = String.format("An exception is epxected (%s) but none was thrown!", expectedExceptionClass);
        fail(message);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "Bäumbördüre", IllegalArgumentException.class });
        parameters.add(new Object[] { "Ä", IllegalArgumentException.class });
        parameters.add(new Object[] { "ä", IllegalArgumentException.class });
        parameters.add(new Object[] { "Ö", IllegalArgumentException.class });
        parameters.add(new Object[] { "ö", IllegalArgumentException.class });
        parameters.add(new Object[] { "Ü", IllegalArgumentException.class });
        parameters.add(new Object[] { "ü", IllegalArgumentException.class });
        parameters.add(new Object[] { "ß", IllegalArgumentException.class });

        return parameters;
    }

}
