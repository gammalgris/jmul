/*
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

package jmul.math.types;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jmul.misc.checks.EqualityHelper;


/**
 * An implementation of a tuple.
 *
 * @param <T>
 */
public class TupleImpl<T> implements Tuple<T> {

    /**
     * All elements of this tuple.
     */
    private final List<T> elements;

    /**
     * The default constructor.
     */
    public TupleImpl() {

        elements = Collections.unmodifiableList(new ArrayList<T>());
    }

    /**
     * Creates a tuple according to the specified parameters.
     *
     * @param someElements
     */
    @SafeVarargs
    public TupleImpl(T... someElements) {

        elements = Collections.unmodifiableList(Arrays.asList(someElements));
    }

    /**
     * Returns the element with the specified index.
     *
     * @param anIndex
     *
     * @return an element
     */
    @Override
    public T get(int anIndex) {

        return elements.get(anIndex);
    }

    /**
     * The number of elements within this tuple.
     *
     * @return an element count
     */
    @Override
    public int size() {

        return elements.size();
    }

    /**
     * Checks if the specified object is equal to this object.
     *
     * @param o
     *
     * @return <code>true</code> if the objects are equal,
     *         else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;

        } else if (this == o) {

            return true;

        } else if (o instanceof Tuple) {

            Tuple other = (Tuple) o;

            int length = this.size();
            if (length == other.size()) {

                for (int a = 0; a < length; a++) {

                    Object o1 = this.get(a);
                    Object o2 = other.get(a);

                    if (!EqualityHelper.equalObjects(o1, o2)) {

                        return false;
                    }
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        return elements.hashCode();
    }

    /**
     * Returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return elements.toString();
    }

}
