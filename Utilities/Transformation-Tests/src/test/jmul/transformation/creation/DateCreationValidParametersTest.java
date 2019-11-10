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


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.SetUpException;

import jmul.transformation.creation.ObjectFactory;
import jmul.transformation.creation.ObjectFactoryImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class tests the date creation with valid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DateCreationValidParametersTest {

    /**
     * A class definition to instantiate an object.
     */
    private ClassDefinition classDefinition;

    /**
     * An input value to initialize an object.
     */
    private String inputValue;

    /**
     * A date pattern to parse a date string.
     */
    private String datePattern;

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
     * @param aDatePattern
     *        a date pattern
     */
    public DateCreationValidParametersTest(String aClassname, String anInputValue, String aDatePattern) {

        super();

        try {

            classDefinition = ClassHelper.getClass(aClassname);

        } catch (ClassNotFoundException e) {

            throw new SetUpException(e);
        }

        inputValue = anInputValue;
        datePattern = aDatePattern;
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
    @Test
    public void testCreation() {

        Object o = objectFactory.newInstance(classDefinition, inputValue, datePattern);

        assertNotNull(o);

        DateFormat format = new SimpleDateFormat(datePattern);
        assertEquals(inputValue, format.format(o));
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "java.util.Date", "01.01.2018", "dd.MM.yyyy" });

        return parameters;
    }

}
