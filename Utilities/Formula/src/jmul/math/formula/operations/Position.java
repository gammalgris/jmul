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

package jmul.math.formula.operations;


/**
 * The enumeration lists different positions of operators. The possible
 * positions of operators depend to a certain extent on its arity.
 * An unary operator might be positioned in front of an operand or after an
 * operand. A binary operator is always positioned between operands.
 *
 * @author Kristian Kutin
 */
public enum Position {


    IN_FRONT_OF_OPERAND("in fron of operand"),
    BETWEEN_OPERANDS("between operands"),
    AFTER_OPERAND("after operand");


    /**
     * The class member contains a description.
     */
    private final String description;

    /**
     * The default constructor.
     *
     * @param aDescription
     */
    private Position(String aDescription) {

        description = aDescription;
    }

    /**
     * The overridden toString-method
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return description;
    }

}
