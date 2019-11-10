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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.checks;


import static jmul.checks.ParameterCheckHelper.checkListenerParameter;
import jmul.checks.exceptions.NullClassParameterException;
import jmul.checks.exceptions.NullListenerParameterException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests to check listener parameter check methods.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ListenerParameterTest {

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullListenerParameterException.class)
    public void testNullListenerParameter() {

        Object input = null;
        checkListenerParameter(input);
    }

    /**
     * Tests the check method with a <code>null</code> parameter.
     */
    @Test(expected = NullListenerParameterException.class)
    public void testNullListenerParameter2() {

        Object input1 = null;
        Class input2 = Object.class;
        checkListenerParameter(input2, input1);
    }

    /**
     * Tests the check method with no specified class (<code>null</code>).
     */
    @Test(expected = NullClassParameterException.class)
    public void testInvalidListenerParameter() {

        Object input1 = new Object();
        Class input2 = null;
        checkListenerParameter(input2, input1);
    }

    /**
     * Tests the check method with valid parameters.
     */
    @Test
    public void testValidListenerParameter() {

        Object input1 = new Object();
        Class input2 = Object.class;
        Object output = checkListenerParameter(input2, input1);
        assertEquals(input1, output);
    }

}
