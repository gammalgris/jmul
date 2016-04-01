/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
     * Tests if the resolved method works correctly.
     */
    @Test
    public void testUnresolved() {

        ConfigurableMessage m = new Message("<greeting> <place>!");
        m.resolvePlaceholder("<greeting>", "Hello");
        assertTrue(m.existUnresolvedPlaceholders());
    }

    /**
     * Tests if the resolved method works correctly.
     */
    @Test
    public void testResolved() {

        ConfigurableMessage m = new Message("<greeting> World!");
        m.resolvePlaceholder("<greeting>", "Hello");
        assertFalse(m.existUnresolvedPlaceholders());
    }

}
