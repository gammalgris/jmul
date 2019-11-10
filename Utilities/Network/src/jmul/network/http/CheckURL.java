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

package jmul.network.http;


import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import jmul.network.NetworkException;
import static jmul.network.http.Protocols.HTTP;
import static jmul.network.http.Protocols.HTTPS;


/**
 * A utility class which provides methods to perform checks on URLs.
 *
 * @author Kristian Kutin
 */
public final class CheckURL {

    /**
     * The default constructor.
     */
    private CheckURL() {

        throw new UnsupportedOperationException();
    }

    /**
     * The method tries to connect to the specified URL and checks if the result matches the specified
     * response code.
     *
     * @param aURLString
     *        a string cotnaining a URL
     * @param aResponseCodeValue
     *        the expected response code
     */
    public static void checkURL(String aURLString, int aResponseCodeValue) {

        try {

            URL url = new URL(aURLString);
            checkURL(url, aResponseCodeValue);

        } catch (MalformedURLException e) {

            throw new NetworkException(e);
        }
    }

    /**
     * The method tries to connect to the specified URL and checks if the result matches the specified
     * response code.
     *
     * @param aURL
     *        a url
     * @param aResponseCodeValue
     *        the expected response code
     */
    public static void checkURL(URL aURL, int aResponseCodeValue) {

        ResponseCode expectedResponseCode = ResponseCodes.getResponseCode(aResponseCodeValue);
        ResponseCode actualResponseCode = null;

        try {

            actualResponseCode = checkURL(aURL);

        } catch (IOException e) {

            throw new NetworkException(e);
        }

        if (!actualResponseCode.equals(expectedResponseCode)) {

            String message =
                "URL: " + aURL + " // response code: actual=" + actualResponseCode.getDescription() + "[" +
                actualResponseCode.getValue() + "] expected=" + expectedResponseCode.getDescription() + "[" +
                expectedResponseCode.getValue() + "]";
            throw new NetworkException(message);
        }
    }

    /**
     * Checks the specified URL and returns a response code.
     *
     * @param aURLString
     *        a string containing a URL
     *
     * @return a response code
     *
     * @throws MalformedURLException
     *         is thrown if the URL is invalid
     * @throws IOException
     *         is thrown if an error occurs while calling the URL
     */
    public static ResponseCode checkURL(String aURLString) throws IOException {

        URL url = new URL(aURLString);
        return checkURL(url);
    }

    /**
     * Checks the specified URL and returns a response code.
     *
     * @param aURL
     *        a url
     *
     * @return a response code
     *
     * @throws IOException
     *         is thrown if an error occurs while calling the URL
     */
    public static ResponseCode checkURL(URL aURL) throws IOException {

        String actualProtocol = aURL.getProtocol();

        if (HTTP.equalsProtocolName(actualProtocol)) {

            return checkHttp(aURL);

        } else if (HTTPS.equalsProtocolName(actualProtocol)) {

            return checkHttps(aURL);
        }

        String message = "Unknown protocol: \"" + actualProtocol + "\"";
        throw new NetworkException(message);
    }

    /**
     * Checks the specified URL (<code>http</code>) and returns a response code.
     *
     * @param aURL
     *        a http resource
     *
     * @return a response code
     *
     * @throws IOException
     *         is thrown if an error occurs while calling the http resource
     */
    private static ResponseCode checkHttp(URL aURL) throws IOException {

        HttpMethods httpMethod = HttpMethods.HEAD;
        HttpURLConnection connection = null;

        try {

            connection = (HttpURLConnection) aURL.openConnection();
            connection.setRequestMethod(httpMethod.name());
            connection.setInstanceFollowRedirects(false);
            connection.connect();

            int value = connection.getResponseCode();
            return ResponseCodes.getResponseCode(value);

        } finally {

            if (connection != null) {

                connection.disconnect();
            }
        }
    }

    /**
     * Checks the specified URL (<code>https</code>) and returns a response code.
     *
     * @param aURL
     *        a https resource
     *
     * @return a response code
     *
     * @throws IOException
     *         is thrown if an error occurs while calling the https resource
     */
    private static ResponseCode checkHttps(URL aURL) throws IOException {

        HttpMethods httpMethod = HttpMethods.HEAD;
        HttpsURLConnection connection = null;

        try {

            connection = (HttpsURLConnection) aURL.openConnection();
            connection.setRequestMethod(httpMethod.name());
            connection.setInstanceFollowRedirects(false);
            connection.connect();

            int value = connection.getResponseCode();
            return ResponseCodes.getResponseCode(value);

        } finally {

            if (connection != null) {

                connection.disconnect();
            }
        }
    }

}
