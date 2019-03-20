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

package jmul.math.notation.evaluation;


import jmul.math.notation.Comparators;
import jmul.math.notation.NotationProperties;
import jmul.math.notation.comparators.NumberComparator;
import jmul.math.notation.comparators.ScientificNotationEqualsComparator;
import jmul.math.notation.comparators.ScientificNotationGreaterThanComparator;
import jmul.math.notation.comparators.ScientificNotationLesserThanComparator;


/**
 * An implementation of an evaluator that evaluates expressions with two numbers of scientific notation.
 *
 * @author Kristian Kutin
 */
public class ScientificNotationScientificNotationEvaluator extends ExpressionEvaluatorBase {

    /**
     * A comparator.
     */
    private static final NumberComparator EQUALS_COMPARATOR = new ScientificNotationEqualsComparator();

    /**
     * A comparator.
     */
    private static final NumberComparator GREATER_THAN_COMPARATOR = new ScientificNotationGreaterThanComparator();

    /**
     * A comparator.
     */
    private static final NumberComparator LESSER_THAN_COMPARATOR = new ScientificNotationLesserThanComparator();

    /**
     * The default constructor.
     */
    public ScientificNotationScientificNotationEvaluator() {

        super();
    }

    /**
     * Evaluates the specified expression.
     *
     * @param firstNumber
     * @param firstProperties
     * @param aComparator
     * @param secondNumber
     * @param secondProperties
     *
     * @return <code>true</code> if the statement is true, else <code>false</code>
     */
    @Override
    public boolean evaluate(String firstNumber, NotationProperties firstProperties, Comparators aComparator,
                            String secondNumber, NotationProperties secondProperties) {

        NumberComparator comparator;

        switch (aComparator) {
        case EQUALS:
            comparator = EQUALS_COMPARATOR;
            break;
        case GREATER_THAN:
            comparator = GREATER_THAN_COMPARATOR;
            break;
        case LESSER_THAN:
            comparator = LESSER_THAN_COMPARATOR;
            break;
        default:
            String message = "Unknown comparator (" + aComparator + ")!";
            throw new UnsupportedOperationException(message);
        }

        return comparator.compare(firstNumber, firstProperties, secondNumber, secondProperties);
    }

}
