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


import java.util.List;


/**
 * This interface defines the functionalities of a table with read only
 * access to its data.
 *
 * @param <T> the content type of table cells
 *
 * @author Krsitian Kutin
 */
public interface Table<T> extends NamedColumns {

    /**
     * Returns the number of columns of this table.
     *
     * @return the number of columns
     */
    int columns();

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
    T getCell(int aColumnIndex, int aRowIndex);

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
    boolean isEmptyCell(int aColumnIndex, int aRowIndex);

    /**
     * Returns a list of all values of the specified row.
     *
     * @param aRowIndex
     *        the row index of a row
     *
     * @return all row values
     */
    List<T> getRow(int aRowIndex);

    /**
     * Returns a list of all values of the specified column.
     *
     * @param aColumnIndex
     *        the column index of a column
     *
     * @return all column values
     */
    List<T> getColumn(int aColumnIndex);

    /**
     * Returns the number of rows of this table.
     *
     * @return the number of rows
     */
    int rows();

    /**
     * Returns the total number of cells within the table.
     *
     * @return a cell count
     */
    int cells();

}
