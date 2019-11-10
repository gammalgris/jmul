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

package test.jmul.misc.container;


import jmul.misc.container.DataContainer;
import jmul.misc.container.DataContainerImpl;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * This class tests the functionalities of a data container.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class DataContainerTest {

    /**
     * A data container.
     */
    private DataContainer<String, String> container;

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        container = new DataContainerImpl<String, String>();
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        container = null;
    }

    /**
     * Tests putting and retrieving data from a data container.
     */
    @Test
    public void testDataContainer() {

        String key = "key";
        String value = "value";

        container.putValue(key, value);

        assertEquals(value, container.getValue(key));
    }

}
