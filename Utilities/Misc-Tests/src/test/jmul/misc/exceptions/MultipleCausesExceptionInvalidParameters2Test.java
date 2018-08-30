/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
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

import jmul.misc.exceptions.MultipleCausesException;

import jmul.test.classification.UnitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains tests to check the instantiation of {@link jmul.misc.exceptions.MultipleCausesException#}
 * with invalid parameters.
 * 
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MultipleCausesExceptionInvalidParameters2Test {

    /**
     * The message of an exception.
     */
    private String message;

    /**
     * The cause of an exception.
     */
    private Throwable[] causes;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aMessage
     * @param someCauses
     */
    public MultipleCausesExceptionInvalidParameters2Test(String aMessage, Throwable... someCauses) {

        message = aMessage;
        causes = someCauses;
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
    @Test(expected = IllegalArgumentException.class)
    public void testInstantiation() {

        new MultipleCausesException(message, causes);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, new Throwable[] { new RuntimeException() } });
        parameters.add(new Object[] { "", new Throwable[] { new RuntimeException() } });
        parameters.add(new Object[] { " ", new Throwable[] { new RuntimeException() } });
        parameters.add(new Object[] { "Error", null });
        parameters.add(new Object[] { "Error", new Throwable[] { } });

        return parameters;
    }

}
