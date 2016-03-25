/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.math;


/**
 * This class implements a counter.
 *
 * @author Kristian Kutin
 */
public final class Counter {

    /**
     * An initial value.
     */
    private static final int INITIAL_VALUE = 0;

    /**
     * The actual count.
     */
    private int count;

    /**
     * The default constructor.
     */
    public Counter() {

        count = INITIAL_VALUE;
    }

    /**
     * Increases the counter by 1.
     */
    public void increment() {

        count++;
    }

    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getCount() {

        return count;
    }

    /**
     * Resets the counter.
     */
    public void reset() {

        count = INITIAL_VALUE;
    }

    /**
     * Returns a string representation of this counter.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.valueOf(getCount());
    }

}
