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

package test.jmul.transformation.xml;


import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.UnitTest;

import jmul.transformation.xml.TransformationHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains tests to check the correctness of utility classes
 * in the transformation helper.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class IsCompositeInvalidParametersTest {

    /**
     * The class which is to be probed.
     */
    private Class input1;

    /**
     * The class which is a filter for class members.
     */
    private Class input2;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anInput1
     * @param anInput2
     */
    public IsCompositeInvalidParametersTest(Class anInput1, Class anInput2) {

        input1 = anInput1;
        input2 = anInput2;
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
     * Tests if the a class can be correctly identified as a
     * composite class.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsComposite() {

        TransformationHelper.isComposite(input1, input2);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, null });
        parameters.add(new Object[] { null, CSuperclassA.class });
        parameters.add(new Object[] { CClassA.class, CSuperclassA.class });

        return parameters;
    }

}


class CSuperclassA {

    private String supertextA;

    public void setSupertextA(String supertextA) {
        this.supertextA = supertextA;
    }

    public String getSupertextA() {
        return supertextA;
    }

}


class CClassA {

    private String textA;

    public void setTextA(String textA) {
        this.textA = textA;
    }

    public String getTextA() {
        return textA;
    }

}
