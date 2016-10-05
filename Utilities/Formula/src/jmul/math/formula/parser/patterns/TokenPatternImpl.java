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


import java.util.Set;
import java.util.regex.Pattern;

import jmul.math.formula.parser.tokens.TokenType;


/**
 * An implementation of a token pattern.
 *
 * @author Kristian Kutin
 */
public class TokenPatternImpl implements TokenPattern {

    /**
     * The class member contains a pattern to identify the token.
     */
    private final String pattern;

    /**
     * The class member contains a classification for this kind of token.
     */
    private final Set<TokenType> classification;

    /**
     * The default constructor.
     *
     * @param aPattern
     *        a pattern to identify this kind of token
     * @param aClassifcation
     *        a classification for this kind of token
     */
    public TokenPatternImpl(String aPattern, Set<TokenType> aClassifcation) {

        pattern = aPattern;
        classification = aClassifcation;
    }

    /**
     * The method returns the pattern which identifies the token. The pattern
     * is a regular expression.
     *
     * @return a pattern
     */
    @Override
    public String getTokenPattern() {

        return pattern;
    }

    /**
     * The method returns the classification for this token.
     *
     * @return a classification
     */
    @Override
    public Set<TokenType> getTokenTypes() {

        return classification;
    }

    /**
     * The method checks whether this kind of token belongs to a specific
     * category.
     *
     * @param aType
     *        a category
     *
     * @return true, if the token belongs to the specified category, else false
     */
    @Override
    public boolean isOfType(TokenType aType) {

        return classification.contains(aType);
    }

    /**
     * The method checks if a string matches this kind of token.
     *
     * @param aString
     *        a string
     *
     * @return <code>true</code>, if a string matches this kind of token,
     *         else <code>false</code>
     */
    @Override
    public boolean isToken(String aString) {

        return Pattern.matches(getTokenPattern(), aString);
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
     * @return <code>true</code>, if a string matches this kind of token,
     *         else <code>false</code>
     */
    @Override
    public boolean isToken(String aString, int startIndex, int endIndex) {

        return isToken(aString.substring(startIndex, endIndex));
    }

}
