/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package jmul.math.formula.parser.tokens;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jmul.math.formula.parser.patterns.TokenPattern;

import static jmul.string.Constants.SPACE;
import jmul.string.TextHelper;


/**
 * A utility class for parsing a string and splitting it into tokens.<br>
 * <br>
 * <i>Note:<br>
 * The methods found in this utility class were moved here from the token parser implementation
 * for better testability.</i>
 *
 * @author Kristian Kutin
 */
public final class TokenParserHelper {

    /**
     * The default constructor.
     */
    private TokenParserHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * The method will split a string into a sequence of tokens. Due to
     * ambigous tokens the method may identify more than one sequence. The
     * caller will have to examine each returned sequence and determine which
     * sequence is the most plausible one.
     *
     * @param aString
     *        a string to be split
     * @param somePatterns
     *        all patterns which are applied on the specified string
     *
     * @return several sequences of tokens
     */
    public static List<TokenSequence> splitString(String aString, Set<TokenPattern> somePatterns) {

        List<TokenSequence> allSequences = new ArrayList<>();

        String string = TextHelper.removeSubstring(aString, SPACE);

        if (hasNext(string)) {

            Map<Integer, Token> next = getNextToken(string, somePatterns);

            for (Map.Entry<Integer, Token> entry : next.entrySet()) {

                Token token = entry.getValue();

                int length = token.getToken().length();
                String rest = string.substring(length);

                List<TokenSequence> subSequences = splitString(rest, somePatterns);

                if (subSequences.isEmpty()) {

                    TokenSequence subSequence = new TokenSequence();
                    subSequence.add(0, token);
                    allSequences.add(subSequence);

                } else {

                    for (TokenSequence subSequence : subSequences) {

                        subSequence.add(0, token);
                        allSequences.add(subSequence);
                    }
                }
            }
        }

        return allSequences;
    }

    /**
     * The method checks if a string may contain more tokens.
     *
     * @param aString
     *        a string to be examined
     *
     * @return <code>true</code> if the string may contain more tokens, else <code>false</code>
     */
    public static boolean hasNext(String aString) {

        return aString.length() > 0;
    }

    /**
     * This method will try to identify the next token according to the known
     * token patterns. More than one token might be identified. The tokens might
     * also vary in length. The method returns all possible tokens and orders
     * them according to their lengths.
     *
     * @param aString
     *        a string a string to be examined
     * @param somePatterns
     *        all patterns which are applied on the specified string
     *
     * @return matching tokens of varying lengths
     */
    public static Map<Integer, Token> getNextToken(String aString, Set<TokenPattern> somePatterns) {

        Map<Integer, Token> identifiedTokens = new HashMap<>();

        int length = aString.length();
        for (int a = 1; a <= length; a++) {

            String substring = aString.substring(0, a);
            Token token = identifyToken(substring, somePatterns);

            if (token != null) {

                identifiedTokens.put(a, token);
            }
        }

        return identifiedTokens;
    }

    /**
     * The method will try to identify the patterns that will exactly match this
     * string (from head to tail). If more than one pattern is recognized the
     * information will be packed into the token.
     *
     * @param aString
     *        a string a string to be examined
     * @param somePatterns
     *        all patterns which are applied on the specified string
     *
     * @return a token exactly matching the string
     */
    public static Token identifyToken(String aString, Set<TokenPattern> somePatterns) {

        Collection<TokenPattern> matchingPatterns = new ArrayList<>();

        for (TokenPattern pattern : somePatterns) {

            boolean matches = pattern.isToken(aString);

            if (matches) {

                matchingPatterns.add(pattern);
            }
        }

        if (matchingPatterns.isEmpty()) {

            return TokenFactory.newUndefinedToken(aString);

        } else {

            return TokenFactory.newToken(aString, matchingPatterns);
        }
    }

}
