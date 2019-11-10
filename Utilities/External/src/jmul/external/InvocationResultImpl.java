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
     * A flag indicating that the output is complete.
     */
    private final boolean isCompleteOutput;

    /**
     * Creates a new invocation result according to the specified parameters.
     *
     * @param anExitValue
     *        an exit value
     * @param theStandardOutput
     *        the standard outout
     * @param theErrorOutput
     *        the error output
     * @param aCompleteOutputFlag
     *        a flag indicating that the console output is complete
     */
    public InvocationResultImpl(int anExitValue, String theStandardOutput, String theErrorOutput,
                                boolean aCompleteOutputFlag) {

        super();

        exitValue = anExitValue;
        standardOutput = theStandardOutput;
        errorOutput = theErrorOutput;
        isCompleteOutput = aCompleteOutputFlag;
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

    /**
     * Checks if the command has produced some standard output.
     *
     * @return <code>true</code> if the command has produced some standard output,
     *         else <code>false</code>
     */
    private boolean hasStandardOutput() {

        return (getStandardOutput() != null) && !getStandardOutput().trim().isEmpty();
    }

    /**
     * Checks if the command has produced some error output.
     *
     * @return <code>true</code> if the command has produced some error output,
     *         else <code>false</code>
     */
    private boolean hasErrorOutput() {

        return (getErrorOutput() != null) && !getErrorOutput().trim().isEmpty();
    }

    /**
     * Checks if the command has produced some console output (i.e. standard output and error output).
     *
     * @return <code>true</code> if the command has produced some console output,
     *         else <code>false</code>
     */
    @Override
    public boolean hasOutput() {

        return hasStandardOutput() || hasErrorOutput();
    }

    /**
     * Checks if the command has produced no standard output.
     *
     * @return <code>true</code> if the command has produced no standard output,
     *         else <code>false</code>
     */
    private boolean hasNoStandardOutput() {

        return (getStandardOutput() == null) || ((getStandardOutput() != null) && getStandardOutput().trim().isEmpty());
    }

    /**
     * Checks if the command has produced no error output.
     *
     * @return <code>true</code> if the command has produced no error output,
     *         else <code>false</code>
     */
    private boolean hasNoErrorOutput() {

        return (getErrorOutput() == null) || ((getErrorOutput() != null) && getErrorOutput().trim().isEmpty());
    }

    /**
     * Checks if the command has produced no console output (i.e. no standard output and no error output).
     *
     * @return <code>true</code> if the command has produced no console output,
     *         else <code>false</code>
     */
    @Override
    public boolean hasNoOutput() {

        return hasNoStandardOutput() && hasNoErrorOutput();
    }

    /**
     * Checks if the command has produced incomplete console output (i.e. either the
     * standard output or error output couldn't be captured completely).
     *
     * @return <code>true</code> if the command has produced incomplete console output,
     *         else <code>false</code>
     */
    @Override
    public boolean hasIncompleteOutput() {

        return !isCompleteOutput;
    }

}
