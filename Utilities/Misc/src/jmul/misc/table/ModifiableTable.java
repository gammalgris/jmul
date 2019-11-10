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


/**
 * This interface defines the functionalities of a modifiable table.
 *
 * @param <T> the content type of table cells
 *
 * @author Kristian Kutin
 */
public interface ModifiableTable<T> extends Table<T> {

    /**
     * Adds a new column to the table.
     */
    void addColumn();

    /**
     * Adds a new row to the table.
     */
    void addRow();

    /**
     * Removes the specified column.
     *
     * @param aColumnIndex
     *        the column index of the column which is to be removed
     */
    void removeColumn(int aColumnIndex);

    /**
     * Removes the specified row.
     *
     * @param aRowIndex
     *        the row index of the row which is to be removed
     */
    void removeRow(int aRowIndex);

    /**
     * Cleans the specified cell.
     *
     * @param aColumnIndex
     *        the column index of a cell
     * @param aRowIndex
     *        the row index of a cell
     */
    void cleanCell(int aColumnIndex, int aRowIndex);

    /**
     * Changes the content of the specified cell.
     *
     * @param aColumnIndex
     *        the column index of a cell
     * @param aRowIndex
     *        the row index of a cell
     * @param aNewValue
     *        the new cell value
     */
    void updateCell(int aColumnIndex, int aRowIndex, T aNewValue);

}
