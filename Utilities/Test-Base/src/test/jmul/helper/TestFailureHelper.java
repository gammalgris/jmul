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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.helper;


import static jmul.checks.ParameterCheckHelper.checkClassParameter;

import static org.junit.Assert.fail;


/**
 * A utility class for test failures.
 *
 * @author Kristian Kutin
 */
public final class TestFailureHelper {

    /**
     * The default constructor.
     */
    private TestFailureHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Fails the test with an apropriate message.
     *
     * @param anExpectedExceptionType
     *        the expected exception type
     * @param theActualException
     *        the actual exception
     */
    public static void failTest(Class anExpectedExceptionType, Throwable theActualException) {

        checkClassParameter(anExpectedExceptionType);

        if (theActualException == null) {

            fail("An exception was expected but none was thrown!");
        }

        Class actualExceptionType = theActualException.getClass();

        failTest(anExpectedExceptionType, actualExceptionType);
    }

    /**
     * Fails the test with an apropriate message.
     *
     * @param anExpectedExceptionType
     *        the expected exception type
     * @param theActualExceptionType
     *        the actual exception type
     */
    public static void failTest(Class anExpectedExceptionType, Class theActualExceptionType) {

        checkClassParameter(anExpectedExceptionType);
        checkClassParameter(theActualExceptionType);

        fail("expected exception: " + anExpectedExceptionType + "; actual exception: " + theActualExceptionType);
    }

}
