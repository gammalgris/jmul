/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

package jmul.math.formula;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * An implementation of an operand.
 *
 * @author Kristian Kutin
 */
public class Constant implements Operand {

    /**
     * The class member contains a numerical value.
     */
    private final int value;

    /**
     * The default constructor.
     */
    public Constant() {

        this(0);
    }

    /**
     * An alternative constructor.
     *
     * @param aValue
     *        an initial value
     */
    public Constant(int aValue) {

        value = aValue;
    }

    /**
     * The method returns the numerical value of the operand.
     *
     * @return a numerical value
     */
    @Override
    public int getValue() {

        return value;
    }

    /**
     * The method performs an update operation on the operand.
     */
    public void update() {

        // no update is required in this case
    }

    /**
     * The method returns a string representation of this entity.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder representation = new StringBuilder();

        int value = getValue();
        if (value < 0) {

            representation.append("(");
            representation.append(value);
            representation.append(")");

        } else {

            representation.append(value);
        }

        return representation.toString();
    }

    /**
     * The overridden equals-method.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if this and the specified object represent the
     *         same value, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof Operand) {

            Operand other = (Operand) o;

            EqualsBuilder builder = new EqualsBuilder();

            builder.append(this.getValue(), other.getValue());

            return builder.isEquals();
        }

        return false;
    }

    /**
     * The overridden hashCode-method.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getValue());

        return builder.toHashCode();
    }

}
