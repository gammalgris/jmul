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

package jmul.network.ip;


import java.io.IOException;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * A utility class which provides methods to perform checks on IP addresses.
 *
 * @author Kristian Kutin
 */
public final class CheckIP {

    /**
     * A second in milliseconds.
     */
    private static final int SECOND = 1000;

    /**
     * A default timeout in milliseconds.
     */
    private static final int DEFAULT_TIMEOUT = 10 * SECOND;

    /**
     * The default constructor.
     */
    private CheckIP() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the specified host is reachable.
     *
     * @param aHostName
     *        a host name or ip address
     *
     * @return <code>true</code> if the specified host is reachable, else
     *         <code>false</code>
     *
     * @throws UnknownHostException
     *         is thrown if the specified host cannot be found
     * @throws IOException
     *         is thrown if an error occurs while trying to contact the specified host
     */
    public static boolean checkIP(String aHostName) throws IOException {

        return checkIP(aHostName, DEFAULT_TIMEOUT);
    }

    /**
     * Checks if the specified host is reachable.
     *
     * @param aHostName
     *        a host name or ip address
     * @param aTimeout
     *        a timeout time
     *
     * @return <code>true</code> if the specified host is reachable, else
     *         <code>false</code>
     *
     * @throws UnknownHostException
     *         is thrown if the specified host cannot be found
     * @throws IOException
     *         is thrown if an error occurs while trying to contact the specified host
     */
    public static boolean checkIP(String aHostName, int aTimeout) throws IOException {

        checkParameter(aHostName);

        return checkIP(InetAddress.getByName(aHostName), aTimeout);
    }

    /**
     * Checks if the specified parameter is valid.
     *
     * @param aHostName
     *        a host name or ip address
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified host name or ip address are invalid
     */
    private static void checkParameter(String aHostName) {

        if (aHostName == null) {

            String message = "No host name has been specified (null)!";
            throw new IllegalArgumentException(message);
        }

        if (aHostName.trim().isEmpty()) {

            String message = "No host name has been specified (empty string)!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks if the specified host is reachable.
     *
     * @param anIPAddress
     *        an ip address
     *
     * @return <code>true</code> if the specified host is reachable, else
     *         <code>false</code>
     *
     * @throws IOException
     *         is thrown if an error occurs while trying to contact the specified host
     */
    public static boolean checkIP(InetAddress anIPAddress) throws IOException {

        return checkIP(anIPAddress, DEFAULT_TIMEOUT);
    }

    /**
     * Checks if the specified host is reachable.
     *
     * @param anIPAddress
     *        an ip address
     * @param aTimeout
     *        a timeout time
     *
     * @return <code>true</code> if the specified host is reachable, else
     *         <code>false</code>
     *
     * @throws IOException
     *         is thrown if an error occurs while trying to contact the specified host
     */
    public static boolean checkIP(InetAddress anIPAddress, int aTimeout) throws IOException {

        checkParameter(anIPAddress);

        return anIPAddress.isReachable(aTimeout);
    }

    /**
     * Checks if the specified parameter is valid.
     *
     * @param anIPAddress
     *        an ip address
     *
     * @throws IllegalArgumentException
     *         is thrown if an error occurs while trying to contact the specified host
     */
    private static void checkParameter(InetAddress anIPAddress) {

        if (anIPAddress == null) {

            String message = "No IP address has been specified (null)!";
            throw new IllegalArgumentException(message);
        }
    }

}
