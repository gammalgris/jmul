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


import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * The enumeration contains all available operators. Some operators may have
 * same symbols.
 *
 * @author Kristian Kutin
 */
public enum Operation {


    ADDITION(new Addition()),
    SUBTRACTION(new Subtraction()),
    MULTIPLICATION(new Multiplication()),
    DIVISION(new Division()),
    DICE_OPERATOR(new DiceOperator()),
    POSITIVE_SIGN(new PositiveSign()),
    NEGATIVE_SIGN(new NegativeSign()), ;


    /**
     * The class member contains an operator.
     */
    private final Operator operator;

    /**
     * The default constructor.
     *
     * @param anOperator
     *        an operator
     */
    private Operation(Operator anOperator) {

        operator = anOperator;
    }

    /**
     * The method returns an operator.
     *
     * @return an operator
     */
    public Operator getOperator() {

        return operator;
    }

    /**
     * The method checks if a string matches the symbol of this operation.
     *
     * @param aString
     *        a string
     *
     * @return true, if the symbol matches the symbol of this operation, else
     *         false
     */
    public boolean equalsString(String aString) {

        return operator.getSymbol().equals(aString);
    }

    /**
     * The method returns the operator which is associated with a given symbol.
     * In general a certain symbol will be associated with only one operator.
     * But there exist exceptions (e.g. minus operator and the minus sign).
     *
     * @param aSymbol
     *        a symbol
     *
     * @return all operations with this symbol
     */
    public static Collection<Operator> getOperators(String aSymbol) {

        Collection<Operator> matchingOperators = new ArrayList<Operator>();

        for (Operation operation : values()) {

            if (operation.equalsString(aSymbol)) {
                matchingOperators.add(operation.getOperator());
            }
        }

        if (matchingOperators.size() == 0) {

            String message = "Unknown operator \"" + aSymbol + "\"!";
            throw new IllegalArgumentException(message);
        }

        return matchingOperators;
    }

    /**
     * The method tests if a string contains an operator.
     *
     * @param aSymbol
     *        a symbol
     *
     * @return true, if the string contains an operator, else false
     */
    public static boolean isOperator(String aSymbol) {

        for (Operation operation : values()) {

            if (operation.equalsString(aSymbol)) {
                return true;
            }
        }

        return false;
    }

}
