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


import java.util.List;

import jmul.misc.exceptions.NullParameterException;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;
import jmul.reflection.classes.equivalence.ClassEquivalenceComparator;
import jmul.reflection.classes.equivalence.InterfaceRelationComparator;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains tests to check a class equivalence comparator.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class InterfaceRelationComparatorInvalidParametersTest {

    /**
     * An equivalence comparator.
     */
    private ClassEquivalenceComparator comparator;

    /**
     * Preparatory steps before the actual test.
     */
    @Before
    public void setUp() {

        comparator = new InterfaceRelationComparator();
    }

    /**
     * Clean up after a test.
     */
    @After
    public void tearDown() {

        comparator = null;
    }

    /**
     * Tests comparing two classes where the first parameter is <code>null</code>.
     */
    @Test(expected = NullParameterException.class)
    public void testEquivalenceWithNullParameter() {

        ClassDefinition cd1 = null;
        ClassDefinition cd2 = null;

        try {

            cd2 = ClassHelper.getClass(String.class);

        } catch (ClassNotFoundException e) {

            fail(e.getMessage());
        }


        comparator.compareClasses(cd1, cd2);
    }

    /**
     * Tests comparing two classes where the second parameter is <code>null</code>.
     */
    @Test(expected = NullParameterException.class)
    public void testEquivalenceWithNullParameter2() {

        ClassDefinition cd1 = null;
        ClassDefinition cd2 = null;

        try {

            cd1 = ClassHelper.getClass(List.class);

        } catch (ClassNotFoundException e) {

            fail(e.getMessage());
        }


        comparator.compareClasses(cd1, cd2);
    }

    /**
     * Tests comparing two classes where the first parameter is a class.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEquivalenceWithNullParameter3() {

        ClassDefinition cd1 = null;
        ClassDefinition cd2 = null;

        try {

            cd1 = ClassHelper.getClass(String.class);
            cd2 = ClassHelper.getClass(String.class);

        } catch (ClassNotFoundException e) {

            fail(e.getMessage());
        }


        comparator.compareClasses(cd1, cd2);
    }

}
