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

package test.jmul.math.hash;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmul.math.hash.HashHelper;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;


/**
 *
 */
@UnitTest
public class HashCodeCollisionTest {

    private List<Integer> twoPrimeNumbers;

    private Map<String, Integer> hashCodes;

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        twoPrimeNumbers = HashHelper.determineTwoPrimeNumbers();
        hashCodes = new HashMap<String, Integer>();
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        twoPrimeNumbers = null;
        hashCodes = null;
    }

    /**
     * Tests generating hash codes in order to detect hash code collisions.
     */
    @Test
    public void testHashCodeCollision() {

        for (Byte a = Byte.MIN_VALUE; a < Byte.MAX_VALUE; a++) {

            String name = byte2String(a);
            int hash = HashHelper.calculateHashCode(twoPrimeNumbers, a);
            addHashCode(name, hash);
        }

        {
            Byte a = Byte.MAX_VALUE;
            String name = byte2String(a);
            int hash = HashHelper.calculateHashCode(twoPrimeNumbers, a);
            addHashCode(name, hash);
        }
    }

    /**
     * Tests generating hash codes in order to detect hash code collisions.
     */
    @Test
    public void testHashCodeCollision2() {

        for (Byte a = Byte.MIN_VALUE; a < Byte.MAX_VALUE; a++) {
            for (Byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {

                String name = byte2String(a, b);
                int hash = HashHelper.calculateHashCode(twoPrimeNumbers, a);
                addHashCode(name, hash);
            }
        }

        {
            Byte a = Byte.MAX_VALUE;
            Byte b = Byte.MAX_VALUE;
            String name = byte2String(a, b);
            int hash = HashHelper.calculateHashCode(twoPrimeNumbers, a);
            addHashCode(name, hash);
        }
    }

    /**
     * Adds the hash code to a container. The container is used to detect
     * hash code collisions.
     *
     * @param aRepresentation
     * @param aHashCode
     */
    private void addHashCode(String aRepresentation, int aHashCode) {

        {
            String message = "The representation already exists (" + aRepresentation + ")!";
            assertFalse(message, hashCodes.containsKey(aRepresentation));
        }

        {
            String message = "The hash code already exists (" + aRepresentation + "=" + aHashCode + ")!";
            assertFalse(message, hashCodes.containsValue(aHashCode));
        }

        hashCodes.put(aRepresentation, aHashCode);
    }

    /**
     * Returns a string representation for the specified parameters.
     *
     * @param someBytes
     *
     * @return a string representation
     */
    private String byte2String(byte... someBytes) {

        StringBuilder buffer = new StringBuilder();

        for (byte a : someBytes) {

            buffer.append(a);
        }

        return buffer.toString();
    }
}
