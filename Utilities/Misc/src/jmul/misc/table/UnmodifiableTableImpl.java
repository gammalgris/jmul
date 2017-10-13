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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static jmul.misc.checks.ParameterCheckHelper.checkIndex;


/**
 * An implementation of an unmodifiable table.
 *
 * @param <T>
 *
 * @author Kristian Kutin
 */
public class UnmodifiableTableImpl<T> extends TableBase<T> {

    /**
     * The number of columns;
     */
    private final int columns;

    /**
     * The number of rows;
     */
    private final int rows;

    /**
     * The number of cells.
     */
    private final int cells;

    /**
     * The actual table.
     */
    private final List<List<T>> table;

    /**
     * The table header.
     */
    private final List<String> header;

    /**
     * Creates a new table according to the specified parameters.
     *
     * @param aHeader
     * @param someValues
     */
    public UnmodifiableTableImpl(String[] aHeader, T[][] someValues) {

        super();


        rows = someValues.length;
        columns = aHeader.length;
        cells = rows * columns;


        header = Collections.unmodifiableList(Arrays.asList(aHeader));


        List<List<T>> tmpOuter = new ArrayList<>();

        int rowIndex = 0;
        for (T[] inner : someValues) {

            if (inner.length != columns) {

                throw createInvalidRowException(columns, rowIndex, inner.length);
            }

            List<T> tmpInner = Arrays.asList(inner);
            tmpInner = Collections.unmodifiableList(tmpInner);
            tmpOuter.add(tmpInner);

            rowIndex++;
        }

        table = Collections.unmodifiableList(tmpOuter);
    }

    /**
     * Creates a new table according to the specified parameters.
     *
     * @param aTable
     */
    public UnmodifiableTableImpl(Table<T> aTable) {

        super();

        rows = aTable.rows();
        columns = aTable.columns();
        cells = rows * columns;


        header = Collections.unmodifiableList(aTable.getColumnNames());


        List<List<T>> tmpOuter = new ArrayList<>();

        for (int r = 0; r < aTable.rows(); r++) {

            List<T> tmpInner = new ArrayList<>();
            for (int c = 0; c < aTable.columns(); c++) {

                tmpInner.add(aTable.getCell(c, r));
            }
            tmpInner = Collections.unmodifiableList(tmpInner);
            tmpOuter.add(tmpInner);
        }

        table = Collections.unmodifiableList(tmpOuter);
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
     * Returns the name of the specified column.
     *
     * @param aColumnIndex
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
     * @param aColumnName
     */
    @Override
    public void setColumnName(int aColumnIndex, String aColumnName) {

        throw new UnsupportedOperationException("Table cannot be modified!");
    }

    /**
     * Returns all column names as a list. The list preserves the order of
     * the columns according to the individual column index.
     *
     * @return a list containing all column names
     */
    @Override
    public List<String> getColumnNames() {

        return header;
    }

}
