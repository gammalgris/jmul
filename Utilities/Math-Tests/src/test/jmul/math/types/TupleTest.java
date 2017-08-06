/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
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

package test.jmul.math.types;


import jmul.math.types.Tuple;
import jmul.math.types.TupleImpl;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * This class tests vaarious aspects of a tuple.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TupleTest {

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
     * Tests the creation of an empty tuple (i.e. zero elements).
     */
    @Test
    public void testEmptyTupleCreation() {

        Tuple<Integer> tuple = new TupleImpl<Integer>();

        assertTrue(tuple.size() == 0);
    }

    /**
     * Tests the creation of a single tuple (i.e. one element).
     */
    @Test
    public void testSingleTupleCreation() {

        Tuple<Integer> tuple = new TupleImpl<Integer>(1);

        assertTrue(tuple.size() == 1);
        assertEquals(new Integer(1), tuple.get(0));
    }

    /**
     * Tests the creation of a double tuple (i.e. two elements).
     */
    @Test
    public void testDoubleTupleCreation() {

        Tuple<Integer> tuple = new TupleImpl<Integer>(1, 2);

        assertTrue(tuple.size() == 2);
        assertEquals(new Integer(1), tuple.get(0));
        assertEquals(new Integer(2), tuple.get(1));
    }

}
