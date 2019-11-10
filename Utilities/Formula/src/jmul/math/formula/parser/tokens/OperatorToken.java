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

package jmul.math.formula.parser.tokens;


import java.util.Set;

import jmul.math.formula.operations.Operator;


/**
 * The interface extends the interface Token.
 *
 * @author Kristian Kutin
 */
public interface OperatorToken extends Token {

    /**
     * The method returns all operators which have been identified with this
     * token.
     *
     * @return some operators
     */
    Set<Operator> getMatchingOperators();

    /**
     * The method will revome an operator and its pattern which is associated
     * with this token.
     *
     * @param anOperator
     *        an operator
     */
    void removeOperator(Operator anOperator);

    /**
     * The method will remove all operators except this operator.
     *
     * @param anOperator
     *        an operator
     */
    void retainOperator(Operator anOperator);

}
