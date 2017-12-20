/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.misc.checks;


/**
 * A utility class with operations to determine object equality.
 *
 * @author Kristian Kutin
 */
public final class EqualityHelper {

    /**
     * The default constructor.
     */
    private EqualityHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks the equality of the specified objects.
     *
     * @param o1
     *        an object
     * @param o2
     *        an object
     *
     * @return <code>true</code> if both objects are either <code>null</code> or
     *         equal according to the result of the <code>equals</code> method.
     *         Otherwise <code>false</code> is returned.
     */
    public static boolean equalObjects(Object o1, Object o2) {

        if ((o1 == null) && (o2 == null)) {

            return true;

        } else if ((o1 == null) || (o2 == null)) {

            return false;

        } else {

            return o1.equals(o2);
        }
    }

}
