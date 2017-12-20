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


import java.util.Collection;

import jmul.math.formula.parser.patterns.OperatorTokenPattern;
import jmul.math.formula.parser.patterns.TokenPattern;


/**
 * A factory class for parser components.
 *
 * @author Kristian Kutin
 */
public final class TokenFactory {

    /**
     * The default constructor.
     */
    private TokenFactory() {
    }

    /**
     * The method creates a token.
     *
     * @param aString
     *        a string
     * @param someMatchingPatterns
     *        patterns which match the token
     *
     * @return a token
     */
    public static Token newToken(String aString, Collection<TokenPattern> someMatchingPatterns) {

        boolean matchesOperator = false;
        for (TokenPattern pattern : someMatchingPatterns) {

            matchesOperator = (pattern instanceof OperatorTokenPattern) || matchesOperator;
        }

        if (matchesOperator) {

            return new OperatorTokenImpl(aString, someMatchingPatterns);

        } else {

            return new TokenImpl(aString, someMatchingPatterns);
        }
    }

    /**
     * The method creates a new token parser.
     *
     * @return a token paqrser
     */
    public static TokenParser newTokenParser() {

        return new TokenParserImpl();
    }

}
