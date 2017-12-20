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

package jmul.web.page;


/**
 * This class represents a data structure that contains the path and content of a
 * published web page.
 *
 * @author Kristian Kutin
 */
public class PublishedPage {

    /**
     * The path under which the file is published within the web server.
     */
    private final String path;

    /**
     * The actual page content.
     */
    private final byte[] content;

    /**
     * Creates a new instance of a published web page.
     *
     * @param aPath
     *        the path of the published file
     * @param someContent
     *        the file content
     */
    public PublishedPage(String aPath, byte[] someContent) {

        path = aPath;
        content = someContent;
    }

    /**
     * Returns the path of the web page.
     *
     * @return a path
     */
    public String getPath() {

        return path;
    }

    /**
     * Returns the content of a web page.
     *
     * @return the page content
     */
    public byte[] getContent() {

        return content;
    }

}
