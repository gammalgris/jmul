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

package test.jmul.persistence.legacy.threads;


/**
 * A utility class which provides convenience methods.
 *
 * @author Kristian Kutin
 */
public class ConcurrencyHelper {

    /**
     * The default constructor.
     */
    private ConcurrencyHelper() {
    }

    /**
     * A synchronized println.
     *
     * @param aString
     * the string which is to be printed.
     */
    public static void println(String aString) {

        String threadPrefix = Thread.currentThread().getName() + "> ";
        System.out.println(threadPrefix + aString);
    }

    /**
     * A println.
     *
     * @param anObject
     * the object whose string representation is to be printed.
     */
    public static void println(Object anObject) {

        println(String.valueOf(anObject));
    }

}
