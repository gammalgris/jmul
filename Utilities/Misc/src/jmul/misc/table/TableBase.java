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


import java.util.ArrayList;
import java.util.List;


/**
 * A base implementation for table functions.
 *
 * @param <T>
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
     * @param aRowIndex
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
     *
     * @return all row values
     */
    @Override
    public List<T> getRow(int aRowIndex) {

        List<T> result = new ArrayList<T>(rows());

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
     *
     * @return all column values
     */
    @Override
    public List<T> getColumn(int aColumnIndex) {

        List<T> result = new ArrayList<T>(columns());

        for (int a = 0; a < rows(); a++) {

            T cell = getCell(aColumnIndex, a);
            result.add(cell);
        }

        return result;
    }

}
