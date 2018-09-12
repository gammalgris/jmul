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

package jmul.web;


import java.io.File;

import java.util.ResourceBundle;

import jmul.network.http.ResponseCode;

import static jmul.string.Constants.COMMA;


/**
 * A utility class for reading the web server configuration.
 *
 * @author Kristian Kutin
 */
class ConfigurationReader {

    /**
     * A property key.
     */
    private static final String SOCKET_PORT_KEY = "socket.port";

    /**
     * A property key.
     */
    private static final String SOCKET_BACKLOG_KEY = "socket.backlog";

    /**
     * A property key.
     */
    private static final String SOCKET_SHUTDOWN_TIME_KEY = "socket.shutdown-time";

    /**
     * A property key.
     */
    private static final String CONTENT_BASE_DIRECTORY_KEY = "content.base-directory";

    /**
     * A property key.
     */
    private static final String CONTENT_CONTENT_TYPES_KEY = "content.content-types";

    /**
     * A property key (optional).
     */
    private static final String CONTENT_MAIN_PAGE_KEY = "content.main-page";

    /**
     * A property key (optional).
     */
    private static final String SPECIAL_RESPONSE_PREFIX_KEY = "content.special-response.";

    /**
     * The name of a resource bundle.
     */
    private final String bundleName;

    /**
     * Creates a configuration reader according to the specified parameter.
     *
     * @param aBundleName
     */
    ConfigurationReader(String aBundleName) {

        bundleName = aBundleName;
    }

    /**
     * Returns the resource bundle which contains the configuration for this web server.
     *
     * @return a resource bundle
     */
    private ResourceBundle getBundle() {

        return ResourceBundle.getBundle(bundleName);
    }

    /**
     * Returns the string value for the specified property key.
     *
     * @param aKey
     *
     * @return a string value
     */
    private String getString(String aKey) {

        ResourceBundle bundle = getBundle();

        return bundle.getString(aKey);
    }

    /**
     * Returns the integer value for the specified property key.
     *
     * @param aKey
     *
     * @return an integer value
     */
    private int getInteger(String aKey) {

        String s = getString(aKey);
        return Integer.parseInt(s);
    }

    /**
     * Returns the port under which the web server will reply.
     *
     * @return a port
     */
    public int getPort() {

        return getInteger(SOCKET_PORT_KEY);
    }

    /**
     * Returns the backlog size which determines how many requests the web server will
     * accept.
     *
     * @return the backlog size
     */
    public int getBacklogSize() {

        return getInteger(SOCKET_BACKLOG_KEY);
    }

    /**
     * Returns the shutdown time the web server is epxetced to wait before closing
     * all remaining TCP connections.
     *
     * @return a shutdown time
     */
    public int getShutdownTime() {

        return getInteger(SOCKET_SHUTDOWN_TIME_KEY);
    }

    /**
     * Returns the base directory of the web content.
     *
     * @return a base directoy
     */
    public File getBaseDirectory() {

        String s = getString(CONTENT_BASE_DIRECTORY_KEY);
        return new File(s);
    }

    /**
     * Returns the content types which are to be scanned within the web content.
     *
     * @return content types (i.e. file suffixes)
     */
    public String[] getContentTypes() {

        String s = getString(CONTENT_CONTENT_TYPES_KEY);
        s = s.trim();

        return s.split(COMMA);
    }

    /**
     * Checks if a main page has been specified.
     *
     * @return <code>true</code> if a main page has been specified,
     *         else <code>false</code>
     */
    public boolean existsMainPage() {

        return getBundle().containsKey(CONTENT_MAIN_PAGE_KEY);
    }

    /**
     * Returns the specified main page.
     *
     * @return a relative main page path
     */
    public String getMainPage() {

        return getString(CONTENT_MAIN_PAGE_KEY);
    }

    /**
     * Builds a property key according to the specified parameters.
     *
     * @param aResponseCode
     *
     * @return a property key
     */
    private static String buildSpecialResponseContentKey(ResponseCode aResponseCode) {

        return SPECIAL_RESPONSE_PREFIX_KEY + aResponseCode.getValue();
    }

    /**
     * Checks if special response content for the specified response code
     * exists.
     *
     * @param aResponseCode
     *
     * @return <code>true</code> if special response content exists,
     *         else <code>false</code>
     */
    public boolean existSpecialResponseContent(ResponseCode aResponseCode) {

        String key = buildSpecialResponseContentKey(aResponseCode);
        return getBundle().containsKey(key);
    }

    /**
     * Returns the pages (i.e. their paths) that are supposed to return the
     * specified status code.
     *
     * @param aResponseCode
     *
     * @return a list of paths
     */
    public String[] getSpecialResponseContent(ResponseCode aResponseCode) {

        String key = buildSpecialResponseContentKey(aResponseCode);
        String s = getString(key);
        s = s.trim();

        return s.split(COMMA);
    }

}
