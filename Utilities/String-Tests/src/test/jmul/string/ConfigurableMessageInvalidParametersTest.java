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


import java.util.ArrayList;
import java.util.Collection;

import jmul.string.ConfigurableMessage;
import jmul.string.Message;

import jmul.test.classification.UnitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Various tests for the string concatenator class.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ConfigurableMessageInvalidParametersTest {

    /**
     * The message with placeholders.
     */
    private String messageSkeleton;

    /**
     * The componenets which make up the string.
     */
    private String[] placeholders;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aMessageSkeleton
     * @param somePlaceholders
     */
    public ConfigurableMessageInvalidParametersTest(String aMessageSkeleton, String... somePlaceholders) {

        messageSkeleton = aMessageSkeleton;
        placeholders = somePlaceholders;
    }

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
     * The actual test concatenates all string components and compares the result
     * to the expected string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testStringConcatenator() {

        ConfigurableMessage m = new Message(messageSkeleton);
        m.resolvePlaceholder(placeholders);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "Hello <place>!", new String[] { } });
        parameters.add(new Object[] { "Hello <place>!", null });
        parameters.add(new Object[] { null, new String[] { "<place>", "World" } });
        parameters.add(new Object[] { "Hello <place>!", new String[] { "<place>" } });
        parameters.add(new Object[] { "Hello <place>!", new String[] { "<place", "World" } });
        parameters.add(new Object[] { "Hello <place>!", new String[] { null, "World" } });
        parameters.add(new Object[] { "Hello World!", new String[] { "<place>", "World" } });
        parameters.add(new Object[] { "", new String[] { "<place>", "World" } });

        return parameters;
    }

}
