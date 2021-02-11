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

package test.jmul.math.formula.parser.patterns;


import jmul.math.formula.parser.patterns.TokenPattern;
import jmul.math.formula.parser.patterns.TokenPatterns;
import jmul.math.formula.parser.tokens.TokenTraits;

import jmul.test.classification.ConfigurationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This class tests the configuration of token patterns.
 *
 * @author Kristian Kutin
 */
@ConfigurationTest
public class TokenPatternsTest {

    @Test
    public void testEnumerations() {

        {
            String message =
                String.format("An enumeration (%s) has changed. The unit tests have to be updated!",
                              TokenPatterns.class.getName());
            assertEquals(message, 10, TokenPatterns.values().length);
        }

        {
            String message =
                String.format("An enumeration (%s) has changed. The unit tests have to be updated!",
                              TokenTraits.class.getName());
            assertEquals(message, 12, TokenTraits.values().length);
        }
    }

    /**
     * Tests a single enumeration element.
     */
    @Test
    public void testClosingParenthesis() {

        TokenPattern tokenPattern = TokenPatterns.CLOSING_PARENTHESIS;

        assertTrue(tokenPattern.hasTrait(TokenTraits.CLOSING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DICE_OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DIVISION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MODULO));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MINUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MULTIPLICATION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.NUMBER));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPENING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERAND));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.PLUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests a single enumeration element.
     */
    @Test
    public void testDiceOperator() {

        TokenPattern tokenPattern = TokenPatterns.DICE_OPERATOR;

        assertFalse(tokenPattern.hasTrait(TokenTraits.CLOSING_PARENTHESIS));
        assertTrue(tokenPattern.hasTrait(TokenTraits.DICE_OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DIVISION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MODULO));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MINUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MULTIPLICATION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.NUMBER));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPENING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERAND));
        assertTrue(tokenPattern.hasTrait(TokenTraits.OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.PLUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests a single enumeration element.
     */
    @Test
    public void testDivision() {

        TokenPattern tokenPattern = TokenPatterns.DIVISION;

        assertFalse(tokenPattern.hasTrait(TokenTraits.CLOSING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DICE_OPERATOR));
        assertTrue(tokenPattern.hasTrait(TokenTraits.DIVISION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MODULO));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MINUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MULTIPLICATION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.NUMBER));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPENING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERAND));
        assertTrue(tokenPattern.hasTrait(TokenTraits.OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.PLUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests a single enumeration element.
     */
    @Test
    public void testModulo() {

        TokenPattern tokenPattern = TokenPatterns.MODULO;

        assertFalse(tokenPattern.hasTrait(TokenTraits.CLOSING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DICE_OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DIVISION));
        assertTrue(tokenPattern.hasTrait(TokenTraits.MODULO));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MINUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MULTIPLICATION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.NUMBER));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPENING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERAND));
        assertTrue(tokenPattern.hasTrait(TokenTraits.OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.PLUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests a single enumeration element.
     */
    @Test
    public void testMinus() {

        TokenPattern tokenPattern = TokenPatterns.MINUS;

        assertFalse(tokenPattern.hasTrait(TokenTraits.CLOSING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DICE_OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DIVISION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MODULO));
        assertTrue(tokenPattern.hasTrait(TokenTraits.MINUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MULTIPLICATION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.NUMBER));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPENING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERAND));
        assertTrue(tokenPattern.hasTrait(TokenTraits.OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.PLUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests a single enumeration element.
     */
    @Test
    public void testMultiplication() {

        TokenPattern tokenPattern = TokenPatterns.MULIPLICATION;

        assertFalse(tokenPattern.hasTrait(TokenTraits.CLOSING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DICE_OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DIVISION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MODULO));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MINUS));
        assertTrue(tokenPattern.hasTrait(TokenTraits.MULTIPLICATION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.NUMBER));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPENING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERAND));
        assertTrue(tokenPattern.hasTrait(TokenTraits.OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.PLUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests a single enumeration element.
     */
    @Test
    public void testNumber() {

        TokenPattern tokenPattern = TokenPatterns.NUMBER;

        assertFalse(tokenPattern.hasTrait(TokenTraits.CLOSING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DICE_OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DIVISION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MODULO));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MINUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MULTIPLICATION));
        assertTrue(tokenPattern.hasTrait(TokenTraits.NUMBER));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPENING_PARENTHESIS));
        assertTrue(tokenPattern.hasTrait(TokenTraits.OPERAND));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.PLUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests a single enumeration element.
     */
    @Test
    public void testOpeningParenthesis() {

        TokenPattern tokenPattern = TokenPatterns.OPENING_PARENTHESIS;

        assertFalse(tokenPattern.hasTrait(TokenTraits.CLOSING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DICE_OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DIVISION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MODULO));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MINUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MULTIPLICATION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.NUMBER));
        assertTrue(tokenPattern.hasTrait(TokenTraits.OPENING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERAND));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.PLUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests a single enumeration element.
     */
    @Test
    public void testPlus() {

        TokenPattern tokenPattern = TokenPatterns.PLUS;

        assertFalse(tokenPattern.hasTrait(TokenTraits.CLOSING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DICE_OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DIVISION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MODULO));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MINUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MULTIPLICATION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.NUMBER));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPENING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERAND));
        assertTrue(tokenPattern.hasTrait(TokenTraits.OPERATOR));
        assertTrue(tokenPattern.hasTrait(TokenTraits.PLUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests a single enumeration element.
     */
    @Test
    public void testVariable() {

        TokenPattern tokenPattern = TokenPatterns.VARIABLE;

        assertFalse(tokenPattern.hasTrait(TokenTraits.CLOSING_PARENTHESIS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DICE_OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.DIVISION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MODULO));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MINUS));
        assertFalse(tokenPattern.hasTrait(TokenTraits.MULTIPLICATION));
        assertFalse(tokenPattern.hasTrait(TokenTraits.NUMBER));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPENING_PARENTHESIS));
        assertTrue(tokenPattern.hasTrait(TokenTraits.OPERAND));
        assertFalse(tokenPattern.hasTrait(TokenTraits.OPERATOR));
        assertFalse(tokenPattern.hasTrait(TokenTraits.PLUS));
        assertTrue(tokenPattern.hasTrait(TokenTraits.VARIABLE));
    }

}
