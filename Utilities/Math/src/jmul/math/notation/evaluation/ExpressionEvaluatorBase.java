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
import jmul.math.notation.NotationHelper;
import jmul.math.notation.NotationProperties;


/**
 * A base implementation of an evaluator.
 *
 * @author Kristian Kutin
 */
abstract class ExpressionEvaluatorBase implements ExpressionEvaluator {

    /**
     * The default constructor.
     */
    public ExpressionEvaluatorBase() {

        super();
    }

    /**
     * Evaluates the specified expression.
     *
     * @param firstNumber
     *        the first number as string
     * @param aComparator
     *        a comparator to compare both numbers
     * @param secondNumber
     *        the second number as string
     *
     * @return <code>true</code> if the statement is true, else <code>false</code>
     */
    @Override
    public boolean evaluate(String firstNumber, Comparators aComparator, String secondNumber) {

        NotationProperties firstProperties = NotationHelper.checkString(firstNumber);
        NotationProperties secondProperties = NotationHelper.checkString(secondNumber);

        return evaluate(firstNumber, firstProperties, aComparator, secondNumber, secondProperties);
    }

}
