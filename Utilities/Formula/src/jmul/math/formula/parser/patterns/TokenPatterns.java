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

package jmul.math.formula.parser.patterns;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static jmul.math.formula.parser.patterns.GroupNames.REST;
import static jmul.math.formula.parser.patterns.GroupNames.TOKEN;
import jmul.math.formula.parser.tokens.TokenTrait;
import jmul.math.formula.parser.tokens.TokenTraits;
import jmul.math.formula.parser.tokens.TokenTraitsHelper;


/**
 * This enumeration contains several token patterns (i.e. regular
 * expressions).
 *
 * @author Kristian Kutin
 */
public enum TokenPatterns implements TokenPattern {


    VARIABLE(String.format("^(?<%s>[a-ce-zA-CE-Z][a-zA-Z0-9]*|[a-zA-Z]{2}[a-zA-Z0-9]*)(?<%s>.*)$", TOKEN, REST),
             TokenTraits.OPERAND, TokenTraits.VARIABLE),
    NUMBER(String.format("^(?<%s>[1-9][0-9]*)(?<%s>.*)$", TOKEN, REST), TokenTraits.OPERAND, TokenTraits.NUMBER),

    PLUS(String.format("^(?<%s>[+])(?<%s>.*)$", TOKEN, REST), TokenTraits.OPERATOR, TokenTraits.PLUS),
    MINUS(String.format("^(?<%s>[-])(?<%s>.*)$", TOKEN, REST), TokenTraits.OPERATOR, TokenTraits.MINUS),
    MULIPLICATION(String.format("^(?<%s>[*])(?<%s>.*)$", TOKEN, REST), TokenTraits.OPERATOR,
                  TokenTraits.MULTIPLICATION),
    DIVISION(String.format("^(?<%s>[\\/])(?<%s>.*)$", TOKEN, REST), TokenTraits.OPERATOR, TokenTraits.DIVISION),
    MODULO(String.format("^(?<%s>[%%])(?<%s>.*)$", TOKEN, REST), TokenTraits.OPERATOR, TokenTraits.MODULO),
    DICE_OPERATOR(String.format("^(?<%s>[dD])(?<%s>[1-9][0-9]*.*)$", TOKEN, REST), TokenTraits.OPERATOR,
                  TokenTraits.DICE_OPERATOR),

    OPENING_PARENTHESIS(String.format("^(?<%s>[(])(?<%s>.*)$", TOKEN, REST), TokenTraits.OPENING_PARENTHESIS),
    CLOSING_PARENTHESIS(String.format("^(?<%s>[)])(?<%s>.*)$", TOKEN, REST), TokenTraits.CLOSING_PARENTHESIS), ;


    /**
     * Contains all traits associated with this token.
     */
    private final List<TokenTrait> traits;

    /**
     * Contains a regular expression.
     */
    private final String pattern;

    /**
     * Creates a new enumeration element according to the specified parameters.
     *
     * @param aPattern
     *        a token pattern (i.e. regular expression)
     */
    private TokenPatterns(String aPattern, TokenTrait... someTraits) {

        traits = Collections.unmodifiableList(Arrays.asList(TokenTraitsHelper.validate(someTraits)));
        pattern = aPattern;
    }

    /**
     * Returns the length of the string which contains a regular expression.
     *
     * @return a string length
     */
    @Override
    public int length() {

        return pattern.length();
    }

    /**
     * Returns the character at the specified index.
     *
     * @param index
     *        an index, i.e. a number greater or equal to zero and lesser than
     *        the string length
     *
     * @return a character
     */
    @Override
    public char charAt(int index) {

        return pattern.charAt(index);
    }

    /**
     * Returns a substring according to the specified parameters.
     *
     * @param start
     *        a start index, i.e. a number greater than zero and lesser than the
     *        end index
     * @param end
     *        an end index, i.e. a number greater than the start index and lesser
     *        than the string length
     *
     * @return a substring
     */
    @Override
    public CharSequence subSequence(int start, int end) {

        return pattern.subSequence(start, end);
    }

    /**
     * Returns all traits which are attributed to the entity.
     *
     * @return a list of traits
     */
    @Override
    public List<TokenTrait> getTraits() {

        return traits;
    }

    /**
     * Checks if the entity has the specified trait.
     *
     * @param aTrait
     *        a trait
     *
     * @return <code>true</code> if the entity has the specifiedtrait, else <code>false</code>
     */
    @Override
    public boolean hasTrait(TokenTrait aTrait) {

        return traits.contains(aTrait);
    }

    /**
     * Returns a string representation of this enumeration element.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return pattern;
    }

}
