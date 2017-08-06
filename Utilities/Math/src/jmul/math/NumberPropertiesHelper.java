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

package jmul.math;


import static jmul.math.Constants.getEpsilon;
import static jmul.math.Constants.getZero;

import jmul.misc.exceptions.NullNumberException;


/**
 * A utility class to check various properties of numbers. See chapters
 * <a href="https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html">Primitive Data Types</a>
 * and <a href="https://docs.oracle.com/javase/tutorial/java/data/numberclasses.html">The Number Classes</a>
 * for details about supported numerical types and their corresponding wrapper classes.
 *
 * @author Kristian Kutin
 */
public final class NumberPropertiesHelper {

    /**
     * The default constructor.
     */
    private NumberPropertiesHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param b
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(byte b) {

        return b % 2 != 0;
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param b
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(Byte b) {

        if (b == null) {

            throw new NullNumberException();
        }

        return isOdd(b.byteValue());
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param s
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(short s) {

        return s % 2 != 0;
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param s
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(Short s) {

        if (s == null) {

            throw new NullNumberException();
        }

        return isOdd(s.shortValue());
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(int i) {

        return i % 2 != 0;
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(Integer i) {

        if (i == null) {

            throw new NullNumberException();
        }

        return isOdd(i.intValue());
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param l
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(long l) {

        return l % 2L != 0L;
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(Long l) {

        if (l == null) {

            throw new NullNumberException();
        }

        return isOdd(l.longValue());
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param f
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(float f) {

        return f % 2F != 0F;
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param f
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(Float f) {

        if (f == null) {

            throw new NullNumberException();
        }

        return isOdd(f.floatValue());
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param d
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(double d) {

        return d % 2D != 0D;
    }

    /**
     * Checks if the specified number is odd.
     *
     * @param d
     *
     * @return <code>true</code> if the specified number is odd,
     *         else <code>false</code>
     */
    public static boolean isOdd(Double d) {

        if (d == null) {

            throw new NullNumberException();
        }

        return isOdd(d.doubleValue());
    }

    /**
     * Checks if the specified number is even.
     *
     * @param b
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(byte b) {

        return b % 2 == 0;
    }

    /**
     * Checks if the specified number is even.
     *
     * @param b
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(Byte b) {

        if (b == null) {

            throw new NullNumberException();
        }

        return isEven(b.byteValue());
    }

    /**
     * Checks if the specified number is even.
     *
     * @param s
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(short s) {

        return s % 2 == 0;
    }

    /**
     * Checks if the specified number is even.
     *
     * @param s
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(Short s) {

        if (s == null) {

            throw new NullNumberException();
        }

        return isEven(s.shortValue());
    }

    /**
     * Checks if the specified number is even.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(int i) {

        return i % 2 == 0;
    }

    /**
     * Checks if the specified number is even.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(Integer i) {

        if (i == null) {

            throw new NullNumberException();
        }

        return isEven(i.intValue());
    }

    /**
     * Checks if the specified number is even.
     *
     * @param l
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(long l) {

        return l % 2L == 0L;
    }

    /**
     * Checks if the specified number is even.
     *
     * @param l
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(Long l) {

        if (l == null) {

            throw new NullNumberException();
        }

        return isEven(l.longValue());
    }

    /**
     * Checks if the specified number is even.
     *
     * @param f
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(float f) {

        return f % 2F == 0F;
    }

    /**
     * Checks if the specified number is even.
     *
     * @param f
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(Float f) {

        if (f == null) {

            throw new NullNumberException();
        }

        return isEven(f.floatValue());
    }

    /**
     * Checks if the specified number is even.
     *
     * @param d
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(double d) {

        return d % 2D == 0D;
    }

    /**
     * Checks if the specified number is even.
     *
     * @param d
     *
     * @return <code>true</code> if the specified number is even,
     *         else <code>false</code>
     */
    public static boolean isEven(Double d) {

        if (d == null) {

            throw new NullNumberException();
        }

        return isEven(d.doubleValue());
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param b
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(byte b) {

        return b > getZero(Byte.TYPE).byteValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param b
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(Byte b) {

        return b > getZero(Byte.class).byteValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param s
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(short s) {

        return s > getZero(Byte.TYPE).shortValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param s
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(Short s) {

        return s > getZero(Byte.class).shortValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(int i) {

        return i > getZero(Integer.TYPE).intValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(Integer i) {

        return i > getZero(Integer.class).intValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param l
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(long l) {

        return l > getZero(Long.TYPE).longValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param l
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(Long l) {

        return l > getZero(Long.class).longValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param f
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(float f) {

        return f > getZero(Float.TYPE).floatValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param f
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(Float f) {

        return f > getZero(Float.class).floatValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param d
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(double d) {

        return d > getZero(Double.TYPE).doubleValue();
    }

    /**
     * Checks if the specified number is a positive number.
     *
     * @param d
     *
     * @return <code>true</code> if the specified number is positive,
     *         else <code>false</code>
     */
    public static boolean isPositive(Double d) {

        return d > getZero(Double.class).doubleValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param b
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(byte b) {

        return b > getZero(Byte.TYPE).byteValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param b
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(Byte b) {

        return b > getZero(Byte.class).byteValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param s
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(short s) {

        return s < getZero(Short.TYPE).shortValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param s
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(Short s) {

        return s < getZero(Short.class).shortValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(int i) {

        return i < getZero(Integer.TYPE).intValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(Integer i) {

        return i < getZero(Integer.class).intValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param l
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(long l) {

        return l < getZero(Long.TYPE).longValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param l
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(Long l) {

        return l < getZero(Long.class).longValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param f
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(float f) {

        return f < getZero(Float.TYPE).floatValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param f
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(Float f) {

        return f < getZero(Float.class).floatValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param d
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(double d) {

        return d < getZero(Double.TYPE).doubleValue();
    }

    /**
     * Checks if the specified number is a negative number.
     *
     * @param d
     *
     * @return <code>true</code> if the specified number is negative,
     *         else <code>false</code>
     */
    public static boolean isNegative(Double d) {

        return d < getZero(Double.class).doubleValue();
    }

    /**
     * Checks if the specified number is equal to zero. As the corresponding
     * number type doesn't have a fractional component only a simple
     * equality check with zero is made.
     *
     * @param b
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(byte b) {

        return b == getZero(Byte.TYPE).byteValue();
    }

    /**
     * Checks if the specified number is equal to zero. As the corresponding
     * number type doesn't have a fractional component only a simple
     * equality check with zero is made.
     *
     * @param b
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(Byte b) {

        return b == getZero(Byte.class).byteValue();
    }

    /**
     * Checks if the specified number is equal to zero. As the corresponding
     * number type doesn't have a fractional component only a simple
     * equality check with zero is made.
     *
     * @param s
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(short s) {

        return s == getZero(Short.TYPE).shortValue();
    }

    /**
     * Checks if the specified number is equal to zero. As the corresponding
     * number type doesn't have a fractional component only a simple
     * equality check with zero is made.
     *
     * @param s
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(Short s) {

        return s == getZero(Short.class).shortValue();
    }

    /**
     * Checks if the specified number is equal to zero. As the corresponding
     * number type doesn't have a fractional component only a simple
     * equality check with zero is made.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(int i) {

        return i == getZero(Integer.TYPE).intValue();
    }

    /**
     * Checks if the specified number is equal to zero. As the corresponding
     * number type doesn't have a fractional component only a simple
     * equality check with zero is made.
     *
     * @param i
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(Integer i) {

        return i == getZero(Integer.class).intValue();
    }

    /**
     * Checks if the specified number is equal to zero. As the corresponding
     * number type doesn't have a fractional component only a simple
     * equality check with zero is made.
     *
     * @param l
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(long l) {

        return l == getZero(Integer.TYPE).longValue();
    }

    /**
     * Checks if the specified number is equal to zero. As the corresponding
     * number type doesn't have a fractional component only a simple
     * equality check with zero is made.
     *
     * @param l
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(Long l) {

        return l == getZero(Long.class).longValue();
    }

    /**
     * Checks if the specified number is equal to zero. As the specified
     * number type has a fractional component a check to determine if the
     * specified value is within a certain range around zero is made.
     *
     * @param f
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(float f) {

        float epsilon = getEpsilon(Float.TYPE).floatValue();
        return (f < epsilon) && (f > -epsilon);
    }

    /**
     * Checks if the specified number is equal to zero. As the specified
     * number type has a fractional component a check to determine if the
     * specified value is within a certain range around zero is made.
     *
     * @param f
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(Float f) {

        float epsilon = getEpsilon(Float.class).floatValue();
        return (f < epsilon) && (f > -epsilon);
    }

    /**
     * Checks if the specified number is equal to zero. As the specified
     * number type has a fractional component a check to determine if the
     * specified value is within a certain range around zero is made.
     *
     * @param d
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(double d) {

        double epsilon = getEpsilon(Double.TYPE).doubleValue();
        return (d < epsilon) && (d > -epsilon);
    }

    /**
     * Checks if the specified number is equal to zero. As the specified
     * number type has a fractional component a check to determine if the
     * specified value is within a certain range around zero is made.
     *
     * @param d
     *
     * @return <code>true</code> if the specified number is equal to zero,
     *         else <code>false</code>
     */
    public static boolean isZero(Double d) {

        double epsilon = getEpsilon(Double.TYPE).doubleValue();
        return (d < epsilon) && (d > -epsilon);
    }

}
