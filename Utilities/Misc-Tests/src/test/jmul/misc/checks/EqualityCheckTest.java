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

package test.jmul.misc.checks;


import jmul.misc.checks.EqualityHelper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This class contains tests to check the eqality helper.
 *
 * @author Kristian Kutin
 */
public class EqualityCheckTest {

    /**
     * The test checks two different objects with the same value.
     */
    @Test
    public void testEqualObjects() {

        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);

        assertFalse(i1 == i2);
        assertTrue(EqualityHelper.equalObjects(i1, i2));
    }

    /**
     * The test checks two null values.
     */
    @Test
    public void testNull() {

        Integer i1 = null;
        Integer i2 = null;

        assertTrue(i1 == i2);
        assertTrue(EqualityHelper.equalObjects(i1, i2));
    }

    /**
     * The test checks two objects of which the first one is <code>null</code>.
     */
    @Test
    public void testFirstNull() {

        Integer i1 = null;
        Integer i2 = new Integer(1);

        assertFalse(i1 == i2);
        assertFalse(EqualityHelper.equalObjects(i1, i2));
    }

    /**
     * The test checks two objects of which the second one is <code>null</code>.
     */
    @Test
    public void testSecondNull() {

        String s1 = null;
        String s2 = "Hello";

        assertFalse(s1 == s2);
        assertFalse(EqualityHelper.equalObjects(s1, s2));
    }

}
