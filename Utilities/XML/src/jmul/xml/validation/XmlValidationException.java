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


import java.util.Collection;
import java.util.Collections;

import static jmul.string.Constants.NEW_LINE;


/**
 * This exception is thrown if the XML validation fails. A file may be checked versus
 * several XML schemas.
 *
 * @author Kristian Kutin
 */
public class XmlValidationException extends RuntimeException {

    /**
     * The results of all validations.
     */
    private final Collection<XmlValidationResult> validationResults;

    /**
     * Create a new exception.
     *
     * @param aFileName
     * @param allValidationResults
     */
    public XmlValidationException(String aFileName, Collection<XmlValidationResult> allValidationResults) {

        super(formatMessage(aFileName, allValidationResults));

        validationResults = Collections.unmodifiableCollection(allValidationResults);
    }

    /**
     * A getter method.
     *
     * @return all validation results
     */
    public Collection<XmlValidationResult> getValidationResults() {

        return validationResults;
    }

    /**
     * Returns a formatted error message.
     *
     * @param aFileName
     * @param allValidationResults
     *
     * @return a formatted error message
     */
    private static String formatMessage(String aFileName, Collection<XmlValidationResult> allValidationResults) {

        StringBuilder buffer = new StringBuilder();

        buffer.append(buildErrorMessage(aFileName));
        buffer.append(NEW_LINE);

        for (XmlValidationResult result : allValidationResults) {

            buffer.append(NEW_LINE);
            buffer.append(result);
        }

        return String.valueOf(buffer);
    }

    /**
     * Builds an error message according to the specified parameters.
     *
     * @param aFileName
     *
     * @return an error message
     */
    private static String buildErrorMessage(String aFileName) {

        StringBuilder buffer = new StringBuilder();
        buffer.append("The XML file (");
        buffer.append(aFileName);
        buffer.append(") doesn't adhere to a known XML schema!");

        return buffer.toString();
    }

}
