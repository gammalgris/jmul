/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package test.jmul.network.http;


import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.network.http.CheckURL;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains several tests for the CheckURL utility class.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class CheckInvalidURLTest {

    /**
     * Returns a matrix of URLs and expected result codes.
     *
     * @return a matrix of URLs and  expected result codes
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "http://localhost", ConnectException.class });
        parameters.add(new Object[] { "http://127.0.0.1", ConnectException.class });
        parameters.add(new Object[] { "http://www.ssp iegel.dde/", UnknownHostException.class });

        return parameters;
    }

    /**
     * The URL which is to be checked.
     */
    private final URL url;

    /**
     * The expected exception type.
     */
    private final Class expectedExceptionType;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aURLString
     *        a string containing a URL
     * @param anExpectedExceptionType
     *        the expected exception type
     *
     * @throws MalformedURLException
     *         is thrown if the specified URL is malformed
     */
    public CheckInvalidURLTest(String aURLString, Class anExpectedExceptionType) throws MalformedURLException {

        url = new URL(aURLString);
        expectedExceptionType = anExpectedExceptionType;
    }

    /**
     * Checks if a specified URL throws the expected exception.
     */
    @Test
    public void testCheckURL() {

        try {

            CheckURL.checkURL(url);

        } catch (Exception e) {

            assertTrue(expectedExceptionType.isAssignableFrom(e.getClass()));
            return;
        }

        String message = "An exception (" + expectedExceptionType + ") was expected but none was thrown!";
        fail(message);
    }

}
