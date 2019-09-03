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

package test.jmul.network.ip;


import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.network.ip.CheckIP;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains several tests for the CheckIP utility class.
 *
 * @deprecated This test needs to be reworked in order to pass the IP directly as string.
 *
 * @author Kristian Kutin
 */
@Deprecated
@UnitTest
@RunWith(Parameterized.class)
public class CheckInvalidIPTest {

    /**
     * Returns a matrix of IPs and expected results.
     *
     * @return a matrix of IPs and  expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        //parameters.add(new Object[] { "1.0.0.255", IOException.class });

        return parameters;
    }

    /**
     * The URL which is to be checked.
     */
    private final InetAddress inetAddress;

    /**
     * The expected exception.
     */
    private final Class expectedExceptionType;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aHostName
     *        a host name or IP address
     * @param anExpectedExceptionType
     *        the expected exception type
     *
     * @throws UnknownHostException
     *         is thrown if the specified host or IP address is unknown
     */
    public CheckInvalidIPTest(String aHostName, Class anExpectedExceptionType) throws UnknownHostException {

        inetAddress = InetAddress.getByName(aHostName);
        expectedExceptionType = anExpectedExceptionType;
    }

    /**
     * Checks if a specified URL throws the expected exception.
     */
    @Test
    public void testCheckIP() {

        try {

            CheckIP.checkIP(inetAddress);

        } catch (Exception e) {

            assertTrue(expectedExceptionType.isAssignableFrom(e.getClass()));
            return;
        }

        String message = "An exception (" + expectedExceptionType + ") was expected but none was thrown!";
        fail(message);
    }

}
