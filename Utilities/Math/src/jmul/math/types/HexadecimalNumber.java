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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.math.types;


import java.io.Serializable;

import java.nio.ByteOrder;
import static java.nio.ByteOrder.BIG_ENDIAN;
import static java.nio.ByteOrder.LITTLE_ENDIAN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import jmul.math.MathHelper;


/**
 * An implementation of a hexadecimal number (unsigned).
 *
 * @author Kristian Kutin
 */
public class HexadecimalNumber extends Number implements Comparable<HexadecimalNumber>, Serializable {

    /**
     * The serial UID which is required by java's serialization mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A constant message.
     */
    private static final String NOT_IMPLEMENTED_MESSAGE = "Not yet implemented!";

    /**
     * The max size of the byte array for the number type <code>byte</code>.
     */
    private static final int BYTE_TYPE_LENGTH = 1;

    /**
     * The max size of the byte array for the number type <code>short</code>.
     */
    private static final int SHORT_TYPE_LENGTH = 2;

    /**
     * The max size of the byte array for the number type <code>int</code>.
     */
    private static final int INTEGER_TYPE_LENGTH = 4;

    /**
     * The max size of the byte array for the number type <code>long</code>.
     */
    private static final int LONG_TYPE_LENGTH = 8;

    /**
     * The max size of the byte array for the number type <code>float</code>.
     */
    private static final int FLOAT_TYPE_LENGTH = 4;

    /**
     * The max size of the byte array for the number type <code>double</code>.
     */
    private static final int DOUBLE_TYPE_LENGTH = 8;

    /**
     * The base for hexadecimal numbers (<code>int</code>).
     */
    private static final int HEXADECIMAL_BASE_INT = 16;

    /**
     * The base for hexadecimal numbers (<code>byte</code>).
     */
    private static final byte HEXADECIMAL_BASE_BYTE = 16;

    /**
     * A base value for bytes.
     */
    private static final int BYTE_BASE = 256;

    /**
     * A constant valaue for calculating sizes in bits.
     */
    private static final int BITS_TO_BYTE_FACTOR = 8;

    /**
     * A regular pattern which is used to test inputs.
     */
    private static final String ALLOWED_STRING_PATTERN = "([0-9A-F][0-9A-F])+";

    /**
     * The constant represents a zero value.
     */
    private static final String ZERO_STRING = "00";

    /**
     * The string representation of the hexadecimal number.
     */
    private final String hexadecimalString;

    /**
     * A byte representation of the hexadecimal number.
     */
    private final List<Byte> bytes;

    /**
     * The byte order.
     */
    private final ByteOrder byteOrder;

    /**
     * Creates a new hexadecimal number (i.e. zero).
     */
    public HexadecimalNumber() {

        this(ByteOrder.nativeOrder());
    }

    /**
     * Creates a new hexadecimal number (i.e. zero).
     *
     * @param aByteOrder
     *        the byte order
     */
    public HexadecimalNumber(ByteOrder aByteOrder) {

        this(aByteOrder, ZERO_STRING);
    }

    /**
     * Creates a new hexadecimal number according to the specified parameters.
     *
     * @param aHexadecimalString
     *        a string representing a hexadecimal number
     */
    public HexadecimalNumber(String aHexadecimalString) {

        this(ByteOrder.nativeOrder(), aHexadecimalString);
    }

    /**
     * Creates a new hexadecimal number according to the specified parameters.
     *
     * @param aByteOrder
     *        the byte order
     * @param aHexadecimalString
     *        a string representing a hexadecimal number
     */
    public HexadecimalNumber(ByteOrder aByteOrder, String aHexadecimalString) {

        super();

        if (!Pattern.matches(ALLOWED_STRING_PATTERN, aHexadecimalString)) {

            String message = "The specified input \"" + aHexadecimalString + "\" is no valid hexadecimal number!";
            throw new IllegalArgumentException(message);
        }

        byteOrder = aByteOrder;
        hexadecimalString = aHexadecimalString;
        bytes = Collections.unmodifiableList(translateStringtoBytes(aHexadecimalString));
    }

    /**
     * Returns the value of the specified number as an <code>int</code>.
     * This may involve rounding or truncation.
     *
     * @return  the numeric value represented by this object after conversion
     *          to type <code>int</code>.
     */
    @Override
    public int intValue() {

        if (bytes() > INTEGER_TYPE_LENGTH) {

            String message = "The number " + hexadecimalString + " is too big to be converted to an integer type!";
            throw new IllegalArgumentException(message);
        }

        int exponent = 0;
        int total = 0;

        for (Byte b : translateLowestToHighest(getByteOrder(), getBytes())) {

            int i = byteToUnsignedInteger(b);

            total += MathHelper.eponentitation(BYTE_BASE, exponent) * i;
            exponent++;
        }

        return total;
    }

    /**
     * Returns the value of the specified number as a <code>long</code>.
     * This may involve rounding or truncation.
     *
     * @return  the numeric value represented by this object after conversion
     *          to type <code>long</code>.
     */
    @Override
    public long longValue() {

        throw new UnsupportedOperationException(NOT_IMPLEMENTED_MESSAGE);
    }

    /**
     * Returns the value of the specified number as a <code>float</code>.
     * This may involve rounding.
     *
     * @return  the numeric value represented by this object after conversion
     *          to type <code>float</code>.
     */
    @Override
    public float floatValue() {

        throw new UnsupportedOperationException(NOT_IMPLEMENTED_MESSAGE);
    }

    /**
     * Returns the value of the specified number as a <code>double</code>.
     * This may involve rounding.
     *
     * @return  the numeric value represented by this object after conversion
     *          to type <code>double</code>.
     */
    @Override
    public double doubleValue() {

        throw new UnsupportedOperationException(NOT_IMPLEMENTED_MESSAGE);
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o
     *        an object
     *
     * @return a number (see method description)
     */
    @Override
    public int compareTo(HexadecimalNumber o) {

        throw new UnsupportedOperationException(NOT_IMPLEMENTED_MESSAGE);
    }

    /**
     * Compares this object with the specified object for equality.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if the specified object and this object are considered
     *         equal, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        throw new UnsupportedOperationException(NOT_IMPLEMENTED_MESSAGE);
    }

    /**
     * Returns a hash code for this object.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        throw new UnsupportedOperationException(NOT_IMPLEMENTED_MESSAGE);
    }

    /**
     * Returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return hexadecimalString;
    }

    /**
     * Returns the byte order for this hexadecimal number.
     *
     * @return a byte order
     */
    public ByteOrder getByteOrder() {

        return byteOrder;
    }

    /**
     * Returns the byte representation for this hexadecimal number.
     *
     * @return a byte representation
     */
    public List<Byte> getBytes() {

        return bytes;
    }

    /**
     * Returns the size of this hexadecimal number in bytes.
     *
     * @return a size (bytes)
     */
    public int bytes() {

        return bytes.size();
    }

    /**
     * Returns the size of this hexadecimal number in bits.
     *
     * @return a size (bits)
     */
    public int bits() {

        return bytes() * BITS_TO_BYTE_FACTOR;
    }

    /**
     * Tranlsates the specified string to a byte array.
     *
     * @param aHexadecimalString
     *        a string representing a hexadecimal number
     *
     * @return a byte array
     */
    private static List<Byte> translateStringtoBytes(String aHexadecimalString) {

        List<Byte> result = new ArrayList<>();

        for (String byteString : splitStringIntoByteStrings(aHexadecimalString)) {

            result.add(translateByteString2Byte(byteString));
        }

        return result;
    }

    /**
     * Translates a byte string into a byte.
     *
     * @param aByteString
     *        a string representing a byte
     *
     * @return a byte
     */
    private static byte translateByteString2Byte(String aByteString) {

        byte first = (byte) Character.digit(aByteString.charAt(0), HEXADECIMAL_BASE_INT);
        byte second = (byte) Character.digit(aByteString.charAt(1), HEXADECIMAL_BASE_INT);

        byte result = first;
        result <<= 4;
        result += second;

        return result;
    }

    /**
     * Translates the specified signed byte to an 'unsigned' integer.
     *
     * @param b
     *        a number (see type)
     *
     * @return an 'unsigned' integer
     */
    private static int byteToUnsignedInteger(byte b) {

        return b & 0xFF;
    }

    /**
     * Splits the specified hexadecimal number into byte sized chunks.
     *
     * @param aHexadecimalString
     *        a string representing a hexadecimal number
     *
     * @return the hexadecimal number as byte sized chunks
     */
    private static List<String> splitStringIntoByteStrings(String aHexadecimalString) {

        List<String> tmp = new ArrayList<>();

        if ((aHexadecimalString == null) || aHexadecimalString.isEmpty()) {

            return tmp;
        }

        String head = aHexadecimalString.substring(0, 2);
        String tail = aHexadecimalString.substring(2);

        tmp.add(head);
        tmp.addAll(splitStringIntoByteStrings(tail));

        return tmp;
    }

    /**
     * The byte list is translated to lowest to highest byte order.
     *
     * @param aByteOrder
     *        the assumed byte order
     * @param someBytes
     *        a list of byte values which have to be processed
     *
     * @return a byte list
     */
    private static List<Byte> translateLowestToHighest(ByteOrder aByteOrder, List<Byte> someBytes) {

        if (BIG_ENDIAN.equals(aByteOrder)) {

            return reverseOrder(someBytes);

        } else if (LITTLE_ENDIAN.equals(aByteOrder)) {

            return someBytes;

        } else {

            String message = "Unsupported byte ordering \"" + aByteOrder + "\"!";
            throw new UnsupportedOperationException(message);
        }
    }

    /**
     * Reverses the order of the specified byte list.
     *
     * @param someBytes
     *        a list of byte values which have to be processed
     *
     * @return a list with reversed order
     */
    private static List<Byte> reverseOrder(List<Byte> someBytes) {

        List<Byte> result = new ArrayList<>();


        for (int a = someBytes.size() - 1; a >= 0; a--) {

            result.add(someBytes.get(a));
        }

        return result;
    }

}
