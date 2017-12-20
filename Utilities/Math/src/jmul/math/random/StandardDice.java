/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package jmul.math.random;


import static jmul.math.random.DiceFactory.createDie;


/**
 * This enumeration contains several standard dice.
 *
 * @author Kristian Kutin
 */
public enum StandardDice implements Die {


    D4(createDie(4)),
    D6(createDie(6)),
    D8(createDie(8)),
    D10(createDie(10)),
    D12(createDie(12)),
    D20(createDie(20)),
    D100(createDie(100)), ;


    /**
     * A die.
     */
    private final Die die;

    /**
     * Creates an enumeration element.
     *
     * @param aDie
     */
    private StandardDice(Die aDie) {

        die = aDie;
    }

    /**
     * Performs a die roll.
     *
     * @return the result of a die roll
     */
    @Override
    public int roll() {

        return die.roll();
    }

    /**
     * A getter method.
     *
     * @return the sides of this die
     */
    @Override
    public int getSides() {

        return die.getSides();
    }

    /**
     * Returns a string representation of this die.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.valueOf(die);
    }

}
