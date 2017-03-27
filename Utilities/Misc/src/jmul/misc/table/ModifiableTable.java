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

package jmul.misc.table;


/**
 * This interface defines the functionalities of a modifiable table.
 *
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
     */
    void removeColumn(int aColumnIndex);

    /**
     * Removes the specified row.
     *
     * @param aRowIndex
     */
    void removeRow(int aRowIndex);

    /**
     * Cleans the specified cell.
     *
     * @param aColumnIndex
     * @param aRowIndex
     */
    void cleanCell(int aColumnIndex, int aRowIndex);

    /**
     * Changes the content of the specified cell.
     *
     * @param aColumnIndex
     * @param aRowIndex
     * @param aNewValue
     */
    void updateCell(int aColumnIndex, int aRowIndex, T aNewValue);

}
