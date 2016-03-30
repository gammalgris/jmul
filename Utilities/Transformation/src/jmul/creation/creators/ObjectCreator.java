/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.creation.creators;


/**
 * This interface describes a strategy pattern for instantiating and
 * initializing objects.
 *
 * @author Kristian Kutin
 */
public interface ObjectCreator {

    /**
     * Instantiates and initializes an object according to the specified
     * informations.
     *
     * @param anInitialValue
     *        a string which contains an initial value
     * @param aPattern
     *        a pattern which tells how to parse the string which contains the
     *        inital value
     *
     * @return a new object
     */
    Object createObject(String anInitialValue, String aPattern);

}
