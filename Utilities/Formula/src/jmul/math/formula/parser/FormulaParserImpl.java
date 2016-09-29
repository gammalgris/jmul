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

package jmul.math.formula.parser;


import java.util.Iterator;

import jmul.math.formula.Constant;
import jmul.math.formula.Formula;
import jmul.math.formula.Operand;
import jmul.math.formula.Term;
import jmul.math.formula.Variable;
import jmul.math.formula.operations.Operation;
import jmul.math.formula.operations.Operator;
import jmul.math.formula.parser.patterns.PatternFactory;
import jmul.math.formula.parser.patterns.TermTokenPatternImpl;
import jmul.math.formula.parser.patterns.TokenPattern;
import jmul.math.formula.parser.tokens.OperatorToken;
import jmul.math.formula.parser.tokens.Token;
import jmul.math.formula.parser.tokens.TokenFactory;
import jmul.math.formula.parser.tokens.TokenGroup;
import jmul.math.formula.parser.tokens.TokenGroupSequence;
import jmul.math.formula.parser.tokens.TokenParser;
import jmul.math.formula.parser.tokens.TokenSequence;
import jmul.math.formula.parser.tokens.TokenType;


/**
 * An implementation of a formula parser.
 *
 * @author Kristian Kutin
 */
public class FormulaParserImpl implements FormulaParser {

    /**
     * The class member contains a token parser which will split a string into
     * tokens.
     */
    private static TokenParser tokenParser = newMathParser();

    /**
     * The method will create a new token parser for formulas.
     *
     * @return a token parser
     */
    private static TokenParser newMathParser() {

        TokenParser parser = TokenFactory.newTokenParser();
        TokenPattern pattern = null;

        // Add operator patterns first.
        for (Operation operation : Operation.values()) {

            pattern = PatternFactory.newTokenPattern(operation.getOperator());
            parser.addTokenPattern(pattern);
        }

        // Add operand pattern (variable) now.
        String regex = "([a-zA-Z][a-zA-Z-0-9_]*)";
        pattern = PatternFactory.newTokenPattern(regex, TokenType.OPERAND, TokenType.VARIABLE);
        parser.addTokenPattern(pattern);

        // Add operand pattern (constant) now.
        regex = "(([1-9][0-9]*)|0)";
        pattern = PatternFactory.newTokenPattern(regex, TokenType.OPERAND, TokenType.CONSTANT);
        parser.addTokenPattern(pattern);

        // Add operand pattern (term) now.
        pattern = new TermTokenPatternImpl();
        parser.addTokenPattern(pattern);

        return parser;
    }

    /**
     * The method will parse a string and return a formula.
     *
     * @param aString
     *        a string to be parsed
     *
     * @return a formula
     */
    public Formula parseString(String aString) {

        Formula formula = new Formula();

        Operand operand = parseString(formula, aString);
        formula.setOperand(operand);

        return formula;
    }

    /**
     * The method will parse a string and an operand. To parse a string the
     * method will split the string into several substrings which are evaluated
     * by recursivley calling this method.
     *
     * @param aFormula
     *        a formula which will will contain this operand. This parameter
     *        is for convenience so the formula can easily detect variable
     *        operands.
     * @param aString
     *        a strng to be parsed
     *
     * @return an operand
     */
    private Operand parseString(Formula aFormula, String aString) {

        TokenSequence tokenSequence = tokenParser.parseExpression(aString);

        // Check if the token parser could determine a sequence without
        // undefined or ambigous tokens.
        if (tokenSequence == null) {

            String message = "The formula \"" + aString + "\" couldn't be parsed!";
            throw new FormulaParserException(message);
        }

        TokenGroupSequence groupSequence = TokenGroupSequence.groupTokens(tokenSequence);

        // Does the string consist of only one group?
        if (groupSequence.size() == 1) {

            // This string contains only one group of operations.
            TokenGroup group = groupSequence.get(0);


            // Does the group contain an operator?
            if (group.containsOperator()) {

                // This group contains an operator and must have at least two
                // operands.

                // Split the group into single operands and call this method
                // recursively.

                Term term = new Term(aFormula, group.getOperator());

                for (int operandIndex : group.getOperands()) {

                    Token token = tokenSequence.get(operandIndex);
                    Operand operand = parseString(aFormula, token.toString());
                    term.addOperand(operand);
                }

                return term;

            } else {

                // This group doesn't have a binary operator. This group may
                // consist of only one operand or an operand with an unary
                // operator. Check which it is.

                if (group.size() == 1) {

                    // The group consists of only one token which must be an
                    // operand.

                    Token token = group.iterator().next();
                    if (token.isOfType(TokenType.OPERAND)) {

                        // The token is indeed an operand. Now check if the
                        // token is a term, a variable or a constant.

                        if (token.isOfType(TokenType.TERM)) {

                            // This operand is a term. Remove the outer
                            // parenthesis (the first and last character of this
                            // operand) and call this method recursively.

                            String withoutParenthesis = token.toString();
                            withoutParenthesis = withoutParenthesis.substring(1, aString.length() - 1);
                            return parseString(aFormula, withoutParenthesis);

                        } else if (token.isOfType(TokenType.VARIABLE)) {

                            // This operand is a variable. Instantiate a
                            // variable and use the token string as label.

                            return new Variable(token.toString());

                        } else if (token.isOfType(TokenType.CONSTANT)) {

                            // This operand is a constant. The token string
                            // should contain a number which has to be parsed.
                            // Instantiate a constant.

                            int value = Integer.parseInt(token.toString());
                            return new Constant(value);

                        } else {

                            String message = "Unknown operand found! Operand=" + token;
                            throw new FormulaParserException(message);
                        }

                    } else {

                        String message = "An operand was expected, but not found! Operand=" + token;
                        throw new FormulaParserException(message);
                    }

                } else if (group.size() == 2) {

                    // The group consists of two tokens. One must be an operand
                    // and the other an unary operator.

                    OperatorToken operatorToken = null;
                    Token operandToken = null;

                    Iterator<Token> i = group.iterator();
                    while (i.hasNext()) {

                        Token token = i.next();

                        if (token.isOfType(TokenType.OPERAND)) {
                            operandToken = token;
                        } else if (token.isOfType(TokenType.OPERATOR) && token.isOfType(TokenType.UNARY)) {
                            operatorToken = (OperatorToken) token;
                        }
                    }

                    if (operatorToken == null) {

                        String message =
                            "The group consists of two operands! One operand and one unary operator are expected. Group=" +
                            group;
                        throw new FormulaParserException(message);
                    }

                    if (operandToken == null) {

                        String message =
                            "The group consists of two operators! One operand and one unary operator are expected. Group=" +
                            group;
                        throw new FormulaParserException(message);
                    }

                    Operator operator = operatorToken.getMatchingOperators().iterator().next();
                    Term term = new Term(aFormula, operator);

                    term.addOperand(parseString(aFormula, operandToken.toString()));

                    return term;

                } else {

                    // This group doesn't have a binary operator and contains
                    // of more than two operands/unary operators.

                    String message = "Only one operand and one unary operator are expected. Group=" + group;
                    throw new FormulaParserException(message);

                }

            }

        } else {

            // This string consists of more than one group of operations.
            // Determine the last operator with the lowest priority and split
            // this string accordingly. Call this method with each substrings
            // recursively and instantiate a term with both results.


            int groupIndex = groupSequence.determineLastGroupWithLowestPriorityOperator();
            TokenGroup group = groupSequence.get(groupIndex);

            int operatorIndex = group.getIndexOfLastGroupOperator();
            OperatorToken operatorToken = (OperatorToken) tokenSequence.get(operatorIndex);
            Operator operator = operatorToken.getMatchingOperators().iterator().next();

            TokenSequence[] parts = tokenSequence.splitSequence(operatorIndex);

            Operand leftOperand = parseString(aFormula, parts[0].toString());
            Operand rightOperand = parseString(aFormula, parts[1].toString());

            Term term = new Term(aFormula, operator);
            term.addOperand(leftOperand);
            term.addOperand(rightOperand);

            return term;
        }

    }

}
