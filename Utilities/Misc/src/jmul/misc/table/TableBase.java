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

package jmul.misc.table;


import java.util.ArrayList;
import java.util.List;

import static jmul.misc.checks.ParameterCheckHelper.checkIndex;


/**
 * A base implementation for table functions.
 *
 * @param <T> the content type of table cells
 *
 * @author Kristian Kutin
 */
abstract class TableBase<T> implements Table<T> {

    /**
     * The default constructor.
     */
    protected TableBase() {

        super();
    }

    /**
     * Checks if the specified cell is empty (i.e. is <code>null</code>).
     *
     * @param aColumnIndex
     *        the column index of a cell
     * @param aRowIndex
     *        the row index of a cell
     *
     * @return <code>true</code> if the cell is empty (i.e. is <code>null</code>),
     *         else <code>false</code>
     */
    @Override
    public boolean isEmptyCell(int aColumnIndex, int aRowIndex) {

        T cell = getCell(aColumnIndex, aRowIndex);
        return cell == null;
    }

    /**
     * Returns a list of all values of the specified row. Modifications to
     * the returned list don't affect the actual table.
     *
     * @param aRowIndex
     *        the row index of a row
     *
     * @return all row values
     */
    @Override
    public List<T> getRow(int aRowIndex) {

        List<T> result = new ArrayList<>(rows());

        for (int a = 0; a < columns(); a++) {

            T cell = getCell(a, aRowIndex);
            result.add(cell);
        }

        return result;
    }

    /**
     * Returns a list of all values of the specified column. Modifications to
     * the returned list don't affect the actual table.
     *
     * @param aColumnIndex
     *        the column index of a column
     *
     * @return all column values
     */
    @Override
    public List<T> getColumn(int aColumnIndex) {

        List<T> result = new ArrayList<>(columns());

        for (int a = 0; a < rows(); a++) {

            T cell = getCell(aColumnIndex, a);
            result.add(cell);
        }

        return result;
    }

    /**
     * Returns a refrence to the internal table.
     *
     * @return a table
     */
    protected abstract List<List<T>> getTable();

    /**
     * Returns the cell at the specified position. If the cell is empty
     * then <code>null</code> is returned.
     *
     * @param aColumnIndex
     *        the column index of a cell
     * @param aRowIndex
     *        the row index of a cell
     *
     * @return a cell or <code>null</code> if the cell is empty
     */
    @Override
    public T getCell(int aColumnIndex, int aRowIndex) {

        checkIndex(0, aColumnIndex, columns() - 1);
        checkIndex(0, aRowIndex, rows() - 1);

        return getTable().get(aRowIndex).get(aColumnIndex);
    }

    /**
     * Creates an Exception according to the specified parameters.
     *
     * @param aColumnCount
     *        the expected column count
     * @param aRowIndex
     *        the current row index
     * @param anActualColumnCount
     *        the current row's column count
     *
     * @return an exception
     */
    static IllegalArgumentException createInvalidRowException(int aColumnCount, int aRowIndex,
                                                              int anActualColumnCount) {

        StringBuilder buffer = new StringBuilder();
        buffer.append("The specified header (");
        buffer.append(aColumnCount);
        buffer.append(" columns) doesn't match the specified table data (row index ");
        buffer.append(aRowIndex);
        buffer.append("; ");
        buffer.append(anActualColumnCount);
        buffer.append(" columns)!");

        return new IllegalArgumentException(buffer.toString());
    }

}
