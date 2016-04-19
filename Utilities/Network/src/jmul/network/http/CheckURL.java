/*
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

package jmul.network.http;


import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

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
     * @param aResponseCodeValue
     */
    public static void checkURL(String aURLString, int aResponseCodeValue) {

        try {

            URL url = new URL(aURLString);
            checkURL(url, aResponseCodeValue);

        } catch (MalformedURLException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * The method tries to connect to the specified URL and checks if the result matches the specified
     * response code.
     *
     * @param aURL
     * @param aResponseCodeValue
     */
    public static void checkURL(URL aURL, int aResponseCodeValue) {

        ResponseCodes expectedResponseCode = ResponseCodes.getResponseCode(aResponseCodeValue);
        ResponseCodes actualResponseCode = null;

        try {

            actualResponseCode = checkURL(aURL);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        if (!actualResponseCode.equals(expectedResponseCode)) {

            String message =
                "URL: " + aURL + " // response code: actual=" + actualResponseCode.getDescription() + "[" +
                actualResponseCode.getValue() + "] expected=" + expectedResponseCode.getDescription() + "[" +
                expectedResponseCode.getValue() + "]";
            throw new RuntimeException(message);
        }
    }

    /**
     * Checks the specified URL and returns a response code.
     *
     * @param aURLString
     *
     * @return a response code
     *
     * @throws MalformedURLException
     * @throws IOException
     */
    public static ResponseCodes checkURL(String aURLString) throws MalformedURLException, IOException {

        URL url = new URL(aURLString);
        return checkURL(url);
    }

    /**
     * Checks the specified URL and returns a response code.
     *
     * @param aURL
     *
     * @return a response code
     *
     * @throws IOException
     */
    public static ResponseCodes checkURL(URL aURL) throws IOException {

        String actualProtocol = aURL.getProtocol();

        if (HTTP.equalsProtocolName(actualProtocol)) {

            return checkHttp(aURL);

        } else if (HTTPS.equalsProtocolName(actualProtocol)) {

            return checkHttps(aURL);
        }

        String message = "Unknown protocol: \"" + actualProtocol + "\"";
        throw new RuntimeException(message);
    }

    /**
     * Checks the specified URL (<code>http</code>) and returns a response code.
     *
     * @param aURL
     *
     * @return a response code
     *
     * @throws IOException
     */
    private static ResponseCodes checkHttp(URL aURL) throws IOException {

        HttpMethods httpMethod = HttpMethods.HEAD;
        HttpURLConnection connection = null;

        try {

            connection = (HttpURLConnection) aURL.openConnection();
            connection.setRequestMethod(httpMethod.name());
            connection.setInstanceFollowRedirects(false);
            connection.connect();

            int value = connection.getResponseCode();
            ResponseCodes responseCode = ResponseCodes.getResponseCode(value);
            return responseCode;

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
     *
     * @return a response code
     *
     * @throws IOException
     */
    private static ResponseCodes checkHttps(URL aURL) throws IOException {

        HttpMethods httpMethod = HttpMethods.HEAD;
        HttpsURLConnection connection = null;

        try {

            connection = (HttpsURLConnection) aURL.openConnection();
            connection.setRequestMethod(httpMethod.name());
            connection.setInstanceFollowRedirects(false);
            connection.connect();

            int value = connection.getResponseCode();
            ResponseCodes responseCode = ResponseCodes.getResponseCode(value);
            return responseCode;

        } finally {

            if (connection != null) {

                connection.disconnect();
            }
        }
    }

}
