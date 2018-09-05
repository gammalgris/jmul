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
 * An implementation of an invocation result.
 *
 * @author Kristian Kutin
 */
public class InvocationResultImpl implements InvocationResult {

    /**
     * The exit value of the invoked command.
     */
    private final int exitValue;

    /**
     * The standard output of the invoked command.
     */
    private final String standardOutput;

    /**
     * The error output of the invoked command.
     */
    private final String errorOutput;

    /**
     * Creates a new invocation result according to the specified parameters.
     *
     * @param anExitValue
     *        an exit value
     * @param theStandardOutput
     *        the standard outout
     * @param theErrorOutput
     *        the error output
     */
    public InvocationResultImpl(int anExitValue, String theStandardOutput, String theErrorOutput) {

        super();

        exitValue = anExitValue;
        standardOutput = theStandardOutput;
        errorOutput = theErrorOutput;
    }

    /**
     * Returns the exit value of the invoked command.
     *
     * @return the exit value
     */
    @Override
    public int getExitValue() {

        return exitValue;
    }

    /**
     * Returns the standard output of the invoked command.
     *
     * @return the standard output
     */
    @Override
    public String getStandardOutput() {

        return standardOutput;
    }

    /**
     * Returns the error output of the invoked command.
     *
     * @return the error output
     */
    @Override
    public String getErrorOutput() {

        return errorOutput;
    }

    /**
     * Checks the exit value for a successful execution.
     *
     * @return <code>true</code> if the command was successfully executed,
     *         else <code>false</code>
     */
    @Override
    public boolean wasSuccessful() {

        return exitValue == 0;
    }

    /**
     * Checks the exit value for a failed execution.
     *
     * @return <code>true</code> if the command execution failed,
     *         else <code>false</code>
     */
    @Override
    public boolean hasFailed() {

        return !wasSuccessful();
    }

}
