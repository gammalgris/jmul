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
 * This interface describes the result of an external command invocation.
 *
 * @author Kristian Kutin
 */
public interface InvocationResult {

    /**
     * Returns the exit value of the invoked command.
     *
     * @return the exit value
     */
    int getExitValue();

    /**
     * Returns the standard output of the invoked command.
     *
     * @return the standard output
     */
    String getStandardOutput();

    /**
     * Returns the error output of the invoked command.
     *
     * @return the error output
     */
    String getErrorOutput();

    /**
     * Checks the exit value for a successful execution.
     *
     * @return <code>true</code> if the command was successfully executed,
     *         else <code>false</code>
     */
    boolean wasSuccessful();

    /**
     * Checks the exit value for a failed execution.
     *
     * @return <code>true</code> if the command execution failed,
     *         else <code>false</code>
     */
    boolean hasFailed();

}
