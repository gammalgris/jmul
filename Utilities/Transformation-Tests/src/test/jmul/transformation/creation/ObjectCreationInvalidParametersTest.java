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

package test.jmul.transformation.creation;


import java.util.ArrayList;
import java.util.Collection;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.SetUpException;

import jmul.transformation.creation.ObjectFactory;
import jmul.transformation.creation.ObjectFactoryImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class tests the object creation with invalid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ObjectCreationInvalidParametersTest {

    /**
     * A class definition to instantiate an object.
     */
    private ClassDefinition classDefinition;

    /**
     * An input value to initialize an object.
     */
    private String inputValue;

    /**
     * The entity that creates objects.
     */
    private ObjectFactory objectFactory;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param aClassname
     *        a class name
     * @param anInputValue
     *        an input value
     */
    public ObjectCreationInvalidParametersTest(String aClassname, String anInputValue) {

        super();

        try {

            classDefinition = ClassHelper.getClass(aClassname);

        } catch (ClassNotFoundException e) {

            throw new SetUpException(e);
        }

        inputValue = anInputValue;
    }

    /**
     * Preparations before a test.
     */
    @Before
    public void setUp() {

        objectFactory = new ObjectFactoryImpl();
    }

    /**
     * Clean up after a test.
     */
    @After
    public void tearDown() {

        objectFactory = null;
    }

    /**
     * Tests the actual creation.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreation() {

        objectFactory.newInstance(classDefinition, inputValue);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "boolean", null });
        parameters.add(new Object[] { "java.lang.Boolean", null });

        parameters.add(new Object[] { "boolean", null });
        parameters.add(new Object[] { "java.lang.Boolean", null });

        parameters.add(new Object[] { "byte", null });
        parameters.add(new Object[] { "byte", "a" });
        parameters.add(new Object[] { "java.lang.Byte", null });
        parameters.add(new Object[] { "java.lang.Byte", "a" });

        parameters.add(new Object[] { "char", null });
        parameters.add(new Object[] { "java.lang.Character", null });

        parameters.add(new Object[] { "byte", null });
        parameters.add(new Object[] { "byte", "a" });
        parameters.add(new Object[] { "java.lang.Byte", null });
        parameters.add(new Object[] { "java.lang.Byte", "a" });

        parameters.add(new Object[] { "double", null });
        parameters.add(new Object[] { "double", "a" });
        parameters.add(new Object[] { "java.lang.Double", null });
        parameters.add(new Object[] { "java.lang.Double", "a" });

        parameters.add(new Object[] { "float", null });
        parameters.add(new Object[] { "float", "a" });
        parameters.add(new Object[] { "java.lang.Float", null });
        parameters.add(new Object[] { "java.lang.Float", "a" });

        parameters.add(new Object[] { "int", null });
        parameters.add(new Object[] { "int", "a" });
        parameters.add(new Object[] { "java.lang.Integer", null });
        parameters.add(new Object[] { "java.lang.Integer", "a" });

        parameters.add(new Object[] { "long", null });
        parameters.add(new Object[] { "long", "a" });
        parameters.add(new Object[] { "java.lang.Long", null });
        parameters.add(new Object[] { "java.lang.Long", "a" });

        parameters.add(new Object[] { "short", null });
        parameters.add(new Object[] { "short", "a" });
        parameters.add(new Object[] { "java.lang.Short", null });
        parameters.add(new Object[] { "java.lang.Short", "a" });

        parameters.add(new Object[] { "java.lang.StringBuffer", null });
        parameters.add(new Object[] { "java.lang.StringBuilder", null });
        parameters.add(new Object[] { "java.lang.String", null });

        return parameters;
    }

}
