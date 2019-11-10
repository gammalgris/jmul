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

package test.jmul.math.types;


import static java.nio.ByteOrder.BIG_ENDIAN;
import static java.nio.ByteOrder.LITTLE_ENDIAN;

import jmul.math.types.HexadecimalNumber;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * The class contains tests to test the default constructor.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class HexadecimalNumberDefaultConstructorTest {

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Tests if the default constructor correctly creates a hexadecimal number.
     */
    @Test
    public void testDefaultConstructor() {

        HexadecimalNumber h = new HexadecimalNumber();
        assertEquals(0, h.intValue());
    }

    /**
     * Tests if the default constructor correctly creates a hexadecimal number.
     */
    @Test
    public void testDefaultConstructor2() {

        HexadecimalNumber h = new HexadecimalNumber(BIG_ENDIAN);
        assertEquals(0, h.intValue());
    }

    /**
     * Tests if the default constructor correctly creates a hexadecimal number.
     */
    @Test
    public void testDefaultConstructor3() {

        HexadecimalNumber h = new HexadecimalNumber(LITTLE_ENDIAN);
        assertEquals(0, h.intValue());
    }

}
