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

import jmul.string.TextHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the string concatenation operations of the class
 * {@link jmul.string.TextHelper#append2StringBuilder(StringBuilder, Object[])}
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class Append2StringBuilderInvalidParametersTest {

    /**
     * The expected exception type.
     */
    private Class expectedExceptionType;

    /**
     * A modifiable string buffer.
     */
    private StringBuilder buffer;

    /**
     * All strings that have to be concatenated.
     */
    private Object[] objects;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anExpectedExceptionType
     *        the expected exception type
     * @param aBuffer
     *        a string buffer
     * @param someObjects
     *        an array of objects
     */
    public Append2StringBuilderInvalidParametersTest(Class anExpectedExceptionType, StringBuilder aBuffer,
                                                     Object... someObjects) {

        expectedExceptionType = anExpectedExceptionType;
        buffer = aBuffer;
        objects = someObjects;
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { IllegalArgumentException.class, null, new Object[] { new Integer(1) } });
        parameters.add(new Object[] { IllegalArgumentException.class, new StringBuilder(), null });

        return parameters;
    }

    /**
     * Tests the concatenation of several strings.
     */
    @Test
    public void testConcatenation() {

        try {

            TextHelper.append2StringBuilder(buffer, objects);
            fail("An exception was expected (" + expectedExceptionType + ") but non was thrown!");

        } catch (Exception e) {

            assertTrue(expectedExceptionType.isAssignableFrom(e.getClass()));
        }
    }

}
