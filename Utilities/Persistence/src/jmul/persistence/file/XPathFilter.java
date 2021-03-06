/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.persistence.file;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;
import jmul.xml.query.XPathQuery;


/**
 * An implementation of a file filter.
 *
 * @author Kristian Kutin
 */
public class XPathFilter implements FileFilter {

    /**
     * Some XPath queries.
     */
    private XPathQuery[] queries;

    /**
     * A document reader.
     */
    private XmlDocumentReader reader;

    /**
     * Constructs a file filter.
     *
     * @param someQueries
     *        all queries which will be performed on all files
     */
    public XPathFilter(XPathQuery... someQueries) {

        reader = new XmlDocumentReaderImpl();
        queries = someQueries;
    }

    /**
     * Checks if the specified file contains the expected value.
     *
     * @param pathname
     *        a file or directory
     *
     * @return <code>true</code> if the specified file matches the expected
     *         filtering criteria, else <code>false</code>
     */
    @Override
    public boolean accept(File pathname) {

        if (pathname.isFile()) {

            Document document = null;

            try {

                document = reader.readFrom(pathname);

            } catch (SAXException | IOException e) {

                // Following cases are considered:
                //
                // 1) If the XML structure is malformed the file cannot be parsed thus the
                // file cannot be processed. By definition the file is not accepted and
                // the exception is ignored.
                //
                // 2) The file cannot be read thus the file cannot be processed. By
                // definition the file is not accepted and the exception is ignored.

                return false;
            }


            boolean found = true;

            for (XPathQuery query : queries) {

                found = found && query.performQuery(document);
            }

            return found;
        }

        return false;
    }

}
