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

package jmul.string;


import jmul.checks.ParameterCheckHelper;

import static jmul.string.Constants.NEW_LINE;
import static jmul.string.Constants.TABULATOR;


/**
 * A utility class for identation
 *
 * @author Kristian Kutin
 */
public final class IndentationHelper {

    /**
     * The default constructor.
     */
    private IndentationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Indents the specified string, according to the specified indentation level.<br>
     * <br>
     * <i>Notes:<br>
     * The new line character varies on different operating systems.</i>
     *
     * @param string
     *        a string
     * @param indentationLevel
     *        the indentation level (number of preceding tabulators)
     * @param newLine
     *        <code>true</code> if the string should be terminated with a new line
     *        character, else <code>false</code>
     *
     * @return an indented string
     */
    public static String indent(String string, int indentationLevel, boolean newLine) {

        ParameterCheckHelper.checkObjectParameter(string);
        ParameterCheckHelper.checkPositiveIntegerParameter(indentationLevel);


        StringBuilder result = new StringBuilder(string);

        for (int a = 0; a < indentationLevel; a++) {

            result.insert(0, TABULATOR);
        }

        if (newLine) {

            result.append(NEW_LINE);
        }

        return result.toString();
    }

    /**
     * Indents the specified string, according to the specified indentation level.
     *
     * @param string
     *        a string
     * @param indentationLevel
     *        the indentation level (number of preceding tabulators)
     *
     * @return a formatted string
     */
    public static String indent(String string, int indentationLevel) {

        return indent(string, indentationLevel, false);
    }

    /**
     * Appends a line feed character to the specified string.
     *
     * @param string
     *        a string
     *
     * @return a string with appended line feed character
     */
    public static String appendNewLine(String string) {

        return appendNewLine(string, 1);
    }

    /**
     * Appends the specified number of line feed characters to the specified string.<br>
     * <br>
     * <i>Notes:<br>
     * The new line character varies on different operating systems.</i>
     *
     * @param string
     *        a string
     * @param newLines
     *        the number of line feeds
     *
     * @return a string with appended line feed characters
     */
    public static String appendNewLine(String string, int newLines) {

        ParameterCheckHelper.checkObjectParameter(string);
        ParameterCheckHelper.checkPositiveIntegerParameter(newLines);


        StringBuilder result = new StringBuilder(string);

        for (int a = 0; a < newLines; a++) {

            result.append(NEW_LINE);
        }

        return result.toString();
    }

}
