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


import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import jmul.math.formula.operations.Operator;
import jmul.math.formula.parser.GroupPosition;


/**
 * The class marks a group of tokens within a sequence of tokens.
 *
 * @author Kristian Kutin
 */
public class TokenGroup implements Iterable<Token> {

    /**
     * The class member contains a reference to the token sequence.
     */
    private final TokenSequence sequence;

    /**
     * The class member contains the starting index for this group.
     */
    private final int startIndex;

    /**
     * The class member contains the token count for this group.
     */
    private int length;

    /**
     * The class member contains the operator for this group. An operator is the
     * 'glue' for groups. Only binary operators are considered.
     */
    private Operator operator;

    /**
     * The default constructor.
     *
     * @param aSequence
     *        a sequence of tokens
     * @param aStartingIndex
     *        the starting index for this group
     */
    public TokenGroup(TokenSequence aSequence, int aStartingIndex) {

        if (aSequence.containsAmbigousToken() ||
            aSequence.containsUndefinedToken()) {

            String message =
                "The token sequence contains ambigous or undefined tokens!";
            throw new IllegalArgumentException(message);
        }

        sequence = aSequence;
        startIndex = aStartingIndex;
        length = 0;
        operator = null;
    }

    /**
     * The method will check whether or not this group contains tokens.
     *
     * @return true, if this group ocntains tokens, else false
     */
    public boolean containsTokens() {

        return (length > 0);
    }

    /**
     * The method will check whether or not this group contains a binary
     * operator.
     *
     * @return true, if this group contains a binary operator, else false
     */
    public boolean containsOperator() {

        return (operator != null);
    }

    /**
     * The method will check whether or not the next token within this sequence
     * can be added to this group.
     *
     * @return true, if the next token in the sequence can be added to this
     *         group, else false
     */
    public boolean hasNextGroupToken() {

        int index = startIndex + length;

        if (index > (sequence.size() - 1)) {
            return false;
        }

        Token nextToken = sequence.get(index);

        if (nextToken.isOfType(TokenType.OPERATOR)) {

            // The next token is an operator.

            OperatorToken nextOperatorToken = (OperatorToken)nextToken;
            if (nextOperatorToken.isOfType(TokenType.BINARY)) {

                // The next token is a binary operator.

                Operator nextOperator =
                    nextOperatorToken.getMatchingOperators().iterator().next();

                if (operator != null) {

                    // This group already possesses a group operator.

                    if (operator.equals(nextOperator)) {

                        return true;

                    }

                } else {

                    // This group doesn't have a group operator yet.

                    return true;
                }

            } else {

                // The next token is an unary operator.

                return true;

            }

        } else {

            // The next token is no operator

            return true;
        }

        return false;
    }

    /**
     * The method will add the next token to this group.
     */
    public void addNextGroupToken() {

        if (hasNextGroupToken()) {

            int index = startIndex + length;
            Token nextToken = sequence.get(index);

            if (nextToken.isOfType(TokenType.OPERATOR)) {

                OperatorToken nextOperatorToken = (OperatorToken)nextToken;

                if (nextOperatorToken.isOfType(TokenType.BINARY)) {

                    Operator nextOperator =
                        nextOperatorToken.getMatchingOperators().iterator().next();
                    operator = nextOperator;
                }
            }

            length++;
        }
    }

    /**
     * The method will return the index of the last group operator within this
     * group.
     *
     * @return an index or -1 if no such operator exists
     */
    public int getIndexOfLastGroupOperator() {

        int end = startIndex + length - 1;
        for (int a = end; a >= startIndex; a--) {

            Token nextToken = sequence.get(a);

            if (nextToken.isOfType(TokenType.OPERATOR)) {

                OperatorToken nextOperatorToken = (OperatorToken)nextToken;
                Operator nextOperator =
                    nextOperatorToken.getMatchingOperators().iterator().next();

                if (operator.equals(nextOperator)) {
                    return a;
                }
            }

        }

        return -1;
    }

    /**
     * The method will return the index of the last group operand within this
     * group.
     *
     * @return an index or -1 if no such operand exists
     */
    public int getIndexOfLastGroupOperand() {

        int end = startIndex + length - 1;
        for (int a = end; a >= startIndex; a--) {

            Token nextToken = sequence.get(a);

            if (nextToken.isOfType(TokenType.OPERAND)) {

                // Found the last operand. Now check if there is an unary
                // operator to the left.

                if (a > 0) {

                    int left = a - 1;
                    Token leftToken = sequence.get(left);

                    if (leftToken.isOfType(TokenType.OPERATOR) &&
                        leftToken.isOfType(TokenType.UNARY)) {
                        return left;
                    }
                }

                return a;
            }
        }

        return -1;
    }

    /**
     * The method returns the token count for this group.
     *
     * @return a token count
     */
    public int size() {

        return length;
    }

    /**
     * The method will determine the position of this group withing the
     * sequence.
     *
     * @return a position
     */
    public GroupPosition getPosition() {

        boolean isLeft = (startIndex == 0);
        boolean isRight = ((startIndex + size()) == sequence.size());

        if (isLeft && isRight) {
            return GroupPosition.NOT_APPLICABLE;
        } else if (isLeft) {
            return GroupPosition.LEFT;
        } else if (isRight) {
            return GroupPosition.RIGHT;
        } else {
            return GroupPosition.MIDDLE;
        }
    }

    /**
     * The method returns the binary operator within this group.
     *
     * @return an operator
     */
    public Operator getOperator() {

        return operator;
    }

    /**
     * The method will return an index set for all operands within this group.
     *
     * @return an index set
     */
    public SortedSet<Integer> getOperands() {

        SortedSet<Integer> indexSet = new TreeSet<Integer>();

        for (int a = startIndex; a < (startIndex + size()); a++) {

            Token token = sequence.get(a);
            if (token.isOfType(TokenType.OPERAND)) {

                indexSet.add(a);
            }
        }

        return indexSet;
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        int end = startIndex + length;
        for (int a = startIndex; a < end; a++) {

            buffer.append(sequence.get(a));
        }

        return buffer.toString();
    }

    /**
     * The method returns an iterator which will allow to iterate all tokens
     * within this group.
     *
     * @return an iterator
     */
    public Iterator<Token> iterator() {

        return new TokenIterator();
    }

    /**
     * The inner class implements a token iterator to iterate all tokens within
     * this group.
     */
    class TokenIterator implements Iterator<Token> {

        /**
         * The class member contains the current token index.
         */
        private int index = startIndex;

        /**
         * The method checks whether or not there is another token left.
         *
         * @return true, if more tokens exist, else false
         */
        public boolean hasNext() {

            if (index < (startIndex + size())) {

                return true;
            }

            return false;
        }

        /**
         * The method will return the next token.
         *
         * @return a token
         */
        public Token next() {

            Token token = sequence.get(index);
            index++;

            return token;
        }

        /**
         * The method isn't supported.
         */
        public void remove() {

            throw new UnsupportedOperationException();
        }

    }

}
