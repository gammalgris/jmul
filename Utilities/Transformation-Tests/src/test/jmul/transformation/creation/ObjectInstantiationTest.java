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

package test.jmul.transformation.creation;


import java.text.SimpleDateFormat;

import java.util.Date;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.test.classification.UnitTest;

import jmul.transformation.creation.ObjectFactory;
import jmul.transformation.creation.ObjectFactoryImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
     * Tests the instantiation and initialization of an integer.
     */
    @Test
    public void testIntegerInstantiation() {

        ClassDefinition integerType = null;

        try {

            integerType = ClassHelper.getClass(Integer.TYPE.getName());

        } catch (ClassNotFoundException e) {

            fail(e.getMessage());
        }

        String input = "1";
        Object o = objectFactory.newInstance(integerType, input);
        assertEquals(input, o.toString());
    }

    /**
     * Tests the instantiation and initialization of a float.
     */
    @Test
    public void testFloatInstantiation() {

        ClassDefinition floatType = null;

        try {

            floatType = ClassHelper.getClass(Float.TYPE.getName());

        } catch (ClassNotFoundException e) {

            fail(e.getMessage());
        }

        String input = "1.5";
        Object o = objectFactory.newInstance(floatType, input);
        assertEquals(input, o.toString());
    }

    /**
     * Tests the instantiation and initialization of a character.
     */
    @Test
    public void testCharacterInstantiation() {

        ClassDefinition characterType = null;

        try {

            characterType = ClassHelper.getClass(Character.TYPE.getName());

        } catch (ClassNotFoundException e) {

            fail(e.getMessage());
        }


        String input = "c";
        Object o = objectFactory.newInstance(characterType, input);
        assertEquals(input, o.toString());
    }

    /**
     * Tests the instantiation and initialization of a date.
     */
    @Test
    public void testDateInstantiation() {

        ClassDefinition dateType = null;

        try {

            dateType = ClassHelper.getClass(Date.class.getName());

        } catch (ClassNotFoundException e) {

            fail(e.getMessage());
        }

        String input = "31.03.2010";
        String inputPattern = "dd.MM.yyyy";
        Object o = objectFactory.newInstance(dateType, input, inputPattern);
        assertEquals(input, toString((Date) o, inputPattern));
    }

    /**
     * Returns a string according to the specified date and date pattern.
     *
     * @param aDate
     * @param aPattern
     *
     * @return a string
     */
    private static String toString(Date aDate, String aPattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(aPattern);

        return sdf.format(aDate);
    }

}
