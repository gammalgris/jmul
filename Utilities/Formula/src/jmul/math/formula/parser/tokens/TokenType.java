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

package jmul.math.formula.parser.tokens;


/**
 * The enumeration lists all types of tokens.
 * 
 * TODO move unary, binary and ternary to token properties. These should not be token types.
 * TODO the distinction between operand and operator isn't clear.
 *
 * @author Kristian Kutin
 */
public enum TokenType {


    OPERAND("operand"),
    OPERATOR("operator"),

    TERM("term"),
    VARIABLE("variable"),
    CONSTANT("constant"),

    UNARY("unary"),
    BINARY("binary"),
    TERNARY("ternary"), ;


    /**
     * The class member contains a description.
     */
    private final String description;

    /**
     * The default constructor.
     */
    private TokenType(String aDescription) {

        description = aDescription;
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return description;
    }

}
