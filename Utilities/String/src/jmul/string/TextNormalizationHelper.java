/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * This utility class contains operations to normalize strings.
 *
 * @author Kristian Kutin
 */
public final class TextNormalizationHelper {

    /**
     * A special fallback character. This is a replacement character for cases when the actual character couldn't
     * be identified.
     */
    private static final String FALLBACK_CHARACTER;

    /**
     * A mapping for special characters.
     */
    private static final Map<String, String> STRING_TO_XML_STRING_CHARACTER_MAPPINGS;

    /**
     * A mapping for special characters.
     */
    private static final Map<String, String> STRING_TO_JAVA_STRING_CHARACTER_MAPPINGS;

    /*
     * The static initializer.
     */
    static {

        FALLBACK_CHARACTER = "\ufffd";


        Map<String, String> tmp;

        tmp = new HashMap<>();

        // characters that need to be escaped
        tmp.put("&", "&amp;");
        tmp.put("<", "&lt;");
        tmp.put(">", "&gt;");
        tmp.put("\"", "&quot;");
        tmp.put("'", "&apos;");

        // language dependent characters that need to be preserved (not complete)
        // german
        tmp.put("\u00c4", "&#196;"); // AE
        tmp.put("\u00e4", "&#228;"); // ae
        tmp.put("\u00d6", "&#214;"); // OE
        tmp.put("\u00f6", "&#246;"); // oe
        tmp.put("\u00dc", "&#220;"); // UE
        tmp.put("\u00fc", "&#252;"); // ue
        tmp.put("\u00df", "&#223;"); // sz

        // combinations of characters that are problematic
        tmp.put("--", "&#45;&#45;");

        // line separators
        tmp.put("\r\n", "&#xD;&#xA;");
        tmp.put("\r", "&#xD;");
        tmp.put("\n", "&#xA;");


        STRING_TO_XML_STRING_CHARACTER_MAPPINGS = Collections.unmodifiableMap(tmp);


        tmp = new HashMap<>();
        tmp.put("\u00c4", "\\u00c4"); // AE
        tmp.put("\u00e4", "\\u00e4"); // ae
        tmp.put("\u00d6", "\\u00d6"); // OE
        tmp.put("\u00f6", "\\u00f6"); // oe
        tmp.put("\u00dc", "\\u00dc"); // UE
        tmp.put("\u00fc", "\\u00fc"); // ue
        tmp.put("\u00df", "\\u00df"); // sz

        STRING_TO_JAVA_STRING_CHARACTER_MAPPINGS = Collections.unmodifiableMap(tmp);
    }

    /**
     * The default constructor.
     */
    private TextNormalizationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * When writing an XML file string values should be normalized, i.e. some
     * characters need to be escaped (e.g. &gt; or &lt;) and some language
     * dependent special character need to be replaced to preserve the actual character
     * (e.g. in german this applies to &#196;, &#214;, &#220;, etc.). When the output file
     * has a lesser encoding (e.g. UTF-16 to UTF-8) there is some loss.<br>
     * Additionally there exist combinations of characters that make the XML file not well formed (e.g. '--').<br>
     * <br>
     * This operation returns a normalized string where some of these issues are resolved.<br>
     * <br>
     * <i>Note:<br>
     * Update the operation as needed when new cases of problematic characters have been
     * identified.<br>
     * The current implementation may not suit a language independent solution. Change the implementation
     * as needed.</i>
     *
     * @param aCharSequence
     *        a string which is not normalized
     *
     * @return a normalized string
     */
    public static String toNormalizedXmlString(CharSequence aCharSequence) {

        return toNormalizedXmlString(aCharSequence, aCharSequence);
    }

    /**
     * This operation recurses into the specified string and replaces certain characters in order to
     * preserve the characters when exporting to an XML file.
     *
     * @param theFullCharSequence
     *        the full string is needed for error messages
     * @param aCharSequence
     *        the string which is normalized
     *
     * @return a normalized string
     */
    private static String toNormalizedXmlString(CharSequence theFullCharSequence, CharSequence aCharSequence) {

        if (aCharSequence == null) {

            return null;
        }

        if (aCharSequence.length() == 0) {

            return String.valueOf(aCharSequence);
        }


        // Check for unrecognized characters. If such a character is found then the string
        // contains an illegal character. At this point it's not possible to restore or map
        // the actual character.
        {
            char c1 = aCharSequence.charAt(0);
            char c2 = FALLBACK_CHARACTER.charAt(0);

            if (c1 == c2) {

                String message =
                    String.format("The specified string (%s) contains at least one unrecognized character (%s)!",
                                  theFullCharSequence, c2);
                throw new IllegalArgumentException(message);
            }
        }


        int length = aCharSequence.length();

        for (String token : STRING_TO_XML_STRING_CHARACTER_MAPPINGS.keySet()) {

            int tokenLength = token.length();

            boolean match = true;
            for (int i = 0; i < tokenLength; i++) {

                char c1 = token.charAt(i);

                if (i >= length) {

                    match = false;
                    break;
                }

                char c2 = aCharSequence.charAt(i);

                match = match && c1 == c2;
            }

            if (!match) {

                continue;
            }

            CharSequence remainder = aCharSequence.subSequence(token.length(), length);
            String replacementToken = STRING_TO_XML_STRING_CHARACTER_MAPPINGS.get(token);

            return replacementToken + toNormalizedXmlString(theFullCharSequence, remainder);
        }


        char first = aCharSequence.charAt(0);
        CharSequence remainder = aCharSequence.subSequence(1, length);

        return first + toNormalizedXmlString(theFullCharSequence, remainder);
    }


    /**
     * When writing a text file string values should be normalized, i.e. some
     * language dependent special character need to be replaced to preserve the actual character
     * (e.g. in german this applies to &#196;, &#214;, &#220;, etc.). When the output file
     * has a lesser encoding (e.g. UTF-16 to UTF-8) there is some loss.<br>
     * <br>
     * This operation returns a normalized string where some of these issues are resolved.<br>
     * <br>
     * <i>Note:<br>
     * Update the operation as needed when new cases of problematic characters have been
     * identified.<br>
     * The current implementation may not suit a language independent solution. Change the implementation
     * as needed.</i>
     *
     * @param aCharSequence
     *        a string which is not normalized
     *
     * @return a normalized string
     */
    public static String toNormalizedJavaString(CharSequence aCharSequence) {

        return toNormalizedJavaString(aCharSequence, aCharSequence);
    }

    /**
     * This operation recurses into the specified string and replaces certain characters in order to
     * preserve the characters when exporting to a text file.
     *
     * @param theFullCharSequence
     *        the full string is needed for error messages
     * @param aCharSequence
     *        the string which is normalized
     *
     * @return a normalized string
     */
    private static String toNormalizedJavaString(CharSequence theFullCharSequence, CharSequence aCharSequence) {

        if (aCharSequence == null) {

            return null;
        }

        if (aCharSequence.length() == 0) {

            return String.valueOf(aCharSequence);
        }


        // Check for unrecognized characters. If such a character is found then the string
        // contains an illegal character. At this point it's not possible to restore or map
        // the actual character.
        {
            char c1 = aCharSequence.charAt(0);
            char c2 = FALLBACK_CHARACTER.charAt(0);

            if (c1 == c2) {

                String message =
                    String.format("The specified string (%s) contains at least one unrecognized character (%s)!",
                                  theFullCharSequence, c2);
                throw new IllegalArgumentException(message);
            }
        }


        int length = aCharSequence.length();

        for (String token : STRING_TO_JAVA_STRING_CHARACTER_MAPPINGS.keySet()) {

            int tokenLength = token.length();

            boolean match = true;
            for (int i = 0; i < tokenLength; i++) {

                char c1 = token.charAt(i);

                if (i >= length) {

                    match = false;
                    break;
                }

                char c2 = aCharSequence.charAt(i);

                match = match && c1 == c2;
            }

            if (!match) {

                continue;
            }

            CharSequence remainder = aCharSequence.subSequence(token.length(), length);
            String replacementToken = STRING_TO_JAVA_STRING_CHARACTER_MAPPINGS.get(token);

            return replacementToken + toNormalizedJavaString(theFullCharSequence, remainder);
        }


        char first = aCharSequence.charAt(0);
        CharSequence remainder = aCharSequence.subSequence(1, length);

        return first + toNormalizedJavaString(theFullCharSequence, remainder);
    }

}
