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

import jmul.misc.table.ModifiableTable;
import jmul.misc.table.ModifiableTableImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains some tests to check border cases (table rows=1 and columns=1).
 *
 * @author Kristian Kutin
 */
public class ModifiableTable3Test {

    /**
     * A table.
     */
    private ModifiableTable<Integer> table;

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        table = new ModifiableTableImpl<Integer>(new String[] { "numbers" }, new Integer[][] { { 1 } });

        assertTrue(1 == table.rows());
        assertTrue(1 == table.columns());
        assertTrue(1 == table.cells());
        assertTrue(1 == table.getCell(0, 0));
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

        assertTrue(1 == table.rows());
    }

    /**
     * Tests invoking {@link ModifiableTable#columns()}.
     */
    @Test
    public void testColumns() {

        assertTrue(1 == table.columns());
    }

    /**
     * Tests invoking {@link ModifiableTable#cells()}.
     */
    @Test
    public void testCells() {

        assertTrue(1 == table.cells());
    }

    /**
     * Tests invoking {@link ModifiableTable#getCell(int,int)}.
     */
    @Test
    public void testGetCell() {

        assertTrue(1 == table.getCell(0, 0));
    }

    /**
     * Tests invoking {@link ModifiableTable#getColumnName(int)}.
     */
    @Test
    public void testGetColumnName() {

        assertEquals("numbers", table.getColumnName(0));
    }

    /**
     * Tests the behaviour on removing a row.
     */
    @Test
    public void testRemoveRow() {

        table.removeRow(0);
        assertTrue(0 == table.rows());
        assertTrue(0 == table.columns());
        assertTrue(0 == table.cells());

        for (int a = 0; a < 1; a++) {

            for (int b = 0; b < 1; b++) {

                try {

                    table.getCell(a, b);
                    fail();

                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }

    /**
     * Tests the behaviour on removing a column.
     */
    @Test
    public void testRemoveColumn() {

        table.removeColumn(0);
        assertTrue(0 == table.rows());
        assertTrue(0 == table.columns());
        assertTrue(0 == table.cells());

        for (int a = 0; a < 1; a++) {

            for (int b = 0; b < 1; b++) {

                try {

                    table.getCell(a, b);
                    fail();

                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }

}
