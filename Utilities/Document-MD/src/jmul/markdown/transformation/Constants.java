/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package jmul.markdown.transformation;


import jmul.transformation.TransformationPath;


/**
 * This class contains constants required for the transformation of
 * markdown documents.
 *
 * @author Kristian Kutin
 */
public final class Constants {

    /**
     * A transformation path.
     */
    public static final TransformationPath OBJECT_TO_MARKDOWN;

    /*
     * The static initializer.
     */
    static {

        OBJECT_TO_MARKDOWN = new TransformationPath("Object", "Markdown");
    }

    /**
     * The default constructor.
     */
    private Constants() {

        throw new UnsupportedOperationException();
    }

}
