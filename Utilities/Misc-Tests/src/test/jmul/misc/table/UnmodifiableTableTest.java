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

package test.jmul.misc.table;


import jmul.misc.table.Table;
import jmul.misc.table.UnmodifiableTableImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * A collection of tests for unmodifiable tables.
 *
 * @author Kristian Kutin
 */
public class UnmodifiableTableTest {

    /**
     * A table.
     */
    private Table<Integer> table;

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        table = new UnmodifiableTableImpl<Integer>(new String[] { "A", "B", "C" }, new Integer[][] {
                                                   { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }
            });
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        table = null;
    }

    /**
     * Tests if the header is correctly set.
     */
    @Test
    public void testTableHeader() {

        assertEquals("A", table.getColumnName(0));
        assertEquals("B", table.getColumnName(1));
        assertEquals("C", table.getColumnName(2));
    }

    /**
     * Tests if the header is correctly set.
     */
    @Test
    public void testTableHeader2() {

        int index = 0;
        for (String name : table.getColumnNames()) {

            assertEquals(name, table.getColumnName(index));
            index++;
        }
    }

    /**
     * Tests if the values are correctly set.
     */
    @Test
    public void testTableValues() {

        assertTrue(1 == table.getCell(0, 0));
        assertTrue(2 == table.getCell(1, 0));
        assertTrue(3 == table.getCell(2, 0));
        assertTrue(4 == table.getCell(0, 1));
        assertTrue(5 == table.getCell(1, 1));
        assertTrue(6 == table.getCell(2, 1));
        assertTrue(7 == table.getCell(0, 2));
        assertTrue(8 == table.getCell(1, 2));
        assertTrue(9 == table.getCell(2, 2));
    }

    /**
     * Tests various table properties for correctness.
     */
    @Test
    public void testTableProperties() {

        assertEquals(3, table.columns());
        assertEquals(3, table.rows());
        assertEquals(9, table.cells());
    }

    /**
     * Tests the modification of the table.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testHeaderModification() {

        table.setColumnName(0, "Z");
    }

}
