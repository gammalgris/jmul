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

package jmul.xml.validation;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import static jmul.string.Constants.NEW_LINE;


/**
 * This class contains the result of an XML validation.
 *
 * @author Kristian Kutin
 */
public class XmlValidationResult implements Serializable {

    /**
     * The serial UID as required by java's serialization mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The name of the XML file which was validated.
     */
    private final String fileName;

    /**
     * The XML schema.
     */
    private final String schemaName;

    /**
     * The result of the validation.<br>
     * <br>
     * <ul>
     * <li><code>true</code> if the file adheres to the XML schema</li>
     * <li><code>false</code> if the file doesn't adhere to the XML schema</li>
     * </ul>
     */
    private final boolean result;

    /**
     * The error if the validation failes.
     */
    private final Exception error;

    /**
     * Creates a new validation result.
     *
     * @param aFileName
     *        the file which was validated
     * @param aSchemaName
     *        the reference name of the schema
     */
    public XmlValidationResult(String aFileName, String aSchemaName) {

        this(aFileName, aSchemaName, true, null);
    }

    /**
     * Creates a new validation result.
     *
     * @param aFileName
     *        the file which was validated
     * @param aSchemaName
     *        the reference name of the schema
     * @param anError
     *        the validation error
     */
    public XmlValidationResult(String aFileName, String aSchemaName, Exception anError) {

        this(aFileName, aSchemaName, false, anError);
    }

    /**
     * Creates a new validation result.
     *
     * @param aFileName
     *        the file which was validated
     * @param aSchemaName
     *        the reference name of the schema
     * @param aResult
     *        a result flag (<code>true</code> = successful</code>; <code>false</code> = failed)
     * @param anError
     *        the validation error
     */
    private XmlValidationResult(String aFileName, String aSchemaName, boolean aResult, Exception anError) {

        fileName = aFileName;
        schemaName = aSchemaName;
        result = aResult;
        error = anError;
    }

    /**
     * A getter method.
     *
     * @return the name of the XML file which was validated.
     */
    public String getFileName() {

        return fileName;
    }

    /**
     * A getter method.
     *
     * @return the name of an XML schema
     */
    public String getSchemaName() {

        return schemaName;
    }

    /**
     * A getter method.
     *
     * @return <code>true</code> if the XML file adheres to the XML schema, else
     *         <code>false</code>
     */
    public boolean isValid() {

        return result;
    }

    /**
     * A getter method.
     *
     * @return a validation error or <code>null</code> if no error occurred
     */
    public Exception getError() {

        return error;
    }

    /**
     * Checks if there are details on a validation error.
     *
     * @return <code>true</code> if an error has occurred, else <code>false</code>
     */
    public boolean existErrorDetails() {

        return error != null;
    }

    /**
     * A getter method.
     *
     * @return a validation report
     */
    public List<String> getReport() {

        List<String> report = new ArrayList<>();

        report.add("XML file: " + getFileName());
        report.add("XML schema: " + getSchemaName());

        if (existErrorDetails()) {

            report.add("Error: " + getError());
        }

        report.add("file adheres to schema: " + isValid());

        return report;
    }

    /**
     * Returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("\tXML schema: ");
        buffer.append(getSchemaName());
        buffer.append(NEW_LINE);

        buffer.append("\tXML file: ");
        buffer.append(getFileName());
        buffer.append(NEW_LINE);

        if (isValid()) {

            buffer.append("\tResult: file adheres to schema.");
            buffer.append(NEW_LINE);

        } else {

            buffer.append("\tResult: ");
            buffer.append(getError().getMessage());
            buffer.append(NEW_LINE);
        }

        return String.valueOf(buffer);
    }

}
