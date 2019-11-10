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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.transformation.creation;


import jmul.checks.exceptions.NullParameterException;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.FailedTestException;

import jmul.transformation.creation.ObjectFactory;
import jmul.transformation.creation.ObjectFactoryImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains various tests regarding object instantiation and initialization.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ObjectInstantiationTest {

    /**
     * A factory.
     */
    private ObjectFactory objectFactory;

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        objectFactory = new ObjectFactoryImpl();
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        objectFactory = null;
    }

    /**
     * Tests the creation with an invalid class definition.
     */
    @Test(expected = NullParameterException.class)
    public void testInvalidInstantiation() {

        ClassDefinition dateType = null;

        objectFactory.newInstance(dateType);
    }

    /**
     * Tests the creation with an invalid class definition.
     */
    @Test(expected = NullParameterException.class)
    public void testInvalidInstantiation2() {

        ClassDefinition dateType = null;
        String input = "Test";

        objectFactory.newInstance(dateType, input);
    }

    /**
     * Tests the creation with an invalid class definition.
     */
    @Test(expected = NullParameterException.class)
    public void testInvalidInstantiation3() {

        ClassDefinition dateType = null;
        String input = "31.03.2010";
        String inputPattern = "dd.MM.yyyy";

        objectFactory.newInstance(dateType, input, inputPattern);
    }

    /**
     * Tests the creation with a valid class name.
     */
    @Test
    public void testClassInstantiation() {

        ClassDefinition type;

        try {

            type = ClassHelper.getClass("java.lang.Class");

        } catch (ClassNotFoundException e) {

            throw new FailedTestException(e);
        }

        String input = "java.lang.String";

        Object o = objectFactory.newInstance(type, input);
        assertTrue(o instanceof Class);

        Class c = (Class) o;
        assertEquals(input, c.getCanonicalName());
    }

}
