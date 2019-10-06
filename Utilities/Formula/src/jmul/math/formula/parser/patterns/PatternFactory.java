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

package jmul.math.formula.parser.patterns;


import java.util.HashSet;
import java.util.Set;

import jmul.math.formula.operations.Operator;
import jmul.math.formula.parser.tokens.TokenType;


/**
 * A factory class for parser components.
 *
 * @author Kristian Kutin
 */
public final class PatternFactory {

    /**
     * The default constructor.
     */
    private PatternFactory() {
    }

    /**
     * Returns a new token pattern according to the specified parameters.
     *
     * @param aString
     *        a string containing the pattern
     * @param types
     *        a classification for token of this kind
     *
     * @return a token pattern
     */
    public static TokenPattern newTokenPattern(String aString, TokenType... types) {

        Set<TokenType> classification = new HashSet<>();
        for (TokenType type : types) {

            classification.add(type);
        }

        return new TokenPatternImpl(aString, classification);
    }

    /**
     * Returns a new token pattern according to the specified parameters.
     *
     * @param anOperator
     *        the operator representing the token
     *
     * @return a token pattern
     */
    public static TokenPattern newTokenPattern(Operator anOperator) {

        return new OperatorTokenPatternImpl(anOperator);
    }

    /**
     * Returns a new token pattern according.
     *
     * @return a token pattern
     */
    public static TokenPattern newTermTokenPattern() {

        return new TermTokenPatternImpl();
    }

}
