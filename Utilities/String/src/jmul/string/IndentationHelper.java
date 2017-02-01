/*
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
     * Indents the specified string, according to the specified indentation level.
     *
     * @param string
     *        a string
     * @param indentationLevel
     *        the indentation level (number of preceding tabulators)
     * @param lineFeed
     *        <code>true</code> if the string should be terminated with a line feed
     *        character, else <code>false</code>
     *
     * @return an indented string
     */
    public static String format(String string, int indentationLevel, boolean lineFeed) {

        String result = string;

        for (int a = 0; a < indentationLevel; a++) {

            result = TABULATOR + result;
        }

        if (lineFeed) {

            result = result + NEW_LINE;
        }

        return result;
    }

    /**
     * Indents the specified string, according to the specified indentation level.
     *
     * @param string
     *        a string
     * @param indentationLevel
     *        the indentation level (number of preceding tabulators)
     */
    public static String format(String string, int indentationLevel) {

        return format(string, indentationLevel, false);
    }

    /**
     * Appends a line feed character to the specified string.
     *
     * @param string
     *        a string
     *
     * @return a string with appended line feed character
     */
    public static String appendLineFeed(String string) {

        return string + NEW_LINE;
    }

    /**
     * Appends the specified number of line feed characters to the specified string.
     *
     * @param string
     *        a string
     * @param lineFeeds
     *        the number of line feeds
     *
     * @return a string with appended line feed characters
     */
    public static String appendLineFeed(String string, int lineFeeds) {

        String result = string;

        for (int a = 0; a < lineFeeds; a++) {

            result = result + NEW_LINE;
        }

        return result;
    }

}
