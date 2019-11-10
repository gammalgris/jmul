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

package jmul.math.formula.parser.tokens;


import java.util.Collection;

import jmul.math.formula.parser.patterns.TokenPattern;


/**
 * The interface describes a token. A TokenPattern is used to identify a token.
 * This class will provide the token string and some additional informations.
 * <br>
 * Implementations should be able to handle ambiguities to some extent, e.g.
 * different operators with the same symbols. An operator symbol that is a
 * substring of another operator's symbol must be handled by the parser as it
 * requires examining a different sequence of tokens.
 *
 * @author Kristian Kutin
 */
public interface Token {

    /**
     * The method returns the token.
     *
     * @return a token
     */
    String getToken();

    /**
     * The method returns all patterns that match this token.
     *
     * @return some patterns
     */
    Collection<TokenPattern> getMatchingPatterns();

    /**
     * The method removes a pattern for this token.
     *
     * @param aPattern
     *        a pattern
     */
    void removePattern(TokenPattern aPattern);

    /**
     * The method removes all patterns, except this pattern.
     *
     * @param aPattern
     *        a pattern
     */
    void retainPattern(TokenPattern aPattern);

    /**
     * The method checks whether this kind of token belongs to a specific
     * category.
     *
     * @param aType
     *        a category
     *
     * @return <code>true</code>, if the token belongs to the specified category, else <code>false</code>
     */
    boolean isOfType(TokenType aType);

    /**
     * In certain cases a string might match several token patterns. The token
     * will then be labeled as 'ambigous'.
     *
     * @return <code>true</code>, if this token is ambigous, else <code>false</code>
     */
    boolean isAmbigous();

    /**
     * In certain cases a string might not match any token pattern. The token
     * will then be labeled as 'undefined'.
     *
     * @return <code>true</code>, if the token is undefined, else <code>false</code>
     */
    boolean isUndefined();

}
