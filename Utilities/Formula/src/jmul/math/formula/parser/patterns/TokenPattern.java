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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.math.formula.parser.patterns;


import java.util.Set;

import jmul.math.formula.parser.tokens.TokenType;


/**
 * The interface describes a token pattern. This entity helps with identifying
 * substrings withing other strings, especially if it's not possible to use a
 * separator character or sequence to split the string.
 *
 * @author Kristian Kutin
 */
public interface TokenPattern {

    /**
     * The method returns the pattern which identifies the token. The pattern
     * is a regular expression.
     *
     * @return a pattern
     */
    String getTokenPattern();

    /**
     * The method returns the classification for this token.
     *
     * @return a classification
     */
    Set<TokenType> getTokenTypes();

    /**
     * The method checks whether this kind of token belongs to a specific
     * category.
     *
     * @param aType
     *        a category
     *
     * @return true, if the token belongs to the specified category, else false
     */
    boolean isOfType(TokenType aType);

    /**
     * The method checks if a string matches this kind of token.
     *
     * @param aString
     *        a string
     *
     * @return true, if a string matches this kind of token, else false
     */
    boolean isToken(String aString);

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
    boolean isToken(String aString, int startIndex, int endIndex);

}
