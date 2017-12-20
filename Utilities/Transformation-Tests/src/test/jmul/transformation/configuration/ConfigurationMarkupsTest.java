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

package test.jmul.transformation.configuration;


import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.ConfigurationTest;

import static jmul.transformation.configuration.ConfigurationMarkups.CLASSNAME_ATTRIBUTE;
import static jmul.transformation.configuration.ConfigurationMarkups.CLASSPATH_ATTRIBUTE;
import static jmul.transformation.configuration.ConfigurationMarkups.DESTINATION_ATTRIBUTE;
import static jmul.transformation.configuration.ConfigurationMarkups.IMPLEMENTATION_ELEMENT;
import static jmul.transformation.configuration.ConfigurationMarkups.ORIGIN_ATTRIBUTE;
import static jmul.transformation.configuration.ConfigurationMarkups.PRIORITY_ATTRIBUTE;
import static jmul.transformation.configuration.ConfigurationMarkups.RULE_ELEMENT;
import static jmul.transformation.configuration.ConfigurationMarkups.TRANSFORMATION_PATH_ELEMENT;

import jmul.xml.XmlMarkup;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains tests to check the corresponding enumeration.
 *
 * @author Kristian Kutin
 */
@ConfigurationTest
@RunWith(Parameterized.class)
public class ConfigurationMarkupsTest {

    /**
     * Returns a matrix of test data.
     *
     * @return some test data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { CLASSNAME_ATTRIBUTE, CLASSNAME_ATTRIBUTE, true });
        parameters.add(new Object[] { CLASSNAME_ATTRIBUTE, CLASSPATH_ATTRIBUTE, false });
        parameters.add(new Object[] { CLASSNAME_ATTRIBUTE, DESTINATION_ATTRIBUTE, false });
        parameters.add(new Object[] { CLASSNAME_ATTRIBUTE, ORIGIN_ATTRIBUTE, false });
        parameters.add(new Object[] { CLASSNAME_ATTRIBUTE, PRIORITY_ATTRIBUTE, false });
        parameters.add(new Object[] { CLASSNAME_ATTRIBUTE, RULE_ELEMENT, false });
        parameters.add(new Object[] { CLASSNAME_ATTRIBUTE, IMPLEMENTATION_ELEMENT, false });
        parameters.add(new Object[] { CLASSNAME_ATTRIBUTE, TRANSFORMATION_PATH_ELEMENT, false });

        parameters.add(new Object[] { CLASSPATH_ATTRIBUTE, CLASSNAME_ATTRIBUTE, false });
        parameters.add(new Object[] { CLASSPATH_ATTRIBUTE, CLASSPATH_ATTRIBUTE, true });
        parameters.add(new Object[] { CLASSPATH_ATTRIBUTE, DESTINATION_ATTRIBUTE, false });
        parameters.add(new Object[] { CLASSPATH_ATTRIBUTE, ORIGIN_ATTRIBUTE, false });
        parameters.add(new Object[] { CLASSPATH_ATTRIBUTE, PRIORITY_ATTRIBUTE, false });
        parameters.add(new Object[] { CLASSPATH_ATTRIBUTE, RULE_ELEMENT, false });
        parameters.add(new Object[] { CLASSPATH_ATTRIBUTE, IMPLEMENTATION_ELEMENT, false });
        parameters.add(new Object[] { CLASSPATH_ATTRIBUTE, TRANSFORMATION_PATH_ELEMENT, false });

        parameters.add(new Object[] { DESTINATION_ATTRIBUTE, CLASSNAME_ATTRIBUTE, false });
        parameters.add(new Object[] { DESTINATION_ATTRIBUTE, CLASSPATH_ATTRIBUTE, false });
        parameters.add(new Object[] { DESTINATION_ATTRIBUTE, DESTINATION_ATTRIBUTE, true });
        parameters.add(new Object[] { DESTINATION_ATTRIBUTE, ORIGIN_ATTRIBUTE, false });
        parameters.add(new Object[] { DESTINATION_ATTRIBUTE, PRIORITY_ATTRIBUTE, false });
        parameters.add(new Object[] { DESTINATION_ATTRIBUTE, RULE_ELEMENT, false });
        parameters.add(new Object[] { DESTINATION_ATTRIBUTE, IMPLEMENTATION_ELEMENT, false });
        parameters.add(new Object[] { DESTINATION_ATTRIBUTE, TRANSFORMATION_PATH_ELEMENT, false });

        parameters.add(new Object[] { ORIGIN_ATTRIBUTE, CLASSNAME_ATTRIBUTE, false });
        parameters.add(new Object[] { ORIGIN_ATTRIBUTE, CLASSPATH_ATTRIBUTE, false });
        parameters.add(new Object[] { ORIGIN_ATTRIBUTE, DESTINATION_ATTRIBUTE, false });
        parameters.add(new Object[] { ORIGIN_ATTRIBUTE, ORIGIN_ATTRIBUTE, true });
        parameters.add(new Object[] { ORIGIN_ATTRIBUTE, PRIORITY_ATTRIBUTE, false });
        parameters.add(new Object[] { ORIGIN_ATTRIBUTE, RULE_ELEMENT, false });
        parameters.add(new Object[] { ORIGIN_ATTRIBUTE, IMPLEMENTATION_ELEMENT, false });
        parameters.add(new Object[] { ORIGIN_ATTRIBUTE, TRANSFORMATION_PATH_ELEMENT, false });

        parameters.add(new Object[] { PRIORITY_ATTRIBUTE, CLASSNAME_ATTRIBUTE, false });
        parameters.add(new Object[] { PRIORITY_ATTRIBUTE, CLASSPATH_ATTRIBUTE, false });
        parameters.add(new Object[] { PRIORITY_ATTRIBUTE, DESTINATION_ATTRIBUTE, false });
        parameters.add(new Object[] { PRIORITY_ATTRIBUTE, ORIGIN_ATTRIBUTE, false });
        parameters.add(new Object[] { PRIORITY_ATTRIBUTE, PRIORITY_ATTRIBUTE, true });
        parameters.add(new Object[] { PRIORITY_ATTRIBUTE, RULE_ELEMENT, false });
        parameters.add(new Object[] { PRIORITY_ATTRIBUTE, IMPLEMENTATION_ELEMENT, false });
        parameters.add(new Object[] { PRIORITY_ATTRIBUTE, TRANSFORMATION_PATH_ELEMENT, false });

        parameters.add(new Object[] { RULE_ELEMENT, CLASSNAME_ATTRIBUTE, false });
        parameters.add(new Object[] { RULE_ELEMENT, CLASSPATH_ATTRIBUTE, false });
        parameters.add(new Object[] { RULE_ELEMENT, DESTINATION_ATTRIBUTE, false });
        parameters.add(new Object[] { RULE_ELEMENT, ORIGIN_ATTRIBUTE, false });
        parameters.add(new Object[] { RULE_ELEMENT, PRIORITY_ATTRIBUTE, false });
        parameters.add(new Object[] { RULE_ELEMENT, RULE_ELEMENT, true });
        parameters.add(new Object[] { RULE_ELEMENT, IMPLEMENTATION_ELEMENT, false });
        parameters.add(new Object[] { RULE_ELEMENT, TRANSFORMATION_PATH_ELEMENT, false });

        parameters.add(new Object[] { IMPLEMENTATION_ELEMENT, CLASSNAME_ATTRIBUTE, false });
        parameters.add(new Object[] { IMPLEMENTATION_ELEMENT, CLASSPATH_ATTRIBUTE, false });
        parameters.add(new Object[] { IMPLEMENTATION_ELEMENT, DESTINATION_ATTRIBUTE, false });
        parameters.add(new Object[] { IMPLEMENTATION_ELEMENT, ORIGIN_ATTRIBUTE, false });
        parameters.add(new Object[] { IMPLEMENTATION_ELEMENT, PRIORITY_ATTRIBUTE, false });
        parameters.add(new Object[] { IMPLEMENTATION_ELEMENT, RULE_ELEMENT, false });
        parameters.add(new Object[] { IMPLEMENTATION_ELEMENT, IMPLEMENTATION_ELEMENT, true });
        parameters.add(new Object[] { IMPLEMENTATION_ELEMENT, TRANSFORMATION_PATH_ELEMENT, false });

        parameters.add(new Object[] { TRANSFORMATION_PATH_ELEMENT, CLASSNAME_ATTRIBUTE, false });
        parameters.add(new Object[] { TRANSFORMATION_PATH_ELEMENT, CLASSPATH_ATTRIBUTE, false });
        parameters.add(new Object[] { TRANSFORMATION_PATH_ELEMENT, DESTINATION_ATTRIBUTE, false });
        parameters.add(new Object[] { TRANSFORMATION_PATH_ELEMENT, ORIGIN_ATTRIBUTE, false });
        parameters.add(new Object[] { TRANSFORMATION_PATH_ELEMENT, PRIORITY_ATTRIBUTE, false });
        parameters.add(new Object[] { TRANSFORMATION_PATH_ELEMENT, RULE_ELEMENT, false });
        parameters.add(new Object[] { TRANSFORMATION_PATH_ELEMENT, IMPLEMENTATION_ELEMENT, false });
        parameters.add(new Object[] { TRANSFORMATION_PATH_ELEMENT, TRANSFORMATION_PATH_ELEMENT, true });

        return parameters;
    }

    /**
     * An XML markup.
     */
    private XmlMarkup markup1;

    /**
     * An XML markup.
     */
    private XmlMarkup markup2;

    /**
     * The expected result when comparing the markups.
     */
    private boolean expectedResult;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aMarkup1
     * @param aMarkup2
     * @param anExpectedResult
     */
    public ConfigurationMarkupsTest(XmlMarkup aMarkup1, XmlMarkup aMarkup2, boolean anExpectedResult) {

        markup1 = aMarkup1;
        markup2 = aMarkup2;
        expectedResult = anExpectedResult;
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
     * Tests if an equality check of the tag names returns the expected result.
     */
    @Test
    public void testTagNameEquality() {

        assertEquals(expectedResult, markup1.equalsTagName(markup2.getTagName()));
    }

    /**
     * Tests if an equality check of the markups returns the expected result.
     */
    @Test
    public void testMarkupEquality() {

        assertEquals(expectedResult, markup1.equalsXmlMarkup(markup2));
    }

}
