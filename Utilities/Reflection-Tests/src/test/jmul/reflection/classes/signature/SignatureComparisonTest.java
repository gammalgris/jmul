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

package test.jmul.reflection.classes.signature;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;
import jmul.reflection.classes.signature.SignatureComparator;
import jmul.reflection.classes.signature.SignatureComparatorImpl;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.SetUpException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class tests a method signature comparator.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SignatureComparisonTest {

    /**
     * The expected signature;
     */
    private List<ClassDefinition> expectedSignature;

    /**
     * The actual signature.
     */
    private List<ClassDefinition> actualSignature;

    /**
     * The expected result.
     */
    private boolean expectedResult;

    /**
     * A signature comparator.
     */
    private SignatureComparator comparator;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param anExpectedSignature
     *        the expected method signature
     * @param anActualSignature
     *        the actual method signature
     * @param anExpectedResult
     *        the expected comparison result
     */
    public SignatureComparisonTest(String[] anExpectedSignature, String[] anActualSignature, boolean anExpectedResult) {

        super();

        expectedSignature = toList(anExpectedSignature);
        actualSignature = toList(anActualSignature);
        expectedResult = anExpectedResult;
    }

    /**
     * Steps to set up a test.
     */
    @Before
    public void setUp() {

        comparator = new SignatureComparatorImpl();
    }

    /**
     * Steps to clean up after a test.
     */
    @After
    public void tearDown() {

        comparator = null;
    }

    /**
     * Transforms a string array to a method signature (i.e. a list of parameter types).
     *
     * @param aSignature
     *        a string array representing a method signature
     *
     * @return a method signature
     */
    private static List<ClassDefinition> toList(String[] aSignature) {

        List<ClassDefinition> tmp = new ArrayList<>();

        for (String classname : aSignature) {

            try {

                ClassDefinition definition = ClassHelper.getClass(classname);
                tmp.add(definition);

            } catch (ClassNotFoundException e) {

                throw new SetUpException("Test couldn't be initialized!", e);
            }
        }

        return tmp;
    }

    /**
     * Tests the comparison of two method signatures.
     */
    @Test
    public void testComparison() {

        boolean actualResult = comparator.compareSignatures(expectedSignature, actualSignature);

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

        parameters.add(new Object[] { new String[] { }, new String[] { }, true });

        parameters.add(new Object[] { new String[] { "byte" }, new String[] { "java.lang.Byte" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Byte" }, new String[] { "byte" }, true });
        parameters.add(new Object[] { new String[] { "byte" }, new String[] { "byte" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Byte" }, new String[] { "java.lang.Byte" }, true });

        parameters.add(new Object[] { new String[] { "short" }, new String[] { "java.lang.Short" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Short" }, new String[] { "short" }, true });
        parameters.add(new Object[] { new String[] { "short" }, new String[] { "short" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Short" }, new String[] { "java.lang.Short" }, true });

        parameters.add(new Object[] { new String[] { "int" }, new String[] { "java.lang.Integer" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Integer" }, new String[] { "int" }, true });
        parameters.add(new Object[] { new String[] { "int" }, new String[] { "int" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Integer" }, new String[] { "java.lang.Integer" },
                                      true });

        parameters.add(new Object[] { new String[] { "float" }, new String[] { "java.lang.Float" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Float" }, new String[] { "float" }, true });
        parameters.add(new Object[] { new String[] { "float" }, new String[] { "float" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Float" }, new String[] { "java.lang.Float" }, true });

        parameters.add(new Object[] { new String[] { "double" }, new String[] { "java.lang.Double" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Double" }, new String[] { "double" }, true });
        parameters.add(new Object[] { new String[] { "double" }, new String[] { "double" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Double" }, new String[] { "java.lang.Double" }, true });

        parameters.add(new Object[] { new String[] { "boolean" }, new String[] { "java.lang.Boolean" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Boolean" }, new String[] { "boolean" }, true });
        parameters.add(new Object[] { new String[] { "boolean" }, new String[] { "java.lang.Boolean" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Boolean" }, new String[] { "java.lang.Boolean" },
                                      true });

        parameters.add(new Object[] { new String[] { "char" }, new String[] { "java.lang.Character" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Character" }, new String[] { "char" }, true });
        parameters.add(new Object[] { new String[] { "char" }, new String[] { "char" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.Character" }, new String[] { "java.lang.Character" },
                                      true });

        parameters.add(new Object[] { new String[] { "java.lang.String", "java.util.List" },
                                      new String[] { "java.lang.String", "java.util.List" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.CharSequence", "java.util.List" },
                                      new String[] { "java.lang.String", "java.util.ArrayList" }, true });
        parameters.add(new Object[] { new String[] { "java.lang.String", "java.util.List" },
                                      new String[] { "java.lang.CharSequence", "java.util.ArrayList" }, false });

        return parameters;
    }

}
