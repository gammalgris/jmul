/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jmul.math.formula.operations.Operation;
import jmul.math.formula.operations.Operator;
import jmul.math.formula.parser.patterns.PatternFactory;
import jmul.math.formula.parser.patterns.TokenPattern;
import jmul.math.formula.parser.patterns.TokenPatternImpl;
import jmul.math.formula.parser.tokens.TokenType;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains tests to check the token types.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class TokenPatternTest {

    /**
     * a token pattern
     */
    private TokenPattern pattern;

    /**
     * The string which is tested for a match.
     */
    private String string;

    /**
     * The expected properties.
     */
    private Set<TokenType> expectedTokenTypes;

    /**
     * The expected match result.
     */
    private boolean expectedMatchResult;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aPattern
     * @param aString
     * @param theExpectedMatchResult
     * @param allExpectedTokenTypes
     */
    public TokenPatternTest(TokenPattern aPattern, String aString, boolean theExpectedMatchResult,
                            Set<TokenType> allExpectedTokenTypes) {

        super();

        pattern = aPattern;
        string = aString;

        expectedMatchResult = theExpectedMatchResult;
        expectedTokenTypes = allExpectedTokenTypes;
    }

    /**
     * Steps before a test.
     */
    @Before
    public void setUp() {

    }

    /**
     * Steps after a test.
     */
    @After
    public void tearDown() {

    }

    /**
     * Tests the token types.
     */
    @Test
    public void testTokenTypes() {

        List<TokenType> missingTypes = new ArrayList<>();

        for (TokenType type : expectedTokenTypes) {

            boolean result = pattern.isOfType(type);

            if (!result) {

                missingTypes.add(type);
            }
        }

        String message = "The token pattern doesn't meet the expected criteria (" + missingTypes.toString() + ")!";
        assertEquals(message, 0, missingTypes.size());
    }

    /**
     * Tests pattern matching.
     */
    @Test
    public void testMatch() {

        boolean result = pattern.isToken(string);

        String message =
            "The specified string (\"" + string + "\") doesn't match this token pattern (" + pattern.toString() + ")!";
        assertEquals(message, expectedMatchResult, result);
    }

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { newConstantTokenPattern(), "0", true, newConstantTokenTypes() });
        parameters.add(new Object[] { newConstantTokenPattern(), "1", true, newConstantTokenTypes() });
        parameters.add(new Object[] { newConstantTokenPattern(), "-1", false, newConstantTokenTypes() });
        parameters.add(new Object[] { newConstantTokenPattern(), "10", true, newConstantTokenTypes() });
        parameters.add(new Object[] { newVariableTokenPattern(), "10", false, newVariablesTokenTypes() });
        parameters.add(new Object[] { newVariableTokenPattern(), "test", true, newVariablesTokenTypes() });
        parameters.add(new Object[] { newOperatorTokenPattern(Operation.ADDITION.getOperator()), "+", true,
                                      newBinaryOperatorTokenTypes() });
        parameters.add(new Object[] { newOperatorTokenPattern(Operation.ADDITION.getOperator()), "1", false,
                                      newBinaryOperatorTokenTypes() });
        parameters.add(new Object[] { newOperatorTokenPattern(Operation.DIVISION.getOperator()), "/", true,
                                      newBinaryOperatorTokenTypes() });
        parameters.add(new Object[] { newOperatorTokenPattern(Operation.MULTIPLICATION.getOperator()), "*", true,
                                      newBinaryOperatorTokenTypes() });
        parameters.add(new Object[] { newOperatorTokenPattern(Operation.NEGATIVE_SIGN.getOperator()), "-", true,
                                      newUnaryOperatorTokenTypes() });
        parameters.add(new Object[] { newOperatorTokenPattern(Operation.POSITIVE_SIGN.getOperator()), "+", true,
                                      newUnaryOperatorTokenTypes() });
        parameters.add(new Object[] { newOperatorTokenPattern(Operation.SUBTRACTION.getOperator()), "-", true,
                                      newBinaryOperatorTokenTypes() });
        parameters.add(new Object[] { newOperatorTokenPattern(Operation.DICE_OPERATOR.getOperator()), "d", true,
                                      newBinaryOperatorTokenTypes() });
        parameters.add(new Object[] { newTermTokenPattern(), "(1+1)", true, newTermTokenTypes() });
        parameters.add(new Object[] { newTermTokenPattern(), "(1+1", false, newTermTokenTypes() });
        parameters.add(new Object[] { newTermTokenPattern(), "1+1)", false, newTermTokenTypes() });
        parameters.add(new Object[] { newTermTokenPattern(), "((1+1))", true, newTermTokenTypes() });

        return parameters;
    }

    /**
     * Returns a set containing all elements of the specified array.
     *
     * @param allTokenTypes
     *        an array of token types
     *
     * @return a set with token types
     */
    private static Set<TokenType> newTokenTypeSet(TokenType... allTokenTypes) {

        return new HashSet<>(Arrays.asList(allTokenTypes));
    }

    /**
     * Returns the token types for a certain setup.
     *
     * @return a set with token types
     */
    private static Set<TokenType> newConstantTokenTypes() {

        return newTokenTypeSet(TokenType.OPERAND, TokenType.CONSTANT);
    }

    /**
     * Returns the token types for a certain setup.
     *
     * @return a set with token types
     */
    private static Set<TokenType> newVariablesTokenTypes() {

        return newTokenTypeSet(TokenType.OPERAND, TokenType.VARIABLE);
    }

    /**
     * Returns the token types for a certain setup.
     *
     * @return a set with token types
     */
    private static Set<TokenType> newUnaryOperatorTokenTypes() {

        return newTokenTypeSet(TokenType.OPERATOR, TokenType.UNARY);
    }

    /**
     * Returns the token types for a certain setup.
     *
     * @return a set with token types
     */
    private static Set<TokenType> newBinaryOperatorTokenTypes() {

        return newTokenTypeSet(TokenType.OPERATOR, TokenType.BINARY);
    }

    /**
     * Returns the token types for a certain setup.
     *
     * @return a set with token types
     */
    private static Set<TokenType> newTernaryOperatorTokenTypes() {

        return newTokenTypeSet(TokenType.OPERATOR, TokenType.TERNARY);
    }

    /**
     * Returns the token types for a certain setup.
     *
     * @return a set with token types
     */
    private static Set<TokenType> newTermTokenTypes() {

        return newTokenTypeSet(TokenType.OPERAND, TokenType.TERM);
    }

    /**
     * Returns a token pattern for constants.
     *
     * @return a token pattern
     */
    private static TokenPattern newConstantTokenPattern() {

        return new TokenPatternImpl("^(([1-9][0-9]*)|0)$", newConstantTokenTypes());
    }

    /**
     * Returns a token pattern for variables.
     *
     * @return a token pattern
     */
    private static TokenPattern newVariableTokenPattern() {

        return new TokenPatternImpl("^([a-zA-Z][a-zA-Z-0-9_]*)$", newVariablesTokenTypes());
    }

    /**
     * Returns a token pattern for the specified operator.
     *
     * @param anOperator
     *
     * @return a token pattern
     */
    private static TokenPattern newOperatorTokenPattern(Operator anOperator) {

        return PatternFactory.newTokenPattern(anOperator);
    }

    /**
     * Returns a token pattern for terms.
     *
     * @return a token pattern
     */
    private static TokenPattern newTermTokenPattern() {

        return PatternFactory.newTermTokenPattern();
    }

}
