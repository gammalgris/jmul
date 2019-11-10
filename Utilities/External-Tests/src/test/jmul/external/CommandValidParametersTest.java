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

package test.jmul.external;


import jmul.external.Command;
import jmul.external.CommandImpl;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests for instantiating commands with valid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CommandValidParametersTest {

    /**
     * Tests instantiation of a command without parameters.
     */
    @Test
    public void testCommandWithoutParameters() {

        Command command = new CommandImpl("test");

        assertEquals("test", command.getCommand());

        assertEquals(0, command.getParameters().length);

        assertEquals(1, command.toArray().length);
        assertEquals("test", command.toArray()[0]);

        assertEquals("test", command.toString());
    }

    /**
     * Tests instantiation of a command with two parameters.
     */
    @Test
    public void testCommandWithParameters() {

        Command command = new CommandImpl("test", "a", "b");

        assertEquals("test", command.getCommand());

        assertEquals(2, command.getParameters().length);
        assertEquals("a", command.getParameters()[0]);
        assertEquals("b", command.getParameters()[1]);

        assertEquals(3, command.toArray().length);
        assertEquals("test", command.toArray()[0]);
        assertEquals("a", command.toArray()[1]);
        assertEquals("b", command.toArray()[2]);

        assertEquals("test a b", command.toString());
    }

}
