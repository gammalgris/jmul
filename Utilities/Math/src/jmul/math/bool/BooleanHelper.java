/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package jmul.math.bool;


/**
 * A utility class which provides certain boolean operations and functions.
 *
 * @author Kristian Kutin
 */
public final class BooleanHelper {

    /**
     * The default constructor.
     */
    private BooleanHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Implements the exclusive OR (see <a href="https://en.wikipedia.org/wiki/XOR_gate" target=_blank>XOR</a>).
     *
     * @param b1
     * @param b2
     *
     * @return a boolean value, see description.
     */
    public static boolean xor(boolean b1, boolean b2) {

        if (b1 && !b2) {

            return true;

        } else if (!b1 && b2) {

            return true;
        }

        return false;
    }

}
