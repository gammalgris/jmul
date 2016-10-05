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

package jmul.math.formula.parser.patterns;


import java.util.HashSet;
import java.util.Set;

import jmul.math.formula.parser.tokens.TokenType;


/**
 * An implementation of a token pattern. This pattern covers a term which is
 * put within parenthesis. The pattern will as well match opening and closing
 * parenthesis.
 *
 * @author Kristian Kutin
 */
public class TermTokenPatternImpl extends TokenPatternImpl implements TokenPattern {

    /**
     * The class member contains the first character of a term.
     */
    private static final char OPENING_PARENTHESIS = '(';

    /**
     * The class member contains the last character of a term.
     */
    private static final char CLOSING_PARENTHESIS = ')';

    /**
     * The default constructor.
     */
    public TermTokenPatternImpl() {

        super(null, getClassifcation());
    }

    /**
     * The method returns the pattern which identifies the token. The pattern
     * is a regular expression.
     *
     * @return a pattern
     */
    @Override
    public String getTokenPattern() {

        String message = "Terms aren't identified with a regular expresion!";
        throw new UnsupportedOperationException(message);
    }

    /**
     * The method checks if a string matches this kind of token.
     *
     * @param aString
     *        a string
     *
     * @return true, if a string matches this kind of token, else false
     */
    @Override
    public boolean isToken(String aString) {

        int open = 0;
        int close = 0;
        int length = aString.length();

        char first = aString.charAt(0);
        char last = aString.charAt(length - 1);

        if ((first == OPENING_PARENTHESIS) && (last == CLOSING_PARENTHESIS)) {

            for (int a = 0; a < length; a++) {


                char c = aString.charAt(a);
                if (c == OPENING_PARENTHESIS) {

                    open++;

                } else if (c == CLOSING_PARENTHESIS) {

                    close++;
                }
            }

            if (open == close) {
                return true;
            }
        }

        return false;
    }

    /**
     * The method checks if a substring matches this kind of token.
     *
     * @param aString
     *        a string
     * @param startIndex
     *        the strating index of the substring
     * @param endIndex
     *        the
     *
     * @return true, if a string matches this kind of token, else false
     */
    @Override
    public boolean isToken(String aString, int startIndex, int endIndex) {

        return isToken(aString.substring(startIndex, endIndex));
    }

    /**
     * The method creates a classification for terms.
     *
     * @return a classification
     */
    private static Set<TokenType> getClassifcation() {

        Set<TokenType> classification = new HashSet<TokenType>();
        classification.add(TokenType.OPERAND);
        classification.add(TokenType.TERM);

        return classification;
    }

}
