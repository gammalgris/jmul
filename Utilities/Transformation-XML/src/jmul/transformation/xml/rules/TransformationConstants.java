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

package jmul.transformation.xml.rules;


/**
 * This class contains some constants which are required by various
 * transformation rules.
 *
 * @author Kristian Kutin
 */
public final class TransformationConstants {

    /**
     * The name of a prerequisite.
     */
    public static final String OBJECT_CACHE = "object cache";

    /**
     * The name of a prerequisite.
     */
    public static final String XML_DOCUMENT = "xml document";

    /**
     * The name of a prerequisite.
     */
    public static final String ROOT_ELEMENT = "root element";

    /**
     * The name of a prerequisite.
     */
    public static final String DECLARED_ELEMENT_TYPE = "declared element type";

    /**
     * The name of a prerequisite.
     */
    public static final String DECLARED_KEY_TYPE = "declared key type";

    /**
     * The name of a prerequisite.
     */
    public static final String DECLARED_VALUE_TYPE = "declared value type";

    /**
     * A date format string.
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";

    /**
     * The default constructor.
     */
    private TransformationConstants() {

        throw new UnsupportedOperationException();
    }

}
