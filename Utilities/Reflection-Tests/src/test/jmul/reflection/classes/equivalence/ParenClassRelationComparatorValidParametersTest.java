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

package test.jmul.reflection.classes.equivalence;


import java.util.ArrayList;
import java.util.Collection;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;
import jmul.reflection.classes.equivalence.ClassEquivalenceComparator;
import jmul.reflection.classes.equivalence.ParentClassRelationComparator;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import test.jmul.reflection.classes.equivalence.classes.C1;
import test.jmul.reflection.classes.equivalence.classes.C2;
import test.jmul.reflection.classes.equivalence.classes.C3;
import test.jmul.reflection.classes.equivalence.classes.C4;
import test.jmul.reflection.classes.equivalence.classes.C5;


/**
 * This class contains tests to check a class equivalence comparator.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ParenClassRelationComparatorValidParametersTest {

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
    public ParenClassRelationComparatorValidParametersTest(Class aClass1, Class aClass2, boolean anExpectedResult) {

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

        comparator = new ParentClassRelationComparator();
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

        parameters.add(new Object[] { C1.class, C1.class, false });
        parameters.add(new Object[] { C1.class, C2.class, false });
        parameters.add(new Object[] { C1.class, C3.class, false });
        parameters.add(new Object[] { C1.class, C4.class, false });
        parameters.add(new Object[] { C1.class, C5.class, false });

        parameters.add(new Object[] { C2.class, C1.class, false });
        parameters.add(new Object[] { C2.class, C2.class, false });
        parameters.add(new Object[] { C2.class, C3.class, false });
        parameters.add(new Object[] { C2.class, C4.class, false });
        parameters.add(new Object[] { C2.class, C5.class, false });

        parameters.add(new Object[] { C3.class, C1.class, false });
        parameters.add(new Object[] { C3.class, C2.class, false });
        parameters.add(new Object[] { C3.class, C3.class, false });
        parameters.add(new Object[] { C3.class, C4.class, false });
        parameters.add(new Object[] { C3.class, C5.class, false });

        parameters.add(new Object[] { C4.class, C1.class, false });
        parameters.add(new Object[] { C4.class, C2.class, false });
        parameters.add(new Object[] { C4.class, C3.class, false });
        parameters.add(new Object[] { C4.class, C4.class, false });
        parameters.add(new Object[] { C4.class, C5.class, true });

        parameters.add(new Object[] { C5.class, C1.class, false });
        parameters.add(new Object[] { C5.class, C2.class, false });
        parameters.add(new Object[] { C5.class, C3.class, false });
        parameters.add(new Object[] { C5.class, C4.class, false });
        parameters.add(new Object[] { C5.class, C5.class, false });

        return parameters;
    }

}
