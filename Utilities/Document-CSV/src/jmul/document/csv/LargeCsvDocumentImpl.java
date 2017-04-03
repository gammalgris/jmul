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

package jmul.document.csv;


import java.io.IOException;

import jmul.document.csv.content.HeaderType;

import jmul.misc.table.Table;


/**
 * This is an implementation of a CSV document where the file content
 * is too large to be loaded into memory.
 *
 * @author Kristian Kutin
 */
public class LargeCsvDocumentImpl extends CsvDocumentBase {

    /**
     * Creates a new document according to the specified parmaeters.
     *
     * @param aFileName
     * @param aHeaderType
     * @param aColumnSeparator
     * @param aRowSeparator
     *
     * @throws IOException
     */
    public LargeCsvDocumentImpl(String aFileName, HeaderType aHeaderType, String aColumnSeparator,
                                String aRowSeparator) throws IOException {

        super(aFileName, aHeaderType, aColumnSeparator, aRowSeparator);
    }

    /**
     * Creates a new document according to the specified parmaeters.
     *
     * @param aFileName
     * @param aColumnSeparator
     * @param aRowSeparator
     * @throws IOException
     */
    public LargeCsvDocumentImpl(String aFileName, String aColumnSeparator, String aRowSeparator) throws IOException {

        super(aFileName, aColumnSeparator, aRowSeparator);
    }

    /**
     * Returns a handle to access the CSV content as table. Internally part
     * of the file content is cached.
     *
     * @return a handle with a table like access to the file data
     */
    @Override
    public Table<String> getContent() {

        // TODO Implement this method
        return null;
    }

}
