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


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jmul.math.formula.operations.Operator;
import jmul.math.formula.parser.patterns.OperatorTokenPattern;
import jmul.math.formula.parser.patterns.TokenPattern;


/**
 * An implementation of an operator token.
 *
 * @author Kristian Kutin
 */
public class OperatorTokenImpl extends TokenImpl implements OperatorToken {

    /**
     * The class member contains all operators and their operator patterns. All
     * patterns that are not associated with an operator are stored in the
     * inherited container. <br>
     * This container contains only the operators and their patterns.
     */
    private Map<Operator, OperatorTokenPattern> matchingOperatorPatterns;

    /**
     * The default constructor.
     */
    public OperatorTokenImpl(String aToken, Collection<TokenPattern> someMatchingPatterns) {

        super(aToken, someMatchingPatterns);

        matchingOperatorPatterns = new HashMap<>();

        for (TokenPattern pattern : getMatchingPatterns()) {

            if (pattern instanceof OperatorTokenPattern) {

                OperatorTokenPattern operatorPattern = (OperatorTokenPattern) pattern;
                Operator operator = operatorPattern.getOperator();
                matchingOperatorPatterns.put(operator, operatorPattern);
            }
        }
    }

    /**
     * The method returns all operators which have been identified with this
     * token.
     *
     * @return some operators
     */
    @Override
    public Set<Operator> getMatchingOperators() {

        return matchingOperatorPatterns.keySet();
    }

    /**
     * The method will revome an operator and its pattern which is associated
     * with this token.
     *
     * @param anOperator
     *        an oeprator
     */
    @Override
    public void removeOperator(Operator anOperator) {

        TokenPattern pattern = matchingOperatorPatterns.get(anOperator);

        matchingOperatorPatterns.remove(anOperator);
        super.removePattern(pattern);
    }

    /**
     * The method will remove all operators except this operator.
     *
     * @param anOperator
     *        an operator
     */
    @Override
    public void retainOperator(Operator anOperator) {

        if (matchingOperatorPatterns.containsKey(anOperator)) {

            OperatorTokenPattern pattern = matchingOperatorPatterns.get(anOperator);

            super.retainPattern(pattern);

            matchingOperatorPatterns.clear();
            matchingOperatorPatterns.put(anOperator, pattern);
        }
    }

}
