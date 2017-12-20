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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import jmul.math.formula.operations.Arity;
import jmul.math.formula.operations.Operator;
import jmul.math.formula.operations.Position;
import jmul.math.formula.parser.patterns.OperatorTokenPattern;
import jmul.math.formula.parser.patterns.TokenPattern;

import jmul.string.TextHelper;


/**
 * An implementation of an expression parser.
 *
 * @author Kristian Kutin
 */
public class TokenParserImpl implements TokenParser {

    /**
     * The class member contains all token patterns that are handled by this
     * parser.
     */
    private Set<TokenPattern> tokenPatterns;

    /**
     * The default constructor.
     */
    public TokenParserImpl() {

        tokenPatterns = new HashSet<>();
    }

    /**
     * The method will add a token pattern.
     *
     * @param aTokenPattern
     *        a token pattern
     */
    @Override
    public void addTokenPattern(TokenPattern aTokenPattern) {

        if (tokenPatterns.contains(aTokenPattern)) {

            String message = "No duplicate token patterns allowed!";
            throw new IllegalArgumentException(message);
        }

        tokenPatterns.add(aTokenPattern);
    }

    /**
     * The method will parse an expression and split it into tokens.
     *
     * @param anExpression
     *        an expression
     *
     * @return a parsed expression
     */
    @Override
    public TokenSequence parseExpression(String anExpression) {

        TokenSequence determinedSequence = null;

        // Check the seqeunces on ambiguity and try to resolve the ambiguity.
        // The first sequence that has no ambiguity will be returned.
        for (TokenSequence sequence : splitString(anExpression)) {

            boolean undefined = sequence.containsUndefinedToken();
            if (!undefined) {

                boolean ambigous = sequence.containsAmbigousToken();
                if (ambigous) {

                    TokenSequence resolvedSequence = resolveAmbiguity(sequence);

                    // Check if the ambiguities could be resolved.
                    ambigous = resolvedSequence.containsAmbigousToken();

                    if (!ambigous) {

                        // Even if the ambiguity could have been resolved the
                        // sequence might not be valid.

                        boolean valid = isValidSequence(sequence);

                        if (valid) {
                            determinedSequence = resolvedSequence;
                            break;
                        }

                    }

                } else {

                    // Even if the sequence contains no undefined or ambigous
                    // tokens it might not be a valid sequence

                    boolean valid = isValidSequence(sequence);

                    if (valid) {
                        determinedSequence = sequence;
                        break;
                    }

                }
            }
        }

        return determinedSequence;
    }

    /**
     * The method will try to resolve ambigous tokens withing this sequence.
     *
     * @param aSequence
     *        a sequence
     *
     * @return a sequence with possibly resolved ambiguities
     */
    private static TokenSequence resolveAmbiguity(TokenSequence aSequence) {

        TokenSequence resolvedSequence = new TokenSequence();

        int length = aSequence.size();
        for (int a = 0; a < length; a++) {

            Token token = aSequence.get(a);

            if (token.isOfType(TokenType.OPERATOR) || containsOperatorTokenPattern(token)) {

                Operator operator = determineOperator(aSequence, a);
                if (operator != null) {

                    // The ambiguity could be resolved.

                    OperatorToken operatorToken = (OperatorToken) token;
                    operatorToken.retainOperator(operator);
                    resolvedSequence.add(operatorToken);

                } else {

                    // The ambiguity cannot be resolved.

                    resolvedSequence.add(token);

                }

            } else {

                // Ambiguity on other elements isn't handled.

                resolvedSequence.add(token);
            }
        }

        return resolvedSequence;
    }

    /**
     * The method checks if a token matches an OperatorTokenPattern. This is
     * a helper method or cases where a token matches both an operand and an
     * operator pattern. This case cannot be distinguised with the TokenType.
     *
     * @param aToken
     *        a token
     *
     * @return true, if the token matches an OperatorTokenPattern, else false
     */
    private static boolean containsOperatorTokenPattern(Token aToken) {

        for (TokenPattern pattern : aToken.getMatchingPatterns()) {

            boolean isOperator = pattern instanceof OperatorTokenPattern;

            if (isOperator) {

                return true;
            }
        }

        return false;
    }

    /**
     * The method will split a string into a sequence of tokens. Due to
     * ambigous tokens the method may identify more than one sequence. The
     * caller will have to examine each returned sequence and determine which
     * sequence is the most plausible one.
     *
     * @param aString
     *        a string to be split
     *
     * @return several sequences of tokens
     */
    private List<TokenSequence> splitString(String aString) {

        List<TokenSequence> allSequences = new ArrayList<>();

        String string = aString.trim(); // The sequence may still have leading
        // and trailing spaces

        if (hasNext(string)) {

            Map<Integer, Token> next = getNextToken(string);

            for (Map.Entry<Integer, Token> entry : next.entrySet()) {

                Token token = entry.getValue();

                int length = token.getToken().length();
                String rest = string.substring(length);
                rest = rest.trim(); // Remove leading spaces.

                List<TokenSequence> subSequences = splitString(rest);

                if (subSequences.isEmpty()) {

                    TokenSequence subSequence = new TokenSequence();
                    subSequence.add(0, token);
                    allSequences.add(subSequence);

                } else {

                    for (TokenSequence subSequence : subSequences) {

                        subSequence.add(0, token);
                        allSequences.add(subSequence);
                    }
                }
            }
        }

        return allSequences;
    }

    /**
     * The method checks if a string may contain more tokens.
     *
     * @param aString
     *        a string to be examined
     *
     * @return true, if the string may contain more tokens, else false
     */
    private static boolean hasNext(String aString) {

        return aString.length() > 0;
    }

    /**
     * This method will try to identify the next token according to the known
     * token patterns. More than one token might be identified. The tokens might
     * also vary in length. The method returns all possible tokens and orders
     * them according to their lengths.
     *
     * @param aString
     *        a string a string to be examined
     *
     * @return matching tokens of varying lengths
     */
    private Map<Integer, Token> getNextToken(String aString) {

        Map<Integer, Token> identifiedTokens = new HashMap<>();

        int length = aString.length();
        for (int a = 1; a <= length; a++) {

            String substring = aString.substring(0, a);
            Token token = identifyToken(substring);

            if (token != null) {

                identifiedTokens.put(a, token);
            }
        }

        return identifiedTokens;
    }

    /**
     * The method will try to identify the patterns that will exactly match this
     * string (from head to tail). If more than one pattern is recognized the
     * information will be packed into one token.
     *
     * @param aString
     *        a string a string to be examined
     *
     * @return a token exactly matching the string, otherwise null
     */
    private Token identifyToken(String aString) {

        Collection<TokenPattern> matchingPatterns = new ArrayList<>();

        // Recognize all matching patterns.
        for (TokenPattern pattern : tokenPatterns) {

            boolean matches = pattern.isToken(aString);

            if (matches) {

                matchingPatterns.add(pattern);
            }
        }

        if (matchingPatterns.isEmpty()) {

            return null;

        } else {

            return TokenFactory.newToken(aString, matchingPatterns);
        }
    }

    /**
     * The method tries to resolve an ambigous operator.
     *
     * @param aTokenSequence
     *        a sequence of tokens
     * @param anIndex
     *        the token to be examined
     *
     * @return an operator or null, if the ambiguity cannot be resolved
     */
    private static Operator determineOperator(TokenSequence aTokenSequence, int anIndex) {

        Operator determinedOperator = null;
        Token token = aTokenSequence.get(anIndex);

        if (!(token instanceof OperatorToken)) {

            return determinedOperator;
        }

        OperatorToken operatorToken = (OperatorToken) token;
        String symbol = operatorToken.getToken();
        Set<Operator> operators = operatorToken.getMatchingOperators();

        // the operator with the lowest arity takes precedence
        SortedMap<Arity, Operator> orderOfPrecedence = new TreeMap<>();

        for (Operator operator : operators) {
            orderOfPrecedence.put(operator.getArity(), operator);
        }

        // check each possible operator and determine which is one applicable
        for (Map.Entry<Arity, Operator> entry : orderOfPrecedence.entrySet()) {

            Arity arity = entry.getKey();
            Operator operator = entry.getValue();

            if (arity.equals(Arity.UNARY)) {

                boolean unary = checkUnaryOperator(aTokenSequence, anIndex, operator);
                if (unary) {

                    determinedOperator = operator;
                    break;
                }

            } else if (arity.equals(Arity.BINARY)) {

                boolean binary = checkBinaryOperator(aTokenSequence, anIndex, operator);
                if (binary) {

                    determinedOperator = operator;
                    break;
                }

            } else {

                String message =
                    TextHelper.concatenateStrings("The ", arity, " operator \"", symbol, "\" cannot be examined!");
                throw new IllegalArgumentException(message);
            }
        }

        return determinedOperator;
    }

    /**
     * The method checks whether an operator is applicable at a given position
     * within a sequence of tokens.
     *
     * @param aTokenSequence
     *        a sequence of tokens
     * @param anIndex
     *        the index of the operator within the sequence
     * @param anOperator
     *        the operator which is to be examined
     *
     * @return true, if the operator fits into this position, else false
     */
    private static boolean checkUnaryOperator(TokenSequence aTokenSequence, int anIndex, Operator anOperator) {

        // Check the position of this operator
        Position position = anOperator.getPosition();

        if (anIndex == 0) {
            // The operator is the first token of this sequence.

            if (position.equals(Position.IN_FRONT_OF_OPERAND)) {

                // As this is the first token it might meet the
                // criteria.
                if (anIndex < (aTokenSequence.size() - 1)) {

                    Token nextToken = aTokenSequence.get(anIndex + 1);
                    if (nextToken.isOfType(TokenType.OPERAND)) {

                        return true;
                    }
                }

            } else if (position.equals(Position.AFTER_OPERAND)) {

                // As this is the first token it doesn't meet the
                // criteria.

            } else {

                // other positions don't apply to unary operators

            }

        } else if (anIndex < (aTokenSequence.size() - 1)) {
            // The operator is a middle token of this sequence.

            if (position.equals(Position.IN_FRONT_OF_OPERAND)) {

                // As this is a middle token it might meet the
                // criteria.
                Token nextToken = aTokenSequence.get(anIndex + 1);
                if (nextToken.isOfType(TokenType.OPERAND)) {

                    Token previousToken = aTokenSequence.get(anIndex - 1);
                    if (!previousToken.isOfType(TokenType.OPERAND)) {
                        return true;
                    }
                }

            } else if (position.equals(Position.AFTER_OPERAND)) {

                // As this is a middle token it might meet the
                // criteria.
                if (anIndex > 0) {

                    Token previousToken = aTokenSequence.get(anIndex - 1);
                    if (previousToken.isOfType(TokenType.OPERAND)) {

                        return true;
                    }
                }

            } else {

                // other positions don't apply to unary operators

            }

        } else {
            // The operator is the last token of this sequence.

            if (position.equals(Position.IN_FRONT_OF_OPERAND)) {

                // As this is the last token it doesn't meet the
                // criteria.

            } else if (position.equals(Position.AFTER_OPERAND)) {

                // As this is the last token it might meet the
                // criteria.
                if (anIndex > 0) {

                    Token previousToken = aTokenSequence.get(anIndex - 1);
                    if (previousToken.isOfType(TokenType.OPERAND)) {

                        return true;
                    }
                }

            } else {

                // other positions don't apply to unary operators

            }

        }

        return false;
    }

    /**
     * The method checks whether an operator is applicable at a given position
     * within a sequence of tokens.
     *
     * @param aTokenSequence
     *        a sequence of tokens
     * @param anIndex
     *        the index of the operator within the sequence
     * @param anOperator
     *        the operator which is to be examined
     *
     * @return true, if the operator fits into this position, else false
     */
    private static boolean checkBinaryOperator(TokenSequence aTokenSequence, int anIndex, Operator anOperator) {

        // Check the position of this operator
        Position position = anOperator.getPosition();

        if (anIndex == 0) {
            // The operator is the first token of this sequence.

            // As this is the first token it doesn't meet the
            // criteria.












        } else if (anIndex < (aTokenSequence.size() - 1)) {
            // The operator is a middle token of this sequence.

            if (position.equals(Position.BETWEEN_OPERANDS)) {

                // As this is a middle token it might meet the
                // criteria.
                Token previousToken = aTokenSequence.get(anIndex - 1);
                Token nextToken = aTokenSequence.get(anIndex + 1);
                if (previousToken.isOfType(TokenType.OPERAND) && nextToken.isOfType(TokenType.OPERAND)) {

                    return true;

                } else if (previousToken.isOfType(TokenType.OPERAND) && nextToken.isOfType(TokenType.OPERATOR)) {

                    return true;
                }

            } else {

                // other positions don't apply to binary operators

            }

        } else {
            // The operator is the last token of this sequence.

            // As this is the last token it doesn't meet the
            // criteria.

        }

        return false;
    }

    /**
     * This method will check if a sequence is valid. The validity depends on
     * the actual context. In this case a valid sequence will consist of
     * operands and binary operators betweenj the operands. Additionally
     * operands may have an unary operator associated with them. This method
     * will only perform a rudimentary check of the validity.
     *
     * @param aSequence
     *        a token sequence
     *
     * @return true, if the sequence is valid, else false
     */
    private static boolean isValidSequence(TokenSequence aSequence) {

        int length = aSequence.size();
        for (int a = 0; a < length; a++) {

            Token token = aSequence.get(a);

            if (token.isOfType(TokenType.OPERAND)) {

                // check the operand

                Token previous = null;
                Token next = null;

                if (a > 0) {
                    previous = aSequence.get(a - 1);
                }

                if (a < (length - 1)) {
                    next = aSequence.get(a + 1);
                }

                boolean validPrevious =
                    (previous == null) || ((previous != null) && previous.isOfType(TokenType.OPERATOR));
                boolean validNext = (next == null) || ((next != null) && next.isOfType(TokenType.OPERATOR));

                if (!validPrevious || !validNext) {
                    return false;
                }

            } else if (token.isOfType(TokenType.OPERATOR)) {

                // check the operator

                boolean unary = token.isOfType(TokenType.UNARY);
                boolean binary = token.isOfType(TokenType.BINARY);

                // check if the operator is unambigous
                boolean unambigousArity = unary || binary;

                if (!unambigousArity) {
                    return false;
                }

                // Operands can be surrounded by operands or other operators.
                // Checking this token will not reveal any new informations.

            } else {

                // if the token is undefined or ambigous then the sequence is
                // not valid

                return false;

            }
        }

        return true;
    }

}
