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

package jmul.transformation;


/**
 * This interface describes an entity which contains a set of transformation
 * parameters.
 *
 * @author Kristian Kutin
 */
public interface TransformationParameters {

    /**
     * Returns informations about the transformation path.
     *
     * @return informations about the transformation path
     */
    TransformationPath getTransformationPath();

    /**
     * Returns the object which is to be transformed.
     *
     * @return the object which is to be transformed
     */
    Object getObject();

    /**
     * Returns the real type of the object which is to be transformed.
     *
     * @return the real type of the object which is to be transformed
     */
    Class getRealType();

    /**
     * Returns the declared type of the object which is to be transformed. In
     * some cases the declared type and the real type are not equal (e.g. if
     * the declared type is an interface type and the true type an
     * implementation of the interface) or the infroamtion is unavailable (e.g.
     * with primitive type like int, boolean, etc.)
     *
     * @return the declared type of the object which is to be transformed
     */
    Class getDeclaredType();

    /**
     * A transformation might require addititional informations or certain
     * prerequisites. This method allows to check if a certain information or
     * prerequisite is available.
     *
     * @param aName
     *        the name of a prerequisite
     *
     * @return <code>true</code> if the specified prerequisite is available,
     *         else <code>false</code>
     */
    boolean containsPrerequisite(CharSequence aName);

    /**
     * A transformation might require addititional informations or certain
     * prerequisites. This method allows access to such an information or
     * prerequisite.
     *
     * @param aName
     *        the name of a prerequisite
     *
     * @return a prerequisite for the transformation
     */
    Object getPrerequisite(CharSequence aName);

    /**
     * The method allows to specify more additional informations or
     * prerequisites.
     *
     * @param aName
     *        the name of a prerequisite
     * @param aPrerequisite
     *        a prerequisite
     */
    void addPrerequisite(CharSequence aName, Object aPrerequisite);

}
