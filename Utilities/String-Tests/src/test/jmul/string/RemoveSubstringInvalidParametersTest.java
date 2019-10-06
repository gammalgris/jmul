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

package test.jmul.string;


import java.util.ArrayList;
import java.util.Collection;

import jmul.checks.exceptions.NullParameterException;

import jmul.string.TextHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests removing substrings with invalid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RemoveSubstringInvalidParametersTest {

    /**
     * The string which is to be processed.
     */
    private String string;

    /**
     * The sbstring which is to be removed.
     */
    private String substring;

    /**
     * The expected exception type.
     */
    private Class expectedExceptionType;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param aString
     * @param aSubstring
     * @param theExpectedExceptionType
     */
    public RemoveSubstringInvalidParametersTest(String aString, String aSubstring, Class theExpectedExceptionType) {

        super();

        string = aString;
        substring = aSubstring;
        expectedExceptionType = theExpectedExceptionType;
    }

    /**
     * Tests the actual string manipulation.
     */
    @Test
    public void testRemoveSubstring() {

        try {

            TextHelper.removeSubstring(string, substring);

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();

            String message =
                "The expected exception type (" + expectedExceptionType +
                ") doesn't match the actual exception type (" + actualExceptionType + ")!";
            assertTrue(message, expectedExceptionType.isAssignableFrom(actualExceptionType));
            return;
        }

        {
            String message = "An exception is expected (" + expectedExceptionType + ") but none was thrown!";
            fail(message);
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

        parameters.add(new Object[] { "Hello World!", null, IllegalArgumentException.class });
        parameters.add(new Object[] { null, " ", NullParameterException.class });

        return parameters;
    }

}
