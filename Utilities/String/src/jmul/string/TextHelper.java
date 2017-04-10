/*
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

package jmul.string;


import java.util.ArrayList;
import java.util.List;

import static jmul.string.Constants.APOSTROPHE;
import static jmul.string.Constants.CARRIAGE_RETURN;
import static jmul.string.Constants.EMPTY_STRING;
import static jmul.string.Constants.LINE_FEED;
import static jmul.string.Constants.QUOTATION_MARK;
import static jmul.string.Constants.SPACE;


/**
 * A utility class for strings and texts.
 *
 * @author Kristian Kutin
 */
public final class TextHelper {

    /**
     * A numeric value indicating that there was no occurrence of a specific
     * string within a string.
     */
    private static final int NOT_FOUND = -1;

    /**
     * The default constructor.
     */
    private TextHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Splits the specified line into substrings according to the specified
     * separator.
     *
     * @param aLine
     * @param aSeparator
     *
     * @return all substrings
     */
    public static List<String> splitLine(String aLine, String aSeparator) {

        List<String> result = new ArrayList<String>();

        if (aLine.isEmpty()) {

            result.add(aLine);
            return result;
        }

        int index = nextSeparatorIndex(aLine, aSeparator);
        int length = aLine.length();

        String left;
        String right;

        if (index == NOT_FOUND) {

            left = aLine;
            right = EMPTY_STRING;

            result.add(left);

        } else {

            left = aLine.substring(0, index);
            right = aLine.substring(Math.min(length, index + aSeparator.length()));

            result.add(left);
            result.addAll(splitLine(right, aSeparator));
        }

        return result;
    }

    /**
     * Looks for the next occurrence of the specified separator within the
     * specified string and returns the starting index of the next occurrence.
     *
     * @param aString
     * @param aSeparator
     *
     * @return an index or -1 if no such separator was found
     */
    public static int nextSeparatorIndex(String aString, String aSeparator) {

        boolean isQuote = false;
        int length = aString.length();
        int separatorLength = aSeparator.length();

        int index = NOT_FOUND;

        for (int a = 0; a < aString.length(); a++) {

            String s = aString.substring(a, Math.min(a + separatorLength, length));

            if (startsWithQuotes(s)) {

                isQuote = !isQuote;
                continue;
            }

            if (s.startsWith(aSeparator)) {

                index = a;
                break;
            }
        }

        if (isQuote) {

            String message = "A quoted section within this string isn't closed properly (\"" + aString + "\")!";
            throw new QuoteNotClosedException(message);
        }

        return index;
    }

    /**
     * Checks if the specified string starts with a quote.
     *
     * @param aString
     *
     * @return <code>true</code> if the string starts qith a quote, else
     *         <code>false</code>
     */
    public static boolean startsWithQuotes(String aString) {

        return aString.startsWith(QUOTATION_MARK) || aString.startsWith(APOSTROPHE);
    }

    /**
     * Removes all leading and trailing spaces and all line breaks (unix style and windows
     * style).
     *
     * @param aString
     *
     * @return a normalized string
     */
    public static String normalizeString(String aString) {

        String tmp = aString.replaceAll(CARRIAGE_RETURN, LINE_FEED);
        String substrings[] = tmp.split(LINE_FEED);

        StringBuilder buffer = new StringBuilder();
        for (int a = 0; a < substrings.length; a++) {

            String s = substrings[a].trim();

            if (s.isEmpty()) {

                continue;
            }

            if (a > 0) {

                buffer.append(SPACE);
            }

            buffer.append(s);
        }

        return buffer.toString();
    }

}
