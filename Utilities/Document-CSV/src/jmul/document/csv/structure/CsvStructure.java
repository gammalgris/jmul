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

package jmul.document.csv.structure;


import java.nio.charset.Charset;

import jmul.document.structure.Structure;
import static jmul.document.csv.structure.HeaderType.NO_HEADER;
import static jmul.document.csv.structure.StructureType.RIGID;


/**
 * This class describes a document structure for CSV files.
 *
 * @author Kristian Kutin
 */
public class CsvStructure implements Structure {

    /**
     * A <code>null</code> string array.
     */
    private static final String[] NULL_STRING_ARRAY = null;

    /**
     * The actual char set.
     */
    private final Charset charset;

    /**
     * The header type.
     */
    private final HeaderType headerType;

    /**
     * The structure type.
     */
    private final StructureType structureType;

    /**
     * The header (including all column names).
     */
    private final String[] header;

    /**
     * The column separator.
     */
    private final String columnSeparator;

    /**
     * The row separator.
     */
    private final String rowSeparator;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aCharset
     *        the charset
     * @param aHeaderType
     *        the header type
     * @param aStructureType
     *        the structure type
     * @param aColumnSeparator
     *        the column separator
     * @param aRowSeparator
     *        the row separator
     * @param someColumnNames
     *        the column names
     */
    public CsvStructure(Charset aCharset, HeaderType aHeaderType, StructureType aStructureType, String aColumnSeparator,
                        String aRowSeparator, String... someColumnNames) {

        charset = aCharset;
        headerType = aHeaderType;
        structureType = aStructureType;
        columnSeparator = aColumnSeparator;
        rowSeparator = aRowSeparator;
        header = someColumnNames;
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aCharset
     *        the charset
     * @param aColumnSeparator
     *        the column separator
     * @param aRowSeparator
     *        the row separator
     */
    public CsvStructure(Charset aCharset, String aColumnSeparator, String aRowSeparator) {

        this(aCharset, NO_HEADER, RIGID, aColumnSeparator, aRowSeparator, NULL_STRING_ARRAY);
    }

    /**
     * Returns the charset for the csv file.
     *
     * @return a char set
     */
    public Charset getCharset() {

        return charset;
    }

    /**
     * Returns the header type for the csv file.
     *
     * @return a header type
     */
    public HeaderType getHeaderType() {

        return headerType;
    }

    /**
     * Returns the structure type for the csv file.
     *
     * @return a structure type
     */
    public StructureType getStructureType() {

        return structureType;
    }

    /**
     * Returns the csv header.
     *
     * @return a header
     */
    public String[] getHeader() {

        return header;
    }

    /**
     * Returns the number of columns.
     *
     * @return number of columns
     */
    public int getColumnCount() {

        return header.length;
    }

    /**
     * Returns a column separator.
     *
     * @return a column separator
     */
    public String getColumnSeparator() {

        return columnSeparator;
    }

    /**
     * Returns a row separator.
     *
     * @return a row separator
     */
    public String getRowSeparator() {

        return rowSeparator;
    }

}
