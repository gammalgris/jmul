/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package jmul.reflection.classes;


/**
 * This interface describes an entity which identifies a class or interface.
 *
 * @author Kristian Kutin
 */
interface ClassIdentifier {

    /**
     * The method returns the classname.
     *
     * @return a classname
     */
    String getClassname();

    /**
     * The method determines if this class is located on the default classpath.
     *
     * @return true, if this class is located on the default classpath, else
     *         false
     */
    boolean isLocatedOnDefaultClasspath();

    /**
     * The method determines if this class is located on an alternate classpath.
     *
     * @return true, if this class is located on an alternate classpath, else
     *         false
     */
    boolean isLocatedOnAlternateClasspath();

    /**
     * The method returns the alternate classpath where this class is located.
     *
     * @return an alternative classpath or <code>null</code>
     */
    String getAlternateClasspath();

}
