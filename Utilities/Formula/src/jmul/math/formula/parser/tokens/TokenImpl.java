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

package jmul.math.formula.parser.tokens;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jmul.math.formula.parser.patterns.TokenPattern;


/**
 * An implementation for a token.
 *
 * @author Kristian Kutin
 */
public class TokenImpl implements Token {

    /**
     * The class member contains the token string.
     */
    private final String token;

    /**
     * The class member contains all matching patterns.
     */
    private final Set<TokenPattern> matchingPatterns;

    /**
     * The default constructor.
     *
     * @param aToken
     *        a token string
     * @param someMatchingPatterns
     *        all matching patterns
     */
    public TokenImpl(String aToken, Collection<TokenPattern> someMatchingPatterns) {

        super();

        token = aToken;

        matchingPatterns = new HashSet<>();

        if (someMatchingPatterns != null) {

            matchingPatterns.addAll(someMatchingPatterns);
        }
    }

    /**
     * The method returns the token.
     *
     * @return a token
     */
    @Override
    public String getToken() {

        return token;
    }

    /**
     * The method returns all patterns that match this token.
     *
     * @return some patterns
     */
    @Override
    public Collection<TokenPattern> getMatchingPatterns() {

        return matchingPatterns;
    }

    /**
     * The method removes a pattern for this token.
     *
     * @param aPattern
     *        a pattern
     */
    @Override
    public void removePattern(TokenPattern aPattern) {

        matchingPatterns.remove(aPattern);
    }

    /**
     * The method removes all patterns, except this pattern.
     *
     * @param aPattern
     *        a pattern
     */
    @Override
    public void retainPattern(TokenPattern aPattern) {

        Collection<TokenPattern> retainables = new ArrayList<>();
        retainables.add(aPattern);
        matchingPatterns.retainAll(retainables);
    }

    /**
     * The method checks whether this kind of token belongs to a specific
     * category.
     *
     * @param aType
     *        a category
     *
     * @return <code>true</code>, if the token belongs to the specified category, else <code>false</code>
     */
    @Override
    public boolean isOfType(TokenType aType) {

        boolean matches = true;

        for (TokenPattern pattern : matchingPatterns) {

            matches = pattern.isOfType(aType) && matches;
        }

        return matches;
    }

    /**
     * In certain cases a string might match several token patterns. The token
     * will then be labeled as 'ambigous'.
     *
     * @return <code>true</code>, if this token is ambigous, else <code>false</code>
     */
    @Override
    public boolean isAmbigous() {

        return matchingPatterns.size() > 1;
    }

    /**
     * In certain cases a string might not match any token pattern. The token
     * will then be labeled as 'undefined'.
     *
     * @return <code>true</code>, if the token is undefined, else <code>false</code>
     */
    @Override
    public boolean isUndefined() {

        return matchingPatterns.isEmpty();
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getToken();
    }

}
