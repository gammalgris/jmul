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

package test.jmul.misc.table;


import jmul.misc.table.ModifiableTable;
import jmul.misc.table.ModifiableTableImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


/**
 * A collection of tests for border cases (table rows=0 and columns=0).
 *
 * @author Kristian Kutin
 */
public class ModifiableTable2Test {

    /**
     * A table.
     */
    private ModifiableTable<Integer> table;

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        table = new ModifiableTableImpl<Integer>();
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        table = null;
    }

    /**
     * Tests invoking {@link ModifiableTable#rows()}.
     */
    @Test
    public void testRows() {

        assertTrue(0 == table.rows());
    }

    /**
     * Tests invoking {@link ModifiableTable#columns()}.
     */
    @Test
    public void testColumns() {

        assertTrue(0 == table.columns());
    }

    /**
     * Tests invoking {@link ModifiableTable#cells()}.
     */
    @Test
    public void testCells() {

        assertTrue(0 == table.cells());
    }

    /**
     * Tests invoking {@link ModifiableTable#getCell(int,int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetCell() {

        table.getCell(0, 0);
    }

    /**
     * Tests invoking {@link ModifiableTable#getColumnName(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetColumnName() {

        table.getColumnName(0);
    }

    /**
     * Tests the behaviour on adding a new row on an empty table.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddRow() {

        table.addRow();
    }

    /**
     * Tests the behaviour on adding a new column on an empty table.
     */
    @Test
    public void testAddColumn() {

        table.addColumn();
        assertTrue("The table has rows but none are expected!", 0 == table.rows());
        assertTrue("The table is expected to have 1 column!", 1 == table.columns());
        assertTrue("The table is expected to have no cells!", 0 == table.cells());
    }

    /**
     * Tests the behaviour on adding a new column on an empty table.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddColumn2() {

        table.addColumn();
        assertTrue("The table has rows but none are expected!", 0 == table.rows());
        assertTrue("The table is expected to have 1 column!", 1 == table.columns());
        assertTrue("The table is expected to have no cells!", 0 == table.cells());

        table.getCell(0, 0);
    }

    /**
     * Tests the behaviour on removing a row.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveRow() {

        table.removeRow(0);
    }

    /**
     * Tests the behaviour on removing a column.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveColumn() {

        table.removeColumn(0);
    }

}
