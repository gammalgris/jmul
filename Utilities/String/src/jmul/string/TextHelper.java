/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.string;


import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

import jmul.metainfo.annotations.Modified;

import jmul.checks.ParameterCheckHelper;

import static jmul.string.Constants.APOSTROPHE;
import static jmul.string.Constants.CARRIAGE_RETURN;
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
     * A constant for an empty string.
     */
    private static final String EMPTY_STRING = "";

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
     *        a string
     * @param aSeparator
     *        a separator
     *
     * @return all substrings
     */
    public static List<String> splitLine(String aLine, String aSeparator) {

        List<String> result = new ArrayList<>();

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
     *        a string
     * @param aSeparator
     *        a separator
     *
     * @return an index or -1 if no such separator was found
     */
    public static int nextSeparatorIndex(String aString, String aSeparator) {

        boolean isQuote = false;
        String quote = null;
        int length = aString.length();
        int separatorLength = aSeparator.length();

        int index = NOT_FOUND;

        for (int a = 0; a < aString.length(); a++) {

            String s = aString.substring(a, Math.min(a + separatorLength, length));


            if ((quote == null) && startsWithQuotes(s)) {

                quote = getFirstCharacter(s);
                isQuote = !isQuote;
                continue;

            } else if ((quote != null) && startsWithQuotes(s, quote)) {

                quote = null;
                isQuote = !isQuote;
                continue;
            }

            if (s.startsWith(aSeparator)) {

                if (!isQuote) {

                    index = a;
                    break;
                }
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
     *        a string
     *
     * @return <code>true</code> if the string starts with a quote, else
     *         <code>false</code>
     */
    public static boolean startsWithQuotes(String aString) {

        return aString.startsWith(QUOTATION_MARK) || aString.startsWith(APOSTROPHE);
    }

    /**
     * Checks if the specified string starts with the specified quote character.
     *
     * @param aString
     *        a string
     * @param aQuoteCharacter
     *        a quote character
     *
     * @return <code>true</code> if the string starts with the specified quote
     *         character, else <code>false</code>
     */
    public static boolean startsWithQuotes(String aString, String aQuoteCharacter) {

        return aString.startsWith(aQuoteCharacter);
    }

    /**
     * Returns a string that only contains the first character of the specified string.
     *
     * @param aString
     *        a string
     *
     * @return a string containing only one character
     */
    public static String getFirstCharacter(String aString) {

        return aString.substring(0, 1);
    }

    /**
     * Removes all leading and trailing spaces and all line breaks (unix style and windows
     * style).
     *
     * @param aString
     *        a string
     *
     * @return a normalized string
     */
    public static String normalizeString(String aString) {

        String tmp = aString.replaceAll(CARRIAGE_RETURN, LINE_FEED);
        String[] substrings = tmp.split(LINE_FEED);

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

    /**
     * Concatenates all string representations of all specified objects.
     *
     * @param someObjects
     *        several objects
     *
     * @return a string
     */
    public static String concatenateStrings(Object... someObjects) {

        ParameterCheckHelper.checkObjectArrayParameter(someObjects);

        StringBuilder buffer = new StringBuilder();

        for (Object o : someObjects) {

            buffer.append(String.valueOf(o));
        }

        return String.valueOf(buffer);
    }

    /**
     * Concatenates all string representations of all specified objects.
     *
     * @param aBuffer
     *        the buffer which is modified
     * @param someObjects
     *        several objects
     *
     * @return a concatenated string
     */
    public static StringBuilder append2StringBuilder(@Modified StringBuilder aBuffer, Object... someObjects) {

        ParameterCheckHelper.checkStringBuilderParameter(aBuffer);
        ParameterCheckHelper.checkObjectArrayParameter(someObjects);

        for (Object o : someObjects) {

            aBuffer.append(String.valueOf(o));
        }

        return aBuffer;
    }

    /**
     * Determines the jvm's default charset.
     *
     * @return a charset
     */
    public static Charset getDefaultCharset() {

        return Charset.defaultCharset();
    }

    /**
     * Removes all occurrences of the specified substring.
     *
     * @param aString
     *        a string
     * @param aSubstring
     *        a substring or regular expression to identify a substring
     *
     * @return a cleaned up substring
     */
    public static String removeSubstring(String aString, String aSubstring) {

        ParameterCheckHelper.checkObjectParameter(aString);
        ParameterCheckHelper.checkObjectParameter(aSubstring);

        return aString.replaceAll(aSubstring, EMPTY_STRING);
    }

}
