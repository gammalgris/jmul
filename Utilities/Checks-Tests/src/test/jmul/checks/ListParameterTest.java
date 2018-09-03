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


import java.util.ArrayList;
import java.util.List;

import static jmul.checks.ParameterCheckHelper.checkList;
import static jmul.checks.ParameterCheckHelper.checkStringListParameter;
import jmul.checks.exceptions.NullListParameterException;
import jmul.checks.exceptions.UnexpectedSizeException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests to check list parameter check methods.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ListParameterTest {

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullListParameterException.class)
    public void testNullParameter() {

        List input = null;
        checkList(input);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullListParameterException.class)
    public void testNullStringListParameter() {

        List<String> input = null;
        checkStringListParameter(input);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullListParameterException.class)
    public void testNullParameter2() {

        List input = null;
        int size = 0;
        checkList(input, size);
    }

    /**
     * Tests the check method with a <code>null</code> size parameter.
     */
    @Test(expected = NullPointerException.class)
    public void testNullSize() {

        List input = new ArrayList();
        Integer size = null;
        checkList(input, size);
    }

    /**
     * Tests the check method with a negative size parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSize() {

        List input = new ArrayList();
        int size = -1;
        checkList(input, size);
    }

    /**
     * Tests the check method with a too big list parameter.
     */
    @Test(expected = UnexpectedSizeException.class)
    public void testListTooBig() {

        List input = new ArrayList();
        input.add(new Object());

        int size = 0;
        checkList(input, size);
    }

    /**
     * Tests the check method with a too small list parameter.
     */
    @Test(expected = UnexpectedSizeException.class)
    public void testListTooSmall() {

        List input = new ArrayList();
        input.add(new Object());

        int size = 2;
        checkList(input, size);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidParameter() {

        List input = new ArrayList();
        List output = checkList(input);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidParameter2() {

        List input = new ArrayList();
        int size = 0;
        List output = checkList(input, size);
        assertEquals(input, output);
    }

    /**
     * Tests the check method with a valid parameter.
     */
    @Test
    public void testValidStringListParameter() {

        List<String> input = new ArrayList<>();
        List<String> output = checkList(input);
        assertEquals(input, output);
    }

}
