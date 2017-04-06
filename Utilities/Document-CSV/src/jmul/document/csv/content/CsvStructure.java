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

package jmul.document.csv.content;


import java.nio.charset.Charset;

import jmul.document.content.Structure;
import static jmul.document.csv.content.HeaderType.NO_HEADER;


/**
 * This interface describes a document structure for csv files.
 *
 * @author Kristian Kutin
 */
public class CsvStructure implements Structure {

    /**
     * The actual char set.
     */
    private final Charset charset;

    /**
     * The header type.
     */
    private final HeaderType headerType;

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
     * @param aHeaderType
     * @param aColumnSeparator
     * @param aRowSeparator
     * @param someColumnNames
     */
    public CsvStructure(Charset aCharset, HeaderType aHeaderType, String aColumnSeparator, String aRowSeparator,
                        String... someColumnNames) {

        charset = aCharset;
        headerType = aHeaderType;
        columnSeparator = aColumnSeparator;
        rowSeparator = aRowSeparator;
        header = someColumnNames;
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aCharset
     * @param aColumnSeparator
     * @param aRowSeparator
     */
    public CsvStructure(Charset aCharset, String aColumnSeparator, String aRowSeparator) {

        charset = aCharset;
        headerType = NO_HEADER;
        columnSeparator = aColumnSeparator;
        rowSeparator = aRowSeparator;
        header = null;
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