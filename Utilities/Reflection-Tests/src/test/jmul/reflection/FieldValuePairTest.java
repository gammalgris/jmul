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

package test.jmul.reflection;


import jmul.reflection.FieldValuePair;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This class contains tests for {@link jmul.reflection.FieldValuePair}.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class FieldValuePairTest {

    /**
     * Checks the initialization with valid parameters (i.e.
     * non-<code>null</code> parameters).
     */
    @Test
    public void testInitializationValidParameters() {

        String fieldName = "dummyField";
        Object value = new Object();

        FieldValuePair pair = new FieldValuePair(fieldName, value);

        assertEquals(fieldName, pair.getFieldName());
        assertEquals(value, pair.getFieldValue());
    }

    /**
     * Checks the initialization with valid parameters (i.e. value
     * is <code>null</code>).
     */
    @Test
    public void testInitializationValidParameters2() {

        String fieldName = "dummyField";
        Object value = null;

        FieldValuePair pair = new FieldValuePair(fieldName, value);

        assertEquals(fieldName, pair.getFieldName());
        assertEquals(value, pair.getFieldValue());
    }

    /**
     * Checks the initialization with invalid parameters (field name
     * is <code>null</code>).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInitializationInvalidParameters() {

        String fieldName = null;
        Object value = new Object();

        FieldValuePair pair = new FieldValuePair(fieldName, value);

        assertEquals(fieldName, pair.getFieldName());
        assertEquals(value, pair.getFieldValue());
    }

}
