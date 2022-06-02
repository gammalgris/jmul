/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package jmul.document.markdown.content.link;


import jmul.document.markdown.content.node.ContentNodeImpl;


/**
 * A base implementation for all link classes.
 *
 * @author Kristian Kutin
 */
abstract class LinkBase extends ContentNodeImpl implements Link {

    /**
     * A path.
     */
    private String path;

    /**
     * The default constructor.
     */
    LinkBase() {

        super();
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aPath
     *        a string representing a path or URL
     */
    LinkBase(CharSequence aPath) {

        this();

        setPath(aPath);
    }

    /**
     * Sets the path or URL.
     *
     * @param aPath
     *        a string representing a path or URL
     */
    @Override
    public void setPath(CharSequence aPath) {

        path = aPath.toString();
    }

    /**
     * Returns the underlying path or URL.
     *
     * @return a path
     */
    @Override
    public String getPath() {

        return path;
    }

}
