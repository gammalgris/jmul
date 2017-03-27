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
 * This interface defines the functionalities of a table with read only
 * access to its data.
 *
 * @param <T>
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
     * @param aRowIndex
     *
     * @return a cell or <code>null</code> if the cell is empty
     */
    T getCell(int aColumnIndex, int aRowIndex);

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
