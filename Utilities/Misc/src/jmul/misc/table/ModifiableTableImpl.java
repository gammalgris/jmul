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

package jmul.misc.table;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static jmul.checks.ParameterCheckHelper.checkIndex;


/**
 * An implementation of a modifiable table.
 *
 * @param <T> the content type of table cells
 *
 * @author Kristian Kutin
 */
public class ModifiableTableImpl<T> extends TableBase<T> implements ModifiableTable<T> {

    /**
     * A name component.
     */
    private static final String COLUMN_NAME_PREFIX = "column";

    /**
     * The number of columns;
     */
    private int columns;

    /**
     * The number of rows;
     */
    private int rows;

    /**
     * The number of cells.
     */
    private int cells;

    /**
     * The actual table.
     */
    private List<List<T>> table;

    /**
     * The table header.
     */
    private List<String> header;

    /**
     * The default constructor.
     */
    public ModifiableTableImpl() {

        super();

        redimensionTable();
    }

    /**
     * Creates a new table according to the specified parameters.
     *
     * @param aHeader
     *        an array with all column names
     * @param someValues
     *        an array with all initial values
     */
    public ModifiableTableImpl(String[] aHeader, T[][] someValues) {

        super();

        rows = someValues.length;
        columns = aHeader.length;
        updateCells();


        header = new ArrayList<>(Arrays.asList(aHeader));


        List<List<T>> tmpOuter = new ArrayList<>();

        int rowIndex = 0;
        for (T[] inner : someValues) {

            if (inner.length != columns) {

                throw createInvalidRowException(columns, rowIndex, inner.length);
            }

            List<T> tmpInner = new ArrayList<>(Arrays.asList(inner));
            tmpOuter.add(tmpInner);

            rowIndex++;
        }

        table = tmpOuter;
    }

    /**
     * Creates a new table according to the specified parameters.
     *
     * @param aTable
     *        a table with initial values
     */
    public ModifiableTableImpl(Table<T> aTable) {

        super();

        rows = aTable.rows();
        columns = aTable.columns();
        updateCells();


        header = new ArrayList<>(aTable.getColumnNames());


        List<List<T>> tmpOuter = new ArrayList<>();

        for (int r = 0; r < aTable.rows(); r++) {

            List<T> tmpInner = new ArrayList<>();
            for (int c = 0; c < aTable.columns(); c++) {

                tmpInner.add(aTable.getCell(c, r));
            }
            tmpOuter.add(tmpInner);
        }

        table = tmpOuter;
    }

    /**
     * Updates the cell count.
     */
    private void updateCells() {

        cells = rows * columns;
    }

    /**
     * Returns the number of columns of this table.
     *
     * @return the number of columns
     */
    @Override
    public int columns() {

        return columns;
    }

    /**
     * Returns a refrence to the internal table.
     *
     * @return a table
     */
    @Override
    protected List<List<T>> getTable() {

        return table;
    }

    /**
     * Returns the number of rows of this table.
     *
     * @return the number of rows
     */
    @Override
    public int rows() {

        return rows;
    }

    /**
     * Returns the total number of cells within the table.
     *
     * @return a cell count
     */
    @Override
    public int cells() {

        return cells;
    }

    /**
     * Creates a new cell with a default value.
     *
     * @return a new cell
     */
    private T newEmptyCell() {

        return null;
    }

    /**
     * Creates a new row with default values.
     *
     * @param aColumnCount
     *        the number of columns of the new row
     *
     * @return a new row
     */
    private List<T> newRow(int aColumnCount) {

        List<T> newRow = new ArrayList<>();

        for (int a = 0; a < aColumnCount; a++) {

            newRow.add(newEmptyCell());
        }

        return newRow;
    }

    /**
     * Adds a new column to the table.
     */
    @Override
    public void addColumn() {

        header.add(newColumnName(columns));
        columns++;

        for (int a = 0; a < rows; a++) {

            List<T> row = table.get(a);
            row.add(newEmptyCell());
        }

        updateCells();
    }

    /**
     * Adds a new row to the table.
     */
    @Override
    public void addRow() {

        if (columns == 0) {

            String message = "Add some columns first, before adding rows!";
            throw new IllegalArgumentException(message);
        }

        table.add(newRow(columns));

        rows++;
        updateCells();
    }

    /**
     * Returns a columns name according to the specified parameters.
     *
     * @param aColumnIndex
     *        the index of the requested column
     *
     * @return a column name
     */
    private static String newColumnName(int aColumnIndex) {

        return COLUMN_NAME_PREFIX + aColumnIndex;
    }

    /**
     * Removes the specified column.
     *
     * @param aColumnIndex
     *        the index of the column which is to be removed
     */
    @Override
    public void removeColumn(int aColumnIndex) {

        checkIndex(0, aColumnIndex, columns - 1);

        if (columns == 1) {

            redimensionTable();

        } else {

            for (List<T> inner : table) {

                inner.remove(aColumnIndex);
            }

            header.remove(aColumnIndex);

            columns--;

            updateCells();
        }
    }

    /**
     * Removes the specified row.
     *
     * @param aRowIndex
     *        the index of the row which is to be removed
     */
    @Override
    public void removeRow(int aRowIndex) {

        checkIndex(0, aRowIndex, rows - 1);

        if (rows == 1) {

            redimensionTable();

        } else {

            table.remove(aRowIndex);

            rows--;

            updateCells();
        }
    }

    /**
     * The table will be redimensioned to zero rows and zero columns. The
     * header will be changed accordingly.
     */
    private void redimensionTable() {

        table = new ArrayList<>();
        header = new ArrayList<>();
        columns = 0;
        rows = 0;

        updateCells();
    }

    /**
     * Cleans the specified cell.
     *
     * @param aColumnIndex
     *        the column index of a cell
     * @param aRowIndex
     *        the row index of a cell
     */
    @Override
    public void cleanCell(int aColumnIndex, int aRowIndex) {

        checkIndex(0, aColumnIndex, columns - 1);
        checkIndex(0, aRowIndex, rows - 1);

        table.get(aRowIndex).set(aColumnIndex, newEmptyCell());
    }

    /**
     * Changes the content of the specified cell.
     *
     * @param aColumnIndex
     *        the column index of a cell
     * @param aRowIndex
     *        the row index of a cell
     * @param aNewValue
     *        the new value for the cell
     */
    @Override
    public void updateCell(int aColumnIndex, int aRowIndex, T aNewValue) {

        checkIndex(0, aColumnIndex, columns - 1);
        checkIndex(0, aRowIndex, rows - 1);

        table.get(aRowIndex).set(aColumnIndex, aNewValue);
    }

    /**
     * Returns the name of the specified column.
     *
     * @param aColumnIndex
     *        the column index of the requested column
     *
     * @return a column name
     */
    @Override
    public String getColumnName(int aColumnIndex) {

        checkIndex(0, aColumnIndex, columns - 1);

        return header.get(aColumnIndex);
    }

    /**
     * Changes the name of the specified column.
     *
     * @param aColumnIndex
     *        the column index
     * @param aColumnName
     *        the new column name
     */
    @Override
    public void setColumnName(int aColumnIndex, String aColumnName) {

        checkIndex(0, aColumnIndex, columns - 1);

        header.set(aColumnIndex, aColumnName);
    }

    /**
     * Returns all column names as a list. The list preserves the order of
     * the columns according to the individual column index.
     *
     * @return a list containing all column names
     */
    @Override
    public List<String> getColumnNames() {

        return Collections.unmodifiableList(header);
    }

}
