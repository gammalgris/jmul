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
 * An implementation of an operator (addition).
 *
 * @author Kristian Kutin
 */
public class DiceOperator extends BinaryOperatorBase implements Operator {

    /**
     * The class member contains the symbol for associated with the operator.
     */
    private static final String SYMBOL = "d";

    /**
     * The default constructor.
     */
    public DiceOperator() {

        super(SYMBOL, 7, Arity.BINARY, Position.BETWEEN_OPERANDS);
    }

    /**
     * The method performs the actual operation
     *
     * @param someOperands
     *        all required operands
     *
     * @return the operation result
     */
    @Override
    public int performOperation(int[] someOperands) {

        checkOperands(someOperands);

        int length = someOperands.length;
        int sum = 0;
        int leftOperand = someOperands[0];

        for (int a = 1; a < length; a++) {

            int rightOperand = someOperands[a];
            leftOperand = rollDice(leftOperand, rightOperand);

            sum += leftOperand;
        }

        return sum;
    }

    /**
     * The method rolls a certain number of dice and adds each result.
     *
     * @param dice
     *        the number of dice
     * @param sides
     *        the sides of each die
     *
     * @return the sum of all dice rolls
     */
    private int rollDice(int dice, int sides) {

        if (dice < 1) {

            String message = "At least one die has to be rolled!";
            throw new IllegalArgumentException(message);
        }

        int sum = 0;

        for (int a = 1; a <= dice; a++) {

            int result = rollDie(sides);
            sum += result;
        }

        return sum;
    }

    /**
     * The method rolls one die and return the result.
     *
     * @param sides
     *        the sides of the die
     *
     * @return the result of the roll
     */
    private int rollDie(int sides) {

        if (sides < 2) {

            String message = "A die must be at least two-sided!";
            throw new IllegalArgumentException(message);
        }

        double randomNumber = Math.random() * ((double) sides);
        return ((int) randomNumber) + 1;
    }

}
