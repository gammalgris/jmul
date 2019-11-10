/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.string;


import jmul.string.StringConcatenator;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests the inherited/ implemented methods of a string concatenator.
 *
 * @author Kristian Kutin
 */
public class StringConcatenatorTest {

    /**
     * The char sequence which is tested.
     */
    private CharSequence charSequence;

    /**
     *  Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        charSequence = new StringConcatenator(1, 2, 3);
    }

    /**
     *  Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        charSequence = null;
    }

    /**
     * Tests the length method.
     */
    @Test
    public void testLength() {

        assertEquals(3, charSequence.length());
    }

    /**
     * Tests the charAt method.
     */
    @Test
    public void testCharAt() {

        assertEquals('1', charSequence.charAt(0));
    }

    /**
     * Tests the charAt method.
     */
    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testCharAtInvalidIndex() {

        charSequence.charAt(3);
    }

    /**
     * Tests the charAt method.
     */
    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testCharAtNegativeIndex() {

        charSequence.charAt(-1);
    }

    /**
     * Tests the subSequence method.
     */
    @Test
    public void testSubSequence() {

        assertEquals("1", charSequence.subSequence(0, 1));
    }

    /**
     * Tests the subSequence method.
     */
    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testSubSequenceInvalidLength() {

        charSequence.subSequence(0, 4);
    }

    /**
     * Tests the subSequence method.
     */
    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testSubSequenceInvalidIndex() {

        charSequence.subSequence(3, 1);
    }

    /**
     * Tests the subSequence method.
     */
    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testSubSequenceNegativeIndex() {

        charSequence.subSequence(-1, 1);
    }

}
