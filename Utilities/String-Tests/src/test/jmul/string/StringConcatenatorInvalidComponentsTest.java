/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package test.jmul.string;


import java.util.ArrayList;
import java.util.Collection;

import jmul.string.StringConcatenator;

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
public class StringConcatenatorInvalidComponentsTest {

    /**
     * The componenets which make up the string.
     */
    private Object[] stringComponents;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param someStringComponents
     */
    public StringConcatenatorInvalidComponentsTest(Object... someStringComponents) {

        stringComponents = someStringComponents;
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
     * The test tries to initialize a string concatenator with the specified invalid components.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInitialization() {

        new StringConcatenator(stringComponents);
    }

    /**
     * The test tries to append invalid components to a string concatenator.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAppend() {

        StringConcatenator s = new StringConcatenator();
        s.append(stringComponents);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null });

        return parameters;
    }

}
