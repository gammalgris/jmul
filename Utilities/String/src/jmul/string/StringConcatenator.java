/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.string;


/**
 * This helper class concatenates strings.<br>
 * <br>
 * <br>
 * <i>Note:<br>
 * This implementation is not as efficient as using <code>StringBuilder</code>
 * directly.<br>
 * <br>
 * <code>{@link StringBuilder}</code> offers append methods to build a string,
 * which is very efficient. The drawback is that this results in long lines of
 * concatenated append statements because not every IDE can handle this in a
 * human readable way when auto formatting the source code.<br>
 * <br>
 * This class offers a convenient way to use the StringBuilder class. The constructor
 * and methods accept an object array as input.</i>
 *
 * @author Kristian Kutin
 */
public final class StringConcatenator implements CharSequence {

    /**
     * The class member contains the actual string.
     */
    private StringBuilder string;

    /**
     * Constructs a string according to the specified elements. The elements are
     * concatenated according their specified order.
     *
     * @param elements
     *        all elements which are to be concatenated
     */
    public StringConcatenator(Object... elements) {

        checkParameter(elements);

        string = new StringBuilder();

        for (Object element : elements) {

            string.append(String.valueOf(element));
        }
    }

    /**
     * Checks the specified Parameter.
     *
     * @param aParameter
     *
     * @throws IllegalArgumentException
     *         is thrown if the parameter has an invalid value
     */
    private static void checkParameter(Object[] aParameter) {

        if (aParameter == null) {

            String message = "No argument (null) has been specified!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * The method appends more elements to the string.
     *
     * @param elements
     *        all elements which are to be concatenated
     */
    public void append(Object... elements) {

        checkParameter(elements);

        for (Object element : elements) {

            string.append(String.valueOf(element));
        }
    }

    /**
     * The method returns a string representation of this entity.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return string.toString();
    }

    /**
     * The method returns the length of this string.
     *
     * @return the current string length
     */
    @Override
    public int length() {

        return string.length();
    }

    /**
     * The method returns the character at the specified position within this string.
     *
     * @param index
     *
     * @return a character
     */
    @Override
    public char charAt(int index) {

        return string.charAt(index);
    }

    /**
     * The method returns a substring at the specified location within this string.
     *
     * @param start
     * @param end
     *
     * @return a substring
     */
    @Override
    public CharSequence subSequence(int start, int end) {

        return string.subSequence(start, end);
    }

}
