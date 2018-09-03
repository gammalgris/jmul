/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package test.jmul.checks;


import static jmul.checks.ParameterCheckHelper.checkIndex;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests to check index parameter check methods.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class IndexParameterTest {

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullPointerException.class)
    public void testNullIndex() {

        int min = 0;
        Integer index = null;
        int max = 10;
        checkIndex(min, index, max);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullPointerException.class)
    public void testNullMinBoundary() {

        Integer min = null;
        int index = 1;
        int max = 10;
        checkIndex(min, index, max);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullPointerException.class)
    public void testNullMaxBoundary() {

        int min = 0;
        int index = 1;
        Integer max = null;
        checkIndex(min, index, max);
    }

    /**
     * Tests the check method with a parameter that is too small.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testTooSmallIndex() {

        int min = 0;
        int index = -1;
        int max = 10;
        checkIndex(min, index, max);
    }

    /**
     * Tests the check method with a parameter that is too small.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testTooSmallIndex2() {

        int min = -10;
        int index = -11;
        int max = 0;
        checkIndex(min, index, max);
    }

    /**
     * Tests the check method with a parameter that is too big.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testTooBigIndex() {

        int min = 0;
        int index = 11;
        int max = 10;
        checkIndex(min, index, max);
    }

    /**
     * Tests the check method with a parameter that is too big.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testTooBigIndex2() {

        int min = -10;
        int index = 1;
        int max = 0;
        checkIndex(min, index, max);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidIndex() {

        int min = 0;
        int index = 1;
        int max = 10;
        int output = checkIndex(min, index, max);
        assertEquals(index, output);
    }

}
