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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * An implementation of a set of dice.
 *
 * @author Kristian Kutin
 */
public class DiceImpl implements Dice {

    /**
     * The container for all dice (sorted by sides).
     */
    private final Map<Integer, Collection<Die>> diceMap;

    /**
     * Creates a new set of dice.
     *
     * @param someDice
     *        several dice
     */
    public DiceImpl(Die... someDice) {

        SortedMap<Integer, Collection<Die>> map = new TreeMap<>();

        for (Die die : someDice) {

            int sides = die.getSides();

            Collection<Die> collection = null;

            if (map.containsKey(sides)) {

                collection = map.get(sides);

            } else {

                collection = new ArrayList<>();
                map.put(sides, collection);
            }

            collection.add(die);
        }


        diceMap = Collections.unmodifiableMap(map);
    }

    /**
     * Rolls all dice.
     *
     * @return the result of all die rolls
     */
    @Override
    public int roll() {

        int total = 0;

        for (Die die : this) {

            total += die.roll();
        }

        return total;
    }

    /**
     * Returns an iterator for this set of dice.
     *
     * @return an iterator
     */
    @Override
    public Iterator<Die> iterator() {

        Collection<Die> collection = new ArrayList<>();

        for (Collection<Die> subset : diceMap.values()) {

            collection.addAll(subset);
        }

        return collection.iterator();
    }

    /**
     * Returns a string representation of set of dice.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        boolean isFirstElement = true;
        for (Collection<Die> subset : diceMap.values()) {

            if (isFirstElement) {

                isFirstElement = false;

            } else {

                buffer.append(" + ");
            }

            Die die = subset.iterator().next();
            int dice = subset.size();

            buffer.append(dice);
            buffer.append(die);
        }

        return String.valueOf(buffer);
    }

}
