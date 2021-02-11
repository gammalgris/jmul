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

package jmul.math.formula.parser;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jmul.checks.ParameterCheckHelper;

import static jmul.math.formula.parser.patterns.GroupNames.REST;
import static jmul.math.formula.parser.patterns.GroupNames.TOKEN;
import jmul.math.formula.parser.patterns.TokenPattern;
import jmul.math.formula.parser.tokens.Token;
import jmul.math.formula.parser.tokens.TokenImpl;
import jmul.math.formula.parser.tokens.TokenTrait;

import static jmul.string.Constants.EMPTY_STRING;


/**
 * A utility class for parsing formula string.
 *
 * @author Kristian Kutin
 */
public final class ParserHelper {

    /**
     * A regular exoression.
     */
    private static final String SPACE_REGEX;

    /*
     * The static initializer.
     */
    static {

        SPACE_REGEX = "[ ]";
    }

    /**
     * The default constructor.
     */
    private ParserHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Trims the specified string, i.e. removes all space characters.
     *
     * @param aString
     *        a string
     *
     * @return a trimmed string
     */
    public static String trimString(String aString) {

        return aString.replaceAll(SPACE_REGEX, EMPTY_STRING);
    }

    /**
     * Splits the specified string into token.
     *
     * @param allPatterns
     *        all patterns that identify tokens
     * @param aString
     *        the stringwhich is to be split into tokens
     *
     * @return a sequence of tokens
     */
    public static List<Token> splitString(Map<TokenPattern, Pattern> allPatterns, String aString) {

        List<Token> sequence = new ArrayList<>();

        ParameterCheckHelper.checkObjectParameter(aString);

        if (aString.isEmpty()) {

            return sequence;
        }

        TokenPattern tokenPattern = null;
        String tokenString = null;
        String rest = null;
        boolean match = false;
        for (Map.Entry<TokenPattern, Pattern> entry : allPatterns.entrySet()) {

            tokenPattern = entry.getKey();
            Pattern pattern = entry.getValue();

            Matcher matcher = pattern.matcher(aString);
            if (!matcher.matches()) {

                continue;
            }

            tokenString = matcher.group(TOKEN);
            rest = matcher.group(REST);

            if ((tokenString != null) && (rest != null)) {

                match = true;
                break;
            }
        }

        if (!match) {

            String message = String.format("No token could be identified in the specified string (\"%s\")!", aString);
            throw new FormulaParsingException(message);
        }

        Token token = new TokenImpl(tokenString, tokenPattern.getTraits().toArray(new TokenTrait[] { }));

        sequence.add(token);
        sequence.addAll(splitString(allPatterns, rest));

        return sequence;
    }

    /*
    public static Node<Token> createTree(List<Token> aTokenSequence) {

        if (aTokenSequence.isEmpty()) {

            return null;
        }

        if (aTokenSequence.size() == 1) {

            Token token = aTokenSequence.get(0);

            if (!token.hasTrait(TokenTraits.OPERAND)) {

                String message = String.format("The specified token sequence (%s) is invalid!", aTokenSequence);
                throw new FormulaParserException(message);
            }

            return new NodeImpl<Token>(token);
        }

        if (aTokenSequence.size() == 2) {

            Token tokenLeft = aTokenSequence.get(0);
            Token tokenRight = aTokenSequence.get(1);

            if (tokenLeft.hasTrait(TokenTraits.OPERAND) && tokenRight.hasTrait(TokenTraits.OPERATOR)) {

                Node<Token> operandNode = new NodeImpl<Token>(tokenLeft);
                Node<Token> operatorNode = new NodeImpl<Token>(tokenRight, operandNode);
                return operatorNode;

            } else if (tokenLeft.hasTrait(TokenTraits.OPERATOR) && tokenRight.hasTrait(TokenTraits.OPERAND)) {

                Node<Token> operandNode = new NodeImpl<Token>(tokenRight);
                Node<Token> operatorNode = new NodeImpl<Token>(tokenLeft, operandNode);
                return operatorNode;

            } else if (tokenLeft.hasTrait(TokenTraits.OPENING_PARENTHESIS) &&
                       tokenRight.hasTrait(TokenTraits.CLOSING_PARENTHESIS)) {

                return null;

            } else {

                String message = String.format("The specified token sequence (%s) is invalid!", aTokenSequence);
                throw new FormulaParserException(message);
            }
        }

        if (aTokenSequence.size() == 3) {

            Token tokenLeft = aTokenSequence.get(0);
            Token tokenMiddle = aTokenSequence.get(1);
            Token tokenRight = aTokenSequence.get(2);

            if (tokenLeft.hasTrait(TokenTraits.OPERAND) && tokenMiddle.hasTrait(TokenTraits.OPERATOR) && tokenRight.hasTrait(TokenTraits.OPERAND)) {

            } else if (tokenLeft.hasTrait(TokenTraits.OPERATOR) && tokenMiddle.hasTrait(TokenTraits.OPERAND) && tokenRight.hasTrait(TokenTraits.OPERATOR)) {
                
            } else {
                
            }
        }
    }

    /*
    public static Node<Token> createTree(List<Token> aTokenSequence) {

        // a condition to stop the recursion

        if (aTokenSequence.isEmpty()) {

            return null;
        }


        // identify all occurrences of sections enclosed with parenthesis

        List<Integer> openingParenthesis = new ArrayList<>();
        List<Integer> closingParenthesis = new ArrayList<>();
        int openParenthesis = 0;
        int openingParenthesisCount = 0;
        int closingParenthesisCount = 0;

        for (int a = 0; a < aTokenSequence.size(); a++) {

            Token token = aTokenSequence.get(a);

            if (token.hasTrait(TokenTraits.OPENING_PARENTHESIS)) {

                openingParenthesisCount++;

                if (openParenthesis == 0) {

                    openingParenthesis.add(a);
                }

                openParenthesis++;

            } else if (token.hasTrait(TokenTraits.CLOSING_PARENTHESIS)) {

                closingParenthesisCount++;

                openParenthesis--;

                if (openParenthesis == 0) {

                    closingParenthesis.add(a);
                }
            }
        }

        if (openingParenthesisCount != closingParenthesisCount) {

            String message =
                String.format("The number of opening and closing brackets is different (%s)!", aTokenSequence);
            throw new FormulaParserException(message);
        }

        if (openParenthesis != 0) {

            String message =
                String.format("There is a mismatch with opening and closing brackets (%s)!", aTokenSequence);
            throw new FormulaParserException(message);
        }

        if ((openingParenthesis.size() != openingParenthesisCount) &&
            (closingParenthesis.size() != closingParenthesisCount)) {

            String message =
                String.format("There is a mismatch with opening and closing brackets (%s)!", aTokenSequence);
            throw new FormulaParserException(message);
        }


        // resolve  sections with parenthesis first

        List<Node> nodes = new ArrayList<>();
        int length = openingParenthesis.size();

        for (int a = 0; a < length; a++) {

            int start = openingParenthesis.get(a);
            int end = closingParenthesis.get(a);
            List<Token> subSequence = getSubsection(aTokenSequence, start, end);

            Node<Token> node = createTree(subSequence);
            nodes.add(node);
        }


    }*/

    /* *
     * Extracts a sub sequence with the specified start and end index. The outer parenthesis are removed
     * as well.
     *
     * @param aTokenSequence
     * @param aStartIndex
     * @param anEndIndex
     *
     * @return a sub section
     * /
    public static List<Token> getSubsection(List<Token> aTokenSequence, int aStartIndex, int anEndIndex) {

        if (anEndIndex - aStartIndex == 1) {

            return new ArrayList<>();

        } else {

            return aTokenSequence.subList(aStartIndex + 1, anEndIndex - 1);
        }
    }*/

}
