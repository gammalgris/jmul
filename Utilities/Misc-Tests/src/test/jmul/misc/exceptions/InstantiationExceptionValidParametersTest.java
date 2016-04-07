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

package test.jmul.misc.exceptions;


import java.util.ArrayList;
import java.util.Collection;

import jmul.misc.exceptions.InstantiationException;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class InstantiationExceptionValidParametersTest {

    /**
     * The message of an exception.
     */
    private String message;

    /**
     * The cause of an exception.
     */
    private Throwable cause;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aMessage
     * @param aCause
     */
    public InstantiationExceptionValidParametersTest(String aMessage, Throwable aCause) {

        message = aMessage;
        cause = aCause;
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
     * Tests the instantiation of an exception with valid parameters.
     */
    @Test
    public void testInstantiation() {

        InstantiationException exception = new InstantiationException(message);
        assertEquals(message, exception.getMessage());
    }

    /**
     * Tests the instantiation of an exception with valid parameters.
     */
    @Test
    public void testInstantiation2() {

        InstantiationException exception = new InstantiationException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "Error", new RuntimeException() });

        return parameters;
    }

}
