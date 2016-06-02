/*
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

package test.jmul.helper;


import static jmul.misc.checks.ParameterCheckHelper.checkClass;

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
     * @param anException
     */
    public static void failTest(Class anExpectedExceptionClass, Throwable theActualException) {

        checkClass(anExpectedExceptionClass);

        if (theActualException == null) {

            fail("An exception was expected but none was thrown!");

        } else {

            fail("expected exception: " + anExpectedExceptionClass.getClass() + "; actual exception: " +
                 theActualException.getClass());
        }
    }

}