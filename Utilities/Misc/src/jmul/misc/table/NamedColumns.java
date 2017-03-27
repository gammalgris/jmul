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


import java.util.List;


/**
 * This interface defines the functionalities of a table where columns can
 * have names.
 *
 * @author Kristian Kutin
 */
public interface NamedColumns {

    /**
     * Returns the name of the specified column.
     *
     * @param aColumnIndex
     *
     * @return a column name
     */
    String getColumnName(int aColumnIndex);

    /**
     * Changes the name of the specified column.
     *
     * @param aColumnIndex
     * @param aColumnName
     */
    void setColumnName(int aColumnIndex, String aColumnName);

    /**
     * Returns all column names as a list. The list preserves the order of
     * the columns according to the individual column index.
     *
     * @return a list containing all column names
     */
    List<String> getColumnNames();

}
