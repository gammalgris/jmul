/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.transformation.message.rules;


/**
 * A base implementation for message creators.
 *
 * @author Kristian Kutin
 */
abstract class MessageCreatorBase implements MessageCreator {

    /**
     * The object type for which messages can be created.
     */
    private final Class parentClass;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aParentClass
     */
    protected MessageCreatorBase(Class aParentClass) {

        super();

        parentClass = aParentClass;
    }

    /**
     * Returns the object type for which messages can be generated.
     *
     * @return an object type
     */
    @Override
    public Class getParentClass() {

        return parentClass;
    }

    /**
     * Checks if this creator can create messages for the specified
     * object.
     *
     * @param anObject
     *
     * @return <code>true</code> if this creator can handle the specified
     *         object, else <code>false</code>
     */
    @Override
    public boolean isApplicable(Object anObject) {

        if (anObject == null) {

            return false;
        }

        Class actualClass = anObject.getClass();

        return getParentClass().isAssignableFrom(actualClass);
    }

}
