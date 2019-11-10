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

package test.jmul.reflection.classes.equivalence;


import java.util.ArrayList;
import java.util.Collection;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;
import jmul.reflection.classes.equivalence.ClassEquivalenceComparator;
import jmul.reflection.classes.equivalence.PrimitiveTypeComparator;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains tests to check a class equivalence comparator.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class PrimitiveTypeComparatorValidParametersTest {

    /**
     * An equivalence comparator.
     */
    private ClassEquivalenceComparator comparator;

    /**
     * A class.
     */
    private Class class1;

    /**
     * A class.
     */
    private Class class2;

    /**
     * The expected comparison result.
     */
    private boolean expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param aClass1
     * @param aClass2
     * @param anExpectedResult
     */
    public PrimitiveTypeComparatorValidParametersTest(Class aClass1, Class aClass2, boolean anExpectedResult) {

        super();

        class1 = aClass1;
        class2 = aClass2;
        expectedResult = anExpectedResult;
    }

    /**
     * Preparatory steps before the actual test.
     */
    @Before
    public void setUp() {

        comparator = new PrimitiveTypeComparator();
    }

    /**
     * Clean up after a test.
     */
    @After
    public void tearDown() {

        comparator = null;
    }

    /**
     * Tests comparing two classes.
     */
    @Test
    public void testEquivalence() {

        ClassDefinition cd1 = null;
        ClassDefinition cd2 = null;

        try {

            cd1 = ClassHelper.getClass(class1);
            cd2 = ClassHelper.getClass(class2);

        } catch (ClassNotFoundException e) {

            fail(e.getMessage());
        }


        boolean actualResult = comparator.compareClasses(cd1, cd2);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { Byte.TYPE, Byte.class, true });
        parameters.add(new Object[] { Byte.TYPE, Short.class, false });
        parameters.add(new Object[] { Byte.TYPE, Integer.class, false });
        parameters.add(new Object[] { Byte.TYPE, Long.class, false });
        parameters.add(new Object[] { Byte.TYPE, Float.class, false });
        parameters.add(new Object[] { Byte.TYPE, Double.class, false });
        parameters.add(new Object[] { Byte.TYPE, Boolean.class, false });

        parameters.add(new Object[] { Short.TYPE, Byte.class, false });
        parameters.add(new Object[] { Short.TYPE, Short.class, true });
        parameters.add(new Object[] { Short.TYPE, Integer.class, false });
        parameters.add(new Object[] { Short.TYPE, Long.class, false });
        parameters.add(new Object[] { Short.TYPE, Float.class, false });
        parameters.add(new Object[] { Short.TYPE, Double.class, false });
        parameters.add(new Object[] { Short.TYPE, Boolean.class, false });

        parameters.add(new Object[] { Integer.TYPE, Byte.class, false });
        parameters.add(new Object[] { Integer.TYPE, Short.class, false });
        parameters.add(new Object[] { Integer.TYPE, Integer.class, true });
        parameters.add(new Object[] { Integer.TYPE, Long.class, false });
        parameters.add(new Object[] { Integer.TYPE, Float.class, false });
        parameters.add(new Object[] { Integer.TYPE, Double.class, false });
        parameters.add(new Object[] { Integer.TYPE, Boolean.class, false });

        parameters.add(new Object[] { Long.TYPE, Byte.class, false });
        parameters.add(new Object[] { Long.TYPE, Short.class, false });
        parameters.add(new Object[] { Long.TYPE, Integer.class, false });
        parameters.add(new Object[] { Long.TYPE, Long.class, true });
        parameters.add(new Object[] { Long.TYPE, Float.class, false });
        parameters.add(new Object[] { Long.TYPE, Double.class, false });
        parameters.add(new Object[] { Long.TYPE, Boolean.class, false });

        parameters.add(new Object[] { Float.TYPE, Byte.class, false });
        parameters.add(new Object[] { Float.TYPE, Short.class, false });
        parameters.add(new Object[] { Float.TYPE, Integer.class, false });
        parameters.add(new Object[] { Float.TYPE, Long.class, false });
        parameters.add(new Object[] { Float.TYPE, Float.class, true });
        parameters.add(new Object[] { Float.TYPE, Double.class, false });
        parameters.add(new Object[] { Float.TYPE, Boolean.class, false });

        parameters.add(new Object[] { Double.TYPE, Byte.class, false });
        parameters.add(new Object[] { Double.TYPE, Short.class, false });
        parameters.add(new Object[] { Double.TYPE, Integer.class, false });
        parameters.add(new Object[] { Double.TYPE, Long.class, false });
        parameters.add(new Object[] { Double.TYPE, Float.class, false });
        parameters.add(new Object[] { Double.TYPE, Double.class, true });
        parameters.add(new Object[] { Double.TYPE, Boolean.class, false });

        parameters.add(new Object[] { Boolean.TYPE, Byte.class, false });
        parameters.add(new Object[] { Boolean.TYPE, Short.class, false });
        parameters.add(new Object[] { Boolean.TYPE, Integer.class, false });
        parameters.add(new Object[] { Boolean.TYPE, Long.class, false });
        parameters.add(new Object[] { Boolean.TYPE, Float.class, false });
        parameters.add(new Object[] { Boolean.TYPE, Double.class, false });
        parameters.add(new Object[] { Boolean.TYPE, Boolean.class, true });

        parameters.add(new Object[] { String.class, Number.class, false });

        return parameters;
    }

}
