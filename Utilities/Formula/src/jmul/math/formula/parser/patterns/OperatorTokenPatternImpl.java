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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.math.formula.parser.patterns;


import java.util.HashSet;
import java.util.Set;

import jmul.math.formula.operations.Arity;
import jmul.math.formula.operations.Operator;
import jmul.math.formula.parser.tokens.TokenType;


/**
 * An implementation of an OperatorTokenPattern.
 *
 * @author Kristian Kutin
 */
public class OperatorTokenPatternImpl extends TokenPatternImpl implements OperatorTokenPattern {

    /**
     * The class member contains the operator which is associated with this
     * token pattern.
     */
    private final Operator operator;

    /**
     * The default constructor.
     *
     * @param anOperator
     *        the operator
     */
    public OperatorTokenPatternImpl(Operator anOperator) {

        super(createPattern(anOperator), createClassification(anOperator));

        operator = anOperator;
    }

    /**
     * The method return the operator which is associated with this
     * TokenPattern.
     *
     * @return an operator
     */
    @Override
    public Operator getOperator() {

        return operator;
    }

    /**
     * The method creates a pattern based on an operator.
     *
     * @param anOperator
     *        an operator
     *
     * @return a pattern
     */
    private static String createPattern(Operator anOperator) {

        String pattern = "^(" + anOperator + ")$";

        // Replace meta characters
        pattern = pattern.replace("*", "\\*");
        pattern = pattern.replace("+", "\\+");

        return pattern;
    }

    /**
     * The method creates a classification based on an operator.
     *
     * @param anOperator
     *        an operator
     *
     * @return a classification
     */
    private static Set<TokenType> createClassification(Operator anOperator) {

        Set<TokenType> classification = new HashSet<>();
        classification.add(TokenType.OPERATOR);

        Arity arity = anOperator.getArity();
        if (arity.equals(Arity.UNARY)) {

            classification.add(TokenType.UNARY);

        } else if (arity.equals(Arity.BINARY)) {

            classification.add(TokenType.BINARY);

        } else if (arity.equals(Arity.TERNARY)) {

            classification.add(TokenType.TERNARY);
        }

        return classification;
    }

}
