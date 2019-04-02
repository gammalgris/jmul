/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package jmul.document.csv;


/**
 * A utility class for CSV document handling.
 *
 * @author Kristian Kutin
 */
public final class CsvHelper {

    /**
     * By default the value for an empty cell.
     */
    public static final String EMPTY_CELL = null;

    /**
     * The default constructor.
     */
    private CsvHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Normalizes a value (i.e. replaces an empty cell value with the correpsonding default).
     *
     * @param aValue
     *        a value
     *
     * @return a normalized value
     */
    public static String normalizeValue(String aValue) {

        if ((aValue == null) || aValue.isEmpty()) {

            return EMPTY_CELL;
        }

        return aValue;
    }

}
