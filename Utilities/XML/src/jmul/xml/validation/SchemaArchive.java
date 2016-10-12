/*
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

package jmul.xml.validation;


import java.util.List;

import javax.xml.validation.Schema;

import jmul.io.DirectoryDetails;


/**
 * This interfacce describes an archive for XML schemas.
 *
 * @author Kristian Kutin
 */
public interface SchemaArchive extends DirectoryDetails {

    /**
     * A getter method.
     *
     * @param aSchemaKey
     *
     * @return a XML schema
     */
    Schema getSchema(String aSchemaKey);

    /**
     * A getter method.
     *
     * @return all schema keys
     */
    List<String> getSchemaKeys();

}
