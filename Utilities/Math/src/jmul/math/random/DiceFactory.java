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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.math.random;


import java.util.ArrayList;
import java.util.List;


/**
 * A factory class.
 *
 * @author Kristian Kutin
 */
public final class DiceFactory {

    /**
     * The default constructor.
     */
    private DiceFactory() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new die.
     *
     * @param sides
     *        the sides of a die
     *
     * @return a new die
     */
    public static Die createDie(int sides) {

        return new DieImpl(sides);
    }

    /**
     * Creates a new set of dice.
     *
     * @param someDice
     *        several dice
     *
     * @return a new set of dice
     */
    public static Dice createDice(Die... someDice) {

        return new DiceImpl(someDice);
    }

    /**
     * Creates a new set of dice.
     *
     * @param dice
     *        the number of dice
     * @param sides
     *        the sides of each die
     *
     * @return a new set of dice
     */
    public static Dice createDice(int dice, int sides) {

        List<Die> tmp = new ArrayList<>();
        for (int a = 0; a < dice; a++) {

            Die die = createDie(sides);
            tmp.add(die);
        }

        return createDice(tmp.toArray(new Die[] { }));
    }

}
