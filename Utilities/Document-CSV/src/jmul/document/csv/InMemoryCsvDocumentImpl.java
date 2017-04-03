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

import java.util.List;

import jmul.document.csv.content.HeaderType;
import static jmul.document.csv.content.HeaderType.NO_HEADER;

import jmul.io.NestedStreams;
import jmul.io.text.ReadBuffer;
import jmul.io.text.TextFileHelper;

import jmul.misc.table.ModifiableTable;
import jmul.misc.table.ModifiableTableImpl;
import jmul.misc.table.Table;

import jmul.string.TextHelper;


/**
 * This is an implementation of a CSV document where the file content
 * can be fully loaded into memory.
 *
 * @author Kristian Kutin
 */
public class InMemoryCsvDocumentImpl extends CsvDocumentBase {

    /**
     * The content of the underlying CSV file.
     */
    private Table<String> content;

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
    public InMemoryCsvDocumentImpl(String aFileName, HeaderType aHeaderType, String aColumnSeparator,
                                   String aRowSeparator) throws IOException {

        super(aFileName, aHeaderType, aColumnSeparator, aRowSeparator);

        content = null;
    }

    /**
     * Creates a new document according to the specified parmaeters.
     *
     * @param aFileName
     * @param aColumnSeparator
     * @param aRowSeparator
     * @throws IOException
     */
    public InMemoryCsvDocumentImpl(String aFileName, String aColumnSeparator, String aRowSeparator) throws IOException {

        super(aFileName, aColumnSeparator, aRowSeparator);

        content = null;
    }

    /**
     * Returns the CSV content as table.
     *
     * @return a table
     */
    @Override
    public Table<String> getContent() {

        ModifiableTable<String> tmp = new ModifiableTableImpl<String>();
        //NestedStreams streams = TextFileHelper.openFile(getMetaData().getFileName());

        // TODO Implement this method
        return null;
    }

    /**
     *
     * @param someStreams
     * @param aTable
     * @param aHeaderType
     */
    private void loadHeader(NestedStreams someStreams, ModifiableTable<String> aTable) throws IOException {

        HeaderType headerType = getStructure().getHeaderType();

        if (headerType == NO_HEADER) {

            return;
        }


        String rowSeparator = getStructure().getRowSeparator();
        String columnSeparator = getStructure().getColumnSeparator();

        ReadBuffer result = TextFileHelper.readLine(someStreams, rowSeparator);

        if (result.isEndOfFile()) {

            return;
        }


        String line = result.getLine();
        List<String> substrings = TextHelper.splitLine(line, columnSeparator);

        int columns = substrings.size();

        for (int a = aTable.columns(); a < columns; a++) {


        }
    }

}
