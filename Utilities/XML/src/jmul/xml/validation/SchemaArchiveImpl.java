/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package jmul.xml.validation;


import java.io.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import jmul.io.DirectoryDetailsImpl;
import jmul.io.FileHelper;

import org.xml.sax.SAXException;


/**
 * An implementation of an XML schema archive.
 *
 * @author Kristian Kutin
 */
public class SchemaArchiveImpl extends DirectoryDetailsImpl implements SchemaArchive {

    /**
     * The file suffix for XML schema files.
     */
    private static final String FILE_SUFFIX = "xsd";

    /**
     * A flag which indicates that the directory which contains the XML schemas is searched
     * recursively.
     */
    private static final boolean RECURSE = true;

    /**
     * All XML schemas which could be found.
     */
    private final Map<String, Schema> schemas;

    /**
     * All existing keys.
     */
    private final List<String> keys;

    /**
     * Creates a new XML schema archive according to the specified parameters.
     *
     * @param aDirectoryName
     *        the base directory where all schema are stored
     */
    public SchemaArchiveImpl(String aDirectoryName) {

        super(aDirectoryName, false);


        Map<String, Schema> map = new HashMap<>();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        for (File file : FileHelper.getFiles(aDirectoryName, FILE_SUFFIX, RECURSE)) {

            try {

                Schema schema = schemaFactory.newSchema(file);
                map.put(file.getAbsolutePath(), schema);

            } catch (SAXException e) {

                String message = "Unable to read XML schema " + file.getAbsolutePath() + " (" + e + ")!";
                System.out.println(message);
            }
        }


        if (map.isEmpty()) {

            String message =
                "The specified directory \"" + aDirectoryName +
                "\" doesn't contain XML schemas! No validation is possible!";
            throw new EmptySchemaArchiveException(message);
        }


        List<String> list = new ArrayList<>();

        for (String schluessel : map.keySet()) {

            list.add(schluessel);
        }


        schemas = Collections.unmodifiableMap(map);
        keys = Collections.unmodifiableList(list);
    }

    /**
     * A getter method.
     *
     * @param aSchemaKey
     *        the key which is asocciated with a specific schema
     *
     * @return a XML schema
     */
    @Override
    public Schema getSchema(String aSchemaKey) {

        return schemas.get(aSchemaKey);
    }

    /**
     * A getter method.
     *
     * @return all schema keys
     */
    @Override
    public List<String> getSchemaKeys() {

        return keys;
    }

}
