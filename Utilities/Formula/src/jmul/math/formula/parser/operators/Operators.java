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

package jmul.math.formula.parser.operators;


import jmul.math.formula.parser.tokens.Token;
import jmul.math.formula.parser.tokens.TokenTrait;
import jmul.math.formula.parser.tokens.TokenTraits;


/**
 * This enumeration contains all supported operators.
 *
 * @author Kristian Kutin
 */
public enum Operators implements Operator {


    PLUS(TokenTraits.PLUS, 0),
    MINUS(TokenTraits.MINUS, 1),
    MULTIPLICATION(TokenTraits.MULTIPLICATION, 2),
    DIVISION(TokenTraits.DIVISION, 3),
    MODULO(TokenTraits.MODULO, 4),
    DICE_OPERATOR(TokenTraits.DICE_OPERATOR, 5), ;


    /**
     * The token trait associated with this operator.
     */
    private final TokenTrait tokenTrait;

    /**
     * The precedence of this operator.
     */
    private final int precedence;

    /**
     * Creates a new enumeration element according to the specified parameters.
     *
     * @param aTokenTrait
     *        a trait for this enumeration element
     * @param aPrecedence
     *        the order of precedence for this enumeration element (i.e. the higher the number
     *        the higher the precedence)
     */
    private Operators(TokenTrait aTokenTrait, int aPrecedence) {

        tokenTrait = aTokenTrait;
        precedence = aPrecedence;
    }

    /**
     * Returns the token trait associated with this operator.
     *
     * @return a token trait
     */
    @Override
    public TokenTrait getTokenTrait() {

        return tokenTrait;
    }

    /**
     * Returns a precedence number for the operator. The higher the precedence
     * number the higher the priority.
     *
     * @return a precedence number
     */
    @Override
    public int getPrecedence() {

        return precedence;
    }

    /**
     * Returns a operator which matches the specified token.
     *
     * @param aToken
     *        a token representing an operator
     *
     * @return an operator
     */
    public static Operator getOperator(Token aToken) {

        for (Operator operator : Operators.values()) {

            TokenTrait trait = operator.getTokenTrait();

            if (aToken.hasTrait(trait)) {

                return operator;
            }
        }

        String message = String.format("No matching operator could be identified (%s)!", aToken);
        throw new IllegalArgumentException(message);
    }

}
