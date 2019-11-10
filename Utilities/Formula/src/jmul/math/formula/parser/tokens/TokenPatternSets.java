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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.math.formula.parser.tokens;


import java.util.HashSet;
import java.util.Set;

import jmul.math.formula.operations.Operation;
import jmul.math.formula.parser.patterns.PatternFactory;
import jmul.math.formula.parser.patterns.TermTokenPatternImpl;
import jmul.math.formula.parser.patterns.TokenPattern;


/**
 * A utility class which provides token pattern sets.
 *
 * @author Kristian Kutin
 */
public final class TokenPatternSets {

    /**
     * The default constructor.
     */
    private TokenPatternSets() {

        throw new UnsupportedOperationException();
    }

    /**
     * A default token pattern set for math with integer numbers.
     *
     * @return a token pattern set
     */
    public static Set<TokenPattern> getDefaultMathTokenPatterns() {

        Set<TokenPattern> set = new HashSet<>();

        for (Operation operation : Operation.values()) {

            TokenPattern pattern = PatternFactory.newTokenPattern(operation.getOperator());
            set.add(pattern);
        }

        // Add operand pattern (variable) now.
        String regex = "^([a-zA-Z][a-zA-Z-0-9_]*)$";
        TokenPattern pattern = PatternFactory.newTokenPattern(regex, TokenType.OPERAND, TokenType.VARIABLE);
        set.add(pattern);

        // Add operand pattern (constant) now.
        regex = "^(([1-9][0-9]*)|0)$";
        pattern = PatternFactory.newTokenPattern(regex, TokenType.OPERAND, TokenType.CONSTANT);
        set.add(pattern);

        // Add operand pattern (term) now.
        pattern = new TermTokenPatternImpl();
        set.add(pattern);

        return set;
    }

}
