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

package test.jmul.external;


import jmul.external.CommandImpl;

import jmul.test.classification.UnitTest;

import org.junit.Test;


/**
 * This class contains tests for instantiating commands with invalid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CommandInvalidParametersTest {

    /**
     * Tests instantiation of a command with a <code>null</code> parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter() {

        String[] input = null;
        new CommandImpl(input);
    }

    /**
     * Tests instantiation of a command with an empty string array parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyParameter() {

        String[] input = new String[] { };
        new CommandImpl(input);
    }

}
