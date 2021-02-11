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

package jmul.math.formula.parser.transformation.tokensequence2tokentree;


import java.util.ArrayList;
import java.util.List;

import jmul.math.formula.parser.FormulaParsingException;
import jmul.math.formula.parser.operators.Operator;
import jmul.math.formula.parser.operators.Operators;
import jmul.math.formula.parser.tokens.Token;
import jmul.math.formula.parser.tokens.TokenTraits;
import jmul.math.graph.Node;
import jmul.math.graph.NodeImpl;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;


/**
 * This rule splits a sequence.
 *
 * @author Kristian Kutin
 */
public class SplitSequenceRule extends TransformationRuleBase {

    /**
     * Creates a new transformation rule according to the specified parameters.
     *
     * @param anOrigin
     * @param aDestination
     * @param aPriority
     */
    public SplitSequenceRule(String anOrigin, String aDestination, int aPriority) {

        super(anOrigin, aDestination, aPriority);
    }

    /**
     * Checks if this rule is applicable on the specified parameter.
     *
     * @param someParameters
     *
     * @return <code>true</code> if this rule is applicable, else <code>false</code>
     */
    @Override
    public boolean isApplicable(TransformationParameters someParameters) {

        Object object = someParameters.getObject();

        if (object instanceof List) {

            List<Token> sequence = (List<Token>) object;

            return sequence.size() >= 3;
        }

        return false;
    }

    /**
     * The actual transformation of the specified parameters.
     *
     * @param someParameters
     *
     * @return a token tree
     */
    @Override
    public Object transform(TransformationParameters someParameters) {

        List<Token> sequence = TransformationHelper.object2TokenSequence(someParameters);
        List<Integer> operators = new ArrayList<>();

        int open = 0;
        int groups = 0;
        int error = 0;
        boolean ignore = false;
        for (int a = 0; a < sequence.size(); a++) {

            Token token = sequence.get(a);
            if (token.hasTrait(TokenTraits.OPENING_PARENTHESIS)) {

                if (open == 0) {

                    ignore = !ignore;
                }

                open++;

            } else if (token.hasTrait(TokenTraits.CLOSING_PARENTHESIS)) {

                if (open > 0) {

                    open--;

                    if (open == 0) {

                        groups++;
                        ignore = !ignore;
                    }

                } else {

                    error++;
                }

            } else if (token.hasTrait(TokenTraits.OPERATOR)) {

                if (!ignore) {

                    operators.add(a);
                }
            }
        }


        if ((open != 0) || (error > 0) || operators.isEmpty()) {

            String message = String.format("An invalid token sequence has been specified (%s)!", sequence);
            throw new FormulaParsingException(message);
        }


        int index = operators.get(0);
        Token token = sequence.get(index);
        Operator operator = Operators.getOperator(token);
        for (int a = 0; a < operators.size(); a++) {

            int nextIndex = operators.get(a);
            Token nextToken = sequence.get(nextIndex);
            Operator nextOperator = Operators.getOperator(nextToken);

            if (operator.getPrecedence() > nextOperator.getPrecedence()) {

                index = nextIndex;
                token = nextToken;
                operator = nextOperator;
            }
        }


        List<Token> left = sequence.subList(0, index);
        List<Token> right = sequence.subList(index + 1, sequence.size());

        TransformationParameters leftParameters = TransformationHelper.createTranformationParameters(left);
        TransformationParameters rightParameters = TransformationHelper.createTranformationParameters(right);

        Node<Token> leftNode = (Node<Token>) TransformationHelper.transform(leftParameters);
        Node<Token> rightNode = (Node<Token>) TransformationHelper.transform(rightParameters);

        return new NodeImpl<Token>(token, leftNode, rightNode);
    }

}
