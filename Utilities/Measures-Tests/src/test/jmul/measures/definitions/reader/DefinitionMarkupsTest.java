/*
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

package test.jmul.measures.definitions.reader;


import java.util.ArrayList;
import java.util.Collection;

import static jmul.measures.definitions.reader.DefinitionMarkups.ABBREVIATION_ATTRIBUTE;
import static jmul.measures.definitions.reader.DefinitionMarkups.CATEGORY_ATTRIBUTE;
import static jmul.measures.definitions.reader.DefinitionMarkups.DEFINITION_ELEMENT;
import static jmul.measures.definitions.reader.DefinitionMarkups.NAME_ATTRIBUTE;
import static jmul.measures.definitions.reader.DefinitionMarkups.NORMALIZED_VALUE_ATTRIBUTE;
import static jmul.measures.definitions.reader.DefinitionMarkups.UNIT_ELEMENT;

import jmul.test.classification.ConfigurationTest;

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
public class DefinitionMarkupsTest {

    /**
     * Returns a matrix of test data.
     *
     * @return some test data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { ABBREVIATION_ATTRIBUTE, ABBREVIATION_ATTRIBUTE, true });
        parameters.add(new Object[] { ABBREVIATION_ATTRIBUTE, CATEGORY_ATTRIBUTE, false });
        parameters.add(new Object[] { ABBREVIATION_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { ABBREVIATION_ATTRIBUTE, NORMALIZED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { ABBREVIATION_ATTRIBUTE, DEFINITION_ELEMENT, false });
        parameters.add(new Object[] { ABBREVIATION_ATTRIBUTE, UNIT_ELEMENT, false });

        parameters.add(new Object[] { CATEGORY_ATTRIBUTE, ABBREVIATION_ATTRIBUTE, false });
        parameters.add(new Object[] { CATEGORY_ATTRIBUTE, CATEGORY_ATTRIBUTE, true });
        parameters.add(new Object[] { CATEGORY_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { CATEGORY_ATTRIBUTE, NORMALIZED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { CATEGORY_ATTRIBUTE, DEFINITION_ELEMENT, false });
        parameters.add(new Object[] { CATEGORY_ATTRIBUTE, UNIT_ELEMENT, false });

        parameters.add(new Object[] { NAME_ATTRIBUTE, ABBREVIATION_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, CATEGORY_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, NAME_ATTRIBUTE, true });
        parameters.add(new Object[] { NAME_ATTRIBUTE, NORMALIZED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, DEFINITION_ELEMENT, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, UNIT_ELEMENT, false });

        parameters.add(new Object[] { NORMALIZED_VALUE_ATTRIBUTE, ABBREVIATION_ATTRIBUTE, false });
        parameters.add(new Object[] { NORMALIZED_VALUE_ATTRIBUTE, CATEGORY_ATTRIBUTE, false });
        parameters.add(new Object[] { NORMALIZED_VALUE_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { NORMALIZED_VALUE_ATTRIBUTE, NORMALIZED_VALUE_ATTRIBUTE, true });
        parameters.add(new Object[] { NORMALIZED_VALUE_ATTRIBUTE, DEFINITION_ELEMENT, false });
        parameters.add(new Object[] { NORMALIZED_VALUE_ATTRIBUTE, UNIT_ELEMENT, false });

        parameters.add(new Object[] { DEFINITION_ELEMENT, ABBREVIATION_ATTRIBUTE, false });
        parameters.add(new Object[] { DEFINITION_ELEMENT, CATEGORY_ATTRIBUTE, false });
        parameters.add(new Object[] { DEFINITION_ELEMENT, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { DEFINITION_ELEMENT, NORMALIZED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { DEFINITION_ELEMENT, DEFINITION_ELEMENT, true });
        parameters.add(new Object[] { DEFINITION_ELEMENT, UNIT_ELEMENT, false });

        parameters.add(new Object[] { UNIT_ELEMENT, ABBREVIATION_ATTRIBUTE, false });
        parameters.add(new Object[] { UNIT_ELEMENT, CATEGORY_ATTRIBUTE, false });
        parameters.add(new Object[] { UNIT_ELEMENT, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { UNIT_ELEMENT, NORMALIZED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { UNIT_ELEMENT, DEFINITION_ELEMENT, false });
        parameters.add(new Object[] { UNIT_ELEMENT, UNIT_ELEMENT, true });

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
    public DefinitionMarkupsTest(XmlMarkup aMarkup1, XmlMarkup aMarkup2, boolean anExpectedResult) {

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
