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

package jmul.external;


/**
 * This Interface describes a command.
 *
 * @author Kristian Kutin
 */
public interface Command {

    /**
     * Returns the command which is to be invoked.
     *
     * @return a command
     */
    String getCommand();

    /**
     * Returns all parameters which were specified.
     *
     * @return all specified parameters
     */
    String[] getParameters();

    /**
     * Returns the command and all parameters as array.
     *
     * @return an array containing the command and all parameters
     */
    String[] toArray();

}
