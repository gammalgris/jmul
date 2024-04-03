/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.web.page;


import jmul.network.http.ResponseCode;
import jmul.network.http.ResponseCodes;


/**
 * A wrapper for a page (i.e. file) that was loaded into memory (i.e. byte array). In case an error
 * happens during loading a page (i.e. file) the wrapper can contain a response code.
 *
 * @author Kristian Kutin
 */
public class PageLoadingResult {

    /**
     * The path under which the file is published within the web server. The file path
     * is provided for information purposes and to help with debugging.
     */
    private final String path;

    /**
     * A response code. Use this response code for answering a web request.
     */
    private ResponseCode responseCode;

    /**
     * An array containing the binary file content. If the page content couldn't be
     * loaded then this field is <code>null</code>
     */
    private byte[] pageContent;

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param path
     *        a file path
     * @param responseCode
     *        a response code to signal that loading the page was not successful
     */
    public PageLoadingResult(String path, ResponseCode responseCode) {

        super();

        if (path == null) {

            throw new IllegalArgumentException("No path (null) was specified!");
        }

        if (responseCode == null) {

            throw new IllegalArgumentException("No response code (null) was specified!");
        }

        if (responseCode == ResponseCodes.RC200) {

            throw new IllegalArgumentException("An illegal response code (200) was specified!");
        }

        this.path = path;
        this.responseCode = responseCode;
        this.pageContent = null;
    }

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param path
     *        a file path
     * @param pageContent
     *        the page (i.e. file) content
     */
    public PageLoadingResult(String path, byte[] pageContent) {

        super();

        if (path == null) {

            throw new IllegalArgumentException("No path (null) was specified!");
        }

        if (pageContent == null) {

            throw new IllegalArgumentException("No page content (null) was specified!");
        }

        this.path = path;
        this.responseCode = ResponseCodes.RC200;
        this.pageContent = pageContent;
    }

    /**
     * Returns the information that the page was successfully loaded.
     *
     * @return <code>true</code> if the page was loaded, else <code>false</code>
     */
    public boolean pageWasLoaded() {

        return (responseCode == ResponseCodes.RC200);
    }

    /**
     * Returns a response code.
     *
     * @return a response code
     */
    public ResponseCode responseCode() {

        return responseCode;
    }

    /**
     * Returns a page content.
     *
     * @return a byte array
     */
    public byte[] pageContent() {

        return pageContent;
    }

    /**
     * Returns the path of the web page.
     *
     * @return a path
     */
    public String path() {

        return path;
    }

}
