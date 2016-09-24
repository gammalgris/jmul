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

package jmul.math.formula.parser.tokens;


import jmul.math.formula.parser.patterns.TokenPattern;


/**
 * This interface describes an exoression parser. The parser will take a string
 * and split it into tokens. To identify the tokens a set of token patterns is
 * used. Ambiguities will have to be resolved by the parser.
 *
 * @author Kristian Kutin
 */
public interface TokenParser {

    /**
     * The method will add a token pattern.
     *
     * @param aTokenPattern
     *        a token pattern
     */
    void addTokenPattern(TokenPattern aTokenPattern);

    /**
     * The method will parse an expression and split it into tokens. The parser
     * might not resolve all ambiguities.
     *
     * @param anExpression
     *        an expression
     *
     * @return a parsed expression
     */
    TokenSequence parseExpression(String anExpression);

}
