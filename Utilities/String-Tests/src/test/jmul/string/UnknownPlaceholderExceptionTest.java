/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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


import jmul.string.UnknownPlaceholderException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


/**
 * Tests the instantiation of {@link jmul.string.UnresolvedPlaceholderException}.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class UnknownPlaceholderExceptionTest {

    /**
     * Test the instantiation with a valid parameter.
     */
    @Test
    public void testValidParameter() {

        String expectedMessage = "Error";

        UnknownPlaceholderException exception = new UnknownPlaceholderException(expectedMessage);
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test the instantiation with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidParameter() {

        new UnknownPlaceholderException(null);
    }

    /**
     * Test the instantiation with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidParameter2() {

        new UnknownPlaceholderException("");
    }

    /**
     * Test the instantiation with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidParameter3() {

        new UnknownPlaceholderException(" ");
    }

}
