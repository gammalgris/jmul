/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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


/**
 * A utility class.
 *
 * @author Kristian Kutin
 */
public final class Constants {

    /**
     * A separator character.
     */
    public static final String COMMA = ",";

    /**
     * An empty string.
     */
    public static final String EMPTY_STRING = "";

    /**
     * A slash character.
     */
    public static final String SLASH = "/";

    /**
     * A slash character.
     */
    public static final String BACKSLASH = "\\";

    /**
     * The prefix to escape a special character in a regular expression.
     */
    public static final String REGEX_ESCAPE = BACKSLASH;

    /**
     * The file separator which is used under the current operating system (e.g.
     * "/" under Unix, "\" under Windows).
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * The file separator which is used under windows.
     */
    public static final String FILE_SEPARATOR_WINDOWS = BACKSLASH;

    /**
     * The file separator which is used under unix.
     */
    public static final String FILE_SEPARATOR_UNIX = SLASH;

    /**
     * A new line character.
     */
    public static final String NEW_LINE = System.getProperty("line.separator");

    /**
     * A new line character.
     */
    public static final String NEW_LINE_UNIX = "\n";

    /**
     * A new line character.
     */
    public static final String NEW_LINE_WINDOWS = "\r\n";

    /**
     * A separator character.
     */
    public static final String POINT = ".";

    /**
     * A tabulator character.
     */
    public static final String TABULATOR = "\t";

    /**
     * A colon character.
     */
    public static final String COLON = ":";

    /**
     * A semicolon character.
     */
    public static final String SEMICOLON = ";";

    /**
     * The default constructor.
     */
    private Constants() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns a regular expression which describes a file separator.
     *
     * @return a regular expression
     */
    public static String fileSeparator2Regex() {

        if (FILE_SEPARATOR.equals(FILE_SEPARATOR_WINDOWS)) {

            return REGEX_ESCAPE + FILE_SEPARATOR;

        } else {

            return FILE_SEPARATOR;
        }
    }

}
