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

package test.jmul.string;


import static jmul.string.Constants.NEW_LINE;
import static jmul.string.Constants.TABULATOR;
import jmul.string.IndentationHelper;
import jmul.string.TextHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests to check the utility methods from
 * {@link jmul.string.IndentationHelper}.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class IdentationHelperTest {

    /**
     * Tests indenting a string (from 0 to 100 tabulator characters).
     */
    @Test
    public void testIndentationValidParameters() {

        String string = "test";

        for (int a = 0; a <= 100; a++) {

            String expectedString = string;
            for (int b = 0; b < a; b++) {

                expectedString = TABULATOR + expectedString;
            }

            String result = IndentationHelper.indent(string, a);

            String message =
                TextHelper.concatenateStrings("The indentation was not correctly applied (identation level=", a, ")!");
            assertEquals(message, expectedString, result);
        }
    }

    /**
     * Tests indenting a string (from 0 up to 100 tabulator characters) and
     * addint a new line character as well.
     */
    @Test
    public void testIndentationValidParameters2() {

        String string = "test";

        for (int a = 0; a <= 100; a++) {

            String expectedString = string;
            for (int b = 0; b < a; b++) {

                expectedString = TABULATOR + expectedString;
            }
            expectedString = expectedString + NEW_LINE;

            String result = IndentationHelper.indent(string, a, true);

            String message =
                TextHelper.concatenateStrings("The indentation was not correctly applied (identation level=", a, ")!");
            assertEquals(message, expectedString, result);
        }
    }

    /**
     * Tests indenting a string with invalid parameters (<code>null</code> string).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIndentationInvalidParameters() {

        String string = null;

        IndentationHelper.indent(string, 1);
    }

    /**
     * Tests indenting a string with invalid parameters (<code>null</code> string).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIndentationInvalidParameters2() {

        String string = null;

        IndentationHelper.indent(string, 1, true);
    }

    /**
     * Tests indenting a string with invalid parameters (negative number).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIndentationInvalidParameters3() {

        String string = "test";

        IndentationHelper.indent(string, -1);
    }

    /**
     * Tests indenting a string with invalid parameters (negative number).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIndentationInvalidParameters4() {

        String string = "test";

        IndentationHelper.indent(string, -1, true);
    }

    /**
     * Tests appending a new line character.
     */
    @Test
    public void testAppendNewLineValidParameters() {

        String string = "test";
        String expectedString = string + NEW_LINE;

        String result = IndentationHelper.appendNewLine(string);

        assertEquals(expectedString, result);
    }

    /**
     * Tests appending new line characters (from 0 up to 100 new line
     * characters).
     */
    @Test
    public void testAppendNewLineValidParameters2() {

        String string = "test";

        for (int a = 0; a <= 100; a++) {

            String expectedString = string;
            for (int b = 0; b < a; b++) {

                expectedString = expectedString + NEW_LINE;
            }

            String result = IndentationHelper.appendNewLine(string, a);

            String message =
                TextHelper.concatenateStrings("The number of new line characters was not correctly applied (new lines=",
                                              a, ")!");
            assertEquals(message, expectedString, result);
        }
    }

    /**
     * Tests appending a new line character with invalid parameters (<code>null</code>
     * string).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAppendNewLineInvalidParameters() {

        String string = null;

        IndentationHelper.appendNewLine(string);
    }

    /**
     * Tests appending a new line character with invalid parameters (<code>null</code>
     * string).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAppendNewLineInvalidParameters2() {

        String string = null;

        IndentationHelper.appendNewLine(string, 1);
    }

    /**
     * Tests appending a new line character with invalid parameters (negative
     * number).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAppendNewLineInvalidParameters3() {

        String string = "test";

        IndentationHelper.appendNewLine(string, -1);
    }

}
