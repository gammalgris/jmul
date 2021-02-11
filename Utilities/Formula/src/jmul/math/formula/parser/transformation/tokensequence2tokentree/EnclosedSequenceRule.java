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

import jmul.math.formula.parser.tokens.Token;
import jmul.math.formula.parser.tokens.TokenTraits;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;


/**
 * This rule processes a sequence which is enclosed by parenthesis.
 *
 * @author Kristian Kutin
 */
public class EnclosedSequenceRule extends TransformationRuleBase {

    /**
     * Creates a new transformation rule according to the specified parameters.
     *
     * @param anOrigin
     * @param aDestination
     * @param aPriority
     */
    public EnclosedSequenceRule(String anOrigin, String aDestination, int aPriority) {

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

            if (sequence.size() >= 2) {

                int firstIndex = 0;
                int lastIndex = sequence.size() - 1;

                Token firstToken = sequence.get(firstIndex);
                Token lastToken = sequence.get(lastIndex);

                if (firstToken.hasTrait(TokenTraits.OPENING_PARENTHESIS) &&
                    lastToken.hasTrait(TokenTraits.CLOSING_PARENTHESIS)) {

                    int open = 0;
                    int groups = 0;
                    int error = 0;

                    for (int a = firstIndex; a <= lastIndex; a++) {

                        Token token = sequence.get(a);
                        if (token.hasTrait(TokenTraits.OPENING_PARENTHESIS)) {

                            open++;

                        } else if (token.hasTrait(TokenTraits.CLOSING_PARENTHESIS)) {

                            if (open > 0) {

                                open--;

                                if (open == 0) {

                                    groups++;
                                }

                            } else {

                                error++;
                            }
                        }
                    }

                    return (open == 0) && (groups == 1) && (error == 0);
                }
            }
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
        List<Token> newSequence = new ArrayList<Token>();

        if (sequence.size() > 2) {

            int firstIndex = 1;
            int lastIndex = sequence.size() - 1;

            newSequence = sequence.subList(firstIndex, lastIndex);
        }

        TransformationParameters newParameters = TransformationHelper.createTranformationParameters(newSequence);
        return TransformationHelper.transform(newParameters);
    }

}
