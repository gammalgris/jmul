/*
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

package test.jmul.string;


import jmul.string.ConfigurableMessage;
import jmul.string.Message;
import jmul.string.UnknownPlaceholderException;
import jmul.string.UnresolvedPlaceholderException;

import static org.junit.Assert.assertEquals;

import jmul.test.classification.UnitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Various tests regarding configurable messages.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ConfigurableMessageMiscTest {

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
     * Tests if the resolvePlaceholder method works correctly.
     */
    @Test(expected = UnresolvedPlaceholderException.class)
    public void resolvePlaceholder() {

        ConfigurableMessage m = new Message("<greeting> <place>!");
        m.resolvePlaceholder(new String[] { "<place>", "World" });
    }

    /**
     * Tests if the resolvePlaceholder method works correctly.
     */
    @Test(expected = UnknownPlaceholderException.class)
    public void resolvePlaceholder2() {

        ConfigurableMessage m = new Message("<greeting> <place>!");
        m.resolvePlaceholder(new String[] { "<time>", "12:10:10", "<place>", "World" });
    }

    /**
     * Tests if the toString method works correctly.
     */
    @Test
    public void testToString() {

        String unresolvedMessage = "<Greeting>";
        ConfigurableMessage m = new Message(unresolvedMessage);
        assertEquals(unresolvedMessage, m.toString());
    }

}
